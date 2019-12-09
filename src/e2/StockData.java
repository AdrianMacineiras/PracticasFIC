package e2;

import java.util.Objects;

public class StockData {

    private final String simbolo;
    private final Double cierre;
    private final Double maximo;
    private final Double minimo;
    private final Integer volumen;

    protected StockData(String simbolo, Double cierre, Double maximo, Double minimo, Integer volumen) {
        this.simbolo = simbolo;
        this.cierre = cierre;
        this.maximo = maximo;
        this.minimo = minimo;
        this.volumen = volumen;
    }

    public String getSimbolo() {
        return this.simbolo;
    }

    public Double getCierre() {
        return this.cierre;
    }

    public Double getMaximo() {
        return this.maximo;
    }

    public Double getMinimo() {
        return this.minimo;
    }

    public Integer getVolumen() {
        return this.volumen;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        StockData stockData = (StockData) o;
        return Objects.equals(simbolo, stockData.simbolo) && Objects.equals(cierre, stockData.cierre) && Objects
            .equals(maximo, stockData.maximo) && Objects.equals(minimo, stockData.minimo) && Objects.equals(volumen, stockData.volumen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simbolo, cierre, maximo, minimo, volumen);
    }

    @Override
    public String toString() {
        return "StockData{" + "simbolo='" + simbolo + '\'' + ", cierre=" + cierre + ", maximo=" + maximo + ", minimo=" + minimo
            + ", volumen=" + volumen + '}';
    }

    public static class Builder {
        private String simbolo;
        private Double cierre;
        private Double maximo;
        private Double minimo;
        private Integer volumen;

        public Builder withSimbolo(String simbolo) {
            this.simbolo = simbolo;
            return this;
        }

        public Builder withCierre(Double cierre) {
            this.cierre = cierre;
            return this;
        }

        public Builder withMaximo(Double maximo) {
            this.maximo = maximo;
            return this;
        }

        public Builder withMinimo(Double minimo) {
            this.minimo = minimo;
            return this;
        }

        public Builder withVolumen(Integer volumen) {
            this.volumen = volumen;
            return this;
        }

        public StockData build() {
            return new StockData(simbolo, cierre, maximo, minimo, volumen);
        }
    }
}