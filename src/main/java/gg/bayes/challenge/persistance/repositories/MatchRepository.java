package gg.bayes.challenge.persistance.repositories;

import gg.bayes.challenge.persistance.model.MatchPO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MatchRepository extends JpaRepository<MatchPO, Long> {

}
