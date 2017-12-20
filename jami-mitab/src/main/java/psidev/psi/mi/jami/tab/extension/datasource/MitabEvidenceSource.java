package psidev.psi.mi.jami.tab.extension.datasource;

import psidev.psi.mi.jami.datasource.InteractionEvidenceSource;
import psidev.psi.mi.jami.model.FeatureEvidence;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.ParticipantEvidence;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * A mitab datasource that loads interaction evidences (full experimental details)
 *
 * It will load the full interaction dataset
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class MitabEvidenceSource extends AbstractMitabSource<InteractionEvidence, ParticipantEvidence, FeatureEvidence> implements InteractionEvidenceSource<InteractionEvidence>{
    /**
     * <p>Constructor for MitabEvidenceSource.</p>
     */
    public MitabEvidenceSource() {
        super(new MitabEvidenceStreamSource());
    }

    /**
     * <p>Constructor for MitabEvidenceSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public MitabEvidenceSource(File file) throws IOException {
        super(new MitabEvidenceStreamSource(file));
    }

    /**
     * <p>Constructor for MitabEvidenceSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public MitabEvidenceSource(InputStream input) {
        super(new MitabEvidenceStreamSource(input));
    }

    /**
     * <p>Constructor for MitabEvidenceSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public MitabEvidenceSource(Reader reader) {
        super(new MitabEvidenceStreamSource(reader));
    }
}
