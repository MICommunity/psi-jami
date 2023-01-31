package psidev.psi.mi.jami.xml.io.writer.expanded;

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
 * Expanded PSI-XML writer for light interactions (no experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class LightExpandedXmlWriter extends AbstractExpandedXmlWriter<Interaction> {

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     */
    public LightExpandedXmlWriter(PsiXmlVersion version) {
        super(version, Interaction.class);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public LightExpandedXmlWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, Interaction.class, file);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public LightExpandedXmlWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, Interaction.class, output);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public LightExpandedXmlWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, Interaction.class, writer);
    }

    /**
     * <p>Constructor for LightExpandedXmlWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public LightExpandedXmlWriter(PsiXmlVersion version, XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(version, Interaction.class, streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        super.initialiseSubWriters(false, false, PsiXmlType.expanded, InteractionCategory.basic, ComplexType.n_ary);
    }
}
