//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.05.20 at 10:58:57 AM BST 
//


package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.reference.xml300.AbstractExperimentRef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * This elements is controlled by the PSI-MI controlled vocabulary
 *                 "experimentalPreparation", root term id MI:0346.
 *
 *
 * <p>Java class for experimentalPreparation complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * The JAXB binding is designed to be read-only and is not designed for writing
 *

 */
@XmlAccessorType(XmlAccessType.NONE)
public class ExperimentalCvTerm
    extends XmlCvTerm
{
    private JAXBExperimentRefWrapper jaxbExperimentRefWrapper;

    /**
     * <p>Constructor for ExperimentalCvTerm.</p>
     */
    public ExperimentalCvTerm() {
    }

    /**
     * <p>Constructor for ExperimentalCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param miIdentifier a {@link String} object.
     */
    public ExperimentalCvTerm(String shortName, String miIdentifier) {
        super(shortName, miIdentifier);
    }

    /**
     * <p>Constructor for ExperimentalCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     */
    public ExperimentalCvTerm(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for ExperimentalCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param miIdentifier a {@link String} object.
     */
    public ExperimentalCvTerm(String shortName, String fullName, String miIdentifier) {
        super(shortName, fullName, miIdentifier);
    }

    /**
     * <p>Constructor for ExperimentalCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param ontologyId a {@link Xref} object.
     */
    public ExperimentalCvTerm(String shortName, Xref ontologyId) {
        super(shortName, ontologyId);
    }

    /**
     * <p>Constructor for ExperimentalCvTerm.</p>
     *
     * @param shortName a {@link String} object.
     * @param fullName a {@link String} object.
     * @param ontologyId a {@link Xref} object.
     */
    public ExperimentalCvTerm(String shortName, String fullName, Xref ontologyId) {
        super(shortName, fullName, ontologyId);
    }

    /**
     * <p>getExperiments.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Experiment> getExperiments() {
        if (this.jaxbExperimentRefWrapper == null){
            this.jaxbExperimentRefWrapper = new JAXBExperimentRefWrapper();
        }
        return this.jaxbExperimentRefWrapper.experiments;
    }

    /**
     * Gets the value of the experimentRefList property.
     *
     * @param wrapper a {@link JAXBExperimentRefWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name="experimentRefList")
    public void setJAXBExperimentRefWrapper(JAXBExperimentRefWrapper wrapper) {
        this.jaxbExperimentRefWrapper = wrapper;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Experimental Cv Term: "+getSourceLocator().toString():super.toString());
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name = "cvExperimentRefList")
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
            else{
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "experimentRef", type = Integer.class, required = true)
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

            @Override
            public String toString() {
                return "Cv Term Experiment references List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
            }
        }

        /////////////////////////////////////////// inner inner classes
        /**
         * Experiment ref for experimental cv term
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
                return "Cv Term Experiment Reference : "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
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
