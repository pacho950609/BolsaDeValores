package Servlets;
import Fachada.conexionDB;
import VOS.OperacionEsperaPrim;
import VOS.OperacionEsperaSec;
import VOS.OperacionRegPrim;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class pacho extends  HttpServlet{
	
	



	
		
		
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
                           PrintWriter respuesta = response.getWriter() ;
                        respuesta.write( "<html>\r\n" );
                        
                    try {
                          OperacionRegPrim x = new OperacionRegPrim(90);
                        respuesta.write("Bien"+ x.toString());
                       
                    } catch (Exception ex) {
                         
                         respuesta.write("Mal"+ex.getMessage());
                    }
                     respuesta.write( "</html>\r\n" );
	    	
	    }
		
		
		
		
	

}
