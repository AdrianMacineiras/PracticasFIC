package e1;

import java.util.ArrayList;
import java.util.List;

public class CambioDeposito implements Cambio {

    @Override
    public List<EuroCoin> cambio(VendingMachine maquina, double diff, List<EuroCoin> monedasinsertadas) {
        double aux = diff * 100;
        List<EuroCoin> resultado = new ArrayList<>();

        int e2 = (int) aux / 200;
        aux = aux % 200;
        int e1 = (int) aux / 100;
        aux = aux % 100;
        int e50 = (int) aux / 50;
        aux = aux % 50;
        int e20 = (int) aux / 20;
        aux = aux % 20;
        int e10 = (int) aux / 10;
        aux = aux % 10;
        int e05 = (int) aux / 5;
        aux = aux % 5;
        int e02 = (int) aux / 2;
        aux = aux % 2;
        int e01 = (int) aux;

    }

    private selectCoins() {

    }

}