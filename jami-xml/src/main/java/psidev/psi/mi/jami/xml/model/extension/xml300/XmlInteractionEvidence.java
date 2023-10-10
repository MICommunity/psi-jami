package psidev.psi.mi.jami.xml.model.extension.xml300;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.VariableParameterValueSet;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlExperiment;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.reference.xml300.AbstractExperimentRef;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Xml implementation of InteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlRootElement(namespace = "http://psi.hupo.org/mi/mif300", name = "interaction")
@XmlAccessorType(XmlAccessType.NONE)
public class XmlInteractionEvidence extends AbstractXmlInteractionEvidence implements ExtendedPsiXmlInteractionEvidence {

    private JAXBVariableParameterValueSetWrapper jaxbVariableParameterValueSetWrapper;
    private JAXBCausalRelationshipWrapper jaxbCausalRelationshipWrapper;

    /**
     * <p>Constructor for XmlInteractionEvidence.</p>
     */
    public XmlInteractionEvidence() {
        super();
    }

    /**
     * <p>Constructor for XmlInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public XmlInteractionEvidence(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlInteractionEvidence(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<VariableParameterValueSet> getVariableParameterValues() {
        if (this.jaxbVariableParameterValueSetWrapper == null){
            this.jaxbVariableParameterValueSetWrapper = new JAXBVariableParameterValueSetWrapper();
        }
        return this.jaxbVariableParameterValueSetWrapper.variableValueSets;
    }

    /** {@inheritDoc} */
    @Override
    public List<ExtendedPsiXmlCausalRelationship> getCausalRelationships() {
        if (this.jaxbCausalRelationshipWrapper == null){
            this.jaxbCausalRelationshipWrapper = new JAXBCausalRelationshipWrapper();
        }
        return this.jaxbCausalRelationshipWrapper.causalRelationships;
    }

    /**
     * <p>setJAXBVariableParameterValueSetWrapper.</p>
     *
     * @param jaxbVariableValueList a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlInteractionEvidence.JAXBVariableParameterValueSetWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "experimentalVariableValueList")
    public void setJAXBVariableParameterValueSetWrapper(JAXBVariableParameterValueSetWrapper jaxbVariableValueList) {
        this.jaxbVariableParameterValueSetWrapper = jaxbVariableValueList;
    }

    /**
     * <p>setJAXBCausalRelationshipWrapper.</p>
     *
     * @param jaxbCausalRelationshipWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlInteractionEvidence.JAXBCausalRelationshipWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name="causalRelationshipList")
    public void setJAXBCausalRelationshipWrapper(JAXBCausalRelationshipWrapper jaxbCausalRelationshipWrapper) {
        this.jaxbCausalRelationshipWrapper = jaxbCausalRelationshipWrapper;
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name = "experimentListType")
    public static class JAXBExperimentWrapper implements Locatable, FileSourceContext{

        private List<ExtendedPsiXmlExperiment> jaxbExperiments;
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Experiment> experiments;
        private JAXBExperimentRefList jaxbExperimentRefList;
        private ExtendedPsiXmlInteractionEvidence parent;

        public JAXBExperimentWrapper(){
            this.experiments = new ArrayList<Experiment>();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name="experimentDescription", required = true, type = XmlExperiment.class)
        public List<ExtendedPsiXmlExperiment> getJAXBExperimentDescriptions() {
            if (jaxbExperiments == null){
                jaxbExperiments = new ArrayList<ExtendedPsiXmlExperiment>();
            }
            return jaxbExperiments;
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name="experimentRef", required = true, type = Integer.class)
        public List<Integer> getJAXBExperimentRefs() {
            if (this.jaxbExperimentRefList == null){
                this.jaxbExperimentRefList = new JAXBExperimentRefList();
            }
            return jaxbExperimentRefList;
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

        @Override
        public String toString() {
            return "Interaction Experiment List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }

        ////////////////////////////////////////////////// Inner classes of ExperimentList

        /**
         * The experiment ref list used by JAXB to populate experiment refs
         */
        private class JAXBExperimentRefList extends ArrayList<Integer>{

            public JAXBExperimentRefList(){
                super();
            }

            @Override
            public boolean add(Integer val) {
                if (val == null){
                    return false;
                }
                return experiments.add(new JAXBExperimentRefList.ExperimentRef(val));
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
                experiments.add(index, new JAXBExperimentRefList.ExperimentRef(val));
                return true;
            }

            @Override
            public String toString() {
                return "Interaction Experiment Reference List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
            }

            /**
             * Experiment ref for experimental interactor
             */
            private class ExperimentRef extends AbstractExperimentRef {
                public ExperimentRef(int ref) {
                    super(ref);
                }

                public boolean resolve(PsiXmlIdCache parsedObjects) {
                    if (parsedObjects.containsExperiment(this.ref)){
                        Experiment obj = parsedObjects.getExperiment(this.ref);
                        if (obj == null){
                            return false;
                        }
                        else if (obj instanceof ExtendedPsiXmlExperiment){
                            ExtendedPsiXmlExperiment exp = (ExtendedPsiXmlExperiment)obj;
                            experiments.remove(this);
                            experiments.add(exp);

                            exp.getInteractionEvidences().add(parent);
                            return true;
                        }
                        else {
                            experiments.remove(this);
                            experiments.add(obj);

                            obj.getInteractionEvidences().add(parent);
                            return true;
                        }
                    }
                    return false;
                }

                @Override
                public String toString() {
                    return "Interaction Experiment Reference: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
                }

                public FileSourceLocator getSourceLocator() {
                    return sourceLocator;
                }

                public void setSourceLocator(FileSourceLocator locator) {
                    throw new UnsupportedOperationException("Cannot set the source locator of an experiment ref");
                }
            }
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name = "variableParameterValueSetList")
    public static class JAXBVariableParameterValueSetWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<VariableParameterValueSet> variableValueSets;

        public JAXBVariableParameterValueSetWrapper(){
            initialiseVariableValueSets();
        }

        public JAXBVariableParameterValueSetWrapper(List<VariableParameterValueSet> values){
            this.variableValueSets = values;
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

        protected void initialiseVariableValueSets(){
            this.variableValueSets = new ArrayList<VariableParameterValueSet>();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type=XmlVariableParameterValueSet.class, name="experimentalVariableValues", required = true)
        public List<VariableParameterValueSet> getJAXBVariableParameterValues() {
            return this.variableValueSets;
        }

        @Override
        public String toString() {
            return "Experimental Variable parameter values : "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="experimentalCausalRelationshipWrapper")
    public static class JAXBCausalRelationshipWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<ExtendedPsiXmlCausalRelationship> causalRelationships;

        public JAXBCausalRelationshipWrapper(){
            initialiseCausalRelationships();
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "causalRelationship", type = XmlCausalRelationship.class, required = true)
        public List<ExtendedPsiXmlCausalRelationship> getJAXBCausalRelationships() {
            return this.causalRelationships;
        }

        protected void initialiseCausalRelationships(){
            this.causalRelationships = new ArrayList<ExtendedPsiXmlCausalRelationship>();
        }

        @Override
        public String toString() {
            return "CausalRelationship List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}
