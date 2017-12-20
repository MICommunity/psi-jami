package psidev.psi.mi.jami.utils.comparator.interactor;

import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.comparator.alias.UnambiguousAliasComparator;
import psidev.psi.mi.jami.utils.comparator.xref.UnambiguousExternalIdentifierComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Unambiguous interactor comparator
 * It will only compare identifiers if one interactor does have identifiers using UnambiguousIdentifierComparator. Otherwise, it will first compare shortNames (case sensitive)
 * fullnames(case sensitive) and if the shortNames and fullnames are the same, it will compare the aliases using UnambiguousAliasComparator.
 *
 * This comparator will ignore all the other properties of an interactor.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>21/12/12</pre>
 */
public class UnambiguousInteractorBaseComparator extends InteractorBaseComparator {
    private static UnambiguousInteractorBaseComparator unambiguousInteractorComparator;

    /**
     * <p>Constructor for UnambiguousInteractorBaseComparator.</p>
     */
    public UnambiguousInteractorBaseComparator() {
        super(new UnambiguousExternalIdentifierComparator(), new UnambiguousAliasComparator());
    }

    /**
     * <p>getIdentifierComparator</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.comparator.xref.UnambiguousExternalIdentifierComparator} object.
     */
    public UnambiguousExternalIdentifierComparator getIdentifierComparator() {
        return (UnambiguousExternalIdentifierComparator)super.getIdentifierComparator();
    }

    /**
     * <p>getAliasComparator</p>
     *
     * @return a {@link psidev.psi.mi.jami.utils.comparator.alias.UnambiguousAliasComparator} object.
     */
    public UnambiguousAliasComparator getAliasComparator() {
        return (UnambiguousAliasComparator)super.getAliasComparator();
    }

    /**
     * Use DefaultConfidenceComparator to know if two confidences are equals.
     *
     * @param interactor1 a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param interactor2 a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @return true if the two confidences are equal
     */
    public static boolean areEquals(Interactor interactor1, Interactor interactor2){
        if (unambiguousInteractorComparator == null){
            unambiguousInteractorComparator = new UnambiguousInteractorBaseComparator();
        }

        return unambiguousInteractorComparator.compare(interactor1, interactor2) == 0;
    }

    /**
     * <p>hashCode</p>
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @return the hashcode consistent with the equals method for this comparator
     */
    public static int hashCode(Interactor interactor){
        if (unambiguousInteractorComparator == null){
            unambiguousInteractorComparator = new UnambiguousInteractorBaseComparator();
        }

        if (interactor == null){
            return 0;
        }

        int hashcode = 31;
        if (!interactor.getIdentifiers().isEmpty()){
            List<Xref> list1 = new ArrayList<Xref>(interactor.getIdentifiers());

            Collections.sort(list1, unambiguousInteractorComparator.getIdentifierCollectionComparator().getObjectComparator());
            for (Xref id : list1){
                hashcode = 31*hashcode + UnambiguousExternalIdentifierComparator.hashCode(id);
            }
        }
        else{
            hashcode = 31*hashcode + interactor.getShortName().hashCode();
            hashcode = 31*hashcode + (interactor.getFullName() != null ? interactor.getFullName().hashCode() : 0);

            List<Alias> list1 = new ArrayList<Alias>(interactor.getAliases());

            Collections.sort(list1, unambiguousInteractorComparator.getAliasCollectionComparator().getObjectComparator());
            for (Alias alias : list1){
                hashcode = 31*hashcode + UnambiguousAliasComparator.hashCode(alias);
            }
        }

        return hashcode;
    }
}
