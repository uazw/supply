package io.github.uazw.supply.controller;

import io.github.uazw.supply.controller.dto.CreateDrugRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/drug")
public class DrugController {

  private final DrugAdapter drugAdapter;

  @Autowired
  public DrugController(DrugAdapter drugAdapter) {
    this.drugAdapter = drugAdapter;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void create(@RequestBody CreateDrugRequest request) {
    drugAdapter.create(request);
  }
}
