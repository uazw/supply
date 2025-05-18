package io.github.uazw.supply.domain;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import io.github.uazw.supply.domain.model.Drug;
import io.github.uazw.supply.domain.model.DrugId;
import io.vavr.collection.List;
import io.vavr.control.Option;
import java.time.Instant;
import org.junit.jupiter.api.Test;

class DrugServiceTest {


  @Test
  public void shouldUpdateStockIfDrugExists() {
    var mockDrugPort = mock(DrugPort.class);
    var name = "name";
    var manufacturer = "manufacturer";
    var drug = new Drug(new DrugId(123L), name, manufacturer, List.of());
    when(mockDrugPort.findBy(name, manufacturer)).thenReturn(Option.of(drug));
    var drugService = new DrugService(mockDrugPort);

    drugService.inventory(name, manufacturer, "123", Instant.now(), 123L);

    verify(mockDrugPort, times(1)).save(drug);
  }

  @Test
  public void shouldCreateAnewDrugAndUpdateIfDrugNotExists() {
    var mockDrugPort = mock(DrugPort.class);
    var name = "name";
    var manufacturer = "manufacturer";
    var drug = new Drug(new DrugId(123L), name, manufacturer, List.of());
    when(mockDrugPort.findBy(name, manufacturer)).thenReturn(Option.none());
    when(mockDrugPort.create("name", manufacturer)).thenReturn(drug);
    var drugService = new DrugService(mockDrugPort);

    drugService.inventory(name, manufacturer, "123", Instant.now(), 123L);

    verify(mockDrugPort, times(1)).save(drug);
  }
}