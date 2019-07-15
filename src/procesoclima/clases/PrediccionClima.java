package procesoclima.clases;

//import com.fasterxml.jackson.annotation.JsonAutoDetect;
//import com.fasterxml.jackson.annotation.JsonProperty;

//@JsonAutoDetect
public class PrediccionClima {
    //@JsonProperty
    private int dia;

    //@JsonProperty
    private EstadoClima estado;

    public PrediccionClima() {
            super();
    }

    public PrediccionClima(int dia, EstadoClima estado) {
            this.dia = dia;
            this.estado = estado;
    }

    public int getDia() {
        return dia;
    }

    public EstadoClima getEstado() {
            return estado;
    }

    public void setEstado(EstadoClima estado) {
            this.estado = estado;
    }

    public void setDia(int dia) {
            this.dia = dia;
    }

}
