package psidev.psi.mi.jami.tab.io.parser;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.tab.extension.*;

import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;

/**
 * An extension of MitabLineParser that returns simple binary interactions only.
 *
 * It ignore properties of BinaryInteractionEvidence and ModelledBinaryInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/07/13</pre>
 */
public class BinaryLineParser extends AbstractLightInteractionLineParser<BinaryInteraction>{
    /**
     * <p>Constructor for BinaryLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     */
    public BinaryLineParser(InputStream stream) {
        super(stream);
    }

    /**
     * <p>Constructor for BinaryLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     * @param encoding a {@link java.lang.String} object.
     */
    public BinaryLineParser(InputStream stream, String encoding) {
        super(stream, encoding);
    }

    /**
     * <p>Constructor for BinaryLineParser.</p>
     *
     * @param stream a {@link java.io.Reader} object.
     */
    public BinaryLineParser(Reader stream) {
        super(stream);
    }

    /**
     * <p>Constructor for BinaryLineParser.</p>
     *
     * @param tm a {@link psidev.psi.mi.jami.tab.io.parser.MitabLineParserTokenManager} object.
     */
    public BinaryLineParser(MitabLineParserTokenManager tm) {
        super(tm);
    }

    /** {@inheritDoc} */
    @Override
    protected MitabBinaryInteraction createInteraction() {
        return new MitabBinaryInteraction();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExpansionMethod(Collection<MitabCvTerm> expansion, BinaryInteraction interaction) {
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

    /** {@inheritDoc} */
    @Override
    protected void addParticipant(Participant participant, BinaryInteraction interaction) {
        if (interaction.getParticipantA() != null){
            interaction.setParticipantB(participant);
        }
        else {
            interaction.setParticipantA(participant);
        }
    }

    MitabParticipant finishParticipant(Collection<MitabXref> uniqueId, Collection<MitabXref> altid, Collection<MitabAlias> aliases,
                                       Collection<MitabOrganism> taxid, Collection<MitabCvTerm> bioRole, Collection<MitabCvTerm> expRole,
                                       Collection<MitabCvTerm> type, Collection<MitabXref> xref, Collection<MitabAnnotation> annot,
                                       Collection<MitabChecksum> checksum, Collection<Feature> feature, Collection<MitabStoichiometry> stc,
                                       Collection<MitabCvTerm> detMethod, Collection<MitabCvTerm> bioeffect,
                                       int line, int column, int mitabColumn) {
        MitabParticipant participant = super.finishParticipant(uniqueId, altid, aliases, taxid, bioRole, expRole, type, xref, annot,
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

    BinaryInteraction finishCausalInteraction(BinaryInteraction interaction, Collection<MitabCvTerm> causalStatement, Collection<MitabCvTerm> causalRegMechanism) {
        if (interaction == null) return null;

        Participant participantA = interaction.getParticipantA();
        Participant participantB = interaction.getParticipantB();

        // add the causalStatement in participant A with target B and relationType the causalStatement
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
            interaction.setCausalRegulatoryMechanism(causalRegulatoryMechanism);
        } else if (!causalRegMechanism.isEmpty()) {
            CvTerm causalRegulatoryMechanism = causalRegMechanism.iterator().next();
            interaction.setCausalRegulatoryMechanism(causalRegulatoryMechanism);
        }

        return interaction;
    }
}
