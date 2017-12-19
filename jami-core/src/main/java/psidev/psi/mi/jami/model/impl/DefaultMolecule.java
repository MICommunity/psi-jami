package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Molecule;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;

/**
 * The default implementation for a molecule
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the molecule object is a complex object.
 * To compare molecule objects, you can use some comparators provided by default (see DefaultInteractor)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/05/13</pre>
 */
public class DefaultMolecule extends DefaultInteractor implements Molecule {

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultMolecule(String name, CvTerm type) {
        super(name, type);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultMolecule(String name, String fullName, CvTerm type) {
        super(name, fullName, type);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultMolecule(String name, CvTerm type, Organism organism) {
        super(name, type, organism);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultMolecule(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type, organism);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultMolecule(String name, CvTerm type, Xref uniqueId) {
        super(name, type, uniqueId);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultMolecule(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type, uniqueId);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultMolecule(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultMolecule(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public DefaultMolecule(String name) {
        super(name);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultMolecule(String name, String fullName) {
        super(name, fullName);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultMolecule(String name, Organism organism) {
        super(name, organism);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultMolecule(String name, String fullName, Organism organism) {
        super(name, fullName, organism);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultMolecule(String name, Xref uniqueId) {
        super(name, uniqueId);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultMolecule(String name, String fullName, Xref uniqueId) {
        super(name, fullName, uniqueId);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultMolecule(String name, Organism organism, Xref uniqueId) {
        super(name, organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultMolecule.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultMolecule(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, organism, uniqueId);
    }
}
