package psidev.psi.mi.jami.xml.io.writer.elements;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.*;
import psidev.psi.mi.jami.xml.model.extension.AbstractExperimentalInteractor;
import psidev.psi.mi.jami.xml.model.extension.AbstractInferredInteraction;

import javax.xml.stream.XMLStreamWriter;
import java.util.Set;

/**
 * Factory to initialise PSI-XML element writers depending on the version and the interaction object category
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/04/14</pre>
 */
public class PsiXmlElementWriterFactory {

    private static final PsiXmlElementWriterFactory instance = new PsiXmlElementWriterFactory();

    private PsiXmlElementWriterFactory() {
        // nothing to do here
    }

    /**
     * <p>Getter for the field <code>instance</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriterFactory} object.
     */
    public static PsiXmlElementWriterFactory getInstance() {
        return instance;
    }

    /**
     * <p>createInteractionWritersFor.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param xmlType a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @param interactionCategory a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param complexType a {@link psidev.psi.mi.jami.model.ComplexType} object.
     * @param extended a boolean.
     * @param named a boolean.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param confidenceWriters an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} objects.
     * @param checksumWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param interactionTypeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param openCvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param experimentWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter} object.
     * @param availabilityWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param interactorWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @return an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlInteractionWriter} objects.
     */
    public static PsiXmlInteractionWriter[] createInteractionWritersFor(XMLStreamWriter streamWriter, PsiXmlObjectCache objectIndex, PsiXmlVersion version,
                                                                        PsiXmlType xmlType, InteractionCategory interactionCategory,
                                                                        ComplexType complexType, boolean extended, boolean named,
                                                                        PsiXmlElementWriter<Alias> aliasWriter,
                                                                        PsiXmlElementWriter<Annotation> attributeWriter,
                                                                        PsiXmlXrefWriter primaryRefWriter,
                                                                        PsiXmlElementWriter[] confidenceWriters,
                                                                        PsiXmlElementWriter<Checksum> checksumWriter,
                                                                        PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
                                                                        PsiXmlVariableNameWriter<CvTerm> openCvWriter,
                                                                        PsiXmlExperimentWriter experimentWriter,
                                                                        PsiXmlElementWriter<String> availabilityWriter,
                                                                        PsiXmlElementWriter<Interactor> interactorWriter,
                                                                        PsiXmlPublicationWriter publicationWriter) {

        PsiXmlParameterWriter[] parameterWriters = createParameterWriters(streamWriter, extended, objectIndex, version, publicationWriter);
        PsiXmlParticipantWriter[] participantWriters = createParticipantWriter(streamWriter, extended, objectIndex, version, xmlType, interactionCategory,
                aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], interactorWriter, interactionTypeWriter, openCvWriter, parameterWriters[0]);
        PsiXmlElementWriter inferredInteractionWriter = createInferredInteractionWriter(streamWriter, objectIndex);

