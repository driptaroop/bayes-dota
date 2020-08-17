package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.persistance.model.EventType;
import gg.bayes.challenge.persistance.model.EventsPO;
import gg.bayes.challenge.persistance.model.SpellEventsPO;
import gg.bayes.challenge.persistance.repositories.EventRepository;
import gg.bayes.challenge.service.EventProcessorService;
import gg.bayes.challenge.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("spellEventProcessorService")
public class SpellEventProcessorServiceImpl implements EventProcessorService {
    private HeroServiceImpl heroService;
    private SpellServiceImpl spellService;
    private MatchDetailsServiceImpl matchDetailsService;
    private Pattern regex = Pattern.compile("(?<time>.*?) (?<hero>.*?) casts ability (?<spell>.*?) \\(lvl (?<lvl>" +
            ".*?)\\) on (?<target>.*)");

    @Autowired
    public SpellEventProcessorServiceImpl(HeroServiceImpl heroService,
                                          SpellServiceImpl spellService, MatchDetailsServiceImpl matchDetailsService) {
        this.heroService = heroService;
        this.spellService = spellService;
        this.matchDetailsService = matchDetailsService;
    }

    @Override
    public EventsPO processEvent(String line, Long matchId) {
        Matcher m = regex.matcher(line);
        if(m.find()) {
            SpellEventsPO event = SpellEventsPO.builder()
                    .spell(spellService.getSpellByNameAndLevel(m.group("spell"), Integer.parseInt(m.group("lvl"))))
                    .eventType(EventType.SPELL)
                    .match(matchDetailsService.getMatch(matchId))
                    .source(heroService.getHeroByName(m.group("hero")))
                    .target(heroService.getHeroByName(m.group("target")))
                    .timestamp(CommonUtils.gameTimeInMillis(m.group("time")))
                    .build();
            return event;
        }
        return null;
    }
}
