package psidev.psi.mi.jami.model;

public class ModelledComparableParticipant {

    private String interactorPreferredIdentifier;
    private int stoichiometry;

    public String getInteractorPreferredIdentifier() {
        return interactorPreferredIdentifier;
    }

    public void setInteractorPreferredIdentifier(String interactorPreferredIdentifier) {
        this.interactorPreferredIdentifier = interactorPreferredIdentifier;
    }

    public int getStoichiometry() {
        return stoichiometry;
    }

    public void setStoichiometry(int stoichiometry) {
        this.stoichiometry = stoichiometry;
    }
}
