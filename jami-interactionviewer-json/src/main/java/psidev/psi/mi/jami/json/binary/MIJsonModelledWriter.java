package psidev.psi.mi.jami.json.binary;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.ModelledInteractionSpokeExpansion;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.model.ModelledInteraction;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * The jsonWriter which writes the modelled interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/07/13</pre>
 */
public class MIJsonModelledWriter extends AbstractMIJsonWriter<ModelledInteraction,ModelledBinaryInteraction> {


    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     */
    public MIJsonModelledWriter(){
        super();
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @throws java.io.IOException if any.
     */
    public MIJsonModelledWriter(File file, OntologyTermFetcher fetcher) throws IOException {
        super(file, fetcher);
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonModelledWriter(OutputStream output, OntologyTermFetcher fetcher) {
        super(output, fetcher);
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonModelledWriter(Writer writer, OntologyTermFetcher fetcher) {
        super(writer, fetcher);
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public MIJsonModelledWriter(File file, OntologyTermFetcher fetcher, ComplexExpansionMethod<ModelledInteraction,ModelledBinaryInteraction> expansionMethod) throws IOException {
        super(file, fetcher, expansionMethod);
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public MIJsonModelledWriter(OutputStream output, OntologyTermFetcher fetcher, ComplexExpansionMethod<ModelledInteraction,ModelledBinaryInteraction> expansionMethod) {
        super(output, fetcher, expansionMethod);
    }

    /**
     * <p>Constructor for MIJsonModelledWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public MIJsonModelledWriter(Writer writer, OntologyTermFetcher fetcher, ComplexExpansionMethod<ModelledInteraction,ModelledBinaryInteraction> expansionMethod) {
        super(writer, fetcher, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultExpansionMethod() {
        super.setExpansionMethod(new ModelledInteractionSpokeExpansion());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBinaryWriter(File file, OntologyTermFetcher fetcher) throws IOException {
        super.setBinaryWriter(new MIJsonModelledBinaryWriter(file, fetcher));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBinaryWriter(OutputStream output, OntologyTermFetcher fetcher) {
        super.setBinaryWriter(new MIJsonModelledBinaryWriter(output, fetcher));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBinaryWriter(Writer writer, OntologyTermFetcher fetcher) {
        super.setBinaryWriter(new MIJsonModelledBinaryWriter(writer, fetcher));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultBinaryWriter() {
        super.setBinaryWriter(new MIJsonModelledBinaryWriter());

    }
}
