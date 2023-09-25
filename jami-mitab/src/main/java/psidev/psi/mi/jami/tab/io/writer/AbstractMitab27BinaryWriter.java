package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.tab.MitabVersion;
import psidev.psi.mi.jami.tab.io.writer.feeder.MitabColumnFeeder;
import psidev.psi.mi.jami.tab.utils.MitabUtils;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Abstract mitab 2.7 writer for binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public abstract class AbstractMitab27BinaryWriter<T extends BinaryInteraction, P extends Participant> extends AbstractMitab26BinaryWriter<T, P> {

    /**
     * <p>Constructor for AbstractMitab27BinaryWriter.</p>
     */
    public AbstractMitab27BinaryWriter() {
        super();
        setVersion(MitabVersion.v2_7);
    }

    /**
     * <p>Constructor for AbstractMitab27BinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public AbstractMitab27BinaryWriter(File file) throws IOException {
        super(file);
        setVersion(MitabVersion.v2_7);
    }

    /**
     * <p>Constructor for AbstractMitab27BinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public AbstractMitab27BinaryWriter(OutputStream output) {
        super(output);
        setVersion(MitabVersion.v2_7);
    }

    /**
     * <p>Constructor for AbstractMitab27BinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public AbstractMitab27BinaryWriter(Writer writer) {
        super(writer);
        setVersion(MitabVersion.v2_7);
    }

    /**
     * {@inheritDoc}
     *
     * Writes the binary interaction and its participants in MITAB 2.7
     *
     * @param interaction a binary interaction
     * @param a participant a
     * @param b participant b
     * @throws java.io.IOException if any.
     */
    @Override
    protected void writeBinary(T interaction, P a, P b) throws IOException {
        // write 2.6 columns
        super.writeBinary(interaction, a, b);
        getWriter().write(MitabUtils.COLUMN_SEPARATOR);

        MitabColumnFeeder<T, P> columnFeeder = getColumnFeeder();

        // write features
        // write features A
        columnFeeder.writeParticipantFeatures(a);
        getWriter().write(MitabUtils.COLUMN_SEPARATOR);
        // write features B
        columnFeeder.writeParticipantFeatures(b);
        getWriter().write(MitabUtils.COLUMN_SEPARATOR);

        // write stc A
        columnFeeder.writeParticipantStoichiometry(a);
        getWriter().write(MitabUtils.COLUMN_SEPARATOR);
        // write stc B
        columnFeeder.writeParticipantStoichiometry(b);
        getWriter().write(MitabUtils.COLUMN_SEPARATOR);
        // skip identification A
        columnFeeder.writeParticipantIdentificationMethod(a);
        getWriter().write(MitabUtils.COLUMN_SEPARATOR);
        // skip identification B
        columnFeeder.writeParticipantIdentificationMethod(b);
    }
}
