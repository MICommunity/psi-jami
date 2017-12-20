package psidev.psi.mi.jami.xml;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.cache.PsiXmlIdCache;
import psidev.psi.mi.jami.xml.exception.PsiXmlParserException;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;
import psidev.psi.mi.jami.xml.model.Entry;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;
import psidev.psi.mi.jami.xml.model.extension.InferredInteraction;
import psidev.psi.mi.jami.xml.model.extension.InferredInteractionParticipant;
import psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory;
import psidev.psi.mi.jami.xml.model.extension.xml300.BindingFeatures;
import psidev.psi.mi.jami.xml.model.reference.XmlIdReference;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * The xml entry context is a context threadlocal that will be valid for the whole xml entry
 * but will be cleared after each entry in the entrySet
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>26/07/13</pre>
 */
public class XmlEntryContext {

    private PsiXmlIdCache elementCache;
    private Collection<XmlIdReference> references;
    private Collection<InferredInteraction> inferredInteractions;
    private Collection<BindingFeatures> bindingFeatures;
    private Entry currentEntry;
    private PsiXmlParserListener listener;
    private XmlInteractorFactory interactorFactory;

    private XmlEntryContext(){
    }

    /**
     * <p>Getter for the field <code>instance</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.XmlEntryContext} object.
     */
    public static XmlEntryContext getInstance() {
        return instance.get();
    }

    private static ThreadLocal<XmlEntryContext> instance = new ThreadLocal<XmlEntryContext>() {
        @Override
        protected XmlEntryContext initialValue() {
            final XmlEntryContext context = new XmlEntryContext();
            return context;
        }
    };

    /**
     * <p>remove.</p>
     */
    public static void remove(){
        instance.remove();
    }

    /**
     * <p>clear.</p>
     */
    public void clear(){
        if (this.elementCache != null){
            this.elementCache.clear();
        }
        if (this.references != null){
            this.references.clear();
        }
        this.currentEntry = null;
        if (this.inferredInteractions != null){
            this.inferredInteractions.clear();
        }
        if (this.bindingFeatures != null){
            this.bindingFeatures.clear();
        }
        this.interactorFactory = null;
    }

    /**
     * <p>Getter for the field <code>interactorFactory</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory} object.
     */
    public XmlInteractorFactory getInteractorFactory() {
        if (this.interactorFactory == null){
            this.interactorFactory = new XmlInteractorFactory();
        }
        return interactorFactory;
    }

    /**
     * <p>Setter for the field <code>interactorFactory</code>.</p>
     *
     * @param interactorFactory a {@link psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory} object.
     */
    public void setInteractorFactory(XmlInteractorFactory interactorFactory) {
        this.interactorFactory = interactorFactory;
    }

    /**
     * <p>Getter for the field <code>currentEntry</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.Entry} object.
     */
    public Entry getCurrentEntry() {
        return currentEntry;
    }

    /**
     * <p>setCurrentSource.</p>
     *
     * @param entry a {@link psidev.psi.mi.jami.xml.model.Entry} object.
     */
    public void setCurrentSource(Entry entry){
        this.currentEntry = entry;
    }

    /**
     * <p>Getter for the field <code>listener</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.listener.PsiXmlParserListener} object.
     */
    public PsiXmlParserListener getListener() {
        return listener;
    }

    /**
     * <p>Setter for the field <code>listener</code>.</p>
     *
     * @param listener a {@link psidev.psi.mi.jami.xml.listener.PsiXmlParserListener} object.
     */
    public void setListener(PsiXmlParserListener listener) {
        this.listener = listener;
    }

    /**
     * <p>Setter for the field <code>elementCache</code>.</p>
     *
     * @param elementCache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlIdCache} object.
     */
    public void setElementCache(PsiXmlIdCache elementCache) {
        this.elementCache = elementCache;
    }

    /**
     * <p>registerAvailability.</p>
     *
     * @param id a int.
     * @param o a {@link psidev.psi.mi.jami.xml.model.extension.AbstractAvailability} object.
     */
    public void registerAvailability(int id, AbstractAvailability o){
        if (this.elementCache != null){
            this.elementCache.registerAvailability(id, o);
        }
    }

