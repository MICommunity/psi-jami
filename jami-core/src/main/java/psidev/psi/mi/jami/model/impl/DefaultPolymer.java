package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Polymer;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.CvTermUtils;

/**
 * Default implementation for Polymer
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the Polymer object is a complex object.
 * To compare Polymer objects, you can use some comparators provided by default:
 * - DefaultPolymerComparator
 * - UnambiguousPolymerComparator
 * - DefaultExactPolymerComparator
 * - UnambiguousExactPolymerComparator
 * - AbstractPolymerComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/05/13</pre>
 */
public class DefaultPolymer extends DefaultMolecule implements Polymer{

    private String sequence;

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultPolymer(String name, CvTerm type) {
        super(name, type != null ? type : CvTermUtils.createPolymerInteractorType());
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultPolymer(String name, String fullName, CvTerm type) {
        super(name, fullName, type != null ? type : CvTermUtils.createPolymerInteractorType());
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultPolymer(String name, CvTerm type, Organism organism) {
        super(name, type != null ? type : CvTermUtils.createPolymerInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultPolymer(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type != null ? type : CvTermUtils.createPolymerInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultPolymer(String name, CvTerm type, Xref uniqueId) {
        super(name, type != null ? type : CvTermUtils.createPolymerInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultPolymer(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type != null ? type : CvTermUtils.createPolymerInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultPolymer(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type != null ? type : CvTermUtils.createPolymerInteractorType(), organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultPolymer(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type != null ? type : CvTermUtils.createPolymerInteractorType(), organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public DefaultPolymer(String name) {
        super(name, CvTermUtils.createPolymerInteractorType());
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultPolymer(String name, String fullName) {
        super(name, fullName, CvTermUtils.createPolymerInteractorType());
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultPolymer(String name, Organism organism) {
        super(name, CvTermUtils.createPolymerInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultPolymer(String name, String fullName, Organism organism) {
        super(name, fullName, CvTermUtils.createPolymerInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultPolymer(String name, Xref uniqueId) {
        super(name, CvTermUtils.createPolymerInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultPolymer(String name, String fullName, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createPolymerInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultPolymer(String name, Organism organism, Xref uniqueId) {
        super(name, CvTermUtils.createPolymerInteractorType(), organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultPolymer.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultPolymer(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createPolymerInteractorType(), organism, uniqueId);
    }

    /**
     * <p>Getter for the field <code>sequence</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getSequence() {
        return this.sequence;
    }


    /**
     * <p>Setter for the field <code>sequence</code>.</p>
     *
     * @param sequence a {@link java.lang.String} object.
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    /**
     * Sets the interactor type of this polymer.
     * If the given interactorType is null, it sets the interactorType to 'biopolymer'(MI:0383)
     */
    @Override
    public void setInteractorType(CvTerm interactorType) {
        if (interactorType == null){
            super.setInteractorType(CvTermUtils.createPolymerInteractorType());
        }
        else {
            super.setInteractorType(interactorType);
        }
    }
}
