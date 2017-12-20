package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultOrganism;

/**
 * Mitab extension of Organism.
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabOrganism extends DefaultOrganism implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabOrganism.</p>
     *
     * @param taxId a int.
     */
    public MitabOrganism(int taxId) {
        super((taxId == -1 || taxId == -2 || taxId == -3 || taxId == -4 || taxId == -5 || taxId > 0) ? taxId : -3);
    }

    /**
     * <p>Constructor for MitabOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     */
    public MitabOrganism(int taxId, String commonName) {
        super((taxId == -1 || taxId == -2 || taxId == -3 || taxId == -4 || taxId == -5 || taxId > 0) ? taxId : -3, commonName);
    }

    /**
     * <p>Constructor for MitabOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     * @param scientificName a {@link java.lang.String} object.
     */
    public MitabOrganism(int taxId, String commonName, String scientificName) {
        super((taxId == -1 || taxId == -2 || taxId == -3 || taxId == -4 || taxId == -5 || taxId > 0) ? taxId : -3, commonName, scientificName);
    }

    /**
     * <p>Constructor for MitabOrganism.</p>
     *
     * @param taxId a int.
     * @param cellType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param tissue a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param compartment a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabOrganism(int taxId, CvTerm cellType, CvTerm tissue, CvTerm compartment) {
        super((taxId == -1 || taxId == -2 || taxId == -3 || taxId == -4 || taxId == -5 || taxId > 0) ? taxId : -3, cellType, tissue, compartment);
    }

    /**
     * <p>Constructor for MitabOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     * @param cellType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param tissue a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param compartment a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabOrganism(int taxId, String commonName, CvTerm cellType, CvTerm tissue, CvTerm compartment) {
        super((taxId == -1 || taxId == -2 || taxId == -3 || taxId == -4 || taxId == -5 || taxId > 0) ? taxId : -3, commonName, cellType, tissue, compartment);
    }

    /**
     * <p>Constructor for MitabOrganism.</p>
     *
     * @param taxId a int.
     * @param commonName a {@link java.lang.String} object.
     * @param scientificName a {@link java.lang.String} object.
     * @param cellType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param tissue a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param compartment a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabOrganism(int taxId, String commonName, String scientificName, CvTerm cellType, CvTerm tissue, CvTerm compartment) {
        super((taxId == -1 || taxId == -2 || taxId == -3 || taxId == -4 || taxId == -5 || taxId > 0) ? taxId : -3, commonName, scientificName, cellType, tissue, compartment);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Organism: "+getSourceLocator().toString():super.toString());
    }
}
