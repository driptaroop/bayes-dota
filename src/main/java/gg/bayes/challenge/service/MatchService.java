package gg.bayes.challenge.service;

import gg.bayes.challenge.rest.model.HeroDamage;
import gg.bayes.challenge.rest.model.HeroItems;
import gg.bayes.challenge.rest.model.HeroKills;
import gg.bayes.challenge.rest.model.HeroSpells;

import java.util.List;

public interface MatchService {
    Long ingestMatch(String payload);

    List<HeroKills> getHeroKillStat(Long matchId);

    List<HeroItems> getHeroPurchaseStat(Long matchId, String heroName);

    List<HeroSpells> getHeroSpellStat(Long matchId, String heroName);

    List<HeroDamage> getHeroDamageStat(Long matchId, String heroName);
}
