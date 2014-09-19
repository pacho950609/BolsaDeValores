/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

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
    
    
    
    
    
    
    
    
}
