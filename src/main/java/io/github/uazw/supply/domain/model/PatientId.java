package io.github.uazw.supply.domain.model;

public record PatientId(long id) {
  public static PatientId from(Long id) {
    return new PatientId(id);
  }
}