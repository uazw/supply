package io.github.uazw.supply.domain.model;

import java.time.Instant;

public final class DrugStock {
  private long id;
  private final DrugId drugId;
  private final String batchNumber;
  private final Instant expiredDate;
  private final long stock;
  private final long remaining;

  public DrugStock(
      DrugId drugId,
      String batchNumber,
      Instant expiredDate,
      long stock,
      long remaining) {
    this.drugId = drugId;
    this.batchNumber = batchNumber;
    this.expiredDate = expiredDate;
    this.stock = stock;
    this.remaining = remaining;
  }


  public DrugStock(long id, DrugId drugId, String batchNumber, Instant expiredDate, long stock,
                   long remaining) {
    this.drugId = drugId;
    this.batchNumber = batchNumber;
    this.expiredDate = expiredDate;
    this.stock = stock;
    this.remaining = remaining;
    this.id = id;
  }

  public DrugStock withRemaining(long remaining) {
    return new DrugStock(drugId, batchNumber, expiredDate, stock, remaining);
  }

  public long id() {
    return id;
  }

  public DrugId drugId() {
    return drugId;
  }

  public String batchNumber() {
    return batchNumber;
  }

  public Instant expiredDate() {
    return expiredDate;
  }

  public long stock() {
    return stock;
  }

  public long remaining() {
    return remaining;
  }

}
