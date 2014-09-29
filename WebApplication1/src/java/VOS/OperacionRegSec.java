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

/**
 *
 * @author Cristian
 */
public class OperacionRegSec {
    private int id;
    private String emailInverCom;
    private String emailInterCom;
    private String emailInverVen;
    private String emailInterVen;
    private long nitValor;
    private String nomValor;
    private String tipoOperacion;
    private double precioUnidad;
    private int cantidad;
    private String fecha;
    

    public OperacionRegSec(int idp) throws Exception 
    {
        conexionDB x = new conexionDB();
        String consulta = "SELECT * FROM OPERACIONES_REGISTRADAS_SEC WHERE ID="+idp;
        ResultSet r= x.consultar(consulta);
        
        try {
            if(r.next())
            {
                id=idp;
                emailInverCom=r.getString("EMAIL_COM");
                emailInverVen=r.getString("EMAIL_VEN");
                emailInterCom=r.getString("EMAIL_INTER_COM");
                emailInterVen=r.getString("EMAIL_INTER_VEN");
                nitValor=Long.parseLong(r.getString("NIT_VALOR"));
                nomValor=r.getString("NOM_VALOR");
                precioUnidad=Double.parseDouble(r.getString("PRECIO_UNIDAD"));
                cantidad=Integer.parseInt(r.getString("CANTIDAD"));
                fecha= r.getString("FECHA");
                tipoOperacion= r.getString("TIPO_OPERACION");
            
            }
            else 
            {
                 throw new Exception("Se totea:"+ consulta);
            }
        } catch (Exception ex) {
           throw ex;
        }
        
    }

   

    
    
    public static boolean insertarOperacion(String emailInverCom, 
            String emailIntermediarioCom,String emailInverVen, 
            String emailIntermediarioVen, int nitValor, String nomValor, 
            double precioUnidad, int cantidad, Date fecha, String tipoOperacion, String precio) throws SQLException {
        conexionDB x = new conexionDB();
         ResultSet maximoid= x.consultar("SELECT MAX (ID) FROM  OPERACIONES_EN_ESPERA_SEC ");
                            int nuevoid=0;
                            if(maximoid.next())
                            {
                             nuevoid = Integer.parseInt(maximoid.getString("MAX(ID)"))+1 ;
                              
                            }
                            maximoid.close();
         String consulta="INSERT INTO OPERACIONES_REGISTRADAS_SEC VALUES("
                                    +maximoid
                                    +",'"+emailInverCom
                                    + "','" +nomValor                                    
                                    + "'," +nitValor
                                    +",'"+tipoOperacion
                                    +"',"+precioUnidad
                                    +","+cantidad
                                    +","+fecha
                                    +",'"+emailIntermediarioCom
                                    +"',"+precio
                                    ;
       return x.actualizarCrear(consulta);
    }
    
     public static boolean eliminarOperacion(int idp) {
        conexionDB x = new conexionDB();
         String consulta="DELETE FROM OPERACIONES_REGISTRADAS_SEC WHERE ID="+idp  ;
       return x.actualizarCrear(consulta);
    }
    
    
    public int getId() {
        return id;
    }

    public String getEmailInverCom() {
        return emailInverCom;
    }

    public String getEmailInterCom() {
        return emailInterCom;
    }

    public String getEmailInverVen() {
        return emailInverVen;
    }

    public String getEmailInterVen() {
        return emailInterVen;
    }

    @Override
    public String toString() {
        return "OperacionRegSec{" + "id=" + id + ", emailInverCom=" + emailInverCom + ", emailInterCom=" + emailInterCom + ", emailInverVen=" + emailInverVen + ", emailInterVen=" + emailInterVen + ", nitValor=" + nitValor + ", nomValor=" + nomValor + ", tipoOperacion=" + tipoOperacion + ", precioUnidad=" + precioUnidad + ", cantidad=" + cantidad + ", fecha=" + fecha + '}';
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
   
    
     public String getTipoOperacion() {
        return tipoOperacion;
    }
}
