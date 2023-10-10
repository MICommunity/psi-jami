package psidev.psi.mi.jami.xml.model.extension.xml300;

import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CooperativityEvidence;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.utils.comparator.cooperativity.UnambiguousCooperativityEvidenceComparator;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

import javax.xml.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Xml 3.0 implementation for cooperativity evidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlCooperativityEvidence implements CooperativityEvidence, FileSourceContext, Locatable {
    private PsiXmlLocator sourceLocator;
    private Publication publication;

    @XmlLocation
    @XmlTransient
    private Locator locator;
    private JAXBEvidenceMethodWrapper jaxbEvidenceMethodWrapper;

    /**
     * <p>Constructor for XmlCooperativityEvidence.</p>
     */
    public XmlCooperativityEvidence() {
    }

    /**
     * <p>Constructor for XmlCooperativityEvidence.</p>
     *
     * @param pub a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public XmlCooperativityEvidence(Publication pub) {
        if (pub == null){
            throw new IllegalArgumentException("The publication is mandatory");
        }
        this.publication = pub;
    }

    /**
     * <p>Getter for the field <code>publication</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        if (this.publication == null){
            this.publication = new BibRef();
        }
        return this.publication;
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
     * <p>setSourceLocation.</p>
     *
     * @param sourceLocator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
    public void setSourceLocation(PsiXmlLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Cooperativity evidence: "+sourceLocator != null ? sourceLocator.toString():super.toString();
    }

    /**
     * <p>initialiseEvidenceMethods.</p>
     */
    protected void initialiseEvidenceMethods(){
        this.jaxbEvidenceMethodWrapper = new JAXBEvidenceMethodWrapper();
    }

    /** {@inheritDoc} */
    public void setPublication(Publication publication) {
        if (publication == null){
            throw new IllegalArgumentException("The publication cannot be null in a CooperativityEvidence");
        }
        this.publication = publication;
    }

    /**
     * <p>getEvidenceMethods.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CvTerm> getEvidenceMethods() {

        if (this.jaxbEvidenceMethodWrapper == null){
            initialiseEvidenceMethods();
        }
        return this.jaxbEvidenceMethodWrapper.evidenceMethods;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof CooperativityEvidence)){
            return false;
        }

        return UnambiguousCooperativityEvidenceComparator.areEquals(this, (CooperativityEvidence) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousCooperativityEvidenceComparator.hashCode(this);
    }

    /**
     * <p>setJAXBPublication.</p>
     *
     * @param bibRef a {@link psidev.psi.mi.jami.xml.model.extension.xml300.BibRef} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "bibref", required = true)
    public void setJAXBPublication(BibRef bibRef) {
        this.publication = bibRef;
    }

    /**
     * <p>setJAXBEvidenceMethodWrapper.</p>
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlCooperativityEvidence.JAXBEvidenceMethodWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name="evidenceMethodList")
    public void setJAXBEvidenceMethodWrapper(JAXBEvidenceMethodWrapper wrapper) {
        this.jaxbEvidenceMethodWrapper = wrapper;
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name = "evidenceMethodList")
    public static class JAXBEvidenceMethodWrapper implements Locatable, FileSourceContext {
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<CvTerm> evidenceMethods;

        public JAXBEvidenceMethodWrapper(){
            evidenceMethods = new ArrayList<CvTerm>();
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

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "evidenceMethod", type = XmlCvTerm.class, required = true)
        public List<CvTerm> getJAXBEvidenceMethods() {
            return evidenceMethods;
        }

        @Override
        public String toString() {
            return "Evidence methods list: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}
