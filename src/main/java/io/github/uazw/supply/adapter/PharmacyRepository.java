package io.github.uazw.supply.adapter;

import io.github.uazw.supply.adapter.entity.PharmacyEntity;
import io.github.uazw.supply.domain.PharmacyPort;
import io.github.uazw.supply.domain.model.Pharmacy;
import io.vavr.collection.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PharmacyRepository extends PharmacyPort, CrudRepository<PharmacyEntity, Long> {

  @Override
  default List<Pharmacy> listAll() {
    return List.ofAll(this.findAll()).map(PharmacyEntity::to);
  }
}
