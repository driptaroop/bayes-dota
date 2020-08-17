package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.persistance.repositories.DamageEventRepository;
import gg.bayes.challenge.persistance.repositories.EventRepository;
import gg.bayes.challenge.persistance.repositories.KillEventRepository;
import gg.bayes.challenge.persistance.repositories.PurchaseEventRepository;
import gg.bayes.challenge.persistance.repositories.SpellEventRepository;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;
import gg.bayes.challenge.service.EventProcessorService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MatchServiceImplTest {
    @Mock
    private EventProcessorService damageEventProcessorService;
    @Mock
    private EventProcessorService spellEventProcessorService;
    @Mock
    private EventProcessorService purchaseEventProcessorService;
    @Mock
    private EventProcessorService killEventProcessorService;
    @Mock
    private KillEventRepository killEventRepository;
    @Mock
    private PurchaseEventRepository purchaseEventRepository;
    @Mock
    private SpellEventRepository spellEventRepository;
    @Mock
    private DamageEventRepository damageEventRepository;
    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    MatchServiceImpl matchService;

    @Test
    void getHeroKillStatTest() {
        Long matchId = RandomUtils.nextLong();
        List<HeroKills> expected = mock(List.class);
        given(killEventRepository.getHeroKillStat(anyLong())).willReturn(expected);

        List<HeroKills> actual = matchService.getHeroKillStat(matchId);

        assertThat(actual).isEqualTo(expected);
        verify(killEventRepository, times(1)).getHeroKillStat(matchId);
    }

    @Test
    void getHeroSpellStatTest() {
        Long matchId = RandomUtils.nextLong();
        String heroName = RandomStringUtils.random(200);
        List<HeroSpells> expected = mock(List.class);
        given(spellEventRepository.getHeroSpellStat(anyLong(), anyString())).willReturn(expected);

        List<HeroSpells> actual = matchService.getHeroSpellStat(matchId, heroName);

        assertThat(actual).isEqualTo(expected);
        verify(spellEventRepository, times(1)).getHeroSpellStat(matchId, heroName);
    }
}
