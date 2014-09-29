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
public class ServletCancelarOrdenOperacionBursatil extends  HttpServlet{
    
    
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
                
                      String email = request.getParameter( "email" );
                      String tipo=request.getParameter("tipoOperacion");
                        conexionDB x = new conexionDB();
                         PrintWriter respuesta = response.getWriter() ;
                       
                        if(tipo.equals("primario"))
                        {
                          try {
                              String sentancia="select * from OPERACIONES_EN_ESPERA_PRIM where email_oferente='"+email+"' and SOLICITUD is null";
                              ResultSet rta = x.consultar(sentancia);
                              enviarhtmlPrimario(respuesta, rta);
                              rta.close();
                          } catch (SQLException ex) {
                              Logger.getLogger(ServletCancelarOrdenOperacionBursatil.class.getName()).log(Level.SEVERE, null, ex);
                          }
                        }
                        else if(tipo.equals("secundario"))
                        {
                          try {
                              String sentancia="select  ID , EMAIL_INVER , nom_valor ,NIT_VALOR, TIPO_OPERACION, NVL(PRECIO_UNIDAD,0) , NVL(CANTIDAD,0) , FECHA , EMAIL_INTER , NVL(PRECIO,0) from OPERACIONES_EN_ESPERA_SEC where email_inver='"+email+"' and SOLICITUD is null";
                              ResultSet rta = x.consultar(sentancia);
                              enviarhtmlSecundario(respuesta, rta
                              
                              );
                              rta.close();
                              x.close();
                              
                          }
                          // Maneja el GET y el POST de la misma manera
                          catch (SQLException ex) {
                              Logger.getLogger(ServletCancelarOrdenOperacionBursatil.class.getName()).log(Level.SEVERE, null, ex);
                          }
                        }
                        
                        
                       
                      
                   
	    	
	    }
		
		
		
    private void enviarhtmlPrimario(PrintWriter respuesta , ResultSet rta)
    {
                 respuesta.write( "<!DOCTYPE html>\r\n" );
                    respuesta.write( "<!-- saved from url=(0044)http://getbootstrap.com/examples/dashboard/? -->\r\n" );
                    respuesta.write( "<html lang=\"en\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n" );
                    respuesta.write( "    <meta charset=\"utf-8\">\r\n" );
                    respuesta.write( "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" );
                    respuesta.write( "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" );
                    respuesta.write( "    <meta name=\"description\" content=\"\">\r\n" );
                    respuesta.write( "    <meta name=\"author\" content=\"\">\r\n" );
                    respuesta.write( "    <link rel=\"icon\" href=\"http://getbootstrap.com/favicon.ico\">\r\n" );
                    respuesta.write( "    <title>ValorAndes</title>\r\n" );
                    respuesta.write( "    <!-- Bootstrap core CSS -->\r\n" );
                    respuesta.write( "    <link href=\"http://getbootstrap.com/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n" );
                    respuesta.write( "    <!-- Custom styles for this template -->\r\n" );
                    respuesta.write( "    <link href=\"http://getbootstrap.com/examples/dashboard/dashboard.css\" rel=\"stylesheet\">\r\n" );
                    respuesta.write( "    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->\r\n" );
                    respuesta.write( "    <!--[if lt IE 9]><script src=\"../../assets/js/ie8-responsive-file-warning.js\"></script><![endif]-->\r\n" );
                    respuesta.write( "    <script src=\"./Dashboard Template for Bootstrap_files/ie-emulation-modes-warning.js\"></script>\r\n" );
                    respuesta.write( "    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n" );
                    respuesta.write( "    <!--[if lt IE 9]>\r\n" );
                    respuesta.write( "      <script src=\"https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js\"></script>\r\n" );
                    respuesta.write( "      <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\r\n" );
                    respuesta.write( "    <![endif]-->\r\n" );
                    respuesta.write( "  <style id=\"holderjs-style\" type=\"text/css\"></style><style type=\"text/css\"></style></head>\r\n" );
                    respuesta.write( "  <body>\r\n" );
                    respuesta.write( "    <div class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\r\n" );
                    respuesta.write( "      <div class=\"container-fluid\">\r\n" );
                    respuesta.write( "        <div class=\"navbar-header\">\r\n" );
                    respuesta.write( "          <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\r\n" );
                    respuesta.write( "            <span class=\"sr-only\">Toggle navigation</span>\r\n" );
                    respuesta.write( "            <span class=\"icon-bar\"></span>\r\n" );
                    respuesta.write( "            <span class=\"icon-bar\"></span>\r\n" );
                    respuesta.write( "            <span class=\"icon-bar\"></span>\r\n" );
                    respuesta.write( "          </button>\r\n" );
                    respuesta.write( "          <a class=\"navbar-brand\" href=\"http://getbootstrap.com/examples/dashboard/?#\">ValorAndes</a>\r\n" );
                    respuesta.write( "        </div>\r\n" );
                    respuesta.write( "        <div class=\"navbar-collapse collapse\">\r\n" );
                    respuesta.write( "          <ul class=\"nav navbar-nav navbar-right\">\r\n" );
                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">Opción 1</a></li>\r\n" );
                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">Opción 2</a></li>\r\n" );
                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">Opción 3</a></li>\r\n" );
                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">Opción 4</a></li>\r\n" );
                    respuesta.write( "<li class=\"dropdown\">\r\n" );
                    respuesta.write( "        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Opción 5 <b class=\"caret\"></b></a>\r\n" );
                    respuesta.write( "        <ul class=\"dropdown-menu\">\r\n" );
                    respuesta.write( "          <li><a href=\"#\">Action</a></li>\r\n" );
                    respuesta.write( "          <li><a href=\"#\">Another action</a></li>\r\n" );
                    respuesta.write( "          <li><a href=\"#\">Something else here</a></li>\r\n" );
                    respuesta.write( "          <li class=\"divider\"></li>\r\n" );
                    respuesta.write( "          <li class=\"dropdown-header\">Dropdown header</li>\r\n" );
                    respuesta.write( "          <li><a href=\"#\">Separated link</a></li>\r\n" );
                    respuesta.write( "          <li><a href=\"#\">One more separated link</a></li>\r\n" );
                    respuesta.write( "        </ul>\r\n" );
                    respuesta.write( "      </li>\r\n" );
                    respuesta.write( "          </ul>\r\n" );
                    respuesta.write( "          \r\n" );
                    respuesta.write( "          <form class=\"navbar-form navbar-right\">\r\n" );
                    respuesta.write( "            <input type=\"text\" class=\"form-control\" placeholder=\"Search...\">\r\n" );
                    respuesta.write( "          </form>\r\n" );
                    respuesta.write( "        </div>\r\n" );
                    respuesta.write( "      </div>\r\n" );
                    respuesta.write( "    </div>\r\n" );
                    respuesta.write( "    <div class=\"container-fluid\">\r\n" );
                    respuesta.write( "      <div class=\"row\">\r\n" );
                    respuesta.write( "        <div class=\"col-sm-3 col-md-2 sidebar\">\r\n" );
                    respuesta.write( "          <ul class=\"nav nav-sidebar\">\r\n" );
                   respuesta.write( "              <li ><a href=\"./ordenarOperacionPrim.htm\">Ordenar operación primaria</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./ordenarOperacionSec.htm\">Ordenar operación secundaria</a></li>\r\n" );
