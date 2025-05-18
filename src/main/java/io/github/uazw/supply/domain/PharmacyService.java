package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.model.ContractedDrug;
import io.github.uazw.supply.domain.model.Drug;
import io.github.uazw.supply.domain.model.Pharmacy;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import io.vavr.collection.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PharmacyService {

  private final PharmacyPort pharmacyPort;
  private final DrugPort drugPort;

  @Autowired
  public PharmacyService(PharmacyPort pharmacyPort, DrugPort drugPort) {
    this.pharmacyPort = pharmacyPort;
    this.drugPort = drugPort;
  }

  @Transactional
  public Tuple2<List<Pharmacy>, List<Drug>> listAll() {
    var pharmacies = pharmacyPort.listAll();
    var drugIds =
        pharmacies.flatMap(Pharmacy::getContractedDrugs).map(ContractedDrug::getDrugId).distinct();
    var drugs = drugPort.findBy(drugIds);
    return Tuple.of(pharmacies, drugs);
  }
}
