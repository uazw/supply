package io.github.uazw.supply.adapter.spec;

import io.github.uazw.supply.adapter.entity.AuditEntity;
import org.springframework.data.jpa.domain.Specification;

public class AuditSpec {

  public static Specification<AuditEntity> byPatientId(long patientId) {
    return (root, query, builder) -> builder.equal(root.get("patientId"), patientId);
  }

  public static Specification<AuditEntity> byPharmacyId(long pharmacyId) {
    return (root, query, builder) -> builder.equal(root.get("pharmacyId"), pharmacyId);
  }

  public static Specification<AuditEntity> bySuccess(boolean success) {
    return (root, query, builder) -> builder.equal(root.get("success"), success);
  }

  public static Specification<AuditEntity> NOOP = Specification.where(null);
}
