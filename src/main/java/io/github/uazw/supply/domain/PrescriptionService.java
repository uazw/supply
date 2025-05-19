package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.model.DrugWithCount;
import io.github.uazw.supply.domain.model.PatientId;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.vavr.collection.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

  private final PrescriptionPort prescriptionPort;
  private final PharmacyPort pharmacyPort;

  @Autowired
  public PrescriptionService(PrescriptionPort prescriptionPort, PharmacyPort pharmacyPort) {
    this.prescriptionPort = prescriptionPort;
    this.pharmacyPort = pharmacyPort;
  }

  public void create(PharmacyId pharmacyId, PatientId patientId,
                     List<DrugWithCount> drugs) {

    if (pharmacyPort.findBy(pharmacyId).exists(pharmacy ->
        pharmacy.canOffer(drugs)
    )) {
      this.prescriptionPort.create(patientId, pharmacyId, drugs);
    }
  }
}
