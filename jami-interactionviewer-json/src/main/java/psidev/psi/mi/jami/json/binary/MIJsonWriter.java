package psidev.psi.mi.jami.json.binary;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.SpokeExpansion;
import psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher;
import psidev.psi.mi.jami.model.Interaction;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * The jsonWriter which writes all interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/07/13</pre>
 */
public class MIJsonWriter extends AbstractMIJsonWriter<Interaction,BinaryInteraction> {


    /**
     * <p>Constructor for MIJsonWriter.</p>
     */
    public MIJsonWriter(){
        super();
    }

    /**
     * <p>Constructor for MIJsonWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @throws java.io.IOException if any.
     */
    public MIJsonWriter(File file, OntologyTermFetcher fetcher) throws IOException {
        super(file, fetcher);
    }

    /**
     * <p>Constructor for MIJsonWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonWriter(OutputStream output, OntologyTermFetcher fetcher) {
        super(output, fetcher);
    }

    /**
     * <p>Constructor for MIJsonWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     */
    public MIJsonWriter(Writer writer, OntologyTermFetcher fetcher) {
        super(writer, fetcher);
    }

    /**
     * <p>Constructor for MIJsonWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     * @throws java.io.IOException if any.
     */
    public MIJsonWriter(File file, OntologyTermFetcher fetcher, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) throws IOException {
        super(file, fetcher, expansionMethod);
    }

    /**
     * <p>Constructor for MIJsonWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public MIJsonWriter(OutputStream output, OntologyTermFetcher fetcher, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) {
        super(output, fetcher, expansionMethod);
    }

    /**
     * <p>Constructor for MIJsonWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @param fetcher a {@link psidev.psi.mi.jami.bridges.fetcher.OntologyTermFetcher} object.
     * @param expansionMethod a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    public MIJsonWriter(Writer writer, OntologyTermFetcher fetcher, ComplexExpansionMethod<Interaction, BinaryInteraction> expansionMethod) {
        super(writer, fetcher, expansionMethod);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultExpansionMethod() {
        super.setExpansionMethod(new SpokeExpansion());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBinaryWriter(File file, OntologyTermFetcher fetcher) throws IOException {
        super.setBinaryWriter(new MIJsonBinaryWriter(file, fetcher));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBinaryWriter(OutputStream output, OntologyTermFetcher fetcher) {
        super.setBinaryWriter(new MIJsonBinaryWriter(output, fetcher));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBinaryWriter(Writer writer, OntologyTermFetcher fetcher) {
        super.setBinaryWriter(new MIJsonBinaryWriter(writer, fetcher));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultBinaryWriter() {
        super.setBinaryWriter(new MIJsonBinaryWriter());

    }
}
