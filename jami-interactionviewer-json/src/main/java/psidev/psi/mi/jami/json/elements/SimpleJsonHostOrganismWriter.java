package psidev.psi.mi.jami.json.elements;

import psidev.psi.mi.jami.json.MIJsonUtils;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;

import java.io.IOException;
import java.io.Writer;

/**
 * Json writer for Host organisms. It writes celltype, tissue and compartment
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public class SimpleJsonHostOrganismWriter extends SimpleJsonOrganismWriter{

    private JsonElementWriter<CvTerm> cvWriter;

    /**
     * <p>Constructor for SimpleJsonHostOrganismWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public SimpleJsonHostOrganismWriter(Writer writer){
        super(writer);
    }

    /**
     * <p>Getter for the field <code>cvWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public JsonElementWriter<CvTerm> getCvWriter() {
        if (this.cvWriter == null){
           this.cvWriter = new SimpleJsonCvTermWriter(getWriter());
        }
        return cvWriter;
    }

    /**
     * <p>Setter for the field <code>cvWriter</code>.</p>
     *
     * @param cvWriter a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public void setCvWriter(JsonElementWriter<CvTerm> cvWriter) {
        this.cvWriter = cvWriter;
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(Organism object) throws IOException {
        if (object.getCellType() != null){
            MIJsonUtils.writeSeparator(getWriter());
            MIJsonUtils.writePropertyKey("cellType", getWriter());
            getCvWriter().write(object.getCellType());
        }
        if (object.getTissue() != null){
            MIJsonUtils.writeSeparator(getWriter());
            MIJsonUtils.writePropertyKey("tissue", getWriter());
            getCvWriter().write(object.getTissue());
        }
        if (object.getCompartment() != null){
            MIJsonUtils.writeSeparator(getWriter());
            MIJsonUtils.writePropertyKey("compartment", getWriter());
            getCvWriter().write(object.getCompartment());
        }
    }
}
