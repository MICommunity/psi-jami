package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Publication;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Xml im
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25/07/13</pre>
 */
@XmlRootElement(namespace = "net:sf:psidev:mi", name = "experimentDescription")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlExperiment extends AbstractXmlExperiment {

    /**
     * <p>Constructor for XmlExperiment.</p>
     */
    public XmlExperiment() {
    }

    /**
     * <p>Constructor for XmlExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public XmlExperiment(Publication publication) {
        super(publication);
    }

    /**
     * <p>Constructor for XmlExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlExperiment(Publication publication, CvTerm interactionDetectionMethod) {
        super(publication, interactionDetectionMethod);
    }

    /**
     * <p>Constructor for XmlExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlExperiment(Publication publication, CvTerm interactionDetectionMethod, Organism organism) {
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
