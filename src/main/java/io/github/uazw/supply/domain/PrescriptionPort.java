package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.model.DrugWithCount;
import io.github.uazw.supply.domain.model.PatientId;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.github.uazw.supply.domain.model.Prescription;
import io.vavr.collection.List;

public interface PrescriptionPort {
  Prescription create(PatientId patientId, PharmacyId pharmacyId,
                      List<DrugWithCount> drugs);
}
