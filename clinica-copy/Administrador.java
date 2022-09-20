
/**
 * Esta clase representa al administrador de la clinica,
 * las funciones del administrador estan declaradas en la
 * clinica y esta comprueba que el usuario es un administrador
 * para permitir la manipulacion de esta.
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 * 
 * Administrador; funciones:
 * 
 * -Gestion de usuarios: altas, bajas y modificaciones de todas las personas
 * 
 * -Asignacion de pruebas diagnosticas a enfermeros y tecnicos
 *  y asignacion de vacunaciones a enfermeros
 *  
 * -Visualizacion de datos de todas las personas registradas en el sistema
 * 
 * -Visualizacion de pacientes asignados a cada enfermero/tecnico para
 * pruebas diagnosticas y vacunaciones
 * 
 * -Gestion de la programacion de pruebas serologicas tras los 
 *  confinamientos.
 *  
 * -Actualizacion del stock de vacunas.
 * 
 * -Visualizacion de la planificacion tentativa de vacunas, a partir de
 *  los pacientes registrados en un momento determinado
 * 
 * 
 * 
 */
public class Administrador extends Trabajador

{
    


    public Administrador(int DNI, Clinica c)
    {
       super(DNI, c);
        
    }
    
    public Administrador(Clinica c, int DNI, String nombre, String apellido1, String apellido2, int edad)
    {
     super(DNI, c);
     this.set_edad(edad);
     this.set_nombre(nombre);
     this.set_apellido1(apellido1);
     this.set_apellido2(apellido2);
     
    }
    
    @Override
    public void aceptar_Tratamiento(Tratamiento x) 
    {
        System.out.println("Nadie manda al administrador");
    }
    
        @Override
        public boolean planSemanal_espacioLibre()
    {
       return false;
    }
    
    @Override
    public boolean planSemanal_espacioLibre(int x)
    {
     return false;
    }
    
    @Override
    public void add_Tratamiento(Tratamiento x)  
    {
         System.out.println("Nadie manda al administrador");
    }
    

    
}
