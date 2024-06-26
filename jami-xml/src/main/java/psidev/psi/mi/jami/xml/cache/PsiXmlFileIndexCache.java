package psidev.psi.mi.jami.xml.cache;

import org.apache.commons.io.input.CountingInputStream;
import org.codehaus.stax2.XMLInputFactory2;
import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.XmlEntryContext;
import psidev.psi.mi.jami.xml.io.parser.JaxbUnmarshallerFactory;
import psidev.psi.mi.jami.xml.io.parser.NonCloseableInputStreamWrapper;
import psidev.psi.mi.jami.xml.io.parser.XmlReaderWithDefaultNamespace;
import psidev.psi.mi.jami.xml.model.extension.AbstractAvailability;
import psidev.psi.mi.jami.xml.model.extension.AbstractBaseXmlInteractor;
import psidev.psi.mi.jami.xml.model.extension.AbstractXmlVariableParameterValue;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlEntity;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlFeature;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlModelledInteraction;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlParticipant;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlInteraction;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.*;
import java.nio.channels.Channels;
import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Cache using a file and a weak map to cache the objects
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25/06/14</pre>
 */
public class PsiXmlFileIndexCache implements PsiXmlIdCache {

    private static final Logger logger = Logger.getLogger("PsiXmlFileIndexCache");

    /**
     * Captures the identifier from a String looking like: id="1"
     */
    private static final Pattern ID_PATTERN = Pattern.compile( "id(?:\\s*)=(?:\\s*)['\"](\\d*?)['\"]", Pattern.CANON_EQ );

    private File file;
    private Unmarshaller unmarshaller;
    private RandomAccessFile randomAccessFile;
    private PsiXmlVersion version;
    private String namespaceUri;
    private XMLInputFactory xmlif;
    private String encoding;

    private Map<Integer, AbstractAvailability> mapOfReferencedAvailabilities;

    private Map<EntryLocation, Long> experimentPositions;
    private Map<EntryLocation, Long> interactorPositions;
    private Map<EntryLocation, Long> interactionPositions;
    private Map<EntryLocation, Long> participantPositions;
    private Map<EntryLocation, Long> featurePositions;
    private Map<EntryLocation, Long> variableParameterValuePositions;
    private Map<EntryLocation, Long> complexPositions;
    private Map<EntryLocation, Long> complexParticipantPositions;
    private Map<EntryLocation, Long> complexFeaturePositions;

    private Map<Integer, Experiment> experimentWeakMap;
    private Map<Integer, Interactor> interactorWeakMap;
    private Map<Integer, Interaction> interactionWeakMap;
    private Map<Integer, Entity> participantWeakMap;
    private Map<Integer, Feature> featureWeakMap;
    private Map<Integer, VariableParameterValue> variableParameterValueWeakMap;
    private Map<Integer, Complex> complexWeakMap;
    private Map<Integer, ModelledEntity> complexParticipantWeakMap;
    private Map<Integer, ModelledFeature> complexFeatureWeakMap;

    private int numberOfEntries=1;

