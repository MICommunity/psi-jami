package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.AllostericEffectorType;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.ModelledEntity;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.model.MoleculeEffector;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.reference.xml300.AbstractEntityRef;

/**
 * XML implementation of allosteric molecule effector
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
@XmlTransient
public class XmlMoleculeEffector implements MoleculeEffector, FileSourceContext {
    private PsiXmlLocator sourceLocator;
    private ModelledEntity participant;

    /**
     * <p>Constructor for XmlMoleculeEffector.</p>
     *
     * @param participant a int.
     * @param locator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
    public XmlMoleculeEffector(int participant, PsiXmlLocator locator){
        this.participant = new MoleculeEffectorRef(participant, locator);
    }

    /**
     * <p>getMolecule.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ModelledEntity} object.
     */
    public ModelledEntity getMolecule() {
        return participant;
    }

    /**
     * <p>getEffectorType.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.AllostericEffectorType} object.
     */
    public AllostericEffectorType getEffectorType() {
        return AllostericEffectorType.molecule;
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
    public void setSourceLocator(FileSourceLocator locator) {
        if (locator == null){
            this.sourceLocator = null;
        }
        else if (locator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)locator;
        }
        else {
            this.sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getCharNumber(), null);
        }
    }

    /**
     * <p>Setter for the field <code>sourceLocator</code>.</p>
     *
     * @param locator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
    public void setSourceLocator(PsiXmlLocator locator) {
        this.sourceLocator = locator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Allosteric molecule effector: "+getSourceLocator().toString():super.toString());
    }

    ////////////////////////////////// inner classes
    /**
     * participant ref for allosteric effector
     */
    private class MoleculeEffectorRef extends AbstractEntityRef<ModelledFeature> implements ModelledEntity{
        private PsiXmlLocator sourceLocator;

        public MoleculeEffectorRef(int ref, PsiXmlLocator locator) {
            super(ref);
            this.sourceLocator = locator;
        }

        public boolean resolve(PsiXmlIdCache parsedObjects) {
            // have a complex participant, load it
            if (parsedObjects.containsComplexParticipant(this.ref)){
                ModelledEntity object = parsedObjects.getComplexParticipant(this.ref);
                if (object == null){
                    return false;
                }
                // use complex participant
                else {
                    participant = object;
                    return true;
                }
            }
            // have a participant evidence, load the interaction as complex and then set participant
            else if (parsedObjects.containsParticipant(this.ref)){
                Entity object = parsedObjects.getParticipant(this.ref);
                if (object == null){
                    return false;
                }
                // convert participant evidence in a modelled participant and load previous complex
                else {
                    ModelledEntity reloadedObject = parsedObjects.registerModelledParticipantLoadedFrom(object);
                    if (reloadedObject != null){
                        participant = reloadedObject;
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Allosteric molecule effector Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
        }

        @Override
        protected void initialiseParticipantDelegate() {
            XmlModelledParticipant modelled = new XmlModelledParticipant();
            modelled.setId(this.ref);
            setDelegate(modelled);
        }

        public FileSourceLocator getSourceLocator() {
            return this.sourceLocator;
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

        public void setSourceLocator(PsiXmlLocator sourceLocator) {
            this.sourceLocator = sourceLocator;
        }
    }
}
