package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.DrugStock;
import jakarta.persistence.Entity;

import java.time.Instant;

@Entity
public final class DrugStockEntity {
  private final String batchNumber;
  private final Instant expiredDate;
  private final Long stock;
  private final Long remaining;

  public DrugStockEntity(
      String batchNumber,
      Instant expiredDate,
      Long stock,
      Long remaining) {
    this.batchNumber = batchNumber;
    this.expiredDate = expiredDate;
    this.stock = stock;
    this.remaining = remaining;
  }


  public DrugStock to() {
    return new DrugStock(batchNumber, expiredDate, stock, remaining);
  }

  public static DrugStockEntity from(DrugStock drugStock) {
    return new DrugStockEntity(drugStock.batchNumber(), drugStock.expiredDate(), drugStock.stock(), drugStock.remaining());
  }
}
