package psidev.psi.mi.jami.xml.model.xml253;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.InteractionEvidence;

import javax.xml.bind.annotation.*;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultAvailability;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlExperiment;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlSource;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlSource;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Entry implementation for JAXB read only
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/11/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class ExperimentalEntry extends AbstractEntry<InteractionEvidence> {
    private JAXBExperimentsWrapper experimentsWrapper;
    private JAXBAvailabilitiesWrapper availabilitiesWrapper;

    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>getExperiments.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Experiment> getExperiments(){
        return this.experimentsWrapper != null ? this.experimentsWrapper.experiments : Collections.EMPTY_LIST;
    }
    /**
     * <p>setJAXBSource.</p>
     *
     * @param source a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlSource} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "source", type = DefaultXmlSource.class)
    public void setJAXBSource(ExtendedPsiXmlSource source) {
        super.setSource(source);
    }

    /**
     * <p>setJAXBAvailabilityWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.xml253.ExperimentalEntry.JAXBAvailabilitiesWrapper} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "availabilityList")
    public void setJAXBAvailabilityWrapper(JAXBAvailabilitiesWrapper wrapper) {
        this.availabilitiesWrapper = wrapper;
    }

    /**
     * <p>setJAXBExperimentWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.xml253.ExperimentalEntry.JAXBExperimentsWrapper} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "experimentList")
    public void setJAXBExperimentWrapper(JAXBExperimentsWrapper wrapper){
        this.experimentsWrapper = wrapper;
    }

    /**
     * <p>setJAXBInteractorsWrapper.</p>
     *
     * @param wrapper a JAXBInteractorsWrapper object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "interactorList")
    public void setJAXBInteractorsWrapper(JAXBInteractorsWrapper wrapper){
        super.setInteractorsWrapper(wrapper);
    }

    /**
     * <p>setJAXBInteractionsWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.xml253.ExperimentalEntry.JAXBInteractionsWrapper} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "interactionList", required = true)
    public void setJAXBInteractionsWrapper(JAXBInteractionsWrapper wrapper){
        super.setInteractionsWrapper(wrapper);
    }

    /**
     * <p>setJAXBAnnotationWrapper.</p>
     *
     * @param wrapper a JAXBAnnotationsWrapper object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "attributeList")
    public void setJAXBAnnotationWrapper(JAXBAnnotationsWrapper wrapper) {
        super.setAnnotationsWrapper(wrapper);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        if (super.getSourceLocator() == null && locator != null){
            super.setSourceLocator(new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null));
        }
        return super.getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            super.setSourceLocator(null);
        }
        else{
            super.setSourceLocator(new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null));
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseAvailabilities() {
        super.initialiseAvailabilitiesWith(this.availabilitiesWrapper != null ? this.availabilitiesWrapper.availabilities : null);
    }

    //////////////////////////////// class wrapper

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name="entryAvailabilitiesWrapper")
    public static class JAXBAvailabilitiesWrapper implements Locatable, FileSourceContext {
        private List<AbstractAvailability> availabilities;
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;

        public JAXBAvailabilitiesWrapper(){
            initialiseAvailabilities();
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

        protected void initialiseAvailabilities(){
            availabilities = new ArrayList<AbstractAvailability>();
        }

        @XmlElement(namespace = "net:sf:psidev:mi", type= DefaultAvailability.class, name="availability", required = true)
        public List<AbstractAvailability> getJAXBAvailabilities() {
            return availabilities;
        }

        @Override
        public String toString() {
            return "Entry availability List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name="experimentsWrapper")
    public static class JAXBExperimentsWrapper implements Locatable, FileSourceContext {
        private List<Experiment> experiments;
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;

        public JAXBExperimentsWrapper(){
            initialiseExperiments();
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

        protected void initialiseExperiments(){
            experiments = new ArrayList<Experiment>();
        }

        @XmlElement(namespace = "net:sf:psidev:mi", type= DefaultXmlExperiment.class, name="experimentDescription", required = true)
        public List<Experiment> getJAXBExperiments() {
            return experiments;
        }

        @Override
        public String toString() {
            return "Entry experiment List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name="interactionEvidencesWrapper")
    public static class JAXBInteractionsWrapper extends AbstractEntry.JAXBInteractionsWrapper<InteractionEvidence>{

        public JAXBInteractionsWrapper(){
            super();
        }

        @XmlElement(namespace = "net:sf:psidev:mi", type= DefaultXmlInteractionEvidence.class, name="interaction", required = true)
        public List<InteractionEvidence> getJAXBInteractions() {
            return super.getJAXBInteractions();
        }
    }
}
