package psidev.psi.mi.jami.xml.model.extension.binary;

import psidev.psi.mi.jami.binary.impl.AbstractBinaryInteraction;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlInteraction;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Abstract class for xml binary interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>29/10/13</pre>
 */
public abstract class AbstractXmlBinaryInteraction<P extends Participant> extends AbstractBinaryInteraction<P>
        implements PsiXmlInteraction<P>, FileSourceContext {

    private PsiXmlLocator sourceLocator;
    private String fullName;
    private List<Alias> aliases;
    private Entry entry;
    private int id;
    private Boolean intraMolecular;

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     */
    public AbstractXmlBinaryInteraction(){
        super();
    }

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public AbstractXmlBinaryInteraction(String shortName){
        super(shortName);
    }

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteraction(String shortName, CvTerm type){
        super(shortName, type);
    }

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     *
     * @param participantA a P object.
     * @param participantB a P object.
     */
    public AbstractXmlBinaryInteraction(P participantA, P participantB){
        super(participantA, participantB);

    }

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a P object.
     * @param participantB a P object.
     */
    public AbstractXmlBinaryInteraction(String shortName, P participantA, P participantB){
        super(shortName, participantA, participantB);
    }

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a P object.
     * @param participantB a P object.
     */
    public AbstractXmlBinaryInteraction(String shortName, CvTerm type, P participantA, P participantB){
        super(shortName, type, participantA, participantB);
    }

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     *
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteraction(CvTerm complexExpansion){
        super(complexExpansion);
    }

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteraction(String shortName, CvTerm type, CvTerm complexExpansion){
        super(shortName, type, complexExpansion);

    }

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     *
     * @param participantA a P object.
     * @param participantB a P object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteraction(P participantA, P participantB, CvTerm complexExpansion){
        super(participantA, participantB, complexExpansion);

    }

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param participantA a P object.
     * @param participantB a P object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteraction(String shortName, P participantA, P participantB, CvTerm complexExpansion){
        super(shortName, participantA, participantB, complexExpansion);
    }

    /**
     * <p>Constructor for AbstractXmlBinaryInteraction.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param participantA a P object.
     * @param participantB a P object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractXmlBinaryInteraction(String shortName, CvTerm type, P participantA, P participantB, CvTerm complexExpansion){
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
    public Collection<Alias> getAliases() {
        if (this.aliases == null){
            this.aliases = new ArrayList<Alias>();
        }
        return this.aliases;
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
