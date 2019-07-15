package procesoclima.util.estrategias;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import procesoclima.clases.Punto;

public class EstrategiaPrediccionManager {

    public static List<EstrategiaPrediccion> LoadStategies(List<Punto> planetas, Punto sol) {
        List<EstrategiaPrediccion> estrategias = new LinkedList<>();

        List<Punto> planetasCO = new LinkedList<>();
        List<Punto> planetasS = new ArrayList<>();
        List<Punto> planetasL = new ArrayList<>();
        
        planetasCO.addAll(planetas);
        planetasS.addAll(planetas);
        planetasL.addAll(planetas);
        
        EstrategiaPrediccionCondicionesOptimas estrategiaCondicionesOptimas = new EstrategiaPrediccionCondicionesOptimas(planetasCO, sol);
        EstrategiaPrediccionSequia estrategiaSequia = new EstrategiaPrediccionSequia(planetasS, sol);
        EstrategiaPrediccionLluvia estrategiaLluvia = new EstrategiaPrediccionLluvia(planetasL, sol);

        estrategias.add(estrategiaSequia);
        estrategias.add(estrategiaLluvia);
        estrategias.add(estrategiaCondicionesOptimas);

        return estrategias;
    }
}
