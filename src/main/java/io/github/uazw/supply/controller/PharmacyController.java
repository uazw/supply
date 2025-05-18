package io.github.uazw.supply.controller;

import io.github.uazw.supply.controller.dto.ListPharmacyResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pharmacy")
public class PharmacyController {

  private final PharmacyAdapter pharmacyAdapter;

  @Autowired
  public PharmacyController(PharmacyAdapter pharmacyAdapter) {
    this.pharmacyAdapter = pharmacyAdapter;
  }

  @GetMapping
  public List<ListPharmacyResponse> listAll() {
    return pharmacyAdapter.listAll();
  }
}
