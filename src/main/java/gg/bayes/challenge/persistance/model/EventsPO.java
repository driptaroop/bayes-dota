package gg.bayes.challenge.persistance.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Type;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;
import static javax.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "dota_events")
@Inheritance(strategy = InheritanceType.JOINED)
public class EventsPO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Type(type="uuid-char")
    private UUID id;

    private long timestamp;

    @ManyToOne(cascade = {MERGE})
    @JoinColumn(name = "match", nullable = false)
    private MatchPO match;

    @ManyToOne(cascade = {MERGE})
    @JoinColumn(name = "source", nullable = false)
    private HeroPO source;

    @Enumerated(STRING)
    private EventType eventType;
}
