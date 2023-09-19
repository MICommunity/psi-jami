package psidev.psi.mi.jami.tab.io.writer;

import psidev.psi.mi.jami.datasource.InteractionWriter;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.Confidence;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.tab.MitabVersion;
import psidev.psi.mi.jami.tab.extension.factory.MitabWriterFactory;
import psidev.psi.mi.jami.tab.extension.factory.options.MitabDataSourceOptions;
import psidev.psi.mi.jami.tab.extension.factory.options.MitabWriterOptions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Generic writer for MITAB
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>02/05/14</pre>
 */
public class DefaultMitabWriter implements InteractionWriter {

    private InteractionWriter delegate;

    /** {@inheritDoc} */
    public void initialiseContext(Map options) {
        MitabWriterFactory factory = MitabWriterFactory.getInstance();
        InteractionCategory category = InteractionCategory.mixed;
        ComplexType type = ComplexType.n_ary;
        MitabVersion version = MitabVersion.v2_7;
        boolean extended = false;

        if (options == null || !options.containsKey(MitabWriterOptions.OUTPUT_OPTION_KEY)){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }

        if (options.containsKey(MitabWriterOptions.INTERACTION_CATEGORY_OPTION_KEY)){
            Object value = options.get(MitabDataSourceOptions.INTERACTION_CATEGORY_OPTION_KEY);
            if (value instanceof InteractionCategory){
                category = (InteractionCategory)value;
            }
        }
        if (options.containsKey(MitabWriterOptions.COMPLEX_TYPE_OPTION_KEY)){
            Object value = options.get(MitabDataSourceOptions.COMPLEX_TYPE_OPTION_KEY);
            if (value instanceof ComplexType){
                type = (ComplexType)value;
            }
        }
        if (options.containsKey(MitabWriterOptions.MITAB_VERSION_OPTION)){
            Object value = options.get(MitabWriterOptions.MITAB_VERSION_OPTION);
            if (value instanceof MitabVersion){
                version = (MitabVersion)value;
            }
        }
        if (options.containsKey(MitabWriterOptions.MITAB_EXTENDED_OPTION)){
            Object value = options.get(MitabWriterOptions.MITAB_EXTENDED_OPTION);
            if (value != null){
                extended = (Boolean)value;
            }
        }

        this.delegate = factory.createMitabWriter(category, type, version, extended);
        this.delegate.initialiseContext(options);
    }

    /**
     * <p>start.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void start() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.start();
    }

    /**
     * <p>end.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void end() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.end();
    }

    /**
     * <p>write.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.Interaction} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(Interaction interaction) throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.write(interaction);
    }

    /**
     * <p>write.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.model.Interaction} object.
     * @param miScore : the MI score of the interaction to write
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(Interaction interaction, Double miScore) throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.write(interaction, miScore);
    }

    /**
     * <p>write.</p>
     *
     * @param interactions a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(Collection interactions) throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.write(interactions);
    }

    /**
     * <p>write.</p>
     *
     * @param interactions a {@link java.util.Collection} object.
     * @param miScore : the MI score of the interactions to write
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(Collection interactions, Double miScore) throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.write(interactions, miScore);
    }

    /** {@inheritDoc} */
    public void write(Iterator interactions) throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.write(interactions);
    }

    /** {@inheritDoc} */
    public void write(Iterator interactions, Double miScore) throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.write(interactions, miScore);
    }

    /**
     * <p>flush.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void flush() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.flush();
    }

    /**
     * <p>close.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.close();
    }

    /**
     * <p>reset.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reset() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The Mitab interaction writer has not been initialised. The options for the Mitab interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.reset();
    }

    /**
     * <p>Getter for the field <code>delegate</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.InteractionWriter} object.
     */
    protected InteractionWriter getDelegate() {
        return delegate;
    }

    /**
     * <p>Setter for the field <code>delegate</code>.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.datasource.InteractionWriter} object.
     */
    protected void setDelegate(InteractionWriter delegate) {
        this.delegate = delegate;
    }
}
