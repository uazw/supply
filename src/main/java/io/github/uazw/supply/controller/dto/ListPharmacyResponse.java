package io.github.uazw.supply.controller.dto;


import java.util.List;

public record ListPharmacyResponse(long id, List<ContractedDrugResponse> contractDrugs) {
}
