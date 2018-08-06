package psidev.psi.mi.jami.enricher.util;

import psidev.psi.mi.jami.enricher.EntityEnricher;
import psidev.psi.mi.jami.enricher.FeatureEnricher;
import psidev.psi.mi.jami.enricher.ParticipantEnricher;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.listener.*;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.comparator.alias.DefaultAliasComparator;
import psidev.psi.mi.jami.utils.comparator.annotation.DefaultAnnotationComparator;
import psidev.psi.mi.jami.utils.comparator.checksum.DefaultChecksumComparator;
import psidev.psi.mi.jami.utils.comparator.confidence.DefaultConfidenceComparator;
import psidev.psi.mi.jami.utils.comparator.parameter.DefaultParameterComparator;
import psidev.psi.mi.jami.utils.comparator.range.DefaultRangeAndResultingSequenceComparator;
import psidev.psi.mi.jami.utils.comparator.xref.DefaultExternalIdentifierComparator;
import psidev.psi.mi.jami.utils.comparator.xref.DefaultXrefComparator;

import java.util.Collection;
import java.util.Iterator;

/**
 * Utilities for enrichers.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 10/07/13

 */
public class EnricherUtils {

    /* Characters to be used for new rows, new columns, blank cells */
    /** Constant <code>NEW_LINE="\n"</code> */
    public static final String NEW_LINE = "\n";
    /** Constant <code>COLUMN_SEPARATOR="\t"</code> */
    public static final String COLUMN_SEPARATOR = "\t";
    /** Constant <code>BLANK_SPACE="-"</code> */
    public static final String BLANK_SPACE = "-";

    /**
     * Takes two lists of Xrefs and produces a list of those to add and those to remove.
     *
     * It will add in toEnrichXrefs all xref from fetchedXrefs that are not there. It will also remove extra identifiers from toEnrichXrefs
     * if remove boolean is true.
     *
     * @param termToEnrich     The object to enrich
     * @param fetchedXrefs      The new xrefs to be added.
     * @param remove: if true, we remove xrefs that are not in enriched list
     * @param isIdentifier if true compare identifiers, otherwise xrefs
     * @param toEnrichXrefs a {@link java.util.Collection} object.
     * @param xrefListener a {@link psidev.psi.mi.jami.listener.XrefsChangeListener} object.
     * @param identifierListener a {@link psidev.psi.mi.jami.listener.IdentifiersChangeListener} object.
     * @param <T> a T object.
     */
    public static <T extends Object> void mergeXrefs(T termToEnrich, Collection<Xref> toEnrichXrefs, Collection<Xref> fetchedXrefs , boolean remove,
                              boolean isIdentifier, XrefsChangeListener<T> xrefListener, IdentifiersChangeListener<T> identifierListener){

        Iterator<Xref> refIterator = toEnrichXrefs.iterator();
        // remove xrefs in toEnrichXrefs that are not in fetchedXrefs
        if (remove){
            while(refIterator.hasNext()){
                Xref ref = refIterator.next();
                boolean containsRef = false;
                for (Xref ref2 : fetchedXrefs){
                    // identical xrefs
                    if (DefaultXrefComparator.areEquals(ref, ref2)){
                        containsRef = true;
                        break;
                    }
                }
                // remove xref not in second list
                if (!containsRef){
                    refIterator.remove();
                    if (isIdentifier){
                        if (identifierListener != null){
                            identifierListener.onRemovedIdentifier(termToEnrich, ref);
                        }
                    }
                    else{
                        if (xrefListener != null){
                            xrefListener.onRemovedXref(termToEnrich, ref);
                        }
                    }
                }
            }
        }

        // add xrefs from fetchedXrefs that are not in toEnrichXref
        refIterator = fetchedXrefs.iterator();
        while(refIterator.hasNext()){
            Xref ref = refIterator.next();
            boolean containsRef = false;
            for (Xref ref2 : toEnrichXrefs){
                // when we allow to removexrefs, we compare qualifiers as well
                if (remove){
                    // identical xrefs
                    if (DefaultXrefComparator.areEquals(ref, ref2)){
                        containsRef = true;
                        break;
                    }
                }
                // when we don't allow to remove xrefs, we compare only database+identifier to avoid dupicating xrefs with same db/id but different qualifiers.
                // it would be too confusing for the suer
                else{
                    // identical identifier
                    if (DefaultExternalIdentifierComparator.areEquals(ref, ref2)){
                        containsRef = true;
                        break;
                    }
                }
            }
            // add missing xref not in second list
            if (!containsRef){
                toEnrichXrefs.add(ref);
                if (isIdentifier){
                    if (identifierListener != null){
                        identifierListener.onAddedIdentifier(termToEnrich, ref);
                    }
                }
                else{
                    if (xrefListener != null){
                        xrefListener.onAddedXref(termToEnrich, ref);
                    }
                }
            }
        }
    }

