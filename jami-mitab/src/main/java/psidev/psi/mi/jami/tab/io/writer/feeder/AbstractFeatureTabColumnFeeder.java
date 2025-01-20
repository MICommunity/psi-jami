package psidev.psi.mi.jami.tab.io.writer.feeder;

import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Feature;
import psidev.psi.mi.jami.model.InteractionEvidence;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.ParticipantEvidence;
import psidev.psi.mi.jami.model.Range;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.tab.utils.MitabUtils;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.RangeUtils;
import psidev.psi.mi.jami.utils.XrefUtils;

import java.io.IOException;
import java.io.Writer;
import java.util.Collection;
import java.util.Iterator;

/**
 * Abstract FeatureTab column feeder
 */
public abstract class AbstractFeatureTabColumnFeeder<F extends Feature, I extends InteractionEvidence>
        extends AbstractTabColumnFeeder
        implements FeatureTabColumnFeeder<F, I> {

    /**
     * <p>Constructor for AbstractFeatureTabColumnFeeder.</p>
     *
     * @param writer a {@link Writer} object.
     */
    public AbstractFeatureTabColumnFeeder(Writer writer) {
        super(writer);
    }

    /** {@inheritDoc} */
    public void writeFeatureShortLabel(F feature) throws IOException {
        if (feature.getShortName() != null && !feature.getShortName().isEmpty()) {
            escapeAndWriteString(feature.getShortName());
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeRanges(F feature) throws IOException {
        Collection<Range> ranges = feature.getRanges();
        if (!ranges.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Range> rangeIterator = feature.getRanges().iterator();
            while (rangeIterator.hasNext()) {
                Range range = rangeIterator.next();
                stringBuilder.append(RangeUtils.convertRangeToString(range));
                if (rangeIterator.hasNext()) {
                    stringBuilder.append(MitabUtils.RANGE_SEPARATOR);
                }
            }
            String rangesString = stringBuilder.toString();
            if (rangesString.isEmpty()) {
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            } else {
                escapeAndWriteString(rangesString);
            }
        } else {
            getWriter().write(Range.UNDETERMINED_POSITION_SYMBOL);
            getWriter().write(Range.POSITION_SEPARATOR);
            getWriter().write(Range.UNDETERMINED_POSITION_SYMBOL);
        }
    }

    /** {@inheritDoc} */
    public void writeOriginalSequence(F feature) throws IOException {
        Collection<Range> ranges = feature.getRanges();
        if (!ranges.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Range> rangeIterator = feature.getRanges().iterator();
            while (rangeIterator.hasNext()) {
                Range range = rangeIterator.next();
                if (range.getResultingSequence() != null) {
                    if (range.getResultingSequence().getOriginalSequence() != null &&
                            !range.getResultingSequence().getOriginalSequence().isEmpty()) {
                        stringBuilder.append(range.getResultingSequence().getOriginalSequence());
                    }
                }
                if (rangeIterator.hasNext()) {
                    stringBuilder.append(MitabUtils.RANGE_SEPARATOR);
                }
            }
            String originalSequenceString = stringBuilder.toString();
            if (originalSequenceString.isEmpty()) {
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            } else {
                escapeAndWriteString(originalSequenceString);
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeResultingSequence(F feature) throws IOException {
        Collection<Range> ranges = feature.getRanges();
        if (!ranges.isEmpty()) {
            StringBuilder stringBuilder = new StringBuilder();
            Iterator<Range> rangeIterator = feature.getRanges().iterator();
            while (rangeIterator.hasNext()) {
                Range range = rangeIterator.next();
                if (range.getResultingSequence() != null) {
                    if (range.getResultingSequence().getNewSequence() != null &&
                            !range.getResultingSequence().getNewSequence().isEmpty()) {
                        stringBuilder.append(range.getResultingSequence().getNewSequence());
                    }
                }
                if (rangeIterator.hasNext()) {
                    stringBuilder.append(MitabUtils.RANGE_SEPARATOR);
                }
            }
            String resultingSequenceString = stringBuilder.toString();
            if (resultingSequenceString.isEmpty()) {
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            } else {
                escapeAndWriteString(resultingSequenceString);
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeFeatureType(F feature) throws IOException {
        if (feature.getType() != null) {
            writeCvTerm(feature.getType());
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeAnnotations(F feature) throws IOException {
        if (!feature.getAnnotations().isEmpty()) {
            Iterator<Annotation> annotationIterator = feature.getAnnotations().iterator();
            while (annotationIterator.hasNext()) {
                Annotation annot = annotationIterator.next();
                writeAnnotation(annot);
                if (annotationIterator.hasNext()) {
                    getWriter().write(MitabUtils.FIELD_SEPARATOR);
                }
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeAffectedProteinAc(F feature) throws IOException {
        if (feature.getParticipant() != null && feature.getParticipant().getInteractor() != null) {
            Interactor interactor = feature.getParticipant().getInteractor();
            if (interactor.getPreferredIdentifier() != null) {
                writeIdentifier(interactor.getPreferredIdentifier());
            } else {
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeAffectedProteinSymbol(F feature) throws IOException {
        if (feature.getParticipant() != null && feature.getParticipant().getInteractor() != null) {
            Interactor interactor = feature.getParticipant().getInteractor();
            if (!interactor.getAliases().isEmpty()) {
                Alias geneNameAlias = AliasUtils.collectFirstAliasWithType(interactor.getAliases(), Alias.GENE_NAME_MI, Alias.GENE_NAME);
                if (geneNameAlias != null) {
                    writeAlias(geneNameAlias);
                } else {
                    getWriter().write(MitabUtils.EMPTY_COLUMN);
                }
            } else {
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeAffectedProteinFullName(F feature) throws IOException {
        if (feature.getParticipant() != null && feature.getParticipant().getInteractor() != null) {
            Interactor interactor = feature.getParticipant().getInteractor();
            if (interactor.getFullName() != null && !interactor.getFullName().isEmpty()) {
                escapeAndWriteString(interactor.getFullName());
            } else {
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeAffectedProteinOrganism(F feature) throws IOException {
        if (feature.getParticipant() != null && feature.getParticipant().getInteractor() != null) {
            Interactor interactor = feature.getParticipant().getInteractor();
            if (interactor.getOrganism() != null) {
                writeOrganism(interactor.getOrganism());
            } else {
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeInteractionParticipants(I interaction) throws IOException {
        if (!interaction.getParticipants().isEmpty()) {
            Iterator<ParticipantEvidence> participantIterator = interaction.getParticipants().iterator();
            while (participantIterator.hasNext()) {
                ParticipantEvidence participant = participantIterator.next();
                Interactor interactor = participant.getInteractor();
                if (interactor != null) {
                    getWriter().write("(");
                    if (interactor.getPreferredIdentifier() != null) {
                        writeIdentifier(interactor.getPreferredIdentifier());
                    } else {
                        getWriter().write(MitabUtils.EMPTY_COLUMN);
                    }
                    getWriter().write("(");
                    writeCvTerm(interactor.getInteractorType());
                    getWriter().write("), ");
                    writeOrganism(interactor.getOrganism());
                    getWriter().write(")");
                } else {
                    getWriter().write(MitabUtils.EMPTY_COLUMN);
                }
                if (participantIterator.hasNext()) {
                    getWriter().write(MitabUtils.FIELD_SEPARATOR);
                }
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writePubMedId(Experiment experiment) throws IOException {
        if (experiment != null && experiment.getPublication() != null) {
            Collection<Xref> pubmedIds = XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(
                    experiment.getPublication().getIdentifiers(), Xref.PUBMED_MI, Xref.PUBMED, Xref.PRIMARY_MI, Xref.PRIMARY);
            if (pubmedIds.isEmpty()) {
                pubmedIds = XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(
                        experiment.getPublication().getIdentifiers(), Xref.PUBMED_MI, Xref.PUBMED, Xref.IDENTITY_MI, Xref.IDENTITY);
            }
            Collection<Xref> imexIds = XrefUtils.collectAllXrefsHavingDatabaseAndQualifier(
                    experiment.getPublication().getXrefs(), Xref.IMEX_MI, Xref.IMEX, Xref.IMEX_PRIMARY_MI, Xref.IMEX_PRIMARY);
            if (!pubmedIds.isEmpty() || !imexIds.isEmpty()) {
                Iterator<Xref> pubmedIdIterator = pubmedIds.iterator();
                while (pubmedIdIterator.hasNext()) {
                    Xref pubmedId = pubmedIdIterator.next();
                    writeIdentifier(pubmedId);
                    if (pubmedIdIterator.hasNext()) {
                        getWriter().write(MitabUtils.FIELD_SEPARATOR);
                    }
                }
                // If any Pubmed ids have been written and there are IMEx ids to be written, then we need a field separator
                if (!pubmedIds.isEmpty() && !imexIds.isEmpty()) {
                    getWriter().write(MitabUtils.FIELD_SEPARATOR);
                }
                Iterator<Xref> imexIdIterator = imexIds.iterator();
                while (imexIdIterator.hasNext()) {
                    Xref imexId = imexIdIterator.next();
                    writeIdentifier(imexId);
                    if (imexIdIterator.hasNext()) {
                        getWriter().write(MitabUtils.FIELD_SEPARATOR);
                    }
                }
            } else {
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeFigureLegends(Experiment experiment) throws IOException {
        if (experiment != null) {
            Collection<Annotation> annotations = AnnotationUtils.collectAllAnnotationsHavingTopic(
                    experiment.getAnnotations(), Annotation.FIGURE_LEGEND_MI, Annotation.FIGURE_LEGEND);
            if (!annotations.isEmpty()) {
                Iterator<Annotation> annotationIterator = annotations.iterator();
                while (annotationIterator.hasNext()) {
                    Annotation annot = annotationIterator.next();
                    writeAnnotation(annot);
                    if (annotationIterator.hasNext()) {
                        getWriter().write(MitabUtils.FIELD_SEPARATOR);
                    }
                }
            } else {
                getWriter().write(MitabUtils.EMPTY_COLUMN);
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }

    /** {@inheritDoc} */
    public void writeXrefIds(F feature) throws IOException {
        if (!feature.getIdentifiers().isEmpty()) {
            Iterator<Xref> identifierIterator = feature.getIdentifiers().iterator();
            while (identifierIterator.hasNext()) {
                Xref identifier = identifierIterator.next();
                writeIdentifier(identifier);
                if (identifierIterator.hasNext()) {
                    getWriter().write(MitabUtils.FIELD_SEPARATOR);
                }
            }
        } else {
            getWriter().write(MitabUtils.EMPTY_COLUMN);
        }
    }
}
