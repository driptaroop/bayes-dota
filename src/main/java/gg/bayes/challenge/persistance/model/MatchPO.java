package gg.bayes.challenge.persistance.model;

import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "dota_match")
public class MatchPO {
    @Id
    private Long id;

    @OneToMany(mappedBy = "match", fetch = FetchType.LAZY)
    private Set<EventsPO> events;
}
