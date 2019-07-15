package procesoclima.util.estrategias;

import java.util.List;
import procesoclima.clases.EstadoClima;
import procesoclima.clases.Punto;
import procesoclima.util.UtilCalculo;


public class EstrategiaPrediccionSequia extends EstrategiaPrediccionBase implements EstrategiaPrediccion {
	
    public EstrategiaPrediccionSequia(List<Punto> planetas, Punto sol) {
        super(planetas, sol);
    }

    @Override
    public boolean verificarCondicion() {
        planetas.add(sol);
        
        return UtilCalculo.estanAlineados(planetas);
    }

    @Override
    public EstadoClima getClima() {
        EstadoClima result = EstadoClima.Normal;

        if(verificarCondicion()) {
                result = EstadoClima.Sequia;
        }

        return result;
    }
}
