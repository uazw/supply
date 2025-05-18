package io.github.uazw.supply.controller;

import io.github.uazw.supply.controller.dto.ContractedDrugResponse;
import io.github.uazw.supply.controller.dto.ListPharmacyResponse;
import io.github.uazw.supply.domain.PharmacyService;
import io.github.uazw.supply.domain.model.Drug;
import java.util.List;
import java.util.function.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyAdapter {

  private final PharmacyService pharmacyService;

  @Autowired
  public PharmacyAdapter(PharmacyService pharmacyService) {
    this.pharmacyService = pharmacyService;
  }

  public List<ListPharmacyResponse> listAll() {
    var result = pharmacyService.listAll();
    var drugs = result._2().toMap(Drug::getId, Function.identity());
    return result._1.map(pharmacy ->
        new ListPharmacyResponse(
            pharmacy.getId().id(),
            pharmacy.getContractedDrugs().map(drug -> {
              var found = drugs.get(drug.getDrugId());
              return new ContractedDrugResponse(found.getOrNull().getName(),
                  found.getOrNull().getManufacturer(), drug.getAllocated(), drug.getRemaining());
            }).toJavaList()
        )
    ).toJavaList();
  }
}
