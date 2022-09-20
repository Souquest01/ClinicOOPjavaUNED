
/**
 * Esta clase representa a un test PCR, impone un limite en
 * los dias entre pruebas pero no tiene nada mas especial con respecto
 * a otra prueba cualquiera de analisis de presencia viral
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public class TestPcr extends Pruebas
{
    
    
    private final int lapsoDias = estatico.PCR;
    

    /**
     * Constructor for objects of class TestPcr
     */
    public TestPcr()
    {

    }
    
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
