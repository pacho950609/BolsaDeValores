/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package VOS;

/**
 *
 * @author GLORIA AZUCENA
 */
public class Intermediario {
    
  
  //nombre
  private String nombre ;
  //número de registro ante la Superintendencia Nacional de Valores
  private String registroSuperInte;
  //ciudad
  private String ciudad ;
  //telefono
  private String telefono ; 
  //direccion
  private String direccion ;
  //nombre del representante legal
  private String nombreRepLegal;
  //cedula del representante legal
  private String ccRepLegal;
    
    
    public Intermediario(String pNombre , String pRegistro , String pCiudad,String pTelefono,String pDireccion  ,String pnombreRep , String pccRep)
    {
        
        nombre = pNombre ;
        registroSuperInte=pRegistro ;
        ciudad=pCiudad ;
        telefono = pTelefono;
        direccion=pDireccion;
        nombreRepLegal=pnombreRep;
        ccRepLegal=pccRep;
        
        
    }
    
    
    public String getNombre() {
			return nombre;
		}


		public void setNombre(String nombre) {
			this.nombre = nombre;
		}


		public String getRegistroSuperInte() {
			return registroSuperInte;
		}


		public void setRegistroSuperInte(String registroSuperInte) {
			this.registroSuperInte = registroSuperInte;
		}


		public String getCiudad() {
			return ciudad;
		}


		public void setCiudad(String ciudad) {
			this.ciudad = ciudad;
		}


		public String getTelefono() {
			return telefono;
		}


		public void setTelefono(String telefono) {
			this.telefono = telefono;
		}


		public String getDireccion() {
			return direccion;
		}


		public void setDireccion(String direccion) {
			this.direccion = direccion;
		}


		public String getNombreRepLegal() {
			return nombreRepLegal;
		}


		public void setNombreRepLegal(String nombreRepLegal) {
			this.nombreRepLegal = nombreRepLegal;
		}


		public String getCcRepLegal() {
			return ccRepLegal;
		}


		public void setCcRepLegal(String ccRepLegal) {
			this.ccRepLegal = ccRepLegal;
		}

    
    
    
    
    
}
