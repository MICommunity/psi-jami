package psidev.psi.mi.jami.xml.model.extension.xml253;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.ResultingSequence;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.comparator.range.ResultingSequenceComparator;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

import java.util.Collection;

/**
 * Xml implementation of resulting sequence for XML 3.0
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25/04/14</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "net:sf:psidev:mi")
public class XmlResultingSequence implements ResultingSequence, FileSourceContext, Locatable {
    private String originalSequence;
    private String newSequence;
    private XrefContainer xrefContainer;

    @XmlLocation
    @XmlTransient
    private Locator locator;
    private PsiXmlLocator sourceLocator;

    /**
     * <p>Constructor for XmlResultingSequence.</p>
     */
    public XmlResultingSequence(){
        this.originalSequence = null;
        this.newSequence = null;
    }

    /**
     * <p>Constructor for XmlResultingSequence.</p>
     *
     * @param oldSequence a {@link String} object.
     * @param newSequence a {@link String} object.
     */
    public XmlResultingSequence(String oldSequence, String newSequence){
        this.originalSequence = oldSequence;
        this.newSequence = newSequence;
    }

    /**
     * <p>Getter for the field <code>newSequence</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getNewSequence() {
        return newSequence;
    }

    /**
     * <p>Getter for the field <code>originalSequence</code>.</p>
     *
     * @return a {@link String} object.
     */
    public String getOriginalSequence() {
        return originalSequence;
    }

    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Xref> getXrefs() {
        if (xrefContainer == null){
            xrefContainer = new XrefContainer();
        }
        return this.xrefContainer.getXrefs();
    }

    /** {@inheritDoc} */
    public void setNewSequence(String sequence) {
        this.newSequence = sequence;
    }

    /** {@inheritDoc} */
    public void setOriginalSequence(String sequence) {
        this.originalSequence = sequence;
    }

    /**
     * <p>setJAXBNewSequence.</p>
     *
     * @param sequence a {@link String} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "newSequence", required = true)
    public void setJAXBNewSequence(String sequence) {
        setNewSequence(sequence);
    }

    /**
     * <p>setJAXBOriginalSequence.</p>
     *
     * @param sequence a {@link String} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "originalSequence", required = true)
    public void setJAXBOriginalSequence(String sequence) {
        setOriginalSequence(sequence);
    }

    /**
     * <p>setJAXBXref.</p>
     *
     * @param value a {@link XrefContainer} object.
     */
    @XmlElement(namespace = "net:sf:psidev:mi", name = "xref")
    public void setJAXBXref(XrefContainer value) {
        this.xrefContainer = value;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof ResultingSequence)){
            return false;
        }

        return ResultingSequenceComparator.areEquals(this, (ResultingSequence) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return ResultingSequenceComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (originalSequence != null ? "original sequence: "+originalSequence : "") +
                (newSequence != null ? "new sequence: "+newSequence : "");
    }

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        if (sourceLocator == null && locator != null){
            sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
        }
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    /**
     * <p>setSourceLocation.</p>
     *
     * @param sourceLocator a {@link PsiXmlLocator} object.
     */
    public void setSourceLocation(PsiXmlLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }
}
