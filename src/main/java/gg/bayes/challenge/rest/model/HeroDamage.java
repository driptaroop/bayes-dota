package gg.bayes.challenge.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HeroDamage {
    private String target;
    @JsonProperty("damage_instances")
    private Integer damageInstances;
    @JsonProperty("total_damage")
    private Integer totalDamage;

    public HeroDamage(String target, Integer damageInstances, Integer totalDamage) {
        this.target = target;
        this.damageInstances = damageInstances;
        this.totalDamage = totalDamage;
    }
}
