package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.Drug;
import io.github.uazw.supply.domain.model.DrugId;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "drug")
public class DrugEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private Long id;
  private String name;
  private String manufacturer;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "drug_id", referencedColumnName = "id")
  private List<DrugStockEntity> drugStockEntities;

  public DrugEntity() {
  }

  public DrugEntity(String name, String manufacturer) {
    this.name = name;
    this.manufacturer = manufacturer;
    this.drugStockEntities = new ArrayList<>();
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

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public List<DrugStockEntity> getDrugStockEntities() {
    return drugStockEntities;
  }
}
