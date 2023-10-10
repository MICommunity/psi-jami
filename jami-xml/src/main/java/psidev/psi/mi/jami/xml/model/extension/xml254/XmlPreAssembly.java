package psidev.psi.mi.jami.xml.model.extension.xml254;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.ModelledInteraction;
import psidev.psi.mi.jami.model.impl.DefaultPreassemby;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.reference.xml254.AbstractComplexRef;

/**
 * Xml implementation of preassembly
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
public class XmlPreAssembly extends DefaultPreassemby implements FileSourceContext {
    private PsiXmlLocator sourceLocator;

    /**
     * <p>Constructor for XmlPreAssembly.</p>
     *
     * @param outcome a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlPreAssembly(CvTerm outcome) {
        super(outcome);
    }

    /**
     * <p>Constructor for XmlPreAssembly.</p>
     *
     * @param outcome a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param response a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlPreAssembly(CvTerm outcome, CvTerm response) {
        super(outcome, response);
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
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else{
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getColumnNumber(), null);
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
        return (getSourceLocator() != null ? "Pre-assembly: "+getSourceLocator().toString():super.toString());
    }

    /**
     * <p>addAffectedInteractionRef.</p>
     *
     * @param affectedInteraction a int.
     * @param locator a {@link psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator} object.
     */
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
            else if (parsedObjects.containsInteraction(this.ref)){
                Interaction object = parsedObjects.getInteraction(this.ref);
                if (object != null){
                    ModelledInteraction reloadedComplex = parsedObjects.registerComplexLoadedFrom(object);
                    if (reloadedComplex != null){
                        getAffectedInteractions().remove(this);
                        getAffectedInteractions().add(reloadedComplex);
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "Affected modelled interaction Reference: "+(ref+(getSourceLocator() != null ? ", "+getSourceLocator().toString():super.toString()));
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
