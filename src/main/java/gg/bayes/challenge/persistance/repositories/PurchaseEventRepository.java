package gg.bayes.challenge.persistance.repositories;

import gg.bayes.challenge.persistance.model.PurchaseEventsPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PurchaseEventRepository extends JpaRepository<PurchaseEventsPO, UUID> {
    List<PurchaseEventsPO> findAllByMatch_idAndSource_name(Long id, String name);
}
