package psidev.psi.mi.jami.model;

public class ModelledComparableParticipant {

    private String proteinId;// only uniprot or intact ac
    private int stoichiometry;

    public ModelledComparableParticipant() {
    }

    public ModelledComparableParticipant(String proteinId, int stoichiometry) {
        this.proteinId = proteinId;
        this.stoichiometry = stoichiometry;
    }

    public String getProteinId() {
        return proteinId;
    }

    public void setProteinId(String proteinId) {
        this.proteinId = proteinId;
    }

    public int getStoichiometry() {
        return stoichiometry;
    }

    public void setStoichiometry(int stoichiometry) {
        this.stoichiometry = stoichiometry;
    }
}
