package e1;

public class StockData {

    private final String simbolo;
    private final Double cierre;
    private final Double maximo;
    private final Double minimo;
    private final Integer volumen;
    
    private StockData() {
        
    }

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

    public class Builder {
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