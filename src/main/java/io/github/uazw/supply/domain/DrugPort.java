package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.model.Drug;
import io.vavr.control.Option;

public interface DrugPort {
  Drug create(String name,
              String manufacturer);

  Option<Drug> findBy(String name, String manufacturer);

  void save(Drug drug);
}
