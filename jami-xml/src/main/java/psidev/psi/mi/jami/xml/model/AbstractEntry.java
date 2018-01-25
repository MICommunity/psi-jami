package psidev.psi.mi.jami.xml.model;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.model.extension.*;
import psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstract class for Entry
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/11/13</pre>
 */
@XmlTransient
public abstract class AbstractEntry<T extends Interaction> extends Entry implements Locatable, FileSourceContext {
    private PsiXmlLocator sourceLocator;
    private JAXBInteractorsWrapper interactorsWrapper;
    private JAXBInteractionsWrapper<T> interactionsWrapper;
    private JAXBAnnotationsWrapper annotationsWrapper;

    /**
     * <p>Constructor for AbstractEntry.</p>
     */
    public AbstractEntry() {
        super();
        XmlEntryContext.getInstance().setCurrentSource(this);
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
     * <p>getInteractors.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Interactor> getInteractors(){
        return this.interactorsWrapper != null ? this.interactorsWrapper.interactors : Collections.EMPTY_LIST;
    }

    /**
     * <p>getInteractions.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<T> getInteractions(){
        return this.interactionsWrapper != null ? this.interactionsWrapper.interactions : Collections.EMPTY_LIST;
    }

    /** {@inheritDoc} */
    @Override
    public boolean hasLoadedFullEntry() {
        return true;
    }

    /** {@inheritDoc} */
    @Override
    public void setHasLoadedFullEntry(boolean hasLoadedFullEntry) {
        if (!hasLoadedFullEntry){
            throw new UnsupportedOperationException("An abstract entry is always fully loaded because full JAXB bindings, no pull parsing");
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Entry : "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }

    /**
     * <p>Setter for the field <code>interactorsWrapper</code>.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.AbstractEntry.JAXBInteractorsWrapper} object.
     */
    protected void setInteractorsWrapper(JAXBInteractorsWrapper wrapper){
        this.interactorsWrapper = wrapper;
    }

    /**
     * <p>Setter for the field <code>interactionsWrapper</code>.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.AbstractEntry.JAXBInteractionsWrapper} object.
     */
    protected void setInteractionsWrapper(JAXBInteractionsWrapper wrapper){
        this.interactionsWrapper = wrapper;
        if (this.interactionsWrapper != null){
            for (T interaction : this.interactionsWrapper.interactions){
                ((PsiXmlInteraction)interaction).setEntry(this);
            }
        }
    }

    /**
     * <p>Setter for the field <code>annotationsWrapper</code>.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.AbstractEntry.JAXBAnnotationsWrapper} object.
     */
    protected void setAnnotationsWrapper(JAXBAnnotationsWrapper wrapper){
        this.annotationsWrapper = wrapper;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseAnnotations() {
        super.initialiseAnnotationsWith(this.annotationsWrapper != null ? this.annotationsWrapper.annotations : null);
    }

    //////////////////////////////////////////////////// Inner classes
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="interactorsWrapper")
    public static class JAXBInteractorsWrapper implements Locatable, FileSourceContext {
        private List<Interactor> interactors;
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;

        public JAXBInteractorsWrapper(){
            initialiseInteractors();
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

        protected void initialiseInteractors(){
            interactors = new InteractorList();
        }

        @XmlElement(type=XmlInteractor.class, name="interactor", required = true)
        public List<Interactor> getJAXBInteractors() {
            return interactors;
        }

        @Override
        public String toString() {
            return "Entry interactor List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }

        private class InteractorList extends AbstractListHavingProperties<Interactor> {
            XmlInteractorFactory interactorFactory = XmlEntryContext.getInstance().getInteractorFactory();

            @Override
            protected void processAddedObjectEvent(Interactor added) {
                // register new interactor instance according to type
                this.interactorFactory.createInteractorFromXmlInteractorInstance((AbstractXmlInteractor)added);
            }

            @Override
            protected void processRemovedObjectEvent(Interactor removed) {
                // nothing
            }

            @Override
            protected void clearProperties() {
                // nothing
            }
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="entryAnnotationsWrapper")
    public static class JAXBAnnotationsWrapper implements Locatable, FileSourceContext {
        private List<Annotation> annotations;
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;

        public JAXBAnnotationsWrapper(){
            initialiseAnnotations();
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

        protected void initialiseAnnotations(){
            annotations = new ArrayList<Annotation>();
        }

        @XmlElement(type=XmlAnnotation.class, name="attribute", required = true)
        public List<Annotation> getJAXBAttributes() {
            return annotations;
        }

        @Override
        public String toString() {
            return "Entry attribute List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name="interactionsWrapper")
    public static class JAXBInteractionsWrapper<T extends Object> implements Locatable, FileSourceContext {
        private List<T> interactions;
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;

        public JAXBInteractionsWrapper(){
            initialiseInteractions();
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

        protected void initialiseInteractions(){
            interactions = new ArrayList<T>();
        }

        public List<T> getJAXBInteractions() {
            return interactions;
        }

        @Override
        public String toString() {
            return "Entry interaction List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}
