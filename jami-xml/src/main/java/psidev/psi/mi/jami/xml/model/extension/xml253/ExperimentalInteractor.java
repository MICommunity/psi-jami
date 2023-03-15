package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.AbstractExperimentalInteractor;
import psidev.psi.mi.jami.xml.model.reference.xml253.AbstractExperimentRef;
import psidev.psi.mi.jami.xml.model.reference.xml253.AbstractInteractorRef;

import javax.xml.bind.annotation.*;
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
public class ExperimentalInteractor extends AbstractExperimentalInteractor {

    /**
     * <p>Constructor for ExperimentalInteractor.</p>
     */
    public ExperimentalInteractor() {
        super();
    }

    /**
     * <p>getExperiments.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Experiment> getExperiments() {
        if (getJaxbExperimentRefWrapper() == null){
            setJAXBExperimentRefWrapper(new JAXBExperimentRefWrapper());
        }
        return getJaxbExperimentRefWrapper().getExperiments();
    }

    /**
     * Sets the value of the interactor property.
     *
     * @param value
     *     allowed object is
     *     {@link psidev.psi.mi.jami.model.Interactor}
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "interactor")
    public void setJAXBInteractor(DefaultXmlInteractor value) {
        super.setJAXBInteractor(value, PsiXmlVersion.v2_5_3);
    }

    /**
     * Sets the value of the interactorRef property.
     *
     * @param value
     *     allowed object is
     *     {@link java.lang.Integer}
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "interactorRef")
    public void setJAXBInteractorRef(Integer value) {
        if (value != null){
            super.setInteractor(new InteractorRef(value));
        }
    }

    /**
     * Gets the value of the experimentRefList property.
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml253.ExperimentalInteractor.JAXBExperimentRefWrapper} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name="experimentRefList")
    public void setJAXBExperimentRefWrapper(JAXBExperimentRefWrapper wrapper) {
        super.setJAXBExperimentRefWrapper(wrapper);
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name = "interactorExperimentRefList")
    public static class JAXBExperimentRefWrapper extends AbstractJAXBExperimentRefWrapper {

        public JAXBExperimentRefWrapper(){
            super();
        }

        @XmlElement(namespace = "net:sf:psidev:mi", name = "experimentRef", type = Integer.class, required = true)
        public List<Integer> getJAXBExperimentRefs() {
            if (super.getJAXBExperimentRefs() == null){
                setJaxbExperimentRefs(new JAXBExperimentRefList());
            }
            return super.getJAXBExperimentRefs();
        }

        //////////////////////////////////////////////////////////////
        /**
         * The experiment ref list used by JAXB to populate experiment refs
         */
        private class JAXBExperimentRefList extends ArrayList<Integer>{

            public JAXBExperimentRefList(){
                super();
                setExperiments(new ArrayList<Experiment>());
            }

            @Override
            public boolean add(Integer val) {
                if (val == null){
                    return false;
                }
                return getExperiments().add(new ExperimentRef(val));
            }

            @Override
            public boolean addAll(Collection<? extends Integer> c) {
                if (c == null){
                    return false;
                }
                boolean added = false;

                for (Integer a : c){
                    if (add(a)){
                        added = true;
                    }
                }
                return added;
            }

            @Override
            public void add(int index, Integer element) {
                addToSpecificIndex(index, element);
            }

            @Override
            public boolean addAll(int index, Collection<? extends Integer> c) {
                int newIndex = index;
                if (c == null){
                    return false;
                }
                boolean add = false;
                for (Integer a : c){
                    if (addToSpecificIndex(newIndex, a)){
                        newIndex++;
                        add = true;
                    }
                }
                return add;
            }

            private boolean addToSpecificIndex(int index, Integer val) {
                if (val == null){
                    return false;
                }
                getExperiments().add(index, new ExperimentRef(val));
                return true;
            }

            @Override
            public String toString() {
                return "Experimental Interactor Experiment List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
            }
        }

        /**
         * Experiment ref for experimental interactor
         */
        private class ExperimentRef extends AbstractExperimentRef{
            public ExperimentRef(int ref) {
                super(ref);
            }

            public boolean resolve(PsiXmlIdCache parsedObjects) {
                if (parsedObjects.containsExperiment(this.ref)){
                    Experiment obj = parsedObjects.getExperiment(this.ref);
                    if (obj != null){
                        getExperiments().remove(this);
                        getExperiments().add(obj);
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String toString() {
                return "Experimental Interactor Experiment Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
            }

            public FileSourceLocator getSourceLocator() {
                return JAXBExperimentRefWrapper.this.getSourceLocator();
            }

            public void setSourceLocator(FileSourceLocator locator) {
                throw new UnsupportedOperationException("Cannot set the source locator of an experiment ref");
            }
        }
    }

    private class InteractorRef extends AbstractInteractorRef {
        public InteractorRef(int ref) {
            super(ref);
        }

        @Override
        public boolean resolve(PsiXmlIdCache parsedObjects) {
            if (parsedObjects.containsInteractor(this.ref)){
                setInteractor(parsedObjects.getInteractor(this.ref));
                return true;
            }
            return false;
        }

        @Override
        public String toString() {
            return "Experimental Interactor Reference: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
        }

        public FileSourceLocator getSourceLocator() {
            return ExperimentalInteractor.this.getSourceLocator();
        }

        public void setSourceLocator(FileSourceLocator locator) {
            throw new UnsupportedOperationException("Cannot set the source locator of an interactor ref");
        }
    }
}
