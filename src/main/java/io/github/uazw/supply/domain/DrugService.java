package io.github.uazw.supply.domain;

import io.github.uazw.supply.domain.model.Drug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class DrugService {

  private final DrugPort drugPort;

  @Autowired
  public DrugService(DrugPort drugPort) {
    this.drugPort = drugPort;
  }

  public void inventory(String name, String manufacturer, String batchNumber, Instant expiredDate, Long stock) {
    Drug drug = drugPort.findBy(name, manufacturer).getOrElse(() -> drugPort.create(name, manufacturer));
    drug.inventory(batchNumber, expiredDate, stock);
    drugPort.save(drug);
  }
}
