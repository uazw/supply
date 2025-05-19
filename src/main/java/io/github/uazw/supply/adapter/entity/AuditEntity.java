package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.Prescription;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

@Table(name = "audit")
@Entity
public class AuditEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;
  private long prescriptionId;
  private long patientId;
  private long pharmacyId;
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "requested", columnDefinition = "jsonb")
  private List<Long> requested;
  @JdbcTypeCode(SqlTypes.JSON)
  @Column(name = "dispensed", columnDefinition = "jsonb")
  private List<Long> dispensed;
  private String reasons;
  private boolean success;

  public AuditEntity() {
  }

  public AuditEntity(long prescriptionId, long patientId, long pharmacyId,
                     List<Long> requested, List<Long> dispensed, String reasons, boolean success) {
    this.prescriptionId = prescriptionId;
    this.patientId = patientId;
    this.pharmacyId = pharmacyId;
    this.requested = requested;
    this.dispensed = dispensed;
    this.reasons = reasons;
    this.success = success;
  }

  public static AuditEntity success(Prescription prescription) {
    var drugIds = prescription.drugs().map(drugWithCount -> drugWithCount.id().id()).toJavaList();
    return new AuditEntity(prescription.prescriptionId().id(), prescription.patientId().id(),
        prescription.pharmacyId().id(), drugIds, drugIds, "", true);
  }

  public static AuditEntity fail(Prescription prescription, String message) {
    var drugIds = prescription.drugs().map(drugWithCount -> drugWithCount.id().id()).toJavaList();
    return new AuditEntity(prescription.prescriptionId().id(), prescription.patientId().id(),
        prescription.pharmacyId().id(), drugIds, List.of(), message, false);
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getPrescriptionId() {
    return prescriptionId;
  }

  public void setPrescriptionId(long prescriptionId) {
    this.prescriptionId = prescriptionId;
  }

  public long getPatientId() {
    return patientId;
  }

  public void setPatientId(long patientId) {
    this.patientId = patientId;
  }

  public long getPharmacyId() {
    return pharmacyId;
  }

  public void setPharmacyId(long pharmacyId) {
    this.pharmacyId = pharmacyId;
  }

  public List<Long> getRequested() {
    return requested;
  }

  public void setRequested(List<Long> requested) {
    this.requested = requested;
  }

  public List<Long> getDispensed() {
    return dispensed;
  }

  public void setDispensed(List<Long> dispensed) {
    this.dispensed = dispensed;
  }

  public String getReasons() {
    return reasons;
  }

  public void setReasons(String reasons) {
    this.reasons = reasons;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }
}
