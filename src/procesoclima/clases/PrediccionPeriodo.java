package procesoclima.clases;

public class PrediccionPeriodo {
    private int diaInicial;
    private int diaFin;

    public PrediccionPeriodo() {
        super();
    }

    public PrediccionPeriodo(int diaInicial, int diaFin) {
        this.diaInicial = diaInicial;
        this.diaFin = diaFin;
    }

    public int getDiaInicial() {
        return diaInicial;
    }

    public void setDiaInicial(int diaInicial) {
        this.diaInicial = diaInicial;
    }

    public int getDiaFin() {
        return diaFin;
    }

    public void setDiaFin(int diaFin) {
        this.diaFin = diaFin;
    }	
}
