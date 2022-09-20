 
import java.util.LinkedList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/***
 * Paciente es una subclase de Person, se construye con un digito dni que representa un valor unico,
 * consta de dos atributos extras con respecto a la clase persona, primeraDosis que representa la situacion
 * de que el paciente haya recibido una dosis de dos y vacunado, que representa la totalidad de la vacuacion.
 * Si se administra una vacuna de 1 sola dosis se validan los dos. No es necesaria la diferenciacion
 * 
 * 
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public class Paciente extends Person
{

    
    private boolean primeraDosis;
    private boolean vacunado;
    /***
     * Constructor de la clase paciente
     */
    public Paciente(int DNI)
    {
        super(DNI);
        nueva_listaTratamientos();
        vacunado = false;
        primeraDosis = false;
    }
    
    /***
     * Constructor de la clase paciente con atributos directos, no necesario en el
     * programa final pero util para hacer pruebas.
     */
    public Paciente( int DNI, String nombre, String apellido1, String apellido2, int edad)
    {
     super(DNI);
     nueva_listaTratamientos();
     vacunado = false;
     primeraDosis = false;
     this.set_edad(edad);
     this.set_nombre(nombre);
     this.set_apellido1(apellido1);
     this.set_apellido2(apellido2);
     
    }
    
    /***
     * Devuelve una cadena de caracteres con el nombre y apellido de la persona representada en el
     * objeto
     * 
     * @return String nombre y apellido del paciente
     */
    public String nomYapell()
    {
     return (this.get_nombre() + " " + this.get_apellido1());  
    }
    
    /***
     * Administra una dosis de vacuna al objeto paciente, altera el estado del objeto
     * vacuna y lo anhade a la cola de tratamientos si es la primera vez que se usa en
     * el paciente.
     */
    public void vacunar(Vacunas x)
    {
        if(x  instanceof JJ){
            this.primeraDosis = true;
            this.vacunado = x.usar();
            this.add_Tratamiento(x);
        }else{
            if(this.primeraDosis = false)
            {
                this.primeraDosis = x.usar();
                this.add_Tratamiento(x);
            }else
            {
                vacunado = x.usar();
            }
        }
        System.out.println("Paciente: \"auch!\" ");
    }
    
    /***
     * confirma que haya recibido su primera dosis de vacuna
     * @return primeraDosis ha recibido primera dosis
     */
    public boolean get_estadoPrimeraDosis(){
        return this.primeraDosis;
    }
    
    /***
     * confirma que haya recibido la totalidad de la vacunacion
     * @return vacunado el estado de vacunacion del usuario
     */
        public boolean get_estadoVacunacion()
    {
        return this.vacunado;
    }
    
    /***
     * Comprueba que el paciente pueda someterse a un analisis serologico
     * @return boolean
     */
    public boolean puede_AnalisisSerologico()
    {
        return puede_entrePruebas(1);
    }
    
    /***
     * Comprueba que el paciente pueda someterse a un test PCR
     * @return boolean
     */
    public boolean puede_TestPcr()
    {
        return puede_entrePruebas(2);
    }
    
    /***
     * Comprueba que el paciente pueda someterse a una vacunacion;
     * primera o segunda dosis.
     * @return boolean
     */
    public boolean puede_Vacuna()
    {
         if(!primeraDosis && !vacunado)
         { return true;}
         else{
             return puede_entrePruebas(3);
            }
    }
    
    /**
     * El metodo ejecutor de las comprobaciones para las pruebas,
     * hace uso del polimorfismo para evitar errores en tiempo de
     * ejecucion. Comprueba el tiempo habilitante entre pruebas de 
     * una variable estatica en una clase concreta, definidora de
     * parametros.
     * 
     * imprime la informacion relativa a los dias restantes para la prueba
     * en caso de que no se pueda repetir en el mismo dia
     */
    public boolean puede_entrePruebas(int seleccion)
    {
        boolean puede = true;
        LocalDate aux =  LocalDate.now();
        LinkedList<? extends Tratamiento> lista = get_tratamientos();
        int dias = 0;
        Tratamiento conmutador = null;
        switch(seleccion)
        {
         case 1:
         System.out.println("Serologico");
         System.out.println(lista.size() );
             for(Tratamiento x : lista)
            {
              if( x instanceof AnalisisSerologico)
               {
                   
                   System.out.println(x.get_fecha());
                   
                 dias =  (int)ChronoUnit.DAYS.between(x.get_fecha(), aux);
                 System.out.println("dias entre fechas" + dias);
                dias = dias - estatico.SEROLOGICO;
                System.out.println("- serologico" + dias);
                   if(dias < 7) {
                       puede = false;
                    } 
                }//else {System.out.println("No lo reconoce");}
            }
         
             
             break;
         case 2:
         System.out.println("PCR");
             for(Tratamiento x : lista)
            {
              if( x instanceof TestPcr)
               {
                 dias =  (int)ChronoUnit.DAYS.between(x.get_fecha(), aux);
                dias = dias - estatico.PCR;
                   if(dias < 7) {
                       puede = false;
                    }else{
                       puede = true;
                    }
                }
            }         
         
  
             break;
             
         case 3:
         System.out.println("vacuna");  
         for(Tratamiento x : lista)
            {
              if( x instanceof Vacunas)
               {
                 dias =  (int)ChronoUnit.DAYS.between(x.get_fecha(), aux);
                dias = dias - estatico.TIEMPOVACUNAS;
                   if(dias < 7) {
                       puede = false;
                    }else{
                       puede = true;
                    }
                }
            }    
         default:
         System.out.println("Default");
             return true;
            
 
        }
        
     return puede;
        
    }
    
    
}

