package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultComplex;

/**
 * Mitab extension for Complex.
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class MitabComplex extends DefaultComplex implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabComplex(String name, CvTerm interactorType) {
        super(name, interactorType);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public MitabComplex(String name, String fullName, CvTerm interactorType) {
        super(name, fullName, interactorType);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public MitabComplex(String name, CvTerm interactorType, Organism organism) {
        super(name, interactorType, organism);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public MitabComplex(String name, String fullName, CvTerm interactorType, Organism organism) {
        super(name, fullName, interactorType, organism);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabComplex(String name, CvTerm interactorType, Xref uniqueId) {
        super(name, interactorType, uniqueId);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabComplex(String name, String fullName, CvTerm interactorType, Xref uniqueId) {
        super(name, fullName, interactorType, uniqueId);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabComplex(String name, CvTerm interactorType, Organism organism, Xref uniqueId) {
        super(name, interactorType, organism, uniqueId);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interactorType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabComplex(String name, String fullName, CvTerm interactorType, Organism organism, Xref uniqueId) {
        super(name, fullName, interactorType, organism, uniqueId);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public MitabComplex(String name) {
        super(name);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public MitabComplex(String name, String fullName) {
        super(name, fullName);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public MitabComplex(String name, Organism organism) {
        super(name, organism);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public MitabComplex(String name, String fullName, Organism organism) {
        super(name, fullName, organism);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabComplex(String name, Xref uniqueId) {
        super(name, uniqueId);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabComplex(String name, String fullName, Xref uniqueId) {
        super(name, fullName, uniqueId);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabComplex(String name, Organism organism, Xref uniqueId) {
        super(name, organism, uniqueId);
    }

    /**
     * <p>Constructor for MitabComplex.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public MitabComplex(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, organism, uniqueId);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return this.sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Complex: "+getSourceLocator().toString():super.toString());
    }
}
