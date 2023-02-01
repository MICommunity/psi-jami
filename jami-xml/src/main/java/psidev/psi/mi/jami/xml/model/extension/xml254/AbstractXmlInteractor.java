package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.model.extension.AbstractBaseXmlInteractor;

import java.util.List;

/**
 * The Xml implementation of Interactor
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractXmlInteractor extends AbstractBaseXmlInteractor {

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     */
    public AbstractXmlInteractor(){
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlInteractor(String name, CvTerm type){
        super(name, type);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlInteractor(String name, String fullName, CvTerm type){
        super(name, fullName, type);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public AbstractXmlInteractor(String name, CvTerm type, Organism organism){
        super(name, type, organism);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public AbstractXmlInteractor(String name, String fullName, CvTerm type, Organism organism){
        super(name, fullName, type, organism);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractXmlInteractor(String name, CvTerm type, Xref uniqueId){
        super(name, type, uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractXmlInteractor(String name, String fullName, CvTerm type, Xref uniqueId){
        super(name, fullName, type, uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractXmlInteractor(String name, CvTerm type, Organism organism, Xref uniqueId){
        super(name, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractXmlInteractor(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId){
        super(name, fullName, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public AbstractXmlInteractor(String name){
        super(name);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public AbstractXmlInteractor(String name, String fullName){
        super(name, fullName);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public AbstractXmlInteractor(String name, Organism organism){
        super(name, organism);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public AbstractXmlInteractor(String name, String fullName, Organism organism){
        super(name, fullName, organism);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractXmlInteractor(String name, Xref uniqueId){
        super(name, uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractXmlInteractor(String name, String fullName, Xref uniqueId){
        super(name, fullName, uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractXmlInteractor(String name, Organism organism, Xref uniqueId){
        super(name, organism, uniqueId);
    }

    /**
     * <p>Constructor for AbstractXmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public AbstractXmlInteractor(String name, String fullName, Organism organism, Xref uniqueId){
        super(name, fullName, organism, uniqueId);
    }

    /**
     * <p>initialiseXrefContainer.</p>
     */
    protected void initialiseXrefContainer() {
        setXrefContainer(new InteractorXrefContainer());
    }

    /**
     * <p>initialiseNamesContainer.</p>
     */
    protected void initialiseNamesContainer() {
        setNamesContainer(new NamesContainer());
    }

    /**
     * Sets the value of the names property.
     *
     * @param value
     *     allowed object is
     *     {@link psidev.psi.mi.jami.xml.model.extension.xml254.NamesContainer}
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="names", required = true)
    public void setJAXBNames(NamesContainer value) {
        super.setJAXBNames(value);
    }

    /**
     * Sets the value of the xrefContainer property.
     *
     * @param value
     *     allowed object is
     *     {@link psidev.psi.mi.jami.xml.model.extension.xml254.InteractorXrefContainer}
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="xref")
    public void setJAXBXref(InteractorXrefContainer value) {
        setXrefContainer(value);
    }

    /**
     * <p>setJAXBInteractorType.</p>
     *
     * @param interactorType a {@link psidev.psi.mi.jami.xml.model.extension.xml254.XmlCvTerm} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="interactorType", required = true)
    public void setJAXBInteractorType(XmlCvTerm interactorType) {
        super.setInteractorType(interactorType);
    }

    /**
     * <p>setJAXBOrganism.</p>
     *
     * @param organism a {@link psidev.psi.mi.jami.xml.model.extension.xml254.XmlOrganism} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="organism")
    public void setJAXBOrganism(XmlOrganism organism) {
        super.setJAXBOrganism(organism);
    }

    /**
     * Sets the value of the sequence property.
     *
     * @param value
     *     allowed object is
     *     {@link java.lang.String}
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name ="sequence")
    public void setSequence(String value) {
        super.setSequence(value);
    }

    /**
     * <p>setJAXBId.</p>
     *
     * @param value a int.
     */
    @XmlAttribute(name = "id", required = true)
    public void setJAXBId(int value) {
        setId(value);
    }

    /**
     * Gets the value of the attributeList property.
     *
     * @param wrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml254.AbstractXmlInteractor.JAXBAttributeWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper wrapper) {
        super.setJAXBAttributeWrapper(wrapper);
    }

    /**
     * <p>createDefaultInteractorType.</p>
     */
    protected void createDefaultInteractorType() {
        setInteractorType(new XmlCvTerm(Interactor.UNKNOWN_INTERACTOR, new XmlXref(CvTermUtils.createPsiMiDatabase(),Interactor.UNKNOWN_INTERACTOR_MI, CvTermUtils.createIdentityQualifier())));
    }

    /**
     * <p>initialiseAnnotationWrapper.</p>
     */
    protected void initialiseAnnotationWrapper(){
        setJAXBAttributeWrapper(new JAXBAttributeWrapper());
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif", name="interactorAttributeWrapper")
    public static class JAXBAttributeWrapper extends AbstractJAXBAttributeWrapper {

        public JAXBAttributeWrapper(){
            super();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif", type= DefaultXmlAnnotation.class, name="attribute", required = true)
        public List<Annotation> getJAXBAttributes() {
            return super.getJAXBAttributes();
        }
    }
}
