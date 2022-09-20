
/***
 * Clase que representa una nevera donde se almacenan las vacunas
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public class Nevera
{

    private int vacunasJJ;
    private int vacunasPfizer;
    private int vacunasModerna;
    private int vacunasTotal;

    /***
     * Constructor de la clase nevera, inicializa los valores a 0
     */
    public Nevera()
    {
        vacunasJJ = 0;
        vacunasPfizer = 0;
        vacunasModerna = 0;
        vacunasTotal = 0;
    }

    /**
     * funcion que devuelve una vacuna de forma aleatoria de la nevera,
     * si la que se ha elegido no tiene existencias se vuelve a elejir aleatoriamente,
     * hasta que al final devuelve una.
     * @return vacunita vacuna de forma aleatoria
     */
    public Vacunas get_vacuna_Random()
    {
        Vacunas vacunita = null;
        if(vacunasTotal >0)
        {
            do{
                switch(estatico.numeroRandom(2))
                {
                 case 0:
                    vacunita = this.get_vacunaJJ();
                     break;
                 case 1:
                    vacunita =  this.get_vacunaModerna();
                     break;
                 case 2:
                    vacunita = this.get_vacunaPfizer();
                     break;
    
                }
                
            }while(vacunita == null);
            vacunasTotal--;
        }else
        {
            System.out.println("No quedan Vacunas");
        }
        return vacunita;
    }
     
    /**
     * metodos que devuelven una vacuna de forma no random. No util para el programa
     * principal, pero si para las pruebas.
     */
        public void recibe_JJ(int x)
    {
        this.vacunasJJ += x;
        vacunasTotal += x;
    }
        public void recibe_Pfizer(int x)
    {
        this.vacunasPfizer += x;
        vacunasTotal += x;
    }
        public void recibe_Moderna(int x)
    {
        this.vacunasModerna += x;
        vacunasTotal += x;
    }
    
    /**
     * metodo que devuelve el numero de vacunas en total almacenadas en la nevera
     * @return vacunasTotal
     */
    public int get_numeroVacunas()
    {
        return vacunasTotal;
    }
    
    /**
     * metodo que devuelve el numero de vacunas en total de un tipo dado
     * @return vacunasPfizer
     */
    public int get_numeroPfizer()
    {
        return this.vacunasPfizer;
    }
    
    /**
     * metodo que devuelve el numero de vacunas en total de un tipo dado
     * @return vacunasModerna
     */    
    public int get_numeroModerna()
    {
        return this.vacunasModerna;
    }
    
    /**
     * metodo que devuelve el numero de vacunas en total de un tipo dado
     * @return vacunasJJ
     */    
    public int get_numeroJJ()
    {
        return this.vacunasJJ;
    }
    
    /**
     * metodo que devuelve una vacuna especifica, de un tipo dado
     * @return JJ
     */    
    public JJ get_vacunaJJ()
    {
        if(vacunasJJ >0)
        {
        vacunasJJ--;
        return new JJ();
        }else
        {
            return null;
        }
    }
    
    /**
     * metodo que devuelve una vacuna especifica, de un tipo dado
     * @return Pfizer
     */    
    public Pfizer get_vacunaPfizer()
    {
        if(vacunasPfizer >0)
        {
        vacunasPfizer--;
        return new Pfizer();
        }else
        {
            return null;
        }
    }
    
    /**
     * metodo que devuelve una vacuna especifica, de un tipo dado
     * @return Moderna
     */    
    
    public Moderna get_vacunaModerna()
    {
        if(vacunasJJ >0)
        {
        vacunasModerna--;
        return new Moderna();
        }else
        {
            return null;
        }
    }
}
