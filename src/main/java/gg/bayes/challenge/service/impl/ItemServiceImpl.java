package gg.bayes.challenge.service.impl;

import gg.bayes.challenge.persistance.model.ItemPO;
import gg.bayes.challenge.persistance.repositories.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ItemServiceImpl {
    Map<String, ItemPO> itemCache = new ConcurrentHashMap<>();

    private ItemRepository itemRepository;

    @Autowired
    public ItemServiceImpl(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public ItemPO getItemByName(String name){
        return itemCache.computeIfAbsent(name, s -> itemRepository.findByName(s)
                .orElseGet(() -> itemRepository.save(ItemPO.builder().name(s).build())));
    }
}
