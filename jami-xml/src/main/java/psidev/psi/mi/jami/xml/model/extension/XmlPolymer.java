package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Polymer;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.CvTermUtils;

import javax.xml.bind.annotation.XmlTransient;

/**
 * Xml implementation of Polymer
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/07/13</pre>
 */
@XmlTransient
public class XmlPolymer extends XmlMolecule implements Polymer {

    /**
     * <p>Constructor for XmlPolymer.</p>
     */
    public XmlPolymer() {
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlPolymer(String name, CvTerm type) {
        super(name, type);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlPolymer(String name, String fullName, CvTerm type) {
        super(name, fullName, type);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlPolymer(String name, CvTerm type, Organism organism) {
        super(name, type, organism);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlPolymer(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type, organism);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlPolymer(String name, CvTerm type, Xref uniqueId) {
        super(name, type, uniqueId);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlPolymer(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type, uniqueId);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlPolymer(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlPolymer(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public XmlPolymer(String name) {
        super(name, new XmlCvTerm(Polymer.POLYMER, new XmlXref(CvTermUtils.createPsiMiDatabase(),Polymer.POLYMER_MI, CvTermUtils.createIdentityQualifier())));
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public XmlPolymer(String name, String fullName) {
        super(name, fullName, new XmlCvTerm(Polymer.POLYMER, new XmlXref(CvTermUtils.createPsiMiDatabase(),Polymer.POLYMER_MI, CvTermUtils.createIdentityQualifier())));
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlPolymer(String name, Organism organism) {
        super(name, new XmlCvTerm(Polymer.POLYMER, new XmlXref(CvTermUtils.createPsiMiDatabase(),Polymer.POLYMER_MI, CvTermUtils.createIdentityQualifier())), organism);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlPolymer(String name, String fullName, Organism organism) {
        super(name, fullName, new XmlCvTerm(Polymer.POLYMER, new XmlXref(CvTermUtils.createPsiMiDatabase(),Polymer.POLYMER_MI, CvTermUtils.createIdentityQualifier())), organism);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlPolymer(String name, Xref uniqueId) {
        super(name, new XmlCvTerm(Polymer.POLYMER, new XmlXref(CvTermUtils.createPsiMiDatabase(),Polymer.POLYMER_MI, CvTermUtils.createIdentityQualifier())), uniqueId);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlPolymer(String name, String fullName, Xref uniqueId) {
        super(name, fullName, new XmlCvTerm(Polymer.POLYMER, new XmlXref(CvTermUtils.createPsiMiDatabase(),Polymer.POLYMER_MI, CvTermUtils.createIdentityQualifier())), uniqueId);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlPolymer(String name, Organism organism, Xref uniqueId) {
        super(name, new XmlCvTerm(Polymer.POLYMER, new XmlXref(CvTermUtils.createPsiMiDatabase(),Polymer.POLYMER_MI, CvTermUtils.createIdentityQualifier())), organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlPolymer(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, new XmlCvTerm(Polymer.POLYMER, new XmlXref(CvTermUtils.createPsiMiDatabase(),Polymer.POLYMER_MI, CvTermUtils.createIdentityQualifier())), organism, uniqueId);
    }

    /** {@inheritDoc} */
    @Override
    protected void createDefaultInteractorType() {
        setInteractorType(new XmlCvTerm(Polymer.POLYMER, new XmlXref(CvTermUtils.createPsiMiDatabase(),Polymer.POLYMER_MI, CvTermUtils.createIdentityQualifier())));
    }

    /**
     * <p>getSequence.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSequence() {
        return super.getSequence();
    }

    /** {@inheritDoc} */
    public void setSequence(String sequence) {
        super.setSequence(sequence);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Polymer: "+getSourceLocator().toString():super.toString());
    }
}
