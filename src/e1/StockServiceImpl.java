import java.util.LinkedList;
import java.util.List;

public class StockServiceImpl implements StockService {

    private List<Stock> stockList = new LinkedList<>();

    public void addStock(Stock stock) {
        stockList.add(stock);
    }

    public StockData getStockValue(ClientTypeEnum clientType, String simbolo) {
        
        StockData.Builder stockBuilder = new StockData.Builder();

        Double closeCotization = stockList.stream()
            .filter(stock -> stock.getSimbolo().equalsIgnoreCase(simbolo))
            .min(Comparator.comparing(Stock::getFechaCotizacion))
            .map(Stock::getCotizacion)
            .get();

        stockBuilder = stockBuilder.withCierre(closeCotization);

        if (Basico.equals(clientType)) {
            return stockBuilder.build();
        } else {
            Double maxCotizacion = stockList.stream()
                .filter(stock -> stock.getSimbolo().equalsIgnoreCase(simbolo))
                .max(Comparator.comparing(Stock::getCotizacion))
                .map(Stock::getCotizacion)
                .get();

            Double minCotizacion = stockList.stream()
                .filter(stock -> stock.getSimbolo().equalsIgnoreCase(simbolo))
                .min(Comparator.comparing(Stock::getCotizacion))
                .map(Stock::getCotizacion)
                .get();
            
            Intege volumen = stockList.stream()
                .filter(stock -> stock.getSimbolo().equalsIgnoreCase(simbolo))
                .mapToInt(Stock::getVolumen)
                .sum();

            return stockBuilder
                .withMaximo(maxCotizacion)
                .withMinimo(minCotizaction)
                .withVolument(volumen)
                .build();
        }

    }

    public List<StockData> populateStockData(ClientTypeEnum clientType) {
        return stockList
            .map(stock -> getStockValue(clientType, stock.getSimbolo()))
            .collect(Collections.toList());
    }
}