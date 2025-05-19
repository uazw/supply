package io.github.uazw.supply.controller.dto;

import io.github.uazw.supply.domain.model.DrugId;
import io.github.uazw.supply.domain.model.DrugWithCount;

public record DrugWithCountRequest(long drugId, long count) {
  public DrugWithCount to() {
    return new DrugWithCount(DrugId.from(drugId), count);
  }
}
