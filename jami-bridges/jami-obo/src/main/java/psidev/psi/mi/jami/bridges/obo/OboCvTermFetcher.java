package psidev.psi.mi.jami.bridges.obo;

import psidev.psi.mi.jami.bridges.fetcher.CvTermFetcher;
import psidev.psi.mi.jami.model.CvTerm;

/**
 * Simple fetcher based on OBO files
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/07/13</pre>
 */
public class OboCvTermFetcher extends OboFetcherTemplate<CvTerm> implements CvTermFetcher<CvTerm>{

    /**
     * <p>Constructor for OboCvTermFetcher.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param filePath a {@link java.lang.String} object.
     */
    public OboCvTermFetcher(CvTerm database, String filePath) {
        super(database, new CvOboLoader(database), filePath);
    }

    /**
     * <p>Constructor for OboCvTermFetcher.</p>
     *
     * @param databaseName a {@link java.lang.String} object.
     * @param filePath a {@link java.lang.String} object.
     */
    public OboCvTermFetcher(String databaseName, String filePath) {
        super(databaseName, new CvOboLoader(databaseName), filePath);
    }
}
