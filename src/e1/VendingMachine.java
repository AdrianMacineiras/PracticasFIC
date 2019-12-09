package e1;

import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class VendingMachine {

    protected List<Stock<String, Integer>> stock = new ArrayList<>();
    protected List<EuroCoin> listamonedas = new ArrayList<>();
    private List<EuroCoin> monedasinsertadas = new ArrayList<>();
    private double valormonedasinsertadas = 0;
    
    private final double limite;
    private double saldo;

    private final EuroCoin e2 = new EuroCoin(Pais.ES,Valores.e2,"",0);
    private final EuroCoin e1 = new EuroCoin(Pais.ES,Valores.e1,"",0);
    private final EuroCoin e50 = new EuroCoin(Pais.ES,Valores.c50,"",0);
    private final EuroCoin e20 = new EuroCoin(Pais.ES,Valores.c20,"",0);
    private final EuroCoin e10 = new EuroCoin(Pais.ES,Valores.c10,"",0);
    private final EuroCoin e05 = new EuroCoin(Pais.ES,Valores.c5,"",0);
    private final EuroCoin e02 = new EuroCoin(Pais.ES,Valores.c2,"",0);
    private final EuroCoin e01 = new EuroCoin(Pais.ES,Valores.c1,"",0);
    
    
    public VendingMachine(double limite) {
        this.limite = limite;
        this.saldo = limite;
        
        listamonedas.add(e2);
        listamonedas.add(e1);
        listamonedas.add(e50);
        listamonedas.add(e20);
        listamonedas.add(e10);
        listamonedas.add(e05);
        listamonedas.add(e02);
        listamonedas.add(e01);
    }

    void insertProduct(String product, int price) {
        int i = 0;
        boolean encontrado = false;

        while (i < stock.size()) {
            if (stock.get(i).getProducto().equals(product)) {
                encontrado = true;
            }
            i++;
        }
        if (!encontrado) {
            Stock aux = new Stock(product, price);
            stock.add(aux);
        }
    }

    void insertCoin(EuroCoin e) {
        monedasinsertadas.add(e);
        valormonedasinsertadas += e.getValor().getValue();
    }

    List<EuroCoin> buy(String product) throws NoSuchElementException, NoInsertedCoinsException, NotEnoughInsertedCoinsException {
        int i = 0;
        double diff = 0;
        boolean encontrado = true;
        List<EuroCoin> cambio = new ArrayList<>();

        while (i < stock.size()) {
            if (monedasinsertadas == null) {
                throw new NoInsertedCoinsException();
            }
            encontrado = stock.get(i).getProducto().equals(product);
            if (valormonedasinsertadas < stock.get(i).getDinero()) {
                throw new NotEnoughInsertedCoinsException();
            } else {
                saldo += stock.get(i).getDinero();
                diff =  valormonedasinsertadas - stock.get(i).getDinero();
                valormonedasinsertadas = 0;
                monedasinsertadas = null;
            }
            i++;
        }
        if (!encontrado) {
            throw new NoSuchElementException();
        }

        return cambio;
    }

    List<EuroCoin> cancel() throws NoInsertedCoinsException {
        if (monedasinsertadas == null) {
            throw new NoInsertedCoinsException();
        }
        return monedasinsertadas;
    }

    public List<Stock<String, Integer>> getStock() {
        return stock;
    }

    public double getSaldo() {
        return saldo;
    }

}
