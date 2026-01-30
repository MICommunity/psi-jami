package psidev.psi.mi.jami.bridges.chebi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.BioactiveEntityFetcher;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.BioactiveEntity;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultBioactiveEntity;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.collection.ListUtils;

import java.util.*;

/**
 * Accesses Chebi entries using the WSDL SOAP service.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 07/08/13
 *
 */
public class ChebiFetcher implements BioactiveEntityFetcher {

    private static final int MAX_SIZE_CHEBI_IDS = 50;
    /** Constant <code>CHEBI_POLYSACCHARYDE_PARENT_ID="18154"</code> */
    public final static String CHEBI_POLYSACCHARYDE_PARENT_ID = "18154";
    private static final String GET_SINGLE_COMPOUND_URL = "https://www.ebi.ac.uk/chebi/backend/api/public/compound/";
    private static final String GET_COMPOUNDS_URL = "https://www.ebi.ac.uk/chebi/backend/api/public/compounds?chebi_ids=";

    private final RestTemplate restTemplate;

    /**
     * <p>Constructor for ChebiFetcher.</p>
     */
    public ChebiFetcher() {
        this.restTemplate = new RestTemplate();
    }

    /**
     *
     * Searches Chebi for an entry matching the identifier.
     * If it's found, the record is used to create a bioactiveEntity with:
     * ChebiAsciiName = Full name, Short name
     * with Inchi, InchiKey, Smile, ChebiId matched to the corresponding fields.
     *
     * @param identifier    The identifier of the CHEBI entry to find.
     * @return              A completed bioactiveEntity for the given identifier. May be null.
     * @throws BridgeFailedException    Thrown if the fetcher encounters a problem.
     */
    public Collection<BioactiveEntity> fetchByIdentifier (String identifier) throws BridgeFailedException {
        if (identifier == null) {
            throw new IllegalArgumentException("Can not fetch on null identifier");
        }

        try {
            String query = GET_SINGLE_COMPOUND_URL + identifier;
            JsonNode response = restTemplate.getForObject(query, JsonNode.class);

            if (response == null) {
                return null;
            }
            return Collections.singleton(createNewBioactiveEntity(response));

        } catch (RestClientException e) {
            throw new BridgeFailedException("Cannot fetch the bioactive entity from CHEBI",e);
        }
    }

    private BioactiveEntity createNewBioactiveEntity(JsonNode jsonNode) {
        CvTerm entityType = retrieveMoleculeTypeFrom(jsonNode);

        String chebiAsciiName = jsonNode.get("ascii_name").asText();
        BioactiveEntity bioactiveEntity = new DefaultBioactiveEntity(chebiAsciiName, chebiAsciiName, entityType);

        // == Chebi ID
        bioactiveEntity.setChebi(jsonNode.get("chebi_accession").asText());

        // == Secondary CHEBI IDs
        if (jsonNode.has("secondary_ids") && !jsonNode.get("secondary_ids").isNull() && jsonNode.get("secondary_ids").isArray()) {
            ArrayNode secondaryIds = (ArrayNode) jsonNode.get("secondary_ids");
            for (JsonNode secondaryId : secondaryIds) {
                bioactiveEntity.getIdentifiers().add(XrefUtils.createChebiSecondary(secondaryId.asText()));
            }
        }

        if (jsonNode.has("default_structure") && !jsonNode.get("default_structure").isNull()) {
            JsonNode defaultStructure = jsonNode.get("default_structure");

            // == Smile
            if (defaultStructure.has("smiles") && !defaultStructure.get("smiles").isNull()) {
                bioactiveEntity.setSmile(defaultStructure.get("smiles").asText());
            }

            // == Inchi / Inchi Key
            if (defaultStructure.has("standard_inchi") && !defaultStructure.get("standard_inchi").isNull()) {
                bioactiveEntity.setStandardInchi(defaultStructure.get("standard_inchi").asText());
            }
            if (defaultStructure.has("standard_inchi_key") && !defaultStructure.get("standard_inchi_key").isNull()) {
                bioactiveEntity.setStandardInchiKey(defaultStructure.get("standard_inchi_key").asText());
            }
        }

        if (jsonNode.has("names") && !jsonNode.get("names").isNull()) {
            JsonNode names = jsonNode.get("names");

            names.fields().forEachRemaining(entry -> {
                String key = entry.getKey();
                JsonNode value = entry.getValue();
                if (key.equals("IUPAC NAME")) {
                    // == IUPAC names
                    for (JsonNode name : value) {
                        bioactiveEntity.getAliases().add(AliasUtils.createAlias(
                                Alias.IUPAC, Alias.IUPAC_MI, name.get("ascii_name").asText()));
                    }
                } else {
                    // == SYNONYMS
                    for (JsonNode name : value) {
                        bioactiveEntity.getAliases().add(AliasUtils.createAlias(
                                Alias.SYNONYM, Alias.SYNONYM_MI, name.get("ascii_name").asText()));
                    }
                }
            });
        }

        return bioactiveEntity;
    }

    private CvTerm retrieveMoleculeTypeFrom(JsonNode jsonNode) {
        CvTerm entityType = null;
        if (jsonNode.has("id") && !jsonNode.get("id").isNull() && jsonNode.get("id").asText().equals(CHEBI_POLYSACCHARYDE_PARENT_ID)) {
            entityType = CvTermUtils.createMICvTerm(BioactiveEntity.POLYSACCHARIDE, BioactiveEntity.POLYSACCHARIDE_MI);
        } else {
            if (jsonNode.has("outgoing_relations") && !jsonNode.get("outgoing_relations").isNull() && jsonNode.get("outgoing_relations").isArray()) {
                ArrayNode outgoingRelations = (ArrayNode) jsonNode.get("outgoing_relations");
                for (JsonNode outgoingRelation : outgoingRelations) {
                    if (outgoingRelation.has("init_id") && !outgoingRelation.get("init_id").isNull() &&
                            outgoingRelation.get("init_id").asText().equals(CHEBI_POLYSACCHARYDE_PARENT_ID)) {
                        entityType = CvTermUtils.createMICvTerm(BioactiveEntity.POLYSACCHARIDE, BioactiveEntity.POLYSACCHARIDE_MI);
                        break;
                    }
                }
            }
        }

        if (entityType == null) {
            entityType = CvTermUtils.createMICvTerm(BioactiveEntity.SMALL_MOLECULE, BioactiveEntity.SMALL_MOLECULE_MI);
        }

        return entityType;
    }

    /** {@inheritDoc} */
    public Collection<BioactiveEntity> fetchByIdentifiers(Collection<String> identifiers) throws BridgeFailedException {
        if (identifiers == null) {
            throw new IllegalArgumentException("The collection of identifiers cannot be null");
        }
        Collection<BioactiveEntity> results = new ArrayList<>();

        List<List<String>> parts = ListUtils.splitter(new ArrayList<>(identifiers), MAX_SIZE_CHEBI_IDS);

        for (List<String> part : parts) {
            try {
                // If we have the same Id in the list, we will have only one Entity
                String query = GET_COMPOUNDS_URL + String.join(",", part);
                JsonNode response = restTemplate.getForObject(query, JsonNode.class);

                if (response != null) {
                    response.fields().forEachRemaining(entry -> {
                        JsonNode value = entry.getValue();
                        if (value != null && value.has("data") && !value.get("data").isNull()) {
                            JsonNode data = value.get("data");
                            results.add(createNewBioactiveEntity(data));
                        }
                    });
                }

            } catch (RestClientException e) {
                throw new BridgeFailedException("Cannot fetch the bioactive entity from CHEBI",e);
            }
        }

        return results;
    }
}
