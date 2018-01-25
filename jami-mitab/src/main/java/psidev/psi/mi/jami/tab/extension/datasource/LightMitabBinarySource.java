package psidev.psi.mi.jami.tab.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.datasource.BinaryInteractionSource;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Participant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * A mitab datasource that loads very basic binary interactions and ignore experimental details, source, confidence and experimental details.
 *
 * It loads the full interaction dataset.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class LightMitabBinarySource extends AbstractMitabSource<BinaryInteraction, Participant, Feature> implements BinaryInteractionSource<BinaryInteraction>{
    /**
     * <p>Constructor for LightMitabBinarySource.</p>
     */
    public LightMitabBinarySource() {
        super(new LightMitabBinaryStreamSource());
    }

    /**
     * <p>Constructor for LightMitabBinarySource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public LightMitabBinarySource(File file) throws IOException {
        super(new LightMitabBinaryStreamSource(file));
    }

    /**
     * <p>Constructor for LightMitabBinarySource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public LightMitabBinarySource(InputStream input) {
        super(new LightMitabBinaryStreamSource(input));
    }

    /**
     * <p>Constructor for LightMitabBinarySource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public LightMitabBinarySource(Reader reader) {
        super(new LightMitabBinaryStreamSource(reader));
    }
}