    /**
     * <p>Constructor for PsiXmlFileIndexCache.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param unmarshaller a {@link javax.xml.bind.Unmarshaller} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @throws java.io.IOException if any.
     */
    public PsiXmlFileIndexCache(File file, Unmarshaller unmarshaller, PsiXmlVersion version) throws IOException {
        if (file == null){
            throw new IllegalArgumentException("The file index cache needs the original file containing data.");
        }
        this.file = file;
        if (unmarshaller == null){
            throw new IllegalArgumentException("The file index cache needs the unmarshaller to unmarshall partial object from file cache.");
        }
        this.unmarshaller = unmarshaller;
        this.randomAccessFile = new RandomAccessFile(this.file, "r");

        this.mapOfReferencedAvailabilities = new HashMap<Integer, AbstractAvailability>();

        this.experimentPositions = new HashMap<EntryLocation, Long>();
        this.interactorPositions = new HashMap<EntryLocation, Long>();
        this.interactionPositions = new HashMap<EntryLocation, Long>();
        this.participantPositions = new HashMap<EntryLocation, Long>();
        this.featurePositions = new HashMap<EntryLocation, Long>();
        this.variableParameterValuePositions = new HashMap<EntryLocation, Long>();
        this.complexPositions = new HashMap<EntryLocation, Long>();
        this.complexParticipantPositions = new HashMap<EntryLocation, Long>();
        this.complexFeaturePositions = new HashMap<EntryLocation, Long>();

        this.experimentWeakMap = new WeakHashMap<Integer, Experiment>();
        this.interactorWeakMap = new WeakHashMap<Integer, Interactor>();
        this.interactionWeakMap = new WeakHashMap<Integer, Interaction>();
        this.participantWeakMap = new WeakHashMap<Integer, Entity>();
        this.featureWeakMap = new WeakHashMap<Integer, Feature>();
        this.variableParameterValueWeakMap = new WeakHashMap<Integer, VariableParameterValue>();
        this.complexWeakMap = new WeakHashMap<Integer, Complex>();
        this.complexParticipantWeakMap = new WeakHashMap<Integer, ModelledEntity>();
        this.complexFeatureWeakMap = new WeakHashMap<Integer, ModelledFeature>();

        this.version = version;
        switch (version){
            case v2_5_4:
                this.namespaceUri = PsiXmlUtils.Xml254_NAMESPACE_URI;
                break;
            case v2_5_3:
                this.namespaceUri = PsiXmlUtils.Xml253_NAMESPACE_URI;
                break;
            case v3_0_0:
                this.namespaceUri = PsiXmlUtils.Xml300_NAMESPACE_URI;
                break;
            default:
                this.namespaceUri = PsiXmlUtils.Xml254_NAMESPACE_URI;
                break;
        }

        buildPositionIndex(this.file);
    }

    /**
     * <p>Constructor for PsiXmlFileIndexCache.</p>
     *
     * @param file a {@link java.io.File} object.
     * @param version a {@link psidev.psi.mi.jami.xml.PsiXmlVersion} object.
     * @param category a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @throws java.io.IOException if any.
     * @throws javax.xml.bind.JAXBException if any.
     */
    public PsiXmlFileIndexCache(File file, PsiXmlVersion version, InteractionCategory category) throws IOException, JAXBException {
        this(file,
                JaxbUnmarshallerFactory.getInstance().createUnmarshaller(version != null ? version : PsiXmlVersion.v2_5_4,
                        category != null ? category : InteractionCategory.evidence),
                version
        );
    }

