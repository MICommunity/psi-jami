package psidev.psi.mi.jami.xml.model.extension.xml300;

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
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.ParticipantCandidate;
import psidev.psi.mi.jami.model.Stoichiometry;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlEntity;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.extension.XmlStoichiometry;
import psidev.psi.mi.jami.xml.model.reference.xml300.AbstractComplexRef;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract class for XmlParticipant
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/10/13</pre>
 */
@XmlTransient
public abstract class AbstractXmlParticipant<I extends Interaction, F extends Feature> extends AbstractXmlEntity<F>
        implements FileSourceContext, Locatable, ExtendedPsiXmlEntity<F>, psidev.psi.mi.jami.model.NamedParticipant<I, F> {

    private I interaction;
    private CvTerm biologicalRole;
    private CvTerm biologicalEffect;

    private NamesContainer namesContainer;
    private XrefContainer xrefContainer;

    private JAXBAttributeWrapper jaxbAttributeWrapper;

    private JAXBInteractorCandidateWrapper jaxbCandidateWrapper;

    /**
     * <p>Constructor for AbstractXmlParticipant.</p>
     */
    public AbstractXmlParticipant(){
        super();
    }

    /**
     * <p>Constructor for AbstractXmlParticipant.</p>
     *
     * @param interactor a {@link Interactor} object.
     */
    public AbstractXmlParticipant(Interactor interactor){
        super(interactor);
        this.biologicalRole = new XmlCvTerm(Participant.UNSPECIFIED_ROLE, new XmlXref(CvTermUtils.createPsiMiDatabase(), Participant.UNSPECIFIED_ROLE_MI, CvTermUtils.createIdentityQualifier()));
    }

    /**
     * <p>Constructor for AbstractXmlParticipant.</p>
     *
     * @param interactor a {@link Interactor} object.
     * @param bioRole a {@link CvTerm} object.
     */
    public AbstractXmlParticipant(Interactor interactor, CvTerm bioRole){
        super(interactor);
        this.biologicalRole = bioRole != null ? bioRole : new XmlCvTerm(Participant.UNSPECIFIED_ROLE, new XmlXref(CvTermUtils.createPsiMiDatabase(), Participant.UNSPECIFIED_ROLE_MI, CvTermUtils.createIdentityQualifier()));
    }

    /**
     * <p>Constructor for AbstractXmlParticipant.</p>
     *
     * @param interactor a {@link Interactor} object.
     * @param stoichiometry a {@link Stoichiometry} object.
     */
    public AbstractXmlParticipant(Interactor interactor, Stoichiometry stoichiometry){
        super(interactor, stoichiometry);
    }

    /**
     * <p>Constructor for AbstractXmlParticipant.</p>
     *
     * @param interactor a {@link Interactor} object.
     * @param bioRole a {@link CvTerm} object.
     * @param stoichiometry a {@link Stoichiometry} object.
     */
    public AbstractXmlParticipant(Interactor interactor, CvTerm bioRole, Stoichiometry stoichiometry){
        this(interactor, bioRole);
        setStoichiometry(stoichiometry);
    }

    /**
     * <p>setInteractionAndAddParticipant.</p>
     *
     * @param interaction a I object.
     */
    public void setInteractionAndAddParticipant(I interaction) {

        if (this.interaction != null){
            // normal participant
            if (this.jaxbCandidateWrapper == null){
                this.interaction.removeParticipant(this);
            }
            // participant pool
            else{
                this.interaction.removeParticipant(this.jaxbCandidateWrapper.pool);
                this.interaction = null;
            }
        }

        if (interaction != null){
            // normal participant
            if (this.jaxbCandidateWrapper == null){
                interaction.addParticipant(this);
            }
            // participant pool
            else{
                interaction.addParticipant(this.jaxbCandidateWrapper.pool);
                this.interaction = interaction;
            }
        }
    }

    /**
     * <p>Getter for the field <code>interaction</code>.</p>
     *
     * @return a I object.
     */
    public I getInteraction() {
        return this.interaction;
    }

    /**
     * <p>Setter for the field <code>interaction</code>.</p>
     *
     * @param interaction a I object.
     */
    public void setInteraction(I interaction) {
        this.interaction = interaction;
        if (jaxbCandidateWrapper != null){
           this.jaxbCandidateWrapper.pool.setInteraction(this.interaction);
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        return this.namesContainer != null ? this.namesContainer.getShortLabel():null;
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        if (this.namesContainer == null){
            this.namesContainer = new NamesContainer();
        }
        this.namesContainer.setShortLabel(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return this.namesContainer != null ? this.namesContainer.getFullName():null;
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        if (this.namesContainer == null){
            this.namesContainer = new NamesContainer();
        }
        this.namesContainer.setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public List<Alias> getAliases() {
        if (namesContainer == null){
            namesContainer = new NamesContainer();
        }
        return this.namesContainer.getAliases();
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

    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (this.jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        return this.jaxbAttributeWrapper.annotations;
    }

    /**
     * <p>getStoichiometry.</p>
     *
     * @return a {@link Stoichiometry} object.
     */
    public Stoichiometry getStoichiometry() {
        if (super.getStoichiometry() == null){
            initialiseStoichiometry();
        }
        return super.getStoichiometry();
    }

    /** {@inheritDoc} */
    public void setStoichiometry(Integer stoichiometry) {
        if (stoichiometry == null){
            super.setStoichiometry((Integer)null);
            if (this.jaxbAttributeWrapper != null){
                this.jaxbAttributeWrapper.stoichiometry = null;
            }
        }
        else {
            super.setStoichiometry(new XmlStoichiometry(stoichiometry));
        }
    }

    /** {@inheritDoc} */
    public void setStoichiometry(Stoichiometry stoichiometry) {
        if (stoichiometry == null){
            super.setStoichiometry((Stoichiometry)null);
            if (this.jaxbAttributeWrapper != null){
                this.jaxbAttributeWrapper.stoichiometry = null;
            }
        }
        else {
            super.setStoichiometry(stoichiometry);
        }
    }

    /**
     * Gets the value of the biologicalRole property.
     *
     * @return a {@link CvTerm} object.
     */
    public CvTerm getBiologicalRole() {
        if (this.biologicalRole == null){
            this.biologicalRole = new XmlCvTerm(Participant.UNSPECIFIED_ROLE, new XmlXref(CvTermUtils.createPsiMiDatabase(), Participant.UNSPECIFIED_ROLE_MI, CvTermUtils.createIdentityQualifier()));
        }
        return this.biologicalRole;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the biologicalRole property.
     */
    public void setBiologicalRole(CvTerm bioRole) {
        if (bioRole == null){
            this.biologicalRole = new XmlCvTerm(Participant.UNSPECIFIED_ROLE, new XmlXref(CvTermUtils.createPsiMiDatabase(), Participant.UNSPECIFIED_ROLE_MI, CvTermUtils.createIdentityQualifier()));
        }
        else {
            biologicalRole = bioRole;
        }
    }

    /**
     * Gets the value of the biologicalEffect property.
     *
     * @return a {@link CvTerm} object.
     */
    public CvTerm getBiologicalEffect() {
        return this.biologicalEffect;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the biologicalEffect property.
     */
    public void setBiologicalEffect(CvTerm biologicalEffect) {
        this.biologicalEffect = biologicalEffect;
    }

    /**
     * Sets the value of the namesContainer property.
     *
     * @param value
     *     allowed object is
     *     {@link NamesContainer}
     */
    public void setJAXBNames(NamesContainer value) {
        this.namesContainer = value;
    }

    /**
     * Sets the value of the xrefContainer property.
     *
     * @param value
     *     allowed object is
     *     {@link XrefContainer}
     */
    public void setJAXBXref(XrefContainer value) {
        this.xrefContainer = value;
    }

    /**
     * Sets the value of the biologicalRole property.
     *
     * @param bioRole
     *     allowed object is
     *     {@link XmlCvTerm}
     */
    public void setJAXBBiologicalRole(XmlCvTerm bioRole) {
        setBiologicalRole(bioRole);
    }

    /**
     * <p>setJAXBStoichiometry.</p>
     *
     * @param stoichiometry a {@link XmlStoichiometry} object.
     */
    public void setJAXBStoichiometry(XmlStoichiometry stoichiometry){
        super.setStoichiometry(stoichiometry);
    }

    /**
     * <p>setJAXBStoichiometryRange.</p>
     *
     * @param stoichiometry a {@link XmlStoichiometryRange} object.
     */
    public void setJAXBStoichiometryRange(XmlStoichiometryRange stoichiometry){
        super.setStoichiometry(stoichiometry);
    }

    /**
     * Sets the value of the interactionRef property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer}
     */
    public void setJAXBInteractionRef(Integer value) {
        if (value != null){
            super.setInteractor(new InteractionRef(value));
        }
    }

    /**
     * <p>getParticipantPool.</p>
     *
     * @return a {@link AbstractXmlParticipantPool} object.
     */
    public AbstractXmlParticipantPool<I,F,? extends ParticipantCandidate> getParticipantPool() {
        return this.jaxbCandidateWrapper != null ? this.jaxbCandidateWrapper.pool : null;
    }

    /**
     * Sets the value of the interactor candidates property.
     *
     * @param value
     *     allowed object is
     *     {@link Integer}
     */
    protected void setJAXBInteractorCandidates(JAXBInteractorCandidateWrapper<I,F,? extends ParticipantCandidate> value) {
        this.jaxbCandidateWrapper = value;
        if (this.jaxbCandidateWrapper != null){
            this.jaxbCandidateWrapper.pool.setDelegate(this);
        }
    }

    /**
     * <p>setJAXBAttributeWrapper.</p>
     *
     * @param jaxbAttributeWrapper a {@link JAXBAttributeWrapper} object.
     */
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper jaxbAttributeWrapper) {
        this.jaxbAttributeWrapper = jaxbAttributeWrapper;
    }

    /**
     * <p>initialiseStoichiometry.</p>
     */
    protected void initialiseStoichiometry() {

        if (this.jaxbAttributeWrapper != null && this.jaxbAttributeWrapper.stoichiometry != null){
            super.setStoichiometry(this.jaxbAttributeWrapper.stoichiometry);
        }
    }

    /**
     * <p>initialiseAnnotationWrapper.</p>
     */
    protected void initialiseAnnotationWrapper() {
        this.jaxbAttributeWrapper = new JAXBAttributeWrapper();
    }

    //////////////////////////////////////////////////////////// classes

    private class InteractionRef extends AbstractComplexRef {
        public InteractionRef(int ref) {
            super(ref);
        }

        @Override
        public boolean resolve(PsiXmlIdCache parsedObjects) {
            // take it from existing references
            if (parsedObjects.containsComplex(this.ref)){
                Complex i = parsedObjects.getComplex(this.ref);
                if (i != null){
                    setInteractor(i);
                    return true;
                }
                else{
                    PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
                    if (listener != null){
                        listener.onParticipantWithoutInteractor(null, this);
                    }
                }
            }
            else if (parsedObjects.containsInteraction(this.ref)){
                Interaction object = parsedObjects.getInteraction(this.ref);
                if (object != null){
                    Complex reloadedComplex = parsedObjects.registerComplexLoadedFrom(object);
                    if (reloadedComplex != null){
                        setInteractor(reloadedComplex);
                        return true;
                    }
                    else{
                        PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
                        if (listener != null){
                            listener.onParticipantWithoutInteractor(null, this);
                        }
                    }
                }
                else{
                    PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
                    if (listener != null){
                        listener.onParticipantWithoutInteractor(null, this);
                    }
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Participant Interaction Reference: "+(ref+(getSourceLocator() != null ? ","+getSourceLocator().toString():super.toString()));
        }

        public FileSourceLocator getSourceLocator() {
            return getParticipantLocator();
        }

        public void setSourceLocator(FileSourceLocator locator) {
            throw new UnsupportedOperationException("Cannot set the source locator of an interaction ref");
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="entityAttributeWrapper")
    public static class JAXBAttributeWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Annotation> annotations;
        private JAXBAttributeList jaxbAttributeList;
        private Stoichiometry stoichiometry;

        public JAXBAttributeWrapper(){
            initialiseAnnotations();
            this.stoichiometry = null;
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

        protected void initialiseAnnotations(){
            annotations = new ArrayList<Annotation>();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= DefaultXmlAnnotation.class, name="attribute", required = true)
        public List<Annotation> getJAXBAttributes() {
            if (this.jaxbAttributeList == null){
                this.jaxbAttributeList = new JAXBAttributeList();
            }
            return this.jaxbAttributeList;
        }

        private class JAXBAttributeList extends ArrayList<Annotation> {

            public JAXBAttributeList(){
                super();
                annotations = new ArrayList<Annotation>();
            }

            @Override
            public boolean add(Annotation annotation) {
                if (annotation == null){
                    return false;
                }
                return processAnnotation(null, annotation);
            }

            @Override
            public boolean addAll(Collection<? extends Annotation> c) {
                if (c == null){
                    return false;
                }
                boolean added = false;

                for (Annotation a : c){
                    if (add(a)){
                        added = true;
                    }
                }
                return added;
            }

            @Override
            public void add(int index, Annotation element) {
                processAnnotation(index, element);
            }

            @Override
            public boolean addAll(int index, Collection<? extends Annotation> c) {
                int newIndex = index;
                if (c == null){
                    return false;
                }
                boolean add = false;
                for (Annotation a : c){
                    if (processAnnotation(newIndex, a)){
                        newIndex++;
                        add = true;
                    }
                }
                return add;
            }

            protected boolean addAnnotation(Integer index, Annotation annotation) {
                if (index == null){
                    return annotations.add(annotation);
                }
                ((List<Annotation>)annotations).add(index, annotation);
                return true;
            }

            private boolean processAnnotation(Integer index, Annotation annotation) {
                // we have stoichiometry
                if(AnnotationUtils.doesAnnotationHaveTopic(annotation, Annotation.COMMENT_MI, Annotation.COMMENT)
                        && annotation.getValue() != null && annotation.getValue().trim().toLowerCase().startsWith(PsiXmlUtils.STOICHIOMETRY_PREFIX)){
                    String stc = annotation.getValue().substring(annotation.getValue().indexOf(PsiXmlUtils.STOICHIOMETRY_PREFIX) + PsiXmlUtils.STOICHIOMETRY_PREFIX.length()).trim();

                    // we have stoichiometry range
                    if (stc.contains("-") && !stc.startsWith("-")){
                        String [] stcs = stc.split("-");
                        // we recognize the stoichiometry range
                        if (stcs.length == 2){
                            try{
                                XmlStoichiometry s = new XmlStoichiometry((int)Double.parseDouble(stc.trim()));
                                s.setSourceLocator(sourceLocator);
                                stoichiometry = s;
                                return false;
                            }
                            catch (NumberFormatException e){
                                PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
                                if (listener != null){
                                    listener.onInvalidStoichiometry("The stoichiometry is invalid "+ e.getMessage(), JAXBAttributeWrapper.this);
                                }
                                return addAnnotation(index, annotation);
                            }
                        }
                        // we cannot recognize the stoichiometry range, we add that as a simple annotation
                        else {
                            return addAnnotation(index, annotation);
                        }
                    }
                    // simple stoichiometry
                    else {
                        try{
                            XmlStoichiometry s = new XmlStoichiometry((int)Double.parseDouble(stc.trim()));
                            s.setSourceLocator(sourceLocator);
                            stoichiometry = s;
                            return false;
                        }
                        // not a number, keep the annotation as annotation
                        catch (NumberFormatException e){
                            PsiXmlParserListener listener = XmlEntryContext.getInstance().getListener();
                            if (listener != null){
                                listener.onInvalidStoichiometry("The stoichiometry is invalid "+ e.getMessage(), JAXBAttributeWrapper.this);
                            }
                            return addAnnotation(index, annotation);
                        }
                    }
                }
                else{
                    return addAnnotation(null, annotation);
                }
            }
        }

        @Override
        public String toString() {
            return "Participant Attribute List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="interactorCandidateListWrapper")
    public static class JAXBInteractorCandidateWrapper<I extends Interaction,F extends Feature,P extends ParticipantCandidate>
            implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private AbstractXmlParticipantPool<I,F,P> pool;

        public JAXBInteractorCandidateWrapper(){
            initialisePool();
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

        protected void initialisePool(){
            throw new UnsupportedOperationException("cannot instantiate default pool");
        }

        protected void setPool(AbstractXmlParticipantPool<I, F, P> pool) {
            this.pool = pool;
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name="moleculeSetType", required = true)
        public void setJAXBType(XmlCvTerm type) {
            this.pool.setType(type);
        }

        public Collection<P> getCandidates(){
            return this.pool;
        }

        @Override
        public String toString() {
            return "Interactor candidate List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}
