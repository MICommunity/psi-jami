package psidev.psi.mi.jami.xml.exception;

import psidev.psi.mi.jami.datasource.FileSourceLocator;

/**
 * An exception to be thrown when a XML file is invalid and cannot be parsed properly
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/10/13</pre>
 */
public class PsiXmlParserException extends Exception{

    private FileSourceLocator locator;

    /**
     * <p>Constructor for PsiXmlParserException.</p>
     */
    public PsiXmlParserException() {
    }

    /**
     * <p>Constructor for PsiXmlParserException.</p>
     *
     * @param locator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public PsiXmlParserException(FileSourceLocator locator) {
        this.locator = locator;
    }

    /**
     * <p>Constructor for PsiXmlParserException.</p>
     *
     * @param line a int.
     * @param col a int.
     */
    public PsiXmlParserException(int line, int col) {
        this.locator = new FileSourceLocator(line, col);
    }

    /**
     * <p>Constructor for PsiXmlParserException.</p>
     *
     * @param locator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     * @param message a {@link java.lang.String} object.
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public PsiXmlParserException(FileSourceLocator locator, String message, Throwable throwable) {
        super(message, throwable);
        this.locator = locator;
    }

    /**
     * <p>Constructor for PsiXmlParserException.</p>
     *
     * @param line a int.
     * @param col a int.
     * @param message a {@link java.lang.String} object.
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public PsiXmlParserException(int line, int col, String message, Throwable throwable) {
        super(message, throwable);
        this.locator = new FileSourceLocator(line, col);
    }

    /**
     * <p>Constructor for PsiXmlParserException.</p>
     *
     * @param s a {@link java.lang.String} object.
     */
    public PsiXmlParserException(String s) {
        super(s);
    }

    /**
     * <p>Constructor for PsiXmlParserException.</p>
     *
     * @param s a {@link java.lang.String} object.
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public PsiXmlParserException(String s, Throwable throwable) {
        super(s, throwable);
    }

    /**
     * <p>Constructor for PsiXmlParserException.</p>
     *
     * @param throwable a {@link java.lang.Throwable} object.
     */
    public PsiXmlParserException(Throwable throwable) {
        super(throwable);
    }

    /**
     * <p>Getter for the field <code>locator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getLocator() {
        return locator;
    }
}
