package psidev.psi.mi.jami.xml.model.extension.xml253;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeature;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeatureEvidence;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipantEvidence;
import psidev.psi.mi.jami.xml.model.reference.AbstractFeatureRef;

import javax.xml.bind.annotation.XmlTransient;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Xml wrapper for feature evidences.
 *
 * The new linked features added to this wrapper are NOT added to the wrapped feature evidence
 * because they are incompatibles.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/10/13</pre>
 */
@XmlTransient
public class XmlFeatureEvidenceWrapper implements ModelledFeature, ExtendedPsiXmlFeature<ModelledEntity,ModelledFeature>, FileSourceContext{
    private ExtendedPsiXmlFeatureEvidence feature;
    private ModelledEntity parent;
    private Collection<ModelledFeature> linkedFeatures;

    /**
     * <p>Constructor for XmlFeatureEvidenceWrapper.</p>
     *
     * @param part a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeatureEvidence} object.
     * @param wrapper a {@link psidev.psi.mi.jami.model.ModelledEntity} object.
     */
    public XmlFeatureEvidenceWrapper(ExtendedPsiXmlFeatureEvidence part, ModelledEntity wrapper){
        if (part == null){
            throw new IllegalArgumentException("A feature evidence wrapper needs a non null feature");
        }
        this.feature = part;
        this.parent = wrapper;
        // register feature as complex feature
        XmlEntryContext.getInstance().registerComplexFeature(this.feature.getId(), this);
    }

    /** {@inheritDoc} */
    @Override
    public String getShortName() {
        return this.feature.getShortName();
    }

    /** {@inheritDoc} */
    @Override
    public void setShortName(String name) {
        this.feature.setShortName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getFullName() {
        return this.feature.getFullName();
    }

    /** {@inheritDoc} */
    @Override
    public void setFullName(String name) {
        this.feature.setFullName(name);
    }

    /** {@inheritDoc} */
    @Override
    public String getInterpro() {
        return this.feature.getInterpro();
    }

    /** {@inheritDoc} */
    @Override
    public void setInterpro(String interpro) {
        this.feature.setInterpro(interpro);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getIdentifiers() {
        return this.feature.getIdentifiers();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Xref> getXrefs() {
        return this.feature.getXrefs();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Annotation> getAnnotations() {
        return this.feature.getAnnotations();
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getType() {
        return this.feature.getType();
    }

    /** {@inheritDoc} */
    @Override
    public void setType(CvTerm type) {
        this.feature.setType(type);
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Range> getRanges() {
        return this.feature.getRanges();
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm getRole() {
        return this.feature.getRole();
    }

    /** {@inheritDoc} */
    @Override
    public void setRole(CvTerm effect) {
        this.feature.setRole(effect);
    }

    /** {@inheritDoc} */
    @Override
    public ModelledEntity getParticipant() {
        if (this.parent == null && this.feature.getParticipant()instanceof ExtendedPsiXmlParticipantEvidence){
            this.parent = new XmlParticipantEvidenceWrapper((ExtendedPsiXmlParticipantEvidence)this.feature.getParticipant(), null);
        }
        return this.parent;
    }

    /** {@inheritDoc} */
    @Override
    public void setParticipant(ModelledEntity participant) {
        this.parent = participant;
    }

    /** {@inheritDoc} */
    @Override
    public void setParticipantAndAddFeature(ModelledEntity participant) {
        if (this.parent != null){
            this.parent.removeFeature(this);
        }

        if (participant != null){
            participant.addFeature(this);
        }
    }

    /** {@inheritDoc} */
    @Override
    public Collection<Alias> getAliases() {
        return this.feature.getAliases();
    }

    /** {@inheritDoc} */
    @Override
    public Collection<ModelledFeature> getLinkedFeatures() {
        if (this.linkedFeatures == null){
            initialiseLinkedFeatures();
        }
        return this.linkedFeatures;
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        return ((FileSourceContext)this.feature).getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    public void setSourceLocator(FileSourceLocator locator) {
        ((FileSourceContext)this.feature).setSourceLocator(locator);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return this.feature.toString();
    }

    /**
     * <p>getWrappedFeature.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeatureEvidence} object.
     */
    public ExtendedPsiXmlFeatureEvidence getWrappedFeature(){
        return this.feature;
    }

    /**
     * <p>initialiseLinkedFeatures.</p>
     */
    protected void initialiseLinkedFeatures(){
        this.linkedFeatures = new ArrayList<ModelledFeature>(this.feature.getLinkedFeatures().size());
        for (FeatureEvidence feature : this.feature.getLinkedFeatures()){
            ExtendedPsiXmlFeature extendedFeature = (ExtendedPsiXmlFeature)feature;
            this.linkedFeatures.add(new FeatureRef(extendedFeature.getId()));
        }
    }

    /** {@inheritDoc} */
    @Override
    public int getId() {
        return this.feature.getId();
    }

    /** {@inheritDoc} */
    @Override
    public void setId(int id) {
        this.feature.setId(id);
        // register feature as complex feature
        XmlEntryContext.getInstance().registerComplexFeature(id, this);
    }

    private class FeatureRef extends AbstractFeatureRef<ModelledEntity, ModelledFeature> implements ModelledFeature{
        public FeatureRef(int ref) {
            super(ref);
        }

        public boolean resolve(PsiXmlIdCache parsedObjects) {
            if (parsedObjects.containsComplexFeature(this.ref)){
                ModelledFeature f = parsedObjects.getComplexFeature(this.ref);
                if (f != null){
                    linkedFeatures.remove(this);
                    linkedFeatures.add(f);
                    return true;
                }
            }
            else if (parsedObjects.containsFeature(this.ref)){
                Feature f = parsedObjects.getFeature(this.ref);
                if (f != null){
                    ModelledFeature reloadedObject = parsedObjects.registerModelledFeatureLoadedFrom(f);
                    if (reloadedObject != null){
                        linkedFeatures.remove(this);
                        linkedFeatures.add(reloadedObject);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Feature Reference in inferred participant: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
        }

        @Override
        protected void initialiseFeatureDelegate() {
            XmlModelledFeature modelled = new XmlModelledFeature();
            modelled.setId(this.ref);
            setDelegate(modelled);
        }

        public FileSourceLocator getSourceLocator() {
            return XmlFeatureEvidenceWrapper.this.getSourceLocator();
        }

        public void setSourceLocator(FileSourceLocator locator) {
            throw new UnsupportedOperationException("Cannot set the source locator of a feature ref");
        }
    }
}
