package psidev.psi.mi.jami.listener;

import psidev.psi.mi.jami.model.*;

/**
 * Listener which listen to changes in feature evidence
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 19/07/13
 * @version $Id: $
 */
public interface FeatureEvidenceChangeListener extends FeatureChangeListener<FeatureEvidence>,ParametersChangeListener<FeatureEvidence> {

    /**
     * <p>onAddedDetectionMethod</p>
     *
     * @param feature : updated feature
     * @param added : added detection method
     */
    public void onAddedDetectionMethod(FeatureEvidence feature, CvTerm added);

    /**
     * <p>onRemovedDetectionMethod</p>
     *
     * @param feature : updated feature
     * @param removed : removed detection method
     */
    public void onRemovedDetectionMethod(FeatureEvidence feature, CvTerm removed);
}
