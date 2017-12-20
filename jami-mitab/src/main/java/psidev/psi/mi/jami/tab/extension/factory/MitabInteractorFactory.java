package psidev.psi.mi.jami.tab.extension.factory;

import psidev.psi.mi.jami.factory.DefaultInteractorFactory;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.tab.extension.*;

import java.util.Collection;
import java.util.Iterator;

/**
 * The MITAB extension of the InteractorFactory
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/06/13</pre>
 */
public class MitabInteractorFactory extends DefaultInteractorFactory {

    /**
     * <p>Constructor for MitabInteractorFactory.</p>
     */
    public MitabInteractorFactory() {
        super();
    }

    /** {@inheritDoc} */
    @Override
    public MitabProtein createProtein(String name, CvTerm type) {
        return new MitabProtein(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public MitabNucleicAcid createNucleicAcid(String name, CvTerm type) {
        return new MitabNucleicAcid(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public MitabGene createGene(String name) {
        return new MitabGene(name);
    }

    /** {@inheritDoc} */
    @Override
    public MitabComplex createComplex(String name, CvTerm type) {
        return new MitabComplex(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public MitabBioactiveEntity createBioactiveEntity(String name, CvTerm type) {
        return new MitabBioactiveEntity(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public MitabPolymer createPolymer(String name, CvTerm type) {
        return new MitabPolymer(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public MitabInteractor createInteractor(String name, CvTerm type) {
        return new MitabInteractor(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public MitabInteractorPool createInteractorSet(String name, CvTerm type) {
        return new MitabInteractorPool(name, type);
    }

    /**
     * Return the proper instance of the interactor if at least one type is recognized (always take the first recognized type). It returns null otherwise.
     *
     * @param types a {@link java.util.Collection} object.
     * @param name a {@link java.lang.String} object.
     * @return the proper instance of the interactor if at least one type (always take the first recognized type) is recognized. It returns null otherwise.
     */
    public Interactor createInteractorFromInteractorTypes(Collection<MitabCvTerm> types, String name){

        Interactor interactor = null;
        Iterator<MitabCvTerm> typesIterator = types.iterator();
        while (interactor == null && typesIterator.hasNext()){
            interactor = createInteractorFromInteractorType(typesIterator.next(), name);
        }

        return interactor;
    }
}
