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
 * CrossLink CVS stream source of interaction evidences
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>26/08/14</pre>
 */
public class CsvEvidenceStreamSource extends AbstractCsvStreamSource<InteractionEvidence>{

    /**
     * <p>Constructor for CsvEvidenceStreamSource.</p>
     */
    public CsvEvidenceStreamSource() {
        super();
    }

    /**
     * <p>Constructor for CsvEvidenceStreamSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public CsvEvidenceStreamSource(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for CsvEvidenceStreamSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public CsvEvidenceStreamSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for CsvEvidenceStreamSource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public CsvEvidenceStreamSource(URL url) {
        super(url);
    }

    /**
     * <p>Constructor for CsvEvidenceStreamSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public CsvEvidenceStreamSource(Reader reader) {
        super(reader);
    }

    /** {@inheritDoc} */
    @Override
    protected AbstractCsvInteractionEvidenceParser<InteractionEvidence> instantiateLineParser() {
        return new CsvInteractionEvidenceParser();
    }
}
