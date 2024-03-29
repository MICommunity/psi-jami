package psidev.psi.mi.jami.utils.comparator.interactor;

import psidev.psi.mi.jami.model.NucleicAcid;

/**
 * Default nucleic acids comparator.
 * It will first use DefaultInteractorBaseComparator to compare the basic interactor properties.
 * If the basic polymer properties are the same, It will look for DDBJ/EMBL/Genbank identifier if both are set. If the DDBJ/EMBL/Genbank identifiers are not both set or are identical, it will look at the
 * Refseq identifiers.
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>15/01/13</pre>
 */
public class DefaultNucleicAcidComparator {

    /**
     * Use DefaultNucleicAcidComparator to know if two nucleic acids are equals.
     *
     * @param nucleicAcid1 a {@link psidev.psi.mi.jami.model.NucleicAcid} object.
     * @param nucleicAcid2 a {@link psidev.psi.mi.jami.model.NucleicAcid} object.
     * @return true if the two nucleic acids are equal
     */
    public static boolean areEquals(NucleicAcid nucleicAcid1, NucleicAcid nucleicAcid2){
        if (nucleicAcid1 == nucleicAcid2){
            return true;
        }
        else if (nucleicAcid1 == null || nucleicAcid2 == null){
            return false;
        }
        else {

            // compares first the basic interactor properties
            if (!DefaultPolymerComparator.areEquals(nucleicAcid1, nucleicAcid2)){
                return false;
            }

            // then compares DDBJ/EMBL/Genbank identifiers
            String ddbjEmblGenbank1 = nucleicAcid1.getDdbjEmblGenbank();
            String ddbjEmblGenbank2 = nucleicAcid2.getDdbjEmblGenbank();

            if (ddbjEmblGenbank1 != null && ddbjEmblGenbank2 != null){
                if (!ddbjEmblGenbank1.equals(ddbjEmblGenbank2)){
                    return false;
                }
            }

            // compares Refseq
            String refseq1 = nucleicAcid1.getRefseq();
            String refseq2 = nucleicAcid2.getRefseq();

            if (refseq1 != null && refseq2 != null){
                return refseq1.equals(refseq2);
            }

            return true;
        }
    }
}
