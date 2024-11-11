package psidev.psi.mi.jami.utils.comparator.participant;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.ModelledComparableParticipant;
import psidev.psi.mi.jami.model.Xref;

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

            int identifierComparisonResult = comparableParticipant1.getInteractorId()
                    .compareTo(comparableParticipant2.getInteractorId());

            if (identifierComparisonResult != 0) {
                boolean noIdMatch = comparableParticipant1.getIdentifiers()
                        .stream()
                        .noneMatch(id1 -> comparableParticipant2.getIdentifiers()
                                .stream()
                                .anyMatch(id2 -> identifiersMatch(id1, id2)));
                if (noIdMatch) {
                    if (comparableParticipant1.getIdentifiers().size() < comparableParticipant2.getIdentifiers().size()) {
                        return BEFORE;
                    } else if (comparableParticipant1.getIdentifiers().size() > comparableParticipant2.getIdentifiers().size()) {
                        return AFTER;
                    } else {
                        String id1 = comparableParticipant1.getIdentifiers()
                                .stream()
                                .map(Xref::getId)
                                .sorted()
                                .findFirst().orElse(comparableParticipant1.getInteractorId());
                        String id2 = comparableParticipant2.getIdentifiers()
                                .stream()
                                .map(Xref::getId)
                                .sorted()
                                .findFirst().orElse(comparableParticipant2.getInteractorId());
                        return id1.compareTo(id2);
                    }
                }
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

    private boolean identifiersMatch(Xref id1, Xref id2) {
        if (id1 == null && id2 == null) {
            return true;
        }
        if (id1 != null && id2 != null) {
            if (cvTermsMatch(id1.getDatabase(), id2.getDatabase())) {
                if (cvTermsMatch(id1.getQualifier(), id2.getQualifier())) {
                    return id1.getId().equals(id2.getId());
                }
            }
        }
        return false;
    }

    private boolean cvTermsMatch(CvTerm term1, CvTerm term2) {
        if (term1 == null) {
            return term2 == null;
        }
        if (term2 == null) {
            return false;
        }
        if (term1.getMIIdentifier() != null && term2.getMIIdentifier() != null) {
            return term1.getMIIdentifier().equals(term2.getMIIdentifier());
        }
        return term1.getShortName().equals(term2.getShortName());
    }
}
