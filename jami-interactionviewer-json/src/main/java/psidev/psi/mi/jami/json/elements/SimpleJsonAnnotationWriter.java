package psidev.psi.mi.jami.json.elements;

import org.json.simple.JSONValue;
import psidev.psi.mi.jami.json.MIJsonUtils;
import psidev.psi.mi.jami.model.Annotation;

import java.io.IOException;
import java.io.Writer;

/**
 * Json writer for annotations
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/14</pre>
 */
public class SimpleJsonAnnotationWriter implements JsonElementWriter<Annotation>{

    private Writer writer;

    /**
     * <p>Constructor for SimpleJsonAnnotationWriter.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     */
    public SimpleJsonAnnotationWriter(Writer writer){
        if (writer == null){
            throw new IllegalArgumentException("The json annotation writer needs a non null Writer");
        }
        this.writer = writer;
    }

    /**
     * <p>write.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Annotation} object.
     * @throws java.io.IOException if any.
     */
    public void write(Annotation object) throws IOException {
        if (object == null) return;
        if (object.getValue() != null){
            MIJsonUtils.writePropertyValue(JSONValue.escape(object.getValue()), writer);
        }
    }
}
