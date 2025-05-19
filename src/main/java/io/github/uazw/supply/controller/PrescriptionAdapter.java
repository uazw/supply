package io.github.uazw.supply.controller;

import io.github.uazw.supply.controller.dto.DrugWithCountRequest;
import io.github.uazw.supply.domain.PrescriptionService;
import io.github.uazw.supply.domain.model.PatientId;
import io.github.uazw.supply.domain.model.PharmacyId;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrescriptionAdapter {

  private final PrescriptionService PrescriptionService;

  @Autowired
  public PrescriptionAdapter(PrescriptionService PrescriptionService) {
    this.PrescriptionService = PrescriptionService;
  }

  public void create(long pharmacyId, long patientId, List<DrugWithCountRequest> drugs) {
    PrescriptionService.create(PharmacyId.from(pharmacyId), PatientId.from(patientId),
        io.vavr.collection.List.ofAll(drugs.stream().map(DrugWithCountRequest::to)));
  }
}
