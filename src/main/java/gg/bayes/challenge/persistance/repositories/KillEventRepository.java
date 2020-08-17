package gg.bayes.challenge.persistance.repositories;

import gg.bayes.challenge.persistance.model.KillEventsPO;
import gg.bayes.challenge.rest.model.HeroKills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface KillEventRepository extends JpaRepository<KillEventsPO, UUID> {
    @Query("select new gg.bayes.challenge.rest.model.HeroKills(k.source.name, CAST(count(k.id) as int))" +
            "from KillEventsPO k where k.match.id=:matchId group by k.source.name")
    List<HeroKills> getHeroKillStat(Long matchId);
}
