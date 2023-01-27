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
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.reference.xml300.AbstractExperimentRef;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The experiment/participant host organism
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class HostOrganism extends XmlOrganism {

    private JAXBExperimentRefWrapper jaxbExperimentRefWrapper;

    /**
     * <p>Constructor for HostOrganism.</p>
     */
    public HostOrganism() {
    }

    /**
     * <p>Constructor for HostOrganism.</p>
     *
     * @param taxId a int.
     */
    public HostOrganism(int taxId) {
        super(taxId);
    }

    /**
     * <p>Constructor for HostOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link String} object.
     */
    public HostOrganism(int taxId, String commonName) {
        super(taxId, commonName);
    }

    /**
     * <p>Constructor for HostOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link String} object.
     * @param scientificName a {@link String} object.
     */
    public HostOrganism(int taxId, String commonName, String scientificName) {
        super(taxId, commonName, scientificName);
    }

    /**
     * <p>Constructor for HostOrganism.</p>
     *
     * @param taxId a int.
     * @param cellType a {@link CvTerm} object.
     * @param tissue a {@link CvTerm} object.
     * @param compartment a {@link CvTerm} object.
     */
    public HostOrganism(int taxId, CvTerm cellType, CvTerm tissue, CvTerm compartment) {
        super(taxId, cellType, tissue, compartment);
    }

    /**
     * <p>Constructor for HostOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link String} object.
     * @param cellType a {@link CvTerm} object.
     * @param tissue a {@link CvTerm} object.
     * @param compartment a {@link CvTerm} object.
     */
    public HostOrganism(int taxId, String commonName, CvTerm cellType, CvTerm tissue, CvTerm compartment) {
        super(taxId, commonName, cellType, tissue, compartment);
    }

    /**
     * <p>Constructor for HostOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link String} object.
     * @param scientificName a {@link String} object.
     * @param cellType a {@link CvTerm} object.
     * @param tissue a {@link CvTerm} object.
     * @param compartment a {@link CvTerm} object.
     */
    public HostOrganism(int taxId, String commonName, String scientificName, CvTerm cellType, CvTerm tissue, CvTerm compartment) {
        super(taxId, commonName, scientificName, cellType, tissue, compartment);
    }

    /**
     * <p>getExperiments.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Experiment> getExperiments() {
        if (jaxbExperimentRefWrapper == null){
            jaxbExperimentRefWrapper = new JAXBExperimentRefWrapper();
        }
        return jaxbExperimentRefWrapper.experiments;
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

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name = "hostOrganismExperimentRefList")
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

        @Override
        public String toString() {
            return "Host Organism experiment list: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }

        //////////////////////////////////////////////////////////////
        /**
         * The experiment ref list used by JAXB to populate experiment refs
         */
        private class JAXBExperimentRefList extends ArrayList<Integer>{

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
                experiments.add(index, new ExperimentRef(val));
                return true;
            }

            @Override
            public String toString() {
                return "Host Organism Experiment List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
            }
        }

        ////////////////////////////////////////////////////// classes
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
                return "Host Organism Experiment Reference: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
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
