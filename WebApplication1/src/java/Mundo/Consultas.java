/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mundo;

import Conexion.conexionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.taglibs.standard.lang.jstl.Coercions;

/**
 *
 * @author Cristian
 */
public class Consultas {
    
    public static ArrayList consultarSolicitudesPrimasriasPorIntermediario(conexionDB x, String email) throws SQLException         
    {
        ResultSet r= x.consultar("SELECT * FROM SOLICITUDES_COMPRA_PRIM WHERE EMAIL_INT_VEN ='"+email+"'");
        ArrayList res= new ArrayList();
        
        while (r.next())
        {
           res.add(new SolicitudCompra(Integer.parseInt(r.getString("ID")),
                   Long.parseLong(r.getString("NIT_VALOR")), r.getString("NOM_VALOR"),
                   Double.parseDouble(r.getString("PRECIO_UNITARIO")), Integer.parseInt(r.getString("CANTIDAD")),
                   r.getString("FECHA"), 
                   r.getString("EMAIL_VEN"),  r.getString("EMAIL_COM"),  r.getString("EMAIL_INT_VEN"),  r.getString("EMAIL_INT_COM")));
        }
        
        return res;
    }
    
    public static ArrayList consultarSolicitudesSecundarioasPorIntermediario(conexionDB x, String email)   throws SQLException      
    {
       ResultSet r= x.consultar("SELECT * FROM SOLICITUDES_COMPRA_SEC WHERE EMAIL_INT_VEN ='"+email+"'");
        ArrayList res= new ArrayList();
        
        while (r.next())
        {
           res.add(new SolicitudCompra(Integer.parseInt(r.getString("ID")),
                   Long.parseLong(r.getString("NIT_VALOR")), r.getString("NOM_VALOR"),
                   Double.parseDouble(r.getString("PRECIO_UNITARIO")), Integer.parseInt(r.getString("CANTIDAD")),
                   r.getString("FECHA"), 
                   r.getString("EMAIL_VEN"),  r.getString("EMAIL_COM"),  r.getString("EMAIL_INT_VEN"),  r.getString("EMAIL_INT_COM")));
        }
        
        return res;
    }
    
    
    public static ArrayList consultarValoresConNitDado(conexionDB conexion , String nit) throws SQLException
    {
       
        ArrayList  lista = new ArrayList() ;
        
        String sentencia = "select * from VALORES_DE_INVERSIONISTAS where nit_valor="+nit;
      ResultSet rta =  conexion.consultar(sentencia) ;
       
       while(rta.next())
       {
          ValoresDeInversionistas nuevo = new ValoresDeInversionistas(rta.getString("EMAIL"), rta.getString("NIT_VALOR"), rta.getString("NOM_VALOR"),Integer.parseInt( rta.getString("CANTIDAD")));
           lista.add(nuevo);
       }
         rta.close();
        
        
        
        return lista ;
    }
    
    
     public static ArrayList  buscarValoresPorTipoDeVaor(String tipoDeValor , conexionDB conexion) throws SQLException
    {
        ArrayList lista = new ArrayList() ;
        
        if(tipoDeValor.equalsIgnoreCase("Accion")||tipoDeValor.equalsIgnoreCase("BonoPriv")||tipoDeValor.equalsIgnoreCase("BonoPub")||tipoDeValor.equalsIgnoreCase("Certificado")||tipoDeValor.equalsIgnoreCase("Titulo"))
        {
        
           String sentencia = "select * from VALORES_DE_INVERSIONISTAS where nom_valor='"+tipoDeValor+"'";
      ResultSet rta =  conexion.consultar(sentencia) ;
       
       while(rta.next())
       {
          ValoresDeInversionistas nuevo = new ValoresDeInversionistas(rta.getString("EMAIL"), rta.getString("NIT_VALOR"), rta.getString("NOM_VALOR"),Integer.parseInt( rta.getString("CANTIDAD")));
           lista.add(nuevo);
       }
         rta.close();
        
        }
        
        return lista ;
    }
    
     
     public static ArrayList valoresNegociados(String negociados ,conexionDB conexion ) throws SQLException
     {
         ArrayList lista = new ArrayList();
        
         if(negociados.equalsIgnoreCase("si"))
         {
                        String sentencia = "select * from OPERACIONES_EN_ESPERA_PRIM where  solicitud is not null";
                 ResultSet rta =  conexion.consultar(sentencia) ;

                  while(rta.next())
                  {
                     ValoresDeInversionistas nuevo = new ValoresDeInversionistas(rta.getString("EMAIL_OFERENTE"), rta.getString("NIT_VALOR"), rta.getString("NOM_VALOR"),Integer.parseInt( rta.getString("CANTIDAD")));
                      lista.add(nuevo);
                  }
                 rta.close();
                 
                  
                          String sentencia2 = "select * from OPERACIONES_EN_ESPERA_SEC where  solicitud is  not null and TIPO_OPERACION='VENTA'";
                ResultSet rta2 =  conexion.consultar(sentencia2) ;

                 while(rta2.next())
                 {
                    ValoresDeInversionistas nuevo = new ValoresDeInversionistas(rta2.getString("EMAIL_INVER"), rta2.getString("NIT_VALOR"), rta2.getString("NOM_VALOR"),Integer.parseInt( rta2.getString("CANTIDAD")));
                     lista.add(nuevo);
                 }
                  rta2.close();
         }
         if(negociados.equalsIgnoreCase("no"))
         {
             
                  String sentencia = "select * from OPERACIONES_EN_ESPERA_PRIM where  solicitud is  null";
                 ResultSet rta =  conexion.consultar(sentencia) ;

                  while(rta.next())
                  {
                     ValoresDeInversionistas nuevo = new ValoresDeInversionistas(rta.getString("EMAIL_OFERENTE"), rta.getString("NIT_VALOR"), rta.getString("NOM_VALOR"),Integer.parseInt( rta.getString("CANTIDAD")));
                      lista.add(nuevo);
                  }
                 rta.close();
                  
                  
                          String sentencia2 = "select * from OPERACIONES_EN_ESPERA_SEC where  solicitud is null and TIPO_OPERACION='VENTA'";
                ResultSet rta2 =  conexion.consultar(sentencia2) ;

                 while(rta2.next())
                 {
                    ValoresDeInversionistas nuevo = new ValoresDeInversionistas(rta2.getString("EMAIL_INVER"), rta2.getString("NIT_VALOR"), rta2.getString("NOM_VALOR"),Integer.parseInt( rta2.getString("CANTIDAD")));
                     lista.add(nuevo);
                 }
                  rta2.close();

             
         }
         
         
         
         
         
         
         
         return lista ;
     }
     
     
     
