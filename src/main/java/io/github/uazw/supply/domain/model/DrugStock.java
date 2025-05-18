package io.github.uazw.supply.domain.model;

import java.time.Instant;

public final class DrugStock {
  private Long id;
  private final DrugId drugId;
  private final String batchNumber;
  private final Instant expiredDate;
  private final Long stock;
  private final Long remaining;

  public DrugStock(
      DrugId drugId,
      String batchNumber,
      Instant expiredDate,
      Long stock,
      Long remaining) {
    this.drugId = drugId;
    this.batchNumber = batchNumber;
    this.expiredDate = expiredDate;
    this.stock = stock;
    this.remaining = remaining;
  }

  public DrugStock(Long id, DrugId drugId, String batchNumber, Instant expiredDate, Long stock,
                   Long remaining) {
    this.drugId = drugId;
    this.batchNumber = batchNumber;
    this.expiredDate = expiredDate;
    this.stock = stock;
    this.remaining = remaining;
    this.id = id;
  }

  public Long id() {
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

  public Long stock() {
    return stock;
  }

  public Long remaining() {
    return remaining;
  }

}
