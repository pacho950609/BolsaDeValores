/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package VOS;

import Fachada.conexionDB;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class OperacionEsperaPrim 
{
  

    public OperacionEsperaPrim obtenerPorIdSolicitud(int idp) throws Exception {

        conexionDB x = new conexionDB();
        String consulta = "SELECT * FROM OPERACIONES_EN_ESPERA_PRIM WHERE SOLICITUD="+idp;
        ResultSet r= x.consultar(consulta);
        
        
            if(r.next())
            {
                id=Integer.parseInt(r.getString("ID"));
                emailOferente=r.getString("EMAIL_OFERENTE");
                emailIntermediario=r.getString("EMAIL_INTERMEDIARIO");
                nitValor=Long.parseLong(r.getString("NIT_VALOR"));
                nomValor=r.getString("NOM_VALOR");
                fecha= r.getString("FECHA");
                try {
                    solicitud=Integer.parseInt(r.getString("SOLICITUD"));
                } catch (Exception e) {
                    solicitud= null;             
                }
                
                 try {
                   precioUnidad=Double.parseDouble(r.getString("PRECIO_UNIDAD"));
                } catch (Exception e) {
                   precioUnidad=null;     
                } 
                 
                 try {
                    cantidad=Integer.parseInt(r.getString("CANTIDAD"));
                } catch (Exception e) {
                    cantidad= null;             
                }
              }
            else 
            {
                throw new Exception("Se totea:"+consulta);
            }
        r.close();
        x.close();
        return this;
    }
    private int id;
    private String emailOferente;
    private String emailIntermediario;
    private long nitValor;
    private String nomValor;
    private Double precioUnidad;
    private Integer cantidad;
    private String fecha;
     private Integer solicitud;

       public OperacionEsperaPrim ()
            {
                
            }
    public OperacionEsperaPrim(int idp) throws Exception 
    {
        conexionDB x = new conexionDB();
        String consulta = "SELECT * FROM OPERACIONES_EN_ESPERA_PRIM WHERE ID="+idp;
        ResultSet r= x.consultar(consulta);
        
        
            if(r.next())
            {
                id=idp;
                emailOferente=r.getString("EMAIL_OFERENTE");
                emailIntermediario=r.getString("EMAIL_INTERMEDIARIO");
                nitValor=Long.parseLong(r.getString("NIT_VALOR"));
                nomValor=r.getString("NOM_VALOR");
                fecha= r.getString("FECHA");
                try {
                    solicitud=Integer.parseInt(r.getString("SOLICITUD"));
                } catch (Exception e) {
                    solicitud= null;             
                }
                
                 try {
                   precioUnidad=Double.parseDouble(r.getString("PRECIO_UNIDAD"));
                } catch (Exception e) {
                   precioUnidad=null;     
                } 
                 
                 try {
                    cantidad=Integer.parseInt(r.getString("CANTIDAD"));
                } catch (Exception e) {
                    cantidad= null;             
                }
                 

            
            }
            else 
            {
                throw new Exception("Se totea:"+consulta);
            }
        r.close();
        x.close();
        
    }

      public boolean eliminarOperacion() throws Exception 
    {
        conexionDB x = new conexionDB();
        String consulta = "DELETE FROM OPERACIONES_EN_ESPERA_PRIM WHERE ID="+id;
        boolean r= x.actualizarCrear(consulta);    
        x.close();
        return r;
        
    }

    public Integer getSolicitud() {
        return solicitud;
    }

    @Override
    public String toString() {
        return "OperacionEsperaPrim{" + "id=" + id + ", emailOferente=" + emailOferente + ", emailIntermediario=" + emailIntermediario + ", nitValor=" + nitValor + ", nomValor=" + nomValor + ", precioUnidad=" + precioUnidad + ", cantidad=" + cantidad + ", fecha=" + fecha + ", solicitud=" + solicitud + '}';
    }

   

    public OperacionEsperaPrim(int id, String emailOferente, String emailIntermediario, long nitValor, String nomValor, double precioUnidad, int cantidad, String fecha) {
        this.id = id;
        this.emailOferente = emailOferente;
        this.emailIntermediario = emailIntermediario;
        this.nitValor = nitValor;
        this.nomValor = nomValor;
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    
    
    public static boolean insertarOperacion(int id, String emailOferente, String emailIntermediario, int nitValor, String nomValor, double precioUnidad, int cantidad, Date fecha) {
        conexionDB x = new conexionDB();
         String consulta="INSERT INTO OPERACIONES_EN_ESPERA_PRIM VALUES("
                                    +id 
                                    +",'"+emailOferente
                                    +"','"+emailIntermediario
                                    + "'," +nitValor
                                    + ",'" +nomValor
                                    +"',"+precioUnidad
                                    +","+cantidad
                                    +",(SELECT SYSDATE FROM DUAL))"
                                    ;
       return x.actualizarCrear(consulta);
    }
    
     public static boolean eliminarOperacion(int idp) {
        conexionDB x = new conexionDB();
         String consulta="DELETE FROM OPERACIONES_EN_ESPERA_PRIM WHERE ID="+idp  ;
         x.close();
       return x.actualizarCrear(consulta);
    }
     public boolean setSolicitud(Integer solicitud) {
        this.solicitud = solicitud;
         conexionDB x = new conexionDB();
         String consulta="UPDATE OPERACIONES_EN_ESPERA_PRIM SET SOLICITUD ='"+solicitud+"' WHERE ID="+id ;
          boolean boo = x.actualizarCrear(consulta);
          x.close();
       return boo;
    }
      public boolean setCantidad(Integer cantidad) {
        this.solicitud = solicitud;
         conexionDB x = new conexionDB();
         String consulta="UPDATE OPERACIONES_EN_ESPERA_PRIM SET CANTIDAD ='"+cantidad+"' WHERE ID="+id ;
          boolean boo = x.actualizarCrear(consulta);
          x.close();
       return boo;
    }
    public boolean setSolicitudNull() {
        
         conexionDB x = new conexionDB();
         String consulta="UPDATE OPERACIONES_EN_ESPERA_PRIM SET SOLICITUD = NULL WHERE ID="+id ;
          boolean boo = x.actualizarCrear(consulta);
          x.close();
       return boo ;
    }
    
    public int getId() {
        return id;
    }

    public String getEmailOferente() {
        return emailOferente;
    }

    public String getEmailIntermediario() {
        return emailIntermediario;
    }

    public long getNitValor() {
        return nitValor;
    }

    public String getNomValor() {
        return nomValor;
    }

    public double getPrecioUnidad() {
        return precioUnidad;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }
    
    
    
}
