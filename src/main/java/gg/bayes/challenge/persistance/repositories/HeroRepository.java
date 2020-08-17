package gg.bayes.challenge.persistance.repositories;

import gg.bayes.challenge.persistance.model.HeroPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface HeroRepository extends JpaRepository<HeroPO, UUID> {
    Optional<HeroPO> findByName(String name);
}
