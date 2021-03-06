package gg.bayes.challenge.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HeroItems {
    private String item;
    private Long timestamp;

    public HeroItems(String item, Long timestamp) {
        this.item = item;
        this.timestamp = timestamp;
    }
}
