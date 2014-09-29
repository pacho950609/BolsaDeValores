/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package VOS;

import Fachada.conexionDB;
import com.sun.faces.taglib.html_basic.SelectOneListboxTag;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Cristian
 */
public class OperacionEsperaSec 
{
     private int id;
    private String emailInversionista;
    private String emailIntermediario;
    private long nitValor;
    private String nomValor;
    private String tipoOperacion;

    @Override
    public String toString() {
        return "OperacionEsperaSec{" + "id=" + id + ", emailInversionista=" + emailInversionista + ", emailIntermediario=" + emailIntermediario + ", nitValor=" + nitValor + ", nomValor=" + nomValor + ", tipoOperacion=" + tipoOperacion + ", precioUnidad=" + precioUnidad + ", cantidad=" + cantidad + ", fecha=" + fecha + ", precio=" + precio + '}';
    }
    private double precioUnidad;
    private Integer cantidad;
    private String fecha;
     private Double precio;
     private String solicitud;

    public OperacionEsperaSec(int idp) 
    {
        conexionDB x = new conexionDB();
        String consulta = "SELECT * FROM OPERACIONES_EN_ESPERA_SEC WHERE ID="+idp;
        ResultSet r= x.consultar(consulta);
        
        try {
            if(r.next())
            {
                id=idp;
                emailInversionista=r.getString("EMAIL_INVER");
                emailIntermediario=r.getString("EMAIL_INTER");
                nitValor=Long.parseLong(r.getString("NIT_VALOR"));
                nomValor=r.getString("NOM_VALOR");
                precioUnidad=Double.parseDouble(r.getString("PRECIO_UNIDAD"));
                try {
                    cantidad=Integer.parseInt(r.getString("CANTIDAD"));
                } catch (Exception e) {
                    cantidad=null;
                }
                    
                fecha= r.getString("FECHA");
                 try {
                     precio=Double.parseDouble(r.getString("PRECIO"));
                } catch (Exception e) {
                    precio=null;
                }
                try {
                    solicitud=r.getString("SOLICITUD");
                } catch (Exception e) {
                    solicitud= null;
                }

               tipoOperacion= r.getString("TIPO_OPERACION");
            r.close();
            }
            else 
            {
                 throw new Exception("Se totea:"+ consulta);
            }
        } catch (Exception ex) {
           
        }
        
    }
    public OperacionEsperaSec(){}
    public OperacionEsperaSec obtenerPorIdSolicitud(int idp) throws SQLException 
    {
        conexionDB x = new conexionDB();
        String consulta = "SELECT * FROM OPERACIONES_EN_ESPERA_SEC WHERE SOLICITUD="+idp;
        ResultSet r= x.consultar(consulta);
        
        
            if(r.next())
            {
                id=idp;
                emailInversionista=r.getString("EMAIL_INVER");
                emailIntermediario=r.getString("EMAIL_INTER");
                nitValor=Long.parseLong(r.getString("NIT_VALOR"));
                nomValor=r.getString("NOM_VALOR");
                precioUnidad=Double.parseDouble(r.getString("PRECIO_UNIDAD"));
                try {
                    cantidad=Integer.parseInt(r.getString("CANTIDAD"));
                } catch (Exception e) {
                    cantidad=null;
                }
                    
                fecha= r.getString("FECHA");
                 try {
                     precio=Double.parseDouble(r.getString("PRECIO"));
                } catch (Exception e) {
                    precio=null;
                }
                try {
                    solicitud=r.getString("SOLICITUD");
                } catch (Exception e) {
                    solicitud= null;
                }

               tipoOperacion= r.getString("TIPO_OPERACION");
            r.close();
            }
            
        
        return this;
    }

     public boolean setCantidad(Integer cantidad) {
        this.solicitud = solicitud;
         conexionDB x = new conexionDB();
         String consulta="UPDATE OPERACIONES_EN_ESPERA_SEC SET CANTIDAD ='"+cantidad+"' WHERE ID="+id ;
          boolean boo = x.actualizarCrear(consulta);
          x.close();
       return boo;
    }
    public String getSolicitud() {
        return solicitud;
    }
     public boolean setSolicitudNull() {
        
         conexionDB x = new conexionDB();
         String consulta="UPDATE OPERACIONES_EN_ESPERA_SEC SET SOLICITUD = NULL WHERE ID="+id ;
          boolean boo = x.actualizarCrear(consulta);
          x.close();
       return boo ;
    }
     public boolean eliminarOperacion() throws Exception 
    {
        conexionDB x = new conexionDB();
        String consulta = "DELETE FROM OPERACIONES_EN_ESPERA_SEC WHERE ID="+id;
        boolean r= x.actualizarCrear(consulta);    
        x.close();
        return r;
        
    }
    

    public OperacionEsperaSec(int id, String emailIntermediario, 
            String emailInversionista, long nitValor, String nomValor, 
            double precioUnidad, int cantidad, String fecha, String tipoOperacion, Double precio) {
        this.id = id;
        this.emailInversionista = emailInversionista;
        this.emailIntermediario = emailIntermediario;
        this.nitValor = nitValor;
        this.nomValor = nomValor;
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.tipoOperacion = tipoOperacion;
        this.precio = precio;
    }

    
    
    public static boolean insertarOperacion(int id, String emailInver, 
            String emailIntermediario, int nitValor, String nomValor, 
            double precioUnidad, int cantidad, Date fecha, String tipoOperacion, String precio) {
        conexionDB x = new conexionDB();
         String consulta="INSERT INTO OPERACIONES_EN_ESPERA_SEC VALUES("
                                    +id 
                                    +",'"+emailInver
                                    + "','" +nomValor                                    
                                    + "'," +nitValor
                                    +",'"+tipoOperacion
                                    +"',"+precioUnidad
                                    +","+cantidad
                                    +","+fecha
                                    +",'"+emailIntermediario
                                    +"',"+precio
                                    ;
       return x.actualizarCrear(consulta);
    }
    
     public static boolean eliminarOperacion(int idp) {
        conexionDB x = new conexionDB();
         String consulta="DELETE FROM OPERACIONES_EN_ESPERA_SEC WHERE ID="+idp  ;
        
         boolean boo = x.actualizarCrear(consulta);
          x.close();
       return boo ;
    }
    
    
    public int getId() {
        return id;
    }

    public String getEmailInversionista() {
        return emailInversionista;
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

    public boolean setSolicitud(String solicitud) {
        this.solicitud = solicitud;
         conexionDB x = new conexionDB();
         String consulta="UPDATE OPERACIONES_EN_ESPERA_SEC SET SOLICITUD ='"+solicitud+"' WHERE ID="+id ;
          boolean boo = x.actualizarCrear(consulta);
          x.close();
       return boo;
    }
    public boolean setSolicitudNull(String solicitud) {
        this.solicitud = solicitud;
         conexionDB x = new conexionDB();
         String consulta="UPDATE OPERACIONES_EN_ESPERA_SEC SET SOLICITUD = NULL WHERE ID="+id ;
          boolean boo = x.actualizarCrear(consulta);
          x.close();
       return boo ;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public String getFecha() {
        return fecha;
    }
    public Double getPrecio() {
        return precio;
    }
    
     public String getTipoOperacion() {
        return tipoOperacion;
    }
    
    
}
