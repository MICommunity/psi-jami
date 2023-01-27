package psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253;

import psidev.psi.mi.jami.model.Complex;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml25.xml253.AbstractXmlModelledInteractionWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.XmlModelledParticipantWriter;
import psidev.psi.mi.jami.xml.model.extension.xml253.BibRef;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlCvTerm;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlExperiment;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlXref;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Compact XML 2.5 writer for a biological complex (ignore experimental details).
 * It will write cooperative effects as attributes
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
public class XmlComplexWriter extends AbstractXmlModelledInteractionWriter<Complex> implements CompactPsiXmlElementWriter<Complex> {

    /**
     * <p>Constructor for XmlComplexWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlComplexWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWriter() {
        super.setParticipantWriter(new XmlModelledParticipantWriter(getStreamWriter(), getObjectIndex()));
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
        DefaultXmlExperiment exp = new DefaultXmlExperiment(new BibRef("Mock publication for biological complexes."));
        exp.setHostOrganism(interaction.getOrganism());
        exp.setInteractionDetectionMethod(new XmlCvTerm(Experiment.INFERRED_BY_CURATOR,
                new XmlXref(CvTermUtils.createPsiMiDatabase(), Experiment.INFERRED_BY_CURATOR_MI, CvTermUtils.createIdentityQualifier())));
        exp.setParticipantIdentificationMethod(new XmlCvTerm(Participant.PREDETERMINED,
                new XmlXref(CvTermUtils.createPsiMiDatabase(), Participant.PREDETERMINED_MI, CvTermUtils.createIdentityQualifier())));
        return exp;
    }
}
