package procesoclima.util.estrategias;

import java.util.LinkedList;
import java.util.List;
import procesoclima.clases.EstadoClima;
import procesoclima.clases.Punto;
import procesoclima.util.UtilCalculo;


public class EstrategiaPrediccionCondicionesOptimas extends EstrategiaPrediccionBase implements EstrategiaPrediccion {
    public EstrategiaPrediccionCondicionesOptimas(List<Punto> planetas, Punto sol) {
	super(planetas, sol);
    }

    @Override
    public boolean verificarCondicion() throws Exception {
        if(UtilCalculo.estanAlineados(planetas)){
            planetas.add(sol);
            return !UtilCalculo.estanAlineados(planetas);
        }else{
            return false;
        }
    }

    @Override
    public EstadoClima getClima() throws Exception {
        EstadoClima res = EstadoClima.Normal;

        if(verificarCondicion()) {
            res = EstadoClima.CondicionesOptimas;
        }

        return res;
    }
}
