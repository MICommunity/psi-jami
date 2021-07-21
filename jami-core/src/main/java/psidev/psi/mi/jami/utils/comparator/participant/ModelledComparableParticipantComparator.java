package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.ModelledComparableParticipant;

import java.util.Comparator;

public class ModelledComparableParticipantComparator implements Comparator<ModelledComparableParticipant> {

    private boolean ignoreStoichiometry;

    @Override
    public int compare(ModelledComparableParticipant comparableParticipant1, ModelledComparableParticipant comparableParticipant2) {
        int EQUAL = 0;
        int BEFORE = -1;
        int AFTER = 1;

        if (comparableParticipant1 == comparableParticipant2) {
            return EQUAL;
        } else if (comparableParticipant1 == null) {
            return AFTER;
        } else if (comparableParticipant2 == null) {
            return BEFORE;
        } else {

            int identifierComparisonResult = comparableParticipant1.getInteractorPreferredIdentifier()
                    .compareTo(comparableParticipant2.getInteractorPreferredIdentifier());

            if (identifierComparisonResult != 0) {
                return identifierComparisonResult;
            }

            if (!isIgnoreStoichiometry()) {
                return Integer.compare(comparableParticipant1.getStoichiometry(), comparableParticipant2.getStoichiometry());
            }

            return EQUAL;
        }
    }

    public boolean isIgnoreStoichiometry() {
        return ignoreStoichiometry;
    }

    public void setIgnoreStoichiometry(boolean ignoreStoichiometry) {
        this.ignoreStoichiometry = ignoreStoichiometry;
    }
}
