package io.github.uazw.supply.domain.model;

import java.time.Instant;

public record DrugStock(
    String batchNumber,
    Instant expiredDate,
    Long stock,
    Long remaining) {
}
