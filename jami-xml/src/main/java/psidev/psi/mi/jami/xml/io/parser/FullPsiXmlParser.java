package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.model.AbstractEntry;
import psidev.psi.mi.jami.xml.model.AbstractEntrySet;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;

/**
 * Full parser that loads the all entry set.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/11/13</pre>
 */
public interface FullPsiXmlParser<T extends Interaction> {
    /**
     * <p>getEntrySet.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.AbstractEntrySet} object.
     * @throws psidev.psi.mi.jami.xml.exception.PsiXmlParserException if any.
     */
    public AbstractEntrySet<AbstractEntry<T>> getEntrySet() throws PsiXmlParserException;

    /**
     * <p>close.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void close() throws MIIOException;

    /**
     * <p>hasFinished.</p>
     *
     * @return a boolean.
     * @throws psidev.psi.mi.jami.xml.exception.PsiXmlParserException if any.
     */
    public boolean hasFinished() throws PsiXmlParserException;

    /**
     * <p>reInit.</p>
     *
     * @throws psidev.psi.mi.jami.exception.MIIOException if any.
     */
    public void reInit() throws MIIOException;

    /**
     * <p>getListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.listener.PsiXmlParserListener} object.
     */
    public PsiXmlParserListener getListener();

    /**
     * <p>setListener.</p>
     *
     * @param listener a {@link psidev.psi.mi.jami.xml.listener.PsiXmlParserListener} object.
     */
    public void setListener(PsiXmlParserListener listener);

    /**
     * <p>setCacheOfObjects.</p>
     *
     * @param indexOfObjects a {@link psidev.psi.mi.jami.xml.cache.PsiXmlIdCache} object.
     */
    public void setCacheOfObjects(PsiXmlIdCache indexOfObjects);
}
