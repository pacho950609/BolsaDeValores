/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Fachada.conexionDB;
import DAO.Consultas;
import VOS.OperacionEsperaPrim;
import VOS.OperacionEsperaSec;
import VOS.SolicitudCompra;
import VOS.ValoresDeInversionistas;
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
        
            SolicitudCompra s= null;
            OperacionEsperaPrim vendedor= null;
            OperacionEsperaSec comprador= null;
            try {
                s= SolicitudCompra.darsolicitudPrim(id);
            vendedor= new OperacionEsperaPrim();
            vendedor= vendedor.obtenerPorIdSolicitud(id);
            comprador= new OperacionEsperaSec();
            comprador= comprador.obtenerPorIdSolicitud(id);
            } catch (Exception e) {
                
                
                 
             respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">Error al obtener los datos de la solicitud</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   

            }
            
            if(vendedor==null||comprador==null)
            {
                respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error en datos de la solicitud\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">No existen las compras y ventas asociadas a esta solicitud</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );    
                                return;
            }
            
            int vender =vendedor.getCantidad();
            int comprar= comprador.getCantidad();
            
            if(vender>comprar)
            {
                  respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">No se puede realizar la transaccion, intente nuevamente</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   

            }
            else
            {
                vendedor.setCantidad(vender-comprar);
                if(vendedor.getCantidad()==0)
                {
                    try {
                        vendedor.eliminarOperacion();
                        
                    } catch (Exception ex) {
                         respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error eliminando operacion de venta\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">No se puede realizar la transaccion, intente de nuevo</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   
                    }
                }
                 else
                {
                    vendedor.setSolicitudNull();
                }
                try {
                    comprador.eliminarOperacion();
                } catch (Exception ex) {
                    
                     respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error eliminando operacion de compra\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">No se puede realizar la transaccion, intente de nuevo</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   
                }
                  try {
                ValoresDeInversionistas v= new ValoresDeInversionistas(comprador.getEmailInversionista(), comprador.getNitValor(), comprador.getNomValor());
                    if(v.getCantidad()!=null)
                    v.setCantidad(v.getCantidad()+comprador.getCantidad());
                    else
                    {
                        int sum= (int)(comprador.getPrecio()/vendedor.getPrecioUnidad());
                        v.setCantidad(v.getCantidad()+sum);
                    }
            } 
                  catch (Exception e) {
                
                ValoresDeInversionistas.insertarValoresDeInversionistas(comprador.getEmailInversionista(),comprador.getNitValor() , comprador.getNomValor(), comprador.getCantidad());
            
            }
                          try {
                 s.eliminarSolicitudPrim(id);
                  respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Operacion exitosa\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">Se realizo la operacion correctamente</div>\r\n" );
                                respuesta.write( "          </div>\r\n" ); 
             } catch (Exception ex) {
                  respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">Error eliminando la solicitud</div>\r\n" );
                                respuesta.write( "          </div>\r\n" ); 
                
             } 
                
            }
        }
        private void aceptarSolicitudSec(int id, PrintWriter respuesta, conexionDB x) {
         SolicitudCompra s= null;
            OperacionEsperaSec vendedor= null;
            OperacionEsperaSec comprador= null;
            try {
                s= SolicitudCompra.darsolicitudPrim(id);
            vendedor= new OperacionEsperaSec();
            vendedor= vendedor.obtenerPorIdSolicitud(id);
            comprador= new OperacionEsperaSec();
            comprador= comprador.obtenerPorIdSolicitud(id);
            } catch (Exception e) {
                
                
                 
             respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">Error al obtener los datos de la solicitud</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   

            }
             if(vendedor==null||comprador==null)
            {
                respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error en datos de la solicitud\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">No existen las compras y ventas asociadas a esta solicitud</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );    
                            return;
            }
            
            int vender =vendedor.getCantidad();
            int comprar= comprador.getCantidad();
            
            if(vender>comprar)
            {
                  respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">No se puede realizar la transaccion, la cantidad a comprar es superior a la de vender, intente nuevamente</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   

            }
            else
            {
                vendedor.setCantidad(vender-comprar);
                if(vendedor.getCantidad()==0)
                {
                    try {
                        vendedor.eliminarOperacion();
                        
                    } catch (Exception ex) {
                         respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error eliminando operacion de venta\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">No se puede realizar la transaccion, intente de nuevo</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   
                    }
                }
                else
                {
                    vendedor.setSolicitudNull();
                }
                try {
                    comprador.eliminarOperacion();
                } catch (Exception ex) {
                    
                     respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error eliminando operacion de compra\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">No se puede realizar la transaccion, intente de nuevo</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );   
                }
                  try {
                ValoresDeInversionistas v= new ValoresDeInversionistas(comprador.getEmailInversionista(), comprador.getNitValor(), comprador.getNomValor());
                    if(v.getCantidad()!=null)
                    v.setCantidad(v.getCantidad()+comprador.getCantidad());
                    else
                    {
                        int sum= (int)(comprador.getPrecio()/vendedor.getPrecioUnidad());
                        v.setCantidad(v.getCantidad()+sum);
                    }
            } 
                  catch (Exception e) {
                
                ValoresDeInversionistas.insertarValoresDeInversionistas(comprador.getEmailInversionista(),comprador.getNitValor() , comprador.getNomValor(), comprador.getCantidad());
            
            }
             try {
                 s.eliminarSolicitudSec(id);
                  respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Operacion exitosa\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">Se realizo la operacion correctamente</div>\r\n" );
                                respuesta.write( "          </div>\r\n" ); 
             } catch (Exception ex) {
                  respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Error\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">Error eliminando la solicitud</div>\r\n" );
                                respuesta.write( "          </div>\r\n" ); 
                
             }
                          
                
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
