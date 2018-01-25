package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.datasource.BinaryInteractionStream;
import psidev.psi.mi.jami.model.Interaction;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

/**
 * Abstract class for binary interaction datasources
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */
public abstract class AbstractPsiXmlBinaryStream<T extends Interaction,B extends BinaryInteraction> extends AbstractPsiXmlStream<B> implements BinaryInteractionStream<B>{
    private ComplexExpansionMethod<T,B> complexExpansion;

    /**
     * <p>Constructor for AbstractPsiXmlBinaryStream.</p>
     */
    public AbstractPsiXmlBinaryStream() {
    }

    /**
     * <p>Constructor for AbstractPsiXmlBinaryStream.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public AbstractPsiXmlBinaryStream(File file) {
        super(file);
    }

    /**
     * <p>Constructor for AbstractPsiXmlBinaryStream.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public AbstractPsiXmlBinaryStream(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for AbstractPsiXmlBinaryStream.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public AbstractPsiXmlBinaryStream(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for AbstractPsiXmlBinaryStream.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public AbstractPsiXmlBinaryStream(URL url) {
        super(url);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExpansionMethod(ComplexExpansionMethod<? extends Interaction, ? extends BinaryInteraction> expansionMethod) {
        this.complexExpansion = (ComplexExpansionMethod<T,B>)expansionMethod;
    }

    /**
     * <p>Getter for the field <code>complexExpansion</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod} object.
     */
    protected ComplexExpansionMethod<T, B> getComplexExpansion() {
        return complexExpansion;
    }
}
