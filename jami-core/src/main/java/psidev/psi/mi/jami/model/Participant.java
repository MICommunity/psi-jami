package psidev.psi.mi.jami.model;

import java.util.Collection;

/**
 * Participant identified in an interaction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/11/12</pre>
 */
public interface Participant<I extends Interaction, F extends Feature> extends Entity<F>{

    /** Constant <code>UNSPECIFIED_ROLE="unspecified role"</code> */
    public static final String UNSPECIFIED_ROLE = "unspecified role";
    /** Constant <code>UNSPECIFIED_ROLE_MI="MI:0499"</code> */
    public static final String UNSPECIFIED_ROLE_MI = "MI:0499";
    /** Constant <code>PUTATIVE_SELF_ROLE="putative self"</code> */
    public static final String PUTATIVE_SELF_ROLE = "putative self";
    /** Constant <code>PUTATIVE_SELF_ROLE_MI="MI:0898"</code> */
    public static final String PUTATIVE_SELF_ROLE_MI = "MI:0898";
    /** Constant <code>SELF_ROLE="self"</code> */
    public static final String SELF_ROLE = "self";
    /** Constant <code>SELF_ROLE_MI="MI:0503"</code> */
    public static final String SELF_ROLE_MI = "MI:0503";
    /** Constant <code>BAIT_ROLE="bait"</code> */
    public static final String BAIT_ROLE = "bait";
    /** Constant <code>BAIT_ROLE_MI="MI:0496"</code> */
    public static final String BAIT_ROLE_MI = "MI:0496";
    /** Constant <code>FLUORESCENCE_DONOR_ROLE="fluorescence donor"</code> */
    public static final String FLUORESCENCE_DONOR_ROLE = "fluorescence donor";
    /** Constant <code>FLUORESCENCE_DONOR_ROLE_MI="MI:0583"</code> */
    public static final String FLUORESCENCE_DONOR_ROLE_MI = "MI:0583";
    /** Constant <code>FLUORESCENCE_ACCEPTOR_ROLE="fluorescence acceptor"</code> */
    public static final String FLUORESCENCE_ACCEPTOR_ROLE = "fluorescence acceptor";
    /** Constant <code>FLUORESCENCE_ACCEPTOR_ROLE_MI="MI:0584"</code> */
    public static final String FLUORESCENCE_ACCEPTOR_ROLE_MI = "MI:0584";
    /** Constant <code>SUPPRESSOR_GENE_ROLE="suppressor gene"</code> */
    public static final String SUPPRESSOR_GENE_ROLE = "suppressor gene";
    /** Constant <code>SUPPRESSOR_GENE_ROLE_MI="MI:0581"</code> */
    public static final String SUPPRESSOR_GENE_ROLE_MI = "MI:0581";
    /** Constant <code>SUPPRESSED_GENE_ROLE="suppressed gene"</code> */
    public static final String SUPPRESSED_GENE_ROLE = "suppressed gene";
    /** Constant <code>SUPPRESSED_GENE_ROLE_MI="MI:0582"</code> */
    public static final String SUPPRESSED_GENE_ROLE_MI = "MI:0582";
    /** Constant <code>ENZYME_ROLE_MI="MI:0501"</code> */
    public static final String ENZYME_ROLE_MI = "MI:0501";
    /** Constant <code>ENZYME_ROLE="enzyme"</code> */
    public static final String ENZYME_ROLE = "enzyme";
    /** Constant <code>ENZYME_TARGET_ROLE_MI="MI:0502"</code> */
    public static final String ENZYME_TARGET_ROLE_MI = "MI:0502";
    /** Constant <code>ENZYME_TARGET_ROLE="enzyme target"</code> */
    public static final String ENZYME_TARGET_ROLE = "enzyme target";
    /** Constant <code>ENZYME_REGULATOR_ROLE_MI="MI:1343"</code> */
    public static final String ENZYME_REGULATOR_ROLE_MI = "MI:1343";
    /** Constant <code>ENZYME_REGULATOR_ROLE="enzyme regulator"</code> */
    public static final String ENZYME_REGULATOR_ROLE = "enzyme regulator";
    /** Constant <code>DONOR_ROLE_MI="MI:0918"</code> */
    public static final String DONOR_ROLE_MI = "MI:0918";
    /** Constant <code>DONOR_ROLE="donor"</code> */
    public static final String DONOR_ROLE = "donor";
    /** Constant <code>ACCEPTOR_ROLE_MI="MI:0919"</code> */
    public static final String ACCEPTOR_ROLE_MI = "MI:0919";
    /** Constant <code>ACCEPTOR_ROLE="acceptor"</code> */
    public static final String ACCEPTOR_ROLE = "acceptor";
    /** Constant <code>ELECTRON_DONOR_ROLE_MI="MI:0579"</code> */
    public static final String ELECTRON_DONOR_ROLE_MI = "MI:0579";
    /** Constant <code>ELECTRON_DONOR_ROLE="electron donor"</code> */
    public static final String ELECTRON_DONOR_ROLE = "electron donor";
    /** Constant <code>ELECTRON_ACCEPTOR_ROLE_MI="MI:0580"</code> */
    public static final String ELECTRON_ACCEPTOR_ROLE_MI = "MI:0580";
    /** Constant <code>ELECTRON_ACCEPTOR_ROLE="electron acceptor"</code> */
    public static final String ELECTRON_ACCEPTOR_ROLE = "electron acceptor";
    /** Constant <code>PHOSPHATE_DONOR_ROLE_MI="MI:0842"</code> */
    public static final String PHOSPHATE_DONOR_ROLE_MI = "MI:0842";
    /** Constant <code>PHOSPHATE_DONOR_ROLE="phosphate donor"</code> */
    public static final String PHOSPHATE_DONOR_ROLE = "phosphate donor";
    /** Constant <code>PHOSPHATE_ACCEPTOR_ROLE_MI="MI:0843"</code> */
    public static final String PHOSPHATE_ACCEPTOR_ROLE_MI = "MI:0843";
    /** Constant <code>PHOSPHATE_ACCEPTOR_ROLE="phosphate acceptor"</code> */
    public static final String PHOSPHATE_ACCEPTOR_ROLE = "phosphate acceptor";
    /** Constant <code>PHOTON_DONOR_ROLE_MI="MI:1084"</code> */
    public static final String PHOTON_DONOR_ROLE_MI = "MI:1084";
    /** Constant <code>PHOTON_DONOR_ROLE="photon donor"</code> */
    public static final String PHOTON_DONOR_ROLE = "photon donor";
    /** Constant <code>PHOTON_ACCEPTOR_ROLE_MI="MI:1085"</code> */
    public static final String PHOTON_ACCEPTOR_ROLE_MI = "MI:1085";
    /** Constant <code>PHOTON_ACCEPTOR_ROLE="photon acceptor"</code> */
    public static final String PHOTON_ACCEPTOR_ROLE = "photon acceptor";
    /** Constant <code>PREY_MI="MI:0498"</code> */
    public static final String PREY_MI = "MI:0498";
    /** Constant <code>PREY="prey"</code> */
    public static final String PREY = "prey";
    /** Constant <code>NEUTRAL_MI="MI:0497"</code> */
    public static final String NEUTRAL_MI = "MI:0497";
    /** Constant <code>NEUTRAL="neutral component"</code> */
    public static final String NEUTRAL = "neutral component";
    /** Constant <code>INHIBITOR_MI="MI:0586"</code> */
    public static final String INHIBITOR_MI = "MI:0586";
    /** Constant <code>INHIBITOR="inhibitor"</code> */
    public static final String INHIBITOR = "inhibitor";
    /** Constant <code>INHIBITED="inhibited"</code> */
    public static final String INHIBITED = "inhibited";
    /** Constant <code>INHIBITED_MI="MI:0587"</code> */
    public static final String INHIBITED_MI = "MI:0587";
    /** Constant <code>PREDETERMINED_MI="MI:0396"</code> */
    public static final String PREDETERMINED_MI = "MI:0396";
    /** Constant <code>PREDETERMINED="predetermined"</code> */
    public static final String PREDETERMINED = "predetermined";

