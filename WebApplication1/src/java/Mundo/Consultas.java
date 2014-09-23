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
    
}
