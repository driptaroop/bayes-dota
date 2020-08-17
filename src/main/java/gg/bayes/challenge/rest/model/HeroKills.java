package gg.bayes.challenge.rest.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HeroKills {
    private String hero;
    private Integer kills;

    public HeroKills(String hero, Integer kills) {
        this.hero = hero;
        this.kills = kills;
    }
}
