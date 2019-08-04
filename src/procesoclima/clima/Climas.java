/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesoclima.clima;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import procesoclima.clases.Planeta;
import procesoclima.clases.Punto;
import procesoclima.clases.PrediccionClima;
import procesoclima.clases.EstadoClima;
import procesoclima.clases.Sol;
import procesoclima.util.UtilCalculo;
import procesoclima.util.estrategias.EstrategiaPrediccion;
import procesoclima.util.estrategias.EstrategiaPrediccionManager;

/**
 *
 * @author mbertinotti
 */
public class Climas {
    public static String procesarClima(){
        return calcularClimaDesde(new Date());
    }
    
    public static String calcularClimaDesde(Date fechaInicial){
        List<Planeta> planetas = initPlanetas();
        Punto puntoSol = new Punto(0, 0);
        
        Date fechaFin = null;
        Long cantDias = null;
        Calendar c = Calendar.getInstance();
        c.setTime(fechaInicial);
        c.add(Calendar.YEAR, 10);
        fechaFin = c.getTime();
        
        cantDias = fechaFin.getTime() - fechaInicial.getTime();
        cantDias = (cantDias / (24*60*60*1000));
        
        ArrayList<PrediccionClima> predicciones = new ArrayList<PrediccionClima>();
        int maxDiaLluvia = 0;
        String repiteDiaMaximo = "";
        double maxPerimetro = 0;
        boolean conto = false;
        int periodo = 0;
        HashMap<String, Integer> totales = new HashMap<>();
        
        totales.put(EstadoClima.Lluvia.getDescripcion(), 0);
        totales.put(EstadoClima.CondicionesOptimas.getDescripcion(), 0);
        totales.put(EstadoClima.Sequia.getDescripcion(), 0);
        totales.put(EstadoClima.Normal.getDescripcion(), 0);
        
        try{
            JsonObject jd = new JsonObject();
            for(int dia = 1; dia <= cantDias; dia++){
                List<Punto> puntosDePlanetas = getPuntosDePlanetas(planetas, dia);
                
                PrediccionClima prediccion = predecirClima(puntosDePlanetas, puntoSol, dia);
                predicciones.add(prediccion);
                String descripcion = prediccion.getEstado().getDescripcion();
                
                totales.put(descripcion, totales.get(descripcion) + 1);
                jd.addProperty(String.valueOf(dia), descripcion);
                
                if(descripcion == EstadoClima.Lluvia.getDescripcion()){
                    double perimetro = UtilCalculo.calcularPerimetro(puntosDePlanetas);
                    if(perimetro > maxPerimetro) {
                        maxPerimetro = perimetro;
                        maxDiaLluvia = dia;
                        repiteDiaMaximo = "" + dia;
                    }else if(perimetro == maxPerimetro) {
                        if(repiteDiaMaximo.equals("")){
                            repiteDiaMaximo = "" + dia;
                        }else{
                            repiteDiaMaximo = repiteDiaMaximo + "," + dia;
                        }
                    }
                    
                    if(!conto){
                        periodo = periodo + 1;
                        conto = true;
                    }
                }else{
                    conto = false;
                }
            }
            
            System.out.println("Proceso Finalizado - Resultados obtenidos:");
            System.out.println("Cantidad de días de Normal: " + totales.get(EstadoClima.Normal.getDescripcion()));
            System.out.println("Cantidad de días de Sequía: " + totales.get(EstadoClima.Sequia.getDescripcion()));
            System.out.println("Cantidad de días con Condiciones Optimas: " + totales.get(EstadoClima.CondicionesOptimas.getDescripcion()));
            System.out.println("Cantidad de días de Lluvia: " + totales.get(EstadoClima.Lluvia.getDescripcion()));
            System.out.println("Cantidad de Períodos de Lluvia: " + periodo);
            System.out.println("Picos Max. de Lluvia: " + repiteDiaMaximo);
            System.out.println("Donde el pico máximo será: " + maxDiaLluvia);
            
            JsonObject jo = new JsonObject();
            
            jo.addProperty("diasNormal", totales.get(EstadoClima.Normal.getDescripcion()));
            jo.addProperty("diasSequia", totales.get(EstadoClima.Sequia.getDescripcion()));
            jo.addProperty("diasCondicionesOptimas", totales.get(EstadoClima.CondicionesOptimas.getDescripcion()));
            jo.addProperty("diasLluvia", totales.get(EstadoClima.Lluvia.getDescripcion()));
            jo.addProperty("cantPeriodoLluvia", periodo);
            jo.addProperty("picoMaxLluvia", repiteDiaMaximo);
            jo.addProperty("diaMaxLluvia", maxDiaLluvia);
            
            JsonObject res = new JsonObject();
            res.addProperty("dias", jd.toString());
            res.addProperty("resultados", jo.toString());
            
            return res.toString();
        }catch(Exception e){
            System.out.println("ERROR EN: procesoclima.clima.Climas.procesarClima()");
            System.out.println("ERROR: " + e.getMessage());
            return "ERROR";
        }
    }
    
    private static List<Planeta> initPlanetas(){
        List<Planeta> list = new ArrayList<Planeta>();
        
        Planeta ferengi = new Planeta(500, 1);
        Planeta betasoide = new Planeta(2000, 3);
        Planeta vulcano = new Planeta(1000, -5);
        
        list.add(ferengi);
        list.add(betasoide);
        list.add(vulcano);
        
        return list;
    }
    
    private static List<Punto> getPuntosDePlanetas(List<Planeta> planetas, int dia) {
        return planetas.stream().map( p -> posicionPlaneta(p, dia)).collect(Collectors.toList());
    }
    
    public static Punto posicionPlaneta(Planeta planeta, int dia) {
        double distancia = planeta.getDistancia();
        double velocidad = planeta.getVelocidad();
        double angulo = velocidad * dia;

        return UtilCalculo.getPosicion(distancia, angulo);
    }
    
    public static PrediccionClima predecirClima(List<Punto> planetas, Punto sol, int dia)  throws Exception {
        List<EstrategiaPrediccion> estrategias = EstrategiaPrediccionManager.LoadStategies(planetas, sol);
        EstadoClima clima = null;
        
        for (EstrategiaPrediccion estrategia : estrategias) {
            clima = estrategia.getClima();

            if(clima != null && clima.getDescripcion() != EstadoClima.Normal.getDescripcion()) {
                break;
            }
        }

        return new PrediccionClima(dia, clima);
    }
}
