/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package procesoclima;

import java.net.URL;
import java.sql.*;
import procesoclima.clima.Climas;

/**
 *
 * @author mbertinotti
 */
public class Proceso {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
           Climas.procesarClima();
        } catch (Exception e) {
           e.printStackTrace();
           LogProc.write(e.getMessage());
       }        
    }
}
