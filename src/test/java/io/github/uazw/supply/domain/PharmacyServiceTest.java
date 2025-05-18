package io.github.uazw.supply.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.uazw.supply.domain.model.ContractedDrug;
import io.github.uazw.supply.domain.model.Drug;
import io.github.uazw.supply.domain.model.DrugId;
import io.github.uazw.supply.domain.model.Pharmacy;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

class PharmacyServiceTest {

  @Test
  void shouldReturnAllPharmacy() {
    var pharmacyPort = mock(PharmacyPort.class);
    var drugPort = mock(DrugPort.class);
    var pharmacyService = new PharmacyService(pharmacyPort, drugPort);
    var pharmacyId = PharmacyId.from(1L);
    var drugId = new DrugId(1L);
    var contractedDrug = new ContractedDrug(12, drugId, pharmacyId, 123L, 123L);
    when(pharmacyPort.listAll()).thenReturn(
        List.of(new Pharmacy(pharmacyId, List.of(contractedDrug))));
    when(drugPort.findBy(List.of(drugId))).thenReturn(
        List.of(new Drug(drugId, "name", "manufacturer", List.empty())));

    var result = pharmacyService.listAll();

    assertThat(result._1().get(0).getId()).isEqualTo(pharmacyId);
    assertThat(result._2().get(0).getId()).isEqualTo(drugId);

  }
}