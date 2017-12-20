package psidev.psi.mi.jami.tab.io.parser;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.tab.extension.MitabCvTerm;
import psidev.psi.mi.jami.tab.extension.MitabModelledBinaryInteraction;

import java.io.InputStream;
import java.io.Reader;
import java.util.Collection;

/**
 * An extension of MitabLineParser which parses only ModelledBinaryInteraction
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>04/07/13</pre>
 */
public class ModelledBinaryLineParser extends AbstractModelledInteractionLineParser<ModelledBinaryInteraction> {
    /**
     * <p>Constructor for ModelledBinaryLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     */
    public ModelledBinaryLineParser(InputStream stream) {
        super(stream);
    }

    /**
     * <p>Constructor for ModelledBinaryLineParser.</p>
     *
     * @param stream a {@link java.io.InputStream} object.
     * @param encoding a {@link java.lang.String} object.
     */
    public ModelledBinaryLineParser(InputStream stream, String encoding) {
        super(stream, encoding);
    }

    /**
     * <p>Constructor for ModelledBinaryLineParser.</p>
     *
     * @param stream a {@link java.io.Reader} object.
     */
    public ModelledBinaryLineParser(Reader stream) {
        super(stream);
    }

    /**
     * <p>Constructor for ModelledBinaryLineParser.</p>
     *
     * @param tm a {@link psidev.psi.mi.jami.tab.io.parser.MitabLineParserTokenManager} object.
     */
    public ModelledBinaryLineParser(MitabLineParserTokenManager tm) {
        super(tm);
    }

    /** {@inheritDoc} */
    @Override
    protected MitabModelledBinaryInteraction createInteraction() {
        return new MitabModelledBinaryInteraction();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExpansionMethod(Collection<MitabCvTerm> expansion, ModelledBinaryInteraction interaction) {
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
