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
public class OperacionRegPrim 
{
      private int id;
    private String emailOferente;
    private String emailIntermediarioOfer;
    private String emailInver;
    private String emailIntermediarioInver;
    private long nitValor;
    private String nomValor;
    private double precioUnidad;
    private int cantidad;
    private String fecha;

    public OperacionRegPrim(int idp) 
    {
        conexionDB x = new conexionDB();
        String consulta = "SELECT * FROM OPERACIONES_REGISTRADAS_PRIM WHERE ID="+idp;
        ResultSet r= x.consultar(consulta);
        
        try {
            if(r.next())
            {
                id=idp;
                emailOferente=r.getString("EMAIL_OFER");
                emailIntermediarioOfer=r.getString("EMAIL_INTE_OFER");emailOferente=r.getString("EMAIL_OFER");
                emailIntermediarioInver=r.getString("EMAIL_INTER_INVER");
                emailInver=r.getString("EMAIL_INVER");
                nitValor=Long.parseLong(r.getString("NIT_VALOR"));
                nomValor=r.getString("NOM_VALOR");
                precioUnidad=Double.parseDouble(r.getString("PRECIO_UNIDAD"));
                cantidad=Integer.parseInt(r.getString("CANTIDAD"));
                fecha= r.getString("FECHA");
               
            
            }
            else 
            {
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionEsperaPrim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

   
    

    
    
    public static boolean insertarOperacion(String emailOferente, String emailIntermediarioOfer,
            String emailInver, String emailIntermediarioInver,
            int nitValor, String nomValor, double precioUnidad, int cantidad, Date fecha) throws SQLException {
        conexionDB x = new conexionDB();
         ResultSet maximoid= x.consultar("SELECT MAX (ID) FROM  OPERACIONES_EN_ESPERA_SEC ");
                            int nuevoid=0;
                            if(maximoid.next())
                            {
                             nuevoid = Integer.parseInt(maximoid.getString("MAX(ID)"))+1 ;
                              
                            }
                            maximoid.close();
         String consulta="INSERT INTO OPERACIONES_REGISTRADAS_PRIM VALUES("
                                    +nuevoid 
                                    +",'"+emailOferente
                                    +"','"+emailIntermediarioInver
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
         String consulta="DELETE FROM OPERACIONES_REGISTRADAS_PRIM WHERE ID="+idp  ;
       return x.actualizarCrear(consulta);
    }
    
    
    public int getId() {
        return id;
    }

    public String getEmailOferente() {
        return emailOferente;
    }

    public String getEmailIntermediarioOfer() {
        return emailIntermediarioOfer;
    }

    public String getEmailInver() {
        return emailInver;
    }

    public String getEmailIntermediarioInver() {
        return emailIntermediarioInver;
    }

    @Override
    public String toString() {
        return "OperacionRegPrim{" + "id=" + id + ", emailOferente=" + emailOferente + ", emailIntermediarioOfer=" + emailIntermediarioOfer + ", emailInver=" + emailInver + ", emailIntermediarioInver=" + emailIntermediarioInver + ", nitValor=" + nitValor + ", nomValor=" + nomValor + ", precioUnidad=" + precioUnidad + ", cantidad=" + cantidad + ", fecha=" + fecha + '}';
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
