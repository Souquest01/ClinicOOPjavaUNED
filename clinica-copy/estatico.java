import java.io.BufferedReader;
import java.io.InputStreamReader;

/***
 * Clase que almacena funciones, metodos y parametros de utilidad general
 * en el programa. Permite centralizar y parametrizar el programa de acorde
 * a las exigencias que se puedan presentar, como un cambio en el tiempo entre
 * vacunas
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public abstract class estatico
{
    // TIEMPOS ENTRE PRUEBAS Y VACUNAS
    static final int TIEMPOVACUNAS = 21;
    static final int PCR = 15;
    static final int SEROLOGICO = 180;
    static final int NUSUARIOTEST = 100;
    static final int TEC_LIMITE = 4;
    static final int ENF_LIMITE = 5;
    static final int PROBABILIDADNEGATIVO = 7; // *10 (porcentaje de negativo)
    
    /**
     * constructor, privado, esta clase no representa ningun objeto en si misma
     */
    private estatico()
    {
    }
    
    /**
     * funcion que devuelve un resultado positivo o negativo
     * en base a un numero random y a 3 posibilidades entre 10,
     * representando la probabilidad que tienes de dar positivo en el test
     */
    public static boolean resultadoTest()
    {
         if(Math.random()*10 <= PROBABILIDADNEGATIVO){
             return false;
            }
            return true;
    }
    
    /**
     * Devuelve el resultado de anticuerpos en base a 100
     * @return int anticuerpos
     */
    public static int resultadoAnticuerpos()
    {
        return (int)(Math.random()* 100);  
    }
    
    /**
     * generador de numeros aleatorios
     * @param ventana indica la ultima cifra generable; 0 es posibilidad
     * return numeroRandom
     */
    public static int numeroRandom(int ventana)
    {
         return (int)(Math.random()*ventana);   
    }
    
    static String[] opcionesNombre = {
         "Pepe", "Maria", "Asuncion", "Gloria", "Lucia", "Lucifer", "Amandonasio", "Teoclido", "Juan","Jhon10"
        };
    
    static  String[] opcionesApellidos =         
        {
         "Sanchez","Jimenez","De la Vega", "Ramirez","Ticchi","Vecchio","XXXXdelta", "Omicron", "Hyperion", "Asimov","Perez","Reverte",
         "Gladious","Zimmerman","Fritz","Delgado","Heredia","Xiaomi","Grundig","Iiyama","Uzumaki","Frics","Zoldick23"
        };
    /**
     * metodo de apoyo para el modo de test (o debug)
     * Carga usuarios en una base de datos, se apoya de las funciones de generacion
     * de numeros aleatorios de la clase estatico
     * @return baseDatos
     */
    public static BBDD carga_bbdd_prueba(Clinica ClinicaSanPedro)
    {
        BBDD baseDatos = new BBDD();
        baseDatos.add_registro(75910907, ((Person)new Administrador(ClinicaSanPedro, 
                                75910907, "Francisco", "Sanchez", "Jimenez",28)));
        int auxiliar; 
        int auxiliar2;
             
        

        
        
        
        for(int x = 1; x <= estatico.NUSUARIOTEST; x++)
        { 
            auxiliar2 = (estatico.numeroRandom(3));
            auxiliar = estatico.numeroRandom(99999999);
            switch(auxiliar2){
                
                case 1:
                    baseDatos.add_registro(auxiliar, ((Person)new Paciente(auxiliar, 
                    opcionesNombre[estatico.numeroRandom(8)], 
                    opcionesApellidos[estatico.numeroRandom(22)], opcionesApellidos[estatico.numeroRandom(22)], 
                    estatico.numeroRandom(110))));
                    System.out.println("Creado Paciente");
                break;
                default:
                    baseDatos.add_registro(auxiliar, ((Person)new Tecnico(ClinicaSanPedro, auxiliar,
                    opcionesNombre[estatico.numeroRandom(8)],
                    opcionesApellidos[estatico.numeroRandom(22)], opcionesApellidos[estatico.numeroRandom(22)],
                    estatico.numeroRandom(60))));
                    System.out.println("Creado Tecnico");
                break;
                case 0 :
                    baseDatos.add_registro(auxiliar,
                    ((Person)new Enfermero(ClinicaSanPedro, auxiliar, 
                    opcionesNombre[estatico.numeroRandom(8)], opcionesApellidos[estatico.numeroRandom(22)], 
                    opcionesApellidos[estatico.numeroRandom(22)], estatico.numeroRandom(60))));
                    System.out.println("Creado Enfermero");
                    break;
            }
            
            
        }
        return baseDatos;
    }
    
    
    public static void cargaPacientes(Clinica ClinicaSanPedro, BBDD baseDatos,int multiplicador)
    {
        int limite = 5 * multiplicador;
        for(int x = 0  ; x <= limite; x++)
        {
            System.out.println(x + "dfjakdjfkajdfjaksdjf");
            baseDatos.add_registro(x, ((Person)new Paciente(x, 
                    opcionesNombre[estatico.numeroRandom(8)], 
                    opcionesApellidos[estatico.numeroRandom(22)], opcionesApellidos[estatico.numeroRandom(22)], 
                    estatico.numeroRandom(110))));
                    System.out.println("Creado Paciente");    
            
        }
        
    }
    
    public static void cargaEnfermeros(Clinica ClinicaSanPedro, BBDD baseDatos, int multiplicador)
    {
        int limite = 11 * multiplicador;
        for(int x = 6 * multiplicador; x <= limite; x++)
        {
                    baseDatos.add_registro(x,
                    ((Person)new Enfermero(ClinicaSanPedro, x, 
                    opcionesNombre[estatico.numeroRandom(8)], opcionesApellidos[estatico.numeroRandom(22)], 
                    opcionesApellidos[estatico.numeroRandom(22)], estatico.numeroRandom(60))));
                    System.out.println("Creado Enfermero");
        }
        
        
    }
    
    public static void cargaTecnicos(Clinica ClinicaSanPedro, BBDD baseDatos, int multiplicador)
    {
        int limite = 16 * multiplicador;
        for(int x = 12 * multiplicador; x <= limite; x++)
        {
                    baseDatos.add_registro(x,
                    ((Person)new Tecnico(ClinicaSanPedro, x, 
                    opcionesNombre[estatico.numeroRandom(8)], opcionesApellidos[estatico.numeroRandom(22)], 
                    opcionesApellidos[estatico.numeroRandom(22)], estatico.numeroRandom(60))));
                    System.out.println("Creado Tecnico");
        }
        
        
    }
    
    public static void cargaPruebasBBDD(Clinica ClinicaSanPedro, BBDD baseDatos)
    {
        
 
        
        for(int x = 0  ; x <= 9; x++)
        {
             
            baseDatos.add_registro(x, ((Person)new Paciente(x, 
                    opcionesNombre[estatico.numeroRandom(8)], 
                    opcionesApellidos[estatico.numeroRandom(22)], opcionesApellidos[estatico.numeroRandom(22)], 
                    estatico.numeroRandom(110))));
                    System.out.println("Creado Paciente");    
            
        }
         
        
        for(int y = 10; y <= 19; y++)
        {
                    baseDatos.add_registro(y,
                    ((Person)new Enfermero(ClinicaSanPedro, y, 
                    opcionesNombre[estatico.numeroRandom(8)], opcionesApellidos[estatico.numeroRandom(22)], 
                    opcionesApellidos[estatico.numeroRandom(22)], estatico.numeroRandom(60))));
                    System.out.println("Creado Enfermero");
        }
        
         
        for(int z = 20; z <= 29; z++)
        {
                    baseDatos.add_registro(z,
                    ((Person)new Tecnico(ClinicaSanPedro, z, 
                    opcionesNombre[estatico.numeroRandom(8)], opcionesApellidos[estatico.numeroRandom(22)], 
                    opcionesApellidos[estatico.numeroRandom(22)], estatico.numeroRandom(60))));
                    System.out.println("Creado Tecnico");
        }
        
        for(int k = 0; k <= 9; k++)
        {
            Paciente uno;
            Enfermero dos;
            Tecnico tres;
            Administrador cuatro = (Administrador)baseDatos.get_registro(75910907);
            
            uno = (Paciente)baseDatos.get_registro(k);
            dos = (Enfermero)baseDatos.get_registro(k+10);
            tres = (Tecnico)baseDatos.get_registro(k+20);
            

        

                 //asignar_prueba(int tipo, Paciente x, Administrador y, Tecnico z, Enfermero l)
             ClinicaSanPedro.asignar_prueba( 2 , uno , cuatro, tres, dos);
            }
            
             for(int t = 10; t <= 19; t++) 
             {
                ((Enfermero)baseDatos.get_registro(t)).trabajarPrueba();
            }  
            
            for(int w = 20; w <= 29; w++)
            {
            
                ((Tecnico)baseDatos.get_registro(w)).TrabajarPruebas();   
           
            }
            
            }
                
                
        
    
    /**
     * Funcion que pide una secuencia numerica a un usuario y la suministra al programa, esta version no tiene restriccion en cuanto
     * rango de numeros, mientras sea un numero.
     * @return entradaInt el numero introducido por el usuario
     */
    public static int pedirNumero(){
        String entrada;
        int entradaInt = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean problema = false;
        do
        {
                
                try{
                    entradaInt = Integer.parseInt(br.readLine());
                    problema = false;
                }catch(Exception e)
                {
                    System.out.println("no se ha registrado una secuencia numerica");
                    System.out.println("por favor, intentelo de nuevo");
                    problema = true;
                }     
        }while(problema || entradaInt <= 0 );
        return entradaInt;
    }
    
    /**
     * Funcion que pide una secuencia numerica a un usuario y la suministra al programa, esta version tiene restriccion en cuanto
     * rango de numeros, primer valor representa el inicio y el segundo el final.
     * @return entradaInt el numero introducido por el usuario
     */
        public static int pedirNumero(int inicio, int ultimo){
            String entrada;
            int entradaInt = -1;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean problema = false;
            do{
                    
                    try{
                        entradaInt = Integer.parseInt(br.readLine());
                        problema = false;
                    }catch(Exception e){
                        System.out.println("no se ha registrado una secuencia numerica");
                        System.out.println("por favor, intentelo de nuevo");
                        problema = true;
                    }     
             }while(problema || entradaInt < inicio || entradaInt > ultimo );
        return entradaInt;
    }
    
    /**
     * imprime un marco para la visualizacion posterior de datos persona
     */
    public static void print_imp()
    {
    System.out.println(String.format("%-15s","Nombre")+String.format("%-15s","Apellido") +String.format("%-15s","Apellido")+String.format("%-5s", "edad")
    +String.format("%-8s","DNI"));
    }
    
    /**
     * Pide una cadena de caracteres al usuario para suministrarla en otros puntos de programa,
     * como en nombres o apellidos. Esta version no tiene restricciones en la actual version (0.1)
     */
    public static String pedirTexto()
    {
        
        boolean problema = false;
        String cadena = "no se ha registrado entrada//fallo";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
                
                try{
                    cadena = br.readLine();
                }catch(Exception e){
                    System.out.println("Ha habido algun fallo de entrada");
                    System.out.println("por favor, intentelo de nuevo");
                    problema = true;
                }     
        }while(problema);
        return cadena;
    }
    
    /**
     * Pide una confirmacion al usuario, devuelve true o false.
     * @return boolean confirmacion
     */
    
    public static boolean pedirConfirmacion()
    {
        char auxiliar = 'e';
        String aux = "";
        boolean procesado = false;
        do
        {
    
            if(procesado)System.out.print("s? / n?");
            aux = estatico.pedirTexto().toLowerCase();
            aux = aux + 'f';
            auxiliar = aux.charAt(0);
            procesado = true;
            
            }
        while(!(auxiliar != 's' || auxiliar != 'n'));   
        if(auxiliar == 's'){return true;}
        else{return false;}
    }
    
}
