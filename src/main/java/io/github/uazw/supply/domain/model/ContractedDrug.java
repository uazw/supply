package io.github.uazw.supply.domain.model;

public class ContractedDrug {
  private long id;
  private final DrugId drugId;
  private final PharmacyId pharmacyId;
  private final long allocated;
  private final long remaining;

  public ContractedDrug(long id, DrugId drugId, PharmacyId pharmacyId, long allocated,
                        long remaining) {
    this.id = id;
    this.drugId = drugId;
    this.pharmacyId = pharmacyId;
    this.allocated = allocated;
    this.remaining = remaining;
  }

  public ContractedDrug withRemaining(long remaining) {
    return new ContractedDrug(id, drugId, pharmacyId, allocated, remaining);
  }


  public long getAllocated() {
    return allocated;
  }

  public DrugId getDrugId() {
    return drugId;
  }

  public long getRemaining() {
    return remaining;
  }

  public long getId() {
    return id;
  }

  public PharmacyId getPharmacyId() {
    return pharmacyId;
  }
}
