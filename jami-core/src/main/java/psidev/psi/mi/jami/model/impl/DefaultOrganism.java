package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.utils.comparator.organism.UnambiguousOrganismComparator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for organism
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousOrganismComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/01/13</pre>
 */
public class DefaultOrganism implements Organism {

    private String commonName;
    private String scientificName;
    private int taxId;
    private Collection<Alias> aliases;
    private CvTerm cellType;
    private CvTerm compartment;
    private CvTerm tissue;

    /**
     * <p>Constructor for DefaultOrganism.</p>
     *
     * @param taxId a int.
     */
    public DefaultOrganism(int taxId){
        if (taxId == -1 || taxId == -2 || taxId == -3 || taxId == -4 || taxId == -5 || taxId > 0){
            this.taxId = taxId;
        }
        else {
            throw new IllegalArgumentException("The taxId "+taxId+" is not a valid taxid. Only NCBI taxid or -1, -2, -3, -4, -5 are valid taxids.");
        }
    }

    /**
     * <p>Constructor for DefaultOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     */
    public DefaultOrganism(int taxId, String commonName){
        this(taxId);
        this.commonName = commonName;
    }

    /**
     * <p>Constructor for DefaultOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     * @param scientificName a {@link java.lang.String} object.
     */
    public DefaultOrganism(int taxId, String commonName, String scientificName){
        this(taxId, commonName);
        this.scientificName = scientificName;
    }

    /**
     * <p>Constructor for DefaultOrganism.</p>
     *
     * @param taxId a int.
     * @param cellType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param tissue a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param compartment a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultOrganism(int taxId, CvTerm cellType, CvTerm tissue, CvTerm compartment){
        this(taxId);
        this.cellType = cellType;
        this.tissue = tissue;
        this.compartment = compartment;
    }

    /**
     * <p>Constructor for DefaultOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     * @param cellType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param tissue a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param compartment a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultOrganism(int taxId, String commonName, CvTerm cellType, CvTerm tissue, CvTerm compartment){
        this(taxId, commonName);
        this.cellType = cellType;
        this.tissue = tissue;
        this.compartment = compartment;
    }

    /**
     * <p>Constructor for DefaultOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     * @param scientificName a {@link java.lang.String} object.
     * @param cellType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param tissue a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param compartment a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultOrganism(int taxId, String commonName, String scientificName, CvTerm cellType, CvTerm tissue, CvTerm compartment){
        this(taxId, commonName, scientificName);
        this.cellType = cellType;
        this.tissue = tissue;
        this.compartment = compartment;
    }

    /**
     * <p>initialiseAliases</p>
     */
    protected void initialiseAliases(){
        this.aliases = new ArrayList<Alias>();
    }

    /**
     * <p>initialiseAliasesWith</p>
     *
     * @param aliases a {@link java.util.Collection} object.
     */
    protected void initialiseAliasesWith(Collection<Alias> aliases){
        if (aliases == null){
            this.aliases = Collections.EMPTY_LIST;
        }
        else {
            this.aliases = aliases;
        }
    }

    /**
     * <p>Getter for the field <code>commonName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getCommonName() {
        return this.commonName;
    }

    /** {@inheritDoc} */
    public void setCommonName(String name) {
        this.commonName = name;
    }

    /**
     * <p>Getter for the field <code>scientificName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getScientificName() {
        return this.scientificName;
    }

    /** {@inheritDoc} */
    public void setScientificName(String name) {
        this.scientificName = name;
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
        if (taxId == -1 || taxId == -2 || taxId == -3 || taxId == -4 || taxId == -5 || taxId > 0){
            this.taxId = id;
        }
        else {
            throw new IllegalArgumentException("The taxId "+id+" is not a valid taxid. Only NCBI taxid or -1, -2, -3, -4, -5 are valid taxids.");
        }
    }

    /**
     * <p>Getter for the field <code>aliases</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases() {
        if (aliases == null){
            initialiseAliases();
        }
        return this.aliases;
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
        return "Organism: "+getTaxId() + "(" + (getCommonName() != null ? getCommonName() : "-" )+")";
    }
}
