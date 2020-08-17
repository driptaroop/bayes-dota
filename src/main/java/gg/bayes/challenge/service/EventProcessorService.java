package gg.bayes.challenge.service;

import gg.bayes.challenge.persistance.model.EventsPO;

public interface EventProcessorService {
    EventsPO processEvent(String line, Long matchId);
}
