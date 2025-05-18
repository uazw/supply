package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.DrugId;
import io.github.uazw.supply.domain.model.DrugStock;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.Instant;

@Entity
@Table(name = "drug_stock")
public final class DrugStockEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  @Column(name = "drug_id")
  private long drugId;
  private String batchNumber;
  private Instant expiredDate;
  private long stock;
  private long remaining;

  public DrugStockEntity() {
  }

  public DrugStockEntity(
      long id,
      long drugId,
      String batchNumber,
      Instant expiredDate,
      long stock,
      long remaining) {
    this.id = id;
    this.drugId = drugId;
    this.batchNumber = batchNumber;
    this.expiredDate = expiredDate;
    this.stock = stock;
    this.remaining = remaining;
  }

  public DrugStock to() {
    return new DrugStock(id, new DrugId(id), batchNumber, expiredDate, stock, remaining);
  }

  public static DrugStockEntity from(DrugStock drugStock) {
    return new DrugStockEntity(drugStock.id(), drugStock.drugId().id(), drugStock.batchNumber(),
        drugStock.expiredDate(), drugStock.stock(), drugStock.remaining());
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getDrugId() {
    return drugId;
  }

  public void setDrugId(long drugId) {
    this.drugId = drugId;
  }

  public String getBatchNumber() {
    return batchNumber;
  }

  public void setBatchNumber(String batchNumber) {
    this.batchNumber = batchNumber;
  }

  public Instant getExpiredDate() {
    return expiredDate;
  }

  public void setExpiredDate(Instant expiredDate) {
    this.expiredDate = expiredDate;
  }

  public long getStock() {
    return stock;
  }

  public void setStock(long stock) {
    this.stock = stock;
  }

  public long getRemaining() {
    return remaining;
  }

  public void setRemaining(long remaining) {
    this.remaining = remaining;
  }
}
