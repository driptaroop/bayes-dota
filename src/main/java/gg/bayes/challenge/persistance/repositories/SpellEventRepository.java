package gg.bayes.challenge.persistance.repositories;

import gg.bayes.challenge.persistance.model.SpellEventsPO;
import gg.bayes.challenge.rest.model.HeroSpells;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface SpellEventRepository extends JpaRepository<SpellEventsPO, UUID> {
    @Query("select new gg.bayes.challenge.rest.model.HeroSpells(s.spell.name, CAST(count(s.id) as int)) " +
            "from SpellEventsPO s where s.match.id=:matchId and s.source.name=:heroName " +
            "group by s.spell.name")
    List<HeroSpells> getHeroSpellStat(Long matchId, String heroName);
}
