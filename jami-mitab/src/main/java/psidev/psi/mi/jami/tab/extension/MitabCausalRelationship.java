package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.impl.DefaultCausalRelationship;

public class MitabCausalRelationship extends DefaultCausalRelationship implements FileSourceContext {

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for MitabCausalRelationship.</p>
     *
     * @param relationType a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param target a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public MitabCausalRelationship(CvTerm relationType, Participant target){
        super(relationType, target);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return this.sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Causal Relationship: " + getSourceLocator().toString()
                : super.toString());
    }
}
