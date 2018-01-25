package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultGene;

/**
 * Mitab extension for Gene.
 * It contains FileSourceContext
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabGene extends DefaultGene implements FileSourceContext {

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public MitabGene(String name) {
        super(name);
    }

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public MitabGene(String name, String fullName) {
        super(name, fullName);
    }

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public MitabGene(String name, Organism organism) {
        super(name, organism);
    }

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public MitabGene(String name, String fullName, Organism organism) {
        super(name, fullName, organism);
    }

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabGene(String name, Xref uniqueId) {
        super(name, uniqueId);
    }

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabGene(String name, String fullName, Xref uniqueId) {
        super(name, fullName, uniqueId);
    }

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabGene(String name, Organism organism, Xref uniqueId) {
        super(name, organism, uniqueId);
    }

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabGene(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, organism, uniqueId);
    }

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param ensembl a {@link java.lang.String} object.
     */
    public MitabGene(String name, String fullName, String ensembl) {
        super(name, fullName, ensembl);
    }

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param ensembl a {@link java.lang.String} object.
     */
    public MitabGene(String name, Organism organism, String ensembl) {
        super(name, organism, ensembl);
    }

    /**
     * <p>Constructor for MitabGene.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param ensembl a {@link java.lang.String} object.
     */
    public MitabGene(String name, String fullName, Organism organism, String ensembl) {
        super(name, fullName, organism, ensembl);
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
        return (getSourceLocator() != null ? "Gene: "+getSourceLocator().toString():super.toString());
    }
}