    /**
     * <p>registerExperiment.</p>
     *
     * @param id a int.
     * @param o a {@link psidev.psi.mi.jami.model.Experiment} object.
     */
    public void registerExperiment(int id, Experiment o){
        if (this.elementCache != null){
            this.elementCache.registerExperiment(id, o);
        }
    }

    /**
     * <p>registerInteractor.</p>
     *
     * @param id a int.
     * @param o a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public void registerInteractor(int id, Interactor o){
        if (this.elementCache != null){
            this.elementCache.registerInteractor(id, o);
        }
    }

    /**
     * <p>registerInteraction.</p>
     *
     * @param id a int.
     * @param o a {@link psidev.psi.mi.jami.model.Interaction} object.
     */
    public void registerInteraction(int id, Interaction o){
        if (this.elementCache != null){
            this.elementCache.registerInteraction(id, o);
        }
    }

    /**
     * <p>registerParticipant.</p>
     *
     * @param id a int.
     * @param o a {@link psidev.psi.mi.jami.model.Entity} object.
     */
    public void registerParticipant(int id, Entity o){
        if (this.elementCache != null){
            this.elementCache.registerParticipant(id, o);
        }
    }

    /**
     * <p>registerFeature.</p>
     *
     * @param id a int.
     * @param o a {@link psidev.psi.mi.jami.model.Feature} object.
     */
    public void registerFeature(int id, Feature o){
        if (this.elementCache != null){
            this.elementCache.registerFeature(id, o);
        }
    }

    /**
     * <p>registerComplexParticipant.</p>
     *
     * @param id a int.
     * @param o a {@link psidev.psi.mi.jami.model.ModelledEntity} object.
     */
    public void registerComplexParticipant(int id, ModelledEntity o){
        if (this.elementCache != null){
            this.elementCache.registerComplexParticipant(id, o);
        }
    }

    /**
     * <p>registerComplexFeature.</p>
     *
     * @param id a int.
     * @param o a {@link psidev.psi.mi.jami.model.ModelledFeature} object.
     */
    public void registerComplexFeature(int id, ModelledFeature o){
        if (this.elementCache != null){
            this.elementCache.registerComplexFeature(id, o);
        }
    }

    /**
     * <p>registerVariableParameterValue.</p>
     *
     * @param id a int.
     * @param o a {@link psidev.psi.mi.jami.model.VariableParameterValue} object.
     */
    public void registerVariableParameterValue(int id, VariableParameterValue o){
        if (this.elementCache != null){
            this.elementCache.registerVariableParameterValue(id, o);
        }
    }

    /**
     * <p>registerComplex.</p>
     *
     * @param id a int.
     * @param o a {@link psidev.psi.mi.jami.model.Complex} object.
     */
    public void registerComplex(int id, Complex o){
        if (this.elementCache != null){
            this.elementCache.registerComplex(id, o);
        }
    }

    /**
     * <p>registerInferredInteraction.</p>
     *
     * @param infer a {@link psidev.psi.mi.jami.xml.model.extension.InferredInteraction} object.
     */
    public void registerInferredInteraction(InferredInteraction infer){
        if (this.inferredInteractions != null){
            this.inferredInteractions.add(infer);
        }
    }

    /**
     * <p>registerBindingFeature.</p>
     *
     * @param infer a {@link psidev.psi.mi.jami.xml.model.extension.xml300.BindingFeatures} object.
     */
    public void registerBindingFeature(BindingFeatures infer){
        if (this.bindingFeatures != null){
            this.bindingFeatures.add(infer);
        }
    }

    /**
     * <p>registerReference.</p>
     *
     * @param ref a {@link psidev.psi.mi.jami.xml.model.reference.XmlIdReference} object.
     */
    public void registerReference(XmlIdReference ref){
        if (this.references != null){
            this.references.add(ref);
        }
    }

    /**
     * <p>hasInferredInteractions.</p>
     *
     * @return a boolean.
     */
    public boolean hasInferredInteractions(){
        return !inferredInteractions.isEmpty() || !bindingFeatures.isEmpty();
    }

    /**
     * <p>hasUnresolvedReferences.</p>
     *
     * @return a boolean.
     */
    public boolean hasUnresolvedReferences(){
        return !references.isEmpty();
    }

    /**
     * <p>initialiseInferredInteractionList.</p>
     */
    public void initialiseInferredInteractionList(){
        this.inferredInteractions = new ArrayList<InferredInteraction>();
        this.bindingFeatures = new ArrayList<BindingFeatures>();
    }

