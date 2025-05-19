package io.github.uazw.supply.domain.model;

import static org.assertj.core.api.Assertions.assertThat;

import io.vavr.collection.List;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import org.junit.jupiter.api.Test;

class DrugTest {

  @Test
  void shouldReturnTrueIfStockIsEnough() {
    var drugId = DrugId.from(1L);

    var drug = new Drug(drugId, "name", "name",
        List.of(new DrugStock(drugId, "sdf", Instant.now().plus(2, ChronoUnit.DAYS), 10L, 10L)));

    var dispense = drug.dispense(5L);
    assertThat(dispense).isTrue();
    assertThat(drug.getStocks().map(DrugStock::remaining)).isEqualTo(List.of(5L));
  }

  @Test
  void shouldReturnFalseFilterOutExpiredStock() {
    var drugId = DrugId.from(1L);

    var drug = new Drug(drugId, "name", "name",
        List.of(new DrugStock(drugId, "sdf", Instant.now().minus(2, ChronoUnit.DAYS), 10L, 10L)));

    var dispense = drug.dispense(5L);
    assertThat(dispense).isFalse();
    assertThat(drug.getStocks().map(DrugStock::remaining)).isEqualTo(List.of(10L));
  }

  @Test
  void shouldReturnTrueIfMultipleStock() {
    var drugId = DrugId.from(1L);

    var drug = new Drug(drugId, "name", "name",
        List.of(
            new DrugStock(drugId, "sdf", Instant.now().plus(2, ChronoUnit.DAYS), 10L, 5L),
            new DrugStock(drugId, "sdf", Instant.now().plus(2, ChronoUnit.DAYS), 10L, 5L)
        )
    );

    var dispense = drug.dispense(10L);
    assertThat(dispense).isTrue();
    assertThat(drug.getStocks().map(DrugStock::remaining)).isEqualTo(List.of(0L, 0L));
  }

}