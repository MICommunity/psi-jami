package psidev.psi.mi.jami.crosslink.extension;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Participant;
import psidev.psi.mi.jami.model.Position;
import psidev.psi.mi.jami.model.ResultingSequence;
import psidev.psi.mi.jami.model.impl.DefaultRange;

/**
 * Crosslink CSV extension of Range
 * It contains a FileSourceLocator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/06/13</pre>
 */
public class CsvRange extends DefaultRange implements FileSourceContext{

    private FileSourceLocator sourceLocator;

    /**
     * <p>Constructor for CsvRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     */
    public CsvRange(Position start, Position end) {
        super(start, end);
    }

    /**
     * <p>Constructor for CsvRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     * @param isLink a boolean.
     */
    public CsvRange(Position start, Position end, boolean isLink) {
        super(start, end, isLink);
    }

    /**
     * <p>Constructor for CsvRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     * @param resultingSequence a {@link psidev.psi.mi.jami.model.ResultingSequence} object.
     */
    public CsvRange(Position start, Position end, ResultingSequence resultingSequence) {
        super(start, end, resultingSequence);
    }

    /**
     * <p>Constructor for CsvRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     * @param isLink a boolean.
     * @param resultingSequence a {@link psidev.psi.mi.jami.model.ResultingSequence} object.
     */
    public CsvRange(Position start, Position end, boolean isLink, ResultingSequence resultingSequence) {
        super(start, end, isLink, resultingSequence);
    }

    /**
     * <p>Constructor for CsvRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     * @param participant a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public CsvRange(Position start, Position end, Participant participant) {
        super(start, end, participant);
    }

    /**
     * <p>Constructor for CsvRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     * @param isLink a boolean.
     * @param participant a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public CsvRange(Position start, Position end, boolean isLink, Participant participant) {
        super(start, end, isLink, participant);
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.datasource.FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        this.sourceLocator = sourceLocator;
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Feature Range: "+sourceLocator != null ? sourceLocator.toString():super.toString();
    }
}
