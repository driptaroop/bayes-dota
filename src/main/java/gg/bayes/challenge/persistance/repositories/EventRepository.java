package gg.bayes.challenge.persistance.repositories;

import gg.bayes.challenge.persistance.model.EventsPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventRepository extends JpaRepository<EventsPO, UUID> {

}
