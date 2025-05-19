package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.DrugId;
import io.github.uazw.supply.domain.model.DrugWithCount;

public record DrugWithCountEntity(long id, long count) {
  public static DrugWithCountEntity from(DrugWithCount drugWithCount) {
    return new DrugWithCountEntity(drugWithCount.id().id(), drugWithCount.count());
  }

  public DrugWithCount to() {
    return new DrugWithCount(DrugId.from(id), count);
  }
}