    /**
     * <p>mergeRanges.</p>
     *
     * @param termToEnrich a T object.
     * @param toEnrichRanges a {@link java.util.Collection} object.
     * @param fetchedRanges a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @param featureListener a {@link psidev.psi.mi.jami.listener.FeatureChangeListener} object.
     * @param <T> a T object.
     */
    public static <T extends Feature> void mergeRanges(T termToEnrich, Collection<Range> toEnrichRanges, Collection<Range> fetchedRanges , boolean remove,
                                                      FeatureChangeListener<T> featureListener){

        Iterator<Range> refIterator = toEnrichRanges.iterator();
        // remove ranges in toEnrichRanges that are not in fetchedRanges
        if (remove){
            while(refIterator.hasNext()){
                Range ref = refIterator.next();
                boolean containsRef = false;
                for (Range ref2 : fetchedRanges){
                    // identical ranges
                    if (DefaultRangeAndResultingSequenceComparator.areEquals(ref, ref2)){
                        containsRef = true;
                        break;
                    }
                }
                // remove range not in second list
                if (!containsRef){
                    refIterator.remove();
                    if (featureListener != null){
                        featureListener.onRemovedRange(termToEnrich, ref);
                    }
                }
            }
        }

        // add ranges from fetchedRanges that are not in toEnrichRanges
        refIterator = fetchedRanges.iterator();
        while(refIterator.hasNext()){
            Range ref = refIterator.next();
            boolean containsRef = false;
            for (Range ref2 : toEnrichRanges){
                // identical ranges
                if (DefaultRangeAndResultingSequenceComparator.areEquals(ref, ref2)){
                    containsRef = true;
                    break;
                }
            }
            // add missing xref not in second list
            if (!containsRef){
                toEnrichRanges.add(ref);
                if (featureListener != null){
                    featureListener.onAddedRange(termToEnrich, ref);
                }
            }
        }
    }

    /**
     * <p>mergeAliases.</p>
     *
     * @param termToEnrich a T object.
     * @param toEnrichAliases a {@link java.util.Collection} object.
     * @param fetchedAliases a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @param aliasListener a {@link psidev.psi.mi.jami.listener.AliasesChangeListener} object.
     * @param <T> a T object.
     */
    public static <T extends Object> void mergeAliases(T termToEnrich, Collection<Alias> toEnrichAliases, Collection<Alias> fetchedAliases, boolean remove, AliasesChangeListener<T> aliasListener){
        Iterator<Alias> aliasIterator = toEnrichAliases.iterator();
        // remove aliases in toEnrichAliases that are not in fetchedAliases
        if (remove){
            while(aliasIterator.hasNext()){
                Alias alias = aliasIterator.next();
                boolean containsAlias = false;
                for (Alias alias2 : fetchedAliases){
                    // identical aliases
                    if (DefaultAliasComparator.areEquals(alias, alias2)){
                        containsAlias = true;
                        break;
                    }
                }
                // remove alias not in second list
                if (!containsAlias){
                    aliasIterator.remove();
                    if (aliasListener != null){
                        aliasListener.onRemovedAlias(termToEnrich, alias);
                    }
                }
            }
        }

        // add xrefs from fetchedXrefs that are not in toEnrichXref
        aliasIterator = fetchedAliases.iterator();
        while(aliasIterator.hasNext()){
            Alias alias = aliasIterator.next();
            boolean containsAlias = false;
            for (Alias alias2 : toEnrichAliases){
                // identical aliases
                if (DefaultAliasComparator.areEquals(alias, alias2)){
                    containsAlias = true;
                    break;
                }
            }
            // add missing xref not in second list
            if (!containsAlias){
                toEnrichAliases.add(alias);
                if (aliasListener != null){
                    aliasListener.onAddedAlias(termToEnrich, alias);
                }
            }
        }
    }

