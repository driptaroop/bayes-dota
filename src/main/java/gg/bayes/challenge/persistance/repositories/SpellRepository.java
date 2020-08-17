package gg.bayes.challenge.persistance.repositories;

import gg.bayes.challenge.persistance.model.ItemPO;
import gg.bayes.challenge.persistance.model.SpellPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SpellRepository extends JpaRepository<SpellPO, UUID> {
    Optional<SpellPO> findByNameAndLevel(String name, int level);
}
