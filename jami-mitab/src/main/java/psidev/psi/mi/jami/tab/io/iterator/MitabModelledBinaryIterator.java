package psidev.psi.mi.jami.tab.io.iterator;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.tab.io.parser.MitabLineParser;

/**
 * A MItab iterator of modelled BinaryInteractions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/06/13</pre>
 */
public class MitabModelledBinaryIterator extends AbstractMitabIterator<ModelledBinaryInteraction, ModelledParticipant, ModelledFeature>{
    /**
     * <p>Constructor for MitabModelledBinaryIterator.</p>
     *
     * @param lineParser a {@link psidev.psi.mi.jami.tab.io.parser.MitabLineParser} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public MitabModelledBinaryIterator(MitabLineParser<ModelledBinaryInteraction, ModelledParticipant, ModelledFeature> lineParser) throws MIIOException {
        super(lineParser);
    }
}
