package psidev.psi.mi.jami.xml.model.extension.datasource;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.datasource.BinaryInteractionSource;
import psidev.psi.mi.jami.model.Interaction;

import java.io.File;
import java.io.InputStream;
import java.io.Reader;
import java.net.URL;

/**
 * Abstract class for Psi-XML 2.5 binary interaction data source which loads the full
 * interaction dataset
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public abstract class AbstractPsiXmlBinarySource<T extends Interaction,B extends BinaryInteraction> extends AbstractPsiXmlSource<B> implements BinaryInteractionSource<B> {
    private ComplexExpansionMethod<T,B> complexExpansion;

    /**
     * <p>Constructor for AbstractPsiXmlBinarySource.</p>
     */
    public AbstractPsiXmlBinarySource() {
    }

    /**
     * <p>Constructor for AbstractPsiXmlBinarySource.</p>
     *
     * @param file a {@link java.io.File} object.
     */
    public AbstractPsiXmlBinarySource(File file) {
        super(file);
    }

    /**
     * <p>Constructor for AbstractPsiXmlBinarySource.</p>
     *
     * @param input a {@link java.io.InputStream} object.
     */
    public AbstractPsiXmlBinarySource(InputStream input) {
        super(input);
    }

    /**
     * <p>Constructor for AbstractPsiXmlBinarySource.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     */
    public AbstractPsiXmlBinarySource(Reader reader) {
        super(reader);
    }

    /**
     * <p>Constructor for AbstractPsiXmlBinarySource.</p>
     *
     * @param url a {@link java.net.URL} object.
     */
    public AbstractPsiXmlBinarySource(URL url) {
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