    /** {@inheritDoc} */
    @Override
    public void registerAvailability(int id, AbstractAvailability object) {
        this.mapOfReferencedAvailabilities.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public AbstractAvailability getAvailability(int id) {
        return this.mapOfReferencedAvailabilities.get(id);
    }

    /** {@inheritDoc} */
    @Override
    public void registerExperiment(int id, Experiment object) {
        this.experimentWeakMap.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Experiment getExperiment(int id) {
        if (this.experimentWeakMap.containsKey(id)){
            return this.experimentWeakMap.get(id);
        }

        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        if (!this.experimentPositions.containsKey(location)){
            return null;
        }
        else {
            try {
                return loadFromFile(this.experimentPositions.get(location));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "cannot reload experiment "+id, e);
                throw new MIIOException("cannot reload experiment "+id, e);
            } catch (JAXBException e) {
                logger.log(Level.SEVERE, "cannot reload experiment "+id, e);
                throw new MIIOException("cannot reload experiment "+id, e);
            } catch (XMLStreamException e) {
                logger.log(Level.SEVERE, "cannot reload experiment "+id, e);
                throw new MIIOException("cannot reload experiment "+id, e);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void registerInteraction(int id, Interaction object) {
        this.interactionWeakMap.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Interaction getInteraction(int id) {
        if (this.interactionWeakMap.containsKey(id)){
            return this.interactionWeakMap.get(id);
        }

        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        if (!this.interactionPositions.containsKey(location)){
            return null;
        }
        else {
            try {
                return loadFromFile(this.interactionPositions.get(location));
            } catch (IOException e) {
                logger.log(Level.SEVERE, "cannot reload interaction "+id, e);
                throw new MIIOException("cannot reload interaction "+id, e);
            } catch (JAXBException e) {
                logger.log(Level.SEVERE, "cannot reload interaction "+id, e);
                throw new MIIOException("cannot reload interaction "+id, e);
            } catch (XMLStreamException e) {
                logger.log(Level.SEVERE, "cannot reload interaction "+id, e);
                throw new MIIOException("cannot reload interaction "+id, e);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void registerInteractor(int id, Interactor object) {
        this.interactorWeakMap.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Interactor getInteractor(int id) {
        if (this.interactorWeakMap.containsKey(id)){
            return this.interactorWeakMap.get(id);
        }

        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        if (!this.interactorPositions.containsKey(location)){
            return null;
        }
        else {
            try {
                AbstractBaseXmlInteractor interactor = loadFromFile(this.interactorPositions.get(location));
                return XmlEntryContext.getInstance().getInteractorFactory().createInteractorFromXmlInteractorInstance(interactor, version);
            } catch (IOException e) {
                logger.log(Level.SEVERE, "cannot reload interactor "+id, e);
                throw new MIIOException("cannot reload interactor "+id, e);
            } catch (JAXBException e) {
                logger.log(Level.SEVERE, "cannot reload interactor "+id, e);
                throw new MIIOException("cannot reload interactor "+id, e);
            } catch (XMLStreamException e) {
                logger.log(Level.SEVERE, "cannot reload interactor "+id, e);
                throw new MIIOException("cannot reload interactor "+id, e);
            }
        }
    }

    /** {@inheritDoc} */
    @Override
    public void registerParticipant(int id, Entity object) {
        this.participantWeakMap.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Entity getParticipant(int id) {
        if (this.participantWeakMap.containsKey(id)){
            return this.participantWeakMap.get(id);
        }

        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        if (!this.participantPositions.containsKey(location)){
            return null;
        }
        else {
            try {
                Interaction originalInteraction = loadFromFile(this.participantPositions.get(location));
                if (originalInteraction == null){
                    logger.log(Level.SEVERE, "cannot reload participant "+id);
                }
                else{
                    for (Object p : originalInteraction.getParticipants()){
                        ExtendedPsiXmlParticipant participant = (ExtendedPsiXmlParticipant)p;
                        if (participant.getId() == id){
                            return participant;
                        }
                        else if (participant instanceof ParticipantPool){
                            ParticipantPool pool = (ParticipantPool)participant;
                            for (Object candidate : pool){
                                ExtendedPsiXmlEntity extendedEntity = (ExtendedPsiXmlEntity)candidate;
                                if (extendedEntity.getId() == id){
                                    return extendedEntity;
                                }
                            }
                        }
                    }
                    logger.log(Level.SEVERE, "cannot reload participant "+id);
                    return null;
                }

            } catch (IOException e) {
                logger.log(Level.SEVERE, "cannot reload participant "+id, e);
                throw new MIIOException("cannot reload participant "+id, e);
            } catch (JAXBException e) {
                logger.log(Level.SEVERE, "cannot reload participant "+id, e);
                throw new MIIOException("cannot reload participant "+id, e);
            } catch (XMLStreamException e) {
                logger.log(Level.SEVERE, "cannot reload participant "+id, e);
                throw new MIIOException("cannot reload participant "+id, e);
            }
            return null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void registerFeature(int id, Feature object) {
        this.featureWeakMap.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Feature getFeature(int id) {
        if (this.featureWeakMap.containsKey(id)){
            return this.featureWeakMap.get(id);
        }

        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        if (!this.featurePositions.containsKey(location)){
            return null;
        }
        else {
            try {
                Interaction originalInteraction = loadFromFile(this.featurePositions.get(location));
                if (originalInteraction == null){
                    logger.log(Level.SEVERE, "cannot reload feature "+id);
                }
                else{
                    for (Object p : originalInteraction.getParticipants()){
                        ExtendedPsiXmlParticipant participant = (ExtendedPsiXmlParticipant)p;
                        for (Object f : participant.getFeatures()){
                            ExtendedPsiXmlFeature feature = (ExtendedPsiXmlFeature)f;
                            if (feature.getId() == id){
                                return feature;
                            }
                        }

                        if (participant instanceof ParticipantPool){
                            ParticipantPool pool = (ParticipantPool)participant;
                            for (Object candidate : pool){
                                for (Object f : ((ParticipantCandidate)candidate).getFeatures()){
                                    ExtendedPsiXmlFeature feature = (ExtendedPsiXmlFeature)f;
                                    if (feature.getId() == id){
                                        return feature;
                                    }
                                }
                            }
                        }
                    }
                    logger.log(Level.SEVERE, "cannot reload feature "+id);
                    return null;
                }

            } catch (IOException e) {
                logger.log(Level.SEVERE, "cannot reload feature "+id, e);
                throw new MIIOException("cannot reload feature "+id, e);
            } catch (JAXBException e) {
                logger.log(Level.SEVERE, "cannot reload feature "+id, e);
                throw new MIIOException("cannot reload feature "+id, e);
            } catch (XMLStreamException e) {
                logger.log(Level.SEVERE, "cannot reload feature "+id, e);
                throw new MIIOException("cannot reload feature "+id, e);
            }
            return null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void registerComplexParticipant(int id, ModelledEntity object) {
        this.complexParticipantWeakMap.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public ModelledEntity getComplexParticipant(int id) {
        if (this.complexParticipantWeakMap.containsKey(id)){
            return this.complexParticipantWeakMap.get(id);
        }

        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        if (!this.complexParticipantPositions.containsKey(location)){
            return null;
        }
        else {
            try {
                Interaction originalInteraction = loadFromFile(this.complexParticipantPositions.get(location));
                Complex originalComplex = reloadOriginalComplex(originalInteraction);
                if (originalComplex == null){
                    logger.log(Level.SEVERE, "cannot reload complex participant "+id);
                }
                else{
                    for (ModelledParticipant p : originalComplex.getParticipants()){
                        ExtendedPsiXmlParticipant participant = (ExtendedPsiXmlParticipant)p;
                        if (participant.getId() == id){
                            return p;
                        }
                        else if (participant instanceof ModelledParticipantPool){
                            ModelledParticipantPool pool = (ModelledParticipantPool)participant;
                            for (ModelledParticipantCandidate candidate : pool){
                                ExtendedPsiXmlEntity extendedEntity = (ExtendedPsiXmlEntity)candidate;
                                if (extendedEntity.getId() == id){
                                    return candidate;
                                }
                            }
                        }
                    }
                    logger.log(Level.SEVERE, "cannot reload complex participant "+id);
                    return null;
                }

            } catch (IOException e) {
                logger.log(Level.SEVERE, "cannot reload complex participant "+id, e);
                throw new MIIOException("cannot reload complex participant "+id, e);
            } catch (JAXBException e) {
                logger.log(Level.SEVERE, "cannot reload complex participant "+id, e);
                throw new MIIOException("cannot reload complex participant "+id, e);
            } catch (XMLStreamException e) {
                logger.log(Level.SEVERE, "cannot reload complex participant "+id, e);
                throw new MIIOException("cannot reload complex participant "+id, e);
            }
            return null;
        }
    }

    private Complex reloadOriginalComplex(Interaction originalInteraction){
        if (originalInteraction instanceof Complex){
            return (Complex)originalInteraction;
        }
        // convert interaction evidence in a complex
        else if (originalInteraction instanceof ExtendedPsiXmlInteractionEvidence){
            switch (version){
                case v2_5_3:
                    return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)originalInteraction);
                case v3_0_0:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)originalInteraction);
                case v2_5_4:
                default:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)originalInteraction);
            }
        }
        // wrap modelled interaction
        else if (originalInteraction instanceof ExtendedPsiXmlModelledInteraction){
            switch (version){
                case v2_5_3:
                    return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlModelledInteractionComplexWrapper((ExtendedPsiXmlModelledInteraction)originalInteraction);
                case v3_0_0:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlModelledInteractionComplexWrapper((ExtendedPsiXmlModelledInteraction)originalInteraction);
                case v2_5_4:
                default:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlModelledInteractionComplexWrapper((ExtendedPsiXmlModelledInteraction)originalInteraction);
            }
        }
        // wrap basic interaction
        else if (originalInteraction instanceof ExtendedPsiXmlInteraction){
            switch (version){
                case v2_5_3:
                    return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)originalInteraction);
                case v3_0_0:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)originalInteraction);
                case v2_5_4:
                default:
                    return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)originalInteraction);
            }
        }
        else{
            return null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void registerComplexFeature(int id, ModelledFeature object) {
         this.complexFeatureWeakMap.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public ModelledFeature getComplexFeature(int id) {
        if (this.complexFeatureWeakMap.containsKey(id)){
            return this.complexFeatureWeakMap.get(id);
        }

        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        if (!this.complexFeaturePositions.containsKey(location)){
            return null;
        }
        else {
            try {
                Interaction originalInteraction = loadFromFile(this.complexParticipantPositions.get(location));
                Complex originalComplex = reloadOriginalComplex(originalInteraction);
                if (originalComplex == null){
                    logger.log(Level.SEVERE, "cannot reload complex feature "+id);
                }
                else{
                    for (ModelledParticipant p : originalComplex.getParticipants()){
                        ExtendedPsiXmlParticipant participant = (ExtendedPsiXmlParticipant)p;
                        for (ModelledFeature f : p.getFeatures()){
                            ExtendedPsiXmlFeature feature = (ExtendedPsiXmlFeature)f;
                            if (feature.getId() == id){
                                return f;
                            }
                        }

                        if (participant instanceof ModelledParticipantPool){
                            ModelledParticipantPool pool = (ModelledParticipantPool)participant;
                            for (ModelledParticipantCandidate candidate : pool){
                                for (ModelledFeature f : candidate.getFeatures()){
                                    ExtendedPsiXmlFeature feature = (ExtendedPsiXmlFeature)f;
                                    if (feature.getId() == id){
                                        return f;
                                    }
                                }
                            }
                        }
                    }
                    logger.log(Level.SEVERE, "cannot reload complex feature "+id);
                    return null;
                }

            } catch (IOException e) {
                logger.log(Level.SEVERE, "cannot reload complex feature "+id, e);
                throw new MIIOException("cannot reload complex feature "+id, e);
            } catch (JAXBException e) {
                logger.log(Level.SEVERE, "cannot reload complex feature "+id, e);
                throw new MIIOException("cannot reload complex feature "+id, e);
            } catch (XMLStreamException e) {
                logger.log(Level.SEVERE, "cannot reload complex feature "+id, e);
                throw new MIIOException("cannot reload complex feature "+id, e);
            }
            return null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void registerComplex(int id, Complex object) {
        this.complexWeakMap.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public Complex getComplex(int id) {
        if (this.complexWeakMap.containsKey(id)){
            return this.complexWeakMap.get(id);
        }

        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        if (!this.complexPositions.containsKey(location)){
            return null;
        }
        else {
            try {
                Interaction originalInteraction = loadFromFile(this.complexPositions.get(location));
                if (originalInteraction == null){
                    logger.log(Level.SEVERE, "cannot reload complex "+id);
                }
                else if (originalInteraction instanceof Complex){
                    return (Complex)originalInteraction;
                }
                // convert interaction evidence in a complex
                else if (originalInteraction instanceof ExtendedPsiXmlInteractionEvidence){
                    switch (version){
                        case v2_5_3:
                            return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)originalInteraction);
                        case v3_0_0:
                            return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)originalInteraction);
                        case v2_5_4:
                        default:
                            return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlInteractionEvidenceComplexWrapper((ExtendedPsiXmlInteractionEvidence)originalInteraction);
                    }
                }
                // wrap modelled interaction
                else if (originalInteraction instanceof ExtendedPsiXmlModelledInteraction){
                    switch (version){
                        case v2_5_3:
                            return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlModelledInteractionComplexWrapper((ExtendedPsiXmlModelledInteraction)originalInteraction);
                        case v3_0_0:
                            return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlModelledInteractionComplexWrapper((ExtendedPsiXmlModelledInteraction)originalInteraction);
                        case v2_5_4:
                        default:
                            return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlModelledInteractionComplexWrapper((ExtendedPsiXmlModelledInteraction)originalInteraction);
                    }
                }
                // wrap basic interaction
                else if (originalInteraction instanceof ExtendedPsiXmlInteraction){
                    switch (version){
                        case v2_5_3:
                            return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)originalInteraction);
                        case v3_0_0:
                            return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)originalInteraction);
                        case v2_5_4:
                        default:
                            return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlBasicInteractionComplexWrapper((ExtendedPsiXmlInteraction)originalInteraction);
                    }
                }
                else{
                    logger.log(Level.SEVERE, "cannot reload complex "+id);
                    return null;
                }

            } catch (IOException e) {
                logger.log(Level.SEVERE, "cannot reload complex "+id, e);
                throw new MIIOException("cannot reload complex "+id, e);
            } catch (JAXBException e) {
                logger.log(Level.SEVERE, "cannot reload complex "+id, e);
                throw new MIIOException("cannot reload complex "+id, e);
            } catch (XMLStreamException e) {
                logger.log(Level.SEVERE, "cannot reload complex "+id, e);
                throw new MIIOException("cannot reload complex "+id, e);
            }
            return null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void registerVariableParameterValue(int id, VariableParameterValue object) {
        this.variableParameterValueWeakMap.put(id, object);
    }

    /** {@inheritDoc} */
    @Override
    public VariableParameterValue getVariableParameterValue(int id) {
        if (this.variableParameterValueWeakMap.containsKey(id)){
            return this.variableParameterValueWeakMap.get(id);
        }

        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        if (!this.variableParameterValuePositions.containsKey(location)){
            return null;
        }
        else {
            try {
                Experiment originalExperiment = loadFromFile(this.variableParameterValuePositions.get(location));
                if (originalExperiment == null){
                    logger.log(Level.SEVERE, "cannot reload variable parameter value "+id);
                }
                else{
                    for (VariableParameter p : originalExperiment.getVariableParameters()){
                        for (VariableParameterValue value : p.getVariableValues()){
                            AbstractXmlVariableParameterValue xmlValue = (AbstractXmlVariableParameterValue)value;
                            if (xmlValue.getId() == id){
                                return xmlValue;
                            }
                        }
                    }
                    logger.log(Level.SEVERE, "cannot reload variable parameter value "+id);
                    return null;
                }

            } catch (IOException e) {
                logger.log(Level.SEVERE, "cannot reload variable parameter value "+id, e);
                throw new MIIOException("cannot reload variable parameter "+id, e);
            } catch (JAXBException e) {
                logger.log(Level.SEVERE, "cannot reload variable parameter value "+id, e);
                throw new MIIOException("cannot reload variable parameter "+id, e);
            } catch (XMLStreamException e) {
                logger.log(Level.SEVERE, "cannot reload variable parameter value "+id, e);
                throw new MIIOException("cannot reload variable parameter "+id, e);
            }
            return null;
        }
    }

    /** {@inheritDoc} */
    @Override
    public void clear() {
        this.mapOfReferencedAvailabilities.clear();

        this.experimentWeakMap.clear();
        this.interactorWeakMap.clear();
        this.interactionWeakMap.clear();
        this.complexWeakMap.clear();
        this.participantWeakMap.clear();
        this.featureWeakMap.clear();
        this.variableParameterValueWeakMap.clear();
        this.complexParticipantWeakMap.clear();
        this.complexFeatureWeakMap.clear();

        // clear method is called after each entry so we increase the number of parsed entries when clear method is called
        this.numberOfEntries++;
    }

    /** {@inheritDoc} */
    @Override
    public void close() {
        clear();

        this.numberOfEntries = 1;
        this.experimentPositions.clear();
        this.interactorPositions.clear();
        this.interactionPositions.clear();
        this.complexPositions.clear();
        this.participantPositions.clear();
        this.featurePositions.clear();
        this.variableParameterValuePositions.clear();
        this.complexParticipantPositions.clear();
        this.complexFeaturePositions.clear();
        this.encoding = null;

        try {
            this.randomAccessFile.close();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "cannot close random access file", e);
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsExperiment(int id) {
        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        return this.experimentWeakMap.containsKey(id) || this.experimentPositions.containsKey(location);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsAvailability(int id) {
        return this.mapOfReferencedAvailabilities.containsKey(id);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsInteraction(int id) {
        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        return this.interactionWeakMap.containsKey(id) || this.interactionPositions.containsKey(location);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsInteractor(int id) {
        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        return this.interactorWeakMap.containsKey(id) || this.interactorPositions.containsKey(location);    }

    /** {@inheritDoc} */
    @Override
    public boolean containsParticipant(int id) {
        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        return this.participantWeakMap.containsKey(id) || this.participantPositions.containsKey(location);    }

    /** {@inheritDoc} */
    @Override
    public boolean containsFeature(int id) {
        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        return this.featureWeakMap.containsKey(id) || this.featurePositions.containsKey(location);    }

    /** {@inheritDoc} */
    @Override
    public boolean containsVariableParameter(int id) {
        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        return this.variableParameterValueWeakMap.containsKey(id) || this.variableParameterValuePositions.containsKey(location);    }

    /** {@inheritDoc} */
    @Override
    public boolean containsComplex(int id) {
        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        return this.complexWeakMap.containsKey(id) || this.complexPositions.containsKey(location);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsComplexParticipant(int id) {
        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        return this.complexParticipantWeakMap.containsKey(id) || this.complexParticipantPositions.containsKey(location);
    }

    /** {@inheritDoc} */
    @Override
    public boolean containsComplexFeature(int id) {
        EntryLocation location = new EntryLocation(this.numberOfEntries, id);
        return this.complexFeatureWeakMap.containsKey(id) || this.complexFeaturePositions.containsKey(location);
    }

    /** {@inheritDoc} */
    @Override
    public ModelledFeature registerModelledFeatureLoadedFrom(Feature f) {
        if (f instanceof ExtendedPsiXmlFeature){
            return getComplexFeature(((ExtendedPsiXmlFeature)f).getId());
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public ModelledEntity registerModelledParticipantLoadedFrom(Entity f) {
        if (f instanceof ExtendedPsiXmlParticipant){
            return getComplexParticipant(((ExtendedPsiXmlParticipant)f).getId());
        }
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Complex registerComplexLoadedFrom(Interaction f) {
        if (f instanceof PsiXmlInteraction){
            return getComplex(((PsiXmlInteraction)f).getId());
        }
        return null;
    }

    private <T extends Object> T loadFromFile(long id) throws IOException, JAXBException, XMLStreamException {

        InputStream in = new NonCloseableInputStreamWrapper(Channels.newInputStream(this.randomAccessFile.getChannel().position(id)));
        T obj = null;
        XMLStreamReader reader = null;
        try{
            if (this.xmlif == null){
                this.xmlif = XMLInputFactory2.newInstance();
            }
            reader = xmlif.createXMLStreamReader(in, this.encoding);

            //Create the filter (to add namespace) and set the xmlReader as its parent.
            XmlReaderWithDefaultNamespace filteredReader = new XmlReaderWithDefaultNamespace(reader, this.namespaceUri);

            obj = (T)this.unmarshaller.unmarshal(filteredReader);
        }
        finally {
            if (reader != null){
                reader.close();
            }
        }
        return obj;
    }

    /**
     * Indexes references component of the given file. that is experiments, interaction, interactor, feature and
     * participant so that we know where they are in the file and we can jump in the right position should we want to
     * extract one of them.
     *
     * @param f the file to index.
     * @throws java.io.IOException if any.
     */
    public void buildPositionIndex( File f ) throws IOException {

        long start = System.currentTimeMillis();
        long length = f.length();
        logger.info( "length = " + length );

        CountingInputStream fis = null;
        StringBuilder sb = new StringBuilder( 100 );
        try{
            fis = new CountingInputStream(new BufferedInputStream(new FileInputStream( f )));

            long startPos = 0;
            char read = ' ';
            boolean recording = false;
            byte[] buf = new byte[1];

            int currentId = -1;
            long currentExperimentPost = 0;
            long currentInteractionPos = 0;
            int currentEntry=0;
            boolean hasReadEncoding = false;

            while ( -1 != nextByte(fis, buf)) {
                read = (char)(buf[0] & 0xFF);

                if ( recording ) {
                    if ( !isAlphabeticalChar( read ) ) {
                        if ( read == '/' ) {

                            // search for '>' and that's our position
                            while ( -1 != nextByte(fis, buf) ) {
                                read = (char)(buf[0] & 0xFF);
                                if ( read == '>') {
                                    break;
                                }
                            }

                            // it was a closing tag (<abc/> or </abc> ... problem will occur with <abc />)
                            recording = false;

                        }
                        else if ( read == '?' && !hasReadEncoding) {

                            // search for '>' and that's our position
                            while ( -1 != nextByte(fis, buf) ) {
                                read = (char)(buf[0] & 0xFF);
                                // add alphabetical char
                                sb.append( new String(buf) );
                                if ( read == '>') {
                                    break;
                                }
                            }

                            // it was a closing tag (<abc/> or </abc> ... problem will occur with <abc />)
                            recording = false;
                            hasReadEncoding = true;
                            // check what start tag it is
                            String line = sb.toString();
                            Pattern encodingRegex = Pattern.compile(".*encoding=['\"](.*?)['\"].*");
                            Matcher encodingMatcher = encodingRegex.matcher(line);
                            if (encodingMatcher.find()) {
                                this.encoding = encodingMatcher.group(1);
                            }
                        }
                        else if ( read == '!' ) {

                            // This is the beginning of a comments.
                            // Now fast forward until the end of the comment: '-->'
                            recording = false;

                            byte c1 = ' ',
                                    c2 = ' ',
                                    c3 = ' ';
                            logger.fine("Skiping comment");
                            while ( -1 != nextByte(fis, buf) ) {
                                c1 = c2;
                                c2 = c3;
                                c3 = buf[0];

                                if ( c1 == '-' && c2 == '-' && c3 == '>' ) {
                                    // found it
                                    logger.fine("End of comment");
                                    break;
                                }
                            }

                        } else {
                            // check what start tag it is
                            String line = sb.toString();

                            if ( "entry".equalsIgnoreCase( line ) ) {

                                currentEntry++;

                            } else if ( "experimentDescription".equalsIgnoreCase( line ) ) {

                                int result = getId( fis, buf );
                                currentId = result;
                                currentExperimentPost = startPos;

                                this.experimentPositions.put(new EntryLocation(currentEntry, currentId), startPos);

                            } else if ( "interactor".equalsIgnoreCase( line ) ) {

                                int result = getId( fis, buf );
                                currentId = result;

                                this.interactorPositions.put(new EntryLocation(currentEntry, currentId), startPos);

                            } else if ( "interaction".equalsIgnoreCase( line ) ) {

                                int result = getId( fis, buf );
                                currentId = result;
                                currentInteractionPos = startPos;

                                this.interactionPositions.put(new EntryLocation(currentEntry, currentId), startPos);

                            }
                            else if ( "abstractInteraction".equalsIgnoreCase( line ) ) {

                                int result = getId( fis,buf );
                                currentId = result;
                                currentInteractionPos = startPos;
                                EntryLocation location = new EntryLocation(currentEntry, currentId);
                                this.complexPositions.put(location, startPos);
                                this.interactionPositions.put(location, startPos);

                            }else if ( "participant".equalsIgnoreCase( line ) ) {

                                int result = getId( fis, buf );
                                currentId = result;

                                this.participantPositions.put(new EntryLocation(currentEntry, currentId), currentInteractionPos);

                            } else if ( "feature".equalsIgnoreCase( line ) ) {

                                int result = getId( fis, buf );
                                currentId = result;
                                currentInteractionPos = startPos;

                                this.featurePositions.put(new EntryLocation(currentEntry, currentId), currentInteractionPos);
                            }
                            else if ( "variableValue".equalsIgnoreCase( line ) ) {

                                int result = getId( fis, buf );
                                currentId = result;
                                currentInteractionPos = startPos;

                                this.variableParameterValuePositions.put(new EntryLocation(currentEntry, currentId), currentExperimentPost);
                            }
                            else if ( "interactorCandidate".equalsIgnoreCase( line ) ) {

                                int result = getId( fis, buf );
                                currentId = result;

                                this.participantPositions.put(new EntryLocation(currentEntry, currentId), currentInteractionPos);

                            }

                            recording = false;
                        }
                    } else {
                        // add alphabetical char
                        sb.append( new String(buf) );
                    }
                }

                if ( read == '<' ) {
                    // start recording
                    startPos = fis.getByteCount() -1; // we want the '<' included
                    recording = true;
                    sb.setLength(0);
                }

            } // while can read

            long stop = System.currentTimeMillis();

            logger.info( "Time elapsed: " + ( stop - start ) + "ms" );
        }
        finally {

            if (fis != null){
                fis.close();
            }
        }
    }

    private int getId( CountingInputStream r, byte[] buf) throws IOException {

        int id = -1;

        StringBuilder sb = new StringBuilder( 20 );
        while ( -1 != nextByte(r, buf) ) {

            char read = (char)(buf[0] & 0xFF);
            if ( read == '>' ) {
                // completed the tag, extract the id
                Matcher matcher = ID_PATTERN.matcher( sb.toString() );
                if ( matcher.matches() ) {
                    String strId = matcher.group( 1 );
                    id = Integer.parseInt( strId );
                }

                break; // stop here
            } else {
                sb.append( new String(buf) );
            }
        }

        return id;
    }

    private boolean isAlphabeticalChar( char c ) {
        return ( ( c >= 'a' && c <= 'z' ) || ( ( c >= 'A' && c <= 'Z' ) ) );
    }

    private static int nextByte(CountingInputStream cis, byte[] buf) throws IOException {
        int result = cis.read(buf);
        while (result != -1 && buf[0] == 0 ){
            result = cis.read(buf);
        }
        return result;
    }

}
