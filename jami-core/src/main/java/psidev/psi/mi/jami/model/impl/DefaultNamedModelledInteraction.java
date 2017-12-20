package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation of Named modelled interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class DefaultNamedModelledInteraction extends DefaultModelledInteraction implements NamedInteraction<ModelledParticipant>{
    private String fullName;
    private Collection<Alias> aliases;

    /**
     * <p>Constructor for DefaultNamedModelledInteraction.</p>
     */
    public DefaultNamedModelledInteraction() {
    }

    /**
     * <p>Constructor for DefaultNamedModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultNamedModelledInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultNamedModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     */
    public DefaultNamedModelledInteraction(String shortName, Source source) {
        super(shortName, source);
    }

    /**
     * <p>Constructor for DefaultNamedModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedModelledInteraction(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for DefaultNamedModelledInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param source a {@link psidev.psi.mi.jami.model.Source} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedModelledInteraction(String shortName, Source source, CvTerm type) {
        super(shortName, source, type);
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
