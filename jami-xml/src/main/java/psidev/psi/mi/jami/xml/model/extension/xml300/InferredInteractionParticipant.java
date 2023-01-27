package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.AbstractInferredInteractionParticipant;
import psidev.psi.mi.jami.xml.model.reference.AbstractFeatureRef;
import psidev.psi.mi.jami.xml.model.reference.xml300.AbstractParticipantRef;

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
public class InferredInteractionParticipant extends AbstractInferredInteractionParticipant {

    /**
     * <p>Constructor for InferredInteractionParticipant.</p>
     */
    public InferredInteractionParticipant(){
    }

    /**
     * Sets the value of the participantFeatureRef property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer}
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "participantFeatureRef")
    public void setJAXBParticipantFeatureRef(Integer value) {
        if (value != null){
            super.setJAXBParticipantFeatureRef(new FeatureRef(value));
        }
    }

    /**
     * Sets the value of the participantRef property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer}
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "participantRef")
    public void setJAXBParticipantRef(Integer value) {
        if (value != null){
            super.setJAXBParticipantRef(new ParticipantRef(value));
        }
    }

    ////////////////////////////////////////////////////////////////// classes

    private class ParticipantRef extends AbstractParticipantRef{
        public ParticipantRef(int ref) {
            super(ref);
        }

        public boolean resolve(PsiXmlIdCache parsedObjects) {
            if (parsedObjects.containsParticipant(this.ref)){
                Entity p = parsedObjects.getParticipant(this.ref);
                if (p != null){
                    setParticipant(p);
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Participant Reference in inferred Participant: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
        }

        @Override
        protected void initialiseParticipantDelegate() {
            XmlModelledParticipant modelled = new XmlModelledParticipant();
            modelled.setId(this.ref);
            setDelegate(modelled);
        }

        public FileSourceLocator getSourceLocator() {
            return getInferredParticipantLocator();
        }

        public void setSourceLocator(FileSourceLocator locator) {
            throw new UnsupportedOperationException("Cannot set the source locator of a participant ref");
        }
    }

    private class FeatureRef extends AbstractFeatureRef {
        public FeatureRef(int ref) {
            super(ref);
        }

        public boolean resolve(PsiXmlIdCache parsedObjects) {
            if (parsedObjects.containsFeature(this.ref)){
                Feature f = parsedObjects.getFeature(this.ref);
                if (f != null){
                    setFeature(f);
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Feature Reference in inferred participant: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
        }

        @Override
        protected void initialiseFeatureDelegate() {
            XmlFeature modelled = new XmlFeature();
            modelled.setId(this.ref);
            setDelegate(modelled);
        }

        public FileSourceLocator getSourceLocator() {
            return getInferredParticipantLocator();
        }

        public void setSourceLocator(FileSourceLocator locator) {
            throw new UnsupportedOperationException("Cannot set the source locator of a feature ref");
        }
    }
}
