/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mundo;

import Conexion.conexionDB;
import java.sql.Date;
import java.sql.ResultSet;

/**
 *
 * @author Cristian
 */
public class OperacionRegSec {
    private int id;
    private String emailInversionista;
    private String emailIntermediario;
    private long nitValor;
    private String nomValor;
    private String tipoOperacion;

    @Override
    public String toString() {
        return "OperacionEsperaSec{" + "id=" + id + ", emailInversionista=" + emailInversionista + ", emailIntermediario=" + emailIntermediario + ", nitValor=" + nitValor + ", nomValor=" + nomValor + ", tipoOperacion=" + tipoOperacion + ", precioUnidad=" + precioUnidad + ", cantidad=" + cantidad + ", fecha=" + fecha + '}';
    }
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
                emailInversionista=r.getString("EMAIL_INVER");
                emailIntermediario=r.getString("EMAIL_INTER");
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

    public OperacionRegSec(int id, String emailIntermediario, 
            String emailInversionista, int nitValor, String nomValor, 
            double precioUnidad, int cantidad, String fecha, String tipoOperacion) {
        this.id = id;
        this.emailInversionista = emailInversionista;
        this.emailIntermediario = emailIntermediario;
        this.nitValor = nitValor;
        this.nomValor = nomValor;
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.tipoOperacion = tipoOperacion;
       
    }

    
    
    public static boolean insertarOperacion(int id, String emailInver, 
            String emailIntermediario, int nitValor, String nomValor, 
            double precioUnidad, int cantidad, Date fecha, String tipoOperacion, String precio) {
        conexionDB x = new conexionDB();
         String consulta="INSERT INTO OPERACIONES_REGISTRADAS_SEC VALUES("
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
         String consulta="DELETE FROM OPERACIONES_REGISTRADAS_SEC WHERE ID="+idp  ;
       return x.actualizarCrear(consulta);
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
