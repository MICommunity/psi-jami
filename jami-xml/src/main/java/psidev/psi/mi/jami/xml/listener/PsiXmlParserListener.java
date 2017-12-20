package psidev.psi.mi.jami.xml.listener;

import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.xml.model.reference.XmlIdReference;

import java.util.Collection;

/**
 * A listener listening to events when parsing a mitab file
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/06/13</pre>
 */
public interface PsiXmlParserListener extends MIFileParserListener{

    /**
     * <p>onUnresolvedReference.</p>
     *
     * @param ref a {@link psidev.psi.mi.jami.xml.model.reference.XmlIdReference} object.
     * @param message a {@link java.lang.String} object.
     */
    public void onUnresolvedReference(XmlIdReference ref, String message);

    /**
     * <p>onSeveralHostOrganismFound.</p>
     *
     * @param organisms a {@link java.util.Collection} object.
     * @param locator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public void onSeveralHostOrganismFound(Collection<Organism> organisms, FileSourceLocator locator);

    /**
     * <p>onSeveralExpressedInOrganismFound.</p>
     *
     * @param organisms a {@link java.util.Collection} object.
     * @param locator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public void onSeveralExpressedInOrganismFound(Collection<Organism> organisms, FileSourceLocator locator);

    /**
     * <p>onSeveralExperimentalRolesFound.</p>
     *
     * @param roles a {@link java.util.Collection} object.
     * @param locator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public void onSeveralExperimentalRolesFound(Collection<CvTerm> roles, FileSourceLocator locator);

    /**
     * <p>onSeveralExperimentsFound.</p>
     *
     * @param experiments a {@link java.util.Collection} object.
     * @param locator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public void onSeveralExperimentsFound(Collection<Experiment> experiments, FileSourceLocator locator);
}
