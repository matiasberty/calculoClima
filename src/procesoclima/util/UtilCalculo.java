package procesoclima.util;

import java.text.DecimalFormat;
import java.util.List;
import procesoclima.clases.Punto;


public class UtilCalculo {
	
	public static Punto getPosicion(double distancia, double angulo) {
            double rad = Math.toRadians(angulo);
            double y = Math.round(Math.cos(rad) * distancia);
            double x = Math.round(Math.sin(rad) * distancia);

            return new Punto(x, y);
	}
	
	public static double calcularPerimetro(List<Punto> puntos) throws Exception {
            double perimetro = 0;

            if(puntos.size() < 3) {
                throw new Exception("Se necesitan al menos 3 puntos para poder realizar el cálculo.");
            }

            Punto punto = puntos.get(0);
            Punto aux = punto;
            for (int i = 1; i < puntos.size(); i++) {
                Punto punto2 = puntos.get(i);
                double distancia = distanciaEntrePuntos(aux, punto2);
                perimetro += distancia;
                aux = punto2;
            }

            Punto punto2 = puntos.get(puntos.size() - 1);
            perimetro += distanciaEntrePuntos(punto, punto2);
            return perimetro;
	}
	
	public static double distanciaEntrePuntos(Punto p1, Punto  p2) {
            return Math.sqrt( Math.pow( (p1.getX() - p2.getX()), 2 ) + Math.pow( (p1.getY() - p2.getY()), 2 ) );
	}
	
	public static boolean estanAlineados(List<Punto> puntos) {
            boolean result = true;

            if(puntos.size() > 3) {
                Punto p1 = puntos.get(0);
                Punto p2 = puntos.get(1);
                Punto p3 = puntos.get(2);
                Punto p4 = puntos.get(3);

                if((p1.getX() == p2.getX()) && (p1.getX() == p3.getX()) && (p1.getX() == p4.getX())){
                    result = true;
                }else if((p1.getX() == p2.getX()) && (p1.getX() == p3.getX()) && (p1.getX() == p4.getX())){
                    result = true;
                }else{
                    //double pendiente = (p2.getY() - p1.getY()) / (p2.getX() - p1.getX());
                    long pendiente = (Math.round(p2.getY()) - Math.round(p1.getY())) / (Math.round(p2.getX()) - Math.round(p1.getX()));

                    for (int i = 2; i < puntos.size(); i++) {
                        Punto p = puntos.get(i);
                        long ladoIzquierdo = Math.round(p.getY()) - Math.round(p1.getY());
                        long ladoDerecho = pendiente * (Math.round(p.getX()) - Math.round(p1.getY()));

                        if(ladoIzquierdo != ladoDerecho) {
                            result = false;
                            break;
                        }
                    }
                }
            }else{
                Punto p1 = puntos.get(0);
                Punto p2 = puntos.get(1);
                Punto p3 = puntos.get(2);

                long dist1 = calcularDistanciaEntre2Puntos(p1, p2);
                long dist2 = calcularDistanciaEntre2Puntos(p2, p3);
                long dist3 = calcularDistanciaEntre2Puntos(p1, p3);
                
                if((dist1 + dist2) == dist3){
                    result = true;
                }else{
                    result = false;
                }
                
                if(!result){
                    dist1 = calcularDistanciaEntre2Puntos(p1, p3);
                    dist2 = calcularDistanciaEntre2Puntos(p3, p2);
                    dist3 = calcularDistanciaEntre2Puntos(p1, p2);

                    if((dist1 + dist2) == dist3){
                        result = true;
                    }else{
                        result = false;
                    }
                }
                
                if(!result){
                    dist1 = calcularDistanciaEntre2Puntos(p2, p1);
                    dist2 = calcularDistanciaEntre2Puntos(p1, p3);
                    dist3 = calcularDistanciaEntre2Puntos(p2, p3);

                    if((dist1 + dist2) == dist3){
                        result = true;
                    }else{
                        result = false;
                    }
                }
                
                if(!result){
                    dist1 = calcularDistanciaEntre2Puntos(p2, p3);
                    dist2 = calcularDistanciaEntre2Puntos(p3, p1);
                    dist3 = calcularDistanciaEntre2Puntos(p2, p1);

                    if((dist1 + dist2) == dist3){
                        result = true;
                    }else{
                        result = false;
                    }
                }
                
                if(!result){
                    dist1 = calcularDistanciaEntre2Puntos(p3, p1);
                    dist2 = calcularDistanciaEntre2Puntos(p1, p2);
                    dist3 = calcularDistanciaEntre2Puntos(p3, p2);

                    if((dist1 + dist2) == dist3){
                        result = true;
                    }else{
                        result = false;
                    }
                }
                
                if(!result){
                    dist1 = calcularDistanciaEntre2Puntos(p3, p2);
                    dist2 = calcularDistanciaEntre2Puntos(p2, p1);
                    dist3 = calcularDistanciaEntre2Puntos(p3, p1);

                    if((dist1 + dist2) == dist3){
                        result = true;
                    }else{
                        result = false;
                    }
                }
            }

            return result;
	}
        
        public static long calcularDistanciaEntre2Puntos(Punto p1, Punto p2) {
            long cateto1 = Math.round(p2.getX()) - Math.round(p1.getX());
            long cateto2 = Math.round(p2.getY()) - Math.round(p1.getY());
            long hipotenusa = Math.round(Math.sqrt(cateto1*cateto1 + cateto2*cateto2));
            return hipotenusa;
        }

	public static boolean dentroDeFigura (List<Punto> puntos, Punto p) throws Exception {
	    if(puntos.size() < 3) {
	    	throw new Exception("La figura debe tener al menos 3 puntos.");
	    }
	    
	    Punto p1 = puntos.get(0);
	    Punto p2 = puntos.get(1);
	    boolean flag = calcularLado(p1, p2, p) < 0.0f;
	    Punto puntoAnterior = p2;
	    for (int i = 2; i < puntos.size(); i++) {
	    	Punto aux = puntos.get(i);
                boolean flag2 = calcularLado(puntoAnterior, aux, p) < 0.0f;

                if(flag != flag2) {
                        return false;
                }

                puntoAnterior = aux;
            }

	    boolean flag2 = calcularLado(puntoAnterior, p1, p) < 0.0f;
	    return flag && flag2;
	}
	
	private static double calcularLado(Punto p1, Punto p2, Punto p){
	    return (p.getX() - p2.getX()) * (p1.getY() - p2.getY()) - (p1.getX() - p2.getX()) * (p.getY() - p2.getY());
	}
}
