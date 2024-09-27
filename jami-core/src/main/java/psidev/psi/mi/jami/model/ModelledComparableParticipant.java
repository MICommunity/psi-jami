package psidev.psi.mi.jami.model;

public class ModelledComparableParticipant {

    private String interactorId;
    private int stoichiometry;
    private CvTerm interactorType;

    public ModelledComparableParticipant() {
    }

    public ModelledComparableParticipant(String interactorId, int stoichiometry, CvTerm interactorType) {
        this.interactorId = interactorId;
        this.stoichiometry = stoichiometry;
        this.interactorType = interactorType;
    }

    public String getInteractorId() {
        return interactorId;
    }

    public void setInteractorId(String interactorId) {
        this.interactorId = interactorId;
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
