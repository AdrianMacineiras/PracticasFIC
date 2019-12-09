package e1;

import java.util.Comparator;

public class Comparador implements Comparator<EuroCoin> {

    @Override
    public int compare(EuroCoin coin1, EuroCoin coin2) {
       int resultado = coin1.getPais().getNamePais().compareToIgnoreCase(coin2.getPais().getNamePais());
       
       if(resultado == 0){
            if(coin1.getValor().getValue() > coin2.getValor().getValue()){
                resultado = -1;
            } else {
                if(coin1.getValor().getValue() < coin2.getValor().getValue()){
                    resultado = 1;
                } else {
                    resultado = 0;
                }
            }
       }
       
       if(resultado == 0) {
            if(coin1.getAcunacion() > coin2.getAcunacion()){
                resultado = -1;
            } else {
                if(coin1.getAcunacion() < coin2.getAcunacion()){
                    resultado = 1;
                } else {
                    resultado = 0;
                }
            }
       }
       
       return resultado;
    }
    
}
