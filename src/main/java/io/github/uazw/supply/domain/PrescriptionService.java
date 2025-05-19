package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.exception.InsufficientStorageException;
import io.github.uazw.supply.domain.model.Drug;
import io.github.uazw.supply.domain.model.DrugWithCount;
import io.github.uazw.supply.domain.model.PatientId;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.github.uazw.supply.domain.model.PrescriptionId;
import io.vavr.Tuple;
import io.vavr.collection.List;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionService {

  private final PrescriptionPort prescriptionPort;
  private final PharmacyPort pharmacyPort;
  private final DrugPort drugPort;
  private final AuditPort auditPort;

  @Autowired
  public PrescriptionService(PrescriptionPort prescriptionPort, PharmacyPort pharmacyPort,
                             DrugPort drugPort, AuditPort auditPort) {
    this.prescriptionPort = prescriptionPort;
    this.pharmacyPort = pharmacyPort;
    this.drugPort = drugPort;
    this.auditPort = auditPort;
  }

  @Transactional
  public void create(PharmacyId pharmacyId, PatientId patientId,
                     List<DrugWithCount> drugs) {

    if (pharmacyPort.findBy(pharmacyId).exists(pharmacy ->
        pharmacy.canOffer(drugs)
    )) {
      this.prescriptionPort.create(patientId, pharmacyId, drugs);
    }
  }

  @Transactional
  public void fulfillment(PrescriptionId prescriptionId) {
    prescriptionPort.findBy(prescriptionId)
        .flatMap(prescription ->
            pharmacyPort.findBy(prescription.pharmacyId())
                .map(pharmacy -> Tuple.of(pharmacy, prescription)))
        .forEach(pharmacyPrescriptionTuple2 -> {
          var prescription = pharmacyPrescriptionTuple2._2();
          var pharmacy = pharmacyPrescriptionTuple2._1();

          try {
            if (pharmacy.canOffer(prescription.drugs())) {
              if (!pharmacy.dispense(prescription.drugs())) {
                throw new InsufficientStorageException(pharmacy.getId());
              }

              var map = prescription.drugs().toMap(DrugWithCount::id, DrugWithCount::count);
              var drugs = drugPort.findBy(List.ofAll(map.keySet()));
              drugs.forEach((Drug drug) -> {
                if (!drug.dispense(map.getOrElse(drug.getId(), 0L))) {
                  throw new InsufficientStorageException(drug.getId());
                }
              });

              drugPort.saveAll(drugs);
              pharmacyPort.save(pharmacy);
              prescriptionPort.save(prescription);
            }
            auditPort.success(prescription);
          } catch (Exception ex) {
            auditPort.fail(prescription, ex.getMessage());
          }
        });
  }
}
