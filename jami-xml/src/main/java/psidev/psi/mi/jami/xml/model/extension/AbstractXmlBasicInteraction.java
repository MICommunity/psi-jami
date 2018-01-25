package psidev.psi.mi.jami.xml.model.extension;

import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Participant;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Xml implementation of interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractXmlBasicInteraction extends AbstractPsiXmlInteraction<Participant> {

    @XmlLocation
    @XmlTransient
    protected Locator locator;

    /**
     * <p>Constructor for AbstractXmlBasicInteraction.</p>
     */
    public AbstractXmlBasicInteraction() {
        super();
    }

    /**
     * <p>Constructor for AbstractXmlBasicInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public AbstractXmlBasicInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for AbstractXmlBasicInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBasicInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        if (super.getSourceLocator() == null && locator != null){
            super.setSourceLocator(new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), getId()));
        }
        return super.getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name = "names")
    public void setInteractionNamesContainer(NamesContainer value) {
        super.setInteractionNamesContainer(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name = "xref")
    public void setInteractionXrefContainer(InteractionXrefContainer value) {
        super.setInteractionXrefContainer(value);
    }

    /** {@inheritDoc} */
    @Override
    public boolean isIntraMolecular() {
        return super.isIntraMolecular();
    }

    /** {@inheritDoc} */
    @Override
    public void setIntraMolecular(boolean intra) {
        super.setIntraMolecular(intra);
    }

    /**
     * <p>setJAXBIntraMolecular.</p>
     *
     * @param intra a {@link java.lang.Boolean} object.
     */
    @XmlElement(name = "intraMolecular", defaultValue = "false", type = Boolean.class)
    public void setJAXBIntraMolecular(Boolean intra) {
        super.setIntraMolecular(intra);
    }

    /**
     * <p>setJAXBId.</p>
     *
     * @param value a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(int value) {
        super.setId(value);
    }

    /**
     * <p>setJAXBImexId.</p>
     *
     * @param value a {@link java.lang.String} object.
     */
    @XmlAttribute(name = "imexId")
    public void setJAXBImexId(String value) {
        super.assignImexId(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        super.setJAXBAttributeWrapper(jaxbAttributeWrapper);
    }

    /**
     * <p>setJAXBParticipantWrapper.</p>
     *
     * @param jaxbParticipantWrapper a {@link psidev.psi.mi.jami.xml.model.extension.AbstractXmlBasicInteraction.JAXBParticipantWrapper} object.
     */
    @XmlElement(name="participantList", required = true)
    public void setJAXBParticipantWrapper(JAXBParticipantWrapper jaxbParticipantWrapper) {
        super.setParticipantWrapper(jaxbParticipantWrapper);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name="inferredInteractionList")
    public void setJAXBInferredInteractionWrapper(JAXBInferredInteractionWrapper jaxbInferredWrapper) {
        super.setJAXBInferredInteractionWrapper(jaxbInferredWrapper);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(name="interactionType", type = XmlCvTerm.class)
    public List<CvTerm> getInteractionTypes() {
        return super.getInteractionTypes();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWrapper() {
        super.setParticipantWrapper(new JAXBParticipantWrapper());
    }

    ////////////////////////////////////////////////////// classes
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="basicParticipantWrapper")
    public static class JAXBParticipantWrapper extends AbstractPsiXmlInteraction.JAXBParticipantWrapper<Participant> {

        public JAXBParticipantWrapper(){
            super();
        }

        @XmlElement(type=XmlParticipant.class, name="participant", required = true)
        public List<Participant> getJAXBParticipants() {
            return super.getJAXBParticipants();
        }
    }
}
