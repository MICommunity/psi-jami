package psidev.psi.mi.jami.xml.io.parser;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory;

/**
 * Interface for PsiXmlParser
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/10/13</pre>
 */
public interface PsiXmlParser<T extends Interaction> {
    /**
     * <p>parseNextInteraction.</p>
     *
     * @return a T object.
     * @throws psidev.psi.mi.jami.xml.exception.PsiXmlParserException if any.
     */
    public T parseNextInteraction() throws PsiXmlParserException;

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

    /**
     * <p>getVersion.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     */
    public PsiXmlVersion getVersion();

    /**
     * <p>getInteractorFactory.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory} object.
     */
    public XmlInteractorFactory getInteractorFactory();

    /**
     * <p>setInteractorFactory.</p>
     *
     * @param factory a {@link psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory} object.
     */
    public void setInteractorFactory(XmlInteractorFactory factory);

}
