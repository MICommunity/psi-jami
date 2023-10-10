package psidev.psi.mi.jami.xml.io.writer.compact;

import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Compact PSI-XML writer for light interactions (no experimental evidences) having names.
 * Participants, experiments and features are also assumed to have expanded names
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class LightCompactXmlNamedWriter extends AbstractCompactXmlWriter<Interaction> {

    /**
     * <p>Constructor for LightCompactXmlNamedWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     */
    public LightCompactXmlNamedWriter(PsiXmlVersion version) {
        super(version, Interaction.class);
    }

    /**
     * <p>Constructor for LightCompactXmlNamedWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public LightCompactXmlNamedWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, Interaction.class, file);
    }

    /**
     * <p>Constructor for LightCompactXmlNamedWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public LightCompactXmlNamedWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, Interaction.class, output);
    }

    /**
     * <p>Constructor for LightCompactXmlNamedWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public LightCompactXmlNamedWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, Interaction.class, writer);
    }

    /**
     * <p>Constructor for LightCompactXmlNamedWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public LightCompactXmlNamedWriter(PsiXmlVersion version, XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(version, Interaction.class, streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected void registerAvailabilities(Interaction interaction) {
        // nothing to do
    }

    /** {@inheritDoc} */
    @Override
    protected void registerExperiment(Interaction interaction) {
        getExperiments().add(getInteractionWriter().extractDefaultExperimentFrom(interaction));
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        super.initialiseSubWriters(false, true, PsiXmlType.compact, InteractionCategory.basic, ComplexType.n_ary);
    }
}