respuesta.write( "            <li class=\"active\"><a href=\"./cancelarOperacion.htm\">Cancelar operacion bursatil</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./registrarOperacion.htm\">Registrar operación </a></li>\r\n" );
respuesta.write( "          \r\n" );
respuesta.write( "          </ul>\r\n" );
respuesta.write( "          <ul class=\"nav nav-sidebar\">\r\n" );
respuesta.write( "            <li><a href=\"./consultarExistencia.htm\">Consultar existencia de valores</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./consultarOperacion.htm\">Consultar operaciones de un usuario</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./consultarValores.htm\">Consultar valores con mayor movimiento</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./consultarIntermediario.htm\">Consultar intermedario mas activo</a></li>\r\n" );
respuesta.write( "          </ul>\r\n" );
                    respuesta.write( "          \r\n" );
                    respuesta.write( "        </div>\r\n" );
                    respuesta.write( "        <div class=\"col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main\">\r\n" );
                    respuesta.write( "          <h1 class=\"page-header\">Cancelar operacion bursatil</h1>\r\n" );
                    respuesta.write( "          <div class=\"row placeholders\">\r\n" );
                    
                    respuesta.write( "          </div>\r\n" );
                    respuesta.write( "          <h2 class=\"sub-header\">Ordenes de operaciones bursatiles primarias</h2>\r\n" );
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
                        respuesta.write( "                  <th>Eliminar</th>\r\n" );
                              
        try {
            while(rta.next())
            {
                respuesta.write(" <form role=\"form\" action=\"ServletEliminaOrden.htm\" method=\"post\">");
                respuesta.write( "                </tr>\r\n" );
                respuesta.write( "              </thead>\r\n" );
                respuesta.write( "              <tbody>\r\n" );
                respuesta.write( "                <tr>\r\n" );
                respuesta.write("<input type=\"hidden\" value=\""+rta.getString("ID")+"\" name=\"id\"/>");
                respuesta.write( "                  <td>"+rta.getString("EMAIL_OFERENTE")+"</td>\r\n" );
                respuesta.write( "                  <td>"+rta.getString("EMAIL_INTERMEDIARIO")+"</td>\r\n" );
                respuesta.write( "                  <td>"+rta.getString("NIT_VALOR")+"</td>\r\n" );
                respuesta.write( "                  <td>"+rta.getString("NOM_VALOR")+"</td>\r\n" );
                respuesta.write( "                  <td>"+rta.getString("PRECIO_UNIDAD")+"</td>\r\n" );
                 respuesta.write( "                  <td>"+rta.getString("CANTIDAD")+"</td>\r\n" );
                    
                   respuesta.write( "                  <td>"+rta.getString("FECHA")+"</td>\r\n" );
                      respuesta.write( "                  <td>    <button type=\"submit\" class=\"btn btn-default\">Eliminar</button>   </td>\r\n" );
                respuesta.write( "                </tr>\r\n" );
                respuesta.write("</form>");
            }
        } catch (SQLException ex) {
            respuesta.write( "                  <td> quedo mal </td>\r\n" );
        }
                    respuesta.write( "              </tbody>\r\n" );
                    respuesta.write( "            </table>\r\n" );
                    respuesta.write( "          </div>\r\n" );
                    respuesta.write( "        </div>\r\n" );
                    respuesta.write( "      </div>\r\n" );
                    respuesta.write( "    </div>\r\n" );
                    respuesta.write( "    <!-- Bootstrap core JavaScript\r\n" );
                    respuesta.write( "    ================================================== -->\r\n" );
                    respuesta.write( "    <!-- Placed at the end of the document so the pages load faster -->\r\n" );
                    respuesta.write( "    <script src=\"./Dashboard Template for Bootstrap_files/jquery.min.js\"></script>\r\n" );
                    respuesta.write( "    <script src=\"./Dashboard Template for Bootstrap_files/bootstrap.min.js\"></script>\r\n" );
                    respuesta.write( "    <script src=\"./Dashboard Template for Bootstrap_files/docs.min.js\"></script>\r\n" );
                    respuesta.write( "    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->\r\n" );
                    respuesta.write( "    <script src=\"./Dashboard Template for Bootstrap_files/ie10-viewport-bug-workaround.js\"></script>\r\n" );
                    respuesta.write( "  \r\n" );
                    respuesta.write( "<div id=\"global-zeroclipboard-html-bridge\" class=\"global-zeroclipboard-container\" style=\"position: absolute; left: 0px; top: -9999px; width: 15px; height: 15px; z-index: 999999999;\" title=\"\" data-original-title=\"Copy to clipboard\">      <object classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\" id=\"global-zeroclipboard-flash-bridge\" width=\"100%\" height=\"100%\">         <param name=\"movie\" value=\"/assets/flash/ZeroClipboard.swf?noCache=1410465793904\">         <param name=\"allowScriptAccess\" value=\"sameDomain\">         <param name=\"scale\" value=\"exactfit\">         <param name=\"loop\" value=\"false\">         <param name=\"menu\" value=\"false\">         <param name=\"quality\" value=\"best\">         <param name=\"bgcolor\" value=\"#ffffff\">         <param name=\"wmode\" value=\"transparent\">         <param name=\"flashvars\" value=\"trustedOrigins=getbootstrap.com%2C%2F%2Fgetbootstrap.com%2Chttp%3A%2F%2Fgetbootstrap.com\">         <embed src=\"/assets/flash/ZeroClipboard.swf?noCache=1410465793904\" loop=\"false\" menu=\"false\" quality=\"best\" bgcolor=\"#ffffff\" width=\"100%\" height=\"100%\" name=\"global-zeroclipboard-flash-bridge\" allowscriptaccess=\"sameDomain\" allowfullscreen=\"false\" type=\"application/x-shockwave-flash\" wmode=\"transparent\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" flashvars=\"trustedOrigins=getbootstrap.com%2C%2F%2Fgetbootstrap.com%2Chttp%3A%2F%2Fgetbootstrap.com\" scale=\"exactfit\">                </object></div></body></html>\r\n" );

        
        
    }
    
    
    private void enviarhtmlSecundario(PrintWriter respuesta , ResultSet rta)
    {
             respuesta.write( "<!DOCTYPE html>\r\n" );
                    respuesta.write( "<!-- saved from url=(0044)http://getbootstrap.com/examples/dashboard/? -->\r\n" );
                    respuesta.write( "<html lang=\"en\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n" );
                    respuesta.write( "    <meta charset=\"utf-8\">\r\n" );
                    respuesta.write( "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n" );
                    respuesta.write( "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n" );
                    respuesta.write( "    <meta name=\"description\" content=\"\">\r\n" );
                    respuesta.write( "    <meta name=\"author\" content=\"\">\r\n" );
                    respuesta.write( "    <link rel=\"icon\" href=\"http://getbootstrap.com/favicon.ico\">\r\n" );
                    respuesta.write( "    <title>ValorAndes</title>\r\n" );
                    respuesta.write( "    <!-- Bootstrap core CSS -->\r\n" );
                    respuesta.write( "    <link href=\"http://getbootstrap.com/dist/css/bootstrap.min.css\" rel=\"stylesheet\">\r\n" );
                    respuesta.write( "    <!-- Custom styles for this template -->\r\n" );
                    respuesta.write( "    <link href=\"http://getbootstrap.com/examples/dashboard/dashboard.css\" rel=\"stylesheet\">\r\n" );
                    respuesta.write( "    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->\r\n" );
                    respuesta.write( "    <!--[if lt IE 9]><script src=\"../../assets/js/ie8-responsive-file-warning.js\"></script><![endif]-->\r\n" );
                    respuesta.write( "    <script src=\"./Dashboard Template for Bootstrap_files/ie-emulation-modes-warning.js\"></script>\r\n" );
                    respuesta.write( "    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->\r\n" );
                    respuesta.write( "    <!--[if lt IE 9]>\r\n" );
                    respuesta.write( "      <script src=\"https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js\"></script>\r\n" );
                    respuesta.write( "      <script src=\"https://oss.maxcdn.com/respond/1.4.2/respond.min.js\"></script>\r\n" );
                    respuesta.write( "    <![endif]-->\r\n" );
                    respuesta.write( "  <style id=\"holderjs-style\" type=\"text/css\"></style><style type=\"text/css\"></style></head>\r\n" );
                    respuesta.write( "  <body>\r\n" );
                    respuesta.write( "    <div class=\"navbar navbar-inverse navbar-fixed-top\" role=\"navigation\">\r\n" );
                    respuesta.write( "      <div class=\"container-fluid\">\r\n" );
                    respuesta.write( "        <div class=\"navbar-header\">\r\n" );
                    respuesta.write( "          <button type=\"button\" class=\"navbar-toggle collapsed\" data-toggle=\"collapse\" data-target=\".navbar-collapse\">\r\n" );
                    respuesta.write( "            <span class=\"sr-only\">Toggle navigation</span>\r\n" );
                    respuesta.write( "            <span class=\"icon-bar\"></span>\r\n" );
                    respuesta.write( "            <span class=\"icon-bar\"></span>\r\n" );
                    respuesta.write( "            <span class=\"icon-bar\"></span>\r\n" );
                    respuesta.write( "          </button>\r\n" );
                    respuesta.write( "          <a class=\"navbar-brand\" href=\"http://getbootstrap.com/examples/dashboard/?#\">ValorAndes</a>\r\n" );
                    respuesta.write( "        </div>\r\n" );
                    respuesta.write( "        <div class=\"navbar-collapse collapse\">\r\n" );
                    respuesta.write( "          <ul class=\"nav navbar-nav navbar-right\">\r\n" );
                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">Opción 1</a></li>\r\n" );
                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">Opción 2</a></li>\r\n" );
                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">Opción 3</a></li>\r\n" );
                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">Opción 4</a></li>\r\n" );
                    respuesta.write( "<li class=\"dropdown\">\r\n" );
                    respuesta.write( "        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">Opción 5 <b class=\"caret\"></b></a>\r\n" );
                    respuesta.write( "        <ul class=\"dropdown-menu\">\r\n" );
                    respuesta.write( "          <li><a href=\"#\">Action</a></li>\r\n" );
                    respuesta.write( "          <li><a href=\"#\">Another action</a></li>\r\n" );
                    respuesta.write( "          <li><a href=\"#\">Something else here</a></li>\r\n" );
                    respuesta.write( "          <li class=\"divider\"></li>\r\n" );
                    respuesta.write( "          <li class=\"dropdown-header\">Dropdown header</li>\r\n" );
                    respuesta.write( "          <li><a href=\"#\">Separated link</a></li>\r\n" );
                    respuesta.write( "          <li><a href=\"#\">One more separated link</a></li>\r\n" );
                    respuesta.write( "        </ul>\r\n" );
                    respuesta.write( "      </li>\r\n" );
                    respuesta.write( "          </ul>\r\n" );
                    respuesta.write( "          \r\n" );
                    respuesta.write( "          <form class=\"navbar-form navbar-right\">\r\n" );
                    respuesta.write( "            <input type=\"text\" class=\"form-control\" placeholder=\"Search...\">\r\n" );
                    respuesta.write( "          </form>\r\n" );
                    respuesta.write( "        </div>\r\n" );
                    respuesta.write( "      </div>\r\n" );
                    respuesta.write( "    </div>\r\n" );
                    respuesta.write( "    <div class=\"container-fluid\">\r\n" );
                    respuesta.write( "      <div class=\"row\">\r\n" );
                    respuesta.write( "        <div class=\"col-sm-3 col-md-2 sidebar\">\r\n" );
                    respuesta.write( "          <ul class=\"nav nav-sidebar\">\r\n" );
                   respuesta.write( "              <li ><a href=\"./ordenarOperacionPrim.htm\">Ordenar operación primaria</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./ordenarOperacionSec.htm\">Ordenar operación secundaria</a></li>\r\n" );
