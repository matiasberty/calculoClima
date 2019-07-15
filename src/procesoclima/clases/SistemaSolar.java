package procesoclima.clases;

import java.util.List;

public class SistemaSolar {
	
	private Sol sol;
	private List<Planeta> planetas;
	
	public SistemaSolar() {
		super();
	}
	
	public SistemaSolar(List<Planeta> planetas, Sol sol) {
		
		this.sol = sol;
		this.planetas = planetas;
		
	}

	public List<Planeta> getPlanetas() {
		return this.planetas;
	}

	public Sol getSol() {
		return sol;
	}
}
