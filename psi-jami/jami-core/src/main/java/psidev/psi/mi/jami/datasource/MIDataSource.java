package psidev.psi.mi.jami.datasource;

import java.util.Map;

/**
 * A molecular interaction  DataSource.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/03/13</pre>
 */

public interface MIDataSource {

    /**
     * Initialise the context of the MIDataSource given a map of options
     * @param options
     */
    public void initialiseContext(Map<String, Object> options);

    /**
     * This method close the file data source and all opened streams and readers
     */
    public void close();

    /**
     * This method will clear the data sources from all loaded options.
     * The data source will be back to what is was before the initialiseContext was called.
     * To re-use the data source after calling the clear() method, the data source needs to be re-initialised with
     * initialiseContext(Map<String, Object> options).
     * Any provided inputStream or reader will not be closed and will have to be closed separately.
     */
    public void clear();
}
