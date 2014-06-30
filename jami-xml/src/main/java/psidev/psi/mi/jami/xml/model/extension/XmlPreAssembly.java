package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.impl.DefaultPreassemby;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.reference.AbstractComplexRef;

/**
 * Xml implementation of preassembly
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */

public class XmlPreAssembly extends DefaultPreassemby implements FileSourceContext {
    private PsiXmlLocator sourceLocator;

    public XmlPreAssembly(CvTerm outcome) {
        super(outcome);
    }

    public XmlPreAssembly(CvTerm outcome, CvTerm response) {
        super(outcome, response);
    }

    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    public void setSourceLocator(FileSourceLocator locator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else{
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getColumnNumber(), null);
        }
    }

    public void setSourceLocator(PsiXmlLocator locator) {
        this.sourceLocator = locator;
    }

    @Override
    public String toString() {
        return "Pre-assembly: "+sourceLocator != null ? sourceLocator.toString():super.toString();
    }

    public void addAffectedInteractionRef(int affectedInteraction, PsiXmlLocator locator){
        getAffectedInteractions().add(new ModelledInteractionRef(affectedInteraction, locator));
    }

    /**
     * interaction ref for affected cooperative interaction
     */
    private class ModelledInteractionRef extends AbstractComplexRef {
        private PsiXmlLocator sourceLocator;

        public ModelledInteractionRef(int ref, PsiXmlLocator locator) {
            super(ref);
            this.sourceLocator = locator;
        }

        public boolean resolve(PsiXmlIdCache parsedObjects) {
            if (parsedObjects.containsComplex(this.ref)){
                Complex c = parsedObjects.getComplex(this.ref);
                if (c != null){
                    getAffectedInteractions().remove(this);
                    getAffectedInteractions().add(c);
                    return true;
                }
            }
            if (parsedObjects.containsInteraction(this.ref)){
                Interaction object = parsedObjects.getInteraction(this.ref);
                if (object == null){
                   return false;
                }
                // convert interaction evidence in a complex
                else if (object instanceof ExtendedPsiXmlInteractionEvidence){
                    ModelledInteraction interaction = new XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)object);
                    getAffectedInteractions().remove(this);
                    getAffectedInteractions().add(interaction);
                    return true;
                }
                // wrap modelled interaction
                else if (object instanceof ModelledInteraction){
                    getAffectedInteractions().remove(this);
                    getAffectedInteractions().add((ModelledInteraction)object);
                    return true;
                }
                // wrap basic interaction
                else if (object instanceof ExtendedPsiXmlInteraction){
                    ModelledInteraction interaction = new XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)object);
                    getAffectedInteractions().remove(this);
                    getAffectedInteractions().add(interaction);
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Affected modelled interaction Reference: "+ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString());
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
