package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254;

import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlExperimentWriter;
import psidev.psi.mi.jami.xml.model.extension.xml254.BibRef;
import psidev.psi.mi.jami.xml.model.extension.xml254.DefaultXmlExperiment;

import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

/**
 * Abstract class for interaction evidence writers that write expanded interactions (having modelled, intramolecular properties, list
 * of experiments, list of interaction types, etc.)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/11/13</pre>
 */
public abstract class AbstractXmlInteractionEvidenceWriter<I extends InteractionEvidence>
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.AbstractXmlInteractionEvidenceWriter<I> {

    /**
     * <p>Constructor for AbstractXmlInteractionEvidenceWriter.</p>
     *
     * @param writer a {@link XMLStreamWriter} object.
     * @param objectIndex a {@link PsiXmlObjectCache} object.
     */
    public AbstractXmlInteractionEvidenceWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);

    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseExperimentWriter(){
        super.setExperimentWriter(new XmlExperimentWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseConfidenceWriter(){
        super.setConfidenceWriter(new XmlConfidenceWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParameterWriter(){
        super.setParameterWriter(new XmlParameterWriter(getStreamWriter(), getObjectIndex()));
    }


    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(I object) {
        // nothing to write
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseDefaultExperiment() {
        setDefaultExperiment(new DefaultXmlExperiment(new BibRef("Mock publication for interactions that do not have experimental details.",(String)null,(Date)null)));
        getParameterWriter().setDefaultExperiment(getDefaultExperiment());
    }
}