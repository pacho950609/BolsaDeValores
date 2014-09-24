/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaz;

import Conexion.conexionDB;
import Mundo.Consultas;
import Mundo.OperacionEsperaPrim;
import Mundo.OperacionEsperaSec;
import Mundo.SolicitudCompra;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ServletRegistrarOrden extends  HttpServlet{
    
    public static String SOLICITAR_COMPRA_PRIM= "Solicitar compra mercado primario";
    public static String SOLICITAR_COMPRA_SEC= "Solicitar compra mercado secundario";
    	
	protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException  {
	      
			
       
	        
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
                    if(tipoOperacion.equals(ServletRegistrarOperacionBursatil.TRANSAR_COMPRA))
                    {
                        ServletRegistrarOperacionBursatil.imprimirHeader(respuesta);
                        transarCompra(id, respuesta, x);
                        ServletRegistrarOperacionBursatil.imprimirFooter(respuesta);
                    }
                    
                     if(tipoOperacion.equals(ServletRegistrarOperacionBursatil.ACEPTAR_SOLICITUD_PRIM))
                    {
                        ServletRegistrarOperacionBursatil.imprimirHeader(respuesta);
                        aceptarSolicitudPrim(id, respuesta, x);
                        ServletRegistrarOperacionBursatil.imprimirFooter(respuesta);
                    }
                      if(tipoOperacion.equals(ServletRegistrarOperacionBursatil.ACEPTAR_SOLICITUD_SEC))
                    {
                        ServletRegistrarOperacionBursatil.imprimirHeader(respuesta);
                        aceptarSolicitudSec(id, respuesta, x);
                        ServletRegistrarOperacionBursatil.imprimirFooter(respuesta);
                    }
                     
                    
	    	x.close();
	    }
            
   
   

        private void transarCompra(int id, PrintWriter respuesta, conexionDB x) {
        OperacionEsperaSec o= new OperacionEsperaSec(id);
        if(o.getPrecio()==null)
        {
            transarCasoPrecioNull(o, respuesta, x);
            
        }
        else
        {
            transarCasoCantidadNull(o, respuesta, x);
        }
        
    }
        private void transarCasoPrecioNull(OperacionEsperaSec o, PrintWriter respuesta, conexionDB x) {
           ResultSet rta= Consultas.buscarVentasCompatiblesPrecioNullPrim(x,o);
            
             
           respuesta.write("                   <h3>Opciones de compra en el mercado primario</h3>");
         respuesta.write( "          <div class=\"table-responsive\">\r\n" );
                    respuesta.write( "            <table class=\"table table-striped\">\r\n" );
                    respuesta.write( "              <thead>\r\n" );
                    respuesta.write( "                <tr>\r\n" );
                    respuesta.write( "                  <th>Email Oferente</th>\r\n" );
                    respuesta.write( "                  <th>Email Intermediario</th>\r\n" );
                    respuesta.write( "                  <th>Nit</th>\r\n" );
                    respuesta.write( "                  <th>Valor</th>\r\n" );
                    respuesta.write( "                  <th>Precio Unidad</th>\r\n" );
                    respuesta.write( "                  <th>Cantidad</th>\r\n" );
                    respuesta.write( "                  <th>Fecha</th>\r\n" );
                    respuesta.write( "                  <th>Buscar comprador</th>\r\n" );
        
          try {
              while(rta.next())
              {
                  respuesta.write(" <form role=\"form\" action=\"ServletRegistrarOperacionBursatil.htm\" method=\"get\">");
                  respuesta.write( "                </tr>\r\n" );
                  respuesta.write( "              </thead>\r\n" );
                  respuesta.write( "              <tbody>\r\n" );
                  respuesta.write( "                <tr>\r\n" );
                  respuesta.write("<input type=\"hidden\" value=\""+rta.getString("ID")+"\" name=\"id\"/>");
                  respuesta.write("<input type=\"hidden\" value=\""+SOLICITAR_COMPRA_PRIM+"\" name=\"operacion\"/>");
                  respuesta.write( "                  <td>"+rta.getString("EMAIL_OFERENTE")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("EMAIL_INTERMEDIARIO")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("NIT_VALOR")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("NOM_VALOR")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("PRECIO_UNIDAD")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("CANTIDAD")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("FECHA")+"</td>\r\n" );
                  respuesta.write( "                  <td>    <button type=\"submit\" class=\"btn btn-default\">Buscar</button>   </td>\r\n" );
                  respuesta.write( "                </tr>\r\n" );
                  respuesta.write("</form>");
              }
              respuesta.write( "              </tbody>\r\n" );
                    respuesta.write( "            </table>\r\n" );
                    respuesta.write( "          </div>\r\n" );
                   
             
                                                            } catch (SQLException ex) {
                                                               respuesta.write("se totea "+ex.getMessage());
                                                            }
             //cierre de coneccion
                                                           try {
                                                              rta.close();
                                                          } catch (SQLException ex) {
                                                              Logger.getLogger(ServletRegistrarOrden.class.getName()).log(Level.SEVERE, null, ex);
                                                          }
           
           rta= Consultas.buscarVentasCompatiblesPrecioNullSec(x,o);
           
         
           //cierre de coneccion
                                                            
                                                          try {
                                                              rta.close();
                                                          } catch (Exception ex) {
                                                              Logger.getLogger(ServletRegistrarOrden.class.getName()).log(Level.SEVERE, null, ex);
                                                          }
           
           
       }
        private void transarCasoCantidadNull(OperacionEsperaSec o, PrintWriter respuesta, conexionDB x) {
       ResultSet rta= Consultas.buscarVentasCompatiblesCantidadNullPrim(x,o);
       
       
       
       
        //cierre de coneccion
                                                            
                                                          try {
                                                              rta.close();
                                                          } catch (SQLException ex) {
                                                              Logger.getLogger(ServletRegistrarOrden.class.getName()).log(Level.SEVERE, null, ex);
                                                          }
       
               rta= Consultas.buscarVentasCompatiblesCantidadNullSec(x,o);  
               
               
               
                //cierre de coneccion
                                                            
                                                          try {
                                                              rta.close();
                                                          } catch (SQLException ex) {
                                                              Logger.getLogger(ServletRegistrarOrden.class.getName()).log(Level.SEVERE, null, ex);
                                                          }
        }	

        private void aceptarSolicitudPrim(int id, PrintWriter respuesta, conexionDB x) 
        {
        try {
            SolicitudCompra s= SolicitudCompra.darsolicitudPrim(id);
            
            
            OperacionEsperaPrim opPrim= OperacionEsperaPrim.obtenerPorIdSolicitud(id);
            
            
            
            
            
            
            
            
            
        } catch (Exception ex) {
            respuesta.write(ex.getMessage());
        }
        }
        private void aceptarSolicitudSec(int id, PrintWriter respuesta, conexionDB x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        
        
       
    
}
