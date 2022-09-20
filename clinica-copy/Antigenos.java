
/**
 * Abstract class Antigenos - 
 * clase abstracta, representa el comun de las pruebas
 * de antigenos, las cuales se pueden hacer diariamente.
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public abstract class Antigenos extends Pruebas

{
     
    final int lapsoDias = 1;
    
 
    
    /**
     * implementa el metodo abstracto de la clase padre.
     * @return lapsoDias
     */
    @Override
    public int get_lapsoDias()
    {
        return this.lapsoDias;
    }
    

 
    
}
