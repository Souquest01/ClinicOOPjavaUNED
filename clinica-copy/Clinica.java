import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.LinkedList;

/***
 * Clase principial del programa, desde aqui se administra y se 
 * consultan los estados de este.
 *
 * @author (Francisco)
 * @version (0.1)
 */
public class Clinica
{
    private BBDD baseDatos;
    private Person usuario;
    private ModuloConfinamiento confinamiento;
    int umbral;
    
    /***
     * Constructor for objects of class Clinica
     */
    public Clinica()
    {
        baseDatos = null;
        usuario = null;
        confinamiento = new ModuloConfinamiento(this);
        umbral = 1;
    }

    /**
     * Metodo principal de la clase, todas las funciones se controlan desde aqui
     */

    public void iniciar()
    {

        
            if(baseDatos == null)
            {
                System.out.println("Quiere cargar una base de datos(1), \n    crear una nueva?(2)");
                switch(estatico.pedirNumero(1,2))
                {
                    case 1:
                        System.out.println("Modulo a implementar en version 0.2 implementando serializable");
                        
                    case 2:
                        cargaBBDD();
                        break;

                
                }
            
            

            }
            
            cargaUsuario();
            menuUsuarios();            
    }
    
     /**
      * Por implementar, carga base de datos desde objeto serializado en archivo
      */
    private void cargaBBDD()
    {
        
        System.out.println("No hay una base de datos cargada, creando...");
        baseDatos = new BBDD();
        
        
    }
    
    
    /**
     * Controlador de la clinica, carga el usuario y el menu correspondiente a su categoria
     */
    private void cargaUsuario()
    {
        
        String entrada;
        int entradaInt;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean error = false;
        
         
            
            
            if(baseDatos.size_registro() == 0)
            {
                System.out.println("Creando administrador;\n Introduzca DNI de administrador");
                entradaInt = estatico.pedirNumero();
                Administrador administrator = new Administrador(entradaInt, this);
                administrator.formularioNuevoRegistro();
                baseDatos.add_registro(entradaInt, administrator);
                usuario = administrator;
            }else
            {
                System.out.println("Por favor, introduzca su DNI");
                entradaInt = estatico.pedirNumero();
                    if(baseDatos.contains_registro(entradaInt))
                {
                    usuario = baseDatos.get_registro(entradaInt);
                } else{
                    System.out.println(" no se ha encontrado el usuario, por favor"
                    +                "\n contacte con su administrador.");
                }
            }
    }
    
    /**
     * template de menus
     */
    private void menuUsuarios()
    {
            if(usuario instanceof Administrador)
            {
               menuAdministrador((Administrador)this.usuario);
            }else{
            if(usuario instanceof Trabajador)
            {
               menuTrabajador((Trabajador)this.usuario);
            }else{
            if(usuario instanceof Paciente)
            {
                 System.out.println("Usted es un paciente de esta clinica, si necesita ayuda");
                 System.out.println("por favor, contacte con un trabajador");
            }}
            if(usuario == null)
            {
                System.out.println("no se ha encontrado el usuario");
            }
    }    
}
   
     /**
     * Menu especifico de administrador
     */
private void menuAdministrador(Administrador x)
    {
        boolean salir = false;
        int entrada;
        
        do{
        System.out.println("       Bienvenido Administrador ");
        System.out.println("seleccione la operacion que desea ejecutar:");
        System.out.println("     * 1 Gestion de usuarios         ");
        System.out.println("     * 2 Asignacion de tratamientos (pruebas y vacunas)");
        System.out.println("     * 3 Visualizacion de datos ");
        System.out.println("     * 4 Modulo Confinamiento");
        System.out.println("     * 5 Actualizacion del stock de vacunas");
        System.out.println("     * 6 Visualizacion planificacion de vacunas");
        System.out.println("     * 7 Cerrar sesion");
        
         
        entrada = estatico.pedirNumero(1,8);
        
            switch(entrada){
                case 0:
                    menuDebug();
                    break;
                case 1:
                    gestionUsuarios((Administrador)usuario);
                    break;
                case 2:
                    if(baseDatos.numeroTecnicos() >1 && baseDatos.numeroEnfermeros()>1)
                    asignarTratamiento((Administrador)usuario);
                    else{System.out.println("Necesitas mas de 1 trabajador de cada tipo");}
                    break;
                case 3:
                    visualizarDatos((Administrador)usuario);
                    break;
                case 4:
                    confinamiento.moduloConfinamiento((Administrador)usuario);
                    break;

                case 5:
                    actualizar_stockVacunas((Administrador)usuario);
                    break;
                case 6:
                    visualizarVacunas((Administrador)usuario);
                    break;
                case 7:
                    System.out.println("cerrando session...");
                    salir = true;
                    usuario = null;
                    break;
                case 8:
                    menuDebug();
                    break;
                }
        }while(!salir);
        }
       
