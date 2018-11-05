import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class ListaIterador implements Iterator {

    private final List<Cancion> listaIterada;
    private int puntero;
    private boolean canRemove = false;

    protected ListaIterador(List<Cancion> lista) {
        this.listaIterada = lista;
        this.puntero = 0;
    }

    public boolean hasNext() {        
        return this.listaIterada.size() > puntero;
    }

    public Cancion next() {
        Cancion cancion;
        if (puntero > this.listaIterada.size()) {
          this.puntero = -1;    
        } 
        cancion = this.listaIterada.get(this.puntero);
        this.canRemove = true;
        this.puntero++;
        return cancion;
    }

    public Cancion remove() {
        Cancion cancion;
        if (this.canRemove) {
            this.canRemove = false;
            cancion = this.listaIterada.get(this.puntero);
            this.listaIterada.remove(this.puntero);
        } else {
            throw new IllegalStateException();
        }
        return cancion;
    }
}