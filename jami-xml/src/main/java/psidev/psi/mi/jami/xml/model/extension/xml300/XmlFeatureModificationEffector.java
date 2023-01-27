package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlTransient;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.AllostericEffectorType;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.FeatureModificationEffector;
import psidev.psi.mi.jami.model.ModelledEntity;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.reference.AbstractFeatureRef;

/**
 * Xml implementation of feature modification effector
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
@XmlTransient
public class XmlFeatureModificationEffector implements FeatureModificationEffector, FileSourceContext {
    private ModelledFeature feature;
    private PsiXmlLocator sourceLocator;

    /**
     * <p>Constructor for XmlFeatureModificationEffector.</p>
     *
     * @param feature a int.
     * @param locator a {@link PsiXmlLocator} object.
     */
    public XmlFeatureModificationEffector(int feature, PsiXmlLocator locator){
        this.feature = new FeatureEffectorRef(feature, locator);
    }

    /**
     * <p>getFeatureModification.</p>
     *
     * @return a {@link ModelledFeature} object.
     */
    public ModelledFeature getFeatureModification() {
        return feature;
    }

    /**
     * <p>getEffectorType.</p>
     *
     * @return a {@link AllostericEffectorType} object.
     */
    public AllostericEffectorType getEffectorType() {
        return AllostericEffectorType.feature_modification;
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator locator) {
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

    /**
     * <p>Setter for the field <code>sourceLocator</code>.</p>
     *
     * @param locator a {@link PsiXmlLocator} object.
     */
    public void setSourceLocator(PsiXmlLocator locator) {
        this.sourceLocator = locator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Allosteric PTM effector: "+getSourceLocator().toString():super.toString());
    }

    ////////////////////////////////// inner classes
    /**
     * feature ref for allosteric effector
     */
    private class FeatureEffectorRef extends AbstractFeatureRef<ModelledEntity, ModelledFeature> implements ModelledFeature {
        private PsiXmlLocator sourceLocator;

        public FeatureEffectorRef(int ref, PsiXmlLocator locator) {
            super(ref);
            this.sourceLocator = locator;
        }

        public boolean resolve(PsiXmlIdCache parsedObjects) {
            // have a complex feature, load it
            if (parsedObjects.containsComplexFeature(this.ref)){
                ModelledFeature object = parsedObjects.getComplexFeature(this.ref);
                if (object == null){
                    return false;
                }
                // use complex feature
                else {
                    feature = object;
                    return true;
                }
            }
            // have a feature evidence, load the interaction as complex and then set feature
            else if (parsedObjects.containsFeature(this.ref)){
                Feature object = parsedObjects.getFeature(this.ref);
                if (object == null){
                    return false;
                }
                // convert feature evidence in a modelled feature and load previous complex
                else {
                    ModelledFeature reloadedObject = parsedObjects.registerModelledFeatureLoadedFrom(object);
                    if (reloadedObject != null){
                        feature = reloadedObject;
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Allosteric feature effector Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
        }

        @Override
        protected void initialiseFeatureDelegate() {
            XmlModelledFeature modelled = new XmlModelledFeature();
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
