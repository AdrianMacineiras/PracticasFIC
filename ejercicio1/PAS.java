package ejercicio1;

public abstract class PAS extends Persona {
    
    protected int horas_extras;
    
    public PAS(String nombre, String dni, String edad) {
        super(nombre, dni, edad);
        horas_extras = 0;
    }

    public int getHoras_extras() {
        return horas_extras;
    }
    
    public void horasExtras(int horas_extras){
        this.horas_extras = horas_extras;
    }
}
