package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.NamedInteraction;
import psidev.psi.mi.jami.model.Participant;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Default implementation for Named interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class DefaultNamedInteraction extends DefaultInteraction implements NamedInteraction<Participant>{
    private String fullName;
    private Collection<Alias> aliases;

    /**
     * <p>Constructor for DefaultNamedInteraction.</p>
     */
    public DefaultNamedInteraction() {
    }

    /**
     * <p>Constructor for DefaultNamedInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultNamedInteraction(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultNamedInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNamedInteraction(String shortName, CvTerm type) {
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
