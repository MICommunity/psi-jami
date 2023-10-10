package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Publication;

/**
 * Xml im
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif", name = "defaultExperiment")
public class DefaultXmlExperiment extends AbstractXmlExperiment {

    /**
     * <p>Constructor for DefaultXmlExperiment.</p>
     */
    public DefaultXmlExperiment() {
    }

    /**
     * <p>Constructor for DefaultXmlExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public DefaultXmlExperiment(Publication publication) {
        super(publication);
    }

    /**
     * <p>Constructor for DefaultXmlExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultXmlExperiment(Publication publication, CvTerm interactionDetectionMethod) {
        super(publication, interactionDetectionMethod);
    }

    /**
     * <p>Constructor for DefaultXmlExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultXmlExperiment(Publication publication, CvTerm interactionDetectionMethod, Organism organism) {
        super(publication, interactionDetectionMethod, organism);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFullNameFromPublication(BibRef publication) {
        if (getFullName() == null && publication.getTitle() != null) {
            setFullName(publication.getTitle());
        }
    }
}
