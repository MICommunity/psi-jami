package psidev.psi.mi.jami.tab.io.iterator;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.tab.io.parser.MitabLineParser;

/**
 * A MItab iterator of BinaryInteraction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/06/13</pre>
 */
public class MitabBinaryEvidenceIterator extends AbstractMitabIterator<BinaryInteractionEvidence, ParticipantEvidence, FeatureEvidence>{
    /**
     * <p>Constructor for MitabBinaryEvidenceIterator.</p>
     *
     * @param lineParser a {@link psidev.psi.mi.jami.tab.io.parser.MitabLineParser} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public MitabBinaryEvidenceIterator(MitabLineParser<BinaryInteractionEvidence, ParticipantEvidence, FeatureEvidence> lineParser) throws MIIOException {
        super(lineParser);
    }
}
