package e1;

import java.util.List;
import java.util.Map;

public interface Cambio {

    public List<EuroCoin> cambio (VendingMachine maquina, double diff, Map<Valores, Integer> monedasinsertadas);
    
}
