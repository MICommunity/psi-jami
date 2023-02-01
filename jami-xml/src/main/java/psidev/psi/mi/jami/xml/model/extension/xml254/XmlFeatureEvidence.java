package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.ExperimentalEntity;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.Parameter;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlExperiment;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeatureEvidence;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.reference.xml254.AbstractExperimentRef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Xml implementation of a Feature
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlFeatureEvidence extends AbstractXmlFeature<ExperimentalEntity, FeatureEvidence> implements ExtendedPsiXmlFeatureEvidence {

    private List<CvTerm> featureDetectionMethods;
    private boolean initialisedMethods = false;
    private XmlParticipantEvidence originalParticipant;
    @XmlLocation
    @XmlTransient
    private Locator locator;
    private JAXBExperimentRefWrapper jaxbExperimentRefWrapper;
    private JAXBParameterWrapper jaxbParameterWrapper;

    /**
     * <p>Constructor for XmlFeatureEvidence.</p>
     */
    public XmlFeatureEvidence() {
    }

    /**
     * <p>Constructor for XmlFeatureEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public XmlFeatureEvidence(String shortName, String fullName) {
        super(shortName, fullName);
    }

    /**
     * <p>Constructor for XmlFeatureEvidence.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlFeatureEvidence(CvTerm type) {
        super(type);
    }

    /**
     * <p>Constructor for XmlFeatureEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlFeatureEvidence(String shortName, String fullName, CvTerm type) {
        super(shortName, fullName, type);
    }

    /**
     * <p>Constructor for XmlFeatureEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public XmlFeatureEvidence(String shortName, String fullName, String interpro) {
        super(shortName, fullName, interpro);
    }

    /**
     * <p>Constructor for XmlFeatureEvidence.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public XmlFeatureEvidence(CvTerm type, String interpro) {
        super(type, interpro);
    }

    /**
     * <p>Constructor for XmlFeatureEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public XmlFeatureEvidence(String shortName, String fullName, CvTerm type, String interpro) {
        super(shortName, fullName, type, interpro);
    }

    /**
     * <p>getDetectionMethods.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CvTerm> getDetectionMethods() {
        if (!initialisedMethods){
            initialiseDetectionMethods();
        }
        return featureDetectionMethods;
    }

    /**
     * <p>getExperiments.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Experiment> getExperiments() {
        if (jaxbExperimentRefWrapper == null){
            jaxbExperimentRefWrapper = new JAXBExperimentRefWrapper();
        }
        return jaxbExperimentRefWrapper.experiments;
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="names")
    public void setJAXBNames(NamesContainer value) {
        super.setJAXBNames(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="xref")
    public void setJAXBXref(FeatureXrefContainer value) {
        super.setJAXBXref(value);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="featureType")
    public void setJAXBType(XmlCvTerm type) {
        super.setJAXBType(type);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="featureRole")
    public void setJAXBFeatureRole(XmlCvTerm role) {
        super.setJAXBFeatureRole(role);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        super.setJAXBAttributeWrapper(jaxbAttributeWrapper);
    }

    /** {@inheritDoc} */
    @Override
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="featureRangeList", required = true)
    public void setJAXBRangeWrapper(JAXBRangeWrapper jaxbRangeWrapper) {
        super.setJAXBRangeWrapper(jaxbRangeWrapper);
    }

    /**
     * <p>setJAXBId.</p>
     *
     * @param id a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(int id) {
        super.setId(id);
    }

    /**
     * <p>getJAXBFeatureDetectionMethods.</p>
     *
     * @return a {@link java.util.List} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="featureDetectionMethod", type = XmlCvTerm.class)
    public List<CvTerm> getJAXBFeatureDetectionMethods() {
        return (List<CvTerm>) getDetectionMethods();
    }

    /**
     * <p>setJAXBExperimentRefWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml254.XmlFeatureEvidence.JAXBExperimentRefWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="experimentRefList")
    public void setJAXBExperimentRefWrapper(JAXBExperimentRefWrapper wrapper) {
        this.jaxbExperimentRefWrapper = wrapper;
    }

    /**
     * <p>setJAXBParameterWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml254.XmlFeatureEvidence.JAXBParameterWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="parameterList")
    public void setJAXBParameterWrapper(JAXBParameterWrapper wrapper) {
        this.jaxbParameterWrapper = wrapper;
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
    public Collection<Parameter> getParameters() {
        if (this.jaxbParameterWrapper == null){
           this.jaxbParameterWrapper = new JAXBParameterWrapper();
        }
        return this.jaxbParameterWrapper.parameters;
    }

    /**
     * <p>initialiseDetectionMethods.</p>
     */
    protected void initialiseDetectionMethods(){

        if (this.featureDetectionMethods == null){
            this.featureDetectionMethods = new ArrayList<CvTerm>();
        }
        else if (!this.featureDetectionMethods.isEmpty()){
            initialisedMethods = true;
            return;
        }

        if (originalParticipant != null){
            ExtendedPsiXmlInteractionEvidence interaction = originalParticipant.getOriginalInteraction();
            if (interaction != null){
                List<ExtendedPsiXmlExperiment> originalExperiments = interaction.getOriginalExperiments();
                if (originalExperiments != null && !originalExperiments.isEmpty()){
                    for (ExtendedPsiXmlExperiment exp : originalExperiments){
                        if (exp.getFeatureDetectionMethod() != null){
                            this.featureDetectionMethods.add(exp.getFeatureDetectionMethod());
                        }
                    }
                }
            }
            originalParticipant = null;
        }

        initialisedMethods = true;
    }

    /**
     * <p>Setter for the field <code>originalParticipant</code>.</p>
     *
     * @param p a {@link psidev.psi.mi.jami.xml.model.extension.xml254.XmlParticipantEvidence} object.
     */
    protected void setOriginalParticipant(XmlParticipantEvidence p){
        this.originalParticipant = p;
        setParticipant(p);
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif", name = "featureExperimentRefList")
    public static class JAXBExperimentRefWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private JAXBExperimentRefList jaxbExperimentRefs;
        private List<Experiment> experiments;

        public JAXBExperimentRefWrapper(){
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="experimentRef", type = Integer.class, required = true)
        public List<Integer> getJAXBExperimentRefs() {
            if (this.jaxbExperimentRefs == null){
                this.jaxbExperimentRefs = new JAXBExperimentRefList();
            }
            return jaxbExperimentRefs;
        }

        //////////////////////////////////////////////////////////////
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
                experiments.add(index, new ExperimentRef(val));
                return true;
            }
        }

        ////////////////////////////////////////////////// classes

        /**
         * Experiment ref
         */
        private class ExperimentRef extends AbstractExperimentRef {
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
                return "Experiment reference: "+ref+" in feature "+(getSourceLocator() != null? getSourceLocator().toString():"") ;
            }

            public FileSourceLocator getSourceLocator() {
                return sourceLocator;
            }

            public void setSourceLocator(FileSourceLocator locator) {
                throw new UnsupportedOperationException("Cannot set the source locator of an experiment ref");
            }
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif", name="featureParameterWrapper")
    public static class JAXBParameterWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Parameter> parameters;

        public JAXBParameterWrapper(){
            initialiseParameters();
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif", type= XmlParameter.class, name="parameter", required = true)
        public List<Parameter> getJAXBParameters() {
            return this.parameters;
        }

        protected void initialiseParameters(){
            this.parameters = new ArrayList<Parameter>();
        }

        @Override
        public String toString() {
            return "Feature Parameter List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}
