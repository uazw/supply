package io.github.uazw.supply.domain.model;


import io.vavr.collection.List;

public class Pharmacy {
  private final PharmacyId id;
  private final List<ContractedDrug> contractedDrugs;

  public Pharmacy(PharmacyId id, List<ContractedDrug> contractedDrugs) {
    this.id = id;
    this.contractedDrugs = contractedDrugs;
  }

  public List<ContractedDrug> getContractedDrugs() {
    return contractedDrugs;
  }

  public PharmacyId getId() {
    return id;
  }
}
