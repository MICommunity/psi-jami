package psidev.psi.mi.jami.xml.utils;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlAllostery;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlCooperativityEvidence;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlPreAssembly;
import psidev.psi.mi.jami.xml.listener.PsiXmlParserListener;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Utility class for psixml
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>18/07/13</pre>
 */
public class PsiXmlUtils {

    /** Constant <code>UNSPECIFIED="unspecified"</code> */
    public static final String UNSPECIFIED = "unspecified";
    /** Constant <code>YEAR_FORMAT</code> */
    public final static DateFormat YEAR_FORMAT = new SimpleDateFormat("yyyy");
    /** Constant <code>STOICHIOMETRY_PREFIX="stoichiometry: "</code> */
    public static final String STOICHIOMETRY_PREFIX = "stoichiometry: ";
    /** Constant <code>ENTRYSET_TAG="entrySet"</code> */
    public static final String ENTRYSET_TAG = "entrySet";
    /** Constant <code>ENTRY_TAG="entry"</code> */
    public static final String ENTRY_TAG = "entry";
    /** Constant <code>EXPERIMENTLIST_TAG="experimentList"</code> */
    public static final String EXPERIMENTLIST_TAG = "experimentList";
    /** Constant <code>INTERACTORLIST_TAG="interactorList"</code> */
    public static final String INTERACTORLIST_TAG = "interactorList";
    /** Constant <code>INTERACTIONLIST_TAG="interactionList"</code> */
    public static final String INTERACTIONLIST_TAG = "interactionList";
    /** Constant <code>EXPERIMENT_TAG="experimentDescription"</code> */
    public static final String EXPERIMENT_TAG = "experimentDescription";
    /** Constant <code>INTERACTOR_TAG="interactor"</code> */
    public static final String INTERACTOR_TAG = "interactor";
    /** Constant <code>INTERACTION_TAG="interaction"</code> */
    public static final String INTERACTION_TAG = "interaction";
    /** Constant <code>ABSTRACT_INTERACTION_TAG="abstractInteraction"</code> */
    public static final String ABSTRACT_INTERACTION_TAG = "abstractInteraction";
    /** Constant <code>SOURCE_TAG="source"</code> */
    public static final String SOURCE_TAG = "source";
    /** Constant <code>AVAILABILITYLIST_TAG="availabilityList"</code> */
    public static final String AVAILABILITYLIST_TAG = "availabilityList";
    /** Constant <code>AVAILABILITY_TAG="availability"</code> */
    public static final String AVAILABILITY_TAG = "availability";
    /** Constant <code>ATTRIBUTELIST_TAG="attributeList"</code> */
    public static final String ATTRIBUTELIST_TAG = "attributeList";
    /** Constant <code>ATTRIBUTE_TAG="attribute"</code> */
    public static final String ATTRIBUTE_TAG = "attribute";

    /** Constant <code>LINE_BREAK="\n"</code> */
    public static final String LINE_BREAK = "\n";
    /** Constant <code>XML_BUFFER_SIZE=2048</code> */
    public static final int XML_BUFFER_SIZE = 2048;

