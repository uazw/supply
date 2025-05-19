package io.github.uazw.supply.domain.exception;

import io.github.uazw.supply.domain.model.DrugId;
import io.github.uazw.supply.domain.model.PharmacyId;

public class InsufficientStorageException extends RuntimeException {
  public InsufficientStorageException(PharmacyId id) {
    super("InsufficientStorage for pharmacy and id is " + id.id());
  }

  public InsufficientStorageException(DrugId id) {

    super("InsufficientStorage for drug and id is " + id.id());
  }
}
