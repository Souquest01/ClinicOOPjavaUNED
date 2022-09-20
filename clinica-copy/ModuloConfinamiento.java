import java.util.LinkedList;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;

/**
 * Esta clase se encarga de los confinamientos, utiliza una
 * clase privada NodoConfinamiento, compuesta de 3 listas
 * las cuales van pasandose las pruebas segun se confirme que el
 * paciente esta aislandose. 
 * Puede extenderse con una funcion que alerte a la policia despues
 * de tres intentos fallidos de comunicacion o con la inclusion de
 * comentarios en las pruebas/casos con lo que
 * se podria utilizar un patron decorador antes de incluir
 * la prueba en el nodo, sustituyendo estas listas de pruebas por esta
 * clase decoradora. En esta version no estan
 * implementadas estas opciones extra.
 * 
 * Este modulo tiene una lista que va recibiendo alertas y las
 * almacena en una lista para ser procesadas, la funcion que las procesa
 * comprueba que el usuario de la prueba alerta no este ya en el sistema
 * ya que los antigenos se pueden repetir sin restriccion de tiempo.
 * Si la alerta la a disparado un test serologico (error) lo descarta.
 * Al procesar satisfactoriamente una alerta la coloca en un nodo "hoy"
 * listo para ser procesado al final del dia.
 *
 * Al finalizar el dia se recorre la lista de nodos, se reinicia la
 * logica de las listas internas (procesar, no contestado y confirmado
 * y se eliminan los nodos con una antiguedad mayor de 10 dias con 
 * respeto a la fecha actual.
 * Estos nodos se almacenan en una lista de nodos para pedir analisis 
 * serologicos post confinamiento.
 * A continuacion se almacena el nodo "hoy" al principio de la lista.
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public class ModuloConfinamiento
{

            private class NodoConfinamiento implements Comparable<NodoConfinamiento>
            {
                
            LinkedList<Pruebas> procesar;
            LinkedList<Pruebas> no_contestado;
            LinkedList<Pruebas> completado;
            
            LocalDate fecha;
            
            /**
             * Constructor de clase NodoConfinamiento, imprime una
             * fecha concreta en el nodo. Esta fecha deberia de ser
             * inmutable pero por motivos de pruebas y debug se ha
             * agregado un metodo mutador.
             */
            NodoConfinamiento()
            {
              procesar = new LinkedList<>();
              no_contestado = new LinkedList<>();
              completado = new LinkedList<>();
              fecha = LocalDate.now();
              
          
            }
            
            /**
             * agrega una prueba a la lista procesar del nodo
             * @param la prueba a procesar
             */
            private void add_prueba(Pruebas x)
            {
             procesar.add(x);   
            }
            
            /**
             * metodo para funciones de debug, a eliminar en programa
             * final
             * @param listaDebugPruebas
             */
            private void add_bloquePruebas(LinkedList<Pruebas> x)
            {
              for(Pruebas y  : x)
              {
                 procesar.add(y); 
                }
            }
            
            
            /**
             * metodo que imprime la informacion de los pacientes
             * almacenados en la lista procesar.
             */
            private void imprimir_procesar()
            {
                System.out.println("XX PACIENTES POR LLAMAR XX");
                estatico.print_imp();
                 for(Pruebas x : procesar)
                 {
                    System.out.println(x.get_Paciente().toString());
                    
                    }
           
            }
            
            /**
             * metodo que imprime la informacion de los pacientes 
             * almacenados en la lista de no contestados.
             */
            private void imprimir_no_contestado()
            {
                if(no_contestado.size() > 0)
                {
                System.out.println("XX CALL BACKS XX");
                estatico.print_imp();
                 for(Pruebas x : no_contestado)
                 {
                    System.out.println(x.get_Paciente().toString());
                    
                    }
                }
            }
            
            /**
             * metodo que imprime la informacion de los pacientes
             * almacenados en la lista de completados
             */ 
        
            private void imprimir_completados()
            {
                if(completado.size() >0){
                System.out.println("XX CONTACTO CONFIRMADO XX");
                estatico.print_imp();
                 for(Pruebas x : completado)
                 {
                    System.out.println(x.get_Paciente().toString());
                    
                    }
                }
            }
            
            /**
             * metodo que imprime un resumen del dia, no tiene en cuenta que las listas
             * puedan estar vacias, esto se hace en las respectivos metodos
             */
            private void imprimir_resumen()
            {
                System.out.println("*************************************************");
                System.out.println("Dia "+ dia_Confi() + " de aislamiento  ");
                imprimir_procesar();
                imprimir_no_contestado();
                imprimir_completados();
                System.out.println();
                System.out.println("*************************************************");
            }
            
            /**
             * metodo que recorre las listas comprobando la pertenencia
             * del paciente pasado por parametro en estas, si lo encuentra
             * devuelve un true.
             * @return encontrado
             */
            private boolean contains(Paciente x)
            {
                boolean encontrado = false;
                int dniPaciente = x.get_identificadorDNI();
                 if(!procesar.isEmpty()){
                for(Pruebas y: procesar){
                 if(y.get_Paciente().get_identificadorDNI() == dniPaciente)
                 encontrado = true;
                }
                }
                
                if(!encontrado && !no_contestado.isEmpty())
                 for(Pruebas y: no_contestado){
                 if(y.get_Paciente().get_identificadorDNI() == dniPaciente)
                 encontrado = true;
                }
                if(!encontrado && !completado.isEmpty()){
                     for(Pruebas y: completado){
                 if(y.get_Paciente().get_identificadorDNI() == dniPaciente)
                 encontrado = true;
                }
                }
                return encontrado;   
            }
            
            /**
             * pasa la informacion de las otras listas a la de procesar, dejando
             * listo el nodo para el dia siguiente.
             */
            private void reiniciarDia()
            {
                while(!no_contestado.isEmpty())
                {
                 procesar.add(no_contestado.poll());   
                }
                while(!completado.isEmpty())
                {
                 procesar.add(completado.poll());   
                }
            }

            /**
             * metodo mutador de fecha, no tiene sentido en el programa principal pero
             * permite cambiar la fecha a un modulo a la fecha actual en el modo debug
             */
            private void set_fecha()
            {
                fecha = LocalDate.now();
            }
            
            private LocalDate get_fecha()
            {
             return this.fecha;   
            }
            /**
             * metodo mutador de fecha, toma como parametro una fecha concreta.
             * No tiene sentido en el programa principal pero es util en el modo debug
             * o pruebas.
             * @param fecha LocalDate
             */
            
            private void set_fecha_ajustada(LocalDate fecha)
            {
                this.fecha = fecha;
            }
            
            /**
             * devuelve la diferencia de dias del nodo con respecto a la fecha actual
             */
            private int dia_Confi()
            {
             return (int) ChronoUnit.DAYS.between(fecha, LocalDate.now());  
            }
            
            /**
             * metodo logico de la clase nodo, recorre la lista de procesar y 
             * mueve las pruebas con respecto a los datos suministrados
             */
            private void llamar_con_confirmacion()
            {
                Pruebas aux = null;
                
                 if(!procesar.isEmpty()){
                  aux = procesar.pollLast();
                }else if(!no_contestado.isEmpty()){
                    aux = no_contestado.pollLast();
                }
                
                if(aux != null)
                {
                    System.out.println("XX LLamar: "+ aux.get_Paciente().nomYapell());
                    System.out.println("¿Contacto confirmado? (S)i (N)o");
                    if(estatico.pedirConfirmacion()) completado.add(aux);
                    else no_contestado.add(aux);
                    
                }else{
                    System.out.println("Lista vacia:");
                }
                
            }
            
            
            /**
             * comprueba si la lista procesar ha sido recorrida
             */
            public boolean terminado()
            {
                 return (procesar.isEmpty() );
            }
            
            /**
             * metodo debug
             */
            private void procesarBloque()
            {
                Pruebas aux = null;
                 while(!procesar.isEmpty()){
                     completado.add(procesar.pollLast());
                    }
                    while(!no_contestado.isEmpty()){
                        completado.add(procesar.pollLast());
                    }
                    System.out.println("PROCESADOS");
            }
            
            @Override
            public int compareTo(NodoConfinamiento x)
            {
                return this.fecha.compareTo(x.get_fecha());
            }
        }
    
    LinkedList<Pruebas> alertas;
    LinkedList<NodoConfinamiento> confinamiento;
    NodoConfinamiento hoy;
    LinkedList<NodoConfinamiento> pendienteSerologico;
 
    Clinica clinica;
    
    /**
     * Constructor de la clase clinica
     */
    ModuloConfinamiento(Clinica clinica)
    {
        alertas = new LinkedList<>();
        confinamiento = new LinkedList<>();
        hoy = new NodoConfinamiento();
        pendienteSerologico = new LinkedList<>();
        
        this.clinica = clinica;
    }
    
    /**
     * agrega una alerta a la lista de alertas
     * @param prueba
     */
    public void alerta(Pruebas prueba)
    {
        alertas.add(prueba);
    }
    
    /**
     * procesa la lista de alertas
     */
    public void procesar_alertas()
    {
        for(Pruebas x : alertas)
        {
            if((x instanceof Antigenos))
            {
                if(!(this.contiene_Usuario(x.get_Paciente()))) hoy.add_prueba(x);
            }else
            {
                    hoy.add_prueba(x);
            }
        }
    alertas.clear();
    }
    
    /**
     * recorre las listas buscando al usuario pasado por parametro
     * @return encontrado si ha encontrado al usuario en las listas.
     */
    public boolean contiene_Usuario(Paciente y)
    {
         boolean encontrado = false;
         for(NodoConfinamiento x : confinamiento)
             if(x.contains(y)) encontrado = true;
             
         if(hoy.contains(y)) encontrado = true;
         
         if(!encontrado)
         for(NodoConfinamiento x : pendienteSerologico)
             if(x.contains(y)) encontrado = true;
             
         return encontrado;
        
    }
    

    /**
     * metodo para pedir analisis serologicos desde el modulo de confinamiento.
     * procesa la lista al completo
     * @param administrador de la clinica
     */
    public void pedir_Serologico(Administrador administrator)
    {
        LinkedList<Pruebas> auxiliar = null;
     for(NodoConfinamiento x: pendienteSerologico)
     {         
      for(Pruebas y: x.procesar){
          clinica.asignarTratamiento(administrator, y.get_Paciente().get_identificadorDNI(),1);
           System.out.println("analisis pedido desde modulo confinamiento");
           if(y.aceptado()){
                 if (auxiliar == null) auxiliar = new LinkedList<>();
               auxiliar.add(y);
            }
        }
        
        for(Pruebas y: auxiliar)
        {
         pendienteSerologico.remove(y);   
        }
     }
    }
    
    /**
     * metodo para finalizar el dia, agrega el nodo hoy a la lista y elimina
     * los nodos con una antiguedad superior a diez dias.
     */
    public void finalizar_dia()
    {
         if(hoy == null)
    {
        if(confinamiento.peekFirst().get_fecha().compareTo(LocalDate.now()) == 0)
            hoy = confinamiento.pollFirst();
        else if(confinamiento.peekLast().get_fecha().compareTo(LocalDate.now()) == 0)
            hoy = confinamiento.pollLast();
            else{ hoy = new NodoConfinamiento();}
    }
        
            
     confinamiento.add(hoy);
     hoy = null;
     
     if(confinamiento.size() > 0){
     for(NodoConfinamiento x : confinamiento) x.reiniciarDia();
    }
     while(confinamiento.getLast().dia_Confi() > 10){
         pendienteSerologico.add(confinamiento.removeLast());
         
        }
        
        }
    
    /**
     * metodo debug, acorta el confinamiento por un valor de dias igual al valor
     * pasado por parametro. Util para pruebas.
     */
    public void finalizar_dia(int diaPrueba)
    {
     
     confinamiento.addFirst(hoy);
     hoy = null;
     while(confinamiento.getLast().dia_Confi() > diaPrueba){
         pendienteSerologico.add(confinamiento.removeLast());
          
        }
        
        }
        
    /**
     * imprime un resumen de los dias de confinamiento
     */
    public void imprimir_resumen()
    { 
        for(NodoConfinamiento m : confinamiento)
        {
             m.imprimir_resumen();   
        }
    }

    /**
     * imprime un resumen de los nodos en la cola de finalizados de confinamiento;
     * los que esperan por un analisis serologico.
     */
    public void imprimir_serologico()
    {
         for(NodoConfinamiento m: pendienteSerologico)
         {
             m.reiniciarDia();
             m.imprimir_procesar();
            }
    }
    
    /**
     * imprime el numero de alertas almacenadas en la lista a la espera de ser 
     * procesadas
     */
    public void imprimir_numeroAlertas()
    {
     System.out.println(alertas.size() + " alertas");
    }
    
    /**
     * metodo debug, cambia la fecha del modulo hoy por el valor indicado por parametro
     * un valor negativo cambia la fecha del nodo en el pasado, un valor positivo lo
     * parametriza en el futuro.
     * @param dias 
     */
    public void set_fecha_nodo_hoy(int dias)
    {
        LocalDate aux = LocalDate.now();
        if(dias >0) aux.plusDays(dias);
        if(dias <0) aux.minusDays(dias);
        hoy.set_fecha_ajustada(aux);
    }
    /**
     * metodo debug, no tiene sentido en la implementacion final del programa pues los
     * nodos siempre se deberian de incluir en orden cronologico.
     */
    public void ordenaListaNodoConfinamiento()
    {
      Collections.sort(confinamiento);
    }

    /**
     * Carga las opciones del modulo de Confinamiento, hay un limitante a la hora de
     * acceder al mutador, como medida extra de seguridad
     * @param administrador
     */
    
    public void moduloConfinamiento(Administrador p)
    {
        if(hoy == null)
        {
            if(confinamiento.peekFirst().get_fecha().compareTo(LocalDate.now()) == 0)
                hoy = confinamiento.pollFirst();
            else if(confinamiento.peekLast().get_fecha().compareTo(LocalDate.now()) == 0)
                hoy = confinamiento.pollLast();
                else{ hoy = new NodoConfinamiento();}
        }
            
            boolean salir = false;
            
            
        
        do{
            System.out.println("  * 1 Numero de alertas    ");
            System.out.println("  * 2 Procesar alertas     ");
            System.out.println("  * 3 Imprimir resumen general ");
            System.out.println("  * 4 Imprimir pendientes de analisis serologico (confinamiento finalizado) ");
            System.out.println("  * 5 Pedir analisis serologicos  ");
            System.out.println("  * 6 Finalizar dia (reiniciar para dia siguiente)  ");
            System.out.println("  * 7 Salir\n");
            
                switch(estatico.pedirNumero(1,7))
                {
                    case 1:
                        this.imprimir_numeroAlertas();
                        break;
                    
                    case 2:
                        this.procesar_alertas();
                        break;
                    
                    case 3:
                        this.imprimir_resumen();
                        break;
                    
                    case 4:
                        this.imprimir_serologico();
                        break;
                    
                    case 5:
                        if(p instanceof Administrador)
                        this.pedir_Serologico(p); // administrador
                        break;
                    
                    case 6:
                        this.finalizar_dia();
                        break;
                    
                    case 7:
                        salir = true;
                        
                        break;
                }
   
                //finalizar_dia(diaPrueba); // int
            
            
            
        }while(!(salir));
        }
       
        /**
         * metodo debug para llenar los modulos de confinamiento.
         */
        public void crear_nodosPrueba(LinkedList<Paciente> listaPacientes, Clinica t)
        {
                PruebaRapida auxiliar;
             for( int x = 1; x <= 8; x++)
            {
             
                hoy = new NodoConfinamiento();
                set_fecha_nodo_hoy(x);
                
                for(int y = 0; y < 3; y++){
                auxiliar = new PruebaRapida();
                auxiliar.set_fecha(hoy.get_fecha());
                auxiliar.set_resultado(true);
                auxiliar.set_paciente(listaPacientes.poll());
                hoy.add_prueba(auxiliar);
                
                }
                finalizar_dia();
            }
            
        }
        
        
    }

    

 