package io.github.uazw.supply.domain.model;

public record DrugId(long id) {
  public static DrugId from(Long id) {
    return new DrugId(id);
  }
}
