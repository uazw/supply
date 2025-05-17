package io.github.uazw.supply.adapter;

import io.github.uazw.supply.adapter.entity.DrugEntity;
import io.github.uazw.supply.domain.DrugPort;
import io.github.uazw.supply.domain.model.Drug;
import io.vavr.control.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DrugRepository extends DrugPort, CrudRepository<DrugEntity, Long> {

  default Drug create(String name,
                      String manufacturer) {
    return this.save(new DrugEntity(name, manufacturer)).to();
  }

  default Option<Drug> findBy(String name, String manufacturer) {
    return Option.ofOptional(findByNameAndManufacturer(name, manufacturer));
  }

  Optional<Drug> findByNameAndManufacturer(String name, String manufacturer);

  default void save(Drug drug) {
    this.save(DrugEntity.from(drug));
  }
}
