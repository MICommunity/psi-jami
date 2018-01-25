package psidev.psi.mi.jami.tab.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.datasource.BinaryInteractionEvidenceSource;
import psidev.psi.mi.jami.model.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * A mitab datasource that loads interaction evidences (full experimental details).
 *
 * It will load the full interaction dataset
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class MitabBinaryEvidenceSource extends AbstractMitabSource<BinaryInteractionEvidence, ParticipantEvidence, FeatureEvidence> implements BinaryInteractionEvidenceSource{
    /**
     * <p>Constructor for MitabBinaryEvidenceSource.</p>
     */
    public MitabBinaryEvidenceSource() {
        super(new MitabBinaryEvidenceStreamSource());
    }

    /**
     * <p>Constructor for MitabBinaryEvidenceSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public MitabBinaryEvidenceSource(File file) throws IOException {
        super(new MitabBinaryEvidenceStreamSource(file));
    }

    /**
     * <p>Constructor for MitabBinaryEvidenceSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public MitabBinaryEvidenceSource(InputStream input) {
        super(new MitabBinaryEvidenceStreamSource(input));
    }

    /**
     * <p>Constructor for MitabBinaryEvidenceSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public MitabBinaryEvidenceSource(Reader reader) {
        super(new MitabBinaryEvidenceStreamSource(reader));
    }
}
