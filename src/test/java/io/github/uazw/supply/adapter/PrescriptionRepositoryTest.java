package io.github.uazw.supply.adapter;

import static org.assertj.core.api.Assertions.assertThat;

import io.github.uazw.supply.domain.model.PatientId;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.vavr.collection.List;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@DataJpaTest
@Testcontainers
class PrescriptionRepositoryTest {
  @Container
  @ServiceConnection
  static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:17");


  @Autowired
  PrescriptionRepository prescriptionRepository;

  @Test
  @Transactional
  void shouldAbleToCreateDrug() {

    var prescription =
        prescriptionRepository.create(PatientId.from(1L), PharmacyId.from(1L), List.of());

    assertThat(prescription.prescriptionId()).isNotNull();
  }
}


