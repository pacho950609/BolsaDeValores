/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mundo;

import Conexion.conexionDB;
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
    private int id;
    private String emailOferente;
    private String emailIntermediario;
    private int nitValor;
    private String nomValor;
    private double precioUnidad;
    private int cantidad;
    private Date fecha;

    public OperacionEsperaPrim(int idp) throws Exception 
    {
        conexionDB x = new conexionDB();
        String consulta = "SELECT * FROM OPERACIONES_EN_ESPERA_PRIM WHERE ID="+idp;
        ResultSet r= x.consultar(consulta);
        
        try {
            if(r.next())
            {
                id=idp;
                emailOferente=r.getString("EMAIL_OFERENTE");
                emailIntermediario=r.getString("EMAIL_INTERMEDIARIO");
                nitValor=Integer.parseInt(r.getString("NIT_VALOR"));
                nomValor=r.getString("NOM_VALOR");
                precioUnidad=Double.parseDouble(r.getString("PRECIO_UNIDAD"));
                cantidad=Integer.parseInt(r.getString("CANTIDAD"));
                fecha= Date.valueOf(r.getString("FECHA"));
               
            
            }
            else 
            {
                throw new Exception("Se totea:"+consulta);
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionEsperaPrim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String toString() {
        return "OperacionEsperaPrim{" + "id=" + id + ", emailOferente=" + emailOferente + ", emailIntermediario=" + emailIntermediario + ", nitValor=" + nitValor + ", nomValor=" + nomValor + ", precioUnidad=" + precioUnidad + ", cantidad=" + cantidad + ", fecha=" + fecha + '}';
    }

    public OperacionEsperaPrim(int id, String emailOferente, String emailIntermediario, int nitValor, String nomValor, double precioUnidad, int cantidad, Date fecha) {
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
       return x.actualizarCrear(consulta);
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

    public int getNitValor() {
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

    public Date getFecha() {
        return fecha;
    }
    
    
    
}
