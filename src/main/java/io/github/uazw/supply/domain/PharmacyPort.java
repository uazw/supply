package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.model.Pharmacy;
import io.vavr.collection.List;

public interface PharmacyPort {
  List<Pharmacy> listAll();
}
