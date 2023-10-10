package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.listener.EntityInteractorChangeListener;
import psidev.psi.mi.jami.model.CausalRelationship;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlEntity;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.extension.XmlStoichiometry;
import psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory;
import psidev.psi.mi.jami.xml.model.reference.xml300.AbstractInteractorRef;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract class for XmlEntity
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/10/13</pre>
 */
@XmlTransient
public abstract class AbstractXmlEntity<F extends Feature> implements ExtendedPsiXmlEntity<F>, FileSourceContext, Locatable{

    private Interactor interactor;
    private Collection<CausalRelationship> causalRelationships;
    private PsiXmlLocator sourceLocator;
    private XmlInteractorFactory interactorFactory;
    private EntityInteractorChangeListener changeListener;
    private int id;

    private JAXBFeatureWrapper<F> jaxbFeatureWrapper;

    private Stoichiometry stoichiometry;

    /**
     * <p>Constructor for AbstractXmlEntity.</p>
     */
    public AbstractXmlEntity(){
        this.interactorFactory = XmlEntryContext.getInstance().getInteractorFactory();
    }

    /**
     * <p>Constructor for AbstractXmlEntity.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public AbstractXmlEntity(Interactor interactor){
        if (interactor == null){
            throw new IllegalArgumentException("The interactor cannot be null.");
        }
        this.interactor = interactor;
        this.interactorFactory = XmlEntryContext.getInstance().getInteractorFactory();
    }

    /**
     * <p>Constructor for AbstractXmlEntity.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public AbstractXmlEntity(Interactor interactor, Stoichiometry stoichiometry){
        this(interactor);
        setStoichiometry(stoichiometry);
        this.interactorFactory =  XmlEntryContext.getInstance().getInteractorFactory();
    }

    /**
     * <p>Getter for the field <code>interactor</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public Interactor getInteractor() {
        if (this.interactor == null){
            initialiseUnspecifiedInteractor();
        }
        return this.interactor;
    }

    /** {@inheritDoc} */
    public void setInteractor(Interactor interactor) {
        if (interactor == null){
            throw new IllegalArgumentException("The interactor cannot be null.");
        }
        Interactor oldInteractor = this.interactor;

        this.interactor = interactor;
        if (this.changeListener != null){
            this.changeListener.onInteractorUpdate(this, oldInteractor);
        }
    }

    /**
     * <p>Getter for the field <code>causalRelationships</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<CausalRelationship> getCausalRelationships() {
        if (this.causalRelationships == null){
            this.causalRelationships = new ArrayList<CausalRelationship>();
        }
        return this.causalRelationships;
    }

    /**
     * <p>Getter for the field <code>stoichiometry</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public Stoichiometry getStoichiometry() {
        return this.stoichiometry;
    }

    /** {@inheritDoc} */
    public void setStoichiometry(Integer stoichiometry) {
        if (stoichiometry == null){
            this.stoichiometry = null;
        }
        else {
            this.stoichiometry = new XmlStoichiometry(stoichiometry);
        }
    }

    /**
     * <p>Setter for the field <code>stoichiometry</code>.</p>
     *
     * @param stoichiometry a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     */
    public void setStoichiometry(Stoichiometry stoichiometry) {
        if (stoichiometry == null){
            this.stoichiometry = null;
        }
        else {
            this.stoichiometry= stoichiometry;
        }
    }

    /**
     * <p>Getter for the field <code>changeListener</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.listener.EntityInteractorChangeListener} object.
     */
    public EntityInteractorChangeListener getChangeListener() {
        return this.changeListener;
    }

    /** {@inheritDoc} */
    public void setChangeListener(EntityInteractorChangeListener listener) {
        this.changeListener = listener;
    }

    /**
     * <p>getFeatures.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<F> getFeatures() {
        if (jaxbFeatureWrapper == null){
            initialiseFeatureWrapper();
        }
        return this.jaxbFeatureWrapper.features;
    }

    /**
     * <p>addFeature.</p>
     *
     * @param feature a F object.
     * @return a boolean.
     */
    public boolean addFeature(F feature) {

        if (feature == null){
            return false;
        }

        if (getFeatures().add(feature)){
            feature.setParticipant(this);
            return true;
        }
        return false;
    }

