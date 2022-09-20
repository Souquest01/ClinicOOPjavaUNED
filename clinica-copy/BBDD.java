import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Set;


/***
 * La base de datos de la que se nutre la clase clinica y donde se almacena
 * toda la informacion, implementa la interfaz Serializable para poder hacer
 * una copia y mandarlo como stream de digitos binarios a un archivo fuerda del entorno
 * de bluej y a la inversa, para cargar un objeto base de datos de un archivo independiente
 * y restaurar la informacion de una ejecucion anterior.
 * No implementada esta ultima funcion en la version 0.1.
 * 
 * Utiliza una estructura HashMap que almacena una tupla de valores <Integer, Person>
 * para guardar un registro de los usuarios de la clinica, <llave, valor>.
 * 
 * Utiliza una instancia de la clase Nevera, que guarda la informacion
 * del stock de vacunas, como su nombre indica, representa la idea de una
 * nevera, donde se guardan los distintos tipos de vacunas.
 * 
 * Tambien hace uso de 4 variables int que guardan un contador de los tipos
 * de usuarios registrados en la base de datos.
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */



public class BBDD implements Serializable
{
    private HashMap<Integer,  Person> registro;
    private LinkedList<Tratamiento> fichero;
    private Nevera neverita;
    int numeroTecnicos;
    int numeroEnfermeros;
    int numeroPacientes;
    int numeroAdministradores;
    
   
    
    /***
     * Constructor for objects of class BBDD
     */
    public BBDD()
    {
        this.registro = new HashMap<Integer, Person>();
        this.fichero = new LinkedList<>();
        this.neverita = new Nevera();
        
        numeroTecnicos = 0;
        numeroEnfermeros = 0;
        numeroPacientes = 0;
        numeroAdministradores = 0;
    }

    public void add_registro(int dni, Person datosPersona)
    {
        this.registro.put(dni, datosPersona);
        if(datosPersona instanceof Tecnico) numeroTecnicos++;
        if(datosPersona instanceof Enfermero) numeroEnfermeros++;
        if(datosPersona instanceof Paciente) numeroPacientes++;
        if(datosPersona instanceof Administrador) numeroAdministradores++;
    }
    
    public int numeroTecnicos(){ return numeroTecnicos;}
    public int numeroEnfermeros(){return numeroEnfermeros;}
    public int numeroPacientes(){ return numeroPacientes;}
    public int numeroAdministradores(){ return numeroAdministradores;}
    
    
    public Person get_registro(int dni)
    {
        return this.registro.get(dni);
    }
    
    public HashMap get_archivo()
    {
        return this.registro;
    }
    
    public boolean contains_registro(int dni)
    {
        return registro.containsKey(dni);
    }
    
    public void eliminar_registro(int dni)
    {
        Person auxiliar;
        auxiliar = this.registro.get(dni);
        if(!(auxiliar instanceof Administrador) || numeroAdministradores != 1)
            {
            Tratamiento comodin;
            while(auxiliar.numero_tratamientos() > 0){
                comodin = auxiliar.poll_Tratamiento();
                comodin.remove_Tratamiento();
           //     this.fichero.remove(comodin);
            }
            
            if(auxiliar instanceof Tecnico) numeroTecnicos--;
            if(auxiliar instanceof Enfermero) numeroEnfermeros--;
            if(auxiliar instanceof Paciente) numeroPacientes--;
            if(auxiliar instanceof Administrador) numeroAdministradores--;
            
            this.registro.remove(dni);
        }else
        {
            System.out.println("No se puede eliminar al unico administrador del sistema");
        }
    }
    
    public int size_registro()
    {
        return this.registro.size();
    }
    
    public void add_Tratamiento(Tratamiento trat)
    {
        this.fichero.add(trat);
    }
    
     public LinkedList<Vacunas> get_vacunas()
    {
        if(!fichero.isEmpty())
            {
                    LinkedList<Vacunas> vacunas = new LinkedList<>();
                for(Tratamiento x: fichero)
                    {
                        if(x instanceof Vacunas)
                        {
                                vacunas.add((Vacunas)x);
                        }
                    }
                    if(!vacunas.isEmpty())
                    {
                        return vacunas;}
                    else
                    {
                        return null;
                    }
    
    
             }else
             {
                 return null;
             }
    }
     
    /**
     * metodos accesores del objeto Nevera
     */
    public int numeroVacunas()
    {
        return this.neverita.get_numeroVacunas();
    }
    
    public JJ get_vacunaJJ()
    {
        return this.neverita.get_vacunaJJ();
    }
    
    public Moderna get_vacunaModerna()
    {
        return this.neverita.get_vacunaModerna();
    }
    
    public Pfizer get_vacunaPfizer()
    {
        return this.neverita.get_vacunaPfizer();
    }
    
    public void agrega_JJ(int x)
    {
        this.neverita.recibe_JJ(x);
    }
    
    public void agrega_Pfizer(int x)
    {
        this.neverita.recibe_Pfizer(x);
    }
    
    public void agrega_Moderna(int x)
    {
        this.neverita.recibe_Moderna(x);
    }
    
    public Nevera get_Nevera()
    {
        return this.neverita;
    }
    
    public int get_numeroPfizer()
    {
        return this.neverita.get_numeroPfizer();
    }
    
    public int get_numeroModerna()
    {
        return this.neverita.get_numeroModerna();
    }
    
    public int get_numeroJJ()
    {
        return this.neverita.get_numeroJJ();
    }
    
    /**
     * Devuelve una lista con los pacientes extraidos de la lista original
     */
    
        public LinkedList<Paciente> lista_Pacientes()
    {
        Person auxiliar;
        Set<Integer> recorrido = get_archivo().keySet();
        LinkedList<Paciente> devolver = new LinkedList<>();
        
        for(int x: recorrido)
            {

                auxiliar = get_registro(x);
                if(auxiliar instanceof Paciente){
                devolver.add(((Paciente)auxiliar));
                 
            }
           
        }
         return devolver;
    }
    
    /**
     * Devuelve una lista con los tecnicos extraidos de la lista original
     */
    public LinkedList<Trabajador> lista_Tecnicos()
    {
        Person auxiliar;
        Set<Integer> recorrido = get_archivo().keySet();
        LinkedList<Trabajador> devolver = new LinkedList<>();
        
        for(int x: recorrido)
            {

                auxiliar = get_registro(x);
                if(auxiliar instanceof Tecnico){
                devolver.add(((Tecnico)auxiliar));
                 
            }
            
        
        }
        return devolver;
    }
    
    /**
     * Devuelve uan lista con los enfermeros extraidos de la lista original
     */
    public LinkedList<Trabajador> lista_Enfermero()
    {
        Person auxiliar;
        Set<Integer> recorrido = get_archivo().keySet();
        LinkedList<Trabajador> devolver = new LinkedList<>();
        
        for(int x: recorrido)
            {

                auxiliar = get_registro(x);
                if(auxiliar instanceof Enfermero){
                devolver.add(((Enfermero)auxiliar));
                 
            }
            
        
        }        
        return devolver;
    }
    
    /**
     * saca una vacuna aleatoria de la nevera
     * @return Vacunas
     */
    public Vacunas get_vacuna_Random(){
     return this.neverita.get_vacuna_Random();
    }
    
    
}


    