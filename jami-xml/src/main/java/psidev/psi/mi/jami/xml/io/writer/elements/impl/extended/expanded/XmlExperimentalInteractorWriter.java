package psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded;

import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.ExpandedPsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.XmlInteractorWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlExperimentalInteractorWriter;
import psidev.psi.mi.jami.xml.model.extension.AbstractExperimentalInteractor;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Expanded XML writer of experimental interactors
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>14/11/13</pre>
 */
public class XmlExperimentalInteractorWriter extends AbstractXmlExperimentalInteractorWriter implements ExpandedPsiXmlElementWriter<AbstractExperimentalInteractor> {
    private PsiXmlElementWriter<Interactor> interactorWriter;

    /**
     * <p>Constructor for XmlExperimentalInteractorWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlExperimentalInteractorWriter(PsiXmlVersion version, XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(version, writer, objectIndex);
    }

    /**
     * <p>Getter for the field <code>interactorWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Interactor> getInteractorWriter() {
        if (this.interactorWriter == null){
            this.interactorWriter = new XmlInteractorWriter(getVersion(), getStreamWriter(), getObjectIndex());

        }
        return interactorWriter;
    }

    /**
     * <p>Setter for the field <code>interactorWriter</code>.</p>
     *
     * @param interactorWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setInteractorWriter(PsiXmlElementWriter<Interactor> interactorWriter) {
        this.interactorWriter = interactorWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeInteractor(Interactor interactor) throws XMLStreamException {
        if (interactor != null){
            getInteractorWriter().write(interactor);
        }
    }
}