    /**
     * metodo publico para acceder al modulo de confinamiento, una vez mas una opcion para debug.
     * Facilita el poder ver el metodo desde el banco de objetos de bluej. 
     */
        public void moduloConfinamiento()
        {
         Administrador ejemplo = null;
         confinamiento.moduloConfinamiento(ejemplo);
        }
    
        public void menuDebug()
    {
        System.out.println("MENU DEBUG/PRUEBAS");        
        System.out.println(" 1 CARGAR 100 USUARIOS DE PRUEBA  "); 
        System.out.println(" 2 CARGAR 5 PACIENTES, 5 ENFERMEROS Y 5 TECNICOS Y ASIGNAR Y PROCESAR PRUEBAS PCR ");
        System.out.println(" 3 CARGAR 11 NODOS EN MODULO CONFINAMIENTO !! ATENCION, SI SE EJECUTA DESPUES DE MODO 2");
        System.out.println("   PUEDE QUE SE ABSORBAN LAS ALERTAS PROPIAS DEL 2 Y NECESITA PACIENTES EN LA BASE DE DATOS");
        System.out.println(" 4 REINICIAR LA CLINICA (ELIMINAR BASE DATOS)");
        
                        switch(estatico.pedirNumero(1,4)){
                            
                            case 1:
                        baseDatos = estatico.carga_bbdd_prueba(this);
                        break;
                            case 2:
                            if(baseDatos == null) {baseDatos = new BBDD();
                                baseDatos.add_registro(75910907, ((Person)new Administrador(this, 
                                75910907, "Francisco", "Sanchez", "Jimenez",28)));
                            }
                           
                        estatico.cargaPruebasBBDD(this,baseDatos);
                        break;
                            case 3:
                            confinamiento.crear_nodosPrueba(baseDatos.lista_Pacientes() , this);
                        break;
                            case 4:
                            
                            this.baseDatos = null;
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                             System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX\n \n");
                            System.out.println("Se ha borrado la base de datos, su usuario administrador solo");
                            System.out.println("existe en la sesion actual, ");
                            System.out.println("dni del programador, 75910907  <- copie y pegue");
                            System.out.println("\n \nXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                            System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
                    }
                        System.out.println("RECUERDE, DESDE MENU ADMINISTRADOR, OPCION 8 MODO DEBUG");
    }
    
    /**
     * Menu que permite la modificacion o eliminacion de usuarios del sistema
     * @param int dni de usuario, pedido en metodo.
     */
    
    private void gestionUsuarios(Administrador t)
    {
        int seleccion;        
        System.out.println("\n\n Administracion de usuarios");
        System.out.println("Introduzca DNI ");
        seleccion = estatico.pedirNumero();
        Person auxiliar = null;
        if(baseDatos.contains_registro(seleccion)){
            System.out.println("se ha encontrado usuario");
            System.out.println("\n  * 1 Modificar usuario");
            System.out.println(  "  * 2 Eliminar usuario");
            System.out.println(  "  * 0 Salir");

            switch(estatico.pedirNumero(0,2))
            {
                case 1:
                    auxiliar = baseDatos.get_registro(seleccion);
                    auxiliar.formularioNuevoRegistro();
                    break;
                case 2:
                    baseDatos.eliminar_registro(seleccion);
                    break;
                case 0:
                    break;
            }
        }else
        {
            System.out.println("No se ha encontrado al usuario");
            this.altaUsuario((Administrador)this.usuario, seleccion);
        }
    }
    
    /**
     * Metodo que crea usuarios por eleccion
     */
    
    public void altaUsuario(Administrador t, int DNI)
    {
            Person auxiliar = null;
            System.out.println("Desea dar de alta a un nuevo usuario? (S)i");
            if(estatico.pedirConfirmacion())
            {
                System.out.println("Seleccione categoria");
                System.out.println("\n  * 1 Paciente");
                System.out.println(  "  * 2 Tecnico");
                System.out.println(  "  * 3 Enfermero");
                System.out.println(  "  * 4 Administrador");
                switch(estatico.pedirNumero(1,4))
                {
                    case 1:
                        auxiliar = new Paciente(DNI);
                        break;
                    case 2:
                        auxiliar = new Tecnico(DNI, this);
                        break;
                    case 3:
                        auxiliar = new Enfermero(DNI, this);
                        break;
                    case 4:
                        auxiliar = new Administrador(DNI, this);
                        break;
                }
                if(auxiliar !=null){
                auxiliar.formularioNuevoRegistro();
                baseDatos.add_registro(DNI, auxiliar);
            }
            }
        
    }
    
    /**
     * metodo que asigna pruebas a los diferentes diferentes usuarios, 
     * @param tipo tipo de prueba, enumerada: 1 Serologico 2 PCR 4 Vacuna y 3 Antigeno
     * @param paciente usuario al que asignar la prueba
     * @param usuario de la clinica, medida de seguridad impuesta en tiempo de ejecucion,
     * si la clase dada por parametro no es un admin e intenta acceder falla el programa.
     */
    public void asignar_prueba(int tipo, Paciente x, Administrador y)
     {
        // 1 serologico 2 PCR  3 Vacuna  4 antigenos (1)dia
        Tratamiento tratamiento = null;
        LinkedList<Trabajador> lista = null;
        
        if(x.puede_entrePruebas(tipo))
        {
            switch(tipo)
            {
                 case 1:
                     tratamiento = new AnalisisSerologico();
                     break;
                 case 2:
                     tratamiento = new TestPcr();
                     break;
                 case 4:
                     tratamiento = baseDatos.get_vacuna_Random();
                     System.out.println("Vacuna??? Clinica\\asignar_prueba");
                     break;
                 case 3:
                     System.out.println("Clasica(1) o Rapida(2)");
                     switch(estatico.pedirNumero(1,2))
                     {
                         case 1:
                             tratamiento = new PruebaClasica();
                             break;
                         case 2:
                             tratamiento = new PruebaRapida();
                             break;
                     }
                     break;
                 default:
                     System.out.println("fallo al introducir numero Clinica\\asignar_prueba");
                     break;
            }
            
            if(!(tratamiento instanceof Vacunas) && (trabajadoresLibres()))
            {
            baseDatos.add_Tratamiento(tratamiento);
            x.add_Tratamiento(tratamiento);
            }
            
            tratamiento.set_paciente(x);
            
              if(tratamiento != null && !(tratamiento instanceof Vacunas) 
              &&(trabajadoresLibres()))
                  { 
                  lista = baseDatos.lista_Tecnicos();
                try
                {
                    for(Trabajador z : lista)
                    {
                        if(z.planSemanal_espacioLibre())
                        {
                            tratamiento.set_tecnico((Tecnico)z);
                            z.aceptar_Tratamiento(tratamiento);
                            throw new RuntimeException();
                        }
                    }
                }catch(Exception e)
                {
                    System.out.println("Tecnico: Aceptado");
                }
              
            
                lista = baseDatos.lista_Enfermero();
                try
                {
                    for(Trabajador g : lista)
                    {
                        if(g.planSemanal_espacioLibre())
                        {
                            tratamiento.set_enfermeroAsignado((Enfermero)g);
                            g.aceptar_Tratamiento(tratamiento);
                            throw new RuntimeException();
                        }
                    }
                }catch(Exception e)
                {
                    System.out.println("Enfermero: Aceptado");
                }
            } else if (tratamiento != null){
                boolean aceptado = false;
                
                lista = baseDatos.lista_Enfermero();
                
                while(aceptado = false){
                 
                    for(Trabajador g : lista)
                    {
                        try{
                        if (((Enfermero)g).vacunacionesRestantes() < umbral)
                        {
                            g.aceptar_Tratamiento(tratamiento);
                            aceptado = true;
                            throw new RuntimeException();
                        }
                    }catch(Exception e)
                        {
                         System.out.println("Enfermero acepto vacuna, confirmar"
                         +"con modulo vacunacion");   
                        }
                        
                    }
                    if(aceptado = false) umbral++;
                    
                }
            
            
            }else{System.out.println("no hay suficientes trabjadores libres");}
        }else{
         System.out.println("No ha pasado el tiempo suficiente entre pruebas");   
        }
    
    
    }
    
    
        /**
     * metodo debug
     * @param tipo tipo de prueba, enumerada: 1 Serologico 2 PCR 4 Vacuna y 3 Antigeno
     * @param paciente usuario al que asignar la prueba
     * @param usuario de la clinica, medida de seguridad impuesta en tiempo de ejecucion,
     * si la clase dada por parametro no es un admin e intenta acceder falla el programa.
     */
    public void asignar_prueba(int tipo, Paciente x, Administrador y, Tecnico z, Enfermero l)
     {
        // 1 serologico 2 PCR  3 Vacuna  4 antigenos (1)dia
        Tratamiento tratamiento = null;
        
        
        if(x.puede_entrePruebas(tipo))
        {
                switch(tipo)
                {
                     case 1:
                         tratamiento = new AnalisisSerologico();
                         break;
                     case 2:
                         tratamiento = new TestPcr();
                         break;
                     case 3:
                         tratamiento = baseDatos.get_vacuna_Random();
                         System.out.println("Vacuna??? Clinica\\asignar_prueba");
                         break;
                     case 5:
                         
                                 tratamiento = new PruebaClasica();
                                 break;
                     case 6:
                                 tratamiento = new PruebaRapida();
                                 break;
                 }
     
                
                
                if(!(tratamiento instanceof Vacunas) && (trabajadoresLibres()))
                {
                baseDatos.add_Tratamiento(tratamiento);
                x.add_Tratamiento(tratamiento);
                }
                
                tratamiento.set_paciente(x);
                
                  if(tratamiento != null && !(tratamiento instanceof Vacunas) 
                  &&(trabajadoresLibres()))
                      { 
                      if(z.planSemanal_espacioLibre())
                        {
                                tratamiento.set_tecnico((Tecnico)z);
                                z.aceptar_Tratamiento(tratamiento);
     
                    
                        System.out.println("Tecnico: Aceptado");
                        }
    
                        
                            if(l.planSemanal_espacioLibre())
                            {
                                tratamiento.set_enfermeroAsignado((Enfermero)l);
                                l.aceptar_Tratamiento(tratamiento);
                                 
                            }
                        
     
                } else if (tratamiento != null)
                {
                                l.aceptar_Tratamiento(tratamiento);
     
                    }
            
                
                
            } System.out.println("No ha pasado el tiempo suficiente entre pruebas");   
        }
 
    /**
     * Metodo decorador del metodo asignar_prueba(), hace las veces de comprobador de estado de la clinica
     * y configurador de parametros, tiene algunos metodos bloqueados a la espera de ser implementados (version 0.1)
     */
    public void asignarTratamiento(Administrador t)
    {
            int apoyo;
            Tratamiento auxiliar;
            Person complemento;
            
            System.out.println(" Menu Asignacion pruebas y vacuNas (Sistema MAN)");
            System.out.println("Por favor, ingrese el DNI del sujeto a MANipular");
            apoyo = estatico.pedirNumero();
            
            if(baseDatos.contains_registro(apoyo))
            {
                complemento = baseDatos.get_registro(apoyo);
                System.out.println("\nPor favor, elija el tratamiento:");
                System.out.println(  "    * 1 Vacuna");
                System.out.println(  "    * 2 Pruebas");
                
                switch(estatico.pedirNumero(1,2))
                {
                    case 1:
                       // System.out.println("\n Desea que el proceso sea automatico(1) o manual?(2)");
                      //  switch(estatico.pedirNumero(1,2))
                          switch(2)
                        {
                            case 1:
                                //asignar_vacuna_automatica();
                                break;
                            case 2:
                                //asignar_vacuna_manual();
                                break;
                        }
                    break;
                    case 2:
                        System.out.println("Elija el tipo de prueba");
                        System.out.println("    * 1 Analisis Serologico");
                        System.out.println("    * 2 Test PCR");
                        System.out.println("    * 3 Antigenos:");
                        System.out.println("            *   Clasica");
                        System.out.println("            *   Prueba rapida");
                        if(complemento instanceof Paciente )
                        {
                            if(trabajadoresLibres())
                        asignar_prueba(estatico.pedirNumero(1,4), ((Paciente)complemento), t);
                        else System.out.println("No hay trabajadores libres, contrate mas");
                        }else{
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("Ha elegido un usuario no paciente, si desea hacerle");
                            System.out.println("una prueba a un trabajador debe crearle una ficha como");
                            System.out.println("paciente, actualmente no esta implementado pero puede usted");
                            System.out.println("acceder a esta funcion creando un usuario con el mismo dni");
                            System.out.println(" + un numero identificativo interno, como la edad, por ejemplo");
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                        }
                        break;
            }
        
            System.out.println("");
            }
        
    }
    
    /**
     * metodo que comprueba que haya trabajadores con espacio en su plan semanal
     * @return boolean
     */
    
    public boolean trabajadoresLibres()
    {
                boolean tecnicoLibre = false;
                boolean enfermeroLibre = false;
                
                LinkedList<Trabajador> lista = baseDatos.lista_Tecnicos();
                try
                {
                    for(Trabajador g : lista)
                    {
                        if(g.planSemanal_espacioLibre())
                        {
                            tecnicoLibre = true;
                            throw new RuntimeException();
                        }
                    }
                }catch(Exception e){}
                
                lista = baseDatos.lista_Enfermero();
                try
                {
                    for(Trabajador g : lista)
                    {
                        if(g.planSemanal_espacioLibre())
                        {
                            enfermeroLibre = true;
                            throw new RuntimeException();
                        }
                    }
                }catch(Exception e){}
                
                return (tecnicoLibre && enfermeroLibre);
                
    }
    
    /**
     * metodo que asigna un tratamiento de forma directa, sin pedir parametros al usuario.
     * Implementado como una forma de acceder a las clases para el modo debug y pruebas.
     * Este metodo hace un bypass a los comrpobadores de estados presentes en la cadena
     * de sucesion de metodos presente en el programa principal
     * 
     * No necesario en el programa final.
     * 
     * @param Administrador , restringe el uso del metodo a los administradores
     * @param DNIpaciente , el identificador del paciente que se va a someter a las pruebas
     * @param tipo de tratamiento a crear y asignar.
     */
    
    public void asignarTratamiento(Administrador t, int DNIpaciente, int tipo)
    {
            int apoyo;
            Tratamiento auxiliar;
            Person complemento;
        if(baseDatos.contains_registro(DNIpaciente))
            {
                complemento = baseDatos.get_registro(DNIpaciente);
 
                         //"   * 1 Analisis Serologico" 
                        // "    * 2 Test PCR" 
                       //  "    * 3 Antigenos:"
                       //  "            *   Clasica" 
                      //   "            *   Prueba rapida" 
                        if(complemento instanceof Paciente)
                        {
                        asignar_prueba(tipo, ((Paciente)complemento), t);
                        }else{
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                            System.out.println("Ha elegido un usuario no paciente, si desea hacerle");
                            System.out.println("una prueba a un trabajador debe crearle una ficha como");
                            System.out.println("paciente, actualmente no esta implementado pero puede usted");
                            System.out.println("acceder a esta funcion creando un usuario con el mismo dni");
                            System.out.println(" + un numero identificativo interno, como la edad, por ejemplo");
                            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

                        }

            }
        
            System.out.println("");
            }
        
    

    /**
     * String de apoyo, otorga claridad al metodo imprimirDatos, el String esta formateado.
     */   

    private String imp()
    {
        return (String.format("%-15s","Nombre")+String.format("%-15s","Apellido") +String.format("%-15s","Apellido")+String.format("%-5s", "edad")
        +String.format("%-8s","DNI"));
    
                }
              
    /**
     * Imprime el estado general de la clinica
     */
    private void imprimirDatos()
    {
            Person auxiliar;
            Set<Integer> recorrido = baseDatos.get_archivo().keySet();
            
            System.out.println("*****************************************************");
            System.out.println("\n\nLa clinica tiene un registro de personas total de "
            +                   baseDatos.size_registro());
            System.out.println();
            
            System.out.println("Datos de Pacientes: ");
            System.out.println("____________________________________________________________________________");
            System.out.print(imp() + "   vacunado  ");
            System.out.println();
            for(int x: recorrido)
            {

                auxiliar = baseDatos.get_registro(x);
                if(auxiliar instanceof Paciente){
                Paciente aux = (Paciente)auxiliar;
                System.out.println(aux.toString()+"   "+String.format("%-8s   ",aux.get_estadoVacunacion() ));
                if(((Paciente)auxiliar).get_estadoPrimeraDosis()) System.out.print("  1 dosis recibida");
                
                }
            }
            
            System.out.println();
            System.out.println("Datos de Tecnicos: ");
            System.out.println("____________________________________________________________________________");
            System.out.print(imp() + "no. casos:  cc  plan semanal");
            System.out.println();
            for(int x : recorrido)
            {
 
                auxiliar = baseDatos.get_registro(x);
                
                if(auxiliar instanceof Tecnico){
                Tecnico aux = (Tecnico)auxiliar;
                
                System.out.println(aux.toString() +"      " +String.format("%-15d",aux.numero_tratamientos())
                + String.format("%-4d",((Trabajador)auxiliar).num_casos_PS()));
                }
            
            }
            
            System.out.println();
            System.out.println("Datos de Enfermeros:");
            System.out.println("____________________________________________________________________________");
            System.out.print(imp() + "no. casos:  cc  plan semanal");
            System.out.println();
            for(int x : recorrido)
            {
                 
                auxiliar = baseDatos.get_registro(x);
                
                if(auxiliar instanceof Enfermero){
                Enfermero aux = (Enfermero)auxiliar;
                
                System.out.println(aux.toString() +"      " +String.format("%-15d",aux.numero_tratamientos())
                + String.format("%-4d",((Trabajador)auxiliar).num_casos_PS()));
            }
            }            
            
            System.out.println("Datos de Administradores: \n");
            System.out.println(imp());
            for(int x : recorrido)
            {
                 
                auxiliar = baseDatos.get_registro(x);
                
                if(auxiliar instanceof Administrador){
                Administrador aux = (Administrador)auxiliar;
                System.out.println(aux.toString() );
            
                }
            }
        }
    
    
     /**
      * Metodo que actualiza el stock de vacunas, necesario que el usuario de la clinica en el
      * momento sea un administrador (medida de seguridad y bloqueo)
      * 
      * @param administrador de la clinica
      */
    public void actualizar_stockVacunas(Administrador t)
    {
            System.out.println("En la nevera hay un total actual de " +baseDatos.numeroVacunas()+" vacunas");
            System.out.println("Pfizer:  " +baseDatos.get_numeroPfizer());
            System.out.println("Moderna: "+baseDatos.get_numeroModerna());
            System.out.println("JJ:      "+baseDatos.get_numeroJJ());
            System.out.println("\n Desea ampliar el stock?");
            if(estatico.pedirConfirmacion())
            {
            System.out.println(" Seleccione una opcion:");
            System.out.println("  * 1 agregar Pfizer");
            System.out.println("  * 2 agregar Moderna");
            System.out.println("  * 3 agregar JJ");
            System.out.println("  * 4 Cancelar");
            
                switch(estatico.pedirNumero(1,4))
                    {
                    case 1:
                        System.out.println("Agregando Pfizer, inserte unidades");
                        baseDatos.agrega_Pfizer(estatico.pedirNumero());
                        break;
                    case 2:
                        System.out.println("Agregando Moderna, inserte unidades");
                        baseDatos.agrega_Moderna(estatico.pedirNumero());
                        break;
                    case 3:
                        System.out.println("Agregando JJ, inserte unidades");
                        baseDatos.agrega_JJ(estatico.pedirNumero());
                        break;
                    case 4:
                        System.out.println("Saliendo");
                        break;
                    }
            }
            System.out.println("\n\nx  x  x  x  x  x  x  x  \n\n");
        
        }
    
        
    private void visualizarVacunas(Administrador t)
    {
            System.out.println("por implementar");
    }
    
    
    
    /**
     * Menu de trabajadores, necesita que el usuario de la clinica sea un
     * trabajador, comprueba el tipo de instancia en tiempo de ejecucion y
     * aplica los metodos y las funciones propias de la clase final.
     * 
     * Permite que los enfermeros y tecnicos vean la carga de trabajo que tienen
     * y que actualicen la informacion acordemente.
     * 
     * @param operario de la clinica
     */
    
    public void menuTrabajador(Trabajador x)
    {
        boolean salir = false;
        do{
        if(x instanceof Enfermero)
        System.out.println("       Bievenido Enfermero  ");
        if(x instanceof Tecnico)
        System.out.println("seleccione la operacion que desee ejecutar:");
        
        System.out.println("     * 1 Visualizacion de datos plan Semanal");
        System.out.println("     * 2 Visualizacion de tratamientos completados");
        System.out.println("     * 3 Visualizacion datos Paciente");
        System.out.println("     * 4 Registro y actualizacion de tratamientos");
        System.out.println ("     * 5 Salir");
        
        switch(estatico.pedirNumero(1,5))
        {
            
         case 1:
             x.imprimir_planSemanal();
             break;
         case 2:
            x.imprimir_completados();
            break;
         case 3:
            System.out.println("        *1   Pacientes de tratamientos completados");
            System.out.println("        *2   Pacientes de plan Semanal");
            System.out.println("        *3   Paciente de la base de datos");
            switch(estatico.pedirNumero(1,3))
            {
                case 1:
                    x.imprimir_pacientes_Completados();
                    break;
                case 2:
                    x.imprimir_pacientes_planSemanal();
                    break;
                case 3:
                    imprimir_datosUsuarioUnitario();
                    break;
                }
             break;
         case 4:
             if(x instanceof Tecnico)
             {
             ((Tecnico)x).TrabajarPruebas();
             } else if(x instanceof Enfermero)
             {
                 System.out.println("Desea realizar pruebas a pacientes(1) o vacunar?(2)");
                 switch(estatico.pedirNumero(1,2))
                 {
                     case 2:
                         ((Enfermero)x).trabajarVacuna();
                         break;
                     
                     case 1:
                         ((Enfermero)x).trabajarPrueba();
                         break;
             }
              
            }
            break;
         case 5:
             salir = true;
             break;
        }
        }while(!salir);
    }
    
    /**
     * Metodo propio de administrador, muestra informacion general o
     * de un usuario concreto
     */
    
        private void visualizarDatos(Administrador t)
    {
            if((t instanceof Administrador))
            System.out.println("  * 1  Ver un glosario general del estado de la clinica  ");
            System.out.println("  * 2  Ver expediente de paciente o trabajador");
 
            
            switch(estatico.pedirNumero(1,2))
            {
                case 1:
                    imprimirDatos();
                    break;
                case 2:
                    imprimir_datosUsuarioUnitario();
                    
                    break;
 
                    
            }
 
    }
    
    /**
     * metodo que imprime los datos de un usuario concreto, lo implementa el menu de trabajador y el
     * de administrador, comprueba tipo de objeto en tiempo de ejecucion (tipo de persona) e imprime
     * la informacion especializada de la clase
     */
    
    public void imprimir_datosUsuarioUnitario()
    {
          System.out.println("Ingrese el DNI");
          int auxiliar = estatico.pedirNumero();
          if(baseDatos.contains_registro(auxiliar))
          {
              System.out.println("Encontrado");
              Person comodin = baseDatos.get_registro(auxiliar);
              if(comodin instanceof Paciente)
              {
                  estatico.print_imp();
                  System.out.println(comodin);
                  comodin.imprimir_Tratamientos();
             }
            
                  if(comodin instanceof Trabajador && (!(comodin instanceof Administrador)) )
                {
                        estatico.print_imp();
                        System.out.println(comodin);
                        System.out.println("\n\n XXXXXXXX tratamientos completados XXXXXXXX\n");
                        comodin.imprimir_Tratamientos();
                        System.out.println("\n\n XXXXXXXX Plan Semanal XXXXXXXX\n");
                        ((Trabajador)comodin).imprimir_planSemanal();
                }
        } else{
            System.out.println("No encontrado");    
        }
    
    }
    
    /**
     * Manda la prueba con resultado positivo al modulo de confinamiento.
     * los parametros sirven para restringir el flujo de informacon y lanzar
     * fallo en tiempo de ejecucion si un usuario que no es de la clase adecuada
     * intenta usar el metodo. Este metodo solo esta implementado en la clase Tecnico.
     */
    
    public void alerta(Tecnico x, Pruebas y)
    {
        confinamiento.alerta(y);
        System.out.println(" modulo confinamiento: ACKNOWLEDGE");   
    }
    
    
    /**
     * Manda una senhal al modulo de vacunacion, si el parametro booleano
     * es igual a true la senhal es de eliminacion de informacion, si
     * la senhal es false manda una senhal al modulo para indicarle que
     * el usuario ha sido ya vacunado y sacarlo de las colas de prioridad
     * correspondientes. No implementado en la version 0.1
     * 
     */
    public void alertaVacuna(Trabajador x, Tratamiento y, boolean z)
    {
        if(z)
         System.out.println("modulo vacunacion, elimine");   
         else System.out.println("modulo vacunacion, acknowlege vacunado");
    }
    
}


