    /** Constant <code>Xml300_NAMESPACE_URI="http://psi.hupo.org/mi/mif300"</code> */
    public final static String Xml300_NAMESPACE_URI = "http://psi.hupo.org/mi/mif300";
    /** Constant <code>Xml254_NAMESPACE_URI="http://psi.hupo.org/mi/mif"</code> */
    public final static String Xml254_NAMESPACE_URI = "http://psi.hupo.org/mi/mif";
    /** Constant <code>Xml253_NAMESPACE_URI="net:sf:psidev:mi"</code> */
    public final static String Xml253_NAMESPACE_URI = "net:sf:psidev:mi";
    /** Constant <code>XML_SCHEMA="http://www.w3.org/2001/XMLSchema-instan"{trunked}</code> */
    public final static String XML_SCHEMA = "http://www.w3.org/2001/XMLSchema-instance";
    /** Constant <code>XML_SCHEMA_PREFIX="xsi"</code> */
    public final static String XML_SCHEMA_PREFIX = "xsi";
    /** Constant <code>PSI_SCHEMA_300_LOCATION="http://psi.hupo.org/mi/mif300 https://r"{trunked}</code> */
    public final static String PSI_SCHEMA_300_LOCATION = "http://psi.hupo.org/mi/mif300 https://raw.githubusercontent.com/HUPO-PSI/miXML/master/3.0/src/MIF300.xsd";
    /** Constant <code>PSI_SCHEMA_254_LOCATION="http://psi.hupo.org/mi/mif https://raw."{trunked}</code> */
    public final static String PSI_SCHEMA_254_LOCATION = "http://psi.hupo.org/mi/mif https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF254.xsd";
    /** Constant <code>PSI_SCHEMA_253_LOCATION="net:sf:psidev:mi https://raw.githubuser"{trunked}</code> */
    public final static String PSI_SCHEMA_253_LOCATION = "net:sf:psidev:mi https://raw.githubusercontent.com/HUPO-PSI/miXML/master/2.5/src/MIF253.xsd";
    /** Constant <code>SCHEMA_LOCATION_ATTRIBUTE="schemaLocation"</code> */
    public final static String SCHEMA_LOCATION_ATTRIBUTE = "schemaLocation";
    /** Constant <code>MINOR_VERSION_ATTRIBUTE="minorVersion"</code> */
    public final static String MINOR_VERSION_ATTRIBUTE="minorVersion";
    /** Constant <code>VERSION_ATTRIBUTE="version"</code> */
    public final static String VERSION_ATTRIBUTE="version";
    /** Constant <code>LEVEL_ATTRIBUTE="level"</code> */
    public final static String LEVEL_ATTRIBUTE="level";

