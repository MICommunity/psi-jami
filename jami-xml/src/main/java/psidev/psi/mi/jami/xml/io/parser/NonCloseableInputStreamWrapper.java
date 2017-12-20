package psidev.psi.mi.jami.xml.io.parser;

import java.io.IOException;
import java.io.InputStream;

/**
 * Wrapper of inputStream
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25/06/14</pre>
 */
public class NonCloseableInputStreamWrapper extends InputStream {
    private InputStream delegate;

    /**
     * <p>Constructor for NonCloseableInputStreamWrapper.</p>
     *
     * @param delegate a {@link java.io.InputStream} object.
     */
    public NonCloseableInputStreamWrapper(InputStream delegate){
        super();
        if (delegate == null){
            throw new IllegalArgumentException("The delegate inputstream cannot be null");
        }
        this.delegate = delegate;
    }
    /** {@inheritDoc} */
    @Override
    public int read() throws IOException {
        return this.delegate.read();
    }

    /** {@inheritDoc} */
    @Override
    public int read(byte[] b) throws IOException {
        return this.delegate.read(b);
    }

    /** {@inheritDoc} */
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return this.delegate.read(b, off, len);
    }

    /** {@inheritDoc} */
    @Override
    public long skip(long n) throws IOException {
        return this.delegate.skip(n);
    }

    /** {@inheritDoc} */
    @Override
    public int available() throws IOException {
        return this.delegate.available();
    }

    /** {@inheritDoc} */
    @Override
    public void close() throws IOException {
        // do not close
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void mark(int readlimit) {
        this.delegate.mark(readlimit);
    }

    /** {@inheritDoc} */
    @Override
    public synchronized void reset() throws IOException {
        this.delegate.reset();
    }

    /** {@inheritDoc} */
    @Override
    public boolean markSupported() {
        return this.delegate.markSupported();
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return this.delegate.hashCode();
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object obj) {
        return this.delegate.equals(obj);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.delegate.toString();
    }
}
