package psidev.psi.mi.jami.xml.io.writer.expanded;

import psidev.psi.mi.jami.model.*;
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
 * Expanded PSI-XML writer for biological complexes (no experimental evidences).
 * Participants, features, experiments also have expanded names
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/11/13</pre>
 */
public class ExpandedXmlComplexWriter extends AbstractExpandedXmlWriter<Complex> {

    /**
     * <p>Constructor for ExpandedXmlComplexWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     */
    public ExpandedXmlComplexWriter(PsiXmlVersion version) {
        super(version, Complex.class);
    }

    /**
     * <p>Constructor for ExpandedXmlComplexWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param file a {@link java.io.File} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlComplexWriter(PsiXmlVersion version, File file) throws IOException, XMLStreamException {
        super(version, Complex.class, file);
    }

    /**
     * <p>Constructor for ExpandedXmlComplexWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param output a {@link java.io.OutputStream} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlComplexWriter(PsiXmlVersion version, OutputStream output) throws XMLStreamException {
        super(version, Complex.class, output);
    }

    /**
     * <p>Constructor for ExpandedXmlComplexWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public ExpandedXmlComplexWriter(PsiXmlVersion version, Writer writer) throws XMLStreamException {
        super(version, Complex.class, writer);
    }

    /**
     * <p>Constructor for ExpandedXmlComplexWriter.</p>
     *
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param cache a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public ExpandedXmlComplexWriter(PsiXmlVersion version, XMLStreamWriter streamWriter, PsiXmlObjectCache cache) {
        super(version, Complex.class, streamWriter, cache);
    }


    /** {@inheritDoc} */
    @Override
    protected Source extractSourceFromInteraction() {
        return getCurrentInteraction().getSource() != null ? getCurrentInteraction().getSource() : super.extractSourceFromInteraction();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSubWriters() {
        super.initialiseSubWriters(false, true, PsiXmlType.expanded, InteractionCategory.complex, ComplexType.n_ary);
    }
}
