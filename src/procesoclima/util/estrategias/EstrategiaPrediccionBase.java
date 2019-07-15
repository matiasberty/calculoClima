package procesoclima.util.estrategias;

import java.util.List;
import procesoclima.clases.Punto;

public class EstrategiaPrediccionBase {
    protected List<Punto> planetas;
    protected Punto sol;

    public EstrategiaPrediccionBase(List<Punto> planetas, Punto sol) {
        this.planetas = planetas;
        this.sol = sol;
    }
}