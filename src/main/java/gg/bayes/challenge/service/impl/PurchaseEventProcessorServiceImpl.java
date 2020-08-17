package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.persistance.model.EventType;
import gg.bayes.challenge.persistance.model.EventsPO;
import gg.bayes.challenge.persistance.model.PurchaseEventsPO;
import gg.bayes.challenge.persistance.repositories.EventRepository;
import gg.bayes.challenge.service.EventProcessorService;
import gg.bayes.challenge.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service("purchaseEventProcessorService")
public class PurchaseEventProcessorServiceImpl implements EventProcessorService {
    private ItemServiceImpl itemService;
    private HeroServiceImpl heroService;
    private MatchDetailsServiceImpl matchDetailsService;
    private Pattern regex = Pattern.compile("(?<time>.*?) (?<hero>.*?) buys item (?<item>.*)");

    @Autowired
    public PurchaseEventProcessorServiceImpl(ItemServiceImpl itemService,
                                             HeroServiceImpl heroService, MatchDetailsServiceImpl matchDetailsService) {
        this.itemService = itemService;
        this.heroService = heroService;
        this.matchDetailsService = matchDetailsService;
    }
    @Override
    public EventsPO processEvent(String line, Long matchId) {
        Matcher m = regex.matcher(line);
        if(m.find()) {
            PurchaseEventsPO event = PurchaseEventsPO.builder()
                    .eventType(EventType.PURCHASE)
                    .item(itemService.getItemByName(m.group("item")))
                    .match(matchDetailsService.getMatch(matchId))
                    .source(heroService.getHeroByName(m.group("hero")))
                    .timestamp(CommonUtils.gameTimeInMillis(m.group("time")))
                    .build();
            return event;
        }
        return null;
    }
}