    /**
     * <p>mergeChecksums.</p>
     *
     * @param termToEnrich a T object.
     * @param toEnrichChecksums a {@link java.util.Collection} object.
     * @param fetchedCehcksum a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @param aliasListener a {@link psidev.psi.mi.jami.listener.ChecksumsChangeListener} object.
     * @param <T> a T object.
     */
    public static <T extends Object> void mergeChecksums(T termToEnrich, Collection<Checksum> toEnrichChecksums, Collection<Checksum> fetchedCehcksum, boolean remove, ChecksumsChangeListener<T> aliasListener){
        Iterator<Checksum> checksumIterator = toEnrichChecksums.iterator();
        // remove aliases in toEnrichAliases that are not in fetchedAliases
        if (remove){
            while(checksumIterator.hasNext()){
                Checksum checksum = checksumIterator.next();
                boolean containsChecksum = false;
                for (Checksum checksum2 : fetchedCehcksum){
                    // identical checksum
                    if (DefaultChecksumComparator.areEquals(checksum, checksum2)){
                        containsChecksum = true;
                        break;
                    }
                }
                // remove alias not in second list
                if (!containsChecksum){
                    checksumIterator.remove();
                    if (aliasListener != null){
                        aliasListener.onRemovedChecksum(termToEnrich, checksum);
                    }
                }
            }
        }

        // add xrefs from fetchedXrefs that are not in toEnrichXref
        checksumIterator = fetchedCehcksum.iterator();
        while(checksumIterator.hasNext()){
            Checksum checksum = checksumIterator.next();
            boolean containsChecksum = false;
            for (Checksum checksum2 : toEnrichChecksums){
                // identical aliases
                if (DefaultChecksumComparator.areEquals(checksum, checksum2)){
                    containsChecksum = true;
                    break;
                }
            }
            // add missing xref not in second list
            if (!containsChecksum){
                toEnrichChecksums.add(checksum);
                if (aliasListener != null){
                    aliasListener.onAddedChecksum(termToEnrich, checksum);
                }
            }
        }
    }

    /**
     * <p>mergeAnnotations.</p>
     *
     * @param termToEnrich a T object.
     * @param toEnrichAnnotations a {@link java.util.Collection} object.
     * @param fetchedAnnotations a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @param annotationListener a {@link psidev.psi.mi.jami.listener.AnnotationsChangeListener} object.
     * @param <T> a T object.
     */
    public static <T extends Object> void mergeAnnotations(T termToEnrich, Collection<Annotation> toEnrichAnnotations, Collection<Annotation> fetchedAnnotations, boolean remove, AnnotationsChangeListener<T> annotationListener){
        Iterator<Annotation> annotIterator = toEnrichAnnotations.iterator();
        // remove aliases in toEnrichAliases that are not in fetchedAliases
        if (remove){
            while(annotIterator.hasNext()){
                Annotation annot = annotIterator.next();

                boolean containsAnnotation = false;
                for (Annotation annot2 : fetchedAnnotations){
                    // identical annot
                    if (DefaultAnnotationComparator.areEquals(annot, annot2)){
                        containsAnnotation = true;
                        break;
                    }
                }
                // remove alias not in second list
                if (!containsAnnotation){
                    annotIterator.remove();
                    if (annotationListener != null){
                        annotationListener.onRemovedAnnotation(termToEnrich, annot);
                    }
                }
            }
        }

        // add annotations from fetchedAnnotatioms that are not in toEnrichAnnotations
        annotIterator = fetchedAnnotations.iterator();
        while(annotIterator.hasNext()){
            Annotation annot = annotIterator.next();
            boolean containsAnnotation = false;
            for (Annotation annot2 : toEnrichAnnotations){
                // identical annot
                if (DefaultAnnotationComparator.areEquals(annot, annot2)){
                    containsAnnotation = true;
                    break;
                }
            }
            // add missing xref not in second list
            if (!containsAnnotation){
                toEnrichAnnotations.add(annot);
                if (annotationListener != null){
                    annotationListener.onAddedAnnotation(termToEnrich, annot);
                }
            }
        }
    }

