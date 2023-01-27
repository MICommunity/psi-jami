package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.CompactPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.XmlParticipantWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.AbstractXmlInteractionWriter;
import psidev.psi.mi.jami.xml.model.extension.xml253.BibRef;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlExperiment;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

/**
 * Expanded XML 2.5 writer for a basic interaction (ignore experimental details).
 * Interactions are named interactions with a fullname and aliases in addition to a shortname
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/11/13</pre>
 */
public class XmlBasicInteractionWriter extends AbstractXmlInteractionWriter<Interaction>
        implements CompactPsiXmlElementWriter<Interaction> {

    /**
     * <p>Constructor for XmlBasicInteractionWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlBasicInteractionWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseParticipantWriter() {
        super.setParticipantWriter(new XmlParticipantWriter(getStreamWriter(), getObjectIndex()));
    }

    /** {@inheritDoc} */
    @Override
    protected CvTerm writeExperiments(Interaction object) throws XMLStreamException {
        return writeExperimentDescription();
    }

    protected void initialiseDefaultExperiment(){
        setDefaultExperiment(new DefaultXmlExperiment(new BibRef("Mock publication for interactions that do not have experimental details.",(String)null,(Date)null)));
    }
}
