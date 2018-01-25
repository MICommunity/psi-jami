package psidev.psi.mi.jami.tab.extension.datasource;

import psidev.psi.mi.jami.datasource.ModelledInteractionStream;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.tab.io.iterator.MitabModelledInteractionIterator;
import psidev.psi.mi.jami.tab.io.parser.ModelledInteractionLineParser;

import java.io.*;
import java.net.URL;
import java.util.Iterator;

/**
 * A mitab datasource that loads modelled interactions and ignore experimental details
 * It only provides an iterator of modelled interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/06/13</pre>
 */
public class MitabModelledStreamSource extends AbstractMitabStreamSource<ModelledInteraction, ModelledParticipant, ModelledFeature> implements ModelledInteractionStream<ModelledInteraction>{

    /**
     * <p>Constructor for MitabModelledStreamSource.</p>
     */
    public MitabModelledStreamSource() {
        super();
    }

    /**
     * <p>Constructor for MitabModelledStreamSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public MitabModelledStreamSource(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for MitabModelledStreamSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public MitabModelledStreamSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for MitabModelledStreamSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public MitabModelledStreamSource(Reader reader) {
        super(reader);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseMitabLineParser(Reader reader) {
        if (reader == null){
            throw new IllegalArgumentException("The reader cannot be null.");
        }
        setOriginalReader(reader);
        setLineParser(new ModelledInteractionLineParser(reader));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseMitabLineParser(File file) {
        if (file == null){
            throw new IllegalArgumentException("The file cannot be null.");
        }
        else if (!file.canRead()){
            throw new IllegalArgumentException("Does not have the permissions to read the file "+file.getAbsolutePath());
        }
        setOriginalFile(file);
        try {
            InputStream stream = new BufferedInputStream(new FileInputStream(file));
            initialiseMitabLineParser(stream);
        } catch (FileNotFoundException e) {
            throw new MIIOException("Impossible to open the file " + file.getName());
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseMitabLineParser(InputStream input) {
        setOriginalStream(input);
        setLineParser(new ModelledInteractionLineParser(input));
    }

    /** {@inheritDoc} */
    @Override
    protected Iterator<ModelledInteraction> createMitabIterator() throws MIIOException {
        return new MitabModelledInteractionIterator(getLineParser());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseMitabLineParser(URL url) {
        if (url == null){
            throw new IllegalArgumentException("The url cannot be null.");
        }
        setOriginalURL(url);
        try {
            InputStream stream = new BufferedInputStream(url.openStream());
            initialiseMitabLineParser(stream);
        } catch (IOException e) {
            throw new MIIOException("Impossible to open the url " + url.toExternalForm());
        }
    }
}

