package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.SpokeExpansion;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.xml.model.AbstractEntry;
import psidev.psi.mi.jami.xml.model.AbstractEntrySet;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

/**
 * Full Parser generating basic binary interaction objects and ignoring all experimental details.
 *
 * It will load the all entrySet so is consuming a lot of memory in case of large files but is very performant for small files
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class LightFullXmlBinaryParser extends AbstractPsixmlBinaryParser<Interaction,BinaryInteraction> implements FullPsiXmlParser<Interaction<? extends Participant>> {

    /**
     * <p>Constructor for LightFullXmlBinaryParser.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws javax.xml.bind.JAXBException if any.
     * @throws java.io.FileNotFoundException if any.
     */
    public LightFullXmlBinaryParser(File file) throws JAXBException, FileNotFoundException {
        super(new LightFullXmlParser(file));
    }

    /**
     * <p>Constructor for LightFullXmlBinaryParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public LightFullXmlBinaryParser(InputStream inputStream) throws JAXBException {
        super(new LightFullXmlParser(inputStream));
    }

    /**
     * <p>Constructor for LightFullXmlBinaryParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public LightFullXmlBinaryParser(URL url) throws IOException, JAXBException {
        super(new LightFullXmlParser(url));
    }

    /**
     * <p>Constructor for LightFullXmlBinaryParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public LightFullXmlBinaryParser(Reader reader) throws JAXBException {
        super(new LightFullXmlParser(reader));
    }

    /** {@inheritDoc} */
    @Override
    protected ComplexExpansionMethod<Interaction, BinaryInteraction> initialiseDefaultExpansionMethod() {
        return new SpokeExpansion();
    }

    /** {@inheritDoc} */
    @Override
    public AbstractEntrySet<AbstractEntry<Interaction<? extends Participant>>> getEntrySet() throws PsiXmlParserException {
        return ((FullPsiXmlParser<Interaction<? extends Participant>>)getDelegateParser()).getEntrySet();
    }
}
