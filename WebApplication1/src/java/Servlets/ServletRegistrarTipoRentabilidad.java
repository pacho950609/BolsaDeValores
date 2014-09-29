/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Fachada.conexionDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GLORIA AZUCENA
 */
public class ServletRegistrarTipoRentabilidad extends  HttpServlet{
    
    
    
     protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	    {
	        // Maneja el GET y el POST de la misma manera
			
       
                
			PrintWriter respuesta = response.getWriter() ;
			respuesta.write( "<html>\r\n" );
	    	respuesta.write("esdsd " );
	    	respuesta.write( "</html>\r\n" );
	    	
	        
	    }
		/**
	     * Maneja un pedido POST de un cliente
	     * @param request Pedido del cliente
	     * @param response Respuesta
	     */
	    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	    {
                
                    try {
                        conexionDB x = new conexionDB();
                        ResultSet rta = x.consultar("SELECT * FROM PARRANDEROS.BARES");
                        
                        
                        // Maneja el GET y el POST de la misma manera
                        PrintWriter respuesta = response.getWriter() ;
                        respuesta.write( "<html>\r\n" );
                        while(rta.next())
                        {
                            
                            respuesta.write(rta.getString("NOMBRE")+ "<br>" );
                        }
                        respuesta.write( "</html>\r\n" );
                        rta.close();
                        x.close();
                    } 
                    catch (Exception ex) 
                    {
                        Logger.getLogger(pacho.class.getName()).log(Level.SEVERE, null, ex);
                    }
	    	
	    }
		
		
		
 
    
    
}
