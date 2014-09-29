/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package VOS;

import Fachada.conexionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class SolicitudCompra
{
    private int id;

    public int getId() {
        return id;
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

    public String getEmailVen() {
        return emailVen;
    }

    public String getEmailComp() {
        return emailComp;
    }

    public String getEmailIntVen() {
        return emailIntVen;
    }

    public String getEmailIntCom() {
        return emailIntCom;
    }
    private long nitValor;
    private String nomValor;
    private double precioUnidad;
    private int cantidad;
    private String fecha;
    private String emailVen;
    private String emailComp;
    private String emailIntVen;
    private String emailIntCom;

    public SolicitudCompra(int id, long nitValor, String nomValor, 
            double precioUnidad, int cantidad, String fecha, 
            String emailVen, String emailComp, String emailIntVen, String emailIntCom) {
        this.id = id;
        this.nitValor = nitValor;
        this.nomValor = nomValor;
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.emailVen = emailVen;
        this.emailComp = emailComp;
        this.emailIntVen = emailIntVen;
        this.emailIntCom = emailIntCom;
    }
    
    public static SolicitudCompra darsolicitudPrim(int id) throws SQLException
    {
        conexionDB x= new conexionDB();
         ResultSet r =  x.consultar("SELECT * \n" +
                "FROM SOLICITUDES_COMPRA_PRIM \n" +
                "WHERE \n" +
                "ID= "+id);
         SolicitudCompra s= null;
         r.next();
             s= new SolicitudCompra(Integer.parseInt(r.getString("ID")), Long.parseLong(r.getString("NIT_VALOR")), r.getString("NOM_VALOR"), 
                     Double.parseDouble(r.getString("PRECIO_UNITARIO")), Integer.parseInt(r.getString("CANTIDAD")), r.getString("FECHA"), 
                     r.getString("EMAIL_VEN"), r.getString("EMAIL_COM"), r.getString("EMAIL_INT_VEN"), r.getString("EMAIL_INT_COM"));
    r.close();
    x.close();
        return s;
    }
        
    public static SolicitudCompra darsolicitudSec(int id) throws SQLException
    {
        conexionDB x= new conexionDB();
         ResultSet r =  x.consultar("SELECT * \n" +
                "FROM SOLICITUDES_COMPRA_SEC \n" +
                "WHERE \n" +
                "ID= "+id);
         SolicitudCompra s= null;
         r.next();
             s= new SolicitudCompra(Integer.parseInt(r.getString("ID")), Long.parseLong(r.getString("NIT_VALOR")), r.getString("NOM_VALOR"), 
                     Double.parseDouble(r.getString("PRECIO_UNITARIO")), Integer.parseInt(r.getString("CANTIDAD")), r.getString("FECHA"), 
                     r.getString("EMAIL_VEN"), r.getString("EMAIL_COM"), r.getString("EMAIL_INT_VEN"), r.getString("EMAIL_INT_COM"));
    x.close();
    r.close();
    
        return s;
    }
        
    public static boolean eliminarSolicitudPrim(int idp) throws Exception 
    {
        conexionDB x = new conexionDB();
        String consulta = "DELETE FROM SOLICITUDES_COMPRA_PRIM WHERE ID="+idp;
        boolean r= x.actualizarCrear(consulta);    
        x.close();
        return r;
        
    }
    public static boolean eliminarSolicitudSec(int idp) throws Exception 
    {
        conexionDB x = new conexionDB();
        String consulta = "DELETE FROM SOLICITUDES_COMPRA_SEC WHERE ID="+idp;
        boolean r= x.actualizarCrear(consulta);    
        x.close();
        return r;
        
    }

    public static int insertarSolicitudPrimaria( String nombreValor, long nit, int cantidad, 
            double precioUnitario, String emailIntCom , String emailCom, String emailIntVen,
            String emailVen )
    {
        conexionDB x= new conexionDB();
        int nuevoid=0;
        try {
              ResultSet maximoid= x.consultar("SELECT MAX (ID) FROM SOLICITUDES_COMPRA_PRIM ");
                            
                            if(maximoid.next())
                            {
                             nuevoid = Integer.parseInt(maximoid.getString("MAX(ID)"))+1 ;
                              
                            }
                            maximoid.close();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        
        boolean res= x.actualizarCrear("INSERT INTO SOLICITUDES_COMPRA_PRIM"
         + " VALUES ("+nuevoid+", '"+nombreValor+"', "+nit+", "+cantidad+", "+precioUnitario+", '"
                +emailIntCom+"','"+emailCom+"','"+emailIntVen+"','"+emailVen+"', (SELECT SYSDATE FROM DUAL))");
        
        x.close();
        if(res)
            return nuevoid;
        else 
            return -1;
    } 
    
    public static int insertarSolicitudSecundaria( String nombreValor, long nit, int cantidad, 
            double precioUnitario, String emailIntCom , String emailCom, String emailIntVen,
            String emailVen )
    {
         conexionDB x= new conexionDB();
        int nuevoid=0;
        try {
              ResultSet maximoid= x.consultar("SELECT MAX (ID) FROM SOLICITUDES_COMPRA_SEC ");
                            
                            if(maximoid.next())
                            {
                             nuevoid = Integer.parseInt(maximoid.getString("MAX(ID)"))+1 ;
                              
                            }
                            maximoid.close();
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
        boolean res= x.actualizarCrear("INSERT INTO SOLICITUDES_COMPRA_SEC"
         + " VALUES ("+nuevoid+", '"+nombreValor+"', "+nit+", "+cantidad+", "+precioUnitario+", '"
                +emailIntCom+"','"+emailCom+"','"+emailIntVen+"','"+emailVen+"', (SELECT SYSDATE FROM DUAL))");
        
        x.close();
         if(res)
            return nuevoid;
        else 
            return -1;
    } 
   
    
}
