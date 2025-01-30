package psidev.psi.mi.jami.tab.io.writer.feeder;

import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.tab.utils.MitabUtils;

import java.io.IOException;
import java.io.Writer;

/**
 * Abstract tabular column feeder
 */
public abstract class AbstractTabColumnFeeder {

    private final Writer writer;

    /**
     * <p>Constructor for AbstractTabColumnFeeder.</p>
     *
     * @param writer a {@link Writer} object.
     */
    public AbstractTabColumnFeeder(Writer writer) {
        if (writer == null) {
            throw  new IllegalArgumentException("The column feeder needs a Writer.");
        }
        this.writer = writer;
    }

    public void writeOrganism(Organism organism) throws IOException {
        if (organism != null) {
            writer.write(MitabUtils.TAXID);
            writer.write(MitabUtils.XREF_SEPARATOR);
            writer.write(Integer.toString(organism.getTaxId()));

            // write common name if provided
            if (organism.getCommonName() != null) {
                writer.write("(");
                escapeAndWriteString(organism.getCommonName());
                writer.write(")");

                // write scientific name if provided
                if (organism.getScientificName() != null) {
                    writer.write(MitabUtils.FIELD_SEPARATOR);
                    writer.write(MitabUtils.TAXID);
                    writer.write(MitabUtils.XREF_SEPARATOR);
                    writer.write(Integer.toString(organism.getTaxId()));
                    writer.write("(");
                    escapeAndWriteString(organism.getScientificName());
                    writer.write(")");
                }
            }
            // write scientific name if provided
            else if (organism.getScientificName() != null) {
                writer.write("(");
                escapeAndWriteString(organism.getScientificName());
                writer.write(")");
            }
        } else {
            writer.write(MitabUtils.EMPTY_COLUMN);
        }
    }

    public void writeCvTerm(CvTerm cv) throws IOException {
        if (cv != null) {
            // write MI xref first
            if (cv.getMIIdentifier() != null) {
                writer.write(CvTerm.PSI_MI);
                writer.write(MitabUtils.XREF_SEPARATOR);
                writer.write("\"");
                writer.write(cv.getMIIdentifier());
                writer.write("\"");

                // write cv name
                writer.write("(");
                writeCvTermName(cv);
                writer.write(")");
            }
            // write MOD xref
            else if (cv.getMODIdentifier() != null) {
                writer.write(CvTerm.PSI_MOD);
                writer.write(MitabUtils.XREF_SEPARATOR);
                writer.write("\"");
                writer.write(cv.getMODIdentifier());
                writer.write("\"");

                // write cv name
                writer.write("(");
                writeCvTermName(cv);
                writer.write(")");
            }
            // write PAR xref
            else if (cv.getPARIdentifier() != null) {
                writer.write(CvTerm.PSI_PAR);
                writer.write(MitabUtils.XREF_SEPARATOR);
                writer.write("\"");
                writer.write(cv.getPARIdentifier());
                writer.write("\"");

                // write cv name
                writer.write("(");
                writeCvTermName(cv);
                writer.write(")");
            }
            // write first identifier
            else if (!cv.getIdentifiers().isEmpty()) {
                writeIdentifier(cv.getIdentifiers().iterator().next());

                // write cv name
                writer.write("(");
                writeCvTermName(cv);
                writer.write(")");
            }
            // write empty column
            else {
                writer.write(MitabUtils.UNKNOWN_DATABASE);
                writer.write(MitabUtils.XREF_SEPARATOR);
                writer.write(MitabUtils.UNKNOWN_ID);

                // write cv name
                writer.write("(");
                writeCvTermName(cv);
                writer.write(")");
            }
        } else {
            writer.write(MitabUtils.EMPTY_COLUMN);
        }
    }

    public void writeAlias(Alias alias) throws IOException {
        if (alias != null) {
            // write db first
            escapeAndWriteString(MitabUtils.findDbSourceForAlias(alias));
            // write xref separator
            writer.write(MitabUtils.XREF_SEPARATOR);
            // write name
            escapeAndWriteString(alias.getName());
            // write type
            if (alias.getType() != null) {
                writer.write("(");
                escapeAndWriteString(alias.getType().getShortName());
                writer.write(")");
            }
        }
    }

    public void writeIdentifier(Xref identifier) throws IOException {
        if (identifier != null) {
            // write db first
            escapeAndWriteString(identifier.getDatabase().getShortName());
            // write xref separator
            writer.write(MitabUtils.XREF_SEPARATOR);
            // write id
            escapeAndWriteString(identifier.getId());
        }
    }

    public void writeXref(Xref xref) throws IOException {
        if (xref != null){
            // write identifier first
            writeIdentifier(xref);

            // write qualifier
            if (xref.getQualifier() != null){
                getWriter().write("(");
                escapeAndWriteString(xref.getQualifier().getShortName());
                getWriter().write(")");
            }
        }
    }

    public void escapeAndWriteString(String stringToEscape) throws IOException {
        // replace first tabs and break line with a space and escape double quote
        String replaced = stringToEscape.replaceAll(MitabUtils.LINE_BREAK + "|" + MitabUtils.COLUMN_SEPARATOR, " ");
        replaced = replaced.replaceAll("\"", "\\\\\"");

        for (String special : MitabUtils.SPECIAL_CHARACTERS) {
            if (replaced.contains(special)) {
                writer.write("\"");
                writer.write(replaced);
                writer.write("\"");
                return;
            }
        }

        writer.write(replaced);
    }

    public void writeAnnotation(Annotation annotation) throws IOException {
        if (annotation != null) {
            // write topic first
            writeCvTermName(annotation.getTopic());

            // write text after
            if (annotation.getValue() != null) {
                getWriter().write(MitabUtils.XREF_SEPARATOR);
                escapeAndWriteString(annotation.getValue());
            }
        }
    }

    /**
     * <p>Getter for the field <code>writer</code>.</p>
     *
     * @return a {@link Writer} object.
     */
    protected Writer getWriter() {
        return writer;
    }

    protected void writeCvTermName(CvTerm cv) throws IOException {
        if (cv != null) {
            if (cv.getFullName() != null) {
                escapeAndWriteString(cv.getFullName());
            } else {
                escapeAndWriteString(cv.getShortName());
            }
        }
    }
}
