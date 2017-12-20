package psidev.psi.mi.jami.enricher;


import psidev.psi.mi.jami.enricher.impl.CompositeEntityEnricher;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.ParticipantPool;

/**
 * Sub enrichers: Protein, CvTerm, Feature, Bioactive3Entity
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since  13/06/13

 */
public interface ParticipantPoolEnricher<P extends ParticipantPool, F extends Feature> extends ParticipantEnricher<P,F>{

    /**
     * <p>getParticipantCandidateEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.CompositeEntityEnricher} object.
     */
    public CompositeEntityEnricher getParticipantCandidateEnricher();

    /**
     * <p>setParticipantCandidateEnricher.</p>
     *
     * @param enricher a {@link psidev.psi.mi.jami.enricher.impl.CompositeEntityEnricher} object.
     */
    public void setParticipantCandidateEnricher(CompositeEntityEnricher enricher);
}
