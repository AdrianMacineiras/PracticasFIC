package e2;

import java.time.LocalDateTime;
import java.util.Objects;

public class Stock {
    private final String simbolo;
    private final LocalDateTime fechaCotizacion;
    private final Double cotizacion;
    private final Integer volumen;
    
    public Stock(String simbolo, LocalDateTime fechaCotizacion, Double cotizacion, Integer volumen) {
        this.simbolo = simbolo;
        this.fechaCotizacion = fechaCotizacion;
        this.cotizacion = cotizacion;
        this.volumen = volumen;
    }

   public String getSimbolo() {
       return this.simbolo;
   }

   public LocalDateTime getFechaCotizacion() {
       return fechaCotizacion;
   }

   public Double getCotizacion() {
       return this.cotizacion;
   }

   public Integer getVolumen() {
       return volumen;
   }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock = (Stock) o;
        return Objects.equals(simbolo, stock.simbolo) && Objects.equals(fechaCotizacion, stock.fechaCotizacion) && Objects
            .equals(cotizacion, stock.cotizacion) && Objects.equals(volumen, stock.volumen);
    }

    @Override
    public int hashCode() {
        return Objects.hash(simbolo, fechaCotizacion, cotizacion, volumen);
    }

    @Override
    public String toString() {
        return "Stock{" + "simbolo='" + simbolo + '\'' + ", fechaCotizacion=" + fechaCotizacion + ", cotizacion=" + cotizacion
            + ", volumen=" + volumen + '}';
    }
}
