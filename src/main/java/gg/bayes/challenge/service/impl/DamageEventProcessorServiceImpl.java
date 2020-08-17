package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.persistance.model.DamageEventsPO;
import gg.bayes.challenge.persistance.model.EventType;
import gg.bayes.challenge.persistance.model.EventsPO;
import gg.bayes.challenge.persistance.repositories.EventRepository;
import gg.bayes.challenge.service.EventProcessorService;
import gg.bayes.challenge.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("damageEventProcessorService")
public class DamageEventProcessorServiceImpl implements EventProcessorService {
    private HeroServiceImpl heroService;
    private MatchDetailsServiceImpl matchDetailsService;
    private Pattern regex = Pattern.compile("(?<time>.*?) (?<hero>.*?) hits (?<target>.*?) with (?<ability>.*?) " +
            "for (?<damage>.*?) damage.*");

    @Autowired
    public DamageEventProcessorServiceImpl(HeroServiceImpl heroService,
                                           MatchDetailsServiceImpl matchDetailsService) {
        this.heroService = heroService;
        this.matchDetailsService = matchDetailsService;
    }

    @Override
    public EventsPO processEvent(String line, Long matchId) {
        Matcher m = regex.matcher(line);
        if (m.find()) {
            DamageEventsPO event = DamageEventsPO.builder()
                    .source(heroService.getHeroByName(m.group("hero")))
                    .target(heroService.getHeroByName(m.group("target")))
                    .timestamp(CommonUtils.gameTimeInMillis(m.group("time")))
                    .eventType(EventType.DAMAGE)
                    .match(matchDetailsService.getMatch(matchId))
                    .damage(Integer.valueOf(m.group("damage")))
                    .ability(m.group("ability"))
                    .build();
            return event;
        }
        return null;
    }
}
