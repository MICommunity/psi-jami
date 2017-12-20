package psidev.psi.mi.jami.xml.model.extension;

import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;

/**
 * Psi-XML source locator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/07/13</pre>
 */
public class PsiXmlLocator extends FileSourceLocator implements Locator{

    private Integer objectId;
    private int characterOffset;

    /**
     * <p>Constructor for PsiXmlLocator.</p>
     *
     * @param lineNumber a int.
     * @param charNumber a int.
     * @param offset a int.
     */
    public PsiXmlLocator(int lineNumber, int charNumber, int offset) {
        super(lineNumber, charNumber);
        this.characterOffset = offset;
    }

    /**
     * <p>Constructor for PsiXmlLocator.</p>
     *
     * @param lineNumber a int.
     * @param charNumber a int.
     * @param objectId a {@link java.lang.Integer} object.
     */
    public PsiXmlLocator(int lineNumber, int charNumber, Integer objectId) {
        super(lineNumber, charNumber);
        this.objectId = objectId;
    }

    /**
     * <p>Getter for the field <code>objectId</code>.</p>
     *
     * @return a {@link java.lang.Integer} object.
     */
    public Integer getObjectId() {
        return objectId;
    }

    /** {@inheritDoc} */
    @Override
    public String getPublicId() {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public String getSystemId() {
        return null;
    }

    /**
     * <p>getColumnNumber.</p>
     *
     * @return a int.
     */
    public int getColumnNumber() {
        return getCharNumber();
    }

    /**
     * <p>Setter for the field <code>objectId</code>.</p>
     *
     * @param objectId a {@link java.lang.Integer} object.
     */
    public void setObjectId(Integer objectId) {
        this.objectId = objectId;
    }

    /**
     * <p>Getter for the field <code>characterOffset</code>.</p>
     *
     * @return a int.
     */
    public int getCharacterOffset() {
        return characterOffset;
    }

    /**
     * <p>Setter for the field <code>characterOffset</code>.</p>
     *
     * @param characterOffset a int.
     */
    public void setCharacterOffset(int characterOffset) {
        this.characterOffset = characterOffset;
    }

    /**
     * <p>toString.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String toString() {
        return super.toString() + (objectId != null ? " Id: "+objectId : "");
    }
}
