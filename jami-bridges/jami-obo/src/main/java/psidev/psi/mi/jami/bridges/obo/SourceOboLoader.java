package psidev.psi.mi.jami.bridges.obo;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultSource;
import psidev.psi.mi.jami.utils.AnnotationUtils;

/**
 * Implementation of Obo loader for sources
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/07/13</pre>
 */
public class SourceOboLoader extends AbstractOboLoader<Source> {
    /**
     * <p>Constructor for SourceOboLoader.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public SourceOboLoader(CvTerm database) {
        super(database);
    }

    /**
     * <p>Constructor for SourceOboLoader.</p>
     *
     * @param databaseName a {@link java.lang.String} object.
     */
    public SourceOboLoader(String databaseName) {
        super(databaseName);
    }

    /** {@inheritDoc} */
    @Override
    protected Source instantiateNewTerm(String name, Xref identity) {
        return new DefaultSource("", name, identity);
    }

    /** {@inheritDoc} */
    @Override
    protected void createDefinitionFor(String def, Source term) {
        term.getAnnotations().add(AnnotationUtils.createAnnotation("definition", def));
    }
}
