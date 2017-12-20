package psidev.psi.mi.jami.json.elements;

import org.json.simple.JSONValue;
import psidev.psi.mi.jami.json.MIJsonUtils;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Xref;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;

/**
 * Json writer for publications
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public class SimpleJsonPublicationWriter implements JsonElementWriter<Publication>{

    private Writer writer;
    private JsonElementWriter<CvTerm> cvWriter;
    private JsonElementWriter<Xref> identifierWriter;

    /**
     * <p>Constructor for SimpleJsonPublicationWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public SimpleJsonPublicationWriter(Writer writer){
        if (writer == null){
            throw new IllegalArgumentException("The json publication writer needs a non null Writer");
        }
        this.writer = writer;
    }

    /**
     * <p>write.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Publication} object.
     * @throws java.io.IOException if any.
     */
    public void write(Publication object) throws IOException {
        if (!object.getIdentifiers().isEmpty()){
            MIJsonUtils.writePropertyKey("pubid", writer);
            MIJsonUtils.writeOpenArray(writer);

            Iterator<Xref> identifierIterator = object.getIdentifiers().iterator();
            while (identifierIterator.hasNext()){
                getIdentifierWriter().write(identifierIterator.next());

                if (identifierIterator.hasNext()){
                    MIJsonUtils.writeSeparator(writer);
                }
            }

            if (object.getImexId() != null){
                Xref imexXref=getImexXref(object);
                if(imexXref!=null) {
                    MIJsonUtils.writeSeparator(writer);
                    getIdentifierWriter().write(imexXref);
                }
            }
            MIJsonUtils.writeEndArray(writer);
        }
        else if (object.getImexId() != null){
            Xref imexXref=getImexXref(object);
            if(imexXref!=null) {
                MIJsonUtils.writePropertyKey("pubid", writer);
                MIJsonUtils.writeOpenArray(writer);
                getIdentifierWriter().write(imexXref);
                MIJsonUtils.writeEndArray(writer);
            }
        }

        // publication source
        if (object.getSource() != null){
            MIJsonUtils.writeSeparator(writer);
            MIJsonUtils.writePropertyKey("sourceDatabase", writer);
            getCvWriter().write(object.getSource());
        }
    }

    private Xref getImexXref(Publication object){
        for(Xref xref:object.getXrefs()){
            if(xref.getDatabase().getShortName().equals("imex")){
                return xref;
            }
        }

        return null;
    }

    /**
     * <p>Getter for the field <code>cvWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public JsonElementWriter<CvTerm> getCvWriter() {
        if (this.cvWriter == null){
            this.cvWriter = new SimpleJsonCvTermWriter(writer);
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

    /**
     * <p>Getter for the field <code>identifierWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public JsonElementWriter<Xref> getIdentifierWriter() {
        if (this.identifierWriter == null){
            this.identifierWriter = new SimpleJsonIdentifierWriter(writer);
        }
        return identifierWriter;
    }

    /**
     * <p>Setter for the field <code>identifierWriter</code>.</p>
     *
     * @param identifierWriter a {@link psidev.psi.mi.jami.json.elements.JsonElementWriter} object.
     */
    public void setIdentifierWriter(JsonElementWriter<Xref> identifierWriter) {
        this.identifierWriter = identifierWriter;
    }
}
