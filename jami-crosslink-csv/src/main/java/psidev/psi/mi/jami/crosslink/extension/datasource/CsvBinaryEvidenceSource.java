package psidev.psi.mi.jami.crosslink.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.crosslink.io.parser.AbstractCsvInteractionEvidenceParser;
import psidev.psi.mi.jami.crosslink.io.parser.CsvBinaryInteractionEvidenceParser;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

/**
 * CrossLink CVS source of binary interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>26/08/14</pre>
 */
public class CsvBinaryEvidenceSource extends AbstractCsvSource<BinaryInteractionEvidence>{

    /**
     * <p>Constructor for CsvBinaryEvidenceSource.</p>
     */
    public CsvBinaryEvidenceSource() {
        super();
    }

    /**
     * <p>Constructor for CsvBinaryEvidenceSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public CsvBinaryEvidenceSource(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for CsvBinaryEvidenceSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public CsvBinaryEvidenceSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for CsvBinaryEvidenceSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public CsvBinaryEvidenceSource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for CsvBinaryEvidenceSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public CsvBinaryEvidenceSource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractCsvInteractionEvidenceParser<BinaryInteractionEvidence> instantiateLineParser() {
        return new CsvBinaryInteractionEvidenceParser();
    }
}
