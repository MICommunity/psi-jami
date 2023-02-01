package psidev.psi.mi.jami.xml.io.writer;

import psidev.psi.mi.jami.datasource.InteractionWriter;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.factory.options.InteractionWriterOptions;
import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.model.extension.factory.PsiXmlWriterFactory;
import psidev.psi.mi.jami.xml.model.extension.factory.options.PsiXmlWriterOptions;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Generic writer for PSI-MI XML
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>02/05/14</pre>
 */
public class DefaultXmlWriter implements InteractionWriter {

    private InteractionWriter delegate;

    /** {@inheritDoc} */
    public void initialiseContext(Map options) {
        PsiXmlWriterFactory factory = PsiXmlWriterFactory.getInstance();
        InteractionCategory category = InteractionCategory.mixed;
        ComplexType type = ComplexType.n_ary;
        PsiXmlVersion version = PsiXmlVersion.v2_5_4;
        PsiXmlType xmlType = PsiXmlType.expanded;

        boolean extended = false;
        boolean named = true;

        if (options == null || !options.containsKey(PsiXmlWriterOptions.OUTPUT_OPTION_KEY)){
            throw new IllegalStateException("The PSI-MI XML interaction writer has not been initialised. The options for the interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }

        if (options.containsKey(PsiXmlWriterOptions.INTERACTION_CATEGORY_OPTION_KEY)){
            Object value = options.get(PsiXmlWriterOptions.INTERACTION_CATEGORY_OPTION_KEY);
            if (value instanceof InteractionCategory){
                category = (InteractionCategory)value;
            }
        }
        if (options.containsKey(PsiXmlWriterOptions.COMPLEX_TYPE_OPTION_KEY)){
            Object value = options.get(PsiXmlWriterOptions.COMPLEX_TYPE_OPTION_KEY);
            if (value instanceof ComplexType){
                type = (ComplexType)value;
            }
        }
        if (options.containsKey(PsiXmlWriterOptions.XML_VERSION_OPTION)){
            Object value = options.get(PsiXmlWriterOptions.XML_VERSION_OPTION);
            if (value instanceof PsiXmlVersion){
                version = (PsiXmlVersion)value;
            }
        }
        if (options.containsKey(PsiXmlWriterOptions.XML_EXTENDED_OPTION)){
            Object value = options.get(PsiXmlWriterOptions.XML_EXTENDED_OPTION);
            if (value != null){
                extended = (Boolean)value;
            }
        }
        if (options.containsKey(PsiXmlWriterOptions.XML_NAMES_OPTION)){
            Object value = options.get(PsiXmlWriterOptions.XML_NAMES_OPTION);
            if (value != null){
                named = (Boolean)value;
            }
        }
        if (options.containsKey(PsiXmlWriterOptions.XML_TYPE_OPTION)){
            Object value = options.get(PsiXmlWriterOptions.XML_TYPE_OPTION);
            if (value instanceof PsiXmlType){
                xmlType = (PsiXmlType)value;
            }
        }

        this.delegate = factory.createPsiXmlWriter(category, type, xmlType, version, extended, named);
        this.delegate.initialiseContext(options);
    }

    /**
     * <p>start.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void start() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The PSI-MI XML interaction writer has not been initialised. The options for the interaction writer " +
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
            throw new IllegalStateException("The PSI-MI XML interaction writer has not been initialised. The options for the interaction writer " +
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
            throw new IllegalStateException("The PSI-MI XML interaction writer has not been initialised. The options for the interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.write(interaction);
    }

    /**
     * <p>write.</p>
     *
     * @param interactions a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void write(Collection interactions) throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The PSI-MI XML interaction writer has not been initialised. The options for the interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.write(interactions);
    }

    /** {@inheritDoc} */
    public void write(Iterator interactions) throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The PSI-MI XML interaction writer has not been initialised. The options for the interaction writer " +
                    "should contain at least "+ InteractionWriterOptions.OUTPUT_OPTION_KEY + " to know where to write the interactions.");
        }
        this.delegate.write(interactions);
    }

    /**
     * <p>flush.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void flush() throws MIIOException {
        if (this.delegate == null){
            throw new IllegalStateException("The PSI-MI XML interaction writer has not been initialised. The options for the interaction writer " +
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
            throw new IllegalStateException("The PSI-MI XML interaction writer has not been initialised. The options for the interaction writer " +
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
            throw new IllegalStateException("The PSI-MI XML interaction writer has not been initialised. The options for the interaction writer " +
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
