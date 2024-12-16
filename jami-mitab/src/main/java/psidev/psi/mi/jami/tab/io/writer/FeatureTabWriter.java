package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.tab.io.writer.feeder.DefaultFeatureTabColumnFeeder;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * FeatureTab writer for features
 */
public class FeatureTabWriter extends AbstractFeatureTabWriter<Feature, Interaction, Experiment> {

    /**
     * <p>Constructor for FeatureTabWriter.</p>
     */
    public FeatureTabWriter() {
        super();
    }

    /**
     * <p>Constructor for FeatureTabWriter.</p>
     *
     * @param file a {@link File} object.
     * @throws IOException if any.
     */
    public FeatureTabWriter(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for FeatureTabWriter.</p>
     *
     * @param output a {@link OutputStream} object.
     */
    public FeatureTabWriter(OutputStream output) {
        super(output);
    }

    /**
     * <p>Constructor for FeatureTabWriter.</p>
     *
     * @param writer a {@link Writer} object.
     */
    public FeatureTabWriter(Writer writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseColumnFeeder() {
        setColumnFeeder(new DefaultFeatureTabColumnFeeder(getWriter()));
    }
}
