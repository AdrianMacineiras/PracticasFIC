package e1;

import java.util.List;

public interface Cambio {
    public List<EuroCoin> cambio (VendingMachine maquina, double diff, List<EuroCoin> monedasinsertadas);
    
}
