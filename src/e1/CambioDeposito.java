package e1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CambioDeposito implements Cambio {

    @Override
    public List<EuroCoin> cambio(VendingMachine maquina, double diff, Map<Valores,Integer> monedasinsertadas) {
        double aux = diff * 100;
        List<EuroCoin> resultado = new ArrayList<>();
        Map<Valores, EuroCoin> tipoMonedas = maquina.getMonedas();
        
        int e2 = (int) aux / 200;
        resultado.addAll(fillCoins(e2, tipoMonedas.get(e2)));
        aux = aux % 200;
        int e1 = (int) aux / 100;
        resultado.addAll(fillCoins(e1, tipoMonedas.get(e1)));
        aux = aux % 100;
        int e50 = (int) aux / 50;
        resultado.addAll(fillCoins(e50, tipoMonedas.get(e50)));
        aux = aux % 50;
        int e20 = (int) aux / 20;
        resultado.addAll(fillCoins(e20, tipoMonedas.get(e20)));
        aux = aux % 20;
        int e10 = (int) aux / 10;
        resultado.addAll(fillCoins(e10, tipoMonedas.get(e10)));
        aux = aux % 10;
        int e05 = (int) aux / 5;
        resultado.addAll(fillCoins(e05, tipoMonedas.get(e05)));
        aux = aux % 5;
        int e02 = (int) aux / 2;
        resultado.addAll(fillCoins(e02, tipoMonedas.get(e02)));
        aux = aux % 2;
        int e01 = (int) aux;
        resultado.addAll(fillCoins(e01, tipoMonedas.get(e01)));
        
        return resultado;
    }

    private List<EuroCoin> fillCoins(int numMonedasCrear, EuroCoin tipoMoneda) {
        List<EuroCoin> euroCoinList = new ArrayList(numMonedasCrear);
        
        Collections.fill(euroCoinList,tipoMoneda);
        return euroCoinList;
    }

}
