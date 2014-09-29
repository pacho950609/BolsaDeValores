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
public class TipoRentabilidad {
    
    
    //ATRIBUTOS
    
    private int id ;
    private String nombreSignificante;
    private String descripcion;
    private int funcionamiento ;
    
    //CONSTANTES
    
    public final static int rentaFijaTerminoFijo=1 ;
    public final static int rentaFijaTerminoIndefinido=2 ;
    public final static int rentaVariableTerminoFijo=3 ;
    public final static int rentaVariableTerminoIndefinido=4 ;
   
    
    //CONSTRUCTOR
    
    
	public TipoRentabilidad(int id, String nombreSignificante, String descripcion,int funcionamientoP)
        {
		super();
		this.id = id;
		this.nombreSignificante = nombreSignificante;
		this.descripcion = descripcion;
                
                if(funcionamientoP==rentaFijaTerminoFijo || funcionamientoP==rentaFijaTerminoIndefinido || funcionamientoP==rentaVariableTerminoFijo||funcionamientoP==rentaVariableTerminoIndefinido)
                {
                funcionamiento=funcionamientoP;
                }
		
             
	}
        
        
        //METODOS
        
	public int getId()
        {
		return id;
	}
        
	public void setId(int id) 
        {
		this.id = id;
	}
        
	public String getNombreSignificante()
        {
		return nombreSignificante;
	}
        
	public void setNombreSignificante(String nombreSignificante) 
        {
		this.nombreSignificante = nombreSignificante;
	}
        
	public String getDescripcion() 
        {
		return descripcion;
	}
        
	public void setDescripcion(String descripcion) 
        {
		this.descripcion = descripcion;
	}
        
	public int getFuncionamiento() 
        {
		return funcionamiento;
	}
        
	
        
	
    
    
    
    
    
    
}
