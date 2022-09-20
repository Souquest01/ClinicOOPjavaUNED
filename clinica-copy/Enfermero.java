import java.util.LinkedList;

/**
 * 
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 * 
 * 
 * 
 * Funciones del enfermero:
 * 
 * -Visualizacion de datos de los pacientes asignados
 * 
 * -Registro y actualizacion de pruebas diagnosticas y vacunacion
 * 
 */
public class Enfermero extends Trabajador
{
    
    private LinkedList<Vacunas> vacunas;
    
    
    /**
     * Constructor de la clase enfermero, requiere el identificador unico
     * el la clinica como lugar de trabajo
     * @param DNI identificador
     * @param clinica lugar de trabajo
     */
    public Enfermero(int DNI, Clinica clinica)
    {
        super(DNI, clinica);
        in_planSemanal();
        nueva_listaTratamientos();
        vacunas = new LinkedList<>();
        
    }
    
    /**
     * Constructor sobrecargado para crear un enfermero con todos los datos
     * suministrado por parametro, sin uso en el programa final pero util para
     * las opciones de debug y pruebas.
     * 
     * @param clinica lugar de trabajao
     * @param DNI identificador unico
     * @param nombre nombre del enfermero
     * @param apellido1 apellido del enfermero
     * @param apellido2 segundo apellido del enfermero
     * @param edad edad del enfermero
     * 
     */
    
    public Enfermero(Clinica clinica, int DNI, String nombre, String apellido1, String apellido2, int edad)
    {
     super(DNI, clinica);
     in_planSemanal();
     nueva_listaTratamientos();
     vacunas = new LinkedList<>();
     this.set_edad(edad);
     this.set_nombre(nombre);
     this.set_apellido1(apellido1);
     this.set_apellido2(apellido2);
     
    }
    
    /**
     * Devuelve el tamanho de la lista de vacunaniones
     * @return size
     */
    public int vacunacionesRestantes()
    {
     return vacunas.size();   
    }
    
    /**
     * Implementacion de la accion de vacunar a un paciente, necesita como parametro una 
     * vacuna que no este vacia, en base a los valores de esta procede a eliminarla
     * de la lista de vacunas pendientes. En la actualidad no comprueba la coherencia
     * con el estado de vacunacion del paciente y esto podria ser una opcion valida a
     * implementar en el futuro. actual version (0.1)
     */
    
    public void vacunar(Vacunas z)
    {
        Person x = z.get_Paciente();
        if(x instanceof Paciente)
        {
            
            ((Paciente)x).vacunar(z);
            if(z.get_dosisRestantes() == 0)
            {
             this.vacunas.remove(z);
             this.add_Tratamiento(z);
             System.out.println("Inmunizado! Vacunado por completo!");
            } else{ System.out.println("Primera dosis!"); }
            
 
        }else
        {  
            System.out.println("Todavia no se puede vacunar a no pacientes");
        
        }
    }
    
    /**
     * Trabaja la lista de trabajos de vacunacion por completo, comprueba la
     * posiblidad de vacunar antes de proceder
     * @pre vacuna no este vacio, paciente pueda vacunarse
     */
    
    public void trabajarVacuna()
    {
      if(!this.vacunas.isEmpty())
      {
          for(Vacunas x : vacunas)
          {
             if(x.get_Paciente().puede_Vacuna()) this.vacunar(x);
            }
        }else{ System.out.println("No hay vacunas");}
    }
    
    /**
     * Metodo que formaliza la accion de realizar la prueba, cambia el estado
     * de la prueba a ejecutado, podria haber sido sobrecargado para aceptar
     * polimorfismo con respecto al parametro (al trabajador)
     * pero por semantica me ha parecido que era mejor mantener
     * los metodos separados, el de realizar la prueba en si y el de analizarla.
     * El metodo esta sobreescrito en cada subclase y se accede al apropiado
     * en tiempo de ejecucion.
     * 
     * version (0.1)
     * @param pruebaApaciente una prueba del enfermero
     * @param enfermero el segundo parametro se contempla en el metodo de la prueba.
     *        requiere de enfermero como parametro para poder realizar la prueba.
     */
    public void realizar_Prueba(Pruebas pruebaApaciente)
    {
        pruebaApaciente.hacer_prueba(this);
        
        
    }
    
    /**
     * metodo sobrecargado de la clase padre persona. Como el enfermero tiene una lista
     * de vacunas es necesaria la posibilidad de borrar los elementos de esta lista tambien
     * y de mandar una alerta al modulo de vacunacion para buscar la ficha en este modulo
     * y poder eliminarla acordemente. En esta version el modulo de vacunacion no esta
     * completamente terminado.
     * version (0.1)
     * @param
     */
    @Override
    public void remove_Tratamiento(Tratamiento x)
    {
     super.remove_Tratamiento(x);
     if(x instanceof Vacunas){
         vacunas.remove(x);
         this.alerta_Clinica(this,x);
        }
    }
    
    /**
     * metodo que hace trabajar al enfermero en su lista de trabajos semanal
     */
    public void trabajarPrueba()
    {
       if(this.tiene_Trabajo())
       {
        Pruebas auxiliar = this.coger_prueba();
        if(auxiliar !=null){
        this.realizar_Prueba(auxiliar);
        
        }else {System.out.println("No se ha devuelto un trabajo aunque la funion tiene trabajo"
                + "ha devuelto un positivo");
            }
        } else
       {
            System.out.println("No hay pruebas que ejecutar");
        
       }
    }
    
    /**
     * Metodo sobrecargado de la clase padre Trabajador.
     * El enfermero tiene dos colas de tratamientos, una para las vacunas y otra
     * para el plan semanal. Este metodo deberia de lanzar una excepcion que pueda
     * ser manejada por la funcion llamante en caso de que el enfermero rechaze la orden
     * En la version actual esta funcion no esta implementada. Deberia de hacerse las pertinentes
     * comprobaciones antes de hacer uso de esta funcion.
     * @throw noImplementado
     * @param x tratamiento a aceptar.
     */
    @Override
    public void add_Tratamiento(Tratamiento x)  
    {
            if(x != null)
            {
                aceptar_Tratamiento(x);
            }else{
                System.out.println("No hay un tratamiento creado");
            }
    }
    
    /**
     * metodo sobreescrito de la funcion padre, aplica las restricciones
     * pertinentes a la clase enfermero, valor almacenado en clase estatico
     */
    
    @Override
    public boolean planSemanal_espacioLibre()
    {
       return  super.planSemanal_espacioLibre(estatico.ENF_LIMITE);
    }
    
    /**
     * metodo sobreescrito, aplica una restriccion diferente a los limites de trabajo
     */
    @Override
    public boolean planSemanal_espacioLibre(int x)
    {
      return  super.planSemanal_espacioLibre(x);
    }
    
    /**
     * metodo sobreescrito de la clase padre, hace comprobaciones extra y redirige
     * el tratamiento a la lista adecuada
     */
    @Override
    public void aceptar_Tratamiento(Tratamiento x)
    {
         if(x instanceof Pruebas){
        if(planSemanal_espacioLibre() )
         { 
             this.agregar_planSemanal((Pruebas)x);
         }else{
             System.out.println("No hay espacio libre para pruebas en el plan de este enfermero");
            }
        }else{
            vacunas.add((Vacunas)x);
        }
    }
}