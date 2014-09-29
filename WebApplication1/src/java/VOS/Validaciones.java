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
 * @author GLORIA AZUCENA
 */

public class Validaciones {
    
    
    
    public static void validarOrdenarOperacionBursatilPrimario(String email , String nombreValorr , int cantidad , String fecha) throws Exception
    {
        
        if(email==null ||email.isEmpty())
                {
                     throw  new Exception("No puedes dejar el email en blanco");
                    
                }
        
        if(nombreValorr==null ||nombreValorr.isEmpty())
                {
                    
                     throw  new Exception("No puedes dejar el nombre del valor en blanco");
                }
        
        if(fecha==null ||fecha.isEmpty())
                {
                     throw  new Exception("No puedes dejar la fecha en blanco");
                    
                }
        
        if(cantidad<=0)
                {
                    
                     throw  new Exception("El monto no puede ser menor a 0");
                }
        
        
    }
    
    
    public static void validarOrdenarOperacionBursatilSecundario(String email , String nombreValorr , int cantidad , String fecha , String comprarVender , String empresa , int montoDinero) throws Exception
    {
        
        if(email==null ||email.isEmpty())
                {
                     throw  new Exception("No puedes dejar el email en blanco");
                    
                }
        
        if(nombreValorr==null ||nombreValorr.isEmpty())
                {
                    
                     throw  new Exception("No puedes dejar el nombre del valor en blanco");
                }
        
        if(fecha==null ||fecha.isEmpty())
                {
                     throw  new Exception("No puedes dejar la fecha en blanco");
                    
                }
        
      
        
          if(!comprarVender.equalsIgnoreCase("Comprar") && !comprarVender.equalsIgnoreCase("Vender"))
            {
                    throw  new Exception("Tienes que saber si compra ro vender");
            }
        
    
        
        
        
        
        
    }
    
    
    
    
    public static boolean validarTieneParaVender(String email , String tipoValor , String cantidad ,String nit,conexionDB x)
    {
         
         String sentencia="Select *  from valores_de_inversionistas , inversionista where inversionista.email=valores_de_inversionistas.email and  nom_valor='"+tipoValor+"'  and cantidad>="+cantidad+"  and valores_de_inversionistas.email='"+email+"' and nit_valor="+nit;
        ResultSet rta = x.consultar(sentencia);
        
        try {
            if(rta.next())
            {
               
                return true ;
            }
            else{
                return false ;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Validaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            rta.close();
        } catch (SQLException ex) {
            Logger.getLogger(Validaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return false ;
        
    }
    
    
    
}