        if (extended) {
            return createExtendedPsiXmlInteractionWriters(streamWriter, objectIndex, version, xmlType, interactionCategory, complexType,
                    aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters, checksumWriter, interactionTypeWriter,
                    experimentWriter, availabilityWriter, parameterWriters, participantWriters, inferredInteractionWriter, publicationWriter,
                    openCvWriter);
        } else if (named) {
            return createNamedPsiXmlInteractionWriters(streamWriter, objectIndex, version, xmlType, interactionCategory, complexType,
                    aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters, checksumWriter, interactionTypeWriter,
                    experimentWriter, availabilityWriter, parameterWriters, participantWriters, inferredInteractionWriter, publicationWriter,
                    openCvWriter);
        } else {
            return createDefaultPsiXmlInteractionWriters(streamWriter, objectIndex, version, xmlType, interactionCategory, complexType,
                    aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters, checksumWriter, interactionTypeWriter,
                    experimentWriter, availabilityWriter, parameterWriters, participantWriters, inferredInteractionWriter, publicationWriter,
                    openCvWriter);
        }
    }

    /**
     * <p>createDefaultPsiXmlInteractionWriters.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param xmlType a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @param interactionCategory a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param complexType a {@link psidev.psi.mi.jami.model.ComplexType} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param confidenceWriters an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} objects.
     * @param checksumWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param interactionTypeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param experimentWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter} object.
     * @param availabilityWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param parameterWriters an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter} objects.
     * @param participantWriters an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParticipantWriter} objects.
     * @param inferredInteractionWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @param openCvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @return an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlInteractionWriter} objects.
     */
    public static PsiXmlInteractionWriter[] createDefaultPsiXmlInteractionWriters(XMLStreamWriter streamWriter, PsiXmlObjectCache objectIndex,
                                                                                  PsiXmlVersion version, PsiXmlType xmlType,
                                                                                  InteractionCategory interactionCategory, ComplexType complexType,
                                                                                  PsiXmlElementWriter<Alias> aliasWriter,
                                                                                  PsiXmlElementWriter<Annotation> attributeWriter,
                                                                                  PsiXmlXrefWriter primaryRefWriter,
                                                                                  PsiXmlElementWriter[] confidenceWriters,
                                                                                  PsiXmlElementWriter<Checksum> checksumWriter,
                                                                                  PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
                                                                                  PsiXmlExperimentWriter experimentWriter,
                                                                                  PsiXmlElementWriter<String> availabilityWriter,
                                                                                  PsiXmlParameterWriter[] parameterWriters,
                                                                                  PsiXmlParticipantWriter[] participantWriters,
                                                                                  PsiXmlElementWriter inferredInteractionWriter,
                                                                                  PsiXmlPublicationWriter publicationWriter,
                                                                                  PsiXmlVariableNameWriter<CvTerm> openCvWriter) {
        switch (version) {
            case v3_0_0:
                PsiXmlElementWriter<Set<Feature>> bindingFeaturesWriter = createBindingFeaturesWriter(streamWriter, objectIndex);
                PsiXmlCausalRelationshipWriter relationshipWriter = createCausalRelationshipWriter(streamWriter, false, objectIndex,
                        openCvWriter);
                PsiXmlElementWriter<Preassembly> preassemblyWriter = createPreassemblyWriter(streamWriter, false, objectIndex, interactionTypeWriter,
                        attributeWriter, publicationWriter);
                PsiXmlElementWriter<Allostery> allosteryWriter = createAllosteryWriter(streamWriter, false, objectIndex, interactionTypeWriter,
                        attributeWriter, publicationWriter);

                switch (xmlType) {
                    case compact:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlModelledInteractionWriter compactModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter30(compactModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter30(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, compactModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                    case complex:
                                        return new PsiXmlInteractionWriter[]{compactModelledWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                        }

                    default:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlModelledInteractionWriter defaultModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter30(defaultModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter30(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, defaultModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                    case complex:
                                        return new PsiXmlInteractionWriter[]{defaultModelledWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                        }
                }

            case v2_5_3:
                switch (xmlType) {
                    case compact:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlModelledInteractionWriter compactModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(compactModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, compactModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlBasicBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlBasicBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{compactModelledWriter, compactModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, compactModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlBasicInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlBasicInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                        }

                    default:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlModelledInteractionWriter defaultModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(defaultModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, defaultModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlBasicBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlBasicBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{defaultModelledWriter, defaultModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, defaultModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlBasicInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlBasicInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                        }
                }

            case v2_5_4:
            default:
                switch (xmlType) {
                    case compact:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlModelledInteractionWriter compactModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(compactModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, compactModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlBasicBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlBasicBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{compactModelledWriter, compactModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, compactModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlBasicInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlBasicInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                        }

                    default:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlModelledInteractionWriter defaultModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(defaultModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, defaultModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlBasicBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlBasicBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{defaultModelledWriter, defaultModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, defaultModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlBasicInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlBasicInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                        }
                }
        }
    }

    /**
     * <p>createNamedPsiXmlInteractionWriters.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param xmlType a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @param interactionCategory a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param complexType a {@link psidev.psi.mi.jami.model.ComplexType} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param confidenceWriters an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} objects.
     * @param checksumWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param interactionTypeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param experimentWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter} object.
     * @param availabilityWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param parameterWriters an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter} objects.
     * @param participantWriters an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParticipantWriter} objects.
     * @param inferredInteractionWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @param openCvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @return an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlInteractionWriter} objects.
     */
    public static PsiXmlInteractionWriter[] createNamedPsiXmlInteractionWriters(XMLStreamWriter streamWriter, PsiXmlObjectCache objectIndex,
                                                                                PsiXmlVersion version, PsiXmlType xmlType,
                                                                                InteractionCategory interactionCategory, ComplexType complexType,
                                                                                PsiXmlElementWriter<Alias> aliasWriter,
                                                                                PsiXmlElementWriter<Annotation> attributeWriter,
                                                                                PsiXmlXrefWriter primaryRefWriter,
                                                                                PsiXmlElementWriter[] confidenceWriters,
                                                                                PsiXmlElementWriter<Checksum> checksumWriter,
                                                                                PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
                                                                                PsiXmlExperimentWriter experimentWriter,
                                                                                PsiXmlElementWriter<String> availabilityWriter,
                                                                                PsiXmlParameterWriter[] parameterWriters,
                                                                                PsiXmlParticipantWriter[] participantWriters,
                                                                                PsiXmlElementWriter inferredInteractionWriter,
                                                                                PsiXmlPublicationWriter publicationWriter,
                                                                                PsiXmlVariableNameWriter<CvTerm> openCvWriter) {
        switch (version) {
            case v3_0_0:
                PsiXmlElementWriter<Set<Feature>> bindingFeaturesWriter = createBindingFeaturesWriter(streamWriter, objectIndex);
                PsiXmlCausalRelationshipWriter relationshipWriter = createCausalRelationshipWriter(streamWriter, false, objectIndex,
                        openCvWriter);
                PsiXmlElementWriter<Preassembly> preassemblyWriter = createPreassemblyWriter(streamWriter, false, objectIndex, interactionTypeWriter,
                        attributeWriter, publicationWriter);
                PsiXmlElementWriter<Allostery> allosteryWriter = createAllosteryWriter(streamWriter, false, objectIndex, interactionTypeWriter,
                        attributeWriter, publicationWriter);

                switch (xmlType) {
                    case compact:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlNamedModelledInteractionWriter compactModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlNamedModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter30(compactModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlNamedModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlNamedModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter30(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, compactModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlNamedBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlNamedBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                    case complex:
                                        return new PsiXmlInteractionWriter[]{compactModelledWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlNamedInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlNamedInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                        }

                    default:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlNamedModelledInteractionWriter defaultModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlNamedModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter30(defaultModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlNamedModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlNamedModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter30(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, defaultModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlNamedBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlNamedBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                    case complex:
                                        return new PsiXmlInteractionWriter[]{defaultModelledWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlNamedInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlNamedInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                        }
                }

            case v2_5_3:
                switch (xmlType) {
                    case compact:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedModelledInteractionWriter compactModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(compactModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, compactModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{compactModelledWriter, compactModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, compactModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml253.XmlNamedInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                        }

                    default:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedModelledInteractionWriter defaultModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(defaultModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, defaultModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{defaultModelledWriter, defaultModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, defaultModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml253.XmlNamedInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                        }
                }

            case v2_5_4:
            default:
                switch (xmlType) {
                    case compact:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedModelledInteractionWriter compactModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(compactModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, compactModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{compactModelledWriter, compactModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, compactModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.xml254.XmlNamedInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                        }

                    default:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedModelledInteractionWriter defaultModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(defaultModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, defaultModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{defaultModelledWriter, defaultModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, defaultModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.xml254.XmlNamedInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                        }
                }
        }
    }

    /**
     * <p>createExtendedPsiXmlInteractionWriters.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param xmlType a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @param interactionCategory a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param complexType a {@link psidev.psi.mi.jami.model.ComplexType} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param confidenceWriters an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} objects.
     * @param checksumWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param interactionTypeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param experimentWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter} object.
     * @param availabilityWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param parameterWriters an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter} objects.
     * @param participantWriters an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParticipantWriter} objects.
     * @param inferredInteractionWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @param openCvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @return an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlInteractionWriter} objects.
     */
    public static PsiXmlInteractionWriter[] createExtendedPsiXmlInteractionWriters(XMLStreamWriter streamWriter, PsiXmlObjectCache objectIndex,
                                                                                   PsiXmlVersion version, PsiXmlType xmlType,
                                                                                   InteractionCategory interactionCategory, ComplexType complexType,
                                                                                   PsiXmlElementWriter<Alias> aliasWriter,
                                                                                   PsiXmlElementWriter<Annotation> attributeWriter,
                                                                                   PsiXmlXrefWriter primaryRefWriter,
                                                                                   PsiXmlElementWriter[] confidenceWriters,
                                                                                   PsiXmlElementWriter<Checksum> checksumWriter,
                                                                                   PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
                                                                                   PsiXmlExperimentWriter experimentWriter,
                                                                                   PsiXmlElementWriter<String> availabilityWriter,
                                                                                   PsiXmlParameterWriter[] parameterWriters,
                                                                                   PsiXmlParticipantWriter[] participantWriters,
                                                                                   PsiXmlElementWriter inferredInteractionWriter,
                                                                                   PsiXmlPublicationWriter publicationWriter,
                                                                                   PsiXmlVariableNameWriter<CvTerm> openCvWriter) {
        PsiXmlElementWriter<AbstractInferredInteraction> extendedInferredInteractionWriter = createExtendedInferredInteractionWriter(streamWriter,
                objectIndex);

        switch (version) {
            case v3_0_0:
                PsiXmlElementWriter<Set<Feature>> bindingFeaturesWriter = createBindingFeaturesWriter(streamWriter, objectIndex);
                PsiXmlCausalRelationshipWriter relationshipWriter = createCausalRelationshipWriter(streamWriter, false, objectIndex,
                        openCvWriter);
                PsiXmlElementWriter<Preassembly> preassemblyWriter = createPreassemblyWriter(streamWriter, false, objectIndex, interactionTypeWriter,
                        attributeWriter, publicationWriter);
                PsiXmlElementWriter<Allostery> allosteryWriter = createAllosteryWriter(streamWriter, false, objectIndex, interactionTypeWriter,
                        attributeWriter, publicationWriter);

                switch (xmlType) {
                    case compact:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlModelledInteractionWriter compactModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initExtendedModelledWriter30(compactModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initExtendedModelledWriter30(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, compactModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                    case complex:
                                        return new PsiXmlInteractionWriter[]{compactModelledWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                        }

                    default:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlModelledInteractionWriter defaultModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initExtendedModelledWriter30(defaultModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initExtendedModelledWriter30(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter,
                                                bindingFeaturesWriter, relationshipWriter, preassemblyWriter, allosteryWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, defaultModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                    case complex:
                                        return new PsiXmlInteractionWriter[]{defaultModelledWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter30(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, relationshipWriter, streamWriter, objectIndex, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                        }
                }

            case v2_5_3:
                switch (xmlType) {
                    case compact:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlModelledInteractionWriter compactModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(compactModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);
                        compactModelledWriter.setXmlInferredInteractionWriter(extendedInferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);
                                        modelledWriter.setXmlInferredInteractionWriter(extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, compactModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlBasicBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlBasicBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);
                                        basicWriter.setXmlInferredInteractionWriter(extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{compactModelledWriter, compactModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, compactModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlBasicInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlBasicInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);
                                        basicWriter.setXmlInferredInteractionWriter(extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                        }

                    default:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlModelledInteractionWriter defaultModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(defaultModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);
                        defaultModelledWriter.setXmlInferredInteractionWriter(extendedInferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);
                                        modelledWriter.setXmlInferredInteractionWriter(extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, defaultModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlBasicBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlBasicBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);
                                        basicWriter.setXmlInferredInteractionWriter(extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{defaultModelledWriter, defaultModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, defaultModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlBasicInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlBasicInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);
                                        basicWriter.setXmlInferredInteractionWriter(extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                        }
                }


            case v2_5_4:
            default:
                switch (xmlType) {
                    case compact:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlModelledInteractionWriter compactModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(compactModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, compactModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlBasicBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlBasicBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{compactModelledWriter, compactModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, compactModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlBasicInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlBasicInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, compactModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, compactModelledWriter};
                                }
                        }

                    default:
                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlModelledInteractionWriter defaultModelledWriter =
                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlModelledInteractionWriter(streamWriter, objectIndex);

                        initModelledWriter(defaultModelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                        switch (complexType) {
                            case binary:
                                switch (interactionCategory) {
                                    case modelled:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlModelledBinaryInteractionWriter modelledWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlModelledBinaryInteractionWriter(streamWriter, objectIndex);
                                        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{modelledWriter, defaultModelledWriter};
                                    case complex:
                                        throw new IllegalArgumentException("Cannot create a XML complex writer for binary interactions");
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlBasicBinaryInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlBasicBinaryInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlBinaryInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlBinaryInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                            default:
                                switch (interactionCategory) {
                                    case modelled:
                                        return new PsiXmlInteractionWriter[]{defaultModelledWriter, defaultModelledWriter};
                                    case complex:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlComplexWriter complexWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlComplexWriter(streamWriter, objectIndex);
                                        initModelledWriter(complexWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriters[1], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[1], participantWriters[1], inferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{complexWriter, defaultModelledWriter};
                                    case basic:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlBasicInteractionWriter basicWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlBasicInteractionWriter(streamWriter, objectIndex);
                                        initWriter(basicWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                                                inferredInteractionWriter, participantWriters[0]);

                                        return new PsiXmlInteractionWriter[]{basicWriter, defaultModelledWriter};
                                    default:
                                        psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlInteractionEvidenceWriter evidenceWriter =
                                                new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlInteractionEvidenceWriter(streamWriter, objectIndex);
                                        initExtendedEvidenceWriter(evidenceWriter, attributeWriter, primaryRefWriter, confidenceWriters[0], checksumWriter,
                                                interactionTypeWriter, experimentWriter, parameterWriters[0], participantWriters[0], inferredInteractionWriter,
                                                availabilityWriter, extendedInferredInteractionWriter);

                                        return new PsiXmlInteractionWriter[]{evidenceWriter, defaultModelledWriter};
                                }
                        }
                }
        }
    }

    /**
     * <p>createAliasWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Alias> createAliasWriter(XMLStreamWriter streamWriter) {
        return new XmlAliasWriter(streamWriter);
    }

    /**
     * <p>createAnnotationWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Annotation> createAnnotationWriter(XMLStreamWriter streamWriter) {
        return new XmlAnnotationWriter(streamWriter);
    }

    /**
     * <p>createXrefWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param annotationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     */
    public static PsiXmlXrefWriter createXrefWriter(XMLStreamWriter streamWriter, boolean extended,
                                                    PsiXmlElementWriter<Annotation> annotationWriter) {
        PsiXmlXrefWriter writer;
        if (extended) {
            writer = new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlDbXrefWriter(streamWriter);
            ((psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlDbXrefWriter) writer).setAnnotationWriter(annotationWriter);
        } else {
            writer = new XmlDbXrefWriter(streamWriter);
        }
        return writer;
    }

    /**
     * <p>createPublicationWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     */
    public static PsiXmlPublicationWriter createPublicationWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                  PsiXmlElementWriter<Annotation> attributeWriter, PsiXmlXrefWriter primaryRefWriter,
                                                                  PsiXmlVersion version) {
        PsiXmlPublicationWriter publicationWriter;
        if (extended) {
            switch (version) {
                case v3_0_0:
                    publicationWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlPublicationWriter(streamWriter);
                    ((psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlPublicationWriter) publicationWriter)
                            .setAttributeWriter(attributeWriter);
                    ((psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlPublicationWriter) publicationWriter)
                            .setXrefWriter(primaryRefWriter);
                    break;
                case v2_5_3:
                case v2_5_4:
                default:
                    publicationWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlPublicationWriter(streamWriter);
                    ((psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlPublicationWriter) publicationWriter)
                            .setAttributeWriter(attributeWriter);
                    ((psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlPublicationWriter) publicationWriter)
                            .setXrefWriter(primaryRefWriter);
                    break;
            }
        } else {
            switch (version) {
                case v3_0_0:
                    publicationWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlPublicationWriter(streamWriter);
                    ((psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlPublicationWriter) publicationWriter)
                            .setAttributeWriter(attributeWriter);
                    ((psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlPublicationWriter) publicationWriter)
                            .setXrefWriter(primaryRefWriter);
                    break;
                case v2_5_3:
                case v2_5_4:
                default:
                    publicationWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlPublicationWriter(streamWriter);
                    ((psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlPublicationWriter) publicationWriter)
                            .setAttributeWriter(attributeWriter);
                    ((psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlPublicationWriter) publicationWriter)
                            .setXrefWriter(primaryRefWriter);
                    break;
            }
        }
        return publicationWriter;
    }

    /**
     * <p>createOpenCvWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public static PsiXmlVariableNameWriter<CvTerm> createOpenCvWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                      PsiXmlElementWriter<Alias> aliasWriter, PsiXmlElementWriter<Annotation> attributeWriter,
                                                                      PsiXmlXrefWriter primaryRefWriter) {
        if (extended) {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlOpenCvTermWriter cellTypeWriter =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlOpenCvTermWriter(streamWriter);
            cellTypeWriter.setAttributeWriter(attributeWriter);
            cellTypeWriter.setAliasWriter(aliasWriter);
            cellTypeWriter.setXrefWriter(primaryRefWriter);
            return cellTypeWriter;
        } else {
            XmlOpenCvTermWriter cellTypeWriter = new XmlOpenCvTermWriter(streamWriter);
            cellTypeWriter.setAttributeWriter(attributeWriter);
            cellTypeWriter.setAliasWriter(aliasWriter);
            cellTypeWriter.setXrefWriter(primaryRefWriter);
            return cellTypeWriter;
        }
    }

    /**
     * <p>createExperimentalCvWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public static PsiXmlVariableNameWriter<CvTerm> createExperimentalCvWriter(XMLStreamWriter streamWriter,
                                                                              PsiXmlVersion version,
                                                                              boolean extended,
                                                                              PsiXmlObjectCache objectIndex,
                                                                              PsiXmlElementWriter<Alias> aliasWriter,
                                                                              PsiXmlXrefWriter primaryRefWriter) {
        if (extended) {
            XmlCvTermWriter cellTypeWriter;
            switch (version) {
                case v3_0_0:
                    cellTypeWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlExperimentalCvTermWriter(streamWriter, objectIndex);
                    break;
                case v2_5_3:
                    cellTypeWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlExperimentalCvTermWriter(streamWriter, objectIndex);
                    break;
                case v2_5_4:
                default:
                    cellTypeWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlExperimentalCvTermWriter(streamWriter, objectIndex);
                    break;
            }
            cellTypeWriter.setAliasWriter(aliasWriter);
            cellTypeWriter.setXrefWriter(primaryRefWriter);
            return cellTypeWriter;
        } else {
            XmlCvTermWriter cellTypeWriter = new XmlCvTermWriter(streamWriter);
            cellTypeWriter.setAliasWriter(aliasWriter);
            cellTypeWriter.setXrefWriter(primaryRefWriter);
            return cellTypeWriter;
        }
    }

    /**
     * <p>createCvWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public static PsiXmlVariableNameWriter<CvTerm> createCvWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                  PsiXmlElementWriter<Alias> aliasWriter, PsiXmlXrefWriter primaryRefWriter) {
        if (extended) {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlCvTermWriter tissueWriter =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlCvTermWriter(streamWriter);
            tissueWriter.setAliasWriter(aliasWriter);
            tissueWriter.setXrefWriter(primaryRefWriter);
            return tissueWriter;
        } else {
            XmlCvTermWriter tissueWriter = new XmlCvTermWriter(streamWriter);
            tissueWriter.setAliasWriter(aliasWriter);
            tissueWriter.setXrefWriter(primaryRefWriter);
            return tissueWriter;
        }
    }

    /**
     * <p>createHostOrganismWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param annotationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param xrefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param openCvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Organism> createHostOrganismWriter(XMLStreamWriter streamWriter,
                                                                         PsiXmlVersion version,
                                                                         boolean extended,
                                                                         PsiXmlObjectCache objectIndex, PsiXmlElementWriter<Alias> aliasWriter,
                                                                         PsiXmlElementWriter<Annotation> annotationWriter,
                                                                         PsiXmlXrefWriter xrefWriter,
                                                                         PsiXmlVariableNameWriter<CvTerm> openCvWriter) {
        if (extended) {
            XmlHostOrganismWriter hostWriter;
            switch (version) {
                case v3_0_0:
                    hostWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlHostOrganismWriter(streamWriter, objectIndex);
                    break;
                case v2_5_3:
                    hostWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlHostOrganismWriter(streamWriter, objectIndex);
                    break;
                case v2_5_4:
                default:
                    hostWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlHostOrganismWriter(streamWriter, objectIndex);
                    break;
            }
            hostWriter.setAliasWriter(aliasWriter);
            hostWriter.setCvWriter(openCvWriter);
            return hostWriter;
        } else {
            XmlHostOrganismWriter hostWriter = new XmlHostOrganismWriter(streamWriter);
            hostWriter.setAliasWriter(aliasWriter);
            hostWriter.setCvWriter(openCvWriter);
            return hostWriter;
        }
    }

    //TODO check if the organism can be extended, becasue for schema definition looks like it is not
    /**
     * <p>createOrganismWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param annotationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param xrefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param openCvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Organism> createOrganismWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                     PsiXmlElementWriter<Alias> aliasWriter,
                                                                     PsiXmlElementWriter<Annotation> annotationWriter,
                                                                     PsiXmlXrefWriter xrefWriter,
                                                                     PsiXmlVariableNameWriter<CvTerm> openCvWriter) {
        if (extended){
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlOrganismWriter hostWriter =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlOrganismWriter(streamWriter);
            hostWriter.setAliasWriter(aliasWriter);
            hostWriter.setCvWriter(openCvWriter);
            return hostWriter;
        }
        else{
            XmlOrganismWriter hostWriter = new XmlOrganismWriter(streamWriter);
            hostWriter.setAliasWriter(aliasWriter);
            hostWriter.setCvWriter(openCvWriter);
            return hostWriter;
        }
    }

    /**
     * <p>createChecksumWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Checksum> createChecksumWriter(XMLStreamWriter streamWriter) {
        return new XmlChecksumWriter(streamWriter);
    }

    /**
     * <p>createConfidenceWriters.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param confidenceTypeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @return an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} objects.
     */
    public static PsiXmlElementWriter<Confidence>[] createConfidenceWriters(XMLStreamWriter streamWriter, boolean extended,
                                                                            PsiXmlObjectCache objectIndex, PsiXmlVersion version,
                                                                            PsiXmlVariableNameWriter<CvTerm> confidenceTypeWriter,
                                                                            PsiXmlPublicationWriter publicationWriter) {
        if (extended) {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlConfidenceWriter confWriter30 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlConfidenceWriter(streamWriter, objectIndex);
                    confWriter30.setTypeWriter(confidenceTypeWriter);
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlModelledConfidenceWriter modelledConfWriter =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlModelledConfidenceWriter(streamWriter);
                    modelledConfWriter.setTypeWriter(confidenceTypeWriter);
                    modelledConfWriter.setPublicationWriter(publicationWriter);
                    return new PsiXmlElementWriter[]{confWriter30, modelledConfWriter};
                case v2_5_3:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlConfidenceWriter confWriter253 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlConfidenceWriter(streamWriter, objectIndex);
                    return new PsiXmlElementWriter[]{confWriter253, confWriter253};
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlConfidenceWriter confWriter254 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlConfidenceWriter(streamWriter, objectIndex);
                    return new PsiXmlElementWriter[]{confWriter254, confWriter254};
            }
        } else {
            XmlConfidenceWriter confWriter = new XmlConfidenceWriter(streamWriter);
            confWriter.setTypeWriter(confidenceTypeWriter);
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlModelledConfidenceWriter modelledConfWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlModelledConfidenceWriter(streamWriter);
                    modelledConfWriter.setTypeWriter(confidenceTypeWriter);
                    modelledConfWriter.setPublicationWriter(publicationWriter);
                    return new PsiXmlElementWriter[]{confWriter, modelledConfWriter};
                case v2_5_3:
                case v2_5_4:
                default:
                    return new PsiXmlElementWriter[]{confWriter, confWriter};
            }
        }
    }

    /**
     * <p>createResultingSequenceWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param refWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<ResultingSequence> createResultingSequenceWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                                       PsiXmlXrefWriter refWriter) {
        if (extended) {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlResultingSequenceWriter writer =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlResultingSequenceWriter(streamWriter);
            writer.setXrefWriter(refWriter);
            return writer;
        } else {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlResultingSequenceWriter writer = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlResultingSequenceWriter(streamWriter);
            writer.setXrefWriter(refWriter);
            return writer;
        }
    }

    /**
     * <p>createCooperativityEvidenceWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param expanded a boolean.
     * @param cvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<CooperativityEvidence> createCooperativityEvidenceWriter(XMLStreamWriter streamWriter, boolean expanded,
                                                                                               PsiXmlVariableNameWriter<CvTerm> cvWriter,
                                                                                               PsiXmlPublicationWriter publicationWriter) {

        if (expanded) {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlCooperativityEvidenceWriter writer =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlCooperativityEvidenceWriter(streamWriter);
            writer.setCvWriter(cvWriter);
            writer.setPublicationWriter(publicationWriter);
            return writer;
        } else {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlCooperativityEvidenceWriter writer = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlCooperativityEvidenceWriter(streamWriter);
            writer.setCvWriter(cvWriter);
            writer.setPublicationWriter(publicationWriter);
            return writer;
        }
    }

    /**
     * <p>createAllosteryWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param expanded a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param cvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Allostery> createAllosteryWriter(XMLStreamWriter streamWriter, boolean expanded,
                                                                       PsiXmlObjectCache objectIndex, PsiXmlVariableNameWriter<CvTerm> cvWriter,
                                                                       PsiXmlElementWriter<Annotation> attributeWriter,
                                                                       PsiXmlPublicationWriter publicationWriter) {
        if (expanded) {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlAllosteryWriter writer =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlAllosteryWriter(streamWriter, objectIndex);
            writer.setCvWriter(cvWriter);
            writer.setAttributeWriter(attributeWriter);
            writer.setCooperativityEvidenceWriter(createCooperativityEvidenceWriter(streamWriter, expanded, cvWriter, publicationWriter));
            return writer;
        } else {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlAllosteryWriter writer = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlAllosteryWriter(streamWriter, objectIndex);
            writer.setCvWriter(cvWriter);
            writer.setAttributeWriter(attributeWriter);
            writer.setCooperativityEvidenceWriter(createCooperativityEvidenceWriter(streamWriter, expanded, cvWriter, publicationWriter));
            return writer;
        }
    }

    /**
     * <p>createPreassemblyWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param cvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Preassembly> createPreassemblyWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                           PsiXmlObjectCache objectIndex, PsiXmlVariableNameWriter<CvTerm> cvWriter,
                                                                           PsiXmlElementWriter<Annotation> attributeWriter,
                                                                           PsiXmlPublicationWriter publicationWriter) {
        if (extended) {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlPreAssemblyWriter writer =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlPreAssemblyWriter(streamWriter, objectIndex);
            writer.setCvWriter(cvWriter);
            writer.setAttributeWriter(attributeWriter);
            writer.setCooperativityEvidenceWriter(createCooperativityEvidenceWriter(streamWriter, extended, cvWriter, publicationWriter));
            return writer;
        } else {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlPreAssemblyWriter writer = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlPreAssemblyWriter(streamWriter, objectIndex);
            writer.setCvWriter(cvWriter);
            writer.setAttributeWriter(attributeWriter);
            writer.setCooperativityEvidenceWriter(createCooperativityEvidenceWriter(streamWriter, extended, cvWriter, publicationWriter));
            return writer;
        }
    }

    /**
     * <p>createCausalRelationshipWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param cvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlCausalRelationshipWriter} object.
     */
    public static PsiXmlCausalRelationshipWriter createCausalRelationshipWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                                PsiXmlObjectCache objectIndex, PsiXmlVariableNameWriter<CvTerm> cvWriter) {
        if (extended) {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlCausalRelationshipWriter writer =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlCausalRelationshipWriter(streamWriter, objectIndex);
            writer.setCausalStatementWriter(cvWriter);
            return writer;
        } else {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlCausalRelationshipWriter writer = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlCausalRelationshipWriter(streamWriter, objectIndex);
            writer.setCausalStatementWriter(cvWriter);
            return writer;
        }
    }

    /**
     * <p>createVariableParameterValueSetWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<VariableParameterValueSet> createVariableParameterValueSetWriter(XMLStreamWriter streamWriter,
                                                                                                       PsiXmlObjectCache objectIndex) {
        return new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlVariableParameterValueSetWriter(streamWriter, objectIndex);
    }

    /**
     * <p>createVariableParameterValueWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<VariableParameterValue> createVariableParameterValueWriter(XMLStreamWriter streamWriter,
                                                                                                 PsiXmlObjectCache objectIndex) {
        return new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlVariableParameterValueWriter(streamWriter, objectIndex);
    }

    /**
     * <p>createVariableParameterWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param cvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<VariableParameter> createVariableParameterWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                                       PsiXmlObjectCache objectIndex, PsiXmlVariableNameWriter<CvTerm> cvWriter) {
        if (extended) {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlVariableParameterWriter writer =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlVariableParameterWriter(streamWriter, objectIndex);
            writer.setUnitWriter(cvWriter);
            writer.setVariableParameterValueWriter(createVariableParameterValueWriter(streamWriter, objectIndex));
            return writer;
        } else {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlVariableParameterWriter writer = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlVariableParameterWriter(streamWriter, objectIndex);
            writer.setUnitWriter(cvWriter);
            writer.setVariableParameterValueWriter(createVariableParameterValueWriter(streamWriter, objectIndex));
            return writer;
        }
    }

    /**
     * <p>createPositionsWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param statusWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @return an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} objects.
     */
    public static PsiXmlElementWriter<Position>[] createPositionsWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                        PsiXmlVariableNameWriter<CvTerm> statusWriter) {
        if (extended) {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlBeginPositionWriter startWriter =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlBeginPositionWriter(streamWriter);
            startWriter.setStatusWriter(statusWriter);
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlEndPositionWriter endWriter =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlEndPositionWriter(streamWriter);
            endWriter.setStatusWriter(statusWriter);
            return new PsiXmlElementWriter[]{startWriter, endWriter};
        } else {
            XmlBeginPositionWriter startWriter = new XmlBeginPositionWriter(streamWriter);
            startWriter.setStatusWriter(statusWriter);
            XmlEndPositionWriter endWriter = new XmlEndPositionWriter(streamWriter);
            endWriter.setStatusWriter(statusWriter);
            return new PsiXmlElementWriter[]{startWriter, endWriter};
        }
    }

    /**
     * <p>createRangeWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param refWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param statusWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Range> createRangeWriter(XMLStreamWriter streamWriter, boolean extended,
                                                               PsiXmlObjectCache objectIndex, PsiXmlVersion version,
                                                               PsiXmlXrefWriter refWriter, PsiXmlVariableNameWriter<CvTerm> statusWriter) {
        PsiXmlElementWriter<Position>[] positionsWriter = createPositionsWriter(streamWriter, extended, statusWriter);

        if (extended) {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlRangeWriter writer =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlRangeWriter(streamWriter, objectIndex);
                    writer.setResultingSequenceWriter(createResultingSequenceWriter(streamWriter, extended, refWriter));
                    writer.setStartPositionWriter(positionsWriter[0]);
                    writer.setEndPositionWriter(positionsWriter[1]);
                    return writer;
                case v2_5_3:
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlRangeWriter writer2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlRangeWriter(streamWriter);
                    writer2.setStartPositionWriter(positionsWriter[0]);
                    writer2.setEndPositionWriter(positionsWriter[1]);
                    return writer2;
            }
        } else {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlRangeWriter writer =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlRangeWriter(streamWriter, objectIndex);
                    writer.setResultingSequenceWriter(createResultingSequenceWriter(streamWriter, extended, refWriter));
                    writer.setStartPositionWriter(positionsWriter[0]);
                    writer.setEndPositionWriter(positionsWriter[1]);
                    return writer;
                case v2_5_3:
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlRangeWriter writer2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlRangeWriter(streamWriter);
                    writer2.setStartPositionWriter(positionsWriter[0]);
                    writer2.setEndPositionWriter(positionsWriter[1]);
                    return writer2;
            }
        }
    }

    /**
     * <p>createParameterWriters.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @return an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter} objects.
     */
    public static PsiXmlParameterWriter[] createParameterWriters(XMLStreamWriter streamWriter, boolean extended,
                                                                 PsiXmlObjectCache objectIndex, PsiXmlVersion version,
                                                                 PsiXmlPublicationWriter publicationWriter) {
        if (extended) {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlParameterWriter paramWriter =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlParameterWriter(streamWriter, objectIndex);
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlModelledParameterWriter modelledParamWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlModelledParameterWriter(streamWriter, objectIndex);
                    modelledParamWriter.setPublicationWriter(publicationWriter);
                    return new PsiXmlParameterWriter[]{paramWriter, modelledParamWriter};
                case v2_5_3:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlParameterWriter paramWriter2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml253.XmlParameterWriter(streamWriter, objectIndex);
                    return new PsiXmlParameterWriter[]{paramWriter2, paramWriter2};
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlParameterWriter paramWriter3 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.xml254.XmlParameterWriter(streamWriter, objectIndex);
                    return new PsiXmlParameterWriter[]{paramWriter3, paramWriter3};
            }
        } else {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlParameterWriter paramWriter =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlParameterWriter(streamWriter, objectIndex);
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlModelledParameterWriter modelledParamWriter = new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlModelledParameterWriter(streamWriter, objectIndex);
                    modelledParamWriter.setPublicationWriter(publicationWriter);
                    return new PsiXmlParameterWriter[]{paramWriter, modelledParamWriter};
                case v2_5_3:
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlParameterWriter paramWriter2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlParameterWriter(streamWriter, objectIndex);
                    return new PsiXmlParameterWriter[]{paramWriter2, paramWriter2};
            }
        }
    }

    /**
     * <p>createAvailabilityWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<String> createAvailabilityWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache objectIndex) {
        return new XmlAvailabilityWriter(streamWriter, objectIndex);
    }

    /**
     * <p>createInferredInteractionWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Set<Feature>> createInferredInteractionWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache objectIndex) {
        return new XmlInferredInteractionWriter(streamWriter, objectIndex);
    }

    /**
     * <p>createExtendedInferredInteractionWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<AbstractInferredInteraction> createExtendedInferredInteractionWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache objectIndex) {
        return new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlInferredInteractionWriter(streamWriter, objectIndex);
    }

    /**
     * <p>createBindingFeaturesWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Set<Feature>> createBindingFeaturesWriter(XMLStreamWriter streamWriter, PsiXmlObjectCache objectIndex) {
        return new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlBindingFeaturesWriter(streamWriter, objectIndex);
    }

    /**
     * <p>createExperimentalInteractorWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param xmlType a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<AbstractExperimentalInteractor> createExperimentalInteractorWriter(XMLStreamWriter streamWriter,
                                                                                                         PsiXmlVersion version,
                                                                                                         PsiXmlObjectCache objectIndex,
                                                                                                         PsiXmlType xmlType) {
        switch (xmlType) {
            case compact:
                switch (version) {
                    case v3_0_0:
                        return new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlExperimentalInteractorWriter(streamWriter, objectIndex);
                    case v2_5_3:
                    case v2_5_4:
                    default:
                        return new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.XmlExperimentalInteractorWriter(streamWriter, objectIndex);
                }
            default:
                return new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.XmlExperimentalInteractorWriter(streamWriter, objectIndex);
        }
    }

    /**
     * <p>createSourceWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlSourceWriter} object.
     */
    public static PsiXmlSourceWriter createSourceWriter(XMLStreamWriter streamWriter, boolean extended,
                                                        PsiXmlVersion version,
                                                        PsiXmlElementWriter<Alias> aliasWriter, PsiXmlElementWriter<Annotation> attributeWriter,
                                                        PsiXmlXrefWriter primaryRefWriter,
                                                        PsiXmlPublicationWriter publicationWriter) {
        if (extended) {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlSourceWriter sourceWriter =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlSourceWriter(streamWriter);
                    sourceWriter.setXrefWriter(primaryRefWriter);
                    sourceWriter.setAttributeWriter(attributeWriter);
                    sourceWriter.setAliasWriter(aliasWriter);
                    sourceWriter.setPublicationWriter(publicationWriter);
                    return sourceWriter;
                case v2_5_3:
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlSourceWriter sourceWriter2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlSourceWriter(streamWriter);
                    sourceWriter2.setXrefWriter(primaryRefWriter);
                    sourceWriter2.setAttributeWriter(attributeWriter);
                    sourceWriter2.setAliasWriter(aliasWriter);
                    sourceWriter2.setPublicationWriter(publicationWriter);
                    return sourceWriter2;
            }
        } else {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlSourceWriter sourceWriter =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlSourceWriter(streamWriter);
                    sourceWriter.setXrefWriter(primaryRefWriter);
                    sourceWriter.setAttributeWriter(attributeWriter);
                    sourceWriter.setAliasWriter(aliasWriter);
                    sourceWriter.setPublicationWriter(publicationWriter);
                    return sourceWriter;
                case v2_5_3:
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlSourceWriter sourceWriter2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlSourceWriter(streamWriter);
                    sourceWriter2.setXrefWriter(primaryRefWriter);
                    sourceWriter2.setAttributeWriter(attributeWriter);
                    sourceWriter2.setAliasWriter(aliasWriter);
                    sourceWriter2.setPublicationWriter(publicationWriter);
                    return sourceWriter2;
            }
        }

    }

    /**
     * <p>createExperimentWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param named a boolean.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param publicationWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlPublicationWriter} object.
     * @param nonExperimentalHostOrganismWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param detectionMethodWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param confidenceWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlExperimentWriter} object.
     */
    public static PsiXmlExperimentWriter createExperimentWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                PsiXmlObjectCache objectIndex, PsiXmlVersion version,
                                                                boolean named, PsiXmlElementWriter<Alias> aliasWriter,
                                                                PsiXmlElementWriter<Annotation> attributeWriter,
                                                                PsiXmlXrefWriter primaryRefWriter,
                                                                PsiXmlPublicationWriter publicationWriter,
                                                                PsiXmlElementWriter<Organism> nonExperimentalHostOrganismWriter,
                                                                PsiXmlVariableNameWriter<CvTerm> detectionMethodWriter,
                                                                PsiXmlElementWriter<Confidence> confidenceWriter) {
        if (extended) {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlExperimentWriter expWriter =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlExperimentWriter(streamWriter, objectIndex);
                    expWriter.setXrefWriter(primaryRefWriter);
                    expWriter.setAttributeWriter(attributeWriter);
                    expWriter.setPublicationWriter(publicationWriter);
                    expWriter.setHostOrganismWriter(nonExperimentalHostOrganismWriter);
                    expWriter.setDetectionMethodWriter(detectionMethodWriter);
                    expWriter.setConfidenceWriter(confidenceWriter);
                    expWriter.setAliasWriter(aliasWriter);
                    return expWriter;
                case v2_5_3:
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlExperimentWriter expWriter2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlExperimentWriter(streamWriter, objectIndex);
                    expWriter2.setXrefWriter(primaryRefWriter);
                    expWriter2.setAttributeWriter(attributeWriter);
                    expWriter2.setPublicationWriter(publicationWriter);
                    expWriter2.setHostOrganismWriter(nonExperimentalHostOrganismWriter);
                    expWriter2.setDetectionMethodWriter(detectionMethodWriter);
                    expWriter2.setConfidenceWriter(confidenceWriter);
                    expWriter2.setAliasWriter(aliasWriter);
                    return expWriter2;
            }
        } else if (named) {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlNamedExperimentWriter expWriter =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlNamedExperimentWriter(streamWriter, objectIndex);
                    expWriter.setXrefWriter(primaryRefWriter);
                    expWriter.setAttributeWriter(attributeWriter);
                    expWriter.setPublicationWriter(publicationWriter);
                    expWriter.setHostOrganismWriter(nonExperimentalHostOrganismWriter);
                    expWriter.setDetectionMethodWriter(detectionMethodWriter);
                    expWriter.setConfidenceWriter(confidenceWriter);
                    expWriter.setAliasWriter(aliasWriter);
                    return expWriter;
                case v2_5_3:
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlNamedExperimentWriter expWriter2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlNamedExperimentWriter(streamWriter, objectIndex);
                    expWriter2.setXrefWriter(primaryRefWriter);
                    expWriter2.setAttributeWriter(attributeWriter);
                    expWriter2.setPublicationWriter(publicationWriter);
                    expWriter2.setHostOrganismWriter(nonExperimentalHostOrganismWriter);
                    expWriter2.setDetectionMethodWriter(detectionMethodWriter);
                    expWriter2.setConfidenceWriter(confidenceWriter);
                    expWriter2.setAliasWriter(aliasWriter);
                    return expWriter2;
            }
        } else {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlExperimentWriter expWriter =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlExperimentWriter(streamWriter, objectIndex);
                    expWriter.setXrefWriter(primaryRefWriter);
                    expWriter.setAttributeWriter(attributeWriter);
                    expWriter.setPublicationWriter(publicationWriter);
                    expWriter.setHostOrganismWriter(nonExperimentalHostOrganismWriter);
                    expWriter.setDetectionMethodWriter(detectionMethodWriter);
                    expWriter.setConfidenceWriter(confidenceWriter);
                    return expWriter;
                case v2_5_3:
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlExperimentWriter expWriter2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlExperimentWriter(streamWriter, objectIndex);
                    expWriter2.setXrefWriter(primaryRefWriter);
                    expWriter2.setAttributeWriter(attributeWriter);
                    expWriter2.setPublicationWriter(publicationWriter);
                    expWriter2.setHostOrganismWriter(nonExperimentalHostOrganismWriter);
                    expWriter2.setDetectionMethodWriter(detectionMethodWriter);
                    expWriter2.setConfidenceWriter(confidenceWriter);
                    return expWriter2;
            }
        }
    }

    /**
     * <p>createInteractorWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param interactorTypeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param organismWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param checksumWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public static PsiXmlElementWriter<Interactor> createInteractorWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                         PsiXmlObjectCache objectIndex, PsiXmlElementWriter<Alias> aliasWriter,
                                                                         PsiXmlElementWriter<Annotation> attributeWriter,
                                                                         PsiXmlXrefWriter primaryRefWriter,
                                                                         PsiXmlVariableNameWriter<CvTerm> interactorTypeWriter,
                                                                         PsiXmlElementWriter<Organism> organismWriter,
                                                                         PsiXmlElementWriter<Checksum> checksumWriter) {
        if (extended) {
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlInteractorWriter interactorWriter =
                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.XmlInteractorWriter(streamWriter, objectIndex);
            interactorWriter.setAliasWriter(aliasWriter);
            interactorWriter.setAttributeWriter(attributeWriter);
            interactorWriter.setXrefWriter(primaryRefWriter);
            interactorWriter.setInteractorTypeWriter(interactorTypeWriter);
            interactorWriter.setOrganismWriter(organismWriter);
            interactorWriter.setChecksumWriter(checksumWriter);
            return interactorWriter;
        } else {
            XmlInteractorWriter interactorWriter = new XmlInteractorWriter(streamWriter, objectIndex);
            interactorWriter.setAliasWriter(aliasWriter);
            interactorWriter.setAttributeWriter(attributeWriter);
            interactorWriter.setXrefWriter(primaryRefWriter);
            interactorWriter.setInteractorTypeWriter(interactorTypeWriter);
            interactorWriter.setOrganismWriter(organismWriter);
            interactorWriter.setChecksumWriter(checksumWriter);
            return interactorWriter;
        }
    }

    /**
     * <p>createFeatureWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param category a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param featureTypeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param parameterWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter} object.
     * @param <F> a F object.
     * @return an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} objects.
     */
    public static <F extends Feature> PsiXmlElementWriter<F>[] createFeatureWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                                   PsiXmlObjectCache objectIndex, PsiXmlVersion version,
                                                                                   InteractionCategory category, PsiXmlElementWriter<Alias> aliasWriter,
                                                                                   PsiXmlElementWriter<Annotation> attributeWriter,
                                                                                   PsiXmlXrefWriter primaryRefWriter,
                                                                                   PsiXmlVariableNameWriter<CvTerm> featureTypeWriter,
                                                                                   PsiXmlParameterWriter parameterWriter) {
        PsiXmlElementWriter<Range> rangeWriter = createRangeWriter(streamWriter, extended, objectIndex, version, primaryRefWriter,
                featureTypeWriter);

        if (extended) {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlModelledFeatureWriter modelledWriter =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlModelledFeatureWriter(streamWriter, objectIndex);
                    modelledWriter.setAliasWriter(aliasWriter);
                    modelledWriter.setAttributeWriter(attributeWriter);
                    modelledWriter.setFeatureTypeWriter(featureTypeWriter);
                    modelledWriter.setRangeWriter(rangeWriter);
                    modelledWriter.setXrefWriter(primaryRefWriter);

                    switch (category) {
                        case modelled:
                            return new PsiXmlElementWriter[]{modelledWriter, modelledWriter};
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlFeatureEvidenceWriter writer =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.XmlFeatureEvidenceWriter(streamWriter, objectIndex);
                            writer.setAliasWriter(aliasWriter);
                            writer.setAttributeWriter(attributeWriter);
                            writer.setFeatureTypeWriter(featureTypeWriter);
                            writer.setRangeWriter(rangeWriter);
                            writer.setXrefWriter(primaryRefWriter);
                            writer.setParameterWriter(parameterWriter);

                            return new PsiXmlElementWriter[]{writer, modelledWriter};
                    }

                case v2_5_3:
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlModelledFeatureWriter modelledWriter2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlModelledFeatureWriter(streamWriter, objectIndex);
                    modelledWriter2.setAliasWriter(aliasWriter);
                    modelledWriter2.setAttributeWriter(attributeWriter);
                    modelledWriter2.setFeatureTypeWriter(featureTypeWriter);
                    modelledWriter2.setRangeWriter(rangeWriter);
                    modelledWriter2.setXrefWriter(primaryRefWriter);

                    switch (category) {
                        case modelled:
                            return new PsiXmlElementWriter[]{modelledWriter2, modelledWriter2};
                        case basic:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlFeatureWriter writer3 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlFeatureWriter(streamWriter, objectIndex);
                            writer3.setAliasWriter(aliasWriter);
                            writer3.setAttributeWriter(attributeWriter);
                            writer3.setFeatureTypeWriter(featureTypeWriter);
                            writer3.setRangeWriter(rangeWriter);
                            writer3.setXrefWriter(primaryRefWriter);

                            return new PsiXmlElementWriter[]{writer3, modelledWriter2};
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlFeatureEvidenceWriter writer2 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml25.XmlFeatureEvidenceWriter(streamWriter, objectIndex);
                            writer2.setAliasWriter(aliasWriter);
                            writer2.setAttributeWriter(attributeWriter);
                            writer2.setFeatureTypeWriter(featureTypeWriter);
                            writer2.setRangeWriter(rangeWriter);
                            writer2.setXrefWriter(primaryRefWriter);

                            return new PsiXmlElementWriter[]{writer2, modelledWriter2};
                    }
            }
        } else {
            switch (version) {
                case v3_0_0:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlModelledFeatureWriter modelledWriter =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlModelledFeatureWriter(streamWriter, objectIndex);
                    modelledWriter.setAliasWriter(aliasWriter);
                    modelledWriter.setAttributeWriter(attributeWriter);
                    modelledWriter.setFeatureTypeWriter(featureTypeWriter);
                    modelledWriter.setRangeWriter(rangeWriter);
                    modelledWriter.setXrefWriter(primaryRefWriter);

                    switch (category) {
                        case modelled:
                            return new PsiXmlElementWriter[]{modelledWriter, modelledWriter};
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlFeatureEvidenceWriter writer =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30.XmlFeatureEvidenceWriter(streamWriter, objectIndex);
                            writer.setAliasWriter(aliasWriter);
                            writer.setAttributeWriter(attributeWriter);
                            writer.setFeatureTypeWriter(featureTypeWriter);
                            writer.setRangeWriter(rangeWriter);
                            writer.setXrefWriter(primaryRefWriter);
                            writer.setParameterWriter(parameterWriter);

                            return new PsiXmlElementWriter[]{writer, modelledWriter};
                    }

                case v2_5_3:
                case v2_5_4:
                default:
                    psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlModelledFeatureWriter modelledWriter2 =
                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlModelledFeatureWriter(streamWriter, objectIndex);
                    modelledWriter2.setAliasWriter(aliasWriter);
                    modelledWriter2.setAttributeWriter(attributeWriter);
                    modelledWriter2.setFeatureTypeWriter(featureTypeWriter);
                    modelledWriter2.setRangeWriter(rangeWriter);
                    modelledWriter2.setXrefWriter(primaryRefWriter);

                    switch (category) {
                        case modelled:
                            return new PsiXmlElementWriter[]{modelledWriter2, modelledWriter2};
                        case basic:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlFeatureWriter writer3 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlFeatureWriter(streamWriter, objectIndex);
                            writer3.setAliasWriter(aliasWriter);
                            writer3.setAttributeWriter(attributeWriter);
                            writer3.setFeatureTypeWriter(featureTypeWriter);
                            writer3.setRangeWriter(rangeWriter);
                            writer3.setXrefWriter(primaryRefWriter);

                            return new PsiXmlElementWriter[]{writer3, modelledWriter2};
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlFeatureEvidenceWriter writer2 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.xml25.XmlFeatureEvidenceWriter(streamWriter, objectIndex);
                            writer2.setAliasWriter(aliasWriter);
                            writer2.setAttributeWriter(attributeWriter);
                            writer2.setFeatureTypeWriter(featureTypeWriter);
                            writer2.setRangeWriter(rangeWriter);
                            writer2.setXrefWriter(primaryRefWriter);

                            return new PsiXmlElementWriter[]{writer2, modelledWriter2};
                    }
            }
        }
    }

    /**
     * <p>createParticipantWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param xmlType a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @param category a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param attributeWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param primaryRefWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlXrefWriter} object.
     * @param confidenceWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param interactorWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param cvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param openCvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     * @param parameterWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParameterWriter} object.
     * @param <P> a P object.
     * @return an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlParticipantWriter} objects.
     */
    public static <P extends Participant> PsiXmlParticipantWriter<P>[] createParticipantWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                                               PsiXmlObjectCache objectIndex, PsiXmlVersion version,
                                                                                               PsiXmlType xmlType,
                                                                                               InteractionCategory category, PsiXmlElementWriter<Alias> aliasWriter,
                                                                                               PsiXmlElementWriter<Annotation> attributeWriter,
                                                                                               PsiXmlXrefWriter primaryRefWriter,
                                                                                               PsiXmlElementWriter<Confidence> confidenceWriter,
                                                                                               PsiXmlElementWriter<Interactor> interactorWriter,
                                                                                               PsiXmlVariableNameWriter<CvTerm> cvWriter,
                                                                                               PsiXmlVariableNameWriter<CvTerm> openCvWriter,
                                                                                               PsiXmlParameterWriter parameterWriter) {

        PsiXmlElementWriter[] featureWriters = createFeatureWriter(streamWriter, extended, objectIndex, version, category, aliasWriter,
                attributeWriter, primaryRefWriter, cvWriter, parameterWriter);
        PsiXmlVariableNameWriter<CvTerm> experimentalCvWriter = createExperimentalCvWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                primaryRefWriter);
        PsiXmlElementWriter[] candidateWriters = createParticipantCandidateWriter(streamWriter, extended, objectIndex, version, xmlType,
                category, interactorWriter, (PsiXmlElementWriter<ModelledFeature>) featureWriters[1],
                (PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);


        if (extended) {
            PsiXmlElementWriter<AbstractExperimentalInteractor> experimentalInteractorWriter = createExperimentalInteractorWriter(streamWriter, version, objectIndex,
                    xmlType);

            switch (version) {
                case v3_0_0:
                    switch (xmlType) {
                        case compact:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlModelledParticipantWriter modelledWriter2 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlModelledParticipantWriter(streamWriter, objectIndex);
                            modelledWriter2.setAliasWriter(aliasWriter);
                            modelledWriter2.setAttributeWriter(attributeWriter);
                            modelledWriter2.setXrefWriter(primaryRefWriter);
                            modelledWriter2.setFeatureWriter((PsiXmlElementWriter<ModelledFeature>) featureWriters[1]);
                            modelledWriter2.setInteractorWriter(interactorWriter);
                            modelledWriter2.setBiologicalRoleWriter(cvWriter);
                            modelledWriter2.setParticipantCandidateWriter(candidateWriters[1]);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlParticipantWriter[]{modelledWriter2, modelledWriter2};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlParticipantEvidenceWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlParticipantEvidenceWriter(streamWriter, objectIndex);
                                    writer2.setAliasWriter(aliasWriter);
                                    writer2.setAttributeWriter(attributeWriter);
                                    writer2.setXrefWriter(primaryRefWriter);
                                    writer2.setFeatureWriter((PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);
                                    writer2.setInteractorWriter(interactorWriter);
                                    writer2.setBiologicalRoleWriter(cvWriter);
                                    writer2.setExperimentalCvWriter(experimentalCvWriter);
                                    writer2.setParameterWriter(parameterWriter);
                                    writer2.setConfidenceWriter(confidenceWriter);
                                    writer2.setHostOrganismWriter(createHostOrganismWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                                            attributeWriter, primaryRefWriter, openCvWriter));
                                    writer2.setExperimentalInteractorWriter((CompactPsiXmlElementWriter<AbstractExperimentalInteractor>)
                                            experimentalInteractorWriter);
                                    writer2.setParticipantCandidateWriter(candidateWriters[0]);

                                    return new PsiXmlParticipantWriter[]{writer2, modelledWriter2};
                            }
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlModelledParticipantWriter modelledWriter3 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlModelledParticipantWriter(streamWriter, objectIndex);
                            modelledWriter3.setAliasWriter(aliasWriter);
                            modelledWriter3.setAttributeWriter(attributeWriter);
                            modelledWriter3.setXrefWriter(primaryRefWriter);
                            modelledWriter3.setFeatureWriter((PsiXmlElementWriter<ModelledFeature>) featureWriters[1]);
                            modelledWriter3.setInteractorWriter(interactorWriter);
                            modelledWriter3.setBiologicalRoleWriter(cvWriter);
                            modelledWriter3.setParticipantCandidateWriter(candidateWriters[1]);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlParticipantWriter[]{modelledWriter3, modelledWriter3};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlParticipantEvidenceWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlParticipantEvidenceWriter(streamWriter, objectIndex);
                                    writer2.setAliasWriter(aliasWriter);
                                    writer2.setAttributeWriter(attributeWriter);
                                    writer2.setXrefWriter(primaryRefWriter);
                                    writer2.setFeatureWriter((PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);
                                    writer2.setInteractorWriter(interactorWriter);
                                    writer2.setBiologicalRoleWriter(cvWriter);
                                    writer2.setExperimentalCvWriter(experimentalCvWriter);
                                    writer2.setParameterWriter(parameterWriter);
                                    writer2.setConfidenceWriter(confidenceWriter);
                                    writer2.setHostOrganismWriter(createHostOrganismWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                                            attributeWriter, primaryRefWriter, openCvWriter));
                                    writer2.setExperimentalInteractorWriter((ExpandedPsiXmlElementWriter<AbstractExperimentalInteractor>)
                                            experimentalInteractorWriter);
                                    writer2.setParticipantCandidateWriter(candidateWriters[0]);

                                    return new PsiXmlParticipantWriter[]{writer2, modelledWriter3};
                            }
                    }

                case v2_5_3:
                    switch (xmlType) {
                        case compact:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.XmlModelledParticipantWriter modelledWriter2 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.XmlModelledParticipantWriter(streamWriter, objectIndex);
                            modelledWriter2.setAliasWriter(aliasWriter);
                            modelledWriter2.setAttributeWriter(attributeWriter);
                            modelledWriter2.setXrefWriter(primaryRefWriter);
                            modelledWriter2.setFeatureWriter((PsiXmlElementWriter<ModelledFeature>) featureWriters[1]);
                            modelledWriter2.setInteractorWriter(interactorWriter);
                            modelledWriter2.setBiologicalRoleWriter(cvWriter);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlParticipantWriter[]{modelledWriter2, modelledWriter2};
                                case basic:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.XmlParticipantWriter writer3 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.XmlParticipantWriter(streamWriter, objectIndex);
                                    writer3.setAliasWriter(aliasWriter);
                                    writer3.setAttributeWriter(attributeWriter);
                                    writer3.setXrefWriter(primaryRefWriter);
                                    writer3.setFeatureWriter((PsiXmlElementWriter<Feature>) featureWriters[0]);
                                    writer3.setInteractorWriter(interactorWriter);
                                    writer3.setBiologicalRoleWriter(cvWriter);

                                    return new PsiXmlParticipantWriter[]{writer3, modelledWriter2};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlParticipantEvidenceWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml253.XmlParticipantEvidenceWriter(streamWriter, objectIndex);
                                    writer2.setAliasWriter(aliasWriter);
                                    writer2.setAttributeWriter(attributeWriter);
                                    writer2.setXrefWriter(primaryRefWriter);
                                    writer2.setFeatureWriter((PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);
                                    writer2.setInteractorWriter(interactorWriter);
                                    writer2.setBiologicalRoleWriter(cvWriter);
                                    writer2.setExperimentalCvWriter(experimentalCvWriter);
                                    writer2.setParameterWriter(parameterWriter);
                                    writer2.setConfidenceWriter(confidenceWriter);
                                    writer2.setHostOrganismWriter(createHostOrganismWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                                            attributeWriter, primaryRefWriter, openCvWriter));

                                    return new PsiXmlParticipantWriter[]{writer2, modelledWriter2};
                            }
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.XmlModelledParticipantWriter modelledWriter3 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.XmlModelledParticipantWriter(streamWriter, objectIndex);
                            modelledWriter3.setAliasWriter(aliasWriter);
                            modelledWriter3.setAttributeWriter(attributeWriter);
                            modelledWriter3.setXrefWriter(primaryRefWriter);
                            modelledWriter3.setFeatureWriter((PsiXmlElementWriter<ModelledFeature>) featureWriters[1]);
                            modelledWriter3.setInteractorWriter(interactorWriter);
                            modelledWriter3.setBiologicalRoleWriter(cvWriter);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlParticipantWriter[]{modelledWriter3, modelledWriter3};
                                case basic:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.XmlParticipantWriter writer3 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.XmlParticipantWriter(streamWriter, objectIndex);
                                    writer3.setAliasWriter(aliasWriter);
                                    writer3.setAttributeWriter(attributeWriter);
                                    writer3.setXrefWriter(primaryRefWriter);
                                    writer3.setFeatureWriter((PsiXmlElementWriter<Feature>) featureWriters[0]);
                                    writer3.setInteractorWriter(interactorWriter);
                                    writer3.setBiologicalRoleWriter(cvWriter);

                                    return new PsiXmlParticipantWriter[]{writer3, modelledWriter3};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlParticipantEvidenceWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml253.XmlParticipantEvidenceWriter(streamWriter, objectIndex);
                                    writer2.setAliasWriter(aliasWriter);
                                    writer2.setAttributeWriter(attributeWriter);
                                    writer2.setXrefWriter(primaryRefWriter);
                                    writer2.setFeatureWriter((PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);
                                    writer2.setInteractorWriter(interactorWriter);
                                    writer2.setBiologicalRoleWriter(cvWriter);
                                    writer2.setExperimentalCvWriter(experimentalCvWriter);
                                    writer2.setParameterWriter(parameterWriter);
                                    writer2.setConfidenceWriter(confidenceWriter);
                                    writer2.setHostOrganismWriter(createHostOrganismWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                                            attributeWriter, primaryRefWriter, openCvWriter));

                                    return new PsiXmlParticipantWriter[]{writer2, modelledWriter3};
                            }
                    }

                case v2_5_4:
                default:
                    switch (xmlType) {
                        case compact:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.XmlModelledParticipantWriter modelledWriter2 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.XmlModelledParticipantWriter(streamWriter, objectIndex);
                            modelledWriter2.setAliasWriter(aliasWriter);
                            modelledWriter2.setAttributeWriter(attributeWriter);
                            modelledWriter2.setXrefWriter(primaryRefWriter);
                            modelledWriter2.setFeatureWriter((PsiXmlElementWriter<ModelledFeature>) featureWriters[1]);
                            modelledWriter2.setInteractorWriter(interactorWriter);
                            modelledWriter2.setBiologicalRoleWriter(cvWriter);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlParticipantWriter[]{modelledWriter2, modelledWriter2};
                                case basic:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.XmlParticipantWriter writer3 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.XmlParticipantWriter(streamWriter, objectIndex);
                                    writer3.setAliasWriter(aliasWriter);
                                    writer3.setAttributeWriter(attributeWriter);
                                    writer3.setXrefWriter(primaryRefWriter);
                                    writer3.setFeatureWriter((PsiXmlElementWriter<Feature>) featureWriters[0]);
                                    writer3.setInteractorWriter(interactorWriter);
                                    writer3.setBiologicalRoleWriter(cvWriter);

                                    return new PsiXmlParticipantWriter[]{writer3, modelledWriter2};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlParticipantEvidenceWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml25.xml254.XmlParticipantEvidenceWriter(streamWriter, objectIndex);
                                    writer2.setAliasWriter(aliasWriter);
                                    writer2.setAttributeWriter(attributeWriter);
                                    writer2.setXrefWriter(primaryRefWriter);
                                    writer2.setFeatureWriter((PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);
                                    writer2.setInteractorWriter(interactorWriter);
                                    writer2.setBiologicalRoleWriter(cvWriter);
                                    writer2.setExperimentalCvWriter(experimentalCvWriter);
                                    writer2.setParameterWriter(parameterWriter);
                                    writer2.setConfidenceWriter(confidenceWriter);
                                    writer2.setHostOrganismWriter(createHostOrganismWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                                            attributeWriter, primaryRefWriter, openCvWriter));

                                    return new PsiXmlParticipantWriter[]{writer2, modelledWriter2};
                            }
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.XmlModelledParticipantWriter modelledWriter3 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.XmlModelledParticipantWriter(streamWriter, objectIndex);
                            modelledWriter3.setAliasWriter(aliasWriter);
                            modelledWriter3.setAttributeWriter(attributeWriter);
                            modelledWriter3.setXrefWriter(primaryRefWriter);
                            modelledWriter3.setFeatureWriter((PsiXmlElementWriter<ModelledFeature>) featureWriters[1]);
                            modelledWriter3.setInteractorWriter(interactorWriter);
                            modelledWriter3.setBiologicalRoleWriter(cvWriter);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlParticipantWriter[]{modelledWriter3, modelledWriter3};
                                case basic:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.XmlParticipantWriter writer3 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.XmlParticipantWriter(streamWriter, objectIndex);
                                    writer3.setAliasWriter(aliasWriter);
                                    writer3.setAttributeWriter(attributeWriter);
                                    writer3.setXrefWriter(primaryRefWriter);
                                    writer3.setFeatureWriter((PsiXmlElementWriter<Feature>) featureWriters[0]);
                                    writer3.setInteractorWriter(interactorWriter);
                                    writer3.setBiologicalRoleWriter(cvWriter);

                                    return new PsiXmlParticipantWriter[]{writer3, modelledWriter3};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlParticipantEvidenceWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml25.xml254.XmlParticipantEvidenceWriter(streamWriter, objectIndex);
                                    writer2.setAliasWriter(aliasWriter);
                                    writer2.setAttributeWriter(attributeWriter);
                                    writer2.setXrefWriter(primaryRefWriter);
                                    writer2.setFeatureWriter((PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);
                                    writer2.setInteractorWriter(interactorWriter);
                                    writer2.setBiologicalRoleWriter(cvWriter);
                                    writer2.setExperimentalCvWriter(experimentalCvWriter);
                                    writer2.setParameterWriter(parameterWriter);
                                    writer2.setConfidenceWriter(confidenceWriter);
                                    writer2.setHostOrganismWriter(createHostOrganismWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                                            attributeWriter, primaryRefWriter, openCvWriter));

                                    return new PsiXmlParticipantWriter[]{writer2, modelledWriter3};
                            }
                    }
            }
        } else {
            switch (version) {
                case v3_0_0:
                    switch (xmlType) {
                        case compact:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlModelledParticipantWriter modelledWriter2 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlModelledParticipantWriter(streamWriter, objectIndex);
                            modelledWriter2.setAliasWriter(aliasWriter);
                            modelledWriter2.setAttributeWriter(attributeWriter);
                            modelledWriter2.setXrefWriter(primaryRefWriter);
                            modelledWriter2.setFeatureWriter((PsiXmlElementWriter<ModelledFeature>) featureWriters[1]);
                            modelledWriter2.setInteractorWriter(interactorWriter);
                            modelledWriter2.setBiologicalRoleWriter(cvWriter);
                            modelledWriter2.setParticipantCandidateWriter(candidateWriters[1]);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlParticipantWriter[]{modelledWriter2, modelledWriter2};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlParticipantEvidenceWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlParticipantEvidenceWriter(streamWriter, objectIndex);
                                    writer2.setAliasWriter(aliasWriter);
                                    writer2.setAttributeWriter(attributeWriter);
                                    writer2.setXrefWriter(primaryRefWriter);
                                    writer2.setFeatureWriter((PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);
                                    writer2.setInteractorWriter(interactorWriter);
                                    writer2.setBiologicalRoleWriter(cvWriter);
                                    writer2.setExperimentalCvWriter(experimentalCvWriter);
                                    writer2.setParameterWriter(parameterWriter);
                                    writer2.setConfidenceWriter(confidenceWriter);
                                    writer2.setHostOrganismWriter(createHostOrganismWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                                            attributeWriter, primaryRefWriter, openCvWriter));
                                    writer2.setParticipantCandidateWriter(candidateWriters[0]);

                                    return new PsiXmlParticipantWriter[]{writer2, modelledWriter2};
                            }
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlModelledParticipantWriter modelledWriter3 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlModelledParticipantWriter(streamWriter, objectIndex);
                            modelledWriter3.setAliasWriter(aliasWriter);
                            modelledWriter3.setAttributeWriter(attributeWriter);
                            modelledWriter3.setXrefWriter(primaryRefWriter);
                            modelledWriter3.setFeatureWriter((PsiXmlElementWriter<ModelledFeature>) featureWriters[1]);
                            modelledWriter3.setInteractorWriter(interactorWriter);
                            modelledWriter3.setBiologicalRoleWriter(cvWriter);
                            modelledWriter3.setParticipantCandidateWriter(candidateWriters[1]);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlParticipantWriter[]{modelledWriter3, modelledWriter3};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlParticipantEvidenceWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlParticipantEvidenceWriter(streamWriter, objectIndex);
                                    writer2.setAliasWriter(aliasWriter);
                                    writer2.setAttributeWriter(attributeWriter);
                                    writer2.setXrefWriter(primaryRefWriter);
                                    writer2.setFeatureWriter((PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);
                                    writer2.setInteractorWriter(interactorWriter);
                                    writer2.setBiologicalRoleWriter(cvWriter);
                                    writer2.setExperimentalCvWriter(experimentalCvWriter);
                                    writer2.setParameterWriter(parameterWriter);
                                    writer2.setConfidenceWriter(confidenceWriter);
                                    writer2.setHostOrganismWriter(createHostOrganismWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                                            attributeWriter, primaryRefWriter, openCvWriter));
                                    writer2.setParticipantCandidateWriter(candidateWriters[0]);

                                    return new PsiXmlParticipantWriter[]{writer2, modelledWriter3};
                            }
                    }

                case v2_5_3:
                case v2_5_4:
                default:
                    switch (xmlType) {
                        case compact:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.XmlModelledParticipantWriter modelledWriter2 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.XmlModelledParticipantWriter(streamWriter, objectIndex);
                            modelledWriter2.setAliasWriter(aliasWriter);
                            modelledWriter2.setAttributeWriter(attributeWriter);
                            modelledWriter2.setXrefWriter(primaryRefWriter);
                            modelledWriter2.setFeatureWriter((PsiXmlElementWriter<ModelledFeature>) featureWriters[1]);
                            modelledWriter2.setInteractorWriter(interactorWriter);
                            modelledWriter2.setBiologicalRoleWriter(cvWriter);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlParticipantWriter[]{modelledWriter2, modelledWriter2};
                                case basic:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.XmlParticipantWriter writer3 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.XmlParticipantWriter(streamWriter, objectIndex);
                                    writer3.setAliasWriter(aliasWriter);
                                    writer3.setAttributeWriter(attributeWriter);
                                    writer3.setXrefWriter(primaryRefWriter);
                                    writer3.setFeatureWriter((PsiXmlElementWriter<Feature>) featureWriters[0]);
                                    writer3.setInteractorWriter(interactorWriter);
                                    writer3.setBiologicalRoleWriter(cvWriter);

                                    return new PsiXmlParticipantWriter[]{writer3, modelledWriter2};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.XmlParticipantEvidenceWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml25.XmlParticipantEvidenceWriter(streamWriter, objectIndex);
                                    writer2.setAliasWriter(aliasWriter);
                                    writer2.setAttributeWriter(attributeWriter);
                                    writer2.setXrefWriter(primaryRefWriter);
                                    writer2.setFeatureWriter((PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);
                                    writer2.setInteractorWriter(interactorWriter);
                                    writer2.setBiologicalRoleWriter(cvWriter);
                                    writer2.setExperimentalCvWriter(experimentalCvWriter);
                                    writer2.setParameterWriter(parameterWriter);
                                    writer2.setConfidenceWriter(confidenceWriter);
                                    writer2.setHostOrganismWriter(createHostOrganismWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                                            attributeWriter, primaryRefWriter, openCvWriter));

                                    return new PsiXmlParticipantWriter[]{writer2, modelledWriter2};
                            }
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.XmlModelledParticipantWriter modelledWriter3 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.XmlModelledParticipantWriter(streamWriter, objectIndex);
                            modelledWriter3.setAliasWriter(aliasWriter);
                            modelledWriter3.setAttributeWriter(attributeWriter);
                            modelledWriter3.setXrefWriter(primaryRefWriter);
                            modelledWriter3.setFeatureWriter((PsiXmlElementWriter<ModelledFeature>) featureWriters[1]);
                            modelledWriter3.setInteractorWriter(interactorWriter);
                            modelledWriter3.setBiologicalRoleWriter(cvWriter);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlParticipantWriter[]{modelledWriter3, modelledWriter3};
                                case basic:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.XmlParticipantWriter writer3 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.XmlParticipantWriter(streamWriter, objectIndex);
                                    writer3.setAliasWriter(aliasWriter);
                                    writer3.setAttributeWriter(attributeWriter);
                                    writer3.setXrefWriter(primaryRefWriter);
                                    writer3.setFeatureWriter((PsiXmlElementWriter<Feature>) featureWriters[0]);
                                    writer3.setInteractorWriter(interactorWriter);
                                    writer3.setBiologicalRoleWriter(cvWriter);

                                    return new PsiXmlParticipantWriter[]{writer3, modelledWriter3};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.XmlParticipantEvidenceWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml25.XmlParticipantEvidenceWriter(streamWriter, objectIndex);
                                    writer2.setAliasWriter(aliasWriter);
                                    writer2.setAttributeWriter(attributeWriter);
                                    writer2.setXrefWriter(primaryRefWriter);
                                    writer2.setFeatureWriter((PsiXmlElementWriter<FeatureEvidence>) featureWriters[0]);
                                    writer2.setInteractorWriter(interactorWriter);
                                    writer2.setBiologicalRoleWriter(cvWriter);
                                    writer2.setExperimentalCvWriter(experimentalCvWriter);
                                    writer2.setParameterWriter(parameterWriter);
                                    writer2.setConfidenceWriter(confidenceWriter);
                                    writer2.setHostOrganismWriter(createHostOrganismWriter(streamWriter, version, extended, objectIndex, aliasWriter,
                                            attributeWriter, primaryRefWriter, openCvWriter));

                                    return new PsiXmlParticipantWriter[]{writer2, modelledWriter3};
                            }
                    }
            }
        }
    }

    /**
     * <p>createParticipantCandidateWriter.</p>
     *
     * @param streamWriter a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param extended a boolean.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param xmlType a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @param category a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param interactorWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param modelledFeatureWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param featureEvidenceWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @param <P> a P object.
     * @return an array of {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} objects.
     */
    public static <P extends ParticipantCandidate> PsiXmlElementWriter<P>[] createParticipantCandidateWriter(XMLStreamWriter streamWriter, boolean extended,
                                                                                                             PsiXmlObjectCache objectIndex, PsiXmlVersion version,
                                                                                                             PsiXmlType xmlType,
                                                                                                             InteractionCategory category, PsiXmlElementWriter<Interactor> interactorWriter,
                                                                                                             PsiXmlElementWriter<ModelledFeature> modelledFeatureWriter,
                                                                                                             PsiXmlElementWriter<FeatureEvidence> featureEvidenceWriter) {

        if (extended) {
            switch (version) {
                case v3_0_0:
                    switch (xmlType) {
                        case compact:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlModelledParticipantCandidateWriter modelledWriter2 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlModelledParticipantCandidateWriter(streamWriter, objectIndex);
                            modelledWriter2.setFeatureWriter(modelledFeatureWriter);
                            modelledWriter2.setInteractorWriter(interactorWriter);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlElementWriter[]{modelledWriter2, modelledWriter2};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlExperimentalParticipantCandidateWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.compact.xml30.XmlExperimentalParticipantCandidateWriter(streamWriter, objectIndex);
                                    writer2.setFeatureWriter(featureEvidenceWriter);
                                    writer2.setInteractorWriter(interactorWriter);

                                    return new PsiXmlElementWriter[]{writer2, modelledWriter2};
                            }
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlModelledParticipantCandidateWriter modelledWriter3 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlModelledParticipantCandidateWriter(streamWriter, objectIndex);
                            modelledWriter3.setFeatureWriter(modelledFeatureWriter);
                            modelledWriter3.setInteractorWriter(interactorWriter);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlElementWriter[]{modelledWriter3, modelledWriter3};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlExperimentalParticipantCandidateWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.expanded.xml30.XmlExperimentalParticipantCandidateWriter(streamWriter, objectIndex);

                                    writer2.setFeatureWriter(featureEvidenceWriter);
                                    writer2.setInteractorWriter(interactorWriter);

                                    return new PsiXmlElementWriter[]{writer2, modelledWriter3};
                            }
                    }

                case v2_5_3:
                case v2_5_4:
                default:
                    return null;
            }
        } else {
            switch (version) {
                case v3_0_0:
                    switch (xmlType) {
                        case compact:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlModelledParticipantCandidateWriter modelledWriter2 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlModelledParticipantCandidateWriter(streamWriter, objectIndex);
                            modelledWriter2.setFeatureWriter(modelledFeatureWriter);
                            modelledWriter2.setInteractorWriter(interactorWriter);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlElementWriter[]{modelledWriter2, modelledWriter2};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlExperimentalParticipantCandidateWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.compact.xml30.XmlExperimentalParticipantCandidateWriter(streamWriter, objectIndex);
                                    writer2.setFeatureWriter(featureEvidenceWriter);
                                    writer2.setInteractorWriter(interactorWriter);

                                    return new PsiXmlElementWriter[]{writer2, modelledWriter2};
                            }
                        default:
                            psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlModelledParticipantCandidateWriter modelledWriter3 =
                                    new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlModelledParticipantCandidateWriter(streamWriter, objectIndex);

                            modelledWriter3.setFeatureWriter(modelledFeatureWriter);
                            modelledWriter3.setInteractorWriter(interactorWriter);

                            switch (category) {
                                case modelled:
                                    return new PsiXmlElementWriter[]{modelledWriter3, modelledWriter3};
                                default:
                                    psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlExperimentalParticipantCandidateWriter writer2 =
                                            new psidev.psi.mi.jami.xml.io.writer.elements.impl.expanded.xml30.XmlExperimentalParticipantCandidateWriter(streamWriter, objectIndex);
                                    writer2.setFeatureWriter(featureEvidenceWriter);
                                    writer2.setInteractorWriter(interactorWriter);

                                    return new PsiXmlElementWriter[]{writer2, modelledWriter3};
                            }
                    }

                case v2_5_3:
                case v2_5_4:
                default:
                    return null;
            }
        }
    }

    private static void initWriter(
            psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlInteractionWriter writer,
            PsiXmlElementWriter<Annotation> attributeWriter,
            PsiXmlXrefWriter primaryRefWriter,
            PsiXmlElementWriter<Checksum> checksumWriter,
            PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
            PsiXmlExperimentWriter experimentWriter,
            PsiXmlElementWriter inferredInteractionWriter,
            PsiXmlParticipantWriter participantWriter) {

        writer.setAttributeWriter(attributeWriter);
        writer.setXrefWriter(primaryRefWriter);
        writer.setInteractionTypeWriter(interactionTypeWriter);
        writer.setInferredInteractionWriter(inferredInteractionWriter);
        writer.setChecksumWriter(checksumWriter);
        writer.setExperimentWriter(experimentWriter);
        writer.setParticipantWriter(participantWriter);
    }

    private static void initModelledWriter(
            psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlModelledInteractionWriter modelledWriter,
            PsiXmlElementWriter<Alias> aliasWriter,
            PsiXmlElementWriter<Annotation> attributeWriter,
            PsiXmlXrefWriter primaryRefWriter,
            PsiXmlElementWriter confidenceWriter,
            PsiXmlElementWriter<Checksum> checksumWriter,
            PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
            PsiXmlExperimentWriter experimentWriter,
            PsiXmlParameterWriter parameterWriter,
            PsiXmlParticipantWriter participantWriter,
            PsiXmlElementWriter inferredInteractionWriter) {

        initWriter(modelledWriter, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                inferredInteractionWriter, participantWriter);
        modelledWriter.setAliasWriter(aliasWriter);
        modelledWriter.setConfidenceWriter(confidenceWriter);
        modelledWriter.setParameterWriter(parameterWriter);
    }

    private static void initModelledWriter30(
            psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30.AbstractXmlModelledInteractionWriter modelledWriter,
            PsiXmlElementWriter<Alias> aliasWriter,
            PsiXmlElementWriter<Annotation> attributeWriter,
            PsiXmlXrefWriter primaryRefWriter,
            PsiXmlElementWriter confidenceWriter,
            PsiXmlElementWriter<Checksum> checksumWriter,
            PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
            PsiXmlExperimentWriter experimentWriter,
            PsiXmlParameterWriter parameterWriter,
            PsiXmlParticipantWriter participantWriter,
            PsiXmlElementWriter inferredInteractionWriter,
            PsiXmlElementWriter<Set<Feature>> bindingFeaturesWriter,
            PsiXmlCausalRelationshipWriter relationshipWriter,
            PsiXmlElementWriter<Preassembly> preassemblyWriter,
            PsiXmlElementWriter<Allostery> allosteryWriter) {

        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriter, checksumWriter,
                interactionTypeWriter, experimentWriter, parameterWriter, participantWriter, inferredInteractionWriter);
        modelledWriter.setBindingFeaturesWriter(bindingFeaturesWriter);
        modelledWriter.setCausalRelationshipWriter(relationshipWriter);
        modelledWriter.setAllosteryWriter(allosteryWriter);
        modelledWriter.setPreAssemblyWriter(preassemblyWriter);
    }

    private static void initExtendedModelledWriter30(
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.AbstractXmlModelledInteractionWriter modelledWriter,
            PsiXmlElementWriter<Alias> aliasWriter,
            PsiXmlElementWriter<Annotation> attributeWriter,
            PsiXmlXrefWriter primaryRefWriter,
            PsiXmlElementWriter confidenceWriter,
            PsiXmlElementWriter<Checksum> checksumWriter,
            PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
            PsiXmlExperimentWriter experimentWriter,
            PsiXmlParameterWriter parameterWriter,
            PsiXmlParticipantWriter participantWriter,
            PsiXmlElementWriter inferredInteractionWriter,
            PsiXmlElementWriter<Set<Feature>> bindingFeaturesWriter,
            PsiXmlCausalRelationshipWriter relationshipWriter,
            PsiXmlElementWriter<Preassembly> preassemblyWriter,
            PsiXmlElementWriter<Allostery> allosteryWriter) {

        initModelledWriter(modelledWriter, aliasWriter, attributeWriter, primaryRefWriter, confidenceWriter, checksumWriter,
                interactionTypeWriter, experimentWriter, parameterWriter, participantWriter, inferredInteractionWriter);
        modelledWriter.setBindingFeaturesWriter(bindingFeaturesWriter);
        modelledWriter.setCausalRelationshipWriter(relationshipWriter);
        modelledWriter.setAllosteryWriter(allosteryWriter);
        modelledWriter.setPreAssemblyWriter(preassemblyWriter);
    }

    private static void initEvidenceWriter(
            psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.AbstractXmlInteractionEvidenceWriter writer,
            PsiXmlElementWriter<Annotation> attributeWriter,
            PsiXmlXrefWriter primaryRefWriter,
            PsiXmlElementWriter confidenceWriter,
            PsiXmlElementWriter<Checksum> checksumWriter,
            PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
            PsiXmlExperimentWriter experimentWriter,
            PsiXmlParameterWriter parameterWriter,
            PsiXmlParticipantWriter participantWriter,
            PsiXmlElementWriter inferredInteractionWriter,
            PsiXmlElementWriter<String> availabilityWriter) {

        initWriter(writer, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                inferredInteractionWriter, participantWriter);
        writer.setConfidenceWriter(confidenceWriter);
        writer.setParameterWriter(parameterWriter);
        writer.setAvailabilityWriter(availabilityWriter);
    }

    private static void initEvidenceWriter30(
            psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30.AbstractXmlInteractionEvidenceWriter writer,
            PsiXmlElementWriter<Annotation> attributeWriter,
            PsiXmlXrefWriter primaryRefWriter,
            PsiXmlElementWriter confidenceWriter,
            PsiXmlElementWriter<Checksum> checksumWriter,
            PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
            PsiXmlExperimentWriter experimentWriter,
            PsiXmlParameterWriter parameterWriter,
            PsiXmlParticipantWriter participantWriter,
            PsiXmlElementWriter inferredInteractionWriter,
            PsiXmlElementWriter<String> availabilityWriter,
            PsiXmlCausalRelationshipWriter relationshipWriter,
            XMLStreamWriter streamWriter,
            PsiXmlObjectCache objectIndex) {

        initEvidenceWriter(writer, attributeWriter, primaryRefWriter, confidenceWriter, checksumWriter,
                interactionTypeWriter, experimentWriter, parameterWriter, participantWriter, inferredInteractionWriter, availabilityWriter);
        writer.setCausalRelationshipWriter(relationshipWriter);
        writer.setVariableParameterValueSetWriter(createVariableParameterValueSetWriter(streamWriter, objectIndex));
        writer.setCausalRelationshipWriter(relationshipWriter);
    }

    private static void initExtendedEvidenceWriter(
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.AbstractXmlInteractionEvidenceWriter writer,
            PsiXmlElementWriter<Annotation> attributeWriter,
            PsiXmlXrefWriter primaryRefWriter,
            PsiXmlElementWriter confidenceWriter,
            PsiXmlElementWriter<Checksum> checksumWriter,
            PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
            PsiXmlExperimentWriter experimentWriter,
            PsiXmlParameterWriter parameterWriter,
            PsiXmlParticipantWriter participantWriter,
            PsiXmlElementWriter inferredInteractionWriter,
            PsiXmlElementWriter<String> availabilityWriter,
            PsiXmlElementWriter<AbstractInferredInteraction> extendedInferredInteractionWriter) {

        initWriter(writer, attributeWriter, primaryRefWriter, checksumWriter, interactionTypeWriter, experimentWriter,
                inferredInteractionWriter, participantWriter);
        writer.setConfidenceWriter(confidenceWriter);
        writer.setParameterWriter(parameterWriter);
        writer.setAvailabilityWriter(availabilityWriter);
        writer.setXmlInferredInteractionWriter(extendedInferredInteractionWriter);
    }

    private static void initExtendedEvidenceWriter30(
            psidev.psi.mi.jami.xml.io.writer.elements.impl.extended.xml30.AbstractXmlInteractionEvidenceWriter writer,
            PsiXmlElementWriter<Annotation> attributeWriter,
            PsiXmlXrefWriter primaryRefWriter,
            PsiXmlElementWriter confidenceWriter,
            PsiXmlElementWriter<Checksum> checksumWriter,
            PsiXmlVariableNameWriter<CvTerm> interactionTypeWriter,
            PsiXmlExperimentWriter experimentWriter,
            PsiXmlParameterWriter parameterWriter,
            PsiXmlParticipantWriter participantWriter,
            PsiXmlElementWriter inferredInteractionWriter,
            PsiXmlElementWriter<String> availabilityWriter,
            PsiXmlCausalRelationshipWriter relationshipWriter,
            XMLStreamWriter streamWriter,
            PsiXmlObjectCache objectIndex,
            PsiXmlElementWriter<AbstractInferredInteraction> extendedInferredInteractionWriter) {

        initExtendedEvidenceWriter(writer, attributeWriter, primaryRefWriter, confidenceWriter, checksumWriter,
                interactionTypeWriter, experimentWriter, parameterWriter, participantWriter, inferredInteractionWriter,
                availabilityWriter, extendedInferredInteractionWriter);
        writer.setCausalRelationshipWriter(relationshipWriter);
        writer.setVariableParameterValueSetWriter(createVariableParameterValueSetWriter(streamWriter, objectIndex));
        writer.setCausalRelationshipWriter(relationshipWriter);
    }
}
