package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.ModelledFeature;
import psidev.psi.mi.jami.model.ModelledParticipant;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * abstract writer for a modelled participant. (ignore all experimental details)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public abstract class AbstractXmlModelledParticipantWriter extends AbstractXmlParticipantWriter<ModelledParticipant, ModelledFeature>{
    /**
     * <p>Constructor for AbstractXmlModelledParticipantWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlModelledParticipantWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeMolecule(Interactor interactor) throws XMLStreamException {
        super.writeMoleculeRef(interactor);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalPreparations(ModelledParticipant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalRoles(ModelledParticipant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParticipantIdentificationMethods(ModelledParticipant object, CvTerm method) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalInteractor(ModelledParticipant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeHostOrganisms(ModelledParticipant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeConfidences(ModelledParticipant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(ModelledParticipant object) {
        // nothing to do
    }
}
