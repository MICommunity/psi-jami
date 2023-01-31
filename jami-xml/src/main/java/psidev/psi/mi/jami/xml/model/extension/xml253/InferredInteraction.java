package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.AbstractInferredInteraction;
import psidev.psi.mi.jami.xml.model.extension.AbstractInferredInteractionParticipant;
import psidev.psi.mi.jami.xml.model.reference.xml253.AbstractExperimentRef;

import javax.xml.bind.annotation.*;
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
public class InferredInteraction extends AbstractInferredInteraction {

    /**
     * <p>Constructor for InferredInteraction.</p>
     */
    public InferredInteraction() {
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
     * {@link psidev.psi.mi.jami.xml.model.extension.xml253.InferredInteractionParticipant}
     *
     * @return a {@link java.util.List} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name="participant", type = InferredInteractionParticipant.class, required = true)
    public List<AbstractInferredInteractionParticipant> getParticipants() {
        if (super.getParticipants() == null) {
            setParticipants(new ArrayList<>());
        }
        return super.getParticipants();
    }

    /**
     * Gets the value of the experimentRefList property.
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml253.InferredInteraction.JAXBExperimentRefWrapper} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name="experimentRefList")
    public void setJAXBExperimentRefWrapper(JAXBExperimentRefWrapper wrapper) {
        super.setJAXBExperimentRefWrapper(wrapper);
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "net:sf:psidev:mi", name = "inferredInteractionExperimentRefList")
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

        /**
         * The experiment ref list used by JAXB to populate experiment refs
         */
        private class JAXBExperimentRefList extends ArrayList<Integer> {

            public JAXBExperimentRefList(){
                super();
                experiments = new ArrayList<Experiment>();
            }

            @Override
            public boolean add(Integer val) {
                if (val == null){
                    return false;
                }
                return experiments.add(new ExperimentRef(val));
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
                ((ArrayList<Experiment>)experiments).add(index, new ExperimentRef(val));
                return true;
            }

            @Override
            public String toString() {
                return "Inferred Interaction Experiment List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
            }
        }

        ///////////////////////////////////////////
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
                        experiments.remove(this);
                        experiments.add(obj);
                        return true;
                    }
                }
                return false;
            }

            @Override
            public String toString() {
                return "Inferred Interaction Experiment Reference: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
            }

            public FileSourceLocator getSourceLocator() {
                return JAXBExperimentRefWrapper.this.getSourceLocator();
            }

            public void setSourceLocator(FileSourceLocator locator) {
                throw new UnsupportedOperationException("Cannot set the source locator of an experiment ref");
            }
        }
    }

}
