package ejercicio1;

public class Administrativo extends PAS {
    
    public Administrativo(String nombre, String dni, String edad) {
        super(nombre, dni, edad);
        nomina = 37*7.5*4;
    }

    @Override
    public String imprimirNomina(){
        String aux; 
        if(horas_extras > 0){
            aux = nombre + "(Administrativo con "+ horas_extras +" horas extras) : "+  devolverNomina() + " euros";
        } else {
            aux = nombre + "(Administrativo) : "+  devolverNomina() + " euros";
        }
        horas_extras = 0;
        return aux;
    }
    
    @Override
    public double devolverNomina() {
       return nomina + (horas_extras * 6);
    }
    
}
