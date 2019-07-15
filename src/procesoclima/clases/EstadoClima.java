package procesoclima.clases;

public enum EstadoClima {
    Normal("N"),
    Lluvia("L"),
    CondicionesOptimas("CO"),
    Sequia("S");

    private String descripcion;

    private EstadoClima(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
