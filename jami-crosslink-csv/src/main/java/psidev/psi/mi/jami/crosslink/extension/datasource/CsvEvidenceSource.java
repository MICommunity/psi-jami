package psidev.psi.mi.jami.crosslink.extension.datasource;

import psidev.psi.mi.jami.crosslink.io.parser.AbstractCsvInteractionEvidenceParser;
import psidev.psi.mi.jami.crosslink.io.parser.CsvInteractionEvidenceParser;
import psidev.psi.mi.jami.model.InteractionEvidence;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

/**
 * CrossLink CVS source of interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>26/08/14</pre>
 */
public class CsvEvidenceSource extends AbstractCsvSource<InteractionEvidence>{

    /**
     * <p>Constructor for CsvEvidenceSource.</p>
     */
    public CsvEvidenceSource() {
        super();
    }

    /**
     * <p>Constructor for CsvEvidenceSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public CsvEvidenceSource(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for CsvEvidenceSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public CsvEvidenceSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for CsvEvidenceSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public CsvEvidenceSource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for CsvEvidenceSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public CsvEvidenceSource(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractCsvInteractionEvidenceParser<InteractionEvidence> instantiateLineParser() {
        return new CsvInteractionEvidenceParser();
    }
}