    /**
     * <p>initialiseReferencesList.</p>
     */
    public void initialiseReferencesList(){
        this.references = new ArrayList<XmlIdReference>();
    }

    /**
     * <p>resolveInteractorAndExperimentRefs.</p>
     */
    public void resolveInteractorAndExperimentRefs(){
        if (references != null){
            Iterator<XmlIdReference> refIterator = references.iterator();
            while(refIterator.hasNext()){
                XmlIdReference ref = refIterator.next();
                if (this.elementCache == null ||
                        (this.elementCache != null && !ref.resolve(this.elementCache))){
                    if (listener != null){
                        listener.onUnresolvedReference(ref, "Cannot resolve a reference in the xml file");
                    }
                }
                else{
                    refIterator.remove();
                }
            }
        }
    }

    /**
     * <p>resolveInferredInteractionRefs.</p>
     */
    public void resolveInferredInteractionRefs(){
        if (inferredInteractions != null){
            Iterator<InferredInteraction> inferredIterator = inferredInteractions.iterator();
            while(inferredIterator.hasNext()){
                InferredInteraction inferred = inferredIterator.next();
                boolean toRemove = true;

                if (!inferred.getParticipants().isEmpty()){
                    Iterator<InferredInteractionParticipant> partIterator = inferred.getParticipants().iterator();
                    List<InferredInteractionParticipant> partIterator2 = new ArrayList<InferredInteractionParticipant>(inferred.getParticipants());
                    int currentIndex = 0;

                    while (partIterator.hasNext()){
                        currentIndex++;
                        InferredInteractionParticipant p1 = partIterator.next();
                        for (int i = currentIndex; i < partIterator2.size();i++){
                            InferredInteractionParticipant p2 = partIterator2.get(i);

                            if (p1.getFeature() != null && p2.getFeature() != null
                                    && !(p1.getFeature() instanceof XmlIdReference)
                                    && !(p2.getFeature() instanceof XmlIdReference)){
                                p1.getFeature().getLinkedFeatures().add(p2.getFeature());
                                if (p1.getFeature() != p2.getFeature()){
                                    p2.getFeature().getLinkedFeatures().add(p1.getFeature());
                                }
                            }
                            // cannot remove this inferred interaction because of unresolved dependencies
                            else{
                                toRemove = false;
                            }
                        }
                    }
                }
                else{
                    if (listener != null){
                        listener.onInvalidSyntax(inferred, new PsiXmlParserException("InferredInteraction must have at least one inferredInteractionParticipant."));
                    }
                }

                if (toRemove){
                    inferredIterator.remove();
                }
            }
        }

        if (bindingFeatures != null){
            Iterator<BindingFeatures> inferredIterator = bindingFeatures.iterator();
            while(inferredIterator.hasNext()){
                BindingFeatures inferred = inferredIterator.next();
                boolean toRemove = true;
                if (!inferred.getLinkedFeatures().isEmpty()){
                    Iterator<ModelledFeature> partIterator = inferred.getLinkedFeatures().iterator();
                    List<ModelledFeature> partIterator2 = new ArrayList<ModelledFeature>(inferred.getLinkedFeatures());
                    int currentIndex = 0;

                    while (partIterator.hasNext()){
                        currentIndex++;
                        ModelledFeature p1 = partIterator.next();
                        if (!(p1 instanceof XmlIdReference)){
                            for (int i = currentIndex; i < partIterator2.size();i++){
                                ModelledFeature p2 = partIterator2.get(i);
                                if (!(p2 instanceof XmlIdReference)){
                                    p1.getLinkedFeatures().add(p2);
                                    if (p1 != p2){
                                        p2.getLinkedFeatures().add(p1);
                                    }
                                }
                                else{
                                    toRemove = false;
                                }
                            }
                        }
                        // cannot remove this inferred interaction because of unresolved dependencies
                        else {
                            toRemove = false;
                        }
                    }
                }
                else{
                    if (listener != null){
                        listener.onInvalidSyntax(inferred, new PsiXmlParserException("BindingFeatures must have at least one participantFeatureRef."));
                    }
                }

                if (toRemove){
                    inferredIterator.remove();
                }
            }
        }
    }
}
