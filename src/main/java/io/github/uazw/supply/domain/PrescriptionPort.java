package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.model.DrugWithCount;
import io.github.uazw.supply.domain.model.PatientId;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.github.uazw.supply.domain.model.Prescription;
import io.github.uazw.supply.domain.model.PrescriptionId;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface PrescriptionPort {
  Prescription create(PatientId patientId, PharmacyId pharmacyId,
                      List<DrugWithCount> drugs);

  Option<Prescription> findBy(PrescriptionId prescriptionId);

  Prescription save(Prescription prescription);
}