     public static ArrayList  valoresDadoUnOferente(String emailOferente , conexionDB conexion) throws SQLException
     {
         ArrayList lista = new ArrayList();
         
         
          String sentencia = "select * from OPERACIONES_EN_ESPERA_prim where  EMAIL_OFERENTE='"+emailOferente+"'";
      ResultSet rta =  conexion.consultar(sentencia) ;
       
       while(rta.next())
       {
          ValoresDeInversionistas nuevo = new ValoresDeInversionistas(rta.getString("EMAIL_OFERENTE"), rta.getString("NIT_VALOR"), rta.getString("NOM_VALOR"),Integer.parseInt( rta.getString("CANTIDAD")));
           lista.add(nuevo);
       }
         rta.close();
        
         
         return lista ; 
         
     }
     
     
     public static ArrayList  valoresDadoUnInversionita(String emailInv , conexionDB conexion) throws SQLException
     {
         ArrayList lista = new ArrayList();
         
         
          String sentencia = "select * from OPERACIONES_EN_ESPERA_SEC where  TIPO_OPERACION='VENTA' AND  EMAIL_INVER='"+emailInv+"'";
      ResultSet rta =  conexion.consultar(sentencia) ;
       
       while(rta.next())
       {
          ValoresDeInversionistas nuevo = new ValoresDeInversionistas(rta.getString("EMAIL_INVER"), rta.getString("NIT_VALOR"), rta.getString("NOM_VALOR"),Integer.parseInt( rta.getString("CANTIDAD")));
           lista.add(nuevo);
       }
         rta.close();
        
         
         return lista ; 
         
     }
     
     
     
