package psidev.psi.mi.jami.model;

import java.util.Collection;

/**
 * A cooperative effect an interaction has on a subsequent interaction.
 *
 * A molecular binding event that influences each other either positively or negatively through allostery or pre-assembly.
 * In this context, covalent post-translational modifications are considered as binding events.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/05/13</pre>
 */
public interface CooperativeEffect {

    /** Constant <code>ALLOSTERY="allostery"</code> */
    public static final String ALLOSTERY = "allostery";
    /** Constant <code>ALLOSTERY_ID="MI:1157"</code> */
    public static final String ALLOSTERY_ID = "MI:1157";
    /** Constant <code>PREASSEMBLY="pre-assembly"</code> */
    public static final String PREASSEMBLY = "pre-assembly";
    /** Constant <code>PREASSEMBLY_ID="MI:1158"</code> */
    public static final String PREASSEMBLY_ID = "MI:1158";
    /** Constant <code>AFFECTED_INTERACTION="affected interaction"</code> */
    public static final String AFFECTED_INTERACTION = "affected interaction";
    /** Constant <code>AFFECTED_INTERACTION_ID="MI:1150"</code> */
    public static final String AFFECTED_INTERACTION_ID = "MI:1150";
    /** Constant <code>NEGATIVE_EFFECT="negative cooperative effect"</code> */
    public static final String NEGATIVE_EFFECT = "negative cooperative effect";
    /** Constant <code>NEGATIVE_EFFECT_ID="MI:1155"</code> */
    public static final String NEGATIVE_EFFECT_ID = "MI:1155";
    /** Constant <code>POSITIVE_EFFECT="positive cooperative effect"</code> */
    public static final String POSITIVE_EFFECT = "positive cooperative effect";
    /** Constant <code>POSITIVE_EFFECT_ID="MI:1154"</code> */
    public static final String POSITIVE_EFFECT_ID = "MI:1154";
    /** Constant <code>ALLOSTERIC_EFFECTOR="allosteric effector"</code> */
    public static final String ALLOSTERIC_EFFECTOR = "allosteric effector";
    /** Constant <code>ALLOSTERIC_EFFECTOR_ID="MI:1160"</code> */
    public static final String ALLOSTERIC_EFFECTOR_ID = "MI:1160";
    /** Constant <code>ALLOSTERIC_MOLECULE="allosteric molecule"</code> */
    public static final String ALLOSTERIC_MOLECULE = "allosteric molecule";
    /** Constant <code>ALLOSTERIC_MOLECULE_ID="MI:1159"</code> */
    public static final String ALLOSTERIC_MOLECULE_ID = "MI:1159";
    /** Constant <code>ALLOSTERIC_K_RESPONSE="allosteric k-type response"</code> */
    public static final String ALLOSTERIC_K_RESPONSE = "allosteric k-type response";
    /** Constant <code>ALLOSTERIC_K_RESPONSE_ID="MI:1162"</code> */
    public static final String ALLOSTERIC_K_RESPONSE_ID = "MI:1162";
    /** Constant <code>ALLOSTERIC_V_RESPONSE="allosteric v-type response"</code> */
    public static final String ALLOSTERIC_V_RESPONSE = "allosteric v-type response";
    /** Constant <code>ALLOSTERIC_V_RESPONSE_ID="MI:1163"</code> */
    public static final String ALLOSTERIC_V_RESPONSE_ID = "MI:1163";
    /** Constant <code>HETEROTROPIC_ALLOSTERY="heterotropic allostery"</code> */
    public static final String HETEROTROPIC_ALLOSTERY = "heterotropic allostery";
    /** Constant <code>HETEROTROPIC_ALLOSTERY_ID="MI:1168"</code> */
    public static final String HETEROTROPIC_ALLOSTERY_ID = "MI:1168";
    /** Constant <code>HOMOTROPIC_ALLOSTERY="homotropic allostery"</code> */
    public static final String HOMOTROPIC_ALLOSTERY = "homotropic allostery";
    /** Constant <code>HOMOTROPIC_ALLOSTERY_ID="MI:1169"</code> */
    public static final String HOMOTROPIC_ALLOSTERY_ID = "MI:1169";
    /** Constant <code>COOPERATIVE_EFFECT_VALUE="cooperative effect value"</code> */
    public static final String COOPERATIVE_EFFECT_VALUE = "cooperative effect value";
    /** Constant <code>COOPERATIVE_EFFECT_VALUE_ID="MI:1152"</code> */
    public static final String COOPERATIVE_EFFECT_VALUE_ID = "MI:1152";
    /** Constant <code>ALLOSTERIC_DYNAMIC_CHANGE="allosteric change in dynamics"</code> */
    public static final String ALLOSTERIC_DYNAMIC_CHANGE = "allosteric change in dynamics";
    /** Constant <code>ALLOSTERIC_DYNAMIC_CHANGE_ID="MI:1166"</code> */
    public static final String ALLOSTERIC_DYNAMIC_CHANGE_ID = "MI:1166";
    /** Constant <code>ALTERED_PHYSICO_COMPATIBILITY="altered physicochemical compatibility"</code> */
    public static final String ALTERED_PHYSICO_COMPATIBILITY = "altered physicochemical compatibility";
    /** Constant <code>ALTERED_PHYSICO_COMPATIBILITY_ID="MI:1172"</code> */
    public static final String ALTERED_PHYSICO_COMPATIBILITY_ID = "MI:1172";
    /** Constant <code>BINDING_HIDING="binding site hiding"</code> */
    public static final String BINDING_HIDING = "binding site hiding";
    /** Constant <code>BINDING_HIDING_ID="MI:1173"</code> */
    public static final String BINDING_HIDING_ID = "MI:1173";
    /** Constant <code>COMPOSITE_BINDING="composite binding site formation"</code> */
    public static final String COMPOSITE_BINDING = "composite binding site formation";
    /** Constant <code>COMPOSITE_BINDING_ID="MI:1171"</code> */
    public static final String COMPOSITE_BINDING_ID = "MI:1171";
    /** Constant <code>ALLOSTERIC_STRUCTURE_CHANE="allosteric change in structure"</code> */
    public static final String ALLOSTERIC_STRUCTURE_CHANE = "allosteric change in structure";
    /** Constant <code>ALLOSTERIC_STRUCTURE_CHANE_ID="MI:1165"</code> */
    public static final String ALLOSTERIC_STRUCTURE_CHANE_ID = "MI:1165";
    /** Constant <code>PRE_ORGANIZATION="configurational pre-organization"</code> */
    public static final String PRE_ORGANIZATION = "configurational pre-organization";
    /** Constant <code>PRE_ORGANIZATION_ID="MI:1174"</code> */
    public static final String PRE_ORGANIZATION_ID = "MI:1174";
    /** Constant <code>ALLOSTERIC_PTM="allosteric ptm"</code> */
    public static final String ALLOSTERIC_PTM = "allosteric ptm";
    /** Constant <code>ALLOSTERIC_PTM_ID="MI:1175"</code> */
    public static final String ALLOSTERIC_PTM_ID = "MI:1175";
    /** Constant <code>PARTICIPANT_REF="participant-ref"</code> */
    public static final String PARTICIPANT_REF = "participant-ref";
    /** Constant <code>PARTICIPANT_REF_ID="MI:1151"</code> */
    public static final String PARTICIPANT_REF_ID = "MI:1151";
    /** Constant <code>EFFECT_OUTCOME_MI="MI:1153"</code> */
    public static final String EFFECT_OUTCOME_MI ="MI:1153";
    /** Constant <code>EFFECT_OUTCOME="cooperative effect outcome"</code> */
    public static final String EFFECT_OUTCOME ="cooperative effect outcome";

