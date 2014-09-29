/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Fachada;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author GLORIA AZUCENA
 */
public class conexionDB {
    
    //Atributos
    
    Connection conexion;
    
    //Constructor
            public conexionDB()
            {
                conexion = conexion() ;
            }
    
            //Este metodo hace la conexion a la base de dato
            // conexion = conexion
            
            private  Connection conexion()
            {

                 try{
                Class.forName("oracle.jdbc.OracleDriver");
                String BaseDeDatos = "jdbc:oracle:thin:@prod.oracle.virtual.uniandes.edu.co:1531:prod";
                Connection conexionP= DriverManager.getConnection(BaseDeDatos,"ISIS2304071420","obtenerse50b9");
                if(conexionP!=null)
                {
                System.out.println("Conexion exitosa a esquema HR");
                return conexionP;
                }
                else{System.out.println("Conexion fallida");}
                }
                catch(Exception e)
                {e.printStackTrace();}



                return null ;
            }

            //metodo para crear o actualizar un tabla
            //me retorna true si se logro ejecutar la sentencia , false de lo contrario
            // es necesario insertar la sentancia como parametro
            
            public boolean actualizarCrear(String sql) 
            {
                    try {
                        Statement sentencia;
                        sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                        sentencia.executeUpdate(sql);
                        conexion.commit();
                        sentencia.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return false;
                    }        return true;
          }
            
            //este metodo es para realizar alguna consulta
            //es necesario enviar la sentencia sql que querio como un parametro
            
            public ResultSet consultar(String sql) 
            {
                    ResultSet resultado = null;
                    try {
                        Statement sentencia;
                        sentencia = conexion.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                        resultado = sentencia.executeQuery(sql);
                        conexion.commit();
                    } catch (SQLException e) {
                        e.printStackTrace();
                        return null;
                    }        return resultado;
             }
            
            
            public void close()
            {
        try {
            conexion.close();
        } catch (SQLException ex) {
            Logger.getLogger(conexionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
    
    
}
