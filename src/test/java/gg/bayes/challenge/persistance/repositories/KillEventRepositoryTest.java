package gg.bayes.challenge.persistance.repositories;

import gg.bayes.challenge.rest.model.HeroKills;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;

import javax.sql.DataSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class KillEventRepositoryTest {
    @Autowired
    private DataSource dataSource;
    @Autowired
    private KillEventRepository repository;

    @Test
    void TestIfDataSourceConfigExists(){
        assertThat(dataSource).isNotNull();
        assertThat(repository).isNotNull();
    }

    @Test
    @Sql("classpath:KillEvent.sql")
    void getHeroKillStatTest() {
        List<HeroKills> heroKills = repository.getHeroKillStat(1597653254777L);
        assertThat(heroKills).hasSize(1);
        assertThat(heroKills.get(0).getHero()).isEqualTo("npc_dota_creep_goodguys_melee");
        assertThat(heroKills.get(0).getKills()).isEqualTo(1);
    }
}
