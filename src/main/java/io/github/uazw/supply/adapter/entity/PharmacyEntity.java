package io.github.uazw.supply.adapter.entity;

import io.github.uazw.supply.domain.model.Pharmacy;
import io.github.uazw.supply.domain.model.PharmacyId;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "pharmacy")
public class PharmacyEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(columnDefinition = "serial")
  private long id;

  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  @JoinColumn(name = "pharmacy_id", referencedColumnName = "id")
  private List<ContractedDrugEntity> contractedDrugs = new ArrayList<>();

  public PharmacyEntity() {
  }

  public PharmacyEntity(List<ContractedDrugEntity> contractedDrugs) {
    this.contractedDrugs = contractedDrugs;
  }

  public PharmacyEntity(long id, List<ContractedDrugEntity> contractedDrugs) {
    this.id = id;
    this.contractedDrugs = contractedDrugs;
  }

  public static PharmacyEntity from(Pharmacy pharmacy) {
    return new PharmacyEntity(pharmacy.getId().id(),
        pharmacy.getContractedDrugs().map(ContractedDrugEntity::from).toJavaList());

  }

  public Pharmacy to() {
    return new Pharmacy(PharmacyId.from(id),
        io.vavr.collection.List.ofAll(contractedDrugs.stream().map(ContractedDrugEntity::to)));
  }

  public long getId() {
    return id;
  }
}
