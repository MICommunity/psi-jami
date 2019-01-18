package psidev.psi.mi.jami.tab.io.parser;

import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.tab.extension.MitabCvTerm;
import psidev.psi.mi.jami.tab.extension.MitabInteraction;

import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;

/**
 * An extension of MitabLineParser that returns simple interactions only.
 *
 * It ignore properties of InteractionEvidence and ModelledInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class InteractionLineParser extends AbstractLightInteractionLineParser<Interaction> {

    /**
     * <p>Constructor for InteractionLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     */
    public InteractionLineParser(InputStream stream) {
        super(stream);
    }

    /**
     * <p>Constructor for InteractionLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     * @param encoding a {@link java.lang.String} object.
     */
    public InteractionLineParser(InputStream stream, String encoding) {
        super(stream, encoding);
    }

    /**
     * <p>Constructor for InteractionLineParser.</p>
     *
     * @param stream a {@link java.io.Reader} object.
     */
    public InteractionLineParser(Reader stream) {
        super(stream);
    }

    /**
     * <p>Constructor for InteractionLineParser.</p>
     *
     * @param tm a {@link psidev.psi.mi.jami.tab.io.parser.MitabLineParserTokenManager} object.
     */
    public InteractionLineParser(MitabLineParserTokenManager tm) {
        super(tm);
    }

    /** {@inheritDoc} */
    protected void addParticipant(Participant participant, Interaction interaction) {
        interaction.addParticipant(participant);
    }

    /** {@inheritDoc} */
    @Override
    protected Interaction createInteraction() {
        return new MitabInteraction();
    }

    @Override
    Interaction finishCausalInteraction(Interaction interaction, Collection<MitabCvTerm> causalStatement, Collection<MitabCvTerm> causalRegMechanism) {
        return interaction;
    }
}
