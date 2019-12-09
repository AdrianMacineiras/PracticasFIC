package e1;

import java.time.LocalDateTime;

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
   
}
