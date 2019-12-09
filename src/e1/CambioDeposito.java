package e1;

import java.util.List;

public class CambioDeposito implements Cambio {

    @Override
    public List<EuroCoin> cambio(VendingMachine maquina, double diff, List<EuroCoin> monedasinsertadas) {
         double aux = diff;
         
          int e200 = (int)aux / 2;
          aux = aux % 25;
          dimes = aux / 10;
          aux = aux % 10;
          nickels = aux / 5;
          aux = aux % 5;
          pennies = aux;
    }
    
}
