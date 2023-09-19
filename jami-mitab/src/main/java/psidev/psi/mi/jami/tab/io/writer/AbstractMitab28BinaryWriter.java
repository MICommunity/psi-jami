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
 * Abstract mitab 2.8 writer for binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>20/06/13</pre>
 */
public abstract class AbstractMitab28BinaryWriter<T extends BinaryInteraction, P extends Participant> extends AbstractMitab27BinaryWriter<T, P> {

    /**
     * <p>Constructor for AbstractMitab28BinaryWriter.</p>
     */
    public AbstractMitab28BinaryWriter() {
        super();
        setVersion(MitabVersion.v2_8);
    }

    /**
     * <p>Constructor for AbstractMitab28BinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws IOException if any.
     */
    public AbstractMitab28BinaryWriter(File file) throws IOException {
        super(file);
        setVersion(MitabVersion.v2_8);
    }

    /**
     * <p>Constructor for AbstractMitab28BinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public AbstractMitab28BinaryWriter(OutputStream output) {
        super(output);
        setVersion(MitabVersion.v2_8);
    }

    /**
     * <p>Constructor for AbstractMitab28BinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public AbstractMitab28BinaryWriter(Writer writer) {
        super(writer);
        setVersion(MitabVersion.v2_8);
    }

    /**
     * {@inheritDoc}
     *
     * Writes the binary interaction and its participants in MITAB 2.8
     *
     * @param interaction a binary interaction
     * @param a participant a
     * @param b participant b
     * @param miScore : the MI score of the interaction to write
     * @throws IOException if any.
     */
    @Override
    protected void writeBinary(T interaction, P a, P b, Double miScore) throws IOException {
        // write 2.7 columns
        super.writeBinary(interaction, a, b, miScore);
        getWriter().write(MitabUtils.COLUMN_SEPARATOR);

        MitabColumnFeeder<T, P> columnFeeder = getColumnFeeder();

        // write biological effects
        // write biological effect A
        columnFeeder.writeParticipantBiologicalEffect(a);
        getWriter().write(MitabUtils.COLUMN_SEPARATOR);
        // write biological effect B
        columnFeeder.writeParticipantBiologicalEffect(b);
        getWriter().write(MitabUtils.COLUMN_SEPARATOR);

        // write causal regulatory mechanism
        columnFeeder.writeInteractionCausalRegulatoryMechanism(interaction);
        getWriter().write(MitabUtils.COLUMN_SEPARATOR);

        // write causal statement
        columnFeeder.writeInteractionCausalStatement(interaction);

    }
}
