package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.binary.ModelledBinaryInteraction;
import psidev.psi.mi.jami.binary.expansion.ComplexExpansionMethod;
import psidev.psi.mi.jami.binary.expansion.ModelledInteractionSpokeExpansion;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.xml.model.AbstractBaseEntry;
import psidev.psi.mi.jami.xml.model.AbstractEntrySet;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;

import javax.xml.bind.JAXBException;
import java.io.*;
import java.net.URL;

/**
 * Full Parser generating binary modelled interaction objects and ignoring all experimental details.
 *
 * It will load the all entrySet so is consuming a lot of memory in case of large files but is very performant for small files
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/11/13</pre>
 */
public class FullXmlModelledBinaryParser extends AbstractPsixmlBinaryParser<ModelledInteraction,ModelledBinaryInteraction> implements FullPsiXmlParser<ModelledInteraction> {
    /**
     * <p>Constructor for FullXmlModelledBinaryParser.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws javax.xml.bind.JAXBException if any.
     * @throws java.io.FileNotFoundException if any.
     */
    public FullXmlModelledBinaryParser(File file) throws JAXBException, FileNotFoundException {
        super(new FullXmlModelledParser(file));
    }

    /**
     * <p>Constructor for FullXmlModelledBinaryParser.</p>
     *
     * @param inputStream a {@link java.io.InputStream} object.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public FullXmlModelledBinaryParser(InputStream inputStream) throws JAXBException {
        super(new FullXmlModelledParser(inputStream));
    }

    /**
     * <p>Constructor for FullXmlModelledBinaryParser.</p>
     *
     * @param url a {@link java.net.URL} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public FullXmlModelledBinaryParser(URL url) throws IOException, JAXBException {
        super(new FullXmlModelledParser(url));
    }

    /**
     * <p>Constructor for FullXmlModelledBinaryParser.</p>
     *
     * @param reader a {@link java.io.Reader} object.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public FullXmlModelledBinaryParser(Reader reader) throws JAXBException {
        super(new FullXmlModelledParser(reader));
    }

    /** {@inheritDoc} */
    @Override
    protected ComplexExpansionMethod<ModelledInteraction,ModelledBinaryInteraction> initialiseDefaultExpansionMethod() {
        return new ModelledInteractionSpokeExpansion();
    }

    /** {@inheritDoc} */
    @Override
    public AbstractEntrySet<AbstractBaseEntry<ModelledInteraction>> getEntrySet() throws PsiXmlParserException {
        return ((FullPsiXmlParser<ModelledInteraction>)getDelegateParser()).getEntrySet();
    }
}
