package psidev.psi.mi.jami.datasource;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.InteractionEvidence;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * The FeatureWriter can write features in a datasource (files, database)
 */
public interface FeatureWriter<F extends Feature, I extends InteractionEvidence> {

    /**
     * Initialise the context of the FeatureWriter given a map of options.
     *
     * @param options : the options given by the user
     */
    void initialiseContext(Map<String, Object> options);

    /**
     * Method to call before starting to write  but after initialising the context.
     *
     * @throws MIIOException if cannot start writing
     */
    void start() throws MIIOException;

    /**
     * Method to call when we reach the end.
     *
     * @throws MIIOException : if cannot end
     */
    void end() throws MIIOException;

    /**
     * Writes a feature.
     *
     * @param feature : the feature to write
     * @throws MIIOException : if cannot write
     */
    void write(F feature) throws MIIOException;

    /**
     * Writes a collection of Feature objects.
     *
     * @param features : the features to write
     * @throws MIIOException : if cannot write
     */
    void write(Collection<? extends F> features) throws MIIOException;

    /**
     * Writes Feature objects using iterator.
     *
     * @param features : the iterator of features to write
     * @throws MIIOException : if cannot write
     */
    void write(Iterator<? extends F> features) throws MIIOException;

    /**
     * Writes the features of an interaction.
     *
     * @param interaction : the interaction to write
     * @throws MIIOException : if cannot write
     */
    void write(I interaction) throws MIIOException;

    /**
     * Flushes the writer (commit or write on disk).
     *
     * @throws MIIOException : if cannot flush
     */
    void flush() throws MIIOException;

    /**
     * Closes the FeatureWriter. It will flushes before closing.
     * It will close any provided outputStream and writer.
     *
     * @throws MIIOException : if cannot close
     */
    void close() throws MIIOException;

    /**
     * This method will reset the writer from all loaded options.
     * The FeatureWriter will be back to what is was before the initialiseContext was called.
     * To re-use the FeatureWriter after calling the reset() method, the data source needs to be re-initialised with
     * initialiseContext.
     * Any provided ouputStream or writer will not be closed and will have to be closed separately.
     *
     * @throws MIIOException : if cannot reset
     */
    void reset() throws MIIOException;
}
