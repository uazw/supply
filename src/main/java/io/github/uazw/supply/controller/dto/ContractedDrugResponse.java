package io.github.uazw.supply.controller.dto;

public record ContractedDrugResponse(
    String drugName,
    String manufacturer,
    long allocated,
    long remaining
) {
}
