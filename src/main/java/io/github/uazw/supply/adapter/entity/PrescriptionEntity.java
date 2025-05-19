package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.PatientId;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.github.uazw.supply.domain.model.Prescription;
import io.github.uazw.supply.domain.model.PrescriptionId;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Entity
@Table(name = "prescription")
public class PrescriptionEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  private final long patientId;
  private final long pharmacyId;

  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "drugs", columnDefinition = "jsonb")
  private final List<DrugWithCountEntity> drugs;

  public PrescriptionEntity(long patientId, long pharmacyId, List<DrugWithCountEntity> drugs) {

    this.patientId = patientId;
    this.pharmacyId = pharmacyId;
    this.drugs = drugs;
  }

  public PrescriptionEntity(long patientId, long pharmacyId, List<DrugWithCountEntity> drugs,
                            long id) {
    this.patientId = patientId;
    this.pharmacyId = pharmacyId;
    this.drugs = drugs;
    this.id = id;
  }

  public static PrescriptionEntity from(Prescription prescription) {
    return new PrescriptionEntity(prescription.patientId().id(), prescription.pharmacyId().id(),
        prescription.drugs().map(DrugWithCountEntity::from).toJavaList(),
        prescription.prescriptionId().id());
  }

  public Prescription to() {
    return new Prescription(PrescriptionId.from(id), PatientId.from(patientId),
        PharmacyId.from(pharmacyId),
        io.vavr.collection.List.ofAll(drugs.stream().map(DrugWithCountEntity::to)));
  }
}
