package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.persistance.model.HeroPO;
import gg.bayes.challenge.persistance.repositories.HeroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class HeroServiceImpl {

    Map<String, HeroPO> heroCache = new ConcurrentHashMap<>();

    private HeroRepository heroRepository;

    @Autowired
    public HeroServiceImpl(HeroRepository heroRepository) {
        this.heroRepository = heroRepository;
    }

    public HeroPO getHeroByName(String name) {
        return heroCache.computeIfAbsent(name, s -> heroRepository.findByName(s)
                .orElseGet(() -> heroRepository.save(HeroPO.builder().name(s).build())));
    }
}
