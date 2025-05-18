package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.model.Drug;
import io.github.uazw.supply.domain.model.DrugId;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface DrugPort {
  Drug create(String name,
              String manufacturer);

  Option<Drug> findBy(String name, String manufacturer);

  void save(Drug drug);

  List<Drug> findBy(List<DrugId> drugIds);
}
