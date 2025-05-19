package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.model.Prescription;

public interface AuditPort {
  void success(Prescription prescription);

  void fail(Prescription prescription, String message);
}
