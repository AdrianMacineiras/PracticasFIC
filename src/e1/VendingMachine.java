package e1;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class VendingMachine {

    private final Map<String, Double> stock = new HashMap();
    private final Map<Valores, EuroCoin> mapaMonedas = new EnumMap(Valores.class);
    private Map<Valores, Integer> monedasinsertadas = new EnumMap(Valores.class);
    private List<EuroCoin> entry = new ArrayList<>();
    private Cambio cambio;

    private double valormonedasinsertadas = 0;

    private final double limite;
    private final double fondoInicial;
    private double saldo;

    public VendingMachine(double fondoInicial, double limite) {
        this.fondoInicial = fondoInicial;
        this.limite = limite;
        this.saldo = limite;

        mapaMonedas.put(Valores.e2, new EuroCoin(Pais.ES, Valores.e2, "", 0));
        mapaMonedas.put(Valores.e1, new EuroCoin(Pais.ES, Valores.e1, "", 0));
        mapaMonedas.put(Valores.c50, new EuroCoin(Pais.ES, Valores.c50, "", 0));
        mapaMonedas.put(Valores.c20, new EuroCoin(Pais.ES, Valores.c20, "", 0));
        mapaMonedas.put(Valores.c10, new EuroCoin(Pais.ES, Valores.c10, "", 0));
        mapaMonedas.put(Valores.c5, new EuroCoin(Pais.ES, Valores.c5, "", 0));
        mapaMonedas.put(Valores.c2, new EuroCoin(Pais.ES, Valores.c2, "", 0));
        mapaMonedas.put(Valores.c1, new EuroCoin(Pais.ES, Valores.c1, "", 0));
    }

    void insertProduct(String product, double price) throws ElementAlreadyExistsException {
        if (stock.containsKey(product)) {
            throw new ElementAlreadyExistsException();
        } else {
            stock.put(product, price);
        }
    }

    void insertCoin(EuroCoin e) {
        entry.add(e);
        monedasinsertadas.compute(e.getValor(), (valor, unidades) -> unidades == null ? 1 : unidades++);
        valormonedasinsertadas += e.getValor().getValue();
    }

    List<EuroCoin> buy(String product) throws NoSuchElementException, NoInsertedCoinsException, NotEnoughInsertedCoinsException {
        double diff = 0;
        boolean encontrado = true;
        List<EuroCoin> vuelta = new ArrayList<>();
        CambioSimple simple = new CambioSimple();
        CambioDeposito deposito = new CambioDeposito();

        if (monedasinsertadas == null) {
            throw new NoInsertedCoinsException();
        }
        encontrado = stock.containsKey(product);
        if (valormonedasinsertadas < stock.get(product)) {
            throw new NotEnoughInsertedCoinsException();
        } else {
            saldo += stock.get(product);
            diff = valormonedasinsertadas - stock.get(product);
            if (saldo < limite) {
                setCambio(simple);
                vuelta = cambioDevolver(diff, monedasinsertadas);
            } else {
                setCambio(deposito);
                vuelta = cambioDevolver(diff, monedasinsertadas);
            }
            valormonedasinsertadas = 0;
            monedasinsertadas = null;
            entry = null;
        }

        if (!encontrado) {
            throw new NoSuchElementException();
        }

        return vuelta;
    }

    List<EuroCoin> cancel() throws NoInsertedCoinsException {
        if (entry.isEmpty()) {
            throw new NoInsertedCoinsException();
        }
        return entry;
    }

    public List<EuroCoin> cambioDevolver(double diff, Map<Valores, Integer> monedasinsertadas) {
        return cambio.cambio(this, diff, monedasinsertadas);
    }

    public Map<String, Double> getStock() {
        return stock;
    }

    public double getSaldo() {
        return saldo;
    }

    public Map<Valores, EuroCoin> getMonedas() {
        return mapaMonedas;
    }

    public void setCambio(Cambio cambio) {
        this.cambio = cambio;
    }

    public List<EuroCoin> getEntry() {
        return entry;
    }

}
