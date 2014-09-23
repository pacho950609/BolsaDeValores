/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Mundo;

import Conexion.conexionDB;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cristian
 */
public class SolicitudCompra
{
    private int id;
    private long nitValor;
    private String nomValor;
    private double precioUnidad;
    private int cantidad;
    private String fecha;
    private String emailVen;
    private String emailComp;
    private String emailIntVen;
    private String emailIntCom;

    public SolicitudCompra(int id, long nitValor, String nomValor, 
            double precioUnidad, int cantidad, String fecha, 
            String emailVen, String emailComp, String emailIntVen, String emailIntCom) {
        this.id = id;
        this.nitValor = nitValor;
        this.nomValor = nomValor;
        this.precioUnidad = precioUnidad;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.emailVen = emailVen;
        this.emailComp = emailComp;
        this.emailIntVen = emailIntVen;
        this.emailIntCom = emailIntCom;
    }

    
   
    
}
