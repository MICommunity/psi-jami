package psidev.psi.mi.jami.bridges.uniprot;


import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.bridges.fetcher.GeneFetcher;
import psidev.psi.mi.jami.bridges.uniprot.util.UniprotUtils;
import psidev.psi.mi.jami.model.Gene;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultGene;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import uk.ac.ebi.kraken.interfaces.uniprot.DatabaseCrossReference;
import uk.ac.ebi.kraken.interfaces.uniprot.DatabaseType;
import uk.ac.ebi.kraken.interfaces.uniprot.UniProtEntry;
import uk.ac.ebi.kraken.interfaces.uniprot.description.Field;
import uk.ac.ebi.kraken.interfaces.uniprot.description.FieldType;
import uk.ac.ebi.kraken.interfaces.uniprot.genename.GeneNameSynonym;
import uk.ac.ebi.uniprot.dataservice.client.Client;
import uk.ac.ebi.uniprot.dataservice.client.QueryResult;
import uk.ac.ebi.uniprot.dataservice.client.exception.ServiceException;
import uk.ac.ebi.uniprot.dataservice.client.uniprot.UniProtService;
import uk.ac.ebi.uniprot.dataservice.query.Query;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import static uk.ac.ebi.uniprot.dataservice.client.uniprot.UniProtQueryBuilder.taxonID;
import static uk.ac.ebi.uniprot.dataservice.client.uniprot.UniProtQueryBuilder.xref;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 22/08/13

 */
public class UniprotGeneFetcher implements GeneFetcher {


    private UniProtService uniProtQueryService;

    /**
     * <p>Constructor for UniprotGeneFetcher.</p>
     */
    public UniprotGeneFetcher() {
        uniProtQueryService = Client.getServiceFactoryInstance().getUniProtQueryService();
    }

    /**
     * {@inheritDoc}
     *
     * Finds a gene in uniprot using with the ensembl ID, the refseq ID or the ensemblGenomes ID.
     * The organism is optional, a taxid of 0 or less
     */
    public Collection<Gene> fetchByIdentifier(String identifier, int taxID)
            throws BridgeFailedException {
        Collection<Gene> genes = new HashSet<Gene>();

        if(identifier == null)
            throw new IllegalArgumentException("Could not perform search on null identifier.");
        try {
            uniProtQueryService.start();
            Query query = xref(DatabaseType.ENSEMBL, identifier)
                    .or(xref(DatabaseType.REFSEQ, identifier)
                            .or(xref(DatabaseType.GENEID, identifier))
                            .or(xref(DatabaseType.ENSEMBLBACTERIA, identifier))
                            .or(xref(DatabaseType.ENSEMBLFUNGI, identifier))
                            .or(xref(DatabaseType.ENSEMBLMETAZOA, identifier))
                            .or(xref(DatabaseType.ENSEMBLPROTISTS, identifier))
                            .or(xref(DatabaseType.ENSEMBLPLANTS, identifier)));
            if (taxID != -3) {
                query.and(taxonID(taxID));
            }
            QueryResult<UniProtEntry> entries = uniProtQueryService.getEntries(query);

            // Because we extract only gene information, as soon as we have one result if
            // enough to retrieve the minimal information needed. This is a temporary solution.
            // This fetcher should be migrated to ensembl services in the future.
            if (entries.getNumberOfHits() >= 1) {
                Gene gene = createGenesFromEntry(entries.getFirstResult(), identifier);
                genes.add(gene);
            }
        } catch (ServiceException e){
            throw new BridgeFailedException("Problem with Uniprot Service.",e);
        }
        finally {
            uniProtQueryService.stop();
        }

        return genes;
    }

    /** {@inheritDoc} */
    public Collection<Gene> fetchByIdentifiers(Collection<String> identifiers) throws BridgeFailedException {
        return fetchByIdentifiers(identifiers, -3);
    }

    /** {@inheritDoc} */
    public Collection<Gene> fetchByIdentifiers(Collection<String> identifiers, int taxID) throws BridgeFailedException {
        if(identifiers == null)
            throw new IllegalArgumentException("Could not perform search on null collection of identifiers.");

        if (!identifiers.isEmpty()){
            Collection<Gene> genes = new ArrayList<Gene>(identifiers.size());
            for (String id : identifiers){
                genes.addAll(fetchByIdentifier(id, taxID));
            }

            return genes;
        }
        return Collections.EMPTY_LIST;
    }

