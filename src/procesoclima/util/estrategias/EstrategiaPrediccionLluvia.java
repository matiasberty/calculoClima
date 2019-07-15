package procesoclima.util.estrategias;

import java.util.List;
import procesoclima.clases.EstadoClima;
import procesoclima.clases.Punto;
import procesoclima.util.UtilCalculo;


public class EstrategiaPrediccionLluvia extends EstrategiaPrediccionBase implements EstrategiaPrediccion {

    public EstrategiaPrediccionLluvia(List<Punto> planetas, Punto sol) {
        super(planetas, sol);
    }

    @Override
    public boolean verificarCondicion() throws Exception {
        boolean result = true;

        result = UtilCalculo.dentroDeFigura(planetas, sol);

        return result;
    }

    @Override
    public EstadoClima getClima() throws Exception {
        EstadoClima result = EstadoClima.Normal;

        if(verificarCondicion()) {
            result = EstadoClima.Lluvia;
        }

        return result;
    }
}
