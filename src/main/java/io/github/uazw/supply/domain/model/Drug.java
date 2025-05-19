package io.github.uazw.supply.domain.model;

import io.vavr.collection.List;
import java.time.Instant;
import java.util.ArrayList;

public class Drug {
  private final DrugId id;
  private final String name;
  private final String manufacturer;
  private List<DrugStock> stocks = List.of();

  public Drug(DrugId id, String name, String manufacturer, List<DrugStock> stocks) {
    this.id = id;
    this.name = name;
    this.manufacturer = manufacturer;
    this.stocks = stocks;
  }

  public void inventory(String batchNumber, Instant expiredDate, long stock) {
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

  public boolean dispense(Long count) {
    var partition = this.stocks.partition(
        stock -> stock.expiredDate().isAfter(Instant.now()) && stock.remaining() > 0);

    var total = count;
    var drugStocks = new ArrayList<DrugStock>();

    for (var drugStock : partition._1().sortBy(DrugStock::expiredDate)) {
      if (total >= drugStock.remaining()) {
        drugStocks.add(drugStock.withRemaining(0));
        total -= drugStock.remaining();
      } else {
        drugStocks.add(drugStock.withRemaining(drugStock.remaining() - total));
        total = 0L;
      }
    }
    this.stocks = partition._2().appendAll(drugStocks);

    return total <= 0;
  }
}
