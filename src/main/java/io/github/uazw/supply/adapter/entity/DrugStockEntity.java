package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.DrugId;
import io.github.uazw.supply.domain.model.DrugStock;
import jakarta.persistence.*;

import java.time.Instant;

@Entity
@Table(name = "drug_stock")
public final class DrugStockEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private Long id;
  @Column(name = "drug_id")
  private Long drugId;
  private String batchNumber;
  private Instant expiredDate;
  private Long stock;
  private Long remaining;

  public DrugStockEntity() {
  }

  public DrugStockEntity(
      Long id,
      Long drugId,
      String batchNumber,
      Instant expiredDate,
      Long stock,
      Long remaining) {
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
    return new DrugStockEntity(drugStock.id(), drugStock.drugId().id(), drugStock.batchNumber(), drugStock.expiredDate(), drugStock.stock(), drugStock.remaining());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getDrugId() {
    return drugId;
  }

  public void setDrugId(Long drugId) {
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

  public Long getStock() {
    return stock;
  }

  public void setStock(Long stock) {
    this.stock = stock;
  }

  public Long getRemaining() {
    return remaining;
  }

  public void setRemaining(Long remaining) {
    this.remaining = remaining;
  }
}
