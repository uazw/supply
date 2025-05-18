package io.github.uazw.supply.domain.model;

public record PharmacyId(Long id) {

  public static PharmacyId from(Long id) {
    return new PharmacyId(id);
  }
}
