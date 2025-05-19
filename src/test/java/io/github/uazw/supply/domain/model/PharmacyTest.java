package io.github.uazw.supply.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.collection.List;
import org.junit.jupiter.api.Test;

class PharmacyTest {


  @Test
  void shouldReturnTrueAndReduceRemainingIfIsEnough() {
    var pharmacyId = PharmacyId.from(1L);
    var firstDrugId = DrugId.from(1L);
    var secondDrugId = DrugId.from(2L);
    var pharmacy = new Pharmacy(pharmacyId,
        List.of(new ContractedDrug(12, firstDrugId, pharmacyId, 20, 10),
            new ContractedDrug(12, secondDrugId, pharmacyId, 20, 10)));
    var drugWithCounts =
        List.of(new DrugWithCount(firstDrugId, 10), new DrugWithCount(secondDrugId, 5));

    var dispense = pharmacy.dispense(drugWithCounts);

    assertThat(dispense).isTrue();

    assertThat(pharmacy.getContractedDrugs().map(ContractedDrug::getRemaining)).isEqualTo(
        List.of(0L, 5L));
  }

  @Test
  void shouldReturnFalseIfNotEnough() {
    var pharmacyId = PharmacyId.from(1L);
    var firstDrugId = DrugId.from(1L);
    var secondDrugId = DrugId.from(2L);
    var pharmacy = new Pharmacy(pharmacyId,
        List.of(new ContractedDrug(12, firstDrugId, pharmacyId, 20, 10),
            new ContractedDrug(12, secondDrugId, pharmacyId, 20, 10)));
    var drugWithCounts =
        List.of(new DrugWithCount(firstDrugId, 20), new DrugWithCount(secondDrugId, 5));

    var dispense = pharmacy.dispense(drugWithCounts);

    assertThat(dispense).isFalse();

    assertThat(pharmacy.getContractedDrugs().map(ContractedDrug::getRemaining)).isEqualTo(
        List.of(10L, 10L));
  }

  @Test
  void shouldReturnFalseIfOutOfContracted() {
    var pharmacyId = PharmacyId.from(1L);
    var firstDrugId = DrugId.from(1L);
    var secondDrugId = DrugId.from(2L);
    var thirdDrugId = DrugId.from(3L);
    var pharmacy = new Pharmacy(pharmacyId,
        List.of(new ContractedDrug(12, firstDrugId, pharmacyId, 20, 10),
            new ContractedDrug(12, secondDrugId, pharmacyId, 20, 10)));

    var drugWithCounts =
        List.of(new DrugWithCount(firstDrugId, 20), new DrugWithCount(thirdDrugId, 5));

    var dispense = pharmacy.dispense(drugWithCounts);

    assertThat(dispense).isFalse();

    assertThat(pharmacy.getContractedDrugs().map(ContractedDrug::getRemaining)).isEqualTo(
        List.of(10L, 10L));
  }
}