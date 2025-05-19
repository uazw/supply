package io.github.uazw.supply.controller;

import io.github.uazw.supply.controller.dto.CreatePrescriptionRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/prescription")
public class PrescriptionController {

  private final PrescriptionAdapter prescriptionAdapter;

  @Autowired
  public PrescriptionController(PrescriptionAdapter PrescriptionAdapter) {
    this.prescriptionAdapter = PrescriptionAdapter;
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public void create(@RequestBody CreatePrescriptionRequest request) {
    prescriptionAdapter.create(request.pharmacyId(), request.patientId(), request.drugs());
  }
}
