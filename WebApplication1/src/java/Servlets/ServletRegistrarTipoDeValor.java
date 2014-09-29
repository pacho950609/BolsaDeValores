/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Fachada.conexionDB;
import VOS.TipoDeValor;
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
public class ServletRegistrarTipoDeValor extends  HttpServlet{
    
    
    
     protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	    {
	        // Maneja el GET y el POST de la misma manera
	
	        
	    }
		/**
	     * Maneja un pedido POST de un cliente
	     * @param request Pedido del cliente
	     * @param response Respuesta
	     */
	    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	    {
                
                
                
                
                try{
                    
                    //valida los datos
                String nombre = request.getParameter( "nombre" );
                String descripcion = request.getParameter( "descripcion" );
                int identifiador = Integer.parseInt(request.getParameter( "identificador" ));
                TipoDeValor.validarDatos(identifiador, nombre, descripcion);
                
                
                
                        conexionDB x = new conexionDB();
                        boolean rta = x.actualizarCrear("SELECT * FROM PARRANDEROS.BARES");
                        
                        
                        // Maneja el GET y el POST de la misma manera
                        PrintWriter respuesta = response.getWriter() ;
                        respuesta.write( "<html>\r\n" );
                        
                            if(rta)
                            {
                                 respuesta.write("ha sido agregada exitosamente"); 
                            }
                            
                          else
                            {
                                respuesta.write("ha ocurrido algun error"); 
                            }
                        
                        respuesta.write( "</html>\r\n" );
                        x.close();
                }
                catch(Exception e)
                        {
                            
                            
                            
                            
                        }
                 
	    	
	    }
		
		
		
    
    
    
    
    
    
}
