package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml25.xml253;

import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.model.extension.xml253.BibRef;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlExperiment;

import javax.xml.stream.XMLStreamWriter;
import java.util.Date;

/**
 * Abstract writer of interaction in PSI-XML 2.5
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public abstract class AbstractXmlInteractionWriter<T extends Interaction>
        extends psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml25.AbstractXmlInteractionWriter<T> {

    /**
     * <p>Constructor for AbstractXmlInteractionWriter.</p>
     *
     * @param writer a {@link XMLStreamWriter} object.
     * @param objectIndex a {@link PsiXmlObjectCache} object.
     */
    public AbstractXmlInteractionWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex){
        super(writer, objectIndex);
    }

    @Override
    protected void initialiseDefaultExperiment() {
        setDefaultExperiment(new DefaultXmlExperiment(new BibRef("Mock publication for interactions that do not have experimental details.",(String)null,(Date)null)));
    }
}
