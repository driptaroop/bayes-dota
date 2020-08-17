package gg.bayes.challenge.persistance.repositories;

import gg.bayes.challenge.persistance.model.ItemPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ItemRepository extends JpaRepository<ItemPO, UUID> {
    Optional<ItemPO> findByName(String name);
}
