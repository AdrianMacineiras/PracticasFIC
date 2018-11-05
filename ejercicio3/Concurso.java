package ejercicio3;

import java.util.ArrayList;

public class Concurso<E> implements Iterator<E> {
    
    ArrayList<E> concursantes = new ArrayList<>();
    private int i = 0, used = 0, direccion = 0;
    
    @Override
    public boolean hasNext() {
        if(concursantes.size() >= 2)
                return true;
        return false;
    }

    @Override
    public E next() {
        used = 1;
        if(hasNext() && direccion == 0){
           if(concursantes.size()-1 == i){
               direccion = 1;
           }
           return concursantes.get(i++);
       } else{
             if(i-1 == 0){
               direccion = 0;
           }
           return concursantes.get(i--);
       }
       
    }

    @Override
    public void remove() {
        if(used == 0)
            throw new IllegalStateException();
        else{
            concursantes.remove(i);
            used = 0;
        }
    }
    
}
