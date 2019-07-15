package procesoclima.util.estrategias;

import procesoclima.clases.EstadoClima;

public interface EstrategiaPrediccion {
    public boolean verificarCondicion() throws Exception;
    public EstadoClima getClima() throws Exception;
}
