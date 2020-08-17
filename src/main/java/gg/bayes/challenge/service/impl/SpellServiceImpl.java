package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.persistance.model.SpellPO;
import gg.bayes.challenge.persistance.repositories.SpellRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class SpellServiceImpl {

    private Map<SpellId, SpellPO> spellCache = new ConcurrentHashMap<>();

    private SpellRepository spellRepository;

    @Autowired
    public SpellServiceImpl(SpellRepository spellRepository) {
        this.spellRepository = spellRepository;
    }

    public SpellPO getSpellByNameAndLevel(String name, int level) {
        return spellCache.computeIfAbsent(new SpellId(name, level),
                s -> spellRepository.findByNameAndLevel(s.name, s.level)
                .orElseGet(() ->
                        spellRepository.save(SpellPO
                                .builder()
                                .name(s.name)
                                .level(s.level)
                                .build())));
    }

    @Data
    @AllArgsConstructor
    class SpellId{
        private String name;
        private int level;
    }
}
