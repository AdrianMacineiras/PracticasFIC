package e1;

public enum Valores {
    c1(0.01, Color.Bronce),
    c2(0.02, Color.Bronce),
    c5(0.05, Color.Bronce),
    c10(0.10, Color.Oro),
    c20(0.20, Color.Oro),
    c50(0.50, Color.Oro),
    e1(1, Color.Oro_plata),
    e2(2, Color.Oro_plata);

    final double value;
    final Color color;

    Valores(final double valor, final Color color) {
        value = valor;
        this.color = color;
    }

    public double getValue() {
        return value;
    }

    public Color getColor() {
        return color;
    }

    public enum Color {
        Bronce("Bronce"),
        Oro("Oro"),
        Oro_plata("Oro-plata");
        
        private final String color;
    
        Color(String color){
            this.color = color;
        }

        public String getColorName() {
            return color;
        }
        
    }

}