    /**
     * <p>mergeConfidences.</p>
     *
     * @param termToEnrich a T object.
     * @param toEnrichConfidences a {@link java.util.Collection} object.
     * @param fetchedConfidences a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @param confListener a {@link psidev.psi.mi.jami.listener.ConfidencesChangeListener} object.
     * @param <T> a T object.
     * @param <C> a C object.
     */
    public static <T extends Object, C extends Confidence> void mergeConfidences(T termToEnrich, Collection<C> toEnrichConfidences, Collection<C> fetchedConfidences, boolean remove, ConfidencesChangeListener<T> confListener){
        Iterator<C> confIterator = toEnrichConfidences.iterator();
        // remove confidences in toEnrichConfidences that are not in fetchedConfidences
        if (remove){
            while(confIterator.hasNext()){
                C conf = confIterator.next();

                boolean containsConf = false;
                for (C conf2 : fetchedConfidences){
                    // identical confidence
                    if (DefaultConfidenceComparator.areEquals(conf, conf2)){
                        containsConf = true;
                        break;
                    }
                }
                // remove confidence not in second list
                if (!containsConf){
                    confIterator.remove();
                    if (confListener != null){
                        confListener.onRemovedConfidence(termToEnrich, conf);
                    }
                }
            }
        }

        // add confidences from fetchedConfidences that are not in toEnrichConfidences
        confIterator = fetchedConfidences.iterator();
        while(confIterator.hasNext()){
            C conf = confIterator.next();
            boolean containsConf = false;
            for (C conf2 : toEnrichConfidences){
                // identical confidence
                if (DefaultConfidenceComparator.areEquals(conf, conf2)){
                    containsConf = true;
                    break;
                }
            }
            // add missing confidence not in second list
            if (!containsConf){
                toEnrichConfidences.add(conf);
                if (confListener != null){
                    confListener.onAddedConfidence(termToEnrich, conf);
                }
            }
        }
    }

    /**
     * <p>mergeParameters.</p>
     *
     * @param termToEnrich a T object.
     * @param toEnrichParameters a {@link java.util.Collection} object.
     * @param fetchedParameters a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @param paramListener a {@link psidev.psi.mi.jami.listener.ParametersChangeListener} object.
     * @param <T> a T object.
     * @param <P> a P object.
     */
    public static <T extends Object, P extends Parameter> void mergeParameters(T termToEnrich, Collection<P> toEnrichParameters, Collection<P> fetchedParameters, boolean remove, ParametersChangeListener<T> paramListener){
        Iterator<P> paramIterator = toEnrichParameters.iterator();
        // remove parameters in toEnrichParameters that are not in fetchedParameters
        if (remove){
            while(paramIterator.hasNext()){
                P param = paramIterator.next();

                boolean containsParam = false;
                for (P param2 : fetchedParameters){
                    // identical parameter
                    if (DefaultParameterComparator.areEquals(param, param2)){
                        containsParam = true;
                        break;
                    }
                }
                // remove parameter not in second list
                if (!containsParam){
                    paramIterator.remove();
                    if (paramListener != null){
                        paramListener.onRemovedParameter(termToEnrich, param);
                    }
                }
            }
        }

        // add parameters from fetchedParameters that are not in toEnrichParameters
        paramIterator = fetchedParameters.iterator();
        while(paramIterator.hasNext()){
            P param = paramIterator.next();
            boolean containsParam = false;
            for (P param2 : toEnrichParameters){
                // identical parameter
                if (DefaultParameterComparator.areEquals(param, param2)){
                    containsParam = true;
                    break;
                }
            }
            // add missing parameter not in second list
            if (!containsParam){
                toEnrichParameters.add(param);
                if (paramListener != null){
                    paramListener.onAddedParameter(termToEnrich, param);
                }
            }
        }
    }

