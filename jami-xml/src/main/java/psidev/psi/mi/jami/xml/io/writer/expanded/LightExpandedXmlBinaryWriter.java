package psidev.psi.mi.jami.xml.io.writer.expanded;

import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.model.ComplexType;
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
 * Expanded PSI-XML writer for light binary interactions (no experimental evidences)
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class LightExpandedXmlBinaryWriter extends AbstractExpandedXmlWriter<BinaryInteraction> {

    /**
     * <p>Constructor for LightExpandedXmlBinaryWriter.</p>
     */
    public LightExpandedXmlBinaryWriter(PsiXmlVersion version) {
        super(version, BinaryInteraction.class);
    }

    /**
     * <p>Constructor for LightExpandedXmlBinaryWriter.</p>
     *
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public LightExpandedXmlBinaryWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, BinaryInteraction.class, file);
    }

    /**
     * <p>Constructor for LightExpandedXmlBinaryWriter.</p>
     *
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public LightExpandedXmlBinaryWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, BinaryInteraction.class, output);
    }

    /**
     * <p>Constructor for LightExpandedXmlBinaryWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public LightExpandedXmlBinaryWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, BinaryInteraction.class, writer);
    }

    /**
     * <p>Constructor for LightExpandedXmlBinaryWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public LightExpandedXmlBinaryWriter(PsiXmlVersion version, XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(version, BinaryInteraction.class, streamWriter, cache);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        super.initialiseSubWriters(false, false, PsiXmlType.expanded, InteractionCategory.basic, ComplexType.binary);
    }
}