      public static ArrayList valoresDadoUnIntermediario(String emailIntermediario ,conexionDB conexion ) throws SQLException
     {
         ArrayList lista = new ArrayList();
        
         
  
                        String sentencia = "select * from OPERACIONES_EN_ESPERA_PRIM where  EMAIL_INTERMEDIARIO='"+emailIntermediario+"'";
                 ResultSet rta =  conexion.consultar(sentencia) ;

                  while(rta.next())
                  {
                     ValoresDeInversionistas nuevo = new ValoresDeInversionistas(rta.getString("EMAIL_INTERMEDIARIO"), rta.getString("NIT_VALOR"), rta.getString("NOM_VALOR"),Integer.parseInt( rta.getString("CANTIDAD")));
                      lista.add(nuevo);
                  }
                 rta.close();
                 
                  
                          String sentencia2 = "select * from OPERACIONES_EN_ESPERA_SEC where  TIPO_OPERACION='VENTA' and EMAIL_INTER='"+emailIntermediario+"'";
                ResultSet rta2 =  conexion.consultar(sentencia2) ;

                 while(rta2.next())
                 {
                    ValoresDeInversionistas nuevo = new ValoresDeInversionistas(rta2.getString("EMAIL_INTER"), rta2.getString("NIT_VALOR"), rta2.getString("NOM_VALOR"),Integer.parseInt( rta2.getString("CANTIDAD")));
                     lista.add(nuevo);
                 }
                  rta2.close();
       
         
         
         
         
         
         
         
         
         return lista ;
     }
      
      
      public static  ArrayList operacionesDadoTipoDeUsuario(String tipoUsuario , conexionDB conexion) throws SQLException
      {
          ArrayList lista = new ArrayList() ;
          
          
          if(tipoUsuario.equalsIgnoreCase("Inversionita"))
          {
               String sentencia = "select  ID , EMAIL_INVER , nom_valor ,NIT_VALOR, TIPO_OPERACION, NVL(PRECIO_UNIDAD,0) , NVL(CANTIDAD,0) , FECHA , EMAIL_INTER , NVL(PRECIO,0) from OPERACIONES_EN_ESPERA_SEC";
                 ResultSet rta =  conexion.consultar(sentencia) ;

                  while(rta.next())
                  {
                     OperacionEsperaSec nuevo = new OperacionEsperaSec(0, " ", " ", Long.parseLong(rta.getString("NIT_VALOR")), rta.getString("NOM_VALOR"),Double.parseDouble( rta.getString("NVL(PRECIO_UNIDAD,0)")),Integer.parseInt( rta.getString("NVL(CANTIDAD,0)")), rta.getString("FECHA"), rta.getString("TIPO_OPERACION"),Double.parseDouble( rta.getString("NVL(PRECIO,0)")));
                      lista.add(nuevo);
                  }
                 rta.close();
          }
          
           if(tipoUsuario.equalsIgnoreCase("Oferente"))
          {
              String sentencia = "select * from OPERACIONES_EN_ESPERA_PRIM";
                 ResultSet rta =  conexion.consultar(sentencia) ;

                  while(rta.next())
                  {
                    OperacionEsperaSec nuevo = new OperacionEsperaSec(0, " ", " ", Long.parseLong(rta.getString("NIT_VALOR")), rta.getString("NOM_VALOR"),Double.parseDouble( rta.getString("PRECIO_UNIDAD")),Integer.parseInt( rta.getString("CANTIDAD")), rta.getString("FECHA"), "VENTA",Double.parseDouble("0"));
                      lista.add(nuevo);
                  }
                 rta.close();
          }
           
            if(tipoUsuario.equalsIgnoreCase("Intermediario"))
          {
             
                        String sentencia = "select  ID , EMAIL_INVER , nom_valor ,NIT_VALOR, TIPO_OPERACION, NVL(PRECIO_UNIDAD,0) , NVL(CANTIDAD,0) , FECHA , EMAIL_INTER , NVL(PRECIO,0) from OPERACIONES_EN_ESPERA_SEC";
                 ResultSet rta =  conexion.consultar(sentencia) ;

                  while(rta.next())
                  {
                     OperacionEsperaSec nuevo = new OperacionEsperaSec(0, " ", " ", Long.parseLong(rta.getString("NIT_VALOR")), rta.getString("NOM_VALOR"),Double.parseDouble( rta.getString("NVL(PRECIO_UNIDAD,0)")),Integer.parseInt( rta.getString("NVL(CANTIDAD,0)")), rta.getString("FECHA"), rta.getString("TIPO_OPERACION"),Double.parseDouble( rta.getString("NVL(PRECIO,0)")));
                      lista.add(nuevo);
                  }
                 rta.close();
                 
                  
                          String sentencia2 = "select * from OPERACIONES_EN_ESPERA_PRIM";
                ResultSet rta2 =  conexion.consultar(sentencia2) ;

                 while(rta2.next())
                 {
                    OperacionEsperaSec nuevo = new OperacionEsperaSec(0, " ", " ", Long.parseLong(rta2.getString("NIT_VALOR")), rta2.getString("NOM_VALOR"),Double.parseDouble( rta2.getString("PRECIO_UNIDAD")),Integer.parseInt( rta2.getString("CANTIDAD")), rta2.getString("FECHA"), "VENTA",Double.parseDouble("0"));
                      lista.add(nuevo);
                 }
                  rta2.close();
       
          }
          
          
          
          
          
          return lista ;
      }
     
     
     
}
