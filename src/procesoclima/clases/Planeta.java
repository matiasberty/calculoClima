package procesoclima.clases;

public class Planeta extends Elemento{

    private String nombre;
    private double distancia;
    private double velocidad;

    public Planeta() {
        super();
    }

    public Planeta(double distancia, double velocidad) {
        super(null);
        this.distancia = distancia;
        this.velocidad = velocidad;
    }

    public double getDistancia() {
        return distancia;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }
}
