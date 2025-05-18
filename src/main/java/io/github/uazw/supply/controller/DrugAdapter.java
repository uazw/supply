package io.github.uazw.supply.controller;

import io.github.uazw.supply.controller.dto.CreateDrugRequest;
import io.github.uazw.supply.domain.DrugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DrugAdapter {

  private final DrugService drugService;

  @Autowired
  public DrugAdapter(DrugService drugService) {
    this.drugService = drugService;
  }

  public void create(CreateDrugRequest request) {
    drugService.inventory(request.name(), request.manufacturer(), request.batchNumber(), request.expiredDate(), request.stock());
  }
}
