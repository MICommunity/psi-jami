package psidev.psi.mi.jami.bridges.fetcher;

import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.Gene;
import psidev.psi.mi.jami.model.NucleicAcid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * A fetcher for genes.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 08/08/13
 */
public interface NucleicAcidFetcher extends InteractorFetcher<NucleicAcid> {

    /**
     * <p>fetchByIdentifier.</p>
     *
     * @param identifier The identifier of the Nucleic Acid
     * @param taxID      The organism the gene is from.
     * @return The genes matching the search terms.
     * @throws BridgeFailedException if any.
     */
    Collection<NucleicAcid> fetchByIdentifier(String identifier, int taxID)
            throws BridgeFailedException;


    /**
     * <p>fetchByIdentifiers.</p>
     *
     * @param identifiers The identifiers of the Nucleic Acid
     * @param taxID       The organism the gene is from.
     * @return The genes matching the search terms.
     * @throws BridgeFailedException if any.
     */
    default Collection<NucleicAcid> fetchByIdentifiers(Collection<String> identifiers, int taxID)
            throws BridgeFailedException {
        List<NucleicAcid> list = new ArrayList<>();
        for (String identifier : identifiers) {
            list.addAll(fetchByIdentifier(identifier, taxID));
        }
        return list;

    }
}
