package psidev.psi.mi.jami.tab.io.parser;

import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.tab.extension.MitabInteractionEvidence;

import java.io.InputStream;
import java.io.Reader;

/**
 * An extension of MitabLineParser that returns interactions evidences only.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/06/13</pre>
 */
public class InteractionEvidenceLineParser extends AbstractInteractionEvidenceLineParser<InteractionEvidence> {

    /**
     * <p>Constructor for InteractionEvidenceLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     */
    public InteractionEvidenceLineParser(InputStream stream) {
        super(stream);
    }

    /**
     * <p>Constructor for InteractionEvidenceLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     * @param encoding a {@link java.lang.String} object.
     */
    public InteractionEvidenceLineParser(InputStream stream, String encoding) {
        super(stream, encoding);
    }

    /**
     * <p>Constructor for InteractionEvidenceLineParser.</p>
     *
     * @param stream a {@link java.io.Reader} object.
     */
    public InteractionEvidenceLineParser(Reader stream) {
        super(stream);
    }

    /**
     * <p>Constructor for InteractionEvidenceLineParser.</p>
     *
     * @param tm a {@link psidev.psi.mi.jami.tab.io.parser.MitabLineParserTokenManager} object.
     */
    public InteractionEvidenceLineParser(MitabLineParserTokenManager tm) {
        super(tm);
    }

    /** {@inheritDoc} */
    @Override
    protected InteractionEvidence createInteraction() {
        return new MitabInteractionEvidence();
    }
}
