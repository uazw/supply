package io.github.uazw.supply.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import io.github.uazw.supply.controller.dto.ContractedDrugResponse;
import io.github.uazw.supply.controller.dto.ListPharmacyResponse;
import io.github.uazw.supply.domain.PharmacyService;
import io.github.uazw.supply.domain.model.ContractedDrug;
import io.github.uazw.supply.domain.model.Drug;
import io.github.uazw.supply.domain.model.DrugId;
import io.github.uazw.supply.domain.model.Pharmacy;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.vavr.Tuple;
import java.util.List;
import org.junit.jupiter.api.Test;

class PharmacyAdapterTest {

  @Test
  void shouldReturnAllPharmacy() {
    var pharmacyService = mock(PharmacyService.class);
    var pharmacyAdapter = new PharmacyAdapter(pharmacyService);
    var pharmacyId = 12L;
    var drugName = "drugName";
    var manufacturer = "manufacturer";
    var drugId = new DrugId(456L);

    when(pharmacyService.listAll()).thenReturn(
        Tuple.of(io.vavr.collection.List.of(
            new Pharmacy(PharmacyId.from(pharmacyId), io.vavr.collection.List.of(
                new ContractedDrug(123L, drugId, PharmacyId.from(pharmacyId), 123L, 123L)
            ))), io.vavr.collection.List.of(
              new Drug(drugId, drugName, manufacturer, io.vavr.collection.List.empty()))));

    var responses = pharmacyAdapter.listAll();

    assertThat(responses).isEqualTo(
        List.of(new ListPharmacyResponse(pharmacyId,
            List.of(new ContractedDrugResponse(drugName, manufacturer, 123L, 123L)))));
  }
}