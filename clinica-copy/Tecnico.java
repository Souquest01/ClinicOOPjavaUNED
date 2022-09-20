import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;

/***
 *  
 *  Funciones del tecnico:
 * 
 * -Visualizacion de datos de los pacientes asignados
 * 
 * -Registro y actualizacion de pruebas diagnosticas y vacunacion
 * @author (Francisco Sanchez)
 * @version (0.1)
 * 

 * 
 */
public class Tecnico extends Trabajador
{
    
    
    /**
     * constructor de la clase tecnico
     * @param DNI el dni identificador del tecnico
     * @param c clinica, la clinica donde va a trabajar el tecnico, necesario para las alertas
     */
    public Tecnico(int DNI,Clinica c)
    {
        super(DNI, c);
        nueva_listaTratamientos();
        in_planSemanal();
    }
    
    /**
     * constructor completo para las funciones de debug y pruebas. No necesario en el programa final
     */
    public Tecnico(Clinica c, int DNI, String nombre, String apellido1, String apellido2, int edad)
    {
     super(DNI, c);
     in_planSemanal();
     nueva_listaTratamientos();
     this.set_edad(edad);
     this.set_nombre(nombre);
     this.set_apellido1(apellido1);
     this.set_apellido2(apellido2);
     
    }
    
    /**
     * metodo sobreescrito para agregar tratamientos a la lista con filtro.
     * El tecnico no necesita
     * aceptar vacunas, por que lo que el primer condicional funciona de filtro
     */
    @Override
    public void add_Tratamiento(Tratamiento x)  
    {
        if(x instanceof Pruebas && x != null)
            aceptar_Tratamiento(x);
            else {
                System.out.println("El tratamiento esta vacio o no es una prueba");
            }
            
    }
    
    /**
     * metodo sobreescrito del metodo abstracto de la clase trabajador.
     * Este metodo comprueba que se pueda aceptar el tratamiento, por tipo
     * y por capacidad
     */
    @Override
    public void aceptar_Tratamiento(Tratamiento x) 
    {
     if(x instanceof Pruebas ){
         if(planSemanal_espacioLibre())
         { 
             this.agregar_planSemanal((Pruebas)x);
         }else
         {
                System.out.println("fallo en aceptar_Tratamiento // Tecnico");
         }
    }else{
        System.out.println("tipo no aceptado por el tecnico");
    }
    }
    
    /**
     * Pone al tecnico a trabajar, procesa todas las pruebas de a una.
     */
    public void TrabajarPruebas()
    {
         
      Iterator x = this.iterador_planSemanal();
      boolean encontrado = false;
      Pruebas auxiliar;
          
          while(x.hasNext())
          {
                auxiliar = (Pruebas)x.next();                 
                if(auxiliar.get_ejecutado())
                {
                 auxiliar.analizar_prueba(this);   
                 encontrado = true;   
                 if( !(auxiliar instanceof AnalisisSerologico) && auxiliar.get_resultado())
                 {
                     this.alerta_Clinica(this, auxiliar);
                    }
                }
          }
          
     
          
             if(!encontrado) System.out.println("no hay pruebas procesadas que analizar");
             if(x == null) System.out.println("Se han recorrido las pruebas");
    }
    

    /**
     * comprueba que el plan semanal tenga espacio para aceptar trabajos con respecto a los limites correspondientes a
     * la subclase
     */
    @Override
        public boolean planSemanal_espacioLibre()
    {
        return super.planSemanal_espacioLibre(estatico.ENF_LIMITE);
    }
    
    /**
     * comprueba que el plan semanal tenga espacio para aceptar trabajos con respecto a los limites correspondientes a
     * un parametro dado
     * @param x los tratamientos que puede aceptar como limite
     */
    @Override
    public boolean planSemanal_espacioLibre(int x)
    {
        return super.planSemanal_espacioLibre(x);
    }
    
    
}
