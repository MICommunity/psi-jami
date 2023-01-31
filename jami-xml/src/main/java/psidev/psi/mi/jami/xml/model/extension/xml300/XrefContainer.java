package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlTransient;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Xref container in XML implementation
 * The JAXB binding is designed to be read-only and is not designed for writing
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlSeeAlso({
        CvTermXrefContainer.class, PublicationXrefContainer.class, InteractorXrefContainer.class, FeatureXrefContainer.class,
        ExperimentXrefContainer.class, InteractionXrefContainer.class
})
public class XrefContainer implements FileSourceContext, Locatable{

    private List<Xref> xrefs;
    private PsiXmlLocator sourceLocator;
    @XmlLocation
    @XmlTransient
    private Locator locator;
    private JAXBSecondaryXrefList jaxbSecondaryRefs;

    /**
     * Sets the value of the primaryRef property.
     *
     * @param value
     *     allowed object is
     *     {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlXref}
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "primaryRef",required = true, type = XmlXref.class)
    public void setJAXBPrimaryRef(Xref value) {
        if (value != null){
            processAddedPrimaryRef(value);
        }
    }

    /**
     * Gets the value of the secondaryReves property.
     *
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the secondaryReves property.
     *
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSecondaryReves().add(newItem);
     * </pre>
     *
     *
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link psidev.psi.mi.jami.xml.model.extension.xml300.XmlXref}
     *
     * @return a {@link java.util.List} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "secondaryRef", type = XmlXref.class)
    public List<Xref> getJAXBSecondaryRefs() {
        if (this.jaxbSecondaryRefs == null) {
            initialiseSecondaryRefs();
        }
        return this.jaxbSecondaryRefs;
    }

    /**
     * <p>Getter for the field <code>xrefs</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Xref> getXrefs() {
        if (xrefs == null) {
            initialiseXrefs();
        }
        return xrefs;
    }

    /**
     * <p>isEmpty.</p>
     *
     * @return a boolean.
     */
    public boolean isEmpty(){
        if (getXrefs().isEmpty()){
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
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

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Xref: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
    }

    /**
     * <p>initialiseXrefs.</p>
     */
    protected void initialiseXrefs(){
        this.xrefs = new ArrayList<Xref>();
    }

    /**
     * <p>initialiseXrefsWith.</p>
     *
     * @param list a {@link java.util.List} object.
     */
    protected void initialiseXrefsWith(List<Xref> list){
        if (list == null){
             this.xrefs = Collections.EMPTY_LIST;
        }
        else{
            this.xrefs = list;
        }
    }

    /**
     * <p>initialiseSecondaryRefs.</p>
     */
    protected void initialiseSecondaryRefs(){
        this.jaxbSecondaryRefs = new JAXBSecondaryXrefList();
    }

    /**
     * <p>initialiseSecondaryResWith.</p>
     *
     * @param list a {@link psidev.psi.mi.jami.xml.model.extension.xml300.XrefContainer.JAXBSecondaryXrefList} object.
     */
    protected void initialiseSecondaryResWith(JAXBSecondaryXrefList list){
        if (list == null){
            this.jaxbSecondaryRefs = new JAXBSecondaryXrefList();
        }
        else{
            this.jaxbSecondaryRefs = list;
        }
    }

    /**
     * <p>processAddedPrimaryRef.</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processAddedPrimaryRef(Xref added) {
        if (xrefs == null){
            initialiseXrefs();
        }
        xrefs.add(0, added);
    }

    ///////////////////////////// classes
    //////////////////////////////// private class
    protected class JAXBSecondaryXrefList extends ArrayList<Xref>{

        protected JAXBSecondaryXrefList() {
        }

        @Override
        public boolean add(Xref xref) {
            if (xref == null){
                return false;
            }
            return addXref(null, xref);
        }

        @Override
        public boolean addAll(Collection<? extends Xref> c) {
            if (c == null){
                return false;
            }
            boolean added = false;

            for (Xref a : c){
                if (add(a)){
                    added = true;
                }
            }
            return added;
        }

        @Override
        public void add(int index, Xref element) {
            addXref(index, element);
        }

        @Override
        public boolean addAll(int index, Collection<? extends Xref> c) {
            int newIndex = index;
            if (c == null){
                return false;
            }
            boolean add = false;
            for (Xref a : c){
                if (addXref(newIndex, a)){
                    newIndex++;
                    add = true;
                }
            }
            return add;
        }

        protected boolean addXref(Integer index, Xref xref) {
            if (index == null){
                return getXrefs().add(xref);
            }
            getXrefs().add(index, xref);
            return true;
        }
    }
}
