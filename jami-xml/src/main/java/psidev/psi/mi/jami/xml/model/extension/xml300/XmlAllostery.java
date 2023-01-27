package psidev.psi.mi.jami.xml.model.extension.xml300;

import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.reference.xml300.AbstractEntityRef;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * XML 3.0 implementation of Allostery
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class XmlAllostery extends AbstractXmlCooperativeEffect implements Allostery<AllostericEffector> {
    private PsiXmlLocator sourceLocator;
    private CvTerm allostericMechanism;
    private CvTerm allosteryType;
    private ModelledEntity allostericMolecule;
    private MoleculeEffector moleculeEffector;
    private FeatureModificationEffector featureEffector;

    /**
     * <p>Constructor for XmlAllostery.</p>
     */
    public XmlAllostery() {
        super();
    }

    /**
     * <p>Constructor for XmlAllostery.</p>
     *
     * @param outcome a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlAllostery(CvTerm outcome) {
        super(outcome);
    }

    /**
     * <p>Constructor for XmlAllostery.</p>
     *
     * @param outcome a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param response a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlAllostery(CvTerm outcome, CvTerm response) {
        super(outcome, response);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Allostery: "+sourceLocator != null ? sourceLocator.toString():super.toString();
    }

    /**
     * <p>Getter for the field <code>allostericMechanism</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getAllostericMechanism() {
        return this.allostericMechanism;
    }

    /** {@inheritDoc} */
    public void setAllostericMechanism(CvTerm mechanism) {
        this.allostericMechanism = mechanism;
    }

    /**
     * <p>Getter for the field <code>allosteryType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getAllosteryType() {
        return this.allosteryType;
    }

    /** {@inheritDoc} */
    public void setAllosteryType(CvTerm type) {
        this.allosteryType = type;
    }

    /**
     * <p>Getter for the field <code>allostericMolecule</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ModelledEntity} object.
     */
    public ModelledEntity getAllostericMolecule() {
        if (this.allostericMolecule == null){
            this.allostericMolecule = new XmlModelledParticipant();
        }
        return this.allostericMolecule;
    }

    /** {@inheritDoc} */
    public void setAllostericMolecule(ModelledEntity participant) {
        if (participant == null){
            throw new IllegalArgumentException("The allosteric molecule cannot be null");
        }
        this.allostericMolecule = participant;
    }

    /**
     * <p>getAllostericEffector.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.AllostericEffector} object.
     */
    public AllostericEffector getAllostericEffector() {
        if (this.moleculeEffector == null && this.featureEffector == null){
            initialiseDefaultAllostericEffector();
        }
        if (this.moleculeEffector != null){
            return this.moleculeEffector;
        }
        else {
            return this.featureEffector;
        }
    }

    /**
     * <p>initialiseDefaultAllostericEffector.</p>
     */
    protected void initialiseDefaultAllostericEffector(){
        setJAXBMoleculeEffectorRef(0);
    }

    /** {@inheritDoc} */
    public void setAllostericEffector(AllostericEffector effector) {
        if (effector == null){
            throw new IllegalArgumentException("The allosteric effector cannot be null");
        }

        switch (effector.getEffectorType()){
            case molecule:
                this.moleculeEffector = (MoleculeEffector)effector;
                break;
            case feature_modification:
                this.featureEffector = (FeatureModificationEffector)effector;
                break;
            default:
                throw new IllegalArgumentException("The allosteric effector can only be a modelled participant (molecule) or a modelled feature (feature_modification)");
        }
    }

    /**
     * <p>setJAXBAllostericMoleculeRef.</p>
     *
     * @param ref a int.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "allostericMoleculeRef", required = true)
    public void setJAXBAllostericMoleculeRef(int ref) {
        this.allostericMolecule = new AllostericMoleculeRef(ref);
    }

    /**
     * <p>setJAXBMoleculeEffectorRef.</p>
     *
     * @param effector a int.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "allostericEffectorRef", required = true)
    public void setJAXBMoleculeEffectorRef(int effector) {
        this.moleculeEffector = new XmlMoleculeEffector(effector, (PsiXmlLocator)sourceLocation());
    }

    /**
     * <p>setJAXBFeatureEffectorRef.</p>
     *
     * @param effector a int.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "allostericModificationRef", required = true)
    public void setJAXBFeatureEffectorRef(int effector) {
        this.featureEffector = new XmlFeatureModificationEffector(effector, (PsiXmlLocator)sourceLocation());
    }

    /**
     * <p>setJAXBAllostericMechanism.</p>
     *
     * @param mechanism a {@link XmlCvTerm} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "allostericMechanism")
    public void setJAXBAllostericMechanism(XmlCvTerm mechanism) {
        this.allostericMechanism = mechanism;
    }

    /**
     * <p>setJAXBAllosteryType.</p>
     *
     * @param type a {@link XmlCvTerm} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "allosteryType")
    public void setJAXBAllosteryType(XmlCvTerm type) {
        this.allosteryType = type;
    }

    ////////////////////////////////// inner classes

    /**
     * participant ref for allosteric molecule
     */
    private class AllostericMoleculeRef extends AbstractEntityRef<ModelledFeature> implements ModelledEntity{
        private PsiXmlLocator sourceLocator;

        public AllostericMoleculeRef(int ref) {
            super(ref);
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
                    setAllostericMolecule(object);
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
                        setAllostericMolecule(reloadedObject);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Allosteric molecule Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
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
