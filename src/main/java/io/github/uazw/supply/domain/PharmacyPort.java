package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.model.Pharmacy;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.vavr.collection.List;
import io.vavr.control.Option;

public interface PharmacyPort {
  List<Pharmacy> listAll();

  Option<Pharmacy> findBy(PharmacyId pharmacyId);

  Pharmacy save(Pharmacy pharmacy);
}
