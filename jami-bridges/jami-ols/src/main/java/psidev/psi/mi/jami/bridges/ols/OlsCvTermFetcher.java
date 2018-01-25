package psidev.psi.mi.jami.bridges.ols;


import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 08/05/13

 */
public class OlsCvTermFetcher extends AbstractOlsFetcher<CvTerm>{


    /**
     * <p>Constructor for OlsCvTermFetcher.</p>
     *
     * @throws psidev.psi.mi.jami.bridges.exception.BridgeFailedException if any.
     */
    public OlsCvTermFetcher() throws BridgeFailedException {
        super();
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm instantiateCvTerm(String termName, Xref identity, String ontologyName) {
        return new LazyCvTerm(olsClient, termName, identity, ontologyName);
    }

}
