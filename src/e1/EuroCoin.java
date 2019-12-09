package e1;

import java.util.Objects;

public class EuroCoin implements Comparable<EuroCoin> {

    private final Pais pais;
    private final Valores valor;
    private final String diseno;
    private final int acunacion;

    public EuroCoin(Pais pais, Valores valor, String diseno,
            int acunacion){

        this.pais = pais;
        this.valor = valor;
        this.diseno = diseno;
        this.acunacion = acunacion;
    }

    public Pais getPais() {
        return pais;
    }

    public Valores getValor() {
        return valor;
    }

    public String getDiseno() {
        return diseno;
    }

    public int getAcunacion() {
        return acunacion;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.pais);
        hash = 17 * hash + Objects.hashCode(this.valor);
        hash = 17 * hash + Objects.hashCode(this.diseno);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EuroCoin other = (EuroCoin) obj;
        return !(this.valor != other.valor || !this.diseno.equals(other.diseno)
                || !this.pais.equals(other.pais));
    }

    @Override
    public int compareTo(EuroCoin coin) {
        int resultado;

        if (valor == coin.valor) {
            resultado = 0;
        } else {
            if (valor.getValue() > coin.valor.getValue()) {
                resultado = -1;
            } else {
                resultado = 1;
            }
        }

        if (resultado == 0) {
            resultado = pais.getNamePais().compareToIgnoreCase(coin.pais.getNamePais());
        }

        if (resultado == 0) {
            resultado = diseno.compareToIgnoreCase(coin.diseno);
        }

        return resultado;
    }

    @Override
    public String toString() {
        return pais.getNamePais() + " " + valor.getValue() + " " + valor.getColor().getColorName() + " " + diseno + " " + acunacion + " ";
    }
}
