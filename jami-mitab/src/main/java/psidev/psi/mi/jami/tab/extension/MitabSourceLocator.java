package psidev.psi.mi.jami.tab.extension;

import psidev.psi.mi.jami.datasource.FileSourceLocator;

/**
 * A MITAB file source context
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/03/13</pre>
 */
public class MitabSourceLocator extends FileSourceLocator {

    private int columnNumber;

    /**
     * <p>Constructor for MitabSourceLocator.</p>
     *
     * @param lineNumber a int.
     * @param charNumber a int.
     * @param columnNumber a int.
     */
    public MitabSourceLocator(int lineNumber, int charNumber, int columnNumber) {
        super(lineNumber, charNumber);
        this.columnNumber = columnNumber;
    }

    /**
     * <p>Getter for the field <code>columnNumber</code>.</p>
     *
     * @return a int.
     */
    public int getColumnNumber() {
        return columnNumber;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Line: "+getLineNumber() + ", MITAB Column: "+ getColumnNumber() + ", Character number: " + getCharNumber();
    }
}
