package ejercicio3;

import java.util.ArrayList;

public class Concurso<E> {
    
    ArrayList<E> concursantes = new ArrayList<>();
    private int i = 0, used = 0, direccion = 0;
    
    Iterator listIterator = new ListaIterator(concursantes);

    void eliminarParticipante(int K) {
        int pointer = 0;
        while (listIterator.hasNext() && pointer<K) {
            listIterator.next();
            pointer++;      
        }
        listIterator.remove();
    }
    
}
