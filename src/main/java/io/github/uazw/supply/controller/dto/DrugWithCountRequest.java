package io.github.uazw.supply.controller.dto;

import io.github.uazw.supply.domain.model.DrugWithCount;
import io.github.uazw.supply.domain.model.DrugId;

public record DrugWithCountRequest(long drugId, long count) {
  public DrugWithCount to() {
    return new DrugWithCount(DrugId.from(drugId), count);
  }
}
