package io.github.uazw.supply.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.uazw.supply.adapter.entity.ContractedDrugEntity;
import io.github.uazw.supply.adapter.entity.PharmacyEntity;
import jakarta.transaction.Transactional;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
class PharmacyRepositoryTest {

  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17");

  @Autowired
  PharmacyRepository pharmacyRepository;


  @Test
  @Transactional
  void shouldAbleToFetchAllOfPharmacy() {
    var contracted = new ContractedDrugEntity(123L, 123L, 20L, 20L);
    pharmacyRepository.saveAll(List.of(
        new PharmacyEntity(List.of(contracted)),
        new PharmacyEntity(List.of(contracted))));

    var pharmacies = pharmacyRepository.listAll();

    assertThat(pharmacies.size()).isEqualTo(2);
  }

  @Test
  @Transactional
  void shouldReturnEmptyListIfPharmacyDoNotExists() {

    var pharmacies = pharmacyRepository.listAll();

    assertThat(pharmacies.size()).isEqualTo(0);
  }
}