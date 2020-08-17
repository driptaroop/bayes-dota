package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.persistance.model.MatchPO;
import gg.bayes.challenge.persistance.repositories.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class MatchDetailsServiceImpl {
    Map<Long, MatchPO> matchCache = new ConcurrentHashMap<>();

    private MatchRepository matchRepository;

    @Autowired
    public MatchDetailsServiceImpl(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public MatchPO getMatch(Long matchId) {
        return matchCache.computeIfAbsent(matchId, id -> matchRepository.findById(id)
                .orElseGet(() -> matchRepository.save(MatchPO.builder().id(id).build())));
    }
}
