import java.util.List;
import java.util.NoSuchElementException;
import java.util.Iterator;

public class ListaIterador implements Iterator {

    private final List<Participantes> listaIterada;
    private int puntero;
    private boolean canRemove = false;
    private boolean forward = true;

    protected ListaIterador(List<Cancion> lista) {
        this.listaIterada = lista;
        this.puntero = 0;
    }

    public boolean hasNext() {        
        return this.listaIterada.size() > puntero;
    }

    public Participantes next() {
        Participantes cancion;
        if (forward) {
            if (puntero > this.listaIterada.size()) {
                forward = false;  
                this.puntero = this.puntero -1;    
            } else {
                this.puntero++;
            }    
        } else {
            if (puntero == 0) {
                forward = true;  
            } else {
                this.puntero--;
            }
        }
        
        cancion = this.listaIterada.get(this.puntero);
        this.canRemove = true;
        
        return cancion;
    }

    public void remove() {
        Participantes cancion;
        if (this.canRemove) {
            this.canRemove = false;
            this.listaIterada.remove(this.puntero);
        } else {
            throw new IllegalStateException();
        }
    }
}