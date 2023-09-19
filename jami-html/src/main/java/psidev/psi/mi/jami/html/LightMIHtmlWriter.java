package psidev.psi.mi.jami.html;

import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Participant;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Writer for light interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>06/12/13</pre>
 */
public class LightMIHtmlWriter extends AbstractMIHtmlWriter<Interaction, Participant, Feature>{

    /**
     * <p>Constructor for LightMIHtmlWriter.</p>
     */
    public LightMIHtmlWriter() {
        super(new MIModelledHtmlWriter());
    }

    /**
     * <p>Constructor for LightMIHtmlWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public LightMIHtmlWriter(File file) throws IOException {
        super(file, new MIModelledHtmlWriter(file));
    }

    /**
     * <p>Constructor for LightMIHtmlWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     */
    public LightMIHtmlWriter(OutputStream output) {
        super(output, new MIModelledHtmlWriter(output));
    }

    /**
     * <p>Constructor for LightMIHtmlWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public LightMIHtmlWriter(Writer writer) {
        super(writer, new MIModelledHtmlWriter(writer));
    }

    /** {@inheritDoc} */
    @Override
    protected void writeCooperativeEffects(Interaction interaction) {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeConfidences(Interaction interaction, Double miScore) throws IOException {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(Interaction interaction) throws IOException {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeDetectionMethods(Feature feature) throws IOException {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeGeneralProperties(Interaction interaction) throws IOException {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperiment(Interaction interaction) throws IOException {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeConfidences(Participant participant) throws IOException {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(Participant participant) throws IOException {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalPreparations(Participant participant) throws IOException {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExpressedInOrganism(Participant participant) throws IOException {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalRole(Participant participant) throws IOException {
        // do nothing
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParticipantIdentificationMethods(Participant participant) throws IOException {
        // do nothing
    }
}
