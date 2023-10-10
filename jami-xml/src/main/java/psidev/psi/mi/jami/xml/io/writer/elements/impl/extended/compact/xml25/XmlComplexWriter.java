package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.AbstractXmlModelledInteractionWriter;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlExperiment;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Arrays;
import java.util.List;

/**
 * Compact XML 2.5 writer for an expanded biological complex (ignore experimental details).
 * It will write cooperative effects as attributes.
 * It will write intra-molecular property, names, interaction types and experiments
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
public class XmlComplexWriter extends AbstractXmlModelledInteractionWriter<Complex>
        implements CompactPsiXmlElementWriter<Complex> {

    /**
     * <p>Constructor for XmlComplexWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlComplexWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWriter() {
        super.setParticipantWriter(new XmlModelledParticipantWriter(getVersion(), getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(Complex object) throws XMLStreamException {
        super.setDefaultExperiment(extractDefaultExperimentFrom(object));
        return writeExperimentRef();
    }

    /** {@inheritDoc} */
    @Override
    public Experiment extractDefaultExperimentFrom(Complex interaction) {
        ExtendedPsiXmlExperiment exp = newExperiment(newPublication("Mock publication for biological complexes."));
        exp.setHostOrganism(interaction.getOrganism());
        exp.setInteractionDetectionMethod(newXmlCvTerm(
                Experiment.INFERRED_BY_CURATOR, CvTermUtils.createPsiMiDatabase(), Experiment.INFERRED_BY_CURATOR_MI, CvTermUtils.createIdentityQualifier()));
        exp.setParticipantIdentificationMethod(newXmlCvTerm(
                Participant.PREDETERMINED, CvTermUtils.createPsiMiDatabase(), Participant.PREDETERMINED_MI, CvTermUtils.createIdentityQualifier()));
        return exp;
    }

    /** {@inheritDoc} */
    @Override
    public List<Experiment> extractDefaultExperimentsFrom(Complex interaction) {
        return Arrays.asList(extractDefaultExperimentFrom(interaction));
    }
}
