package io.github.uazw.supply.domain.model;


import io.vavr.collection.List;
import io.vavr.control.Option;

public class Pharmacy {
  private final PharmacyId id;
  private List<ContractedDrug> contractedDrugs;

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

  public boolean canOffer(List<DrugWithCount> drugs) {
    var drugAndRemaining =
        contractedDrugs.toMap(ContractedDrug::getDrugId, ContractedDrug::getRemaining);
    return drugs.forAll(
        drugWithCount -> drugAndRemaining.getOrElse(drugWithCount.id(), 0L) >=
            drugWithCount.count()
    );

  }

  public boolean dispense(List<DrugWithCount> drugs) {
    var map = drugs.toMap(DrugWithCount::id, DrugWithCount::count);
    var result = Option.traverse(contractedDrugs, contractedDrug -> {
      var remaining = contractedDrug.getRemaining();
      var count = map.getOrElse(contractedDrug.getDrugId(), 0L);
      if (remaining >= count) {
        return Option.some(contractedDrug.withRemaining(remaining - count));
      } else {
        return Option.none();
      }
    });

    if (result.isDefined()) {
      this.contractedDrugs = result.get().toList();
      return true;
    } else {
      return false;
    }
  }
}
