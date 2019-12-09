package e1;

import java.util.Objects;

public class Stock<P,D> {
    private final P producto;
    private final D dinero;
    
    public Stock(P producto, D dinero){
        this.producto = producto;
        this.dinero = dinero;
    }

    public P getProducto() {
        return producto;
    }

    public D getDinero() {
        return dinero;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.producto);
        hash = 97 * hash + Objects.hashCode(this.dinero);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Stock<?, ?> other = (Stock<?, ?>) obj;
        if (!Objects.equals(this.producto, other.producto)) {
            return false;
        }
        if (!Objects.equals(this.dinero, other.dinero)) {
            return false;
        }
        return true;
    }
    
    
}
