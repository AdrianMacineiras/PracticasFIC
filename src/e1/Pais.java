package e1;

public enum Pais {
    AT("Austria"),
    BE("Belgium"),
    CY("Cyprus"),
    NL("Netherlands"),
    EE("Estonia"),
    FI("Finland"),
    FR("France"),
    DE("Germany"),
    IE("Ireland"),
    IT("Italy"),
    LV("Latvia"),
    LT("Lithuania"),
    LU("Luxemburg"),
    MT("Malta"),
    MC("Monaco"),
    PT("Portugal"),
    SM("San Marino"),
    SK("Slovakia"),
    SI("Slovenia"),
    ES("Spain"),
    VA("Vatican City");

    private final String pais;

    Pais(String pais) {
        this.pais = pais;
    }

    public String getNamePais() {
        return pais;
    }

}
