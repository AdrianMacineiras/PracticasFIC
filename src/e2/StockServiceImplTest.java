package e2;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class StockServiceImplTest {

  private StockService stockService = new StockServiceImpl();

  @Test
  public void addStock() {
    Stock stock = new Stock("EUR", LocalDateTime.now(), 3.4, 10);

    stockService.addStock(stock);
    assertTrue(stockService.getStockList().contains(stock));
  }

  @Test
  public void getStockValueBasico() {
    LocalDateTime fechaHoy = LocalDateTime.now();
    Stock stock = new Stock("EUR", fechaHoy, 3.4, 10);
    StockData expectedStockData = new StockData.Builder().withSimbolo("EUR").withCierre(3.4).withVolumen(10).build();
    stockService.addStock(stock);
    StockData stockData = stockService.getStockValue(ClientTypeEnum.Basico, "EUR");

    assertEquals(stockData, expectedStockData);
  }

  @Test
  public void getStockValueDetallado() {
    Stock stock1 = new Stock("EUR", LocalDateTime.now(), 3.4, 10);
    Stock stock2 = new Stock("EUR", LocalDateTime.now(), 5.4, 10);
    StockData expectedStockData = new StockData.Builder().withSimbolo("EUR").withCierre(5.4).withMaximo(5.4).withMinimo(3.4).withVolumen(20).build();
    stockService.addStock(stock1);
    stockService.addStock(stock2);
    StockData stockData = stockService.getStockValue(ClientTypeEnum.Detallado, "EUR");

    assertEquals(expectedStockData, stockData);
  }

  @Test
  public void populateStockData() {
    Stock stock1 = new Stock("EUR", LocalDateTime.now(), 3.4, 10);
    Stock stock2 = new Stock("EUR", LocalDateTime.now().plusMinutes(1), 5.4, 10);
    Stock stock3 = new Stock("USA", LocalDateTime.now().plusMinutes(2), 7.4, 15);
    Stock stock4 = new Stock("USA", LocalDateTime.now().plusMinutes(3), 1.4, 15);
    StockData expectedStockData1 = new StockData.Builder().withSimbolo("EUR").withCierre(5.4).withMaximo(5.4).withMinimo(3.4).withVolumen(20).build();
    StockData expectedStockData2 = new StockData.Builder().withSimbolo("USA").withCierre(1.4).withMaximo(7.4).withMinimo(1.4).withVolumen(30).build();

    stockService.addStock(stock1);
    stockService.addStock(stock2);
    stockService.addStock(stock3);
    stockService.addStock(stock4);

    List<StockData> stockDataList = stockService.populateStockData(ClientTypeEnum.Detallado);

    assertArrayEquals(new StockData[] {expectedStockData1, expectedStockData2}, stockDataList.toArray());
  }
}