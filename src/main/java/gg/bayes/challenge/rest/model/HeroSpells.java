package gg.bayes.challenge.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HeroSpells {
    private String spell;
    private Integer casts;

    public HeroSpells(String spell, Integer casts) {
        this.spell = spell;
        this.casts = casts;
    }
}
