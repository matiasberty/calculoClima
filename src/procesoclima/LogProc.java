/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package procesoclima;

import java.io.FileWriter;
import java.io.File;
import java.util.Calendar;

/**
 *
 * @author mbertinotti
 */
public class LogProc {
    public static final String INFO = "INFO";
    public static final String WARN = "WARN";
    public static final String ERROR = "ERROR";
    public static String pathLog = "log.txt";
            
    public static void write(Exception e, String tipo ){
        String msg = tipo + " - " + e.getMessage();
        write(msg);
    }
    
    public static void write(Exception e, String msg, String tipo){
        msg = tipo + " - " + msg + " - "  + e.getMessage() + " | " + e.toString();
        if(e.getMessage() == null){
            e.printStackTrace();
        }
        write(msg);
    }
    
    public static void write(String msg, String tipo){
        msg = tipo + " - " + msg;
        write(msg);
    }
    
    public static void write(String msg){
        try{
            escribirLog(msg);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private static void escribirLog(String msg) throws Exception{   
//       FileWriter archivo;
//       if (new File(pathLog).exists()==false){
//           archivo=new FileWriter(new File(pathLog),false);
//       }
//       archivo = new FileWriter(new File(pathLog), true);
//       Calendar fechaActual = Calendar.getInstance(); //Para poder utilizar el paquete calendar     
//       archivo.write((String.valueOf(fechaActual.get(Calendar.DAY_OF_MONTH))
//             +"/"+String.valueOf(fechaActual.get(Calendar.MONTH)+1)
//             +"/"+String.valueOf(fechaActual.get(Calendar.YEAR))
//             +";"+String.valueOf(fechaActual.get(Calendar.HOUR_OF_DAY))
//             +":"+String.valueOf(fechaActual.get(Calendar.MINUTE))
//             +":"+String.valueOf(fechaActual.get(Calendar.SECOND)))+" -- "+msg+"\r\n");
//       archivo.close(); 
    }
    
}
