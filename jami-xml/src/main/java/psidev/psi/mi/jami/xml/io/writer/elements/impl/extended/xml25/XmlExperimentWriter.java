package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.utils.ExperimentUtils;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlCvTermWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlDbXrefWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.*;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlExperiment;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.List;

/**
 * XML 2.5 writer for expanded experiments having participant identification method,
 * feature detection method and a list of host organisms
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>13/11/13</pre>
 */
public class XmlExperimentWriter extends XmlNamedExperimentWriter {

    /**
     * <p>Constructor for XmlExperimentWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlExperimentWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeParticipantIdentificationMethod(Experiment object) {
        if (object instanceof ExtendedPsiXmlExperiment){
            ExtendedPsiXmlExperiment xmlExperiment = (ExtendedPsiXmlExperiment)object;
            // write participant identification method
            CvTerm identificationMethod = xmlExperiment.getParticipantIdentificationMethod();
            if (identificationMethod != null){
                // write cv
                getDetectionMethodWriter().write(identificationMethod,"participantIdentificationMethod");
                return identificationMethod;
            }
            else {
                return super.writeParticipantIdentificationMethod(object);
            }
        }
        return super.writeParticipantIdentificationMethod(object);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(Experiment object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlExperiment){
            ExtendedPsiXmlExperiment xmlExperiment = (ExtendedPsiXmlExperiment)object;
            // write feature detection method
            CvTerm featureMethod = xmlExperiment.getFeatureDetectionMethod();
            if (featureMethod != null){
                // write cv
                getDetectionMethodWriter().write(featureMethod,"featureDetectionMethod");
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public CvTerm extractDefaultParticipantIdentificationMethod(Experiment exp) {
        if (exp instanceof ExtendedPsiXmlExperiment){
            ExtendedPsiXmlExperiment xmlExperiment = (ExtendedPsiXmlExperiment)exp;
            // write participant identification method
            CvTerm identificationMethod = xmlExperiment.getParticipantIdentificationMethod();
            if (identificationMethod != null){
                return identificationMethod;
            }
        }
        return ExperimentUtils.extractMostCommonParticipantDetectionMethodFrom(exp);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeHostOrganism(Experiment object) throws XMLStreamException {
        if (object instanceof ExtendedPsiXmlExperiment){
            ExtendedPsiXmlExperiment xmlExperiment = (ExtendedPsiXmlExperiment)object;
            // write hostOrganismList
            List<Organism> hostList = xmlExperiment.getHostOrganisms();
            if (!hostList.isEmpty()){
                // write start host organism list
                getStreamWriter().writeStartElement("hostOrganismList");
                // write host
                for (Organism host : hostList){
                    if (host != null){
                        getHostOrganismWriter().write(host);
                    }
                }
                // write end host organism list
                getStreamWriter().writeEndElement();
            }
        }
        else{
            super.writeHostOrganism(object);
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialisePublicationWriter() {
        super.setPublicationWriter(new XmlPublicationWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefWriter() {
        super.setXrefWriter(new XmlDbXrefWriter(getStreamWriter()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseHostOrganismWriter() {
        switch (getVersion()) {
            case v2_5_3:
                super.setHostOrganismWriter(
                        new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlHostOrganismWriter(
                                getStreamWriter(), getObjectIndex()));
                break;
            case v2_5_4:
            default:
                super.setHostOrganismWriter(
                        new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlHostOrganismWriter(
                                getStreamWriter(), getObjectIndex()));
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseConfidenceWriter() {
        switch (getVersion()) {
            case v2_5_3:
                super.setConfidenceWriter(
                        new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlConfidenceWriter(
                                getStreamWriter(), getObjectIndex()));
                break;
            case v2_5_4:
            default:
                super.setConfidenceWriter(
                        new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlConfidenceWriter(
                                getStreamWriter(), getObjectIndex()));
                break;
        }
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDetectionMethodWriter() {
        super.setDetectionMethodWriter(new XmlCvTermWriter(getStreamWriter()));
    }
}
