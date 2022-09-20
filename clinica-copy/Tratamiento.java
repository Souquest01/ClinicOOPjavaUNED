import java.io.Serializable;
import java.time.LocalDate;

/***
 * Abstract class Tratamiento - Esta clase representa de forma general cualquier
 * procedimiento por el cual un paciente o usuario haya acudido a la clinica.
 * Agrupa todos puesto que cualquiera, ya sea vacuna o prueba necesita almacenar 
 * una informacion muy parecida, utilizando la herencia se ahorra la duplicidad
 * del codigo en gran medida. Tiene un alto grado de uso de polimorfismo y
 * de comprobaciones en tiempo de ejecucion para dirigir el curso del programa
 * 
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public abstract class Tratamiento implements Serializable
{
    private Tecnico tecnicoAsignado;
    private Enfermero enfermeroAsignado;
    private Paciente paciente;
    private LocalDate fecha;
    
    /**
     * constructor de la clase Tratamiento, esta es abstracta, aun asi dispone que cualquier
     * tratamiento que se construya deba tener la fecha del momento "estampada"
     */
    public Tratamiento()
    {
        fecha = LocalDate.now();
    }
    

    
    /**
     * devuelve una cadena con el tipo de subclase asignable al tratamiento
     * return tipo de clase
     */
    public String get_tipoTratamiento()
    {
     
        if(this instanceof JJ) return ("vacuna: JJ");
        if(this instanceof Moderna) return ("vacuna: Moderna");
        if(this instanceof Pfizer) return ("vacuna: Pfizer");
        if(this instanceof TestPcr) return ("Test PCR ");
        if(this instanceof PruebaClasica) return ("Prueba Clasica");
        if(this instanceof PruebaRapida) return("Prueba Rapida");
        if(this instanceof AnalisisSerologico) return ("Analisis Serologico");
        return ("Categoria no identificada");
    }
  
    /**
     * metodo mutador, asigna tecnico a la prueba
     */
    public void set_tecnico(Tecnico x)
    {
    this.tecnicoAsignado = x;
    }
    
    /**
     * metodo que comprueba si este tratamiento ha sido correctamente
     * asignado.
     */
    public boolean aceptado()
    {
     if(!(this instanceof Vacunas)){
         if(this.enfermeroAsignado != null && this.tecnicoAsignado !=null)
         return true;
         else return false;
         
        }else if(this.enfermeroAsignado != null) return true;
        else return false;
    }
    
    /**
     * metodo mutador, asigna enfermero a la prueba
     */
    public void set_enfermeroAsignado(Enfermero x)
    {
    this.enfermeroAsignado = x;
    }

    /**
     * metodo mutador, asigna paciente a la prueba
     */
    public void set_paciente(Paciente y)
    {
    this.paciente = y;
    }
 
    /**
     * metodo selector, devuelve paciente del tratamiento
     * @return paciente
     */
    public Paciente get_Paciente()
    {
        return this.paciente;
    }

    
    /**
     * metodo selector, devuelve el identificador del paciente del tratamiento
     * @return identificadorDNI
     */    
    public int get_dni_Paciente()
    {
       return this.paciente.get_identificadorDNI();
    }
    
    /**
     * metodo selector, devuelve enfermero del tratamiento
     * @return enfermeroAsignado
     */
    public Enfermero get_Enfermero()
    {
        return this.enfermeroAsignado;
    }
    
    /**
     * metodo selector, devuelve tecnico del tratamiento
     * @return tecnicoAsignado
     */
    public Tecnico get_Tecnico()
    {
        return this.tecnicoAsignado;
    }
    
    /**
     * metodo mutador, remueve tecnico del tratamiento
     */
    public void remove_Tecnico()
    {
        this.tecnicoAsignado = null;
    }
    
    /**
     * metodo mutador, remueve enfermero del tratamiento
     */
    public void remove_Enfermero()
    {
        this.enfermeroAsignado = null;
    }
    
    /**
     * metodo mutador, remueve paciente del tratamiento
     */
    public void remove_Paciente()
    {
        this.paciente = null;
    }
    
    /**
     * metodo que se asegura de que no queda rastro del tratamiento a lo largo del programa
     * este metodo tiene que revisarse pues contiene bugs, version actual (0.1)
     */
    public void remove_Tratamiento()
    {
        while(tecnicoAsignado != null)
        {
            if(tecnicoAsignado.contains_Tratamiento(this)){
                tecnicoAsignado.remove_Tratamiento(this);
            }else{
                tecnicoAsignado = null;
            }
        }
        while(enfermeroAsignado != null)
        {
            if(enfermeroAsignado.contains_Tratamiento(this)){
                enfermeroAsignado.remove_Tratamiento(this);
            }else{
                enfermeroAsignado = null;
            }
        }
        while(tecnicoAsignado != null)
        {
            if(tecnicoAsignado.contains_Tratamiento(this))
            {
                tecnicoAsignado.remove_Tratamiento(this);
            }else{
                tecnicoAsignado = null;
            }
        }
        while(paciente != null)
        {
            if(paciente.contains_Tratamiento(this))
            {
                tecnicoAsignado.remove_Tratamiento(this);
            }else
            {
                tecnicoAsignado = null;
            }
        }
    }
    
    /**
     * metodo mutador que asigna la fecha actual al tratamiento
     */
    public void timeStamp()
    {
    fecha = LocalDate.now();
    }
    
    /**
     * metodo selector que devuelve la fecha asignada al tratamiento
     */
    public LocalDate get_fecha()
    {
        return this.fecha;
    }
    
    /**
     * metodo mutador debug para cambiar la fecha del tratamiento
     */
    public void set_fecha(LocalDate debug)
    {
     this.fecha = debug;   
    }
    
    /**
     * funcion que calcula cuantos dias han pasado desde una fecha hasta la
     * fecha almacenada en el tratamiento.
     * Esta funcion es util para calcular los tiempos entre vacunas o tratamientos.
     */
    public boolean cuota_tiempo(int days)
    {
        LocalDate aux = null;
        return this.fecha.plusDays(days).isBefore(aux.now());
        
    }
    
    /**
     * metodo interfaz, permite la correcta utilizacion del polimorfismo
     */
    abstract public int get_lapsoDias();
    
}

