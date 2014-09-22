/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Conexion.conexionDB;
import Mundo.Validaciones;
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
public class ServletOrdenarOperacionBursatil extends HttpServlet{
    
    
	
		
protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	    {
	        // secundario
                             PrintWriter respuesta = response.getWriter() ;
                           
			
                            try{
                                 String email = request.getParameter( "email" );
                            String nombreValor = request.getParameter( "nombreValor" );
                            int cantidad = Integer.parseInt(request.getParameter( "monto" ));
                            int montoDinero = Integer.parseInt(request.getParameter( "cantidadDinero" ));
                            String comprarVender =request.getParameter( "comprarVender" );
                            String empresa =request.getParameter( "empresa" );
                            String fecha = "fecha";
                    
                            Validaciones.validarOrdenarOperacionBursatilSecundario(email, nombreValor, cantidad, fecha, comprarVender, empresa, montoDinero);
                            //conexion base de datos
                             conexionDB x = new conexionDB();
                             boolean rta = x.actualizarCrear("SELECT * FROM PARRANDEROS.BARES");
                        
                           
                            respuesta.write( "<html>\r\n" );
                        
                            if(rta)
                            {
                                //en caso que se halla agregado exitosamente
                                 respuesta.write("Ha terminado exitosamente "); 
                            }
                            
                          else
                            {
                                //caso no se logro terminar 
                                respuesta.write("No tienes los permisos correspondiente"); 
                            }
                        
                        respuesta.write( "</html>\r\n" );
                        
                        
                            }
                
		 catch (SQLException ex) 
                    {
                        Logger.getLogger(pacho.class.getName()).log(Level.SEVERE, null, ex);
                    }
                   
                   catch (Exception e )
                   {
                    respuesta.write(e.getMessage());    
                   }
	    	
	    	
	        
	    }
		/**
	     * Maneja un pedido POST de un cliente
	     * @param request Pedido del cliente
	     * @param response Respuesta
	     */
      
      
      
	    protected void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	    {
                
                //primario 
                 PrintWriter respuesta = response.getWriter() ;
                
                   try {
                           
                            String emailOferente = request.getParameter( "emailOferente" );
                              String emailIntermediario = request.getParameter( "emailIntermediario" );
                            String nombreValor = request.getParameter( "nombreValor" );
                            String nit = request.getParameter( "nit" );
                             String precio = request.getParameter( "precio" );
                            int cantidad = Integer.parseInt(request.getParameter( "cantidad" ));
                          
                            //realiza las validaciones correspondientes
                        
                            //conexion base de datos
                             conexionDB x = new conexionDB();
                             boolean rta = x.actualizarCrear("SELECT * FROM PARRANDEROS.BARES");
                        
                           
                            respuesta.write( "<html>\r\n" );
                        
                           respuesta.write(emailOferente+emailIntermediario+nit+cantidad);
                           respuesta.write(precio);
                        
                        respuesta.write( "</html>\r\n" );
                   } 
                   
                   
                   
                  
                   
                   catch (Exception e )
                   {
                       respuesta.write(e.getMessage()); 
                   }
	    	
	    }
		
		    
    
}
