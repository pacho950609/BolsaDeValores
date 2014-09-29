/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import Fachada.conexionDB;
import VOS.Validaciones;
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
public class ServletOrdenarOperacionBursatil extends HttpServlet{
    
    
	
		
protected void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
	    {
	        // secundario
                             PrintWriter respuesta = response.getWriter() ;
                              String emailInversionista = request.getParameter( "emailInversionista" );
                                String emailIntermediario = request.getParameter( "emailIntermediario" );
                                String tipoOperacion = request.getParameter( "tipoOperacion" );
                                String nombreValor = request.getParameter( "tipoValor" );
                                String nit = request.getParameter( "nit" );
                                String precio = request.getParameter( "precio" );
                                String precioCompra = request.getParameter( "precioCompra" );
                                String seranull=null;
                               
                                String cantidad = request.getParameter( "cantidad" );
                                  conexionDB x = new conexionDB();
                           
			
                            try{
                               
                                
                            if(tipoOperacion.equalsIgnoreCase("COMPRA")||(tipoOperacion.equals("VENTA")&& Validaciones.validarTieneParaVender(emailInversionista, nombreValor, cantidad, nit, x)))
                            {
                                
                            //conexion base de datos
                           
                              ResultSet maximoid= x.consultar("SELECT MAX (ID) FROM  OPERACIONES_EN_ESPERA_SEC ");
                            int nuevoid=0;
                            if(maximoid.next())
                            {
                             nuevoid = Integer.parseInt(maximoid.getString("MAX(ID)"))+1 ;
                              
                            }
                            maximoid.close();
                            String sqlS="INSERT INTO OPERACIONES_EN_ESPERA_SEC VALUES("
                             +nuevoid 
                                    +",'"+emailInversionista 
                                    +"','"+nombreValor
                                    + "'," +nit
                                    + ",'" +tipoOperacion
                                    +"',"+precio
                                    +","+cantidad
                                    +",(SELECT SYSDATE FROM DUAL)"
                                    +",'"+emailIntermediario+"',"+precioCompra+","+seranull+")";
                             boolean rta = x.actualizarCrear(sqlS);
                        
                           
                            
                        
                            if(rta)
                            {
                                //en caso que se halla agregado exitosamente
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
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 1</a></li>\r\n" );
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 2</a></li>\r\n" );
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 3</a></li>\r\n" );
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 4</a></li>\r\n" );
respuesta.write( "<li class=\"dropdown\">\r\n" );
respuesta.write( "        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">OpciÃ³n 5 <b class=\"caret\"></b></a>\r\n" );
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
respuesta.write( "            <li><a href=\"./ordenarOperacionPrim.htm\">Ordenar operaciÃ³n primaria</a></li>\r\n" );
respuesta.write( "            <li class=\"active\"><a href=\"./ordenarOperacionSec.htm\">Ordenar operaciÃ³n secundaria</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./cancelarOperacion.htm\">Cancelar operaciÃ³n bursÃ¡til</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./registrarOperacionPrim.htm\">Registrar operaciÃ³n primaria</a></li>\r\n" );
respuesta.write( "             <li ><a href=\"./registrarOperacionSec.htm\">Registrar operaciÃ³n secundaria</a></li>\r\n" );
respuesta.write( "           \r\n" );
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
respuesta.write( "          <h1 class=\"page-header\">Ordenar operaciÃ³n secundaria</h1>\r\n" );
respuesta.write( " \r\n" );
respuesta.write( "           <div class=\"panel panel-primay\">\r\n" );
respuesta.write( "            <div class=\"panel-body\">\r\n" );
respuesta.write( "              Orden realizada\r\n" );
respuesta.write( "            </div>\r\n" );
respuesta.write( "            <div class=\"panel-footer\">La orden se realizÃ³ correctamente</div>\r\n" );
respuesta.write( "          </div>\r\n" );
respuesta.write( "<form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"get\">\r\n" );
respuesta.write( "      <h3>Venta</h3>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email inversionista</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailInversionista\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "    <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo de operacion</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoOperacion\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de operacion</option>\r\n" );
respuesta.write( "          <option value=\"COMPRA\" >Compra</option>\r\n" );
respuesta.write( "          <option value=\"VENTA\">Venta</option>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>   \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
respuesta.write( "          <option value=\"BonoPub\">Bono pÃºblico</option>\r\n" );
respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>           \r\n" );
respuesta.write( "  \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Cantidad</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"cantidad\" name=\"cantidad\" placeholder=\"Introduzca la cantidad de valores a transar\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Precio</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"precio\" name=\"precio\" placeholder=\"Introduzca el precio por cada unidad de valor\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
respuesta.write( "</form>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "          <form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"get\">\r\n" );
respuesta.write( "      <h3>Compra por cantidad</h3>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email inversionista</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailInversionista\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "    <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo de operacion</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoOperacion\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de operacion</option>\r\n" );
respuesta.write( "          <option value=\"VENTA\">Compra</option>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>   \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
respuesta.write( "          <option value=\"BonoPub\">Bono público</option>\r\n" );
respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>           \r\n" );
respuesta.write( "  \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Cantidad</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"cantidad\" name=\"cantidad\" placeholder=\"Introduzca la cantidad de valores a transar\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
respuesta.write( "</form>\r\n" );
respuesta.write( "          <form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"get\">\r\n" );
respuesta.write( "      <h3>Compra por precio</h3>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email inversionista</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailInversionista\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "    <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo de operacion</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoOperacion\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de operacion</option>\r\n" );
respuesta.write( "          <option value=\"VENTA\">Compra</option>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>   \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
respuesta.write( "          <option value=\"BonoPub\">Bono público</option>\r\n" );
respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>           \r\n" );
respuesta.write( "  \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Precio</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"precio\" name=\"precioCompra\" placeholder=\"El precio total de la compra\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
respuesta.write( "</form>\r\n" );
respuesta.write( "         \r\n" );
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
                            
                          else
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
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 1</a></li>\r\n" );
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 2</a></li>\r\n" );
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 3</a></li>\r\n" );
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 4</a></li>\r\n" );
respuesta.write( "<li class=\"dropdown\">\r\n" );
respuesta.write( "        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">OpciÃ³n 5 <b class=\"caret\"></b></a>\r\n" );
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
respuesta.write( "            <li><a href=\"./ordenarOperacionPrim.htm\">Ordenar operaciÃ³n primaria</a></li>\r\n" );
respuesta.write( "            <li class=\"active\"><a href=\"./ordenarOperacionSec.htm\">Ordenar operaciÃ³n secundaria</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./cancelarOperacion.htm\">Cancelar operaciÃ³n bursÃ¡til</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./registrarOperacionPrim.htm\">Registrar operaciÃ³n primaria</a></li>\r\n" );
respuesta.write( "             <li ><a href=\"./registrarOperacionSec.htm\">Registrar operaciÃ³n secundaria</a></li>\r\n" );
respuesta.write( "           \r\n" );
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
respuesta.write( "          <h1 class=\"page-header\">Ordenar operaciÃ³n secundaria</h1>\r\n" );
respuesta.write( "          <div class=\"panel panel-danger\">\r\n" );
respuesta.write( "            <div class=\"panel-body\">\r\n" );
respuesta.write( "              Error ordenando\r\n" );
respuesta.write( "            </div>\r\n" );
respuesta.write( "            <div class=\"panel-footer\">Los datos ingresados no son correctos</div>\r\n" );
respuesta.write( "          </div>\r\n" );
respuesta.write( "          \r\n" );
respuesta.write( "<form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"get\">\r\n" );
respuesta.write( "      <h3>Venta</h3>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email inversionista</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailInversionista\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "    <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo de operacion</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoOperacion\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de operacion</option>\r\n" );
respuesta.write( "          <option value=\"COMPRA\" >Compra</option>\r\n" );
respuesta.write( "          <option value=\"VENTA\">Venta</option>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>   \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
respuesta.write( "          <option value=\"BonoPub\">Bono pÃºblico</option>\r\n" );
respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>           \r\n" );
respuesta.write( "  \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Cantidad</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"cantidad\" name=\"cantidad\" placeholder=\"Introduzca la cantidad de valores a transar\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Precio</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"precio\" name=\"precio\" placeholder=\"Introduzca el precio por cada unidad de valor\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
respuesta.write( "</form>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "          <form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"get\">\r\n" );
respuesta.write( "      <h3>Compra por cantidad</h3>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email inversionista</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailInversionista\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "    <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo de operacion</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoOperacion\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de operacion</option>\r\n" );
respuesta.write( "          <option value=\"VENTA\">Compra</option>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>   \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
respuesta.write( "          <option value=\"BonoPub\">Bono público</option>\r\n" );
respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>           \r\n" );
respuesta.write( "  \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Cantidad</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"cantidad\" name=\"cantidad\" placeholder=\"Introduzca la cantidad de valores a transar\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
respuesta.write( "</form>\r\n" );
respuesta.write( "          <form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"get\">\r\n" );
respuesta.write( "      <h3>Compra por precio</h3>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email inversionista</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailInversionista\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "    <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo de operacion</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoOperacion\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de operacion</option>\r\n" );
respuesta.write( "          <option value=\"VENTA\">Compra</option>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>   \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
respuesta.write( "          <option value=\"BonoPub\">Bono público</option>\r\n" );
respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>           \r\n" );
respuesta.write( "  \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Precio</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"precio\" name=\"precioCompra\" placeholder=\"El precio total de la compra\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
respuesta.write( "</form>\r\n" );
respuesta.write( "         \r\n" );
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
                            
                            
                            else
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
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 1</a></li>\r\n" );
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 2</a></li>\r\n" );
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 3</a></li>\r\n" );
respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 4</a></li>\r\n" );
respuesta.write( "<li class=\"dropdown\">\r\n" );
respuesta.write( "        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">OpciÃ³n 5 <b class=\"caret\"></b></a>\r\n" );
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
respuesta.write( "            <li><a href=\"./ordenarOperacionPrim.htm\">Ordenar operaciÃ³n primaria</a></li>\r\n" );
respuesta.write( "            <li class=\"active\"><a href=\"./ordenarOperacionSec.htm\">Ordenar operaciÃ³n secundaria</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./cancelarOperacion.htm\">Cancelar operaciÃ³n bursÃ¡til</a></li>\r\n" );
respuesta.write( "            <li><a href=\"./registrarOperacionPrim.htm\">Registrar operaciÃ³n primaria</a></li>\r\n" );
respuesta.write( "             <li ><a href=\"./registrarOperacionSec.htm\">Registrar operaciÃ³n secundaria</a></li>\r\n" );
respuesta.write( "           \r\n" );
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
respuesta.write( "          <h1 class=\"page-header\">Ordenar operaciÃ³n secundaria</h1>\r\n" );
respuesta.write( "          <div class=\"panel panel-danger\">\r\n" );
respuesta.write( "            <div class=\"panel-body\">\r\n" );
respuesta.write( "              Error ordenando\r\n" );
respuesta.write( "            </div>\r\n" );
respuesta.write( "            <div class=\"panel-footer\">No tienes los valores sufuciente que quieres vender</div>\r\n" );
respuesta.write( "          </div>\r\n" );
respuesta.write( "          \r\n" );
respuesta.write( "<form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"get\">\r\n" );
respuesta.write( "      <h3>Venta</h3>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email inversionista</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailInversionista\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "    <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo de operacion</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoOperacion\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de operacion</option>\r\n" );
respuesta.write( "          <option value=\"COMPRA\" >Compra</option>\r\n" );
respuesta.write( "          <option value=\"VENTA\">Venta</option>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>   \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
respuesta.write( "          <option value=\"BonoPub\">Bono pÃºblico</option>\r\n" );
respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>           \r\n" );
respuesta.write( "  \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Cantidad</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"cantidad\" name=\"cantidad\" placeholder=\"Introduzca la cantidad de valores a transar\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Precio</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"precio\" name=\"precio\" placeholder=\"Introduzca el precio por cada unidad de valor\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
respuesta.write( "</form>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "          <form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"get\">\r\n" );
respuesta.write( "      <h3>Compra por cantidad</h3>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email inversionista</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailInversionista\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "    <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo de operacion</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoOperacion\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de operacion</option>\r\n" );
respuesta.write( "          <option value=\"VENTA\">Compra</option>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>   \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
respuesta.write( "          <option value=\"BonoPub\">Bono público</option>\r\n" );
respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>           \r\n" );
respuesta.write( "  \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Cantidad</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"cantidad\" name=\"cantidad\" placeholder=\"Introduzca la cantidad de valores a transar\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
respuesta.write( "</form>\r\n" );
respuesta.write( "          <form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"get\">\r\n" );
respuesta.write( "      <h3>Compra por precio</h3>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email inversionista</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailInversionista\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
respuesta.write( "           placeholder=\"Introduce el email\">\r\n" );
respuesta.write( "  </div>\r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "    <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo de operacion</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoOperacion\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de operacion</option>\r\n" );
respuesta.write( "          <option value=\"VENTA\">Compra</option>\r\n" );
respuesta.write( "         \r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>   \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
respuesta.write( "          <option value=\"BonoPub\">Bono público</option>\r\n" );
respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
respuesta.write( "        </select>\r\n" );
respuesta.write( "  </div>           \r\n" );
respuesta.write( "  \r\n" );
respuesta.write( "  <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "             \r\n" );
respuesta.write( "              \r\n" );
respuesta.write( "               <div class=\"form-group\">\r\n" );
respuesta.write( "    <label for=\"ejemplo_email_1\">Precio</label>\r\n" );
respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"precio\" name=\"precioCompra\" placeholder=\"El precio total de la compra\" required=\"\" autofocus=\"\">\r\n" );
respuesta.write( "  </div> \r\n" );
respuesta.write( "    \r\n" );
respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
respuesta.write( "</form>\r\n" );
respuesta.write( "         \r\n" );
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
                
		
                   catch (Exception e )
                   {
                    respuesta.write(e.getMessage());    
                   }
	    	x.close();
	    	
	        
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
                            String nombreValor = request.getParameter( "tipoValor" );
                            String nit = request.getParameter( "nit" );
                             String precio = request.getParameter( "precio" );
                            String cantidad = request.getParameter( "cantidad" );
                            String seranull=null;
                          
                            //realiza las validaciones correspondientes
                        
                            //conexion base de datos
                             conexionDB x = new conexionDB();
                            ResultSet maximoid= x.consultar("SELECT MAX (ID) FROM  OPERACIONES_EN_ESPERA_PRIM ");
                            int nuevoid=0;
                            if(maximoid.next())
                            {
                             nuevoid = Integer.parseInt(maximoid.getString("MAX(ID)"))+1 ;
                              
                            }
                            maximoid.close();
                            
                            String sqlS="INSERT INTO OPERACIONES_EN_ESPERA_PRIM VALUES("
                             +nuevoid +",'"+emailOferente + "','"+emailIntermediario+"'," +nit+",'"+nombreValor+"',"+precio+","+cantidad+",(SELECT SYSDATE FROM DUAL),"+seranull+")";
                             boolean rta = x.actualizarCrear(sqlS);
                        x.close();
                           
                           
                            if(rta)
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
                                respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 1</a></li>\r\n" );
                                respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 2</a></li>\r\n" );
                                respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 3</a></li>\r\n" );
                                respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 4</a></li>\r\n" );
                                respuesta.write( "<li class=\"dropdown\">\r\n" );
                                respuesta.write( "        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">OpciÃ³n 5 <b class=\"caret\"></b></a>\r\n" );
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
                                respuesta.write( "            <li class=\"active\"><a href=\"./ordenarOperacionPrim.htm\">Ordenar operaciÃ³n primaria</a></li>\r\n" );
                                respuesta.write( "            <li><a href=\"./ordenarOperacionSec.htm\">Ordenar operaciÃ³n secundaria</a></li>\r\n" );
                                respuesta.write( "            <li><a href=\"./cancelarOperacion.htm\">Cancelar operaciÃ³n bursÃ¡til</a></li>\r\n" );
                                respuesta.write( "            <li><a href=\"./registrarOperacionPrim.htm\">Registrar operaciÃ³n primaria</a></li>\r\n" );
                                respuesta.write( "             <li><a href=\"./registrarOperacionSec.htm\">Registrar operaciÃ³n secundaria</a></li>\r\n" );
                                respuesta.write( "           \r\n" );
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
                                respuesta.write( "            \r\n" );
                                respuesta.write( "            \r\n" );
                                respuesta.write( "            \r\n" );
                                respuesta.write( "            \r\n" );
                                respuesta.write( "            \r\n" );
                                respuesta.write( "            \r\n" );
                                respuesta.write( "          <h1 class=\"page-header\">Ordenar operaciÃ³n mercado primario</h1>\r\n" );
                                respuesta.write( "          \r\n" );
                                respuesta.write( "          \r\n" );
                                respuesta.write( "          \r\n" );
                                respuesta.write( "           <div class=\"panel panel-primary\">\r\n" );
                                respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                respuesta.write( "              Orden realizada\r\n" );
                                respuesta.write( "            </div>\r\n" );
                                respuesta.write( "            <div class=\"panel-footer\">La orden se realizÃ³ correctamente</div>\r\n" );
                                respuesta.write( "          </div>\r\n" );
                                respuesta.write( "          \r\n" );
                                respuesta.write( "          <form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"post\">\r\n" );
                                respuesta.write( "  <div class=\"form-group\">\r\n" );
                                respuesta.write( "    <label for=\"ejemplo_email_1\">Email oferente</label>\r\n" );
                                respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailOferente\"\r\n" );
                                respuesta.write( "           placeholder=\"Introduce tu email\">\r\n" );
                                respuesta.write( "  </div>\r\n" );
                                respuesta.write( "  <div class=\"form-group\">\r\n" );
                                respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
                                respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
                                respuesta.write( "           placeholder=\"Introduce tu email\">\r\n" );
                                respuesta.write( "  </div>\r\n" );
                                respuesta.write( "              \r\n" );
                                respuesta.write( "  <div class=\"form-group\">\r\n" );
                                respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
                                respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
                                respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
                                respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
                                respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
                                respuesta.write( "          <option value=\"BonoPub\">Bono pÃºblico</option>\r\n" );
                                respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
                                respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
                                respuesta.write( "        </select>\r\n" );
                                respuesta.write( "  </div>           \r\n" );
                                respuesta.write( "  \r\n" );
                                respuesta.write( "  <div class=\"form-group\">\r\n" );
                                respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
                                respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
                                respuesta.write( "  </div> \r\n" );
                                respuesta.write( "              \r\n" );
                                respuesta.write( "               <div class=\"form-group\">\r\n" );
                                respuesta.write( "    <label for=\"ejemplo_email_1\">Cantidad</label>\r\n" );
                                respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"cantidad\" name=\"cantidad\" placeholder=\"Introduzca la cantidad de valores a transar\" required=\"\" autofocus=\"\">\r\n" );
                                respuesta.write( "  </div> \r\n" );
                                respuesta.write( "              \r\n" );
                                respuesta.write( "               <div class=\"form-group\">\r\n" );
                                respuesta.write( "    <label for=\"ejemplo_email_1\">Precio</label>\r\n" );
                                respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"precio\" name=\"precio\" placeholder=\"Introduzca el precio por cada unidad de valor\" required=\"\" autofocus=\"\">\r\n" );
                                respuesta.write( "  </div> \r\n" );
                                respuesta.write( "    \r\n" );
                                respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
                                respuesta.write( "</form>\r\n" );
                                respuesta.write( "         \r\n" );
                                respuesta.write( "          \r\n" );
                                respuesta.write( "        \r\n" );
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
                            else
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
                                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 1</a></li>\r\n" );
                                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 2</a></li>\r\n" );
                                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 3</a></li>\r\n" );
                                    respuesta.write( "            <li><a href=\"http://getbootstrap.com/examples/dashboard/?#\">OpciÃ³n 4</a></li>\r\n" );
                                    respuesta.write( "<li class=\"dropdown\">\r\n" );
                                    respuesta.write( "        <a href=\"#\" class=\"dropdown-toggle\" data-toggle=\"dropdown\">OpciÃ³n 5 <b class=\"caret\"></b></a>\r\n" );
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
                                    respuesta.write( "            <li class=\"active\"><a href=\"./ordenarOperacionPrim.htm\">Ordenar operaciÃ³n primaria</a></li>\r\n" );
                                    respuesta.write( "            <li><a href=\"./ordenarOperacionSec.htm\">Ordenar operaciÃ³n secundaria</a></li>\r\n" );
                                    respuesta.write( "            <li><a href=\"./cancelarOperacion.htm\">Cancelar operaciÃ³n bursÃ¡til</a></li>\r\n" );
                                    respuesta.write( "            <li><a href=\"./registrarOperacionPrim.htm\">Registrar operaciÃ³n primaria</a></li>\r\n" );
                                    respuesta.write( "             <li><a href=\"./registrarOperacionSec.htm\">Registrar operaciÃ³n secundaria</a></li>\r\n" );
                                    respuesta.write( "           \r\n" );
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
                                    respuesta.write( "            \r\n" );
                                    respuesta.write( "            \r\n" );
                                    respuesta.write( "            \r\n" );
                                    respuesta.write( "            \r\n" );
                                    respuesta.write( "            \r\n" );
                                    respuesta.write( "            \r\n" );
                                    respuesta.write( "          <h1 class=\"page-header\">Ordenar operaciÃ³n mercado primario</h1>\r\n" );
                                    respuesta.write( "          <div class=\"panel panel-danger\">\r\n" );
                                    respuesta.write( "            <div class=\"panel-body\">\r\n" );
                                    respuesta.write( "              Error ordenando\r\n" );
                                    respuesta.write( "            </div>\r\n" );
                                    respuesta.write( "            <div class=\"panel-footer\">Los datos ingresados no son correctos</div>\r\n" );
                                    respuesta.write( "          </div>\r\n" );
                                    respuesta.write( "          <form role=\"form\" action=\"ServletOrdenarOperacionBursatil.htm\" method=\"post\">\r\n" );
                                    respuesta.write( "  <div class=\"form-group\">\r\n" );
                                    respuesta.write( "    <label for=\"ejemplo_email_1\">Email oferente</label>\r\n" );
                                    respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailOferente\" name=\"emailOferente\"\r\n" );
                                    respuesta.write( "           placeholder=\"Introduce tu email\">\r\n" );
                                    respuesta.write( "  </div>\r\n" );
                                    respuesta.write( "  <div class=\"form-group\">\r\n" );
                                    respuesta.write( "    <label for=\"ejemplo_email_1\">Email intermediario</label>\r\n" );
                                    respuesta.write( "    <input type=\"email\" class=\"form-control\" id=\"emailIntermediario\" name=\"emailIntermediario\"\r\n" );
                                    respuesta.write( "           placeholder=\"Introduce tu email\">\r\n" );
                                    respuesta.write( "  </div>\r\n" );
                                    respuesta.write( "              \r\n" );
                                    respuesta.write( "  <div class=\"form-group\">\r\n" );
                                    respuesta.write( "    <label for=\"ejemplo_email_1\">Tipo del valor</label>\r\n" );
                                    respuesta.write( "    <select class=\"form-control\" name=\"tipoValor\" >\r\n" );
                                    respuesta.write( "           <option value=\"\" disabled selected>Seleccione el tipo de valor a transar</option>\r\n" );
                                    respuesta.write( "          <option value=\"Accion\" >Accion</option>\r\n" );
                                    respuesta.write( "          <option value=\"BonoPriv\">Bono privado</option>\r\n" );
                                    respuesta.write( "          <option value=\"BonoPub\">Bono pÃºblico</option>\r\n" );
                                    respuesta.write( "          <option value=\"Certificado\">Certificado</option>\r\n" );
                                    respuesta.write( "          <option value=\"Titulo\">Titulo de participacion</option>\r\n" );
                                    respuesta.write( "        </select>\r\n" );
                                    respuesta.write( "  </div>           \r\n" );
                                    respuesta.write( "  \r\n" );
                                    respuesta.write( "  <div class=\"form-group\">\r\n" );
                                    respuesta.write( "    <label for=\"ejemplo_email_1\">Nit</label>\r\n" );
                                    respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"nit\" name=\"nit\" placeholder=\"Introduzca el nit de la emprea a la que representa\" required=\"\" autofocus=\"\">\r\n" );
                                    respuesta.write( "  </div> \r\n" );
                                    respuesta.write( "              \r\n" );
                                    respuesta.write( "               <div class=\"form-group\">\r\n" );
                                    respuesta.write( "    <label for=\"ejemplo_email_1\">Cantidad</label>\r\n" );
                                    respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"cantidad\" name=\"cantidad\" placeholder=\"Introduzca la cantidad de valores a transar\" required=\"\" autofocus=\"\">\r\n" );
                                    respuesta.write( "  </div> \r\n" );
                                    respuesta.write( "              \r\n" );
                                    respuesta.write( "               <div class=\"form-group\">\r\n" );
                                    respuesta.write( "    <label for=\"ejemplo_email_1\">Precio</label>\r\n" );
                                    respuesta.write( "       <input type=\"text\" class=\"form-control\" id=\"precio\" name=\"precio\" placeholder=\"Introduzca el precio por cada unidad de valor\" required=\"\" autofocus=\"\">\r\n" );
                                    respuesta.write( "  </div> \r\n" );
                                    respuesta.write( "    \r\n" );
                                    respuesta.write( "  <button type=\"submit\" class=\"btn btn-default\">Enviar</button>\r\n" );
                                    respuesta.write( "</form>\r\n" );
                                    respuesta.write( "         \r\n" );
                                    respuesta.write( "          \r\n" );
                                    respuesta.write( "        \r\n" );
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
                   
                   
                   
                  
                   
                   catch (Exception e )
                   {
                       respuesta.write(e.getMessage()); 
                   }
	    	
	    }
		
		    
    
}
