package io.github.uazw.supply.controller;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import io.github.uazw.supply.controller.dto.CreateDrugRequest;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class DrugControllerTest {

  @Test
  void shouldAbleToCreateDrug() {
    var adapter = mock(DrugAdapter.class);
    var drugController = new DrugController(adapter);
    var manufacturer = "manufacturer";
    var name = "name";
    var number = "123";
    var now = Instant.now();
    var stock = 123L;
    var createDrugRequest = new CreateDrugRequest(name, manufacturer, number,
        now, stock);

    drugController.create(createDrugRequest);

    verify(adapter).create(createDrugRequest);
  }
}