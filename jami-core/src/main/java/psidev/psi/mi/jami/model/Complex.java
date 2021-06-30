package psidev.psi.mi.jami.model;

import com.thoughtworks.xstream.XStream;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * An interactor composed of interacting molecules that can be copurified.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/11/12</pre>
 */
public interface Complex extends Interactor, ModelledInteraction, NamedInteraction<ModelledParticipant> {

    /**
     * Constant <code>COMPLEX="complex"</code>
     */
    public static final String COMPLEX = "complex";
    /**
     * Constant <code>COMPLEX_MI="MI:0314"</code>
     */
    public static final String COMPLEX_MI = "MI:0314";

    /**
     * Complex accession if the complex has been curated under the Complex Portal curation rules.
     * It can be null if the complex is not registered in the Complex Portal.
     * This complex accession should be a shortcut to the complex-primary Xref in the collection of xrefs.
     * Ex: CPX-123
     *
     * @return the complex accession
     */
    public String getComplexAc();

    /**
     * Complex version if the complex has been curated under the Complex Portal curation rules.
     * It can be null if the complex is not registered in the Complex Portal.
     * This complex version should be a shortcut to the complex-primary Xref version in the collection of xrefs.
     * Ex: 1
     *
     * @return the complex version
     */
    public String getComplexVersion();

    /**
     * Assign a complex accession to a complex.
     * It will add the new complex-primary ref to the collection of xrefs
     *
     * @param accession : the complex accession. If the version is added to the accession e.g. CPX-1234.2 the complex will be updated with the corresponding version, if not it is assumed version 1
     * @throws IllegalArgumentException if
     *                                  - the accession is null or empty
     */
    public void assignComplexAc(String accession);


    /**
     * Assign a complex accession to a complex.
     * It will add the new complex-primary ref to the collection of xrefs
     *
     * @param accession : the complex accession
     * @param version   : the version of the complex if it is provided. If version is null it will create the complex with version 1
     * @throws IllegalArgumentException if
     *                                  - the accession is null or empty
     */
    public void assignComplexAc(String accession, String version);

    /**
     * The physical properties for this complex.
     * It is a shortcut which should point to the first complex-properties annotation in the collection of annotations.
     * Example: Molecular mass = 154 kDa
     *
     * @return the properties
     */
    public String getPhysicalProperties();

    /**
     * Sets the physical properties of this complex.
     * It will remove the old complex-properties annotation from the collection of annotations and replace it
     * with the new complex-properties annotation. If the new complex-properties is null, all the existing complex-properties annotations will be removed from the
     * collection of annotations
     *
     * @param properties : complex properties
     */
    public void setPhysicalProperties(String properties);

    /**
     * The recommended name of a complex.
     * It is a shortcut which should point to the first complex recommended name alias in the collection of aliases.
     *
     * @return the recommended name
     */
    public String getRecommendedName();

    /**
     * Sets the recommended name of this complex.
     * It will remove the old recommended name from the collection of aliases and replace it
     * with the new recommended name. If the new recommended name is null, all the existing recommended names will be removed from the
     * collection of aliases
     *
     * @param name : the recommended name
     */
    public void setRecommendedName(String name);

    /**
     * The systematic name of a complex.
     * It is a shortcut which should point to the first complex systematic name alias in the collection of aliases.
     *
     * @return the systematic name
     */
    public String getSystematicName();

    /**
     * Sets the systematic name of this complex.
     * It will remove the old systematic name from the collection of aliases and replace it
     * with the new systematic name. If the new systematic name is null, all the existing systematic names will be removed from the
     * collection of aliases
     *
     * @param name : the systematic name
     */
    public void setSystematicName(String name);

    /**
     *
     */
    default Collection<ModelledParticipant> getComparableParticipants() {
        Collection<ModelledParticipant> allParticipantsCollection = new ArrayList<>();
        for (ModelledParticipant modelledParticipant : this.getParticipants()) {
            if (modelledParticipant.getInteractor() instanceof Complex) {
                allParticipantsCollection.addAll(expandComplexIntoParticipants(modelledParticipant));
            } else {
                allParticipantsCollection.add(modelledParticipant);
            }
        }

        List<ModelledParticipant> comparableParticipants = allParticipantsCollection.stream().filter(
                participant -> participant instanceof Protein || //protein
                        participant instanceof InteractorPool) //sets
                .collect(Collectors.toList());
        return comparableParticipants;
    }

    /**
     *
     */
    static Collection<ModelledParticipant> expandComplexIntoParticipants(ModelledParticipant parentParticipant) {

        Collection<ModelledParticipant> expandedModelledParticipants = new ArrayList<>();
        if (parentParticipant.getInteractor() instanceof Complex) {
            Complex complex = (Complex) parentParticipant.getInteractor();
            for (ModelledParticipant complexParticipant : complex.getParticipants()) {

                // clone as we do not want to change the stoichiometry of interactor complex participants
                ModelledParticipant expandedModelledParticipant = (ModelledParticipant) new XStream().fromXML(new XStream().toXML(complexParticipant));

                // expand stoichiometry
                int minStoichiometry = 0;
                int maxStoichiometry = 0;
                Stoichiometry expandedStoichiometry = null;
                if (complexParticipant.getStoichiometry() != null && parentParticipant.getStoichiometry() != null) {
                    minStoichiometry = (complexParticipant.getStoichiometry().getMinValue())
                            *
                            (parentParticipant.getStoichiometry().getMinValue());
                    maxStoichiometry = (complexParticipant.getStoichiometry().getMaxValue())
                            *
                            (parentParticipant.getStoichiometry().getMaxValue());
                    Class stoichiometryClass = expandedModelledParticipant.getStoichiometry().getClass();
                    try {
                        expandedStoichiometry = (Stoichiometry) stoichiometryClass.getConstructor(Integer.TYPE, Integer.TYPE).newInstance(minStoichiometry, maxStoichiometry);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                expandedModelledParticipant.setStoichiometry(expandedStoichiometry);
                expandedModelledParticipants.add(expandedModelledParticipant);
            }
            return expandedModelledParticipants;
        }

        // return back the participant if not complex
        expandedModelledParticipants.add(parentParticipant);
        return expandedModelledParticipants;
    }
}

