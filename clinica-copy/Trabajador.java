import java.util.LinkedList;
import java.util.Iterator;

/***
 * Abstract class Trabajador - write a description of the class here
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public abstract class Trabajador extends Person
{
  private  LinkedList<Pruebas> planSemanal; 
  private Clinica clini;
  
 Trabajador(int DNI, Clinica clinica)
 {
     super(DNI);
     clini = clinica;
     
 }

 /**
  * metodos interfaz para poder usar polimorfismo, sobreescritos en clases hijas
  */
 public abstract void aceptar_Tratamiento(Tratamiento t)  ;
 public abstract boolean planSemanal_espacioLibre();
    
 /**
  * mutador contructor de atributo planSemanal, representativo de la carga de trabajo
  * correspondiente al trabajador
  */
 public void in_planSemanal()
 {
     this.planSemanal = new LinkedList<>();
    }
    
    /**
     * imprime el plan semanal
     */
 public void imprimir_planSemanal()
 {
     this.imprimir_Lista(this.planSemanal);
    }

    /**
     * imprime los tratamientos almacenados en la lista Tratamientos
     * declarada en la clase persona. En los pacientes representa el historial
     * de procedimientos a los que se ha sometido, en los trabajadores los trabajos efectuados.
     * Otro tipo de historial.
     */
 public void imprimir_completados()
 {
     this.imprimir_Lista(this.get_tratamientos());
    }
 
    /**
     * imprime la informacion de los pacientes de los tratamientos completados.
     */
 public void imprimir_pacientes_Completados()
 {
     this.imprimir_pacientes_Tratamientos(this.get_tratamientos());
    }
 
    /**
     * imprime la informacion de los pacientes de los tratamientos del plan semanal
     */
 public void imprimir_pacientes_planSemanal()
 {
     this.imprimir_pacientes_Tratamientos(this.planSemanal);   
   }
    
   /**
    * devuelve el tamnho de la lista del plan semanal, util para compararlo
    * con los limites impuestos en la practica
    */
    public int num_casos_PS()
    {
        return this.planSemanal.size();
    }
    
    /**
     * clase general para imprimir una lista, consta de comprobadores de
     * tipo en tiempo de ejecucion
     */
 private void imprimir_Lista(LinkedList<? extends Tratamiento> p)
    {
         String e = "     ";
        for(Tratamiento x : p)
        {
            System.out.println("XXXX" + x.get_tipoTratamiento()+ "XXXX");
            System.out.println("Fecha      Paciente     Enfermero      Tecnico      ");
            System.out.println(x.get_fecha() + e + x.get_Paciente() +e+ x.get_Enfermero() + e+ x.get_Tecnico());     
            if(!(x instanceof Vacunas)){
                if(((Pruebas)x).get_ejecutado()) System.out.print("Ejecutado     "); else System.out.print("No ejecutado    ");
                if(((Pruebas)x).get_procesado()) System.out.print("Procesado     "); else System.out.print("No procesado    ");
                if(((Pruebas)x).get_procesado() && ((Pruebas)x).get_ejecutado()) System.out.print(((Pruebas)x).get_resultado());
            }else{ 
                if(!(x instanceof JJ)){
                    System.out.print(((Vacunas)x).get_dosisRestantes() +" dosis restantes");
                }
                }
        }
        
    }
 
    /**
     * clase general para imprimir la informacion de los pacientes de una lista de tratamientos
     */
 private void imprimir_pacientes_Tratamientos(LinkedList<? extends Tratamiento> p)
 {
     for(Tratamiento xa : p){
     System.out.println("XXXX" + xa.get_tipoTratamiento() + "XXXX");
     estatico.print_imp();
     System.out.println(xa.get_Paciente().toString());
    }
    }
    
    /**
     * iterador para la lista de plan semanal, permite recorrer y visualizar la
     * lista sin alterar su composicion u orden.
     */
    public Iterator iterador_planSemanal()
    {
        return planSemanal.listIterator();
    }

    /**
     * devuelve la comprobacion de si el objeto trabajador tiene
     * espacio en su lista de trabajos semanal. Comprueba si alguna creada,
     * si no la crea. Comprueba si la carga semanal es menor que el limite y
     * devuelve que hay espacio libre en caso afirmativo. En el caso de que
     * siga sin haber devuelto un valor true recorre la lista y comprueba los
     * dias que han pasado entre pruebas, si hay alguna mayor de 7 dias de antiguedad
     * taponando la lista, la elimina y la agrega a la lista de tratamientos completados.
     * @param limite de tratamientos por semana
     * @return puede aceptar otro tratamiento.
     */
 public boolean planSemanal_espacioLibre(int limite)
 {
     if(this instanceof Administrador) return false;
     Tratamiento auxiliar = null;
     if(this.planSemanal == null){
         planSemanal = new LinkedList<>(); 
         return true;
         
        }else
        {
         if(this.planSemanal.size() < limite){ return true;}
         else{ 
            
            Iterator<Pruebas> i = planSemanal.iterator();
            while(i.hasNext()){
                auxiliar = i.next();
             if(auxiliar.get_lapsoDias() > 7)
             {
                 this.add_Tratamiento(auxiliar);
                 i.remove();
                 return true;
                }
            }
            return false;
            } 
        }
 }
 
 /**
  * busca en la lista el elemento que necesita ser, o bien ejectuado por el enfermero
  * o bien procesado por el tecnico y lo devuelve. No lo elimina por que esto haria
  * que la cola menguara y permitiese al trabajador aceptar mas trabajos. Por ello es
  * requisito indispensable actualizar la cola de plan semanal y comprobar si hay
  * elementos anteriores a 7 dias antes de meter un elemento en la cola.
  *  
  */
 public Pruebas coger_prueba()
 {
     if (this.planSemanal.size() > 0)
     {
      if(this instanceof Tecnico)
          for(Pruebas x : planSemanal)
          {
             if(x.get_ejecutado() && !(x.get_procesado())) return x; 
            }
      if(this instanceof Enfermero)
      {
         for(Pruebas x: planSemanal)
         {
             if(!x.get_ejecutado()) return x;
            }
        }
        }
        return null;
 }
 
 /**
  * comprueba si la cola de trabajo semanal esta vacia
  */
 public boolean tiene_Trabajo()
 {
     if (this.planSemanal.size() > 0)
     {
      if(this instanceof Tecnico)
          for(Pruebas x : planSemanal)
          {
             if(x.get_ejecutado() && !(x.get_procesado())) return true; 
            }
      if(this instanceof Enfermero)
      {
         for(Pruebas x: planSemanal)
         {
             if(!x.get_ejecutado()) return true;
            }
        }
        }
     return false;
 } 
 
 /**
  * agrega una prueba al plan semanal
  * @param la prueba a agregar
  */
 public void agregar_planSemanal(Pruebas x)
 {
     planSemanal.add(x);
 } 
 
 /**
  * envia una senhal de alerta a la clinica, comprueba por tiempo
  * de ejecucion que tipo de trabajador esta dando la alerta y
  * elije la senhal de acuerdo a sus limitaciones.
  * alertaVacuna puede mandar 2 senhales, en esta version
  * solo esta habilitada un ti
  * 
  * @param el trabajador que da la senhal 
  * @param la prueba razon de la alarma
  * 
  * 
  * 
  */
 public void alerta_Clinica(Trabajador x, Tratamiento y)
 {
     if(x instanceof Tecnico && y instanceof Pruebas)
     this.clini.alerta((Tecnico)x,(Pruebas)y);
     else
     {
         if((x instanceof Enfermero) && (y instanceof Vacunas))
         {
         this.clini.alertaVacuna(x,y, false);
        }

    }
}
}
