
package ejercicio2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class listaCanciones  {
     
    private ArrayList<Cancion> canciones = new ArrayList<>(); 
    private int puntero;
    private estadoCancion estado;

    public listaCanciones() {
    this.puntero = 0;
    this.estado = estadoCancion.DETENIDO;
    }
    
    
    public void añadirCancion(Cancion cancion){
        canciones.add(cancion);
    }
    
    public void añadirCancion (Cancion cancion, int puntero){
        canciones.add(puntero,cancion);
    }
    
    public void borrarCancion (int posicion){
        canciones.remove(posicion);
    }
    
    public void moverCancion (int p_origen, int p_destino){
        Cancion cancion;
        cancion = canciones.remove(p_origen);
        canciones.add(p_destino, cancion);
    }
    
    public int obtenerPuntero (){
        return puntero;
    }
    
    public void moverPuntero (int posicion){
        this.puntero = posicion;
    }
    
    public Cancion reproducirCancion (){
        estado = estadoCancion.SONANDO;
        return canciones.get(puntero);
    }
    
    public Cancion siguienteCancion (){
        if (puntero==canciones.size()-1) puntero = 0;
        else puntero ++;
        return canciones.get(puntero);
    }
    
    public Cancion anteriorCancion (){
        if (puntero==0) puntero = canciones.size()-1;
        else puntero--;
        return canciones.get(puntero);
    }
    
    public void detenerReproduccion (){
        estado = estadoCancion.DETENIDO;
    }
    
    public boolean estaReproduciendo(){
        return estado != estadoCancion.DETENIDO;
    }

    @Override
    public String toString() {
        return "listaCanciones{" + "canciones=" + canciones + '}';
    }

    public void ordenaLista(){
        this.puntero = 0;
        this.estado = estadoCancion.DETENIDO;
        Collections.sort(canciones);
    }
    
    public void ordenaLista(Comparator comp){
        this.puntero = 0;
        this.estado = estadoCancion.DETENIDO;
        Collections.sort(canciones,comp);
    }
    
}