    /** {@inheritDoc} */
    public Collection<Gene> fetchByIdentifier(String identifier)
            throws BridgeFailedException {
        if(identifier == null)
            throw new IllegalArgumentException("Could not perform search on null identifier.");
        return fetchByIdentifier(identifier, -3);
    }

    private psidev.psi.mi.jami.model.Gene createGenesFromEntry(UniProtEntry entity , String identifier){
        // Using protein id as gene short name ending with _gene:
        psidev.psi.mi.jami.model.Gene jamiGene = new DefaultGene(entity.getUniProtId().getValue()+"_gene");

        String fullName = null;
        for(Field f: entity.getProteinDescription().getRecommendedName().getFields()){
            if(f.getType() == FieldType.FULL){
                fullName = f.getValue();
                break;
            }
        }
        jamiGene.setFullName(fullName);
        jamiGene.setOrganism(UniprotUtils.createOrganismFromEntry(entity));

        for(DatabaseCrossReference crossRef : entity.getDatabaseCrossReferences()){
            switch (crossRef.getDatabase()){
                case REFSEQ:
                    if(crossRef.getPrimaryId() != null) {
                        if(crossRef.getPrimaryId().getValue().equals(identifier))
                            jamiGene.setRefseq(identifier);
                        else
                            jamiGene.getXrefs().add(XrefUtils.createXref(Xref.REFSEQ, Xref.REFSEQ_MI, crossRef.getPrimaryId().getValue()));
                    }
                    break;
                    //TODO tests this
                case GENEID:
                    if(crossRef.getPrimaryId() != null) {
                        if(crossRef.getPrimaryId().getValue().equals(identifier))
                            jamiGene.setEntrezGeneId(identifier);
                        else
                            jamiGene.getXrefs().add(XrefUtils.createXref(Xref.ENTREZ_GENE, Xref.ENTREZ_GENE_MI, crossRef.getPrimaryId().getValue()));
                    }
                    break;
                case ENSEMBL:
                    if (crossRef.hasThird()){
                        if(crossRef.getThird().getValue().equals(identifier))
                            jamiGene.setEnsembl(identifier);
                        else
                            jamiGene.getXrefs().add(XrefUtils.createXref(Xref.ENSEMBL, Xref.ENSEMBL_MI, crossRef.getThird().getValue()));
                    }
                    else if(crossRef.getPrimaryId() != null) {
                        if(crossRef.getPrimaryId().getValue().equals(identifier))
                            jamiGene.setEnsembl(identifier);
                        else
                            jamiGene.getXrefs().add(XrefUtils.createXref(Xref.ENSEMBL, Xref.ENSEMBL_MI, crossRef.getPrimaryId().getValue()));
                    }

                    break;
                case ENSEMBLBACTERIA:
                case ENSEMBLFUNGI:
                case ENSEMBLMETAZOA:
                case ENSEMBLPLANTS:
                case ENSEMBLPROTISTS:
                    if(crossRef.getPrimaryId() != null) {
                        if(crossRef.getPrimaryId().getValue().equals(identifier))
                            jamiGene.setEnsemblGenome(identifier);
                        else
                            jamiGene.getXrefs().add(XrefUtils.createXref(Xref.ENSEMBL_GENOMES, Xref.ENSEMBL_GENOMES_MI, crossRef.getPrimaryId().getValue()));
                    }
                    break;
            }
        }

        for(uk.ac.ebi.kraken.interfaces.uniprot.Gene entityGene : entity.getGenes()){
            if(entityGene.hasGeneName())
                jamiGene.getAliases().add(AliasUtils.createGeneName(entityGene.getGeneName().getValue()));
            for(GeneNameSynonym synonym : entityGene.getGeneNameSynonyms()){
                jamiGene.getAliases().add(AliasUtils.createGeneNameSynonym(synonym.getValue()));
            }
        }
        return jamiGene;
    }
}
