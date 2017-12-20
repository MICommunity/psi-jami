package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.*;

import java.util.List;

/**
 * Extended experiment for PSI-XML 2,5 standards
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>30/10/13</pre>
 */
public interface ExtendedPsiXmlExperiment extends NamedExperiment {
    /**
     * <p>getId.</p>
     *
     * @return a int.
     */
    public int getId();
    /**
     * <p>setId.</p>
     *
     * @param id a int.
     */
    public void setId(int id);
    /**
     * <p>getHostOrganisms.</p>
     *
     * @return a {@link java.util.List} object.
     */
    public List<Organism> getHostOrganisms();
    /**
     * <p>getParticipantIdentificationMethod.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getParticipantIdentificationMethod();
    /**
     * <p>setParticipantIdentificationMethod.</p>
     *
     * @param method a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public void setParticipantIdentificationMethod(CvTerm method);
    /**
     * <p>getFeatureDetectionMethod.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public CvTerm getFeatureDetectionMethod();
    /**
     * <p>setFeatureDetectionMethod.</p>
     *
     * @param method a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public void setFeatureDetectionMethod(CvTerm method);
}
