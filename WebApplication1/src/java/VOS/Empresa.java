/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VOS;

import java.util.ArrayList;

/**
 *
 * @author GLORIA AZUCENA
 */
public class Empresa {
   
    //ATRIBUTOS
    
    private String nombre;
    private String nit;
    private String nombreRepresentante;
    private String ccRepresentante;
    private String email ;
    private ArrayList listaTipoRentabilidad ;
    
            
            //CONSTRUCTOR
    
    
    
             public Empresa(String nombre, String nit, String nombreRepresentante,String ccRepresentante, String email) 
                {
			super();
			this.nombre = nombre;
			this.nit = nit;
			this.nombreRepresentante = nombreRepresentante;
			this.ccRepresentante = ccRepresentante;
			this.email = email;
                        listaTipoRentabilidad = new ArrayList();
                }
    
             
             //METODOS
             
    
		public String getNombre() 
                {
			return nombre;
		}
                
		public void setNombre(String nombre) 
                {
			this.nombre = nombre;
		}
                
		public String getNit() 
                {
			return nit;
		}
                
		public void setNit(String nit)
                {
			this.nit = nit;
		}
                
		public String getNombreRepresentante() 
                {
			return nombreRepresentante;
		}
                
		public void setNombreRepresentante(String nombreRepresentante) 
                {
			this.nombreRepresentante = nombreRepresentante;
		}
                
		public String getCcRepresentante() 
                {
			return ccRepresentante;
		}
                
		public void setCcRepresentante(String ccRepresentante) 
                {
			this.ccRepresentante = ccRepresentante;
		}
                
		public String getEmail()
                {
			return email;
		}
                
		public void setEmail(String email) 
                {
			this.email = email;
		}
		
    
    
    
    
    
    
    
    
}
