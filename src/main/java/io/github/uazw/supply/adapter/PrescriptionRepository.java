package io.github.uazw.supply.adapter;

import io.github.uazw.supply.adapter.entity.DrugWithCountEntity;
import io.github.uazw.supply.adapter.entity.PrescriptionEntity;
import io.github.uazw.supply.domain.PrescriptionPort;
import io.github.uazw.supply.domain.model.DrugWithCount;
import io.github.uazw.supply.domain.model.PatientId;
import io.github.uazw.supply.domain.model.PharmacyId;
import io.github.uazw.supply.domain.model.Prescription;
import io.github.uazw.supply.domain.model.PrescriptionId;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PrescriptionRepository
    extends PrescriptionPort, CrudRepository<PrescriptionEntity, Long> {
  @Override
  default Prescription create(PatientId patientId, PharmacyId pharmacyId,
                              List<DrugWithCount> drugs) {
    return this.save(new PrescriptionEntity(patientId.id(), pharmacyId.id(),
        drugs.map(DrugWithCountEntity::from).toJavaList())).to();
  }

  @Override
  default Prescription save(Prescription prescription) {
    return this.save(PrescriptionEntity.from(prescription)).to();
  }

  @Override
  default Option<Prescription> findBy(PrescriptionId prescriptionId) {
    return Option.ofOptional(this.findById(prescriptionId.id())).map(PrescriptionEntity::to);
  }
}
