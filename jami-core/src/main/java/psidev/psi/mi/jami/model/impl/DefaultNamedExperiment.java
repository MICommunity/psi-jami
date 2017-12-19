package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for NamedExperiment.
 *
 * The equals and hashcode methods are not overriden.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class DefaultNamedExperiment extends DefaultExperiment implements NamedExperiment{
    private String shortName;
    private String fullName;
    private Collection<Alias> aliases;

    /**
     * <p>Constructor for DefaultNamedExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public DefaultNamedExperiment(Publication publication) {
        super(publication);
        this.shortName = null;
        this.fullName = null;
    }

    /**
     * <p>Constructor for DefaultNamedExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedExperiment(Publication publication, CvTerm interactionDetectionMethod) {
        super(publication, interactionDetectionMethod);
        this.shortName = null;
        this.fullName = null;
    }

    /**
     * <p>Constructor for DefaultNamedExperiment.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     * @param interactionDetectionMethod a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultNamedExperiment(Publication publication, CvTerm interactionDetectionMethod, Organism organism) {
        super(publication, interactionDetectionMethod, organism);
        this.shortName = null;
        this.fullName = null;
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
     * <p>Getter for the field <code>shortName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getShortName() {
        return shortName;
    }

    /** {@inheritDoc} */
    public void setShortName(String shortName) {
        this.shortName = shortName;
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
