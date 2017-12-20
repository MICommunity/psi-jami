package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for Named InteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class DefaultNamedInteractionEvidence extends DefaultInteractionEvidence implements NamedInteraction<ParticipantEvidence>{

    private String fullName;
    private Collection<Alias> aliases;

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public DefaultNamedInteractionEvidence(Experiment experiment) {
        super(experiment);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultNamedInteractionEvidence(Experiment experiment, String shortName) {
        super(experiment, shortName);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public DefaultNamedInteractionEvidence(Experiment experiment, String shortName, Source source) {
        super(experiment, shortName, source);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedInteractionEvidence(Experiment experiment, String shortName, CvTerm type) {
        super(experiment, shortName, type);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNamedInteractionEvidence(Experiment experiment, Xref imexId) {
        super(experiment, imexId);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNamedInteractionEvidence(Experiment experiment, String shortName, Xref imexId) {
        super(experiment, shortName, imexId);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNamedInteractionEvidence(Experiment experiment, String shortName, Source source, Xref imexId) {
        super(experiment, shortName, source, imexId);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param experiment a {@link psidev.psi.mi.jami.model.Experiment} object.
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNamedInteractionEvidence(Experiment experiment, String shortName, CvTerm type, Xref imexId) {
        super(experiment, shortName, type, imexId);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNamedInteractionEvidence(Xref imexId) {
        super(imexId);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNamedInteractionEvidence(String shortName, Xref imexId) {
        super(shortName, imexId);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNamedInteractionEvidence(String shortName, Source source, Xref imexId) {
        super(shortName, source, imexId);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param imexId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNamedInteractionEvidence(String shortName, CvTerm type, Xref imexId) {
        super(shortName, type, imexId);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     */
    public DefaultNamedInteractionEvidence() {
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultNamedInteractionEvidence(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultNamedInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedInteractionEvidence(String shortName, CvTerm type) {
        super(shortName, type);
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
     * <p>Getter for the field <code>fullName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getFullName() {
        return fullName;
    }

    /** {@inheritDoc} */
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    /**
     * <p>Getter for the field <code>aliases</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getAliases() {
        if (this.aliases == null){
            initialiseAliases();
        }
        return aliases;
    }
}
