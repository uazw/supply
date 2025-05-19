package io.github.uazw.supply.domain.model;


import io.vavr.collection.List;

public record Prescription(
    PrescriptionId prescriptionId,
    PatientId patientId,
    PharmacyId pharmacyId,
    List<PrescriptionDrug> drugs
) {
}
