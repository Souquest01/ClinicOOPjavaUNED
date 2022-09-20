
/***
 * Abstract class Vacunas - Define unos patrones genericos para las 3 vacunas
 * y los metodos aplicables como interfaz. Metodos pueden estar sobrecargados en las clases
 * hijas para adaptarse a las necesidades propias de las subclases.
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public abstract class Vacunas extends Tratamiento

{

    
    private int dosisRestantes;
    /***
     * constructor de la clase Vacunas, la clase es abstracta
     */
    Vacunas()
    {

        dosisRestantes = 2; //representa una dosis total, en jj al usar la vacuna se restan las dos a la vez
    }
    
    /**
     * representa la accion de aplicar una dosis de la vacuna
     * @return boolean si la vacuna tenia dosis suficientes devuelve true
     */
    public boolean usar()
    {
        if(dosisRestantes >= 1)
        {
            dosisRestantes--;
            return true;
        }else
        {
            return false;
        }
    }
    
    /**
     * metodo mutador que pone a 0 la cantidad de dosis restantes
     * en la vacuna
     */
    public void vaciar()
    {
        this.dosisRestantes = 0;
    }
    
    
    /**
     * metodo selector de dosis restantes de la vacuna
     */
    public int get_dosisRestantes()
    {
         return this.dosisRestantes;   
    }
    
    /**
     * Comprueba, accediendo a el metodo de su clase padre si se podria
     * realizar una segunda vacuna en el dia de la comprobacion.
     * 
     */
    public boolean cuota_tiempo()
    {
        return super.cuota_tiempo(estatico.TIEMPOVACUNAS);
    }
    
    /**
     * devuelve el tiempo necesario de espera entre vacunas
     * @return dias
     */
    public int get_lapsoDias()
    {
        return estatico.TIEMPOVACUNAS;
    }
}
