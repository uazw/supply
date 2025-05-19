package io.github.uazw.supply.controller.dto;


import java.util.List;

public record CreatePrescriptionRequest(
    long patientId,
    long pharmacyId,
    List<DrugWithCountRequest> drugs
) {

}
