package psidev.psi.mi.jami.xml.io.iterator;

import psidev.psi.mi.jami.datasource.DefaultFileSourceContext;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;
import psidev.psi.mi.jami.xml.io.parser.PsiXmlParser;

import java.util.Iterator;

/**
 * Abstract iterator for parsing xml interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */
public class AbstractXmlIterator<T extends Interaction> implements Iterator<T> {

    private PsiXmlParser<T> parser;
    private MIFileParserListener parserListener;
    private T nextInteraction;

    /**
     * <p>Constructor for AbstractXmlIterator.</p>
     *
     * @param lineParser a {@link psidev.psi.mi.jami.xml.io.parser.PsiXmlParser} object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public AbstractXmlIterator(PsiXmlParser<T> lineParser) throws MIIOException {
        if (lineParser == null){
            throw new IllegalArgumentException("The PsiXml iterator needs a non null parser.");
        }
        this.parser = lineParser;
        processNextBinary();
    }

    private void processNextBinary() throws MIIOException{
        this.nextInteraction = null;
        try {
            while (!this.parser.hasFinished() && this.nextInteraction == null){

                this.nextInteraction = this.parser.parseNextInteraction();
            }
        } catch (PsiXmlParserException e) {
            if (this.parserListener != null){
                this.parserListener.onInvalidSyntax(new DefaultFileSourceContext(e.getLocator()), e);
            }
            else{
                throw new MIIOException("Impossible to read next interaction.", e);
            }
        } catch (Exception e) {
            if (this.parserListener != null){
                this.parserListener.onInvalidSyntax(new DefaultFileSourceContext(), e);
            }
            else{
                throw new MIIOException("Impossible to read next interaction.", e);
            }
        }
    }

    /**
     * <p>hasNext.</p>
     *
     * @return a boolean.
     */
    public boolean hasNext() {
        return this.nextInteraction != null;
    }

    /**
     * <p>next.</p>
     *
     * @return a T object.
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public T next() throws MIIOException{
        T currentBinary = this.nextInteraction;
        processNextBinary();
        return currentBinary;
    }

    /**
     * <p>remove.</p>
     */
    public void remove() {
        throw new UnsupportedOperationException("A MITAB iterator does not support the remove method");
    }

    /**
     * <p>Setter for the field <code>parserListener</code>.</p>
     *
     * @param defaultParserListener a {@link psidev.psi.mi.jami.listener.MIFileParserListener} object.
     */
    public void setParserListener(MIFileParserListener defaultParserListener) {
        this.parserListener = defaultParserListener;
    }
}
