package io.github.uazw.supply.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.uazw.supply.domain.model.DrugId;
import io.vavr.collection.List;
import jakarta.transaction.Transactional;
import java.time.Instant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
class DrugRepositoryTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17");

  @Autowired
  DrugRepository drugRepository;

  @Test
  @Transactional
  void shouldAbleToCreateDrug() {

    var drug = drugRepository.create("hello", "world");

    assertThat(drug.getId()).isNotNull();
  }

  @Test
  @Transactional
  void shouldReturnNothingIfDrugNotExists() {

    var drug = drugRepository.findBy("hello", "world");

    assertThat(drug.isEmpty()).isTrue();
  }

  @Test
  @Transactional
  void shouldReturnDrugIfExists() {

    var createdDrug = drugRepository.create("hello", "world");
    var drug = drugRepository.findBy("hello", "world");

    assertThat(drug.isDefined()).isTrue();
    assertThat(drug.get().getId()).isEqualTo(createdDrug.getId());
  }

  @Test
  @Transactional
  void shouldAbleToUpdateDrugWithoutError() {

    var createdDrug = drugRepository.create("hello", "world");
    var now = Instant.now();

    createdDrug.inventory("1", now, 1L);
    drugRepository.save(createdDrug);

    var drug = drugRepository.findBy("hello", "world");
    assertThat(drug.get().getStocks().size()).isEqualTo(1);
  }

  @Test
  @Transactional
  void shouldAbleToReturnDrugById() {

    var createdDrug = drugRepository.create("hello", "world");
    var anotherCreatedDrug = drugRepository.create("konichiwaa", "sekai");

    var drugs = drugRepository.findBy(List.of(createdDrug.getId(), anotherCreatedDrug.getId()));

    assertThat(drugs.size()).isEqualTo(2);
  }

  @Test
  @Transactional
  void shouldReturnEmptyDrugIfDrugDoNotExists() {

    var drugs = drugRepository.findBy(List.of(new DrugId(123312L)));

    assertThat(drugs.size()).isEqualTo(0);
  }
}
