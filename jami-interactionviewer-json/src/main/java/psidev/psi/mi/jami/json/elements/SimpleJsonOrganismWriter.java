package psidev.psi.mi.jami.json.elements;

import org.json.simple.JSONValue;
import psidev.psi.mi.jami.json.MIJsonUtils;
import psidev.psi.mi.jami.model.Organism;

import java.io.IOException;
import java.io.Writer;

/**
 * Json writer for organisms. It ignores celltype, tissue and compartment
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public class SimpleJsonOrganismWriter implements JsonElementWriter<Organism>{

    private Writer writer;

    /**
     * <p>Constructor for SimpleJsonOrganismWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public SimpleJsonOrganismWriter(Writer writer){
        if (writer == null){
            throw new IllegalArgumentException("The json organism writer needs a non null Writer");
        }
        this.writer = writer;
    }

    /**
     * <p>write.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Organism} object.
     * @throws java.io.IOException if any.
     */
    public void write(Organism object) throws IOException {
        MIJsonUtils.writeStartObject(writer);
        MIJsonUtils.writeProperty("taxid", Integer.toString(object.getTaxId()), writer);
        if (object.getCommonName() != null){
            MIJsonUtils.writeSeparator(writer);
            MIJsonUtils.writeProperty("common", JSONValue.escape(object.getCommonName()), writer);
        }
        if (object.getScientificName() != null){
            MIJsonUtils.writeSeparator(writer);
            MIJsonUtils.writeProperty("scientific", JSONValue.escape(object.getScientificName()), writer);
        }

        writeOtherProperties(object);

        MIJsonUtils.writeEndObject(writer);
    }

    /**
     * <p>writeOtherProperties.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Organism} object.
     * @throws java.io.IOException if any.
     */
    protected void writeOtherProperties(Organism object) throws IOException {
        // nothing to do here but can be overriden
    }

    /**
     * <p>Getter for the field <code>writer</code>.</p>
     *
     * @return a {@link java.io.Writer} object.
     */
    protected Writer getWriter() {
        return writer;
    }
}
