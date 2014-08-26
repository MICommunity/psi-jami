package psidev.psi.mi.jami.crosslink.extension.datasource;

import psidev.psi.mi.jami.crosslink.io.parser.AbstractCsvInteractionEvidenceParser;
import psidev.psi.mi.jami.crosslink.io.parser.CsvInteractionEvidenceParser;
import psidev.psi.mi.jami.model.InteractionEvidence;

/**
 * CrossLink CVS source of interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>26/08/14</pre>
 */

public class CvsEvidenceSource extends AbstractCsvSource<InteractionEvidence>{
    @Override
    protected AbstractCsvInteractionEvidenceParser<InteractionEvidence> instantiateLineParser() {
        return new CsvInteractionEvidenceParser();
    }
}