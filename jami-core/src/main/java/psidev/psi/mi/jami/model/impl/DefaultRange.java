package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.comparator.range.UnambiguousRangeAndResultingSequenceComparator;

/**
 * Default implementation for Range
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousRangeAndResultingSequenceComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/01/13</pre>
 */
public class DefaultRange implements Range {

    private Position start;
    private Position end;
    private boolean isLink;

    private ResultingSequence resultingSequence;
    private Entity participant;

    /**
     * <p>Constructor for DefaultRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     */
    public DefaultRange(Position start, Position end){
        setPositions(start, end);
    }

    /**
     * <p>Constructor for DefaultRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     * @param isLink a boolean.
     */
    public DefaultRange(Position start, Position end, boolean isLink){
        this(start, end);
        this.isLink = isLink;
    }

    /**
     * <p>Constructor for DefaultRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     * @param resultingSequence a {@link psidev.psi.mi.jami.model.ResultingSequence} object.
     */
    public DefaultRange(Position start, Position end, ResultingSequence resultingSequence){
        this(start, end);
        this.resultingSequence = resultingSequence;
    }

    /**
     * <p>Constructor for DefaultRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     * @param isLink a boolean.
     * @param resultingSequence a {@link psidev.psi.mi.jami.model.ResultingSequence} object.
     */
    public DefaultRange(Position start, Position end, boolean isLink, ResultingSequence resultingSequence){
        this(start, end, isLink);
        this.resultingSequence = resultingSequence;
    }

    /**
     * <p>Constructor for DefaultRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     * @param participant a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public DefaultRange(Position start, Position end, Participant participant){
        this(start, end);
        this.participant = participant;
    }

    /**
     * <p>Constructor for DefaultRange.</p>
     *
     * @param start a {@link psidev.psi.mi.jami.model.Position} object.
     * @param end a {@link psidev.psi.mi.jami.model.Position} object.
     * @param isLink a boolean.
     * @param participant a {@link psidev.psi.mi.jami.model.Participant} object.
     */
    public DefaultRange(Position start, Position end, boolean isLink, Participant participant){
        this(start, end, isLink);
        this.participant = participant;
    }

    /**
     * <p>Getter for the field <code>start</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Position} object.
     */
    public Position getStart() {
        return this.start;
    }

    /**
     * <p>Getter for the field <code>end</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Position} object.
     */
    public Position getEnd() {
        return this.end;
    }

    /** {@inheritDoc} */
    public void setPositions(Position start, Position end) {
        if (start == null){
            throw new IllegalArgumentException("The start position is required and cannot be null");
        }
        if (end == null){
            throw new IllegalArgumentException("The end position is required and cannot be null");
        }

        if (start.getEnd() != 0 && end.getStart() != 0 && start.getEnd() > end.getStart()){
            throw new IllegalArgumentException("The start position cannot be ending before the end position");
        }
        this.start = start;
        this.end = end;
    }

    /**
     * <p>isLink</p>
     *
     * @return a boolean.
     */
    public boolean isLink() {
        return this.isLink;
    }

    /** {@inheritDoc} */
    public void setLink(boolean link) {
        this.isLink = link;
    }

    /**
     * <p>Getter for the field <code>resultingSequence</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.ResultingSequence} object.
     */
    public ResultingSequence getResultingSequence() {
        return this.resultingSequence;
    }

    /** {@inheritDoc} */
    public void setResultingSequence(ResultingSequence resultingSequence) {
        this.resultingSequence = resultingSequence;
    }

    /**
     * <p>Getter for the field <code>participant</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Entity} object.
     */
    public Entity getParticipant() {
        return this.participant;
    }

    /** {@inheritDoc} */
    public void setParticipant(Entity participant) {
        this.participant = participant;
    }

    /** {@inheritDoc} */
    @Override
    public boolean equals(Object o) {
        if (this == o){
            return true;
        }

        if (!(o instanceof Range)){
            return false;
        }

        return UnambiguousRangeAndResultingSequenceComparator.areEquals(this, (Range) o);
    }

    /** {@inheritDoc} */
    @Override
    public int hashCode() {
        return UnambiguousRangeAndResultingSequenceComparator.hashCode(this);
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getStart().toString() + " - " + getEnd().toString() + (isLink() ? "(linked)" : "");
    }
}
