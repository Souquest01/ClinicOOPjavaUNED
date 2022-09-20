
/**
 * Abstract class Pruebas - 
 * clase de la que derivan las pruebas, esta a su vez
 * hereda de tratamientos
 *
 * consta de 3 atributos mas con respecto a Tratamientos;
 * resultado, procesado y ejecutado. Respectivamente muestran
 * si ha dado un resultado positivo o negativo, si ha sido procesado por el
 * tecnico y ejecutado por el enfermero, por lo tanto el resultado
 * seria el ultimo atributo en asignarse y solo tendria valor si
 * los otros dos son true.
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public abstract class Pruebas extends Tratamiento
{


    private boolean resultado;
    private boolean procesado;
    private boolean ejecutado;
    public Pruebas ()
    {
        this.resultado = false;
        this.procesado = false;
        this.ejecutado = false;
    }
    
    /**
     * metodo abstracto para implementar por las clases hijas
     * @return numero de dias entre pruebas
     */
    abstract public int get_lapsoDias();
    
    /**
     * accesor resultado
     * @param resultado
     */
    public boolean get_resultado()
    {
        return this.resultado;
    }

    /**
     * mutador resultado y procesado.
     * @param resultado
     */
    public void set_resultado(boolean resultado)
    {
        this.procesado = true;
        this.resultado = resultado;
    }

    /**
     * accesor procesado
     * @return procesado
     */
    public boolean get_procesado()
    {
        return procesado;
    }
    
    /**
     * accesor ejecutado
     * @return ejecutado
     */
    public boolean get_ejecutado()
    {
        return this.ejecutado;
    }
 
    /**
     * mutador de ejecutado. Este es el metodo que debe usarse
     * para cambiar el estado de una prueba, puesto que es el enfermero el
     * que tiene que declarar que se ha ejecutado la prueba
     * @param enfermero que ejecuta la prueba
     */
    public void hacer_prueba(Enfermero enfermero)
    {
            this.ejecutado = true;
    }
    
     /**
      * mutador de resultado, la contraparte del metodo hacer_prueba para el tecnico.
      * el resultado deberia de aplicarse con este metodo.
      * @param tecnico que realiza la prueba.
      */
    public void analizar_prueba(Tecnico tecnico)
    {
      this.set_resultado(estatico.resultadoTest());   
    }
    
}
