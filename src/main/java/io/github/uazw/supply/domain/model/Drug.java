package io.github.uazw.supply.domain.model;

import io.vavr.collection.List;
import java.time.Instant;

public class Drug {
  private DrugId id;
  private String name;
  private String manufacturer;
  private List<DrugStock> stocks;

  public Drug() {
    this.stocks = List.of();
  }

  public Drug(DrugId id, String name, String manufacturer, List<DrugStock> stocks) {

    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.stocks = stocks;
  }

  public void inventory(String batchNumber, Instant expiredDate, Long stock) {
    this.stocks = this.stocks.append(new DrugStock(id, batchNumber, expiredDate, stock, stock));
  }

  public DrugId getId() {
    return id;
  }

  public String getName() {
    return this.name;
  }

  public String getManufacturer() {
    return manufacturer;
  }

  public List<DrugStock> getStocks() {
    return stocks;
  }

}
