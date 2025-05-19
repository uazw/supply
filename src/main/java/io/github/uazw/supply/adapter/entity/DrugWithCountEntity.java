package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.DrugId;
import io.github.uazw.supply.domain.model.DrugWithCount;
import io.github.uazw.supply.domain.model.PrescriptionDrug;

public record DrugWithCountEntity(long id, long count) {
  public static DrugWithCountEntity from(DrugWithCount drugWithCount) {
    return new DrugWithCountEntity(drugWithCount.id().id(), drugWithCount.count());
  }

  public PrescriptionDrug to() {
    return new PrescriptionDrug(DrugId.from(id), count);
  }
}
