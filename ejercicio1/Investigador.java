package ejercicio1;

public class Investigador extends PDI {

    public Investigador(String nombre, String dni, String edad) {
        super(nombre, dni, edad);
        nomina = 35*7*4;
    }
    
    @Override
    public String imprimirNomina(){
        return nombre + "(Investigador) : "+  devolverNomina()+ " euros";
    }

    @Override
    public double devolverNomina() {
        return nomina;
    }
    
}
