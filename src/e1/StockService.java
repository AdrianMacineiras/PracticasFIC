public interface StockService {

    void addStock(Stock stock);

    StockData getStockValue(ClientTypeEnum clientType);

    void populateStockData();
}