package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml25;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlCvTermWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlDbXrefWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlInteractorWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlFeatureWriter;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Xml 25 writer for participant
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public abstract class AbstractXmlParticipantWriter
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlParticipantWriter<Participant, Feature> {

    /**
     * <p>Constructor for AbstractXmlParticipantWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public AbstractXmlParticipantWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParticipantPool(ParticipantPool pool) throws XMLStreamException {
        writeMolecule(pool.getInteractor());
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter() {
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseFeatureWriter() {
        super.setFeatureWriter(new XmlFeatureWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseBiologicalRoleWriter() {
        super.setBiologicalRoleWriter(new XmlCvTermWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseInteractorWriter() {
        super.setInteractorWriter(new XmlInteractorWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    protected void writeStoichiometry(Participant object){
        // nothing to write
    }

    /** {@inheritDoc} */
    protected void writeOtherAttributes(Participant object, boolean writeAttributeList) throws XMLStreamException {
        if (object.getStoichiometry() != null){
            if (writeAttributeList){
                // write start attribute list
                getStreamWriter().writeStartElement("attributeList");
            }
            writeStoichiometryAttribute(object.getStoichiometry());
            if (writeAttributeList){
                // write end attribute list
                getStreamWriter().writeEndElement();
            }
        }
    }

    /**
     * <p>writeStoichiometryAttribute.</p>
     *
     * @param stc a {@link psidev.psi.mi.jami.model.Stoichiometry} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeStoichiometryAttribute(Stoichiometry stc) throws XMLStreamException {
        // write stoichiometry

        // write start
        getStreamWriter().writeStartElement("attribute");
        // write topic
        getStreamWriter().writeAttribute("name", Annotation.COMMENT);
        getStreamWriter().writeAttribute("nameAc", Annotation.COMMENT_MI);
        // write description
        getStreamWriter().writeCharacters(PsiXmlUtils.STOICHIOMETRY_PREFIX);
        getStreamWriter().writeCharacters(Long.toString(stc.getMinValue()));
        // stoichiometry range
        if (stc.getMaxValue() != stc.getMinValue()){
            getStreamWriter().writeCharacters(" - ");
            getStreamWriter().writeCharacters(Long.toString(stc.getMaxValue()));
        }
        // write end attribute
        getStreamWriter().writeEndElement();
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalPreparations(Participant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalRoles(Participant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParticipantIdentificationMethods(Participant object, CvTerm method) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeExperimentalInteractor(Participant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeHostOrganisms(Participant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeConfidences(Participant object) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void writeParameters(Participant object) {
        // nothing to do
    }
}
