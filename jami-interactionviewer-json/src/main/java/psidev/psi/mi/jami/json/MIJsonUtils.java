package psidev.psi.mi.jami.json;

import org.json.simple.JSONValue;
import psidev.psi.mi.jami.binary.BinaryInteraction;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.comparator.interactor.UnambiguousExactInteractorBaseComparator;

import java.io.IOException;
import java.io.Writer;

/**
 * Utility class for public properties
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/07/13</pre>
 */
public class MIJsonUtils {
    /** Constant <code>OPEN="{"</code> */
    public final static String OPEN = "{";
    /** Constant <code>CLOSE="}"</code> */
    public final static String CLOSE = "}";
    /** Constant <code>OPEN_ARRAY="["</code> */
    public final static String OPEN_ARRAY = "[";
    /** Constant <code>CLOSE_ARRAY="]"</code> */
    public final static String CLOSE_ARRAY = "]";
    /** Constant <code>PROPERTY_DELIMITER="\""</code> */
    public final static String PROPERTY_DELIMITER = "\"";
    /** Constant <code>PROPERTY_VALUE_SEPARATOR=":"</code> */
    public final static String PROPERTY_VALUE_SEPARATOR = ":";
    /** Constant <code>ELEMENT_SEPARATOR=","</code> */
    public final static String ELEMENT_SEPARATOR = ",";
    /** Constant <code>LINE_SEPARATOR="System.getProperty(line.separator)"</code> */
    public final static String LINE_SEPARATOR = System.getProperty("line.separator");
    /** Constant <code>INDENT="\t"</code> */
    public final static String INDENT = "\t";

    /**
     * <p>writePropertyKey.</p>
     *
     * @param key a {@link java.lang.String} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public static void writePropertyKey(String key, Writer writer) throws IOException {
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
        writer.write(key);
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
        writer.write(MIJsonUtils.PROPERTY_VALUE_SEPARATOR);
    }
    /**
     * <p>writePropertyValue.</p>
     *
     * @param value a {@link java.lang.String} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public static void writePropertyValue(String value, Writer writer) throws IOException {
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
        writer.write(value);
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
    }

    /**
     * <p>writeProperty.</p>
     *
     * @param propertyName a {@link java.lang.String} object.
     * @param value a {@link java.lang.String} object.
     * @param writer a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public static void writeProperty(String propertyName, String value, Writer writer) throws IOException {
        writePropertyKey(propertyName, writer);
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
        writer.write(value);
        writer.write(MIJsonUtils.PROPERTY_DELIMITER);
    }

    /**
     * <p>writeStartObject.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public static void writeStartObject(Writer writer) throws IOException {
        writer.write(MIJsonUtils.OPEN);
    }

    /**
     * <p>writeEndObject.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public static void writeEndObject(Writer writer) throws IOException {
        writer.write(MIJsonUtils.CLOSE);
    }

    /**
     * <p>writeSeparator.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public static void writeSeparator(Writer writer) throws IOException {
        writer.write(MIJsonUtils.ELEMENT_SEPARATOR);
    }

    /**
     * <p>writeOpenArray.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public static void writeOpenArray(Writer writer) throws IOException {
        writer.write(MIJsonUtils.OPEN_ARRAY);
    }

    /**
     * <p>writeEndArray.</p>
     *
     * @param writer a {@link java.io.Writer} object.
     * @throws java.io.IOException if any.
     */
    public static void writeEndArray(Writer writer) throws IOException {
        writer.write(MIJsonUtils.CLOSE_ARRAY);
    }

    /**
     * <p>extractInteractorId.</p>
     *
     * @param ref a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @return an array of String : first the database, then the interactorId
     */
    public static String[] extractInteractorId(Xref ref, Interactor interactor){
        String interactorId = null;
        String db = null;
        if (ref != null){
            interactorId = JSONValue.escape(ref.getId());
            db = JSONValue.escape(ref.getDatabase().getShortName());
        }
        else{
            interactorId = Integer.toString(UnambiguousExactInteractorBaseComparator.hashCode(interactor));
            db = "generated";
        }
        return new String[]{db, interactorId};
    }

    /**
     * <p>extractInteractionId.</p>
     *
     * @param ref a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param interaction a {@link psidev.psi.mi.jami.model.Interaction} object.
     * @return  an array of String : first the database, then the interactionId
     */
    public static String[] extractInteractionId(Xref ref, Interaction interaction){
        String interactorId = null;
        String db = null;
        if (ref != null){
            interactorId = JSONValue.escape(ref.getId());
            db = JSONValue.escape(ref.getDatabase().getShortName());
        }
        else if (interaction instanceof InteractionEvidence && ((InteractionEvidence)interaction).getImexId() != null){
           interactorId = ((InteractionEvidence)interaction).getImexId();
            db = "imex";
        }
        else{
            interactorId = Integer.toString(interaction.hashCode());
            db = "generated";
        }
        return new String[]{db, interactorId};
    }

    /**
     * <p>extractBinaryInteractionId.</p>
     *
     * @param ref a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param interaction a {@link psidev.psi.mi.jami.binary.BinaryInteraction} object.
     * @param number a suffix
     * @return  an array of String : first the database, then the interactionId, then a number to append
     */
    public static String[] extractBinaryInteractionId(Xref ref, BinaryInteraction interaction, Integer number){
        String interactorId = null;
        String db = null;
        if (ref != null){
            interactorId = JSONValue.escape(ref.getId())+(number != null ? "_"+number:"");
            db = JSONValue.escape(ref.getDatabase().getShortName());
        }
        else if (interaction instanceof InteractionEvidence && ((InteractionEvidence)interaction).getImexId() != null){
            interactorId = ((InteractionEvidence)interaction).getImexId()+(number != null ? "_"+number:"");
            db = "imex";
        }
        else{
            interactorId = Integer.toString(interaction.hashCode())+(number != null ? "_"+number:"");
            db = "generated";
        }
        return new String[]{db, interactorId};
    }
}
