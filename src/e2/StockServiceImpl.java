package e2;

import static e2.ClientTypeEnum.Basico;
import static java.util.stream.Collectors.toList;

import java.util.Comparator;
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
            .max(Comparator.comparing(Stock::getFechaCotizacion))
            .map(Stock::getCotizacion)
            .get();

        Integer volumen = stockList.stream()
            .filter(stock -> stock.getSimbolo().equalsIgnoreCase(simbolo))
            .mapToInt(Stock::getVolumen)
            .sum();

        stockBuilder = stockBuilder
            .withSimbolo(simbolo)
            .withCierre(closeCotization)
            .withVolumen(volumen);

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

            return stockBuilder
                .withMaximo(maxCotizacion)
                .withMinimo(minCotizacion)
                .build();
        }

    }

    public List<StockData> populateStockData(ClientTypeEnum clientType) {
        return stockList.stream()
            .map(Stock::getSimbolo)
            .distinct()
            .map(simbolo -> getStockValue(clientType, simbolo))
            .collect(toList());
    }

    public List<Stock> getStockList() {
        return this.stockList;
    }
}