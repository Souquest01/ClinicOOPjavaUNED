
/**
 * Write a description of class JJ here.
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public class JJ extends Vacunas
{
    
    boolean lleno;
    
    /**
     * Constructor for objects of class JJ
     */
    public JJ( )
    {
        super();
        lleno = true;
        
    }

    @Override
    public boolean usar()
    {
        
      if(lleno) {lleno = false; this.vaciar(); return true; }
      else      return false;
    
    }

}
