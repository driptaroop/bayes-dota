package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.persistance.model.EventType;
import gg.bayes.challenge.persistance.model.EventsPO;
import gg.bayes.challenge.persistance.model.KillEventsPO;
import gg.bayes.challenge.persistance.repositories.EventRepository;
import gg.bayes.challenge.service.EventProcessorService;
import gg.bayes.challenge.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("killEventProcessorService")
public class KillEventProcessorServiceImpl implements EventProcessorService {
    private HeroServiceImpl heroService;
    private MatchDetailsServiceImpl matchDetailsService;
    private Pattern regex = Pattern.compile("(?<time>.*?) (?<target>.*?) is killed by (?<source>.*)");

    @Autowired
    public KillEventProcessorServiceImpl(HeroServiceImpl heroService,
                                         MatchDetailsServiceImpl matchDetailsService) {
        this.heroService = heroService;
        this.matchDetailsService = matchDetailsService;
    }
    @Override
    public EventsPO processEvent(String line, Long matchId) {
        Matcher m = regex.matcher(line);
        if(m.find()) {
            KillEventsPO event = KillEventsPO.builder()
                    .source(heroService.getHeroByName(m.group("source")))
                    .target(heroService.getHeroByName(m.group("target")))
                    .timestamp(CommonUtils.gameTimeInMillis(m.group("time")))
                    .eventType(EventType.KILL)
                    .match(matchDetailsService.getMatch(matchId))
                    .build();
            return event;
        }
        return null;
    }

}
