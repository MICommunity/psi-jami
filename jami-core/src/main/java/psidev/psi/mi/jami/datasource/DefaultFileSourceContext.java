package psidev.psi.mi.jami.datasource;

/**
 * A DefaultFileSourceContext is the basic implementation of FileSourceContext and
 * contains some file location information
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/03/13</pre>
 */
public class DefaultFileSourceContext implements FileSourceContext {

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for DefaultFileSourceContext.</p>
     */
    public DefaultFileSourceContext(){

    }

    /**
     * <p>Constructor for DefaultFileSourceContext.</p>
     *
     * @param locator a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public DefaultFileSourceContext(FileSourceLocator locator){
        this.sourceLocator = locator;
    }

    /**
     * <p>Constructor for DefaultFileSourceContext.</p>
     *
     * @param lineNumber a int.
     * @param columnNumber a int.
     */
    public DefaultFileSourceContext(int lineNumber, int columnNumber){
        this.sourceLocator = new FileSourceLocator(lineNumber, columnNumber);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "File context: "+sourceLocator != null ? sourceLocator.toString() : super.toString();
    }
}