    /**
     * <p>mergeParticipants.</p>
     *
     * @param termToEnrich a {@link psidev.psi.mi.jami.model.Interaction} object.
     * @param toEnrichParticipants a {@link java.util.Collection} object.
     * @param fetchedParticipants a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @param interactionListener a {@link psidev.psi.mi.jami.listener.InteractionChangeListener} object.
     * @param participantEnricher a {@link psidev.psi.mi.jami.enricher.ParticipantEnricher} object.
     * @param <P> a P object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public static <P extends Participant> void mergeParticipants(Interaction termToEnrich, Collection<P> toEnrichParticipants, Collection<P> fetchedParticipants, boolean remove, InteractionChangeListener interactionListener,
                                                                 ParticipantEnricher participantEnricher) throws EnricherException {
        Iterator<P> partIterator = toEnrichParticipants.iterator();
        // remove participants in toEnrichParticipants that are not in fetchedParticipants
        if (remove){
            while(partIterator.hasNext()){
                P part = partIterator.next();

                boolean containsPart = false;
                for (P part2 : fetchedParticipants){
                    // identical participant
                    if (part == part2){
                        containsPart = true;
                        break;
                    }
                }
                // remove participant not in second list
                if (!containsPart){
                    partIterator.remove();
                    part.setInteraction(null);
                    if (interactionListener != null){
                        interactionListener.onRemovedParticipant(termToEnrich, part);
                    }
                }
            }
        }

        // add confidences from fetchedConfidences that are not in toEnrichConfidences
        partIterator = fetchedParticipants.iterator();
        while(partIterator.hasNext()){
            P part = partIterator.next();
            boolean containsPart = false;
            for (P part2 : toEnrichParticipants){
                // identical participants
                if (part == part2){
                    containsPart = true;
                    if (participantEnricher != null){
                        participantEnricher.enrich(part2, part);
                    }
                    break;
                }
            }
            // add missing confidence not in second list
            if (!containsPart){
                termToEnrich.addParticipant(part);
                if (interactionListener != null){
                    interactionListener.onAddedParticipant(termToEnrich, part);
                }
            }
        }
    }

    /**
     * <p>mergeParticipantCandidates.</p>
     *
     * @param termToEnrich a {@link psidev.psi.mi.jami.model.ParticipantPool} object.
     * @param toEnrichParticipants a {@link java.util.Collection} object.
     * @param fetchedParticipants a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @param poolListener a {@link psidev.psi.mi.jami.listener.ParticipantPoolChangeListener} object.
     * @param participantEnricher a {@link psidev.psi.mi.jami.enricher.EntityEnricher} object.
     * @param <P> a P object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public static <P extends ParticipantCandidate> void mergeParticipantCandidates(ParticipantPool termToEnrich, Collection<P> toEnrichParticipants, Collection<P> fetchedParticipants, boolean remove, ParticipantPoolChangeListener poolListener,
                                                                 EntityEnricher participantEnricher) throws EnricherException {
        Iterator<P> partIterator = toEnrichParticipants.iterator();
        // remove participants in toEnrichParticipants that are not in fetchedParticipants
        if (remove){
            while(partIterator.hasNext()){
                P part = partIterator.next();

                boolean containsPart = false;
                for (P part2 : fetchedParticipants){
                    // identical participant
                    if (part == part2){
                        containsPart = true;
                        break;
                    }
                }
                // remove participant not in second list
                if (!containsPart){
                    partIterator.remove();
                    if (poolListener != null){
                        poolListener.onRemovedCandidate(termToEnrich, part);
                    }
                }
            }
        }

        // add confidences from fetchedConfidences that are not in toEnrichConfidences
        partIterator = fetchedParticipants.iterator();
        while(partIterator.hasNext()){
            P part = partIterator.next();
            boolean containsPart = false;
            for (P part2 : toEnrichParticipants){
                // identical participants
                if (part == part2){
                    containsPart = true;
                    if (participantEnricher != null){
                        participantEnricher.enrich(part2, part);
                    }
                    break;
                }
            }
            // add missing confidence not in second list
            if (!containsPart){
                termToEnrich.add(part);
                if (poolListener != null){
                    poolListener.onAddedCandidate(termToEnrich, part);
                }
            }
        }
    }

    /**
     * <p>mergeCausalRelationships.</p>
     *
     * @param termToEnrich a {@link psidev.psi.mi.jami.model.Entity} object.
     * @param toEnrichRelationships a {@link java.util.Collection} object.
     * @param fetchedRelationships a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @param entityListener a {@link psidev.psi.mi.jami.listener.EntityChangeListener} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public static void mergeCausalRelationships(Entity termToEnrich, Collection<CausalRelationship> toEnrichRelationships,
                                                Collection<CausalRelationship> fetchedRelationships,
                                                boolean remove, EntityChangeListener entityListener) throws EnricherException {
        Iterator<CausalRelationship> relationshipIterator = toEnrichRelationships.iterator();
        if (remove){
            while(relationshipIterator.hasNext()){
                CausalRelationship relationship = relationshipIterator.next();

                boolean containsRelationship = false;
                for (CausalRelationship relation2 : fetchedRelationships){
                    // identical participant
                    if (relationship == relation2){
                        containsRelationship = true;
                        break;
                    }
                }
                if (!containsRelationship){
                    relationshipIterator.remove();
                    if (entityListener != null){
                        entityListener.onRemovedCausalRelationship(termToEnrich, relationship);
                    }
                }
            }
        }

        relationshipIterator = fetchedRelationships.iterator();
        while(relationshipIterator.hasNext()){
            CausalRelationship relation1 = relationshipIterator.next();
            boolean containsRelationship = false;
            for (CausalRelationship relation2 : toEnrichRelationships){
                if (relation1 == relation2){
                    containsRelationship = true;
                    break;
                }
            }
            if (!containsRelationship){
                termToEnrich.getCausalRelationships().add(relation1);
                if (entityListener != null){
                    entityListener.onAddedCausalRelationship(termToEnrich, relation1);
                }
            }
        }
    }

    /**
     * <p>mergeFeatures.</p>
     *
     * @param termToEnrich a {@link psidev.psi.mi.jami.model.Entity} object.
     * @param toEnrichFeatures a {@link java.util.Collection} object.
     * @param fetchedFeatures a {@link java.util.Collection} object.
     * @param remove a boolean.
     * @param entityListener a {@link psidev.psi.mi.jami.listener.EntityChangeListener} object.
     * @param featureEnricher a {@link psidev.psi.mi.jami.enricher.FeatureEnricher} object.
     * @param <F> a F object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public static <F extends Feature> void mergeFeatures(Entity termToEnrich, Collection<F> toEnrichFeatures, Collection<F> fetchedFeatures, boolean remove, EntityChangeListener entityListener,
                                                                                   FeatureEnricher<F> featureEnricher) throws EnricherException {
        Iterator<F> featureIterator = toEnrichFeatures.iterator();
        if (remove){
            while(featureIterator.hasNext()){
                F feature = featureIterator.next();

                boolean containsFeature = false;
                for (F feature2 : fetchedFeatures){
                    // identical participant
                    if (feature == feature2){
                        containsFeature = true;
                        break;
                    }
                }
                if (!containsFeature){
                    featureIterator.remove();
                    feature.setParticipant(null);
                    if (entityListener != null){
                        entityListener.onRemovedFeature(termToEnrich, feature);
                    }
                }
            }
        }

        featureIterator = fetchedFeatures.iterator();
        while(featureIterator.hasNext()){
            F feature = featureIterator.next();
            boolean containsFeature = false;
            for (F feature2 : toEnrichFeatures){
                if (feature == feature2){
                    containsFeature = true;
                    if (featureEnricher != null){
                        featureEnricher.enrich(feature2, feature);
                    }
                    break;
                }
            }
            if (!containsFeature){
                termToEnrich.addFeature(feature);
                if (entityListener != null){
                    entityListener.onAddedFeature(termToEnrich, feature);
                }
            }
        }
    }
}
