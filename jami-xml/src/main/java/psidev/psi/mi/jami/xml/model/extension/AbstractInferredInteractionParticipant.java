package psidev.psi.mi.jami.xml.model.extension;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;

/**
 * Participant of the inferred interaction.
 *
 * <p>Java class for inferredInteractionParticipant complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="inferredInteractionParticipant"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;choice&gt;
 *         &lt;elements name="participantRef" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;elements name="participantFeatureRef" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *       &lt;/choice&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *

 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
public abstract class AbstractInferredInteractionParticipant implements FileSourceContext, Locatable {

    private Feature feature;
    private Entity participant;
    private PsiXmlLocator sourceLocator;
    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for InferredInteractionParticipant.</p>
     */
    public AbstractInferredInteractionParticipant(){
    }

    /**
     * <p>Getter for the field <code>feature</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Feature} object.
     */
    public Feature getFeature() {
        return feature;
    }

    /**
     * <p>Getter for the field <code>participant</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Entity} object.
     */
    public Entity getParticipant() {
        return participant;
    }

    /**
     * <p>Setter for the field <code>feature</code>.</p>
     *
     * @param feature a {@link psidev.psi.mi.jami.model.Feature} object.
     */
    public void setFeature(Feature feature) {
        this.feature = feature;
    }

    /**
     * <p>Setter for the field <code>participant</code>.</p>
     *
     * @param participant a {@link psidev.psi.mi.jami.model.Entity} object.
     */
    public void setParticipant(Entity participant) {
        this.participant = participant;
    }

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        if (sourceLocator == null && locator != null){
            sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
        }
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    /**
     * Sets the value of the participantFeatureRef property.
     *
     * @param feature
     *     allowed object is
     *     {@link psidev.psi.mi.jami.model.Feature}
     */
    public void setJAXBParticipantFeatureRef(Feature feature) {
        this.feature = feature;
    }

    /**
     * Sets the value of the participantRef property.
     *
     * @param participant
     *     allowed object is
     *     {@link psidev.psi.mi.jami.model.Entity}
     */
    public void setJAXBParticipantRef(Entity participant) {
        this.participant = participant;
    }

    protected FileSourceLocator getInferredParticipantLocator(){
        return sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Inferred participant: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }
}
