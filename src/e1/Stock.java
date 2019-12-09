package e1;

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

   public void setSimbolo(String simbolo) {
       this.simbolo = simbolo;
   }

   public LocalDateTime getFechaCotizacion() {
       return fechaCotizacion;
   }

   public void setFechaCotizacion(LocalDateTime fechaCotizacion) {
        this.fechaCotizacion = fechaCotizacion;
   }

   public Double getCotizacion() {
       return this.cotizacion;
   }

   public void setCotizacion(Double cotizacion) {
       this.cotizacion = cotizacion;
   }

   public Integer getVolumen() {
       return volumenm;
   }

   public void setVolumen(Integer volumen) {
       this.volumen = volumen;
   }

   
}
