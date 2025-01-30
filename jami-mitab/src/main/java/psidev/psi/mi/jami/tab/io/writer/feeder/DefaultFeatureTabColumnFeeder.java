package psidev.psi.mi.jami.tab.io.writer.feeder;

import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.tab.utils.MitabUtils;

import java.io.IOException;
import java.io.Writer;

/**
 * The default FeatureTab column feeder for features
 */
public class DefaultFeatureTabColumnFeeder extends AbstractFeatureTabColumnFeeder<Feature, InteractionEvidence> {

    /**
     * <p>Constructor for DefaultMitabColumnFeeder.</p>
     *
     * @param writer a {@link Writer} object.
     */
    public DefaultFeatureTabColumnFeeder(Writer writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    public void writeFeatureAc(Feature feature) throws IOException {
        getWriter().write(MitabUtils.EMPTY_COLUMN);
    }

    /** {@inheritDoc} */
    public void writeInteractionAc(InteractionEvidence interaction) throws IOException {
        getWriter().write(MitabUtils.EMPTY_COLUMN);
    }
}

