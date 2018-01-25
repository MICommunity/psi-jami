package psidev.psi.mi.jami.tab.extension.datasource;

import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Participant;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

/**
 * A mitab datasource that loads very basic interactions and ignore experimental details, source, confidence and experimental details
 * It will load the full interaction set.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class LightMitabSource extends AbstractMitabSource<Interaction, Participant, Feature> {
    /**
     * <p>Constructor for LightMitabSource.</p>
     */
    public LightMitabSource() {
        super(new LightMitabStreamSource());
    }

    /**
     * <p>Constructor for LightMitabSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public LightMitabSource(File file) throws IOException {
        super(new LightMitabStreamSource(file));
    }

    /**
     * <p>Constructor for LightMitabSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public LightMitabSource(InputStream input) {
        super(new LightMitabStreamSource(input));
    }

    /**
     * <p>Constructor for LightMitabSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public LightMitabSource(Reader reader) {
        super(new LightMitabStreamSource(reader));
    }
}
