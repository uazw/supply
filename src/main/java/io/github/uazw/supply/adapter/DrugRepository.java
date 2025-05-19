package io.github.uazw.supply.adapter;

import io.github.uazw.supply.adapter.entity.DrugEntity;
import io.github.uazw.supply.domain.DrugPort;
import io.github.uazw.supply.domain.model.Drug;
import io.github.uazw.supply.domain.model.DrugId;
import io.vavr.collection.List;
import io.vavr.control.Option;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DrugRepository extends DrugPort, CrudRepository<DrugEntity, Long> {


  Optional<DrugEntity> findByNameAndManufacturer(String name, String manufacturer);

  default Drug create(String name,
                      String manufacturer) {
    return this.save(new DrugEntity(name, manufacturer)).to();
  }

  default Option<Drug> findBy(String name, String manufacturer) {
    return Option.ofOptional(findByNameAndManufacturer(name, manufacturer)).map(DrugEntity::to);
  }

  default void save(Drug drug) {
    this.save(DrugEntity.from(drug));
  }

  default List<Drug> findBy(List<DrugId> drugIds) {
    return List.ofAll(this.findAllById(drugIds.map(DrugId::id).asJava())).map(DrugEntity::to);
  }

  @Override
  default void saveAll(List<Drug> drugs) {
    this.saveAll(drugs.map(DrugEntity::from));
  }
}
