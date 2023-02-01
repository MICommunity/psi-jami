package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.utils.comparator.organism.UnambiguousOrganismComparator;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

import java.util.Collection;

/**
 * Xml implementation of an organism
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({
        HostOrganism.class
})
public class XmlOrganism implements Organism, FileSourceContext, Locatable{

    private NamesContainer namesContainer;
    private int taxId;
    private CvTerm cellType;
    private CvTerm compartment;
    private CvTerm tissue;
    private PsiXmlLocator sourceLocator;
    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>Constructor for XmlOrganism.</p>
     */
    public XmlOrganism(){

    }

    /**
     * <p>Constructor for XmlOrganism.</p>
     *
     * @param taxId a int.
     */
    public XmlOrganism(int taxId){
        if (taxId == -1 || taxId == -2 || taxId == -3 || taxId == -4 || taxId > 0){
            this.taxId = taxId;
        }
        else {
            throw new IllegalArgumentException("The taxId "+taxId+" is not a valid taxid. Only NCBI taxid or -1, -2, -3, -4 are valid taxids.");
        }
    }

    /**
     * <p>Constructor for XmlOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     */
    public XmlOrganism(int taxId, String commonName){
        this(taxId);
        this.namesContainer = new NamesContainer();
        this.namesContainer.setShortLabel(commonName);
    }

    /**
     * <p>Constructor for XmlOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     * @param scientificName a {@link java.lang.String} object.
     */
    public XmlOrganism(int taxId, String commonName, String scientificName){
        this(taxId, commonName);
        this.namesContainer.setFullName(scientificName);
    }

    /**
     * <p>Constructor for XmlOrganism.</p>
     *
     * @param taxId a int.
     * @param cellType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param tissue a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param compartment a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlOrganism(int taxId, CvTerm cellType, CvTerm tissue, CvTerm compartment){
        this(taxId);
        this.cellType = cellType;
        this.tissue = tissue;
        this.compartment = compartment;
    }

    /**
     * <p>Constructor for XmlOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     * @param cellType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param tissue a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param compartment a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlOrganism(int taxId, String commonName, CvTerm cellType, CvTerm tissue, CvTerm compartment){
        this(taxId, commonName);
        this.cellType = cellType;
        this.tissue = tissue;
        this.compartment = compartment;
    }

    /**
     * <p>Constructor for XmlOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     * @param scientificName a {@link java.lang.String} object.
     * @param cellType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param tissue a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param compartment a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlOrganism(int taxId, String commonName, String scientificName, CvTerm cellType, CvTerm tissue, CvTerm compartment){
        this(taxId, commonName, scientificName);
        this.cellType = cellType;
        this.tissue = tissue;
        this.compartment = compartment;
    }

    /**
     * <p>Getter for the field <code>cellType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getCellType() {
        return this.cellType;
    }

    /** {@inheritDoc} */
    public void setCellType(CvTerm cellType) {
        this.cellType = cellType;
    }

    /**
     * <p>Getter for the field <code>compartment</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getCompartment() {
        return this.compartment;
    }

    /** {@inheritDoc} */
    public void setCompartment(CvTerm compartment) {
        this.compartment = compartment;
    }

    /**
     * <p>Getter for the field <code>tissue</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getTissue() {
        return this.tissue;
    }

    /** {@inheritDoc} */
    public void setTissue(CvTerm tissue) {
        this.tissue = tissue;
    }

    /**
     * <p>Getter for the field <code>taxId</code>.</p>
     *
     * @return a int.
     */
    public int getTaxId() {
        return this.taxId;
    }

    /** {@inheritDoc} */
    public void setTaxId(int id) {
        if (id == -1 || id == -2 || id == -3 || id == -4 || id > 0){
            this.taxId = id;
        }
        else {
            throw new IllegalArgumentException("The taxId "+id+" is not a valid taxid. Only NCBI taxid or -1, -2, -3, -4 are valid taxids.");
        }
    }

    /**
     * <p>getCommonName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCommonName() {
        return this.namesContainer != null ? this.namesContainer.getShortLabel() : null;
    }

    /** {@inheritDoc} */
    public void setCommonName(String name) {
        if (this.namesContainer == null){
            this.namesContainer = new NamesContainer();
        }
        this.namesContainer.setShortLabel(name);
    }

    /**
     * <p>getScientificName.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getScientificName() {
        return this.namesContainer != null ? this.namesContainer.getFullName() : null;
    }

    /** {@inheritDoc} */
    public void setScientificName(String name) {
        if (this.namesContainer == null){
            this.namesContainer = new NamesContainer();
        }
        this.namesContainer.setFullName(name);
    }

    /**
     * <p>getAliases.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases() {
        if (this.namesContainer == null){
            this.namesContainer = new NamesContainer();
        }
        return this.namesContainer.getAliases();
    }

    /**
     * Sets the value of the names property.
     *
     * @param value
     *     allowed object is
     *     {@link psidev.psi.mi.jami.xml.model.extension.xml254.NamesContainer}
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="names")
    public void setJAXBNames(NamesContainer value) {
        this.namesContainer = value;
    }

    /**
     * <p>setJAXBCellType.</p>
     *
     * @param cellType a {@link psidev.psi.mi.jami.xml.model.extension.xml254.XmlOpenCvTerm} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="cellType")
    public void setJAXBCellType(XmlOpenCvTerm cellType) {
        this.cellType = cellType;
    }

    /**
     * <p>setJAXBCompartment.</p>
     *
     * @param compartment a {@link psidev.psi.mi.jami.xml.model.extension.xml254.XmlOpenCvTerm} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="compartment")
    public void setJAXBCompartment(XmlOpenCvTerm compartment) {
        this.compartment = compartment;
    }

    /**
     * <p>setJAXBTissue.</p>
     *
     * @param tissue a {@link psidev.psi.mi.jami.xml.model.extension.xml254.XmlOpenCvTerm} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="tissue")
    public void setJAXBTissue(XmlOpenCvTerm tissue) {
        this.tissue = tissue;
    }

    /**
     * <p>setJAXBTaxId.</p>
     *
     * @param id a int.
     */
    @XmlAttribute(name = "ncbiTaxId", required = true)
    public void setJAXBTaxId(int id) {
        if (id == -1 || id == -2 || id == -3 || id == -4 || id > 0){
            this.taxId = id;
        }
        else {
            this.taxId = -3;
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onInvalidOrganismTaxid(Integer.toString(id) , this);
            }
        }
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

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof Organism)){
            return false;
        }

        return UnambiguousOrganismComparator.areEquals(this, (Organism) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousOrganismComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Organism: "+getSourceLocator().toString():super.toString());
    }
}
