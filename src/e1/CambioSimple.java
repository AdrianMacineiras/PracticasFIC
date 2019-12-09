package e1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CambioSimple implements Cambio {

    @Override
    public List<EuroCoin> cambio(VendingMachine maquina, double diff, Map<Valores, Integer> monedasinsertadas) {
        List<EuroCoin> resultado = new ArrayList();
        double aux = diff * 100;
        Map<Valores, EuroCoin> tipoMonedas = maquina.getMonedas();
        
        int e2 = (int) aux / 200;
        if(e2 > 0){
            if(monedasinsertadas.get(e2) >= e2){
                resultado.addAll(fillCoins(e2, tipoMonedas.get(e2)));
                aux = aux - 200 * e2;
            } else {
                resultado.addAll(fillCoins(monedasinsertadas.get(e2), tipoMonedas.get(e2)));
                aux = aux - 200 * monedasinsertadas.get(e2);
            }
        }
        int e1 = (int) aux / 100;
        if(e1 > 0){
            if(monedasinsertadas.get(e1) >= e1){
                resultado.addAll(fillCoins(e1, tipoMonedas.get(e1)));
                aux = aux - 100 * e1;
            } else {
                resultado.addAll(fillCoins(monedasinsertadas.get(e1), tipoMonedas.get(e1)));
                aux = aux - 100 * monedasinsertadas.get(e1);
            }
        }
        int e50 = (int) aux / 50;
        if(e50 > 0){
            if(monedasinsertadas.get(e50) >= e50){
                resultado.addAll(fillCoins(e50, tipoMonedas.get(e50)));
                aux = aux - 50 * e50;
            } else {
                resultado.addAll(fillCoins(monedasinsertadas.get(e50), tipoMonedas.get(e50)));
                aux = aux - 50 * monedasinsertadas.get(e50);
            }
        }
        int e20 = (int) aux / 20;
        if(e20 > 0){
            if(monedasinsertadas.get(e20) >= e20){
                resultado.addAll(fillCoins(e20, tipoMonedas.get(e20)));
                aux = aux - 20 * e20;
            } else {
                resultado.addAll(fillCoins(monedasinsertadas.get(e20), tipoMonedas.get(e20)));
                aux = aux - 20 * monedasinsertadas.get(e20);
            }
        }
        int e10 = (int) aux / 10;
        if(e10 > 0){
            if(monedasinsertadas.get(e10) >= e10){
                resultado.addAll(fillCoins(e10, tipoMonedas.get(e10)));
                aux = aux - 10 * e10;
            } else {
                resultado.addAll(fillCoins(monedasinsertadas.get(e10), tipoMonedas.get(e10)));
                aux = aux - 10 * monedasinsertadas.get(e10);
            }
        }
        int e05 = (int) aux / 5;
        if(e05 > 0){
            if(monedasinsertadas.get(e05) >= e05){
                resultado.addAll(fillCoins(e05, tipoMonedas.get(e05)));
                aux = aux - 5 * e05;
            } else {
                resultado.addAll(fillCoins(monedasinsertadas.get(e05), tipoMonedas.get(e05)));
                aux = aux - 5 * monedasinsertadas.get(e05);
            }
        }
        int e02 = (int) aux / 2;
        if(e02 > 0){
            if(monedasinsertadas.get(e02) >= e02){
                resultado.addAll(fillCoins(e02, tipoMonedas.get(e02)));
                aux = aux - 2 * e02;
            } else {
                resultado.addAll(fillCoins(monedasinsertadas.get(e02), tipoMonedas.get(e02)));
                aux = aux - 2 * monedasinsertadas.get(e02);
            }
        }
        int e01 = (int) aux;
        if(e01 > 0){
            if(monedasinsertadas.get(e01) >= e01){
                resultado.addAll(fillCoins(e01, tipoMonedas.get(e01)));
                aux = aux - 1 * e01;
            } else {
                resultado.addAll(fillCoins(monedasinsertadas.get(e01), tipoMonedas.get(e01)));
                aux = aux - 1 * monedasinsertadas.get(e01);
            }
        }
        return resultado;
    }
    
        private List<EuroCoin> fillCoins(int numMonedasCrear, EuroCoin tipoMoneda) {
        List<EuroCoin> euroCoinList = new ArrayList(numMonedasCrear);
        
        Collections.fill(euroCoinList,tipoMoneda);
        return euroCoinList;
    }
}
