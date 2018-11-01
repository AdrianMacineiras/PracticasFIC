package ejercicio1;

public class Informatico extends PAS {

    public Informatico(String nombre, String dni, String edad) {
        super(nombre, dni, edad);
        nomina = 40*6*4;
    }

    @Override
    public double devolverNomina() {
        return nomina + (horas_extras * 6);
    }
    
    @Override
    public String imprimirNomina(){
        String aux; 
        if(horas_extras > 0){
            aux = this.nombre + "(Informatico con "+ horas_extras +" horas extras) : "+  devolverNomina() + " euros";
        } else {
            aux = this.nombre + "(Informatico) : "+  devolverNomina() + " euros";
        }
        horas_extras = 0;
        return aux;
    }
    

    
}
