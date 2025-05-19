package io.github.uazw.supply.domain.model;

public record PrescriptionId(long id) {
  public static PrescriptionId from(Long id) {
    return new PrescriptionId(id);
  }
}