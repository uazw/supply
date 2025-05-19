package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.ContractedDrug;
import io.github.uazw.supply.domain.model.DrugId;
import io.github.uazw.supply.domain.model.PharmacyId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "contracted_drug")
public class ContractedDrugEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  private long drugId;
  @Column(name = "pharmacy_id")
  private long pharmacyId;
  private long allocated;
  private long remaining;

  public ContractedDrugEntity() {
  }

  public ContractedDrugEntity(long drugId, long pharmacyId, long allocated,
                              long remaining) {
    this.drugId = drugId;
    this.pharmacyId = pharmacyId;
    this.allocated = allocated;
    this.remaining = remaining;
  }

  public ContractedDrugEntity(long id, long drugId, long pharmacyId, long allocated,
                              long remaining) {
    this.id = id;
    this.drugId = drugId;
    this.pharmacyId = pharmacyId;
    this.allocated = allocated;
    this.remaining = remaining;
  }

  public ContractedDrug to() {
    return new ContractedDrug(id, DrugId.from(drugId), PharmacyId.from(pharmacyId), allocated,
        remaining);
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

  public long getAllocated() {
    return allocated;
  }

  public void setAllocated(long allocated) {
    this.allocated = allocated;
  }

  public long getRemaining() {
    return remaining;
  }

  public void setRemaining(long remaining) {
    this.remaining = remaining;
  }

  public long getPharmacyId() {
    return pharmacyId;
  }

  public void setPharmacyId(long pharmacyId) {
    this.pharmacyId = pharmacyId;
  }

  public static ContractedDrugEntity from(ContractedDrug contractedDrug) {
    return new ContractedDrugEntity(contractedDrug.getId(), contractedDrug.getDrugId().id(),
        contractedDrug.getPharmacyId().id(), contractedDrug.getAllocated(),
        contractedDrug.getRemaining());
  }
}
