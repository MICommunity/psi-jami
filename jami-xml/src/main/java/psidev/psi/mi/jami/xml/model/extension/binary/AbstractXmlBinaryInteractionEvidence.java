package psidev.psi.mi.jami.xml.model.extension.binary;

import psidev.psi.mi.jami.binary.BinaryInteractionEvidence;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlExperiment;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Xml implementation of BinaryInteractionEvidence
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
@XmlTransient
public abstract class AbstractXmlBinaryInteractionEvidence extends AbstractExtendedXmlBinaryInteraction<ParticipantEvidence> implements BinaryInteractionEvidence, ExtendedPsiXmlInteractionEvidence {
    private Xref imexId;
    private String availability;
    private Collection<Parameter> parameters;
    private boolean isInferred = false;
    private Collection<Confidence> confidences;
    private boolean isNegative;
    private Collection<VariableParameterValueSet> variableParameterValueSets;
    private AbstractAvailability xmlAvailability;
    private Boolean isModelled;
    private List<Experiment> experiments;
    private List<ExtendedPsiXmlExperiment> originalExperiments;

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     */
    public AbstractXmlBinaryInteractionEvidence() {
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public AbstractXmlBinaryInteractionEvidence(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteractionEvidence(String shortName, CvTerm type) {
        super(shortName, type);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public AbstractXmlBinaryInteractionEvidence(ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(participantA, participantB);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public AbstractXmlBinaryInteractionEvidence(String shortName, ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     */
    public AbstractXmlBinaryInteractionEvidence(String shortName, CvTerm type, ParticipantEvidence participantA, ParticipantEvidence participantB) {
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteractionEvidence(CvTerm complexExpansion) {
        super(complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteractionEvidence(String shortName, CvTerm type, CvTerm complexExpansion) {
        super(shortName, type, complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteractionEvidence(ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteractionEvidence(String shortName, ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for XmlBinaryInteractionEvidence.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param participantB a {@link psidev.psi.mi.jami.model.ParticipantEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteractionEvidence(String shortName, CvTerm type, ParticipantEvidence participantA, ParticipantEvidence participantB, CvTerm complexExpansion) {
        super(shortName, type, participantA, participantB, complexExpansion);
    }

    /**
     * <p>initialiseExperimentalConfidences.</p>
     */
    protected void initialiseExperimentalConfidences(){
        this.confidences = new ArrayList<Confidence>();
    }

    /**
     * <p>initialiseVariableParameterValueSets.</p>
     */
    protected void initialiseVariableParameterValueSets(){
        this.variableParameterValueSets = new ArrayList<VariableParameterValueSet>();
    }

    /**
     * <p>initialiseExperimentalParameters.</p>
     */
    protected void initialiseExperimentalParameters(){
        this.parameters = new ArrayList<Parameter>();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefs() {
        initialiseXrefsWith(new ExperimentalBinaryInteractionXrefList());
    }

    /**
     * <p>Getter for the field <code>imexId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getImexId() {
        return this.imexId != null ? this.imexId.getId() : null;
    }

    public void setImexId(Xref imexId) {
        this.imexId = imexId;
    }

    protected abstract void setImexId(CvTerm database, String id, CvTerm qualifier);

    /** {@inheritDoc} */
    public void assignImexId(String identifier) {
        // add new imex if not null
        if (identifier != null){
            ExperimentalBinaryInteractionXrefList interactionXrefs = (ExperimentalBinaryInteractionXrefList) getXrefs();
            CvTerm imexDatabase = CvTermUtils.createImexDatabase();
            CvTerm imexPrimaryQualifier = CvTermUtils.createImexPrimaryQualifier();
            // first remove old doi if not null
            if (this.imexId != null){
                interactionXrefs.removeOnly(this.imexId);
            }
            setImexId(imexDatabase, identifier, imexPrimaryQualifier);
            interactionXrefs.addOnly(this.imexId);
        }
        else {
            throw new IllegalArgumentException("The imex id has to be non null.");
        }
    }

    /**
     * <p>getExperiment.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public Experiment getExperiment() {
        if (getExperiments().isEmpty()){
            return null;
        }
        return getExperiments().iterator().next();
    }

    /** {@inheritDoc} */
    public void setExperiment(Experiment experiment) {
        if (experiment != null){
            if (!getExperiments().isEmpty()){
                getExperiments().remove(0);
            }
            getExperiments().add(0, experiment);
        }
        else{
            if (!getExperiments().isEmpty()){
                this.getExperiments().remove(0);
            }
        }
    }

    /** {@inheritDoc} */
    public void setExperimentAndAddInteractionEvidence(Experiment experiment) {
        Experiment current = getExperiment();
        if (current != null){
            current.removeInteractionEvidence(this);
        }

        if (experiment != null){
            experiment.addInteractionEvidence(this);
        }
    }

    /**
     * <p>getVariableParameterValues.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<VariableParameterValueSet> getVariableParameterValues() {

        if (variableParameterValueSets == null){
            initialiseVariableParameterValueSets();
        }
        return this.variableParameterValueSets;
    }

    /**
     * <p>Getter for the field <code>confidences</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Confidence> getConfidences() {
        if (confidences == null){
            initialiseExperimentalConfidences();
        }
        return this.confidences;
    }

    /**
     * <p>Getter for the field <code>availability</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getAvailability() {
        return this.availability;
    }

    /** {@inheritDoc} */
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * <p>isNegative.</p>
     *
     * @return a boolean.
     */
    public boolean isNegative() {
        return this.isNegative;
    }

    /** {@inheritDoc} */
    public void setNegative(boolean negative) {
        this.isNegative = negative;
    }

    /**
     * <p>Getter for the field <code>parameters</code>.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Parameter> getParameters() {
        if (parameters == null){
            initialiseExperimentalParameters();
        }
        return this.parameters;
    }

    /**
     * <p>isInferred.</p>
     *
     * @return a boolean.
     */
    public boolean isInferred() {
        return this.isInferred;
    }

    /** {@inheritDoc} */
    public void setInferred(boolean inferred) {
        this.isInferred = inferred;
    }

    /**
     * <p>processAddedXrefEvent.</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processAddedXrefEvent(Xref added) {

        // the added identifier is imex and the current imex is not set
        if (imexId == null && XrefUtils.isXrefFromDatabase(added, Xref.IMEX_MI, Xref.IMEX)){
            // the added xref is imex-primary
            if (XrefUtils.doesXrefHaveQualifier(added, Xref.IMEX_PRIMARY_MI, Xref.IMEX_PRIMARY)){
                imexId = added;
            }
        }
    }

    /**
     * <p>processRemovedXrefEvent.</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processRemovedXrefEvent(Xref removed) {
        // the removed identifier is pubmed
        if (imexId != null && imexId.equals(removed)){
            imexId = null;
        }
    }

    /**
     * <p>clearPropertiesLinkedToXrefs.</p>
     */
    protected void clearPropertiesLinkedToXrefs() {
        imexId = null;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return imexId != null ? imexId.getId() : super.toString();
    }

    /** {@inheritDoc} */
    @Override
    public AbstractAvailability getXmlAvailability() {
        return this.xmlAvailability;
    }

    /** {@inheritDoc} */
    @Override
    public void setXmlAvailability(AbstractAvailability availability) {
        this.xmlAvailability = availability;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isModelled() {
        return isModelled != null ? isModelled : false;
    }

    /** {@inheritDoc} */
    @Override
    public void setModelled(boolean modelled) {
       isModelled = modelled;
    }

    /** {@inheritDoc} */
    @Override
    public List<Experiment> getExperiments() {
        if (experiments == null){
           experiments = new ArrayList<Experiment>();
        }
        return experiments;
    }

    /** {@inheritDoc} */
    @Override
    public List<ExtendedPsiXmlExperiment> getOriginalExperiments() {
        if (this.originalExperiments == null){
            this.originalExperiments = new ArrayList<ExtendedPsiXmlExperiment>();
        }
        return originalExperiments;
    }

    /**
     * Experimental interaction Xref list
     */
    protected class ExperimentalBinaryInteractionXrefList extends AbstractListHavingProperties<Xref> {
        public ExperimentalBinaryInteractionXrefList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Xref added) {

            processAddedXrefEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Xref removed) {
            processRemovedXrefEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToXrefs();
        }
    }
}