respuesta.write( "            <li class=\"active\"><a href=\"./cancelarOperacion.htm\">Cancelar operación bursátil</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./registrarOperacion.htm\">Registrar operación </a></li>\r\n" );
respuesta.write( "          \r\n" );
respuesta.write( "          </ul>\r\n" );
respuesta.write( "          <ul class=\"nav nav-sidebar\">\r\n" );
respuesta.write( "            <li><a href=\"./consultarExistencia.htm\">Consultar existencia de valores</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./consultarOperacion.htm\">Consultar operaciones de un usuario</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./consultarValores.htm\">Consultar valores con mayor movimiento</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./consultarIntermediario.htm\">Consultar intermedario mas activo</a></li>\r\n" );
respuesta.write( "          </ul>\r\n" );
                    respuesta.write( "          \r\n" );
                    respuesta.write( "        </div>\r\n" );
                    respuesta.write( "        <div class=\"col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main\">\r\n" );
                    respuesta.write( "          <h1 class=\"page-header\">Cancelar operacion bursatil</h1>\r\n" );
                    respuesta.write( "          <div class=\"row placeholders\">\r\n" );
                    
                    respuesta.write( "          </div>\r\n" );
                    respuesta.write( "          <h2 class=\"sub-header\">Ordenes de operaciones bursatiles Secundarias</h2>\r\n" );
                    respuesta.write( "          <div class=\"table-responsive\">\r\n" );
                    respuesta.write( "            <table class=\"table table-striped\">\r\n" );
                    respuesta.write( "              <thead>\r\n" );
                    respuesta.write( "                <tr>\r\n" );
                    respuesta.write( "                  <th>Email Invercionista</th>\r\n" );
                    respuesta.write( "                  <th>Email Intermediario</th>\r\n" );
                    respuesta.write( "                  <th>Tipo Operacion</th>\r\n" );
                    respuesta.write( "                  <th>Nit</th>\r\n" );
                    respuesta.write( "                  <th>Valor</th>\r\n" );
                    respuesta.write( "                  <th>Precio Unidad</th>\r\n" );
                    respuesta.write( "                  <th>Cantidad</th>\r\n" );
                           respuesta.write( "                  <th>Precio de Compra</th>\r\n" );
                    respuesta.write( "                  <th>Fecha</th>\r\n" );
                    respuesta.write( "                  <th>Eliminar</th>\r\n" );
                              
        try {
            
            
            
            
            while(rta.next())
            {
                respuesta.write(" <form role=\"form\" action=\"ServletEliminaOrden.htm\" method=\"get\">");
                respuesta.write( "                </tr>\r\n" );
                respuesta.write( "              </thead>\r\n" );
                respuesta.write( "              <tbody>\r\n" );
                respuesta.write( "                <tr>\r\n" );
                respuesta.write("<input type=\"hidden\" value=\""+rta.getString("ID")+"\" name=\"id\"/>");
                respuesta.write( "                  <td>"+rta.getString("EMAIL_INVER")+"</td>\r\n" );
                respuesta.write( "                  <td>"+rta.getString("EMAIL_INTER")+"</td>\r\n" );
                  respuesta.write( "                  <td>"+rta.getString("TIPO_OPERACION")+"</td>\r\n" );
                respuesta.write( "                  <td>"+rta.getString("NIT_VALOR")+"</td>\r\n" );
                respuesta.write( "                  <td>"+rta.getString("NOM_VALOR")+"</td>\r\n" );
                respuesta.write( "                  <td>"+rta.getString("NVL(PRECIO_UNIDAD,0)")+"</td>\r\n" );
                 respuesta.write( "                  <td>"+rta.getString("NVL(CANTIDAD,0)")+"</td>\r\n" );
                   respuesta.write( "                  <td>"+rta.getString("NVL(PRECIO,0)")+"</td>\r\n" );
                   respuesta.write( "                  <td>"+rta.getString("FECHA")+"</td>\r\n" );
                      respuesta.write( "                  <td>    <button type=\"submit\" class=\"btn btn-default\">Eliminar</button>   </td>\r\n" );
                respuesta.write( "                </tr>\r\n" );
                respuesta.write("</form>");
            }
        } catch (SQLException ex) {
            respuesta.write( "                  <td> quedo mal </td>\r\n" );
        }
                    respuesta.write( "              </tbody>\r\n" );
                    respuesta.write( "            </table>\r\n" );
                    respuesta.write( "          </div>\r\n" );
                    respuesta.write( "        </div>\r\n" );
                    respuesta.write( "      </div>\r\n" );
                    respuesta.write( "    </div>\r\n" );
                    respuesta.write( "    <!-- Bootstrap core JavaScript\r\n" );
                    respuesta.write( "    ================================================== -->\r\n" );
                    respuesta.write( "    <!-- Placed at the end of the document so the pages load faster -->\r\n" );
                    respuesta.write( "    <script src=\"./Dashboard Template for Bootstrap_files/jquery.min.js\"></script>\r\n" );
                    respuesta.write( "    <script src=\"./Dashboard Template for Bootstrap_files/bootstrap.min.js\"></script>\r\n" );
                    respuesta.write( "    <script src=\"./Dashboard Template for Bootstrap_files/docs.min.js\"></script>\r\n" );
                    respuesta.write( "    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->\r\n" );
                    respuesta.write( "    <script src=\"./Dashboard Template for Bootstrap_files/ie10-viewport-bug-workaround.js\"></script>\r\n" );
                    respuesta.write( "  \r\n" );
                    respuesta.write( "<div id=\"global-zeroclipboard-html-bridge\" class=\"global-zeroclipboard-container\" style=\"position: absolute; left: 0px; top: -9999px; width: 15px; height: 15px; z-index: 999999999;\" title=\"\" data-original-title=\"Copy to clipboard\">      <object classid=\"clsid:d27cdb6e-ae6d-11cf-96b8-444553540000\" id=\"global-zeroclipboard-flash-bridge\" width=\"100%\" height=\"100%\">         <param name=\"movie\" value=\"/assets/flash/ZeroClipboard.swf?noCache=1410465793904\">         <param name=\"allowScriptAccess\" value=\"sameDomain\">         <param name=\"scale\" value=\"exactfit\">         <param name=\"loop\" value=\"false\">         <param name=\"menu\" value=\"false\">         <param name=\"quality\" value=\"best\">         <param name=\"bgcolor\" value=\"#ffffff\">         <param name=\"wmode\" value=\"transparent\">         <param name=\"flashvars\" value=\"trustedOrigins=getbootstrap.com%2C%2F%2Fgetbootstrap.com%2Chttp%3A%2F%2Fgetbootstrap.com\">         <embed src=\"/assets/flash/ZeroClipboard.swf?noCache=1410465793904\" loop=\"false\" menu=\"false\" quality=\"best\" bgcolor=\"#ffffff\" width=\"100%\" height=\"100%\" name=\"global-zeroclipboard-flash-bridge\" allowscriptaccess=\"sameDomain\" allowfullscreen=\"false\" type=\"application/x-shockwave-flash\" wmode=\"transparent\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" flashvars=\"trustedOrigins=getbootstrap.com%2C%2F%2Fgetbootstrap.com%2Chttp%3A%2F%2Fgetbootstrap.com\" scale=\"exactfit\">                </object></div></body></html>\r\n" );

                   
    }
    
    
    
    
}
