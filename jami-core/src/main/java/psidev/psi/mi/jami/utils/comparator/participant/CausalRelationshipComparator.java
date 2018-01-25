package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.CausalRelationship;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Entity;

import java.util.Comparator;

/**
 * Basic comparator for CausalRelationship
 *
 * It will first compare the relationType using AbstractCvTermComparator. If both relationTypes are identical, it will compare the
 * target using ParticipantBaseComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/05/13</pre>
 */
public class CausalRelationshipComparator implements Comparator<CausalRelationship>{

    private Comparator<CvTerm> cvTermComparator;
    private EntityComparator participantComparator;

    /**
     * <p>Constructor for CausalRelationshipComparator.</p>
     *
     * @param cvTermComparator a {@link java.util.Comparator} object.
     * @param participantComparator a {@link psidev.psi.mi.jami.utils.comparator.participant.EntityComparator} object.
     */
    public CausalRelationshipComparator(Comparator<CvTerm> cvTermComparator, EntityComparator participantComparator){
        if (cvTermComparator == null){
            throw new IllegalArgumentException("The cvTermComparator cannot be null in a CausalRelationshipComparator");
        }
        this.cvTermComparator = cvTermComparator;

        if (participantComparator == null){
            throw new IllegalArgumentException("The entityBaseComparator cannot be null in a CausalRelationshipComparator");
        }
        this.participantComparator = participantComparator;
    }

    /**
     * <p>Getter for the field <code>cvTermComparator</code>.</p>
     *
     * @return a {@link java.util.Comparator} object.
     */
    public Comparator<CvTerm> getCvTermComparator() {
        return cvTermComparator;
    }

    /**
     * <p>Getter for the field <code>participantComparator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.comparator.participant.EntityComparator} object.
     */
    public EntityComparator getParticipantComparator() {
        return participantComparator;
    }

    /**
     * It will first compare the relationType using AbstractCvTermComparator. If both relationTypes are identical, it will compare the
     * target using ParticipantBaseComparator
     *
     * @param causalRelationship1 : first causal relationship
     * @param causalRelationship2 : second causal relationship
     * @return the comparison value
     */
    public int compare(CausalRelationship causalRelationship1, CausalRelationship causalRelationship2) {
        int EQUAL = 0;
        int BEFORE = -1;
        int AFTER = 1;

        if (causalRelationship1 == causalRelationship2){
            return EQUAL;
        }
        else if (causalRelationship1 == null){
            return AFTER;
        }
        else if (causalRelationship2 == null){
            return BEFORE;
        }
        else {
            CvTerm relationType1 = causalRelationship1.getRelationType();
            CvTerm relationType2 = causalRelationship2.getRelationType();

            int comp = cvTermComparator.compare(relationType1, relationType2);
            if (comp != 0){
                return comp;
            }

            Entity p1 = causalRelationship1.getTarget();
            Entity p2 = causalRelationship2.getTarget();

            return participantComparator.compare(p1, p2);
        }
    }
}
