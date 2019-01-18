package psidev.psi.mi.jami.tab.io.parser;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.tab.extension.*;

import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;

/**
 * An extension of MitabLineParser that returns binary interactions evidences only.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/07/13</pre>
 */
public class BinaryEvidenceLineParser extends AbstractInteractionEvidenceLineParser<BinaryInteractionEvidence>{
    /**
     * <p>Constructor for BinaryEvidenceLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     */
    public BinaryEvidenceLineParser(InputStream stream) {
        super(stream);
    }

    /**
     * <p>Constructor for BinaryEvidenceLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     * @param encoding a {@link java.lang.String} object.
     */
    public BinaryEvidenceLineParser(InputStream stream, String encoding) {
        super(stream, encoding);
    }

    /**
     * <p>Constructor for BinaryEvidenceLineParser.</p>
     *
     * @param stream a {@link java.io.Reader} object.
     */
    public BinaryEvidenceLineParser(Reader stream) {
        super(stream);
    }

    /**
     * <p>Constructor for BinaryEvidenceLineParser.</p>
     *
     * @param tm a {@link psidev.psi.mi.jami.tab.io.parser.MitabLineParserTokenManager} object.
     */
    public BinaryEvidenceLineParser(MitabLineParserTokenManager tm) {
        super(tm);
    }

    /** {@inheritDoc} */
    @Override
    protected MitabBinaryInteractionEvidence createInteraction() {
        return new MitabBinaryInteractionEvidence();
    }

    MitabParticipantEvidence finishParticipant(Collection<MitabXref> uniqueId, Collection<MitabXref> altid, Collection<MitabAlias> aliases,
                                               Collection<MitabOrganism> taxid, Collection<MitabCvTerm> bioRole, Collection<MitabCvTerm> expRole,
                                               Collection<MitabCvTerm> type, Collection<MitabXref> xref, Collection<MitabAnnotation> annot,
                                               Collection<MitabChecksum> checksum, Collection<FeatureEvidence> feature,
                                               Collection<MitabStoichiometry> stc, Collection<MitabCvTerm> detMethod,
                                               Collection<MitabCvTerm> bioeffect, int line, int column, int mitabColumn) {
        MitabParticipantEvidence participant = super.finishParticipant(uniqueId, altid, aliases, taxid, bioRole, expRole, type, xref, annot,
                checksum, feature, stc, detMethod, bioeffect, line, column, mitabColumn);
        if (participant != null) {
            // set biological effect
            if (bioeffect.size() > 1) {
                if (getParserListener() != null) {
                    getParserListener().onSeveralCvTermsFound(bioeffect, bioeffect.iterator().next(),
                            bioeffect.size() + " biological effects found in one participant. Only the first one will be loaded");
                }
                MitabCvTerm bioeffectTerm = bioeffect.iterator().next();
                participant.setBiologicalEffect(bioeffectTerm);
            }
            else if (!bioeffect.isEmpty()) {
                MitabCvTerm bioeffectTerm = bioeffect.iterator().next();
                participant.setBiologicalEffect(bioeffectTerm);
            }
        }
        return participant;
    }

    @Override
    BinaryInteractionEvidence finishCausalInteraction(BinaryInteractionEvidence interactionEvidence, Collection<MitabCvTerm> causalStatement, Collection<MitabCvTerm> causalRegMechanism) {
        if (interactionEvidence == null) return null;

        ParticipantEvidence participantA = interactionEvidence.getParticipantA();
        ParticipantEvidence participantB = interactionEvidence.getParticipantB();

        // add the causalStatement in participant A (relationType) with target the participant B
        if (causalStatement.size() > 1) {
            if (getParserListener() != null) {
                getParserListener().onSeveralCvTermsFound(causalStatement, causalStatement.iterator().next(),
                        causalStatement.size() + " causal statements found. Only the first one will be loaded.");
            }
            CvTerm causalStat = causalStatement.iterator().next();
            participantA.getCausalRelationships().add(new MitabCausalRelationship(causalStat, participantB));
        } else if (!causalStatement.isEmpty()) {
            CvTerm causalStat = causalStatement.iterator().next();
            participantA.getCausalRelationships().add(new MitabCausalRelationship(causalStat, participantB));
        }

        // add the causalRegulatoryMechanism
        if (causalRegMechanism.size() > 1) {
            if (getParserListener() != null) {
                getParserListener().onSeveralCvTermsFound(causalRegMechanism, causalRegMechanism.iterator().next(),
                        causalRegMechanism.size() + " causal regulatory mechanisms found. Only the first one will be loaded.");
            }
            CvTerm causalRegulatoryMechanism = causalRegMechanism.iterator().next();
            interactionEvidence.setCausalRegulatoryMechanism(causalRegulatoryMechanism);
        } else if (!causalRegMechanism.isEmpty()) {
            CvTerm causalRegulatoryMechanism = causalRegMechanism.iterator().next();
            interactionEvidence.setCausalRegulatoryMechanism(causalRegulatoryMechanism);
        }

        return interactionEvidence;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExpansionMethod(Collection<MitabCvTerm> expansion, BinaryInteractionEvidence interaction) {
        if (expansion.size() > 1){
            if (getParserListener() != null){
                getParserListener().onSeveralCvTermsFound(expansion, expansion.iterator().next(), expansion.size()+" complex expansions found. Only the first one will be loaded.");
            }
            interaction.setComplexExpansion(expansion.iterator().next());
        }
        else if (!expansion.isEmpty()){
            interaction.setComplexExpansion(expansion.iterator().next());
        }
    }
}
