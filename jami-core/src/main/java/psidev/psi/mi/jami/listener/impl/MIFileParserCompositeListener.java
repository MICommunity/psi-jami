package psidev.psi.mi.jami.listener.impl;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.listener.MIFileParserListener;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Participant;

import java.util.ArrayList;
import java.util.List;

/**
 * This class contains several MI file listeners
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/12/13</pre>
 */
public class MIFileParserCompositeListener<T extends MIFileParserListener> implements MIFileParserListener{

    private List<T> delegates;

    /**
     * <p>Constructor for MIFileParserCompositeListener.</p>
     *
     * @param <T> a T object.
     */
    public MIFileParserCompositeListener(){
        this.delegates = new ArrayList<T>();
    }

    /** {@inheritDoc} */
    public void onInvalidSyntax(FileSourceContext context, Exception e) {
        for (T delegate : delegates){
            delegate.onInvalidSyntax(context, e);
        }
    }

    /** {@inheritDoc} */
    public void onSyntaxWarning(FileSourceContext context, String message) {
        for (T delegate : delegates){
            delegate.onSyntaxWarning(context, message);
        }
    }

    /** {@inheritDoc} */
    public void onMissingCvTermName(CvTerm term, FileSourceContext context, String message) {
        for (T delegate : delegates){
            delegate.onMissingCvTermName(term, context, message);
        }
    }

    /** {@inheritDoc} */
    public void onMissingInteractorName(Interactor interactor, FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onMissingInteractorName(interactor, context);
        }
    }

    /** {@inheritDoc} */
    public void onParticipantWithoutInteractor(Participant participant, FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onParticipantWithoutInteractor(participant, context);
        }
    }

    /** {@inheritDoc} */
    public void onInteractionWithoutParticipants(Interaction interaction, FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onInteractionWithoutParticipants(interaction, context);
        }
    }

    /** {@inheritDoc} */
    public void onInvalidOrganismTaxid(String taxid, FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onInvalidOrganismTaxid(taxid, context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingParameterValue(FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onMissingParameterValue(context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingParameterType(FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onMissingParameterType(context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingConfidenceValue(FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onMissingConfidenceValue(context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingConfidenceType(FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onMissingConfidenceType(context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingChecksumValue(FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onMissingChecksumValue(context);
        }
    }

    /** {@inheritDoc} */
    public void onMissingChecksumMethod(FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onMissingChecksumMethod(context);
        }
    }

    /** {@inheritDoc} */
    public void onInvalidPosition(String message, FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onInvalidPosition(message, context);
        }
    }

    /** {@inheritDoc} */
    public void onInvalidRange(String message, FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onInvalidRange(message, context);
        }
    }

    /** {@inheritDoc} */
    public void onInvalidStoichiometry(String message, FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onInvalidStoichiometry(message, context);
        }
    }

    /** {@inheritDoc} */
    public void onXrefWithoutDatabase(FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onXrefWithoutDatabase(context);
        }
    }

    /** {@inheritDoc} */
    public void onXrefWithoutId(FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onXrefWithoutId(context);
        }
    }

    /** {@inheritDoc} */
    public void onAnnotationWithoutTopic(FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onAnnotationWithoutTopic(context);
        }
    }

    /** {@inheritDoc} */
    public void onAliasWithoutName(FileSourceContext context) {
        for (T delegate : delegates){
            delegate.onAliasWithoutName(context);
        }
    }

    /**
     * <p>Getter for the field <code>delegates</code>.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<T> getDelegates() {
        return delegates;
    }
}
