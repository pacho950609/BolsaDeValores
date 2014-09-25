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
import Mundo.OperacionRegPrim;
import Mundo.OperacionRegSec;
import Mundo.SolicitudCompra;
import Mundo.ValoresDeInversionistas;
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
	     String op= request.getParameter("operacion");
               int idCompra = Integer.parseInt(request.getParameter("idCompra"));
               int idVenta = Integer.parseInt(request.getParameter("idVenta"));
                    conexionDB x = new conexionDB();
                    PrintWriter respuesta = response.getWriter() ;
            if(op.equals(SOLICITAR_COMPRA_PRIM))
            {
                  ServletRegistrarOperacionBursatil.imprimirHeader(respuesta);
                  realizarSolicitudPrim(idCompra, idVenta, respuesta, x);
                  ServletRegistrarOperacionBursatil.imprimirFooter(respuesta);
            }
            if(op.equals(SOLICITAR_COMPRA_SEC))
            {
                  ServletRegistrarOperacionBursatil.imprimirHeader(respuesta);
                  realizarSolicitudSec(idCompra, idVenta, respuesta, x);
                  ServletRegistrarOperacionBursatil.imprimirFooter(respuesta);
            }
       
	        
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
            transarCasoPrecioNull(o, respuesta, x, id);
            
        }
        else
        {
            transarCasoCantidadNull(o, respuesta, x, id);
        }
    }
        private void transarCasoPrecioNull(OperacionEsperaSec o, PrintWriter respuesta, conexionDB x, int id) {
           ResultSet rta= Consultas.buscarVentasCompatiblesPrecioNullPrim(x,o);
            
             
           respuesta.write("                   <h3>Opciones de compra en el mercado primario</h3>");
         respuesta.write( "          <div class=\"table-responsive\">\r\n" );
                    respuesta.write( "            <table class=\"table table-striped\">\r\n" );
                    respuesta.write( "              <thead>\r\n" );
                    respuesta.write( "                <tr>\r\n" );
                    respuesta.write( "                  <th>Email Oferente</th>\r\n" );
                    respuesta.write( "                  <th>Email Intermediario</th>\r\n" );
                    respuesta.write( "                  <th>Nombre empresa</th>\r\n" );
                    respuesta.write( "                  <th>Valor</th>\r\n" );
                    respuesta.write( "                  <th>Precio Unidad</th>\r\n" );
                    respuesta.write( "                  <th>Cantidad</th>\r\n" );
                    respuesta.write( "                  <th>Fecha</th>\r\n" );
                    respuesta.write( "                  <th>Solicitar compra</th>\r\n" );
        
          try {
              while(rta.next())
              {
                  respuesta.write(" <form role=\"form\" action=\"ServletRegistrarOrden.htm\" method=\"get\">");
                  respuesta.write( "                </tr>\r\n" );
                  respuesta.write( "              </thead>\r\n" );
                  respuesta.write( "              <tbody>\r\n" );
                  respuesta.write( "                <tr>\r\n" );
                  respuesta.write("<input type=\"hidden\" value=\""+rta.getString("ID")+"\" name=\"idVenta\"/>");
                  respuesta.write("<input type=\"hidden\" value=\""+id+"\" name=\"idCompra\"/>");
                  respuesta.write("<input type=\"hidden\" value=\""+SOLICITAR_COMPRA_PRIM+"\" name=\"operacion\"/>");
                  respuesta.write( "                  <td>"+rta.getString("EMAIL_OFERENTE")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("EMAIL_INTERMEDIARIO")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+Consultas.darNombreDeEmpresa(rta.getString("NIT_VALOR"))+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("NOM_VALOR")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("PRECIO_UNIDAD")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("CANTIDAD")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("FECHA")+"</td>\r\n" );
                  respuesta.write( "                  <td>    <button type=\"submit\" class=\"btn btn-default\">Solicitar</button>   </td>\r\n" );
                  respuesta.write( "                </tr>\r\n" );
                  respuesta.write("</form>");
              }
              respuesta.write( "              </tbody>\r\n" );
                    respuesta.write( "            </table>\r\n" );
                    respuesta.write( "          </div>\r\n" );
                   
             
                                                            } catch (SQLException ex) {
                                                               respuesta.write("Error en  opciones de compra mercado primario: "+ex.getMessage());
                                                            }
             //cierre de coneccion
                                                           try {
                                                              rta.close();
                                                              x.close();
                                                          } catch (SQLException ex) {
                                                              Logger.getLogger(ServletRegistrarOrden.class.getName()).log(Level.SEVERE, null, ex);
                                                          }
           x = new conexionDB();
           ResultSet rta1= Consultas.buscarVentasCompatiblesPrecioNullSec(x,o);
           
                respuesta.write("                   <h3>Opciones de compra en el mercado Secundario</h3>");
         respuesta.write( "          <div class=\"table-responsive\">\r\n" );
                    respuesta.write( "            <table class=\"table table-striped\">\r\n" );
                    respuesta.write( "              <thead>\r\n" );
                    respuesta.write( "                <tr>\r\n" );
                    respuesta.write( "                  <th>Email Inversionista</th>\r\n" );
                    respuesta.write( "                  <th>Email Intermediario</th>\r\n" );
                    respuesta.write( "                  <th>Empresa del vaor</th>\r\n" );
                    respuesta.write( "                  <th>Valor</th>\r\n" );
                    respuesta.write( "                  <th>Precio Unidad</th>\r\n" );
                    respuesta.write( "                  <th>Cantidad</th>\r\n" );
                    respuesta.write( "                  <th>Fecha</th>\r\n" );
                    respuesta.write( "                  <th>Solicitar compra</th>\r\n" );
        
          try {
              while(rta1.next())
              {
                  respuesta.write(" <form role=\"form\" action=\"ServletRegistrarOrden.htm\" method=\"get\">");
                  respuesta.write( "                </tr>\r\n" );
                  respuesta.write( "              </thead>\r\n" );
                  respuesta.write( "              <tbody>\r\n" );
                  respuesta.write( "                <tr>\r\n" );
                  respuesta.write("<input type=\"hidden\" value=\""+rta1.getString("ID")+"\" name=\"idVenta\"/>");
                  respuesta.write("<input type=\"hidden\" value=\""+id+"\" name=\"idCompra\"/>");
                  respuesta.write("<input type=\"hidden\" value=\""+SOLICITAR_COMPRA_SEC+"\" name=\"operacion\"/>");
                  respuesta.write( "                  <td>"+rta1.getString("EMAIL_INVER")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta1.getString("EMAIL_INTER")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+Consultas.darNombreDeEmpresa(rta1.getString("NIT_VALOR"))+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta1.getString("NOM_VALOR")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta1.getString("PRECIO_UNIDAD")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta1.getString("CANTIDAD")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta1.getString("FECHA")+"</td>\r\n" );
                  respuesta.write( "                  <td>    <button type=\"submit\" class=\"btn btn-default\">Solicitar</button>   </td>\r\n" );
                  respuesta.write( "                </tr>\r\n" );
                  respuesta.write("</form>");
              }
          }
              catch (Exception ex) {
                      respuesta.write("se totea en Opciones de compra en el mercado Secundario"+ex.getMessage());
                                                            }
              respuesta.write( "              </tbody>\r\n" );
                    respuesta.write( "            </table>\r\n" );
                    respuesta.write( "          </div>\r\n" );
                   
           
           
           
           
           
           //cierre de coneccion
                                                            
                                                          try {
                                                              x.close();
                                                              rta1.close();
                                                              
                                                          } catch (Exception ex) {
                                                              Logger.getLogger(ServletRegistrarOrden.class.getName()).log(Level.SEVERE, null, ex);
                                                          }
           
           
       }
        private void transarCasoCantidadNull(OperacionEsperaSec o, PrintWriter respuesta, conexionDB x, int id) {
      ResultSet rta= Consultas.buscarVentasCompatiblesCantidadNullPrim(x,o);
            
             
           respuesta.write("                   <h3>Opciones de compra en el mercado primario</h3>");
         respuesta.write( "          <div class=\"table-responsive\">\r\n" );
                    respuesta.write( "            <table class=\"table table-striped\">\r\n" );
                    respuesta.write( "              <thead>\r\n" );
                    respuesta.write( "                <tr>\r\n" );
                    respuesta.write( "                  <th>Email Oferente</th>\r\n" );
                    respuesta.write( "                  <th>Email Intermediario</th>\r\n" );
                    respuesta.write( "                  <th>Nombre Empresa</th>\r\n" );
                    respuesta.write( "                  <th>Valor</th>\r\n" );
                    respuesta.write( "                  <th>Precio Unidad</th>\r\n" );
                    respuesta.write( "                  <th>Cantidad</th>\r\n" );
                    respuesta.write( "                  <th>Fecha</th>\r\n" );
                    respuesta.write( "                  <th>Solicitar compra</th>\r\n" );
        
          try {
              while(rta.next())
              {
                  respuesta.write(" <form role=\"form\" action=\"ServletRegistrarOrden.htm\" method=\"get\">");
                  respuesta.write( "                </tr>\r\n" );
                  respuesta.write( "              </thead>\r\n" );
                  respuesta.write( "              <tbody>\r\n" );
                  respuesta.write( "                <tr>\r\n" );
                  respuesta.write("<input type=\"hidden\" value=\""+rta.getString("ID")+"\" name=\"idVenta\"/>");
                  respuesta.write("<input type=\"hidden\" value=\""+id+"\" name=\"idCompra\"/>");
                  respuesta.write("<input type=\"hidden\" value=\""+SOLICITAR_COMPRA_PRIM+"\" name=\"operacion\"/>");
                  respuesta.write( "                  <td>"+rta.getString("EMAIL_OFERENTE")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("EMAIL_INTERMEDIARIO")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+Consultas.darNombreDeEmpresa(rta.getString("NIT_VALOR"))+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("NOM_VALOR")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("PRECIO_UNIDAD")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("CANTIDAD")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("FECHA")+"</td>\r\n" );
                  respuesta.write( "                  <td>    <button type=\"submit\" class=\"btn btn-default\">Solicitar</button>   </td>\r\n" );
                  respuesta.write( "                </tr>\r\n" );
                  respuesta.write("</form>");
              }
              respuesta.write( "              </tbody>\r\n" );
                    respuesta.write( "            </table>\r\n" );
                    respuesta.write( "          </div>\r\n" );
                   
             
                                                            } catch (SQLException ex) {
                                                               respuesta.write("Error en  opciones de compra mercado primario: "+ex.getMessage());
                                                            }
             //cierre de coneccion
                                                           try {
                                                              rta.close();
                                                              x.close();
                                                          } catch (SQLException ex) {
                                                              Logger.getLogger(ServletRegistrarOrden.class.getName()).log(Level.SEVERE, null, ex);
                                                          }
           x = new conexionDB();
           ResultSet rta1= Consultas.buscarVentasCompatiblesCantidadNullSec(x,o);
           
                respuesta.write("                   <h3>Opciones de compra en el mercado Secundario</h3>");
         respuesta.write( "          <div class=\"table-responsive\">\r\n" );
                    respuesta.write( "            <table class=\"table table-striped\">\r\n" );
                    respuesta.write( "              <thead>\r\n" );
                    respuesta.write( "                <tr>\r\n" );
                    respuesta.write( "                  <th>Email Inversionista</th>\r\n" );
                    respuesta.write( "                  <th>Email Intermediario</th>\r\n" );
                    respuesta.write( "                  <th>Empresa</th>\r\n" );
                    respuesta.write( "                  <th>Valor</th>\r\n" );
                    respuesta.write( "                  <th>Precio Unidad</th>\r\n" );
                    respuesta.write( "                  <th>Cantidad</th>\r\n" );
                    respuesta.write( "                  <th>Fecha</th>\r\n" );
                    respuesta.write( "                  <th>Solicitar compra</th>\r\n" );
        
          try {
              while(rta1.next())
              {
                  respuesta.write(" <form role=\"form\" action=\"ServletRegistrarOrden.htm\" method=\"get\">");
                  respuesta.write( "                </tr>\r\n" );
                  respuesta.write( "              </thead>\r\n" );
                  respuesta.write( "              <tbody>\r\n" );
                  respuesta.write( "                <tr>\r\n" );
                  respuesta.write("<input type=\"hidden\" value=\""+rta1.getString("ID")+"\" name=\"idVenta\"/>");
                  respuesta.write("<input type=\"hidden\" value=\""+id+"\" name=\"idCompra\"/>");
                  respuesta.write("<input type=\"hidden\" value=\""+SOLICITAR_COMPRA_SEC+"\" name=\"operacion\"/>");
                  respuesta.write( "                  <td>"+rta1.getString("EMAIL_INVER")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta1.getString("EMAIL_INTER")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+Consultas.darNombreDeEmpresa(rta1.getString("NIT_VALOR"))+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta1.getString("NOM_VALOR")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta1.getString("PRECIO_UNIDAD")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta1.getString("CANTIDAD")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta1.getString("FECHA")+"</td>\r\n" );
                  respuesta.write( "                  <td>    <button type=\"submit\" class=\"btn btn-default\">Solicitar</button>   </td>\r\n" );
                  respuesta.write( "                </tr>\r\n" );
                  respuesta.write("</form>");
              }
          }
              catch (Exception ex) {
                      respuesta.write("se totea en Opciones de compra en el mercado Secundario"+ex.getMessage());
                                                            }
              respuesta.write( "              </tbody>\r\n" );
                    respuesta.write( "            </table>\r\n" );
                    respuesta.write( "          </div>\r\n" );
                   
           
           
           
           
           
           //cierre de coneccion
                                                            
                                                          try {
                                                              x.close();
                                                              rta1.close();
                                                              
                                                          } catch (Exception ex) {
                                                              Logger.getLogger(ServletRegistrarOrden.class.getName()).log(Level.SEVERE, null, ex);
                                                          }
        }	

        
        
        private void aceptarSolicitudPrim(int id, PrintWriter respuesta, conexionDB x) {
        try {
            SolicitudCompra s= SolicitudCompra.darsolicitudPrim(id);
            
            
            OperacionEsperaPrim opPrim= new OperacionEsperaPrim();
            opPrim= opPrim.obtenerPorIdSolicitud(id);
            OperacionEsperaSec opSec= new OperacionEsperaSec();
            opSec= opSec.obtenerPorIdSolicitud(id);
            
            
            int vender =opPrim.getCantidad();
            int comprar= opSec.getCantidad();
            
            opPrim.setCantidad(vender-comprar);
            OperacionEsperaSec.eliminarOperacion(opSec.getId());
            
            
            //OperacionRegPrim.insertarOperacion(opPrim.getEmailOferente(),
                    //opSec.getEmailInversionista(), 
                   // opSec.getNitValor(), SOLICITAR_COMPRA_SEC, comprar, comprar, null);
            
            respuesta.write(opPrim.toString());
            respuesta.write(opSec.toString());
            
        } catch (Exception ex) {
            respuesta.write(ex.getMessage());
        }
        }
        private void aceptarSolicitudSec(int id, PrintWriter respuesta, conexionDB x) {
        try {
            SolicitudCompra s= SolicitudCompra.darsolicitudPrim(id);
            
            
            OperacionEsperaPrim opPrim= new OperacionEsperaPrim();
            opPrim= opPrim.obtenerPorIdSolicitud(id);
            OperacionEsperaSec opSec= new OperacionEsperaSec();
            opSec= opSec.obtenerPorIdSolicitud(id);
            
            
            int vender =opPrim.getCantidad();
            int comprar= opSec.getCantidad();
            
            opPrim.setCantidad(vender-comprar);
            OperacionEsperaSec.eliminarOperacion(opSec.getId());
            
            
            //OperacionRegSec.insertarOperacion(SOLICITAR_COMPRA_SEC, SOLICITAR_COMPRA_SEC, 
                    //comprar, SOLICITAR_COMPRA_SEC, comprar, comprar, null);
            
            respuesta.write(opPrim.toString());
            respuesta.write(opSec.toString());
            
        } catch (Exception ex) {
            respuesta.write(ex.getMessage());
        }
    }

        private void realizarSolicitudPrim(int idCompra, int idVenta, PrintWriter respuesta, conexionDB x) {
            
        try {
            OperacionEsperaPrim vendedor =new OperacionEsperaPrim(idVenta);
            OperacionEsperaSec comprador=  new OperacionEsperaSec(idCompra);
            int id =SolicitudCompra.insertarSolicitudPrimaria(comprador.getNomValor(),vendedor.getNitValor(),comprador.getCantidad(),vendedor.getPrecioUnidad(),
                comprador.getEmailIntermediario(), comprador.getEmailInversionista(), vendedor.getEmailIntermediario()
                ,vendedor.getEmailOferente());
            
            if(id!=-1&&comprador.setSolicitud(""+id)&&vendedor.setSolicitud(id))
            {
                
                try {
                    ValoresDeInversionistas v= new ValoresDeInversionistas(comprador.getEmailInversionista(), comprador.getNitValor(), comprador.getNomValor());
                    if(v.getCantidad()!=null)
                    v.setCantidad(v.getCantidad()+comprador.getCantidad());
                    else
                    {
                        int sum= (int)(comprador.getPrecio()/vendedor.getPrecioUnidad());
                        v.setCantidad(v.getCantidad()+sum);
                    }
            } catch (Exception e) {
                ValoresDeInversionistas.insertarValoresDeInversionistas(comprador.getEmailInversionista(),comprador.getNitValor() , comprador.getNomValor(), comprador.getCantidad());
            }
                
                
             respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Orden realizada\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">La solicitud se ingreso con exito al sistema, el id es "+id +"</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   
            }
            else
            {
                  respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Orden fallida\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">La solicitud no se ingreso al sistema</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   
            }
                
            
            
        } catch (Exception ex) {
            respuesta.write(ex.getMessage());
        }
        
        
        }
        private void realizarSolicitudSec(int idCompra, int idVenta, PrintWriter respuesta, conexionDB x) {
            try {
            OperacionEsperaSec vendedor =new OperacionEsperaSec(idVenta);
            OperacionEsperaSec comprador=  new OperacionEsperaSec(idCompra);
            int id =SolicitudCompra.insertarSolicitudSecundaria (comprador.getNomValor(),vendedor.getNitValor(),comprador.getCantidad(),vendedor.getPrecioUnidad(),
                comprador.getEmailIntermediario(), comprador.getEmailInversionista(), vendedor.getEmailIntermediario()
                ,vendedor.getEmailInversionista());
             if(id!=-1&&comprador.setSolicitud(""+id)&&vendedor.setSolicitud(id+""))
            {
                
                try {
                    ValoresDeInversionistas v= new ValoresDeInversionistas(comprador.getEmailInversionista(), comprador.getNitValor(), comprador.getNomValor());
                    if(v.getCantidad()!=null)
                    v.setCantidad(v.getCantidad()+comprador.getCantidad());
                    else
                    {
                        int sum= (int)(comprador.getPrecio()/vendedor.getPrecioUnidad());
                        v.setCantidad(v.getCantidad()+sum);
                    }
            } catch (Exception e) {
                ValoresDeInversionistas.insertarValoresDeInversionistas(comprador.getEmailInversionista(),comprador.getNitValor() , comprador.getNomValor(), comprador.getCantidad());
            }
                
                
             respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Orden realizada\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">La solicitud se ingreso con exito al sistema, el id es "+id +"</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   
            }
            else
            {
                  respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Orden fallida\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">La solicitud no se ingreso al sistema</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   
            }
                
            
            
        } catch (Exception ex) {
            respuesta.write(ex.getMessage());
        }
        
        }
        
        
        
        
       
    
}
