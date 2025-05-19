package io.github.uazw.supply.adapter;

import io.github.uazw.supply.adapter.entity.AuditEntity;
import io.github.uazw.supply.domain.AuditPort;
import io.github.uazw.supply.domain.model.Prescription;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditRepository extends AuditPort, CrudRepository<AuditEntity, Long>,
    JpaSpecificationExecutor<AuditEntity> {
  @Override
  default void success(Prescription prescription) {
    this.save(AuditEntity.success(prescription));
  }

  @Override
  default void fail(Prescription prescription, String message) {
    this.save(AuditEntity.fail(prescription, message));
  }

}