    /**
     * <p>extractCooperativeEffectFrom.</p>
     *
     * @param annots a {@link java.util.Collection} object.
     * @param experiments a {@link java.util.Collection} object.
     * @param listener a {@link psidev.psi.mi.jami.xml.listener.PsiXmlParserListener} object.
     * @return a {@link psidev.psi.mi.jami.model.CooperativeEffect} object.
     */
    public static CooperativeEffect extractCooperativeEffectFrom(Collection<Annotation> annots, Collection<Experiment> experiments,
                                                                 PsiXmlParserListener listener){
        if (!annots.isEmpty()){
            Annotation allostery = null;
            Annotation preAssembly = null;
            Annotation outcome = null;
            Annotation response = null;
            Annotation allostericMolecule = null;
            Annotation allostericPTM = null;
            Annotation allostericEffector = null;
            Annotation allosteryType = null;
            Annotation allosteryMechanism = null;
            boolean isValidCooperativeEffect = true;
            List<Annotation> affectedInteractions = new ArrayList<Annotation>(annots.size());
            for (Annotation a : annots){
                if (AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.ALLOSTERY_ID, CooperativeEffect.ALLOSTERY)){
                    if ((allostery != null || preAssembly != null) && listener != null){
                        listener.onSyntaxWarning((FileSourceContext)a, "We found several allostery/preassembly mechanism attributes where only one is expected. " +
                                "It will not load the cooperative effect attributes and keep them as simple attributes");
                        isValidCooperativeEffect = false;
                        break;
                    }
                    allostery = a;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.PREASSEMBLY_ID, CooperativeEffect.PREASSEMBLY)){
                    if ((allostery != null || preAssembly != null) && listener != null){
                        listener.onSyntaxWarning((FileSourceContext)a, "We found several allostery/preassembly mechanism attributes where only one is expected. " +
                                "It will not load the cooperative effect attributes and keep them as simple attributes");
                        isValidCooperativeEffect = false;
                        break;
                    }
                    preAssembly = a;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.NEGATIVE_EFFECT_ID, CooperativeEffect.NEGATIVE_EFFECT)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.POSITIVE_EFFECT_ID, CooperativeEffect.POSITIVE_EFFECT)){
                    if (outcome != null && listener != null){
                        listener.onSyntaxWarning((FileSourceContext)a, "We found several cooperative effect outcome attributes where only one is expected. " +
                                "It will not load the cooperative effect attributes and keep them as simple attributes");
                        isValidCooperativeEffect = false;
                        break;
                    }
                    outcome = a;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.ALLOSTERIC_K_RESPONSE_ID, CooperativeEffect.ALLOSTERIC_K_RESPONSE)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.ALLOSTERIC_V_RESPONSE_ID, CooperativeEffect.ALLOSTERIC_V_RESPONSE)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.ALTERED_PHYSICO_COMPATIBILITY_ID, CooperativeEffect.ALTERED_PHYSICO_COMPATIBILITY)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.BINDING_HIDING_ID, CooperativeEffect.BINDING_HIDING)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.COMPOSITE_BINDING_ID, CooperativeEffect.COMPOSITE_BINDING)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.PRE_ORGANIZATION_ID, CooperativeEffect.PRE_ORGANIZATION)){
                    if (response != null && listener != null){
                        listener.onSyntaxWarning((FileSourceContext)a, "We found several cooperative effect response attributes where only one is expected. " +
                                "It will not load the cooperative effect attributes and keep them as simple attributes");
                        isValidCooperativeEffect = false;
                        break;
                    }
                    response = a;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.ALLOSTERIC_MOLECULE_ID, CooperativeEffect.ALLOSTERIC_MOLECULE)
                        && a.getValue() != null){
                    if (allostericMolecule != null && listener != null){
                        listener.onSyntaxWarning((FileSourceContext)a, "We found several allosteric molecule attributes where only one is expected. " +
                                "It will not load the cooperative effect attributes and keep them as simple attributes");
                        isValidCooperativeEffect = false;
                        break;
                    }
                    allostericMolecule = a;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.ALLOSTERIC_EFFECTOR_ID, CooperativeEffect.ALLOSTERIC_EFFECTOR)
                        && a.getValue() != null){
                    if (allostericEffector != null && listener != null){
                        listener.onSyntaxWarning((FileSourceContext)a, "We found several allosteric molecule effector attributes where only one is expected. " +
                                "It will not load the cooperative effect attributes and keep them as simple attributes");
                        isValidCooperativeEffect = false;
                        break;
                    }
                    allostericEffector = a;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.ALLOSTERIC_PTM_ID, CooperativeEffect.ALLOSTERIC_PTM)
                        && a.getValue() != null){
                    if (allostericPTM != null && listener != null){
                        listener.onSyntaxWarning((FileSourceContext)a, "We found several allosteric feature modification attributes where only one is expected. " +
                                "It will not load the cooperative effect attributes and keep them as simple attributes");
                        isValidCooperativeEffect = false;
                        break;
                    }
                    allostericPTM = a;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.HETEROTROPIC_ALLOSTERY_ID, CooperativeEffect.HETEROTROPIC_ALLOSTERY)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.HOMOTROPIC_ALLOSTERY_ID, CooperativeEffect.HOMOTROPIC_ALLOSTERY)){
                    if (allosteryType != null && listener != null){
                        listener.onSyntaxWarning((FileSourceContext)a, "We found several allostery type attributes where only one is expected. " +
                                "It will not load the cooperative effect attributes and keep them as simple attributes");
                        isValidCooperativeEffect = false;
                        break;
                    }
                    allosteryType = a;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.AFFECTED_INTERACTION_ID, CooperativeEffect.AFFECTED_INTERACTION)
                        && a.getValue() != null){
                    affectedInteractions.add(a);
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.ALLOSTERIC_DYNAMIC_CHANGE_ID, CooperativeEffect.ALLOSTERIC_DYNAMIC_CHANGE)
                        || AnnotationUtils.doesAnnotationHaveTopic(a, CooperativeEffect.ALLOSTERIC_STRUCTURE_CHANE_ID, CooperativeEffect.ALLOSTERIC_STRUCTURE_CHANE)){
                    if (allosteryMechanism != null && listener != null){
                        listener.onSyntaxWarning((FileSourceContext)a, "We found several allostery mechanism attributes where only one is expected. " +
                                "It will not load the cooperative effect attributes and keep them as simple attributes");
                        isValidCooperativeEffect = false;
                        break;
                    }
                    allosteryMechanism = a;
                }
            }

            if (outcome != null && isValidCooperativeEffect){
                CooperativeEffect effect = null;
                // create pre-assembly
                if (preAssembly != null){
                    effect = new XmlPreAssembly(outcome.getTopic());
                    XmlPreAssembly preAssemblyEffect = (XmlPreAssembly)effect;
                    preAssemblyEffect.setSourceLocator((PsiXmlLocator)((FileSourceContext)preAssembly).getSourceLocator());

                    // remove annotations
                    annots.remove(outcome);
                    annots.remove(preAssembly);

                    // add affected interactions
                    if (!affectedInteractions.isEmpty()){
                        for (Annotation ann : affectedInteractions){
                            try{
                                preAssemblyEffect.addAffectedInteractionRef(Integer.parseInt(ann.getValue()), (PsiXmlLocator) ((FileSourceContext) ann).getSourceLocator());
                                annots.remove(ann);
                            }
                            catch (NumberFormatException e){
                                if (listener != null){
                                    listener.onSyntaxWarning((FileSourceContext)ann, "The affected interaction annotation does not refer to a valid interaction id and will be ignored.");
                                }
                            }
                        }
                    }
                }
                // create allostery
                else if (allostery != null && allostericMolecule != null
                        && (allostericEffector != null || allostericPTM != null)){
                    XmlAllostery xmlAllostery = null;
                    if (allostericEffector != null){
                        try{
                            int refEffector = Integer.parseInt(allostericEffector.getValue());
                            int refMolecule = Integer.parseInt(allostericMolecule.getValue());
                            effect = new XmlAllostery<MoleculeEffector>(outcome.getTopic());
                            xmlAllostery = (XmlAllostery)effect;
                            xmlAllostery.setAllostericMoleculeRef(refMolecule, (PsiXmlLocator)((FileSourceContext)allostericMolecule).getSourceLocator());
                            xmlAllostery.setAllostericEffectorRef(refEffector, (PsiXmlLocator)((FileSourceContext)allostericEffector).getSourceLocator());

                            // remove annotations
                            annots.remove(allostery);
                            annots.remove(allostericEffector);
                            annots.remove(allostericMolecule);
                            annots.remove(outcome);
                        }
                        catch (NumberFormatException e){
                            if (listener != null){
                                listener.onSyntaxWarning((FileSourceContext)allostericEffector, "The molecule effector/allosteric molecule annotation does not refer to a valid participant id and will be ignored.");
                            }
                        }
                    }
                    else{
                        try{
                            int refEffector = Integer.parseInt(allostericPTM.getValue());
                            int refMolecule = Integer.parseInt(allostericMolecule.getValue());
                            effect = new XmlAllostery<FeatureModificationEffector>(outcome.getTopic());
                            xmlAllostery = (XmlAllostery)effect;
                            xmlAllostery.setAllostericMoleculeRef(refMolecule, (PsiXmlLocator)((FileSourceContext)allostericMolecule).getSourceLocator());
                            xmlAllostery.setAllostericPTMRef(refEffector, (PsiXmlLocator)((FileSourceContext)allostericPTM).getSourceLocator());

                            // remove annotations
                            annots.remove(allostery);
                            annots.remove(allostericPTM);
                            annots.remove(allostericMolecule);
                            annots.remove(outcome);
                        }
                        catch (NumberFormatException e){
                            if (listener != null){
                                listener.onSyntaxWarning((FileSourceContext)allostericPTM, "The feature modification effector/allosteric molecule annotation does not refer to a valid participant id/feature id and will be ignored.");
                            }
                        }
                    }

                    // add affected interactions
                    if (!affectedInteractions.isEmpty()){
                        for (Annotation ann : affectedInteractions){
                            try{
                                xmlAllostery.addAffectedInteractionRef(Integer.parseInt(ann.getValue()), (PsiXmlLocator) ((FileSourceContext) ann).getSourceLocator());
                                annots.remove(ann);
                            }
                            catch (NumberFormatException e){
                                if (listener != null){
                                    listener.onSyntaxWarning((FileSourceContext)ann, "The affected interaction annotation does not refer to a valid interaction id and will be ignored.");
                                }
                            }
                        }
                    }

                    // add allosteric type
                    if (allosteryType != null){
                        xmlAllostery.setAllosteryType(allosteryType.getTopic());
                        annots.remove(allosteryType);
                    }

                    // add allosteric mechanism
                    if (allosteryMechanism != null){
                        xmlAllostery.setAllostericMechanism(allosteryMechanism.getTopic());
                        annots.remove(allosteryMechanism);
                    }
                }

                if (effect != null){
                    // add response
                    if (response != null){
                        effect.setResponse(response.getTopic());
                        annots.remove(response);
                    }

                    // add experimental evidences
                    if (experiments != null && !experiments.isEmpty()){
                        for (Experiment exp : experiments){
                            CooperativityEvidence evidence = new XmlCooperativityEvidence(exp);
                            effect.getCooperativityEvidences().add(evidence);
                        }
                    }
                }
                return effect;
            }
            else{
                return null;
            }
        }

        return null;
    }

    /**
     * <p>writeCompleteNamesElement.</p>
     *
     * @param shortLabel a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param aliases a {@link java.util.Collection} object.
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public static void writeCompleteNamesElement(String shortLabel, String fullName, Collection<Alias> aliases, XMLStreamWriter writer,
                                                 PsiXmlElementWriter<Alias> aliasWriter) throws XMLStreamException {
        // write names
        boolean hasShortLabel = shortLabel != null;
        boolean hasFullLabel = fullName != null;
        boolean hasAliases = !aliases.isEmpty();
        if (hasShortLabel || hasFullLabel || hasAliases){
            writer.writeStartElement("names");
            // write shortname
            if (hasShortLabel){
                writer.writeStartElement("shortLabel");
                writer.writeCharacters(shortLabel);
                writer.writeEndElement();
            }
            // write fullname
            if (hasFullLabel){
                writer.writeStartElement("fullName");
                writer.writeCharacters(fullName);
                writer.writeEndElement();
            }

            // write aliases
            for (Alias alias : aliases){
                aliasWriter.write(alias);
            }
            // write end names
            writer.writeEndElement();
        }
    }

    /**
     * <p>writeCompleteNamesForExperiment.</p>
     *
     * @param xmlExperiment a {@link psidev.psi.mi.jami.model.NamedExperiment} object.
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    public static void writeCompleteNamesForExperiment(NamedExperiment xmlExperiment, XMLStreamWriter writer,
                                                       PsiXmlElementWriter<Alias> aliasWriter) throws XMLStreamException {
        boolean hasExperimentFullLabel = xmlExperiment.getFullName() != null;
        boolean hasPublicationTitle = xmlExperiment.getPublication() != null && xmlExperiment.getPublication().getTitle() != null;

        PsiXmlUtils.writeCompleteNamesElement(xmlExperiment.getShortName(),
                hasExperimentFullLabel ? xmlExperiment.getFullName() : (hasPublicationTitle ? xmlExperiment.getPublication().getTitle() : null),
                xmlExperiment.getAliases(),
                writer,
                aliasWriter);
    }
}
