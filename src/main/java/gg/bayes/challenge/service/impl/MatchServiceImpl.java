package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.persistance.model.EventsPO;
import gg.bayes.challenge.persistance.repositories.DamageEventRepository;
import gg.bayes.challenge.persistance.repositories.EventRepository;
import gg.bayes.challenge.persistance.repositories.KillEventRepository;
import gg.bayes.challenge.persistance.repositories.PurchaseEventRepository;
import gg.bayes.challenge.persistance.repositories.SpellEventRepository;
import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.EventProcessorService;
import gg.bayes.challenge.service.MatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MatchServiceImpl implements MatchService {
    private EventProcessorService damageEventProcessorService;
    private EventProcessorService spellEventProcessorService;
    private EventProcessorService purchaseEventProcessorService;
    private EventProcessorService killEventProcessorService;
    private KillEventRepository killEventRepository;
    private PurchaseEventRepository purchaseEventRepository;
    private SpellEventRepository spellEventRepository;
    private DamageEventRepository damageEventRepository;
    private EventRepository eventRepository;

    @Autowired
    public MatchServiceImpl(EventProcessorService damageEventProcessorService,
                            EventProcessorService spellEventProcessorService,
                            EventProcessorService purchaseEventProcessorService,
                            EventProcessorService killEventProcessorService,
                            KillEventRepository killEventRepository,
                            PurchaseEventRepository purchaseEventRepository,
                            SpellEventRepository spellEventRepository,
                            DamageEventRepository damageEventRepository,
                            EventRepository eventRepository) {
        this.damageEventProcessorService = damageEventProcessorService;
        this.spellEventProcessorService = spellEventProcessorService;
        this.purchaseEventProcessorService = purchaseEventProcessorService;
        this.killEventProcessorService = killEventProcessorService;
        this.killEventRepository = killEventRepository;
        this.purchaseEventRepository = purchaseEventRepository;
        this.spellEventRepository = spellEventRepository;
        this.damageEventRepository = damageEventRepository;
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public Long ingestMatch(String payload) {
        long matchId = System.currentTimeMillis();
        List<EventsPO> events = payload.lines().parallel()
                .map(s -> processLines(s, matchId))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        eventRepository.saveAll(events);
        return matchId;
    }

    @Override
    public List<HeroKills> getHeroKillStat(Long matchId) {
        return killEventRepository.getHeroKillStat(matchId);
    }

    @Override
    public List<HeroItems> getHeroPurchaseStat(Long matchId, String heroName) {
        return purchaseEventRepository.findAllByMatch_idAndSource_name(matchId, heroName)
                .stream()
                .map(p -> new HeroItems(p.getItem().getName(), p.getTimestamp()))
                .collect(Collectors.toList());
    }

    @Override
    public List<HeroSpells> getHeroSpellStat(Long matchId, String heroName) {
        return spellEventRepository.getHeroSpellStat(matchId, heroName);
    }

    @Override
    public List<HeroDamage> getHeroDamageStat(Long matchId, String heroName) {
        return damageEventRepository.getHeroDamageStat(matchId, heroName);
    }

    private EventsPO processLines(String line, long matchId) {
        if (line.contains("casts ability")) {
            return spellEventProcessorService.processEvent(line, matchId);
        } else if (line.contains("buys item")) {
            return purchaseEventProcessorService.processEvent(line, matchId);
        } else if (line.contains("hits")) {
            return damageEventProcessorService.processEvent(line, matchId);
        } else if (line.contains("is killed by")) {
            return killEventProcessorService.processEvent(line, matchId);
        }
        return null;
    }
}
