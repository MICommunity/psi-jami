package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.CvTermUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Abstract class for Participant
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>09/07/13</pre>
 */
public abstract class AbstractParticipant<I extends Interaction, F extends Feature> extends AbstractEntity<F> implements Participant<I,F> {
    private I interaction;
    private CvTerm biologicalRole;
    private CvTerm biologicalEffect;
    private Collection<Xref> xrefs;
    private Collection<Annotation> annotations;
    private Collection<Alias> aliases;

    /**
     * <p>Constructor for AbstractParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public AbstractParticipant(Interactor interactor){
        super(interactor);
        this.biologicalRole = CvTermUtils.createUnspecifiedRole();
    }

    /**
     * <p>Constructor for AbstractParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractParticipant(Interactor interactor, CvTerm bioRole){
        super(interactor);
        this.biologicalRole = bioRole != null ? bioRole : CvTermUtils.createUnspecifiedRole();
    }

    /**
     * <p>Constructor for AbstractParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public AbstractParticipant(Interactor interactor, Stoichiometry stoichiometry){
        super(interactor, stoichiometry);
        this.biologicalRole = CvTermUtils.createUnspecifiedRole();
    }

    /**
     * <p>Constructor for AbstractParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public AbstractParticipant(Interactor interactor, CvTerm bioRole, Stoichiometry stoichiometry){
        super(interactor, stoichiometry);
        this.biologicalRole = bioRole != null ? bioRole : CvTermUtils.createUnspecifiedRole();
    }

    /**
     * <p>Constructor for AbstractParticipant.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param bioRole a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @param biologicalEffect a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractParticipant(Interactor interactor, CvTerm bioRole, CvTerm biologicalEffect, Stoichiometry stoichiometry){
        super(interactor, stoichiometry);
        this.biologicalRole = bioRole != null ? bioRole : CvTermUtils.createUnspecifiedRole();
        this.biologicalEffect = biologicalEffect;
    }

    /**
     * <p>initialiseXrefs</p>
     */
    protected void initialiseXrefs() {
        this.xrefs = new ArrayList<Xref>();
    }

    /**
     * <p>initialiseAnnotations</p>
     */
    protected void initialiseAnnotations() {
        this.annotations = new ArrayList<Annotation>();
    }

    /**
     * <p>initialiseAliases</p>
     */
    protected void initialiseAliases(){
        this.aliases = new ArrayList<Alias>();
    }

    /**
     * <p>initialiseXrefsWith</p>
     *
     * @param xrefs a {@link java.util.Collection} object.
     */
    protected void initialiseXrefsWith(Collection<Xref> xrefs) {
        if (xrefs == null){
            this.xrefs = Collections.EMPTY_LIST;
        }
        else {
            this.xrefs = xrefs;
        }
    }

    /**
     * <p>initialiseAnnotationsWith</p>
     *
     * @param annotations a {@link java.util.Collection} object.
     */
    protected void initialiseAnnotationsWith(Collection<Annotation> annotations) {
        if (annotations == null){
            this.annotations = Collections.EMPTY_LIST;
        }
        else {
            this.annotations = annotations;
        }
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
     * <p>Getter for the field <code>biologicalRole</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getBiologicalRole() {
        return this.biologicalRole;
    }

    /** {@inheritDoc} */
    public void setBiologicalRole(CvTerm bioRole) {
        if (bioRole == null){
            this.biologicalRole = CvTermUtils.createUnspecifiedRole();
        }
        else {
            biologicalRole = bioRole;
        }
    }

    /**
     * <p>Getter for the field <code>biologicalEffect</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getBiologicalEffect() {
        return this.biologicalEffect;
    }

    /**
     * <p>Setter for the field <code>biologicalEffect</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public void setBiologicalEffect(CvTerm biologicalEffect) {
        this.biologicalEffect = biologicalEffect;
    }

    /**
     * <p>Getter for the field <code>xrefs</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        if (xrefs == null){
            initialiseXrefs();
        }
        return this.xrefs;
    }

    /**
     * <p>Getter for the field <code>annotations</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (annotations == null){
            initialiseAnnotations();
        }
        return this.annotations;
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
     * <p>addFeature</p>
     *
     * @param feature a F object.
     * @return a boolean.
     */
    public boolean addFeature(F feature) {

        if (feature == null){
            return false;
        }

        if (getFeatures().add(feature)){
            feature.setParticipant(this);
            return true;
        }
        return false;
    }

    /**
     * <p>removeFeature</p>
     *
     * @param feature a F object.
     * @return a boolean.
     */
    public boolean removeFeature(F feature) {

        if (feature == null){
            return false;
        }

        if (getFeatures().remove(feature)){
            feature.setParticipant(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean addAllFeatures(Collection<? extends F> features) {
        if (features == null){
            return false;
        }

        boolean added = false;
        for (F feature : features){
            if (addFeature(feature)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean removeAllFeatures(Collection<? extends F> features) {
        if (features == null){
            return false;
        }

        boolean added = false;
        for (F feature : features){
            if (removeFeature(feature)){
                added = true;
            }
        }
        return added;
    }

    /**
     * <p>setInteractionAndAddParticipant</p>
     *
     * @param interaction a I object.
     */
    public void setInteractionAndAddParticipant(I interaction) {

        if (this.interaction != null){
            this.interaction.removeParticipant(this);
        }

        if (interaction != null){
            interaction.addParticipant(this);
        }
    }

    /**
     * <p>Getter for the field <code>interaction</code>.</p>
     *
     * @return a I object.
     */
    public I getInteraction() {
        return this.interaction;
    }

    /**
     * <p>Setter for the field <code>interaction</code>.</p>
     *
     * @param interaction a I object.
     */
    public void setInteraction(I interaction) {
        this.interaction = interaction;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Participant: "+getInteractor().toString() + (getStoichiometry() != null ? ", stoichiometry: " + getStoichiometry().toString() : "");
    }
}
