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
public class TipoDeValor {
    
    
        //ATRIBUTOS
    
        private int identificador ;
        private String nombreSignificante ;
        private String descripcion ;

    
        //CONSTRUCTOR
    
        public TipoDeValor(int identificador, String nombreSignificante, String descripcion)
            {
                    super();
                    this.identificador = identificador;
                    this.nombreSignificante = nombreSignificante;
                    this.descripcion = descripcion;
            }
		
        
        //METODOS
        
            public int getIdentificador() 
            {
                return identificador;
            }


            public void setIdentificador(int identificador) 
            {
                    this.identificador = identificador;
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


            public static void validarDatos(int identificadorP, String nombreSignificanteP, String descripcionP) throws Exception
            {
                if(  nombreSignificanteP==null ||nombreSignificanteP.isEmpty())
                {
                    throw  new Exception("El nombre signoficante no puede estar vacio");
                }
                
                if(descripcionP==null || descripcionP.isEmpty() )
                {
                    throw  new Exception("la Descripcion no puede estar vacia");
                }
                if( identificadorP<0 || identificadorP>4 )
                {
                     throw  new Exception("Seleciona un tipo de valor");
                }
                
               
            }
            




    
}
