package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.ParameterValue;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.reference.xml254.AbstractExperimentRef;

import java.math.BigDecimal;

/**
 * Xml implementation of Parameter
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlParameter extends AbstractXmlParameter {

    private Experiment experiment;

    /**
     * <p>Constructor for XmlParameter.</p>
     */
    public XmlParameter() {
    }

    /**
     * <p>Constructor for XmlParameter.</p>
     *
     * @param type a {@link CvTerm} object.
     * @param value a {@link ParameterValue} object.
     * @param uncertainty a {@link BigDecimal} object.
     * @param unit a {@link CvTerm} object.
     */
    public XmlParameter(CvTerm type, ParameterValue value, BigDecimal uncertainty, CvTerm unit) {
        super(type, value, uncertainty, unit);
    }

    /**
     * Sets the value of the experimentRefList property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer}
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name="experimentRef")
    public void setJAXBExperimentRef(Integer value) {
        if (value != null){
            this.experiment = new ExperimentRef(value);
        }
    }

    /**
     * <p>Getter for the field <code>experiment</code>.</p>
     *
     * @return a {@link Experiment} object.
     */
    public Experiment getExperiment() {
        return experiment;
    }

    /**
     * <p>Setter for the field <code>experiment</code>.</p>
     *
     * @param experiment a {@link Experiment} object.
     */
    public void setExperiment(Experiment experiment) {
        this.experiment = experiment;
    }

    private FileSourceLocator getParameterLocator(){
        return getSourceLocator();
    }

    ///////////////////////////////////////////////////////// classes

    /**
     * Experiment ref for experimental interactor
     */
    private class ExperimentRef extends AbstractExperimentRef {
        public ExperimentRef(int ref) {
            super(ref);
        }

        public boolean resolve(PsiXmlIdCache parsedObjects) {
            if (parsedObjects.containsExperiment(this.ref)){
                Experiment exp = parsedObjects.getExperiment(this.ref);
                if (exp != null){
                    experiment = exp;
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Parameter Experiment Reference: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
        }

        public FileSourceLocator getSourceLocator() {
            return getParameterLocator();
        }

        public void setSourceLocator(FileSourceLocator locator) {
            throw new UnsupportedOperationException("Cannot set the source locator of an experiment ref");
        }
    }
}
