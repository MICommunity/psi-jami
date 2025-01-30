package psidev.psi.mi.jami.datasource;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.InteractionEvidence;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public class MockFeatureWriter implements FeatureWriter<Feature, InteractionEvidence> {

    @Override
    public void initialiseContext(Map<String, Object> options) {
        if (!options.containsKey(InteractionWriterOptions.OUTPUT_OPTION_KEY)){
            throw new IllegalArgumentException("no output option");
        } else if (options.get(InteractionWriterOptions.OUTPUT_OPTION_KEY) == null){
            throw new IllegalArgumentException("no output option");
        }
    }

    @Override
    public void start() throws MIIOException {
    }

    @Override
    public void end() throws MIIOException {
    }

    @Override
    public void write(Feature feature) throws MIIOException {
    }

    @Override
    public void write(Collection<? extends Feature> features) throws MIIOException {
    }

    @Override
    public void write(Iterator<? extends Feature> features) throws MIIOException {
    }

    @Override
    public void write(InteractionEvidence interaction) throws MIIOException {
    }

    @Override
    public void flush() throws MIIOException {
    }

    @Override
    public void close() throws MIIOException {
    }

    @Override
    public void reset() throws MIIOException {
    }
}
