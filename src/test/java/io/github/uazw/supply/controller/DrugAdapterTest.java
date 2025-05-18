package io.github.uazw.supply.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.github.uazw.supply.controller.dto.CreateDrugRequest;
import io.github.uazw.supply.domain.DrugService;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class DrugAdapterTest {

  @Test
  void shouldAbleToInventory() {
    var drugService = mock(DrugService.class);
    var drugAdapter = new DrugAdapter(drugService);
    var manufacturer = "manufacturer";
    var name = "name";
    var number = "123";
    var now = Instant.now();
    var stock = 123L;

    drugAdapter.create(new CreateDrugRequest(name, manufacturer, number,
        now, stock));

    verify(drugService).inventory(name, manufacturer, number, now, stock);
  }
}