    /**
     * Sets the Interaction and add the new Participant to its list of Participants.
     * If the given interaction is null, it will remove the Participant from the previous interaction it was attached to
     *
     * @param interaction : interaction
     */
    public void setInteractionAndAddParticipant(I interaction);

    /**
     * The interaction in which the participant is involved.
     * It can be null if the participant is not attached to any interactions. It can happen if the participant has been removed from an interaction and is now invalid.
     *
     * @return the interaction
     */
    public I getInteraction();

    /**
     * Sets the interaction.
     *
     * @param interaction : interaction
     */
    public void setInteraction(I interaction);

    /**
     * The biological role of the participant.
     * It is a controlled vocabulary term and cannot be null.
     * It the biological role of a participant is not known or not relevant, the method should return
     * unspecified role (MI:0499)
     * Ex: enzyme, enzyme target, ...
     *
     * @return the biological role
     */
    public CvTerm getBiologicalRole();

    /**
     * Sets the biological role.
     * If the bioRole is null, should create a bioRole
     *
     * @param bioRole : biological role unspecified role (MI:0499)
     */
    public void setBiologicalRole(CvTerm bioRole);

    /**
     * The biological effect of the participant in a causal interaction.
     * It is a controlled vocabulary term and can be null.
     *
     * @return the biological effect
     */
    public CvTerm getBiologicalEffect();

    /**
     * Sets the biological effect of the participant in a causal interaction.
     *
     * @param biologicalEffect : biological effect
     */
    public void setBiologicalEffect(CvTerm biologicalEffect);

    /**
     * Collection of cross references which give more information about the participant.
     * The set of xrefs cannot be null. If the participant does not have any xrefs, the method should return an empty Collection.
     * Ex: author identifiers, ...
     *
     * @return the xrefs
     * @param <X> a X object
     */
    public <X extends Xref> Collection<X> getXrefs();

    /**
     * Collection of annotations describing the participant.
     * The set cannot be null. If the participant does not have any annotations, the method should return an empty Collection.
     *
     * @return the annotations
     * @param <A> an A object
     */
    public <A extends Annotation> Collection<A> getAnnotations();

    /**
     * Collection of aliases which give more information about the participant.
     * The set of aliases cannot be null. If the participant does not have any aliases, the method should return an empty Collection.
     * Ex: author assigned name, ...
     *
     * @return the xrefs
     * @param <A> an A object
     */
    public <A extends Alias> Collection<A> getAliases();
}
