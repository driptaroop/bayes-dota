package gg.bayes.challenge.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dota_events_damage")
@PrimaryKeyJoinColumn(name = "event")
public class DamageEventsPO extends EventsPO {
    @ManyToOne(cascade = {MERGE})
    @JoinColumn(name = "target", nullable = false)
    private HeroPO target;
    private Integer damage;
    private String ability;
}
