package io.github.uazw.supply.controller.dto;


import java.util.List;

public record AuditResponse(
    long id,
    long prescriptionId,
    long patientId,
    long pharmacyId,
    List<Long> requested,
    List<Long> dispensed,
    String reasons,
    boolean success
) {
}
