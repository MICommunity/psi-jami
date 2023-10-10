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
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * <p>Java class for experimentalInteractor complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="experimentalInteractor"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;choice&gt;
 *           &lt;elements name="interactorRef" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *           &lt;elements name="interactor" type="{http://psi.hupo.org/mi/mif}interactor"/&gt;
 *         &lt;/choice&gt;
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
public abstract class AbstractExperimentalInteractor implements FileSourceContext, Locatable {
    private Interactor interactor;
    private XmlInteractorFactory interactorFactory;
    private PsiXmlLocator sourceLocator;
    @XmlLocation
    @XmlTransient
    private Locator locator;

    private AbstractJAXBExperimentRefWrapper jaxbExperimentRefWrapper;

    /**
     * <p>Constructor for AbstractExperimentalInteractor.</p>
     */
    public AbstractExperimentalInteractor() {
        this.interactorFactory =  XmlEntryContext.getInstance().getInteractorFactory();
    }

    public abstract Collection<Experiment> getExperiments();

    /**
     * <p>Getter for the field <code>interactor</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public Interactor getInteractor() {
        return this.interactor;
    }

    /**
     * <p>Setter for the field <code>interactor</code>.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public void setInteractor(Interactor interactor) {
        this.interactor = interactor;
    }

    public AbstractJAXBExperimentRefWrapper getJaxbExperimentRefWrapper() {
        return jaxbExperimentRefWrapper;
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
     * Sets the value of the interactor property.
     *
     * @param value
     *     allowed object is
     *     {@link psidev.psi.mi.jami.model.Interactor}
     */
    public void setJAXBInteractor(AbstractBaseXmlInteractor value, PsiXmlVersion version) {
        if (value == null){
            this.interactor = null;
        }
        else{
            this.interactor = this.interactorFactory.createInteractorFromXmlInteractorInstance(value, version);
        }
    }

    /**
     * Gets the value of the experimentRefList property.
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.AbstractExperimentalInteractor.AbstractJAXBExperimentRefWrapper} object.
     */
    public void setJAXBExperimentRefWrapper(AbstractJAXBExperimentRefWrapper wrapper) {
        this.jaxbExperimentRefWrapper = wrapper;
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlTransient
    public static abstract class AbstractJAXBExperimentRefWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private ArrayList<Integer> jaxbExperimentRefs;
        private List<Experiment> experiments;

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
            else{
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        public List<Integer> getJAXBExperimentRefs() {
            return jaxbExperimentRefs;
        }

        public void setJaxbExperimentRefs(ArrayList<Integer> jaxbExperimentRefs) {
            this.jaxbExperimentRefs = jaxbExperimentRefs;
        }

        public void setExperiments(List<Experiment> experiments) {
            this.experiments = experiments;
        }

        public List<Experiment> getExperiments() {
            return experiments;
        }

        @Override
        public String toString() {
            return "Experimental Interactor: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}
