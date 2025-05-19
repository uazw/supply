package io.github.uazw.supply.adapter;

import io.github.uazw.supply.adapter.entity.PharmacyEntity;
import io.github.uazw.supply.domain.PharmacyPort;
import io.github.uazw.supply.domain.model.Pharmacy;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PharmacyRepository extends PharmacyPort, CrudRepository<PharmacyEntity, Long> {

  @Override
  default List<Pharmacy> listAll() {
    return List.ofAll(this.findAll()).map(PharmacyEntity::to);
  }

  @Override
  default Option<Pharmacy> findBy(PharmacyId pharmacyId) {
    return Option.ofOptional(this.findById(pharmacyId.id())).map(PharmacyEntity::to);
  }

  @Override
  default Pharmacy save(Pharmacy pharmacy) {
    return this.save(PharmacyEntity.from(pharmacy)).to();
  }
}
