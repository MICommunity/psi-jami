package psidev.psi.mi.jami.tab.io.parser;

import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.tab.extension.MitabModelledInteraction;

import java.io.InputStream;
import java.io.Reader;

/**
 * An extension of MitabLineParser which parses only ModelledInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public class ModelledInteractionLineParser extends AbstractModelledInteractionLineParser<ModelledInteraction> {

    /**
     * <p>Constructor for ModelledInteractionLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     */
    public ModelledInteractionLineParser(InputStream stream) {
        super(stream);
    }

    /**
     * <p>Constructor for ModelledInteractionLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     * @param encoding a {@link java.lang.String} object.
     */
    public ModelledInteractionLineParser(InputStream stream, String encoding) {
        super(stream, encoding);
    }

    /**
     * <p>Constructor for ModelledInteractionLineParser.</p>
     *
     * @param stream a {@link java.io.Reader} object.
     */
    public ModelledInteractionLineParser(Reader stream) {
        super(stream);
    }

    /**
     * <p>Constructor for ModelledInteractionLineParser.</p>
     *
     * @param tm a {@link psidev.psi.mi.jami.tab.io.parser.MitabLineParserTokenManager} object.
     */
    public ModelledInteractionLineParser(MitabLineParserTokenManager tm) {
        super(tm);
    }

    /** {@inheritDoc} */
    @Override
    protected ModelledInteraction createInteraction() {
        return new MitabModelledInteraction();
    }
}
