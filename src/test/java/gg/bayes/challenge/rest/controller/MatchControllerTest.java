package gg.bayes.challenge.rest.controller;

import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.service.MatchService;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.apache.commons.lang3.RandomStringUtils.random;
import static org.apache.commons.lang3.RandomUtils.nextInt;
import static org.apache.commons.lang3.RandomUtils.nextLong;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MatchController.class)
class MatchControllerTest {
    @Autowired
    MockMvc mvc;
    @MockBean
    MatchService matchService;

    @Test
    void getMatchTest() throws Exception {
        List<HeroKills> expected = List.of(new HeroKills(random(200), nextInt()));
        long matchId = nextLong();
        given(matchService.getHeroKillStat(anyLong())).willReturn(expected);

        mvc.perform(get("/api/match/" + matchId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].*", hasSize(2)))
                .andExpect(jsonPath("$[0].hero", is(expected.get(0).getHero())))
                .andExpect(jsonPath("$[0].kills", is(expected.get(0).getKills())));

        verify(matchService, times(1)).getHeroKillStat(eq(matchId));
    }
}
