package io.github.uazw.supply.controller.dto;

import java.time.Instant;

public record CreateDrugRequest(String name, String manufacturer, String batchNumber,
                                Instant expiredDate, long stock) {
}
