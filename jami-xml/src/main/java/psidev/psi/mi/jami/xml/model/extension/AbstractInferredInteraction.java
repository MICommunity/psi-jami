package psidev.psi.mi.jami.xml.model.extension;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.xml.XmlEntryContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>Java class for inferredInteraction complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="inferredInteraction"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;elements name="participant" type="{http://psi.hupo.org/mi/mif}inferredInteractionParticipant" maxOccurs="unbounded" minOccurs="2"/&gt;
 *         &lt;elements name="experimentRefList" type="{http://psi.hupo.org/mi/mif}experimentRefList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 *

 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlTransient
public abstract class AbstractInferredInteraction implements FileSourceContext, Locatable {

    private List<AbstractInferredInteractionParticipant> participants;
    private PsiXmlLocator sourceLocator;
    @XmlLocation
    @XmlTransient
    private Locator locator;
    private AbstractJAXBExperimentRefWrapper jaxbExperimentRefWrapper;

    /**
     * <p>Constructor for InferredInteraction.</p>
     */
    public AbstractInferredInteraction() {
        XmlEntryContext.getInstance().registerInferredInteraction(this);
    }

    /**
     * <p>getExperiments.</p>
     *
     * @return a {@link Collection} object.
     */
    public abstract Collection<Experiment> getExperiments();

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link FileSourceLocator} object.
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
     * Gets the value of the participants property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the participants property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLinkedFeatures().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AbstractInferredInteractionParticipant}
     *
     * @return a {@link List} object.
     */
    public List<AbstractInferredInteractionParticipant> getParticipants() {
        return this.participants;
    }

    public void setParticipants(List<AbstractInferredInteractionParticipant> participants) {
        this.participants = participants;
    }

    public AbstractJAXBExperimentRefWrapper getJaxbExperimentRefWrapper() {
        return jaxbExperimentRefWrapper;
    }

    /**
     * Gets the value of the experimentRefList property.
     *
     * @param wrapper a {@link AbstractInferredInteraction.AbstractJAXBExperimentRefWrapper} object.
     */
    public void setJAXBExperimentRefWrapper(AbstractJAXBExperimentRefWrapper wrapper) {
        this.jaxbExperimentRefWrapper = wrapper;
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    public static abstract class AbstractJAXBExperimentRefWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Integer> jaxbExperimentRefs;
        protected List<Experiment> experiments;

        public AbstractJAXBExperimentRefWrapper(){
            experiments = new ArrayList<Experiment>();
        }

        @Override
        public Locator sourceLocation() {
            return (Locator)getSourceLocator();
        }

        public FileSourceLocator getSourceLocator() {
            if (sourceLocator == null && locator != null){
                sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
            }
            return sourceLocator;
        }

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

        public List<Experiment> getExperiments() {
            return experiments;
        }

        public List<Integer> getJAXBExperimentRefs() {
            return jaxbExperimentRefs;
        }

        public void setJaxbExperimentRefs(List<Integer> jaxbExperimentRefs) {
            this.jaxbExperimentRefs = jaxbExperimentRefs;
        }

        @Override
        public String toString() {
            return "Inferred Interaction: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

}
