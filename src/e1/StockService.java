package e1;

import java.util.List;

public interface StockService {

    void addStock(Stock stock);

    StockData getStockValue(ClientTypeEnum clientType, String simbolo);

    List<StockData> populateStockData(ClientTypeEnum clientType);
}