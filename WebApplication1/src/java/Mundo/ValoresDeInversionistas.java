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
public class ValoresDeInversionistas 
{
    private String email;
     private int nitValor;
    private String nomValor;
    private int cantidad;
   
    public ValoresDeInversionistas(String email, int nitValor,String nomValor) 
    {
        conexionDB x = new conexionDB();
        String consulta = "SELECT * FROM VALORES_DE_INVERSIONISTAS WHERE NIT_VALOR="
                +nitValor+" AND EMAIL='"+email+"' AND NOM_VALOR='"+ nomValor+"'";
        ResultSet r= x.consultar(consulta);
        
        try {
            if(r.next())
            {
              
                email=r.getString("EMAIL");
                nitValor=Integer.parseInt(r.getString("NIT_VALOR"));
                nomValor=r.getString("NOM_VALOR");
                cantidad=Integer.parseInt(r.getString("CANTIDAD"));
              
            
            }
            else 
            {
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacionEsperaPrim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @Override
    public String toString() {
        return "ValoresDeInversionistas{" + "email=" + email + ", nitValor=" + nitValor + ", nomValor=" + nomValor + ", cantidad=" + cantidad + '}';
    }

    public ValoresDeInversionistas(int id, String email, int nitValor, String nomValor, int cantidad) {
        
        this.email = email;
        this.nitValor = nitValor;
        this.nomValor = nomValor;
         this.cantidad = cantidad;
        
    }

    
    
    public static boolean insertarValoresDeInversionistas( String email, int nitValor, String nomValor, int cantidad) {
        conexionDB x = new conexionDB();
         String consulta="INSERT INTO VALORES_DE_INVERSIONISTAS VALUES("
                                    +nitValor
                                    +",'"+nomValor
                                    +"',"+cantidad
                                    + ",'" + email
                                    +"')";
       return x.actualizarCrear(consulta);
    }
    
     public static boolean eliminarValoresDeInversionistas(String email, int nitValor,String nomValor) {
        conexionDB x = new conexionDB();
         String consulta="DELETE FROM VALORES_DE_INVERSIONISTAS WHERE NIT_VALOR="
                +nitValor+" AND EMAIL='"+email+"' AND NOM_VALOR='"+ nomValor+"'";
       return x.actualizarCrear(consulta);
    }
    
    
  
    public String getEmail() {
        return email;
    }

    public int getNitValor() {
        return nitValor;
    }

    public String getNomValor() {
        return nomValor;
    }

   

    public int getCantidad() {
        return cantidad;
    }

   
}