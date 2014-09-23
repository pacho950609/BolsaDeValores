/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Conexion.conexionDB;
import Mundo.Consultas;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author GLORIA AZUCENA
 */
public class ServletRegistrarOrden extends  HttpServlet{
    
    
    	
		protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	    {
	      
			
       
	        
	    }
		/**
	     * Maneja un pedido POST de un cliente
	     * @param request Pedido del cliente
	     * @param response Respuesta
	     */
	    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	    {
                
                    String tipoOperacion = request.getParameter("operacion");
                    int id = Integer.parseInt(request.getParameter("id"));
                    conexionDB x = new conexionDB();
                    PrintWriter respuesta = response.getWriter() ;
                    if(tipoOperacion.equals(ServletRegistrarOperacionBursatil.TRANSAR_SECUNDARIO))
                    {
                        ServletRegistrarOperacionBursatil.imprimirHeader(respuesta);
                        ServletRegistrarOperacionBursatil.imprimirHeader(respuesta);
                    }
                    
                     if(tipoOperacion.equals(ServletRegistrarOperacionBursatil.TRANSAR_SECUNDARIO))
                    {
                        ServletRegistrarOperacionBursatil.imprimirHeader(respuesta);
                        ServletRegistrarOperacionBursatil.imprimirHeader(null);
                        ServletRegistrarOperacionBursatil.imprimirHeader(respuesta);
                    }
                    
	    	
	    }
            
           
    
}
