package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Molecule;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;

/**
 * Xml implementation of molecule
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/07/13</pre>
 */
@XmlTransient
public class XmlMolecule extends DefaultXmlInteractor implements Molecule {

    /**
     * <p>Constructor for XmlMolecule.</p>
     */
    public XmlMolecule() {
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public XmlMolecule(String name, CvTerm type) {
        super(name, type);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public XmlMolecule(String name, String fullName, CvTerm type) {
        super(name, fullName, type);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     */
    public XmlMolecule(String name, CvTerm type, Organism organism) {
        super(name, type, organism);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     */
    public XmlMolecule(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type, organism);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlMolecule(String name, CvTerm type, Xref uniqueId) {
        super(name, type, uniqueId);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlMolecule(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type, uniqueId);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlMolecule(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlMolecule(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     */
    public XmlMolecule(String name) {
        super(name);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     */
    public XmlMolecule(String name, String fullName) {
        super(name, fullName);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param organism a {@link Organism} object.
     */
    public XmlMolecule(String name, Organism organism) {
        super(name, organism);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param organism a {@link Organism} object.
     */
    public XmlMolecule(String name, String fullName, Organism organism) {
        super(name, fullName, organism);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlMolecule(String name, Xref uniqueId) {
        super(name, uniqueId);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlMolecule(String name, String fullName, Xref uniqueId) {
        super(name, fullName, uniqueId);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlMolecule(String name, Organism organism, Xref uniqueId) {
        super(name, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlMolecule.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public XmlMolecule(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, organism, uniqueId);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Molecule: "+getSourceLocator().toString():super.toString());
    }
}
