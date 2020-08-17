package gg.bayes.challenge.persistance.repositories;

import gg.bayes.challenge.persistance.model.DamageEventsPO;
import gg.bayes.challenge.rest.model.HeroDamage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface DamageEventRepository extends JpaRepository<DamageEventsPO, UUID> {
    @Query("select new gg.bayes.challenge.rest.model.HeroDamage(d.target.name, " +
            "CAST(count(d.id) as int), CAST(sum(d.damage) as int)) " +
            "from DamageEventsPO d where d.match.id=:matchId and d.source.name=:heroName " +
            " group by d.target.name")
    List<HeroDamage> getHeroDamageStat(Long matchId, String heroName);
}