    /**
     * <p>removeFeature.</p>
     *
     * @param feature a F object.
     * @return a boolean.
     */
    public boolean removeFeature(F feature) {

        if (feature == null){
            return false;
        }

        if (getFeatures().remove(feature)){
            feature.setParticipant(null);
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    public boolean addAllFeatures(Collection<? extends F> features) {
        if (features == null){
            return false;
        }

        boolean added = false;
        for (F feature : features){
            if (addFeature(feature)){
                added = true;
            }
        }
        return added;
    }

    /** {@inheritDoc} */
    public boolean removeAllFeatures(Collection<? extends F> features) {
        if (features == null){
            return false;
        }

        boolean added = false;
        for (F feature : features){
            if (removeFeature(feature)){
                added = true;
            }
        }
        return added;
    }

    /**
     * <p>setJAXBInteractor.</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.xml.model.extension.xml300.DefaultXmlInteractor} object.
     */
    public void setJAXBInteractor(DefaultXmlInteractor interactor) {
        if (interactor == null){
            this.interactor = null;
            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
            if (listener != null){
                listener.onParticipantWithoutInteractor(null, this);
            }
        }
        else{
            this.interactor = this.interactorFactory.createInteractorFromXmlInteractorInstance(interactor, PsiXmlVersion.v3_0_0);
        }
    }

    /**
     * Sets the value of the interactorRef property.
     *
     * @param value
     *     allowed object is
     *     {@link java.lang.Integer}
     */
    public void setJAXBInteractorRef(Integer value) {
        if (value != null){
            this.interactor = new InteractorRef(value);
        }
    }

    /**
     * Gets the value of the id property.
     *
     * @return a int.
     */
    public int getId() {
        return id;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the id property.
     */
    public void setId(int value) {
        this.id = value;
        XmlEntryContext.getInstance().registerParticipant(this.id, this);
        if (getSourceLocator() != null){
            this.sourceLocator.setObjectId(this.id);
        }
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
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
            this.sourceLocator.setObjectId(getId());
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), getId());
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Participant: "+getSourceLocator().toString():super.toString());
    }

    /**
     * <p>setFeatureWrapper.</p>
     *
     * @param jaxbFeatureWrapper a {@link psidev.psi.mi.jami.xml.model.extension.xml300.AbstractXmlEntity.JAXBFeatureWrapper} object.
     */
    protected void setFeatureWrapper(JAXBFeatureWrapper<F> jaxbFeatureWrapper) {
        this.jaxbFeatureWrapper = jaxbFeatureWrapper;
        // initialise all features because of back references
        if (this.jaxbFeatureWrapper != null && !this.jaxbFeatureWrapper.features.isEmpty()){
            for (F feature : this.jaxbFeatureWrapper.features){
                processAddedFeature(feature);
            }
        }
    }

    /**
     * <p>processAddedFeature.</p>
     *
     * @param feature a F object.
     */
    protected void processAddedFeature(F feature){
        feature.setParticipant(this);
    }

    /**
     * <p>initialiseUnspecifiedInteractor.</p>
     */
    protected void initialiseUnspecifiedInteractor() {
        this.interactor = new DefaultXmlInteractor(PsiXmlUtils.UNSPECIFIED);
    }

    /**
     * <p>initialiseFeatureWrapper.</p>
     */
    protected void initialiseFeatureWrapper(){
        this.jaxbFeatureWrapper = new JAXBFeatureWrapper();
    }

    /**
     * <p>getParticipantLocator.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    protected FileSourceLocator getParticipantLocator(){
        return getSourceLocator();
    }

    //////////////////////////////////////////////////////////// classes

    private class InteractorRef extends AbstractInteractorRef {
        public InteractorRef(int ref) {
            super(ref);
        }

        @Override
        public boolean resolve(PsiXmlIdCache parsedObjects) {
            if (parsedObjects.containsInteractor(this.ref)){
                Interactor i = parsedObjects.getInteractor(this.ref);
                if (i != null){
                    interactor = i;
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Participant Interactor Reference: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
        }

        public FileSourceLocator getSourceLocator() {
            return getParticipantLocator();
        }

        public void setSourceLocator(FileSourceLocator locator) {
            throw new UnsupportedOperationException("Cannot set the source locator of an interactor ref");
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="featureWrapper")
    public static class JAXBFeatureWrapper<F extends Feature> implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<F> features;

        public JAXBFeatureWrapper(){
            initialiseFeatures();
        }

        public JAXBFeatureWrapper(List<F> features){
            this.features = features;
        }

        @Override
        public Locator sourceLocation() {
            return (Locator)getSourceLocator();
        }

        public FileSourceLocator getSourceLocator() {
            if (sourceLocator == null && locator != null){
                sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
            }
            return sourceLocator;
        }

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

        protected void initialiseFeatures(){
            this.features = new ArrayList<F>();
        }

        public List<F> getJAXBFeatures() {
            return this.features;
        }

        @Override
        public String toString() {
            return "Participant Feature List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}
