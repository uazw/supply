package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.Drug;
import io.github.uazw.supply.domain.model.DrugId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import org.springframework.data.annotation.Id;

import java.util.List;

@Entity
public class DrugEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private final String name;
  private final String manufacturer;
  private final List<DrugStockEntity> drugStockEntities;

  public DrugEntity(String name, String manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.drugStockEntities = List.of();
  }

  public DrugEntity(Long id, String name, String manufacturer, List<DrugStockEntity> drugStockEntities) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.drugStockEntities = drugStockEntities;
  }

  public static DrugEntity from(Drug drug) {
    return new DrugEntity(drug.getId().id(), drug.getName(), drug.getManufacturer(), drug.getStocks().map(DrugStockEntity::from).toJavaList());
  }

  public Drug to() {
    return new Drug(new DrugId(id), name, manufacturer, io.vavr.collection.List.ofAll(drugStockEntities.stream().map(DrugStockEntity::to)));
  }
}
