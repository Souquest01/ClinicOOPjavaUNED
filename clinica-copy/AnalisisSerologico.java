
/**
 * Analisis serologico hereda de prueba aunque es un poco mas especial que el
 * resto, esta prueba no determina si se tiene el virus, si no si se hayan
 * presentes en el organismo los anticuerpos para este.
 * Por lo tanto el resultado determina en este caso si se esta "inmunizado".
 * Hay que prestar especial atencion para no mezlarlo con los otros.
 *
 * @author (Francisco Sanchez)
 * @version (0.1)
 */
public class AnalisisSerologico extends Pruebas
{
 
    private int nivelAnticuerpos;
    private final int lapsoDias = estatico.SEROLOGICO;
     
    /**
     * constructor del analisis serologico. el constructor llama a super() automaticamente.
     */
    public AnalisisSerologico()
    {
     nivelAnticuerpos = 0;
    }
    
    
 
    /**
     * metodo que sobreescribe al metodo de la clase padre analizar_prueba.
     * La clase difiere del resto y por lo tanto necesita un trato especial.
     * @param el tecnico que va a analizar la prueba
     */
    @Override
    public void analizar_prueba(Tecnico tecnico)
    {
        this.nivelAnticuerpos = estatico.resultadoAnticuerpos();
        this.set_resultado(nivelAnticuerpos > 2);
    }
    
    /**
     * metodo accesor del tiempo en dias entre pruebas de tipo serologico.
     * @return estatico.SEROLOGICO
     */
    @Override
    public int get_lapsoDias()
    {
        return this.lapsoDias;
    }
}