    /**
     * Collection of experimental methods and publications from which this cooperative effect has been inferred.
     * The collection cannot be null, if the CooperativeEffect does not have any cooperativityEvidences, this method should
     * return an empty collection.
     *
     * @return Collection of experimental methods and publications
     * @param <T> a T object.
     */
    public <T extends CooperativityEvidence> Collection<T> getCooperativityEvidences();

    /**
     * Collection of model interactions affected by this model interaction.
     * The collection cannot be null, if the CooperativeEffect does not have any affectedInteractions, this method should
     * return an empty collection.
     *
     * @return collection of model interactions affected by this model interaction
     * @param <T> a T object.
     */
    public <T extends ModelledInteraction> Collection<T> getAffectedInteractions();

    /**
     * The Collection of annotations describing the cooperativeEffect.
     * The Collection cannot be null. If the cooperativeEffect does not have any annotations, the method should return an empty Collection.
     * Ex: comments, cautions, ...
     *
     * @return the annotations
     * @param <T> a T object.
     */
    public <T extends Annotation> Collection<T> getAnnotations();

    /**
     * For an interaction that has a cooperative effect on a subsequent interaction, this term indicates whether this effect is
     * positive or negative, i.e. whether the subsequent interaction is augmented or diminished.
     * It is a controlled vocabulary term and cannot be null.
     *
     * @return cooperative effect outcome
     */
    public CvTerm getOutCome();

    /**
     * Sets the cooperative effect outcome
     *
     * @param effect : cooperative effect outcome
     * @throws java.lang.IllegalArgumentException when effect is null
     */
    public void setOutCome(CvTerm effect);

    /**
     * This term describes the cooperative effect on the interactions.
     * It could be a pre-assembly response or an allosteric response.
     * The pre-assembly response describes the way in which preformation of a molecular
     * complex has a non-allosteric cooperative effect on subsequent interactions of its components.
     * The allosteric response describes the effect of an allosteric binding event. It specifies which properties of
     * the allosteric molecule are altered, i.e. whether the interaction alters either (a) binding or (b) catalytic
     * properties of the allosteric molecule at a site distinct from the allosteric site.
     * The response is a controlled vocabulary term and can be null if it is not known.
     * Ex: binding site hiding, allosteric k-type response, ...
     *
     * @return the response
     */
    public CvTerm getResponse();

    /**
     * Sets the response.
     *
     * @param response : the allosteric or pre-assembly response
     */
    public void setResponse(CvTerm response);
}
