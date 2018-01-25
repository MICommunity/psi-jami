package psidev.psi.mi.jami.tab.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.datasource.BinaryInteractionStream;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.tab.io.iterator.MitabBinaryIterator;
import psidev.psi.mi.jami.tab.io.parser.BinaryLineParser;

import java.io.*;
import java.net.URL;
import java.util.Iterator;

/**
 * A mitab datasource that loads very basic binary interactions and ignore experimental details, source, confidence and experimental details
 * It only provides an iterator of binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/06/13</pre>
 */
public class LightMitabBinaryStreamSource extends AbstractMitabStreamSource<BinaryInteraction, Participant, Feature> implements BinaryInteractionStream<BinaryInteraction>{

    /**
     * <p>Constructor for LightMitabBinaryStreamSource.</p>
     */
    public LightMitabBinaryStreamSource() {
        super();
    }

    /**
     * <p>Constructor for LightMitabBinaryStreamSource.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     */
    public LightMitabBinaryStreamSource(File file) throws IOException {
        super(file);
    }

    /**
     * <p>Constructor for LightMitabBinaryStreamSource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public LightMitabBinaryStreamSource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for LightMitabBinaryStreamSource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public LightMitabBinaryStreamSource(Reader reader) {
        super(reader);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseMitabLineParser(Reader reader) {
        if (reader == null){
            throw new IllegalArgumentException("The reader cannot be null.");
        }
        setOriginalReader(reader);
        setLineParser(new BinaryLineParser(reader));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseMitabLineParser(File file) throws MIIOException{
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
        setLineParser(new BinaryLineParser(input));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseMitabLineParser(URL url) throws MIIOException{
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

    /** {@inheritDoc} */
    @Override
    protected Iterator<BinaryInteraction> createMitabIterator() throws MIIOException {
        return new MitabBinaryIterator(getLineParser());
    }
}
