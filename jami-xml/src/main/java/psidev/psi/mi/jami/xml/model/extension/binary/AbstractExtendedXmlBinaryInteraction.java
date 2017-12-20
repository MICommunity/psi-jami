package psidev.psi.mi.jami.xml.model.extension.binary;

import psidev.psi.mi.jami.binary.impl.AbstractBinaryInteraction;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;
import psidev.psi.mi.jami.xml.model.extension.InferredInteraction;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class for xml binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/10/13</pre>
 */
public abstract class AbstractExtendedXmlBinaryInteraction<P extends Participant> extends AbstractBinaryInteraction<P> implements
        ExtendedPsiXmlInteraction<P>, FileSourceContext {

    private PsiXmlLocator sourceLocator;
    private String fullName;
    private List<Alias> aliases;
    private Entry entry;
    private int id;
    private List<CvTerm> interactionTypes;
    private List<InferredInteraction> inferredInteractions;
    private Boolean intraMolecular;

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     */
    public AbstractExtendedXmlBinaryInteraction(){
        super();
    }

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public AbstractExtendedXmlBinaryInteraction(String shortName){
        super(shortName);
    }

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractExtendedXmlBinaryInteraction(String shortName, CvTerm type){
        super(shortName, type);
    }

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     *
     * @param participantA a P object.
     * @param participantB a P object.
     */
    public AbstractExtendedXmlBinaryInteraction(P participantA, P participantB){
        super(participantA, participantB);

    }

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a P object.
     * @param participantB a P object.
     */
    public AbstractExtendedXmlBinaryInteraction(String shortName, P participantA, P participantB){
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a P object.
     * @param participantB a P object.
     */
    public AbstractExtendedXmlBinaryInteraction(String shortName, CvTerm type, P participantA, P participantB){
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractExtendedXmlBinaryInteraction(CvTerm complexExpansion){
        super(complexExpansion);
    }

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractExtendedXmlBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion){
        super(shortName, type, complexExpansion);

    }

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     *
     * @param participantA a P object.
     * @param participantB a P object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractExtendedXmlBinaryInteraction(P participantA, P participantB, CvTerm complexExpansion){
        super(participantA, participantB, complexExpansion);

    }

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a P object.
     * @param participantB a P object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractExtendedXmlBinaryInteraction(String shortName, P participantA, P participantB, CvTerm complexExpansion){
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for AbstractExtendedXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a P object.
     * @param participantB a P object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractExtendedXmlBinaryInteraction(String shortName, CvTerm type, P participantA, P participantB, CvTerm complexExpansion){
        super(shortName, type, participantA, participantB, complexExpansion);
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
        this.sourceLocator = (PsiXmlLocator)sourceLocator;
    }

    /**
     * <p>isIntraMolecular.</p>
     *
     * @return a boolean.
     */
    public boolean isIntraMolecular(){
        return intraMolecular != null ? intraMolecular : false;
    }

    /**
     * {@inheritDoc}
     *
     * Sets the value of the intraMolecular property.
     */
    public void setIntraMolecular(boolean value) {
        this.intraMolecular = value;
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return this.fullName;
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        this.fullName = name;
    }

    /** {@inheritDoc} */
    @Override
    public List<Alias> getAliases() {
        if (this.aliases == null){
            this.aliases = new ArrayList<Alias>();
        }
        return this.aliases;
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getInteractionType() {
        if (interactionTypes == null || interactionTypes.isEmpty()){
            return null;
        }
        return interactionTypes.iterator().next();
    }

    /** {@inheritDoc} */
    @Override
    public void setInteractionType(CvTerm term) {
        if (!getInteractionTypes().isEmpty()){
            interactionTypes.remove(0);
        }
        if (term != null){
            interactionTypes.add(0, term);
        }
    }

    /** {@inheritDoc} */
    @Override
    public List<CvTerm> getInteractionTypes() {
        if (this.interactionTypes == null){
            this.interactionTypes = new ArrayList<CvTerm>();
        }
        return this.interactionTypes;
    }

    /** {@inheritDoc} */
    @Override
    public Entry getEntry() {
        return this.entry;
    }

    /** {@inheritDoc} */
    @Override
    public void setEntry(Entry entry) {
        this.entry = entry;
    }

    /** {@inheritDoc} */
    @Override
    public List<InferredInteraction> getInferredInteractions() {
        if (this.inferredInteractions == null){
            this.inferredInteractions = new ArrayList<InferredInteraction>();
        }
        return this.inferredInteractions;
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return this.id;
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        this.id = id;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Binary interaction: "+getSourceLocator().toString():super.toString());
    }
}
