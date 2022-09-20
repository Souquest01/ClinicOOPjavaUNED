import java.io.Serializable;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;

/**
 * Abstract class Person - Superclase, define los atributos de un
 * objeto abstracto persona
 * 
 * Atributos principales designados en la clase:
 * Identificador
 * Nombre
 * Apellido
 * Apellido
 * Edad
 * lista de tratamientos (el significado de esta lista varia en funcion de la subclase)
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public abstract class Person implements Serializable
{
   
    private String nombre;
    private String apellido1;
    private String apellido2;
    private final int identificadorDNI;
    private int edad;
    private LinkedList<Tratamiento> tratamientos;
    
    /**
     * clase constructora, define solo el dni, que pasa a ser un atributo inmutable
     */
    Person(int DNI)
    {
        this.nombre = "";
        this.apellido1 = "";
        this.apellido2 = "";
        this.identificadorDNI = DNI;
        this.edad = 0;
         
    }
    
    /**
     * accesor
     * @return identificadorDNI
     */
    public int get_identificadorDNI()
    {
        return this.identificadorDNI;

    }
    
    /**
     * accesor
     * @return nombre
     */
    public String get_nombre()
    {
        return this.nombre;
    }
    
    /**
     * accesor
     * @return apellido1
     */
    public String get_apellido1()
    {
        return this.apellido1;
    }
    
    /**
     * accesor
     * @return apellido2
     */
    public String get_apellido2()
    {
        return this.apellido2;
    }
    
    /**
     * accesor
     * @return edad
     */
    public int get_edad()
    {
        return this.edad;
    }
    
    /**
     * mutador, asigna una nueva lista de tratamientos
     */
    public void nueva_listaTratamientos()
    {
     this.tratamientos = new LinkedList<>();
    }
    
    /**
     * mutador, asigna nombre
     * @param nombre
     */
    public void set_nombre(String nombre)
    {
        this.nombre = nombre.trim();
    }
    
    /**
     * mutador, asigna apellido
     * @param apellido
     */
    public void set_apellido1(String apellido)
    {
        this.apellido1 = apellido.trim();
    }
    
    /**
     * mutador, asigna segundo apellido
     * @param apellido
     */
    public void set_apellido2(String apellido)
    {
        this.apellido2 = apellido.trim();
    }
    
    /**
     * mutador, asigna edad
     * @param edad
     */
    public void set_edad(int edad)
    {
        this.edad = edad;
    }
    
    /**
     * accesor, sobreescribe la funcion toString
     * devuelve una cadena de los atributos de la persona
     */
    @Override
    public String toString()
    {
        String comodin = String.format("%-15s",this.nombre) + 
        String.format("%-15s", this.apellido1) + 
        String.format("%-15s", this.apellido2) + 
        String.format("%-5d", this.edad) + 
        String.format("%-8d", this.identificadorDNI);
        return comodin;
    }
    
    /**
     * funcion mutadora, pide al usuario que introduzca los
     * datos de la nueva persona creada
     */
    public void formularioNuevoRegistro()
    {
         
        do
        {

            
            System.out.println("introduzca nombre:");
            this.nombre = estatico.pedirTexto();
            
            System.out.println("introduzca primer apellido:");
            this.apellido1 = estatico.pedirTexto();
        
            System.out.println("introduzca segundo apellido:");
            this.apellido2 = estatico.pedirTexto();
            
            System.out.println("introduzca edad:");
            this.edad = estatico.pedirNumero(1,110);
            
    
            System.out.println("Informacion correcta? (S)i");
             
            
            }
        while(!estatico.pedirConfirmacion());
    }
    

    /**
     * devuelve la lista de tratamientos
     * @return tratamientos
     */
    public LinkedList<Tratamiento> get_tratamientos()
    {
        return tratamientos;
    }
    
    /**
     * elimina y devuelve un elemento del principio de la lista de tratamientos
     * @return Tratamiento
     */
    public Tratamiento poll_Tratamiento()
    {
       return tratamientos.poll();
    }
    
    /**
     * mutador, agrega un tratamiento a la lista de tratamientos
     * @param x tratamiento
     */
    public void add_Tratamiento(Tratamiento x)  
    {
        tratamientos.add(x);
    }
    
    /**
     * accesor, devuelve el valor de tamanho de la lista tratamientos
     * @return size
     */
    public int numero_tratamientos()
    {
        return this.tratamientos.size();
    }
    
    /**
     * mutador, elimina un tratamiento de la lista de tratamientos
     * @param x tratamiento
     */
    public void remove_Tratamiento(Tratamiento x)
    {
        tratamientos.remove(x);
    }
    
    
    /**
     * accesor, comprueba si el tratamiento dado como parametro esta contenido en la 
     * lista de tratamientos de la persona
     * @param x tratamiento
     * @return boolean, contiene
     */
    public boolean contains_Tratamiento(Tratamiento x)
    {
        return tratamientos.contains(x);
    }
    
    /**
     * funcion que imprime los datos almacenados en la lista de tratamientos
     */
     public void imprimir_Tratamientos()
        {
             String e = "     ";
            for(Tratamiento x : this.tratamientos)
            {
                System.out.println("XXXX" + x.get_tipoTratamiento()+ "XXXX");
                System.out.println("Fecha          Paciente     Enfermero      Tecnico      ");
                System.out.print(x.get_fecha() + e+e + x.get_Paciente().get_identificadorDNI() +e+ x.get_Enfermero().get_identificadorDNI() + e+ x.get_Tecnico().get_identificadorDNI());     
                if(!(x instanceof Vacunas)){
                    if(((Pruebas)x).get_ejecutado()) System.out.print("Ejecutado     "); else System.out.print("No ejecutado    \n");
                    if(((Pruebas)x).get_procesado()) System.out.print("Procesado     "); else System.out.print("No procesado    \n");
                    if(((Pruebas)x).get_procesado() && ((Pruebas)x).get_ejecutado()) System.out.print(((Pruebas)x).get_resultado()+ "\n\n");
                    System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n");
                }else{ 
                    if(!(x instanceof JJ)){
                        System.out.print(((Vacunas)x).get_dosisRestantes() +" dosis restantes");
                    }
                    }
            }
            
        }
}
 
