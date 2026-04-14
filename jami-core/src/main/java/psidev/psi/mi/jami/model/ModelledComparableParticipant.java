package psidev.psi.mi.jami.model;

import java.util.Collection;

public class ModelledComparableParticipant {

    private String interactorId;
    private Collection<Xref> identifiers;
    private int stoichiometry;
    private CvTerm interactorType;

    public ModelledComparableParticipant() {
    }

    public ModelledComparableParticipant(
            String interactorId,
            Collection<Xref> identifiers,
            int stoichiometry,
            CvTerm interactorType) {

        this.interactorId = interactorId;
        this.identifiers = identifiers;
        this.stoichiometry = stoichiometry;
        this.interactorType = interactorType;
    }

    public String getInteractorId() {
        return interactorId;
    }

    public void setInteractorId(String interactorId) {
        this.interactorId = interactorId;
    }

    public Collection<Xref> getIdentifiers() {
        return identifiers;
    }

    public void setIdentifiers(Collection<Xref> identifiers) {
        this.identifiers = identifiers;
    }

    public int getStoichiometry() {
        return stoichiometry;
    }

    public void setStoichiometry(int stoichiometry) {
        this.stoichiometry = stoichiometry;
    }

    public CvTerm getInteractorType() {
        return interactorType;
    }

    public void setInteractorType(CvTerm interactorType) {
        this.interactorType = interactorType;
    }
}
