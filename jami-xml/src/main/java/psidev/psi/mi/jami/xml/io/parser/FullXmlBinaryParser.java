package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.SpokeExpansion;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;
import psidev.psi.mi.jami.xml.model.AbstractBaseEntry;
import psidev.psi.mi.jami.xml.model.AbstractEntrySet;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

/**
 * Full Parser generating binary interactions that could be a mix or abstract interactions and interaction evidences.
 *
 * It will load the all entrySet so is consuming a lot of memory in case of large files but is very performant for small files
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class FullXmlBinaryParser extends AbstractPsixmlBinaryParser<Interaction,BinaryInteraction> implements FullPsiXmlParser<Interaction> {

    /**
     * <p>Constructor for FullXmlBinaryParser.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws javax.xml.bind.JAXBException if any.
     * @throws java.io.FileNotFoundException if any.
     */
    public FullXmlBinaryParser(File file) throws JAXBException, FileNotFoundException {
        super(new FullXmlParser(file));
    }

    /**
     * <p>Constructor for FullXmlBinaryParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public FullXmlBinaryParser(InputStream inputStream) throws JAXBException {
        super(new FullXmlParser(inputStream));
    }

    /**
     * <p>Constructor for FullXmlBinaryParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public FullXmlBinaryParser(URL url) throws IOException, JAXBException {
        super(new FullXmlParser(url));
    }

    /**
     * <p>Constructor for FullXmlBinaryParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public FullXmlBinaryParser(Reader reader) throws JAXBException {
        super(new FullXmlParser(reader));
    }

    /** {@inheritDoc} */
    @Override
    protected ComplexExpansionMethod<Interaction, BinaryInteraction> initialiseDefaultExpansionMethod() {
        return new SpokeExpansion();
    }

    /** {@inheritDoc} */
    @Override
    public AbstractEntrySet<AbstractBaseEntry<Interaction>> getEntrySet() throws PsiXmlParserException {
        return ((FullPsiXmlParser<Interaction>)getDelegateParser()).getEntrySet();
    }
}
