package psidev.psi.mi.jami.bridges.uniprot.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psidev.psi.mi.jami.bridges.exception.BridgeFailedException;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.model.impl.DefaultOrganism;
import psidev.psi.mi.jami.model.impl.DefaultProtein;
import psidev.psi.mi.jami.model.impl.DefaultXref;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.ChecksumUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import uk.ac.ebi.intact.commons.util.Crc64;
import uk.ac.ebi.intact.irefindex.seguid.RogidGenerator;
import uk.ac.ebi.intact.irefindex.seguid.SeguidException;
import uk.ac.ebi.kraken.interfaces.uniprot.*;
import uk.ac.ebi.kraken.interfaces.uniprot.Gene;
import uk.ac.ebi.kraken.interfaces.uniprot.comments.*;
import uk.ac.ebi.kraken.interfaces.uniprot.dbx.ensembl.Ensembl;
import uk.ac.ebi.kraken.interfaces.uniprot.dbx.flybase.FlyBase;
import uk.ac.ebi.kraken.interfaces.uniprot.dbx.go.Go;
import uk.ac.ebi.kraken.interfaces.uniprot.dbx.interpro.InterPro;
import uk.ac.ebi.kraken.interfaces.uniprot.dbx.ipi.Ipi;
import uk.ac.ebi.kraken.interfaces.uniprot.dbx.pdb.Pdb;
import uk.ac.ebi.kraken.interfaces.uniprot.dbx.reactome.Reactome;
import uk.ac.ebi.kraken.interfaces.uniprot.dbx.refseq.RefSeq;
import uk.ac.ebi.kraken.interfaces.uniprot.dbx.wormbase.WormBase;
import uk.ac.ebi.kraken.interfaces.uniprot.description.Field;
import uk.ac.ebi.kraken.interfaces.uniprot.description.FieldType;
import uk.ac.ebi.kraken.interfaces.uniprot.features.*;
import uk.ac.ebi.kraken.interfaces.uniprot.features.Feature;
import uk.ac.ebi.kraken.interfaces.uniprot.genename.GeneNameSynonym;
import uk.ac.ebi.kraken.interfaces.uniprot.genename.ORFName;
import uk.ac.ebi.kraken.interfaces.uniprot.genename.OrderedLocusName;
import uk.ac.ebi.kraken.interfaces.uniprot.DatabaseCrossReference;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utilities for translating Uniprot objects to JAMI objects.
 *
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 14/05/13
 */
public class UniprotTranslationUtil {

    static final String CHAIN_PARENT_MI = "MI:0951";
    static final String CHAIN_PARENT = "chain-parent";

    static final String ISOFORM_PARENT_MI = "MI:0243";
    static final String ISOFORM_PARENT = "isoform-parent";

    private final static Logger log = LoggerFactory.getLogger(UniprotTranslationUtil.class.getName());

    public UniprotTranslationUtil(){

    }

    /**
     * SHORTNAME = recommended short name // full name // identifier
     * FULLNAME = recommended  full name
     * UNIPROTAC = primary UniprotAC
     * IDENTIFIERS + UniprotID
     * IDNETIFIERS + Secondary UniprotACs
     * ALIASES + genes, ORF, Locus
     * XREFS + database cross references
     * SEQUENCE = sequence
     * ORGANISM = organism
     * CHECKSUMS + generated ROGID, supplied CRC64
     * @param e
     * @return
     * @throws BridgeFailedException
     */                                     //TODO should be a static method
    public Protein getProteinFromEntry(UniProtEntry e) throws BridgeFailedException {

        if(e == null) throw new IllegalArgumentException("The Uniprot entry was null");

        Protein p;
        String shortName = null;
        String fullName = null;

        //THIS ID HAS BEEN TAKEN FROM THE 'ID' name
        List<Field> fields =  e.getProteinDescription().getRecommendedName().getFields();
        for(Field f: fields){
            if(f.getType() == FieldType.SHORT){
                if(shortName == null){
                    shortName = f.getValue();
                }
                else{log.warn("Uniprot entry has multiple recommended shortNames: "+shortName+", "+f.getValue());}
            }
            else if(f.getType() == FieldType.FULL){
                if(fullName == null){
                    fullName = f.getValue();
                }
                else{log.warn("Uniprot entry has multiple recommended fullNames: "+fullName+", "+f.getValue());}
            }
        }

        //SHORT NAME - ShortName/FullName/UniprotID/UniprotAC
        if(shortName != null){
            p = new DefaultProtein(shortName);
        }else if(fullName != null){
            p = new DefaultProtein(fullName);
        }else if(e.getUniProtId() != null){
            p = new DefaultProtein(e.getUniProtId().getValue());
        }else if(e.getPrimaryUniProtAccession() != null){
            p = new DefaultProtein(e.getPrimaryUniProtAccession().getValue());
        } else {
            throw new IllegalArgumentException(
                    "The Uniprot entry has no names, accessions, or identifiers.");
        }

        //FULLNAME
        p.setFullName(fullName);

        //PRIMARY ACCESSION
        if(e.getPrimaryUniProtAccession() != null){
            p.setUniprotkb(e.getPrimaryUniProtAccession().getValue());
        } else {
            throw new IllegalArgumentException(
                    "The Uniprot entry ["+p.getShortName()+"] has no primary Accession.");
        }

        //UNIPROT ID AS SECONDARY AC
        if(e.getUniProtId() != null){
            p.getIdentifiers().add(
                    XrefUtils.createUniprotSecondary(e.getUniProtId().getValue()));
        }
        //SECONDARY ACs
        if(e.getSecondaryUniProtAccessions() != null
                && ! e.getSecondaryUniProtAccessions().isEmpty()) {
            for(SecondaryUniProtAccession ac : e.getSecondaryUniProtAccessions()){
                if(ac.getValue() != null){
                    p.getIdentifiers().add(
                            XrefUtils.createUniprotSecondary(ac.getValue()));
                }
            }
        }


        //Aliases
        if(e.getGenes() != null && e.getGenes().size() > 0){
            for(Gene g : e.getGenes()){
                //Gene Name
                if(g.hasGeneName()){
                    p.getAliases().add(AliasUtils.createGeneName(g.getGeneName().getValue()));
                }
                //Gene Name Synonym
                if(g.getGeneNameSynonyms() != null
                        && g.getGeneNameSynonyms().size() > 0){
                    for(GeneNameSynonym gns : g.getGeneNameSynonyms()){
                        p.getAliases().add(AliasUtils.createGeneNameSynonym(gns.getValue()));
                    }
                }
                //ORF names
                if(g.getORFNames() != null
                        && g.getORFNames().size() > 0){
                    for(ORFName orf : g.getORFNames()){
                        p.getAliases().add(AliasUtils.createOrfName(orf.getValue()));
                    }
                }
                //Locus Names
                if(g.getOrderedLocusNames() != null
                        && g.getOrderedLocusNames().size() > 0){
                    for(OrderedLocusName oln : g.getOrderedLocusNames()){
                        p.getAliases().add(AliasUtils.createLocusName(oln.getValue()));
                    }
                }
            }
        }

        // Database Xrefs
        for(DatabaseCrossReference dbxref : e.getDatabaseCrossReferences()){
            Xref dbxrefStandardised = getDatabaseXref(dbxref);
            if(dbxrefStandardised != null){
                p.getXrefs().add(dbxrefStandardised);
            }
        }


        // SEQUENCE
        p.setSequence(e.getSequence().getValue());

        // ORGANISM
        p.setOrganism(getOrganismFromEntry(e));

        generateChecksums(p);

        return p;
    }



    private static void generateChecksums(Protein p) throws BridgeFailedException {
        // CHECKSUMS
        if(p.getSequence() != null){
           /* //TODO add an MI term if one is created
            Checksum crc64Checksum = ChecksumUtils.createChecksum("CRC64", Crc64.getCrc64(p.getSequence()));
            p.getChecksums().add(crc64Checksum);

            if(p.getOrganism() != null
                    && p.getOrganism().getTaxId() != -3){
                try {
                    RogidGenerator rogidGenerator = new RogidGenerator();
                    String rogidValue = null;
                    rogidValue = rogidGenerator.calculateRogid(
                            p.getSequence(),""+p.getOrganism().getTaxId());
                    Checksum rogidChecksum = ChecksumUtils.createRogid(rogidValue);
                    p.getChecksums().add(rogidChecksum);

                } catch (SeguidException exception) {
                    throw new BridgeFailedException(
                            "Error was encountered whilst generating RogID in protein fetcher. "+
                                    exception.toString());
                }
            } */
        }
    }



    /**
     *
     * The mapping of fields for isoforms is as follows:
     * SHORTNAME = isoform identifier
     * FULLNAME = protein description
     * UNIPROTAC = ?
     * IDENTIFIERS + ?
     * IDENTIFIERS + ?
     * ALIASES = genes, ORF, Locus
     * ALIASES = isoform synonyms
     * SEQUENCE = spliced sequence
     * ORGANISM = organism
     * CHECKSUMS + generated ROGID, supplied CRC64
     * XREFS - ONLY: MasterProtein (db = uniprotkb, id = uniprotMaster, qualifier = isoform-parent (MI:0243))
     *
     *primary id, secondary ids
     *
     * @param entry
     * @param isoform
     * @param identifier
     * @return
     */
    public static Protein getProteinIsoformFromEntry(
            UniProtEntry entry, AlternativeProductsIsoform isoform, String identifier) throws BridgeFailedException {

        if(entry == null) throw new IllegalArgumentException("Uniprot entry was null.");
        if(isoform == null) throw new IllegalArgumentException("Isoform entry was null.");

        // SHORT NAME - identifier
        Protein p = new DefaultProtein(identifier);

        // FULL NAME - protein description
        if(entry.getProteinDescription().hasRecommendedName()) {
            for(Field f: entry.getProteinDescription().getRecommendedName().getFields()){
                if(f.getType() == FieldType.FULL){
                    p.setFullName(f.getValue()); //Use the full name if there is one
                    break;
                }else if(f.getType() == FieldType.SHORT){
                    p.setFullName(f.getValue()); //Use the short name if nothing else
                }
            }
        }

        //PRIMARY ACCESSION
        if(entry.getPrimaryUniProtAccession() != null){
            p.setUniprotkb(entry.getPrimaryUniProtAccession().getValue());
        } else {
            throw new IllegalArgumentException(
                    "The Uniprot entry ["+p.getShortName()+"] has no primary Accession.");
        }


        // ORGANISM
        p.setOrganism(getOrganismFromEntry(entry));


        // SEQUENCE
        switch (isoform.getIsoformSequenceStatus()) {
            case NOT_DESCRIBED:
                log.error("Splice variant has no sequence (status = NOT_DESCRIBED)");
                break;
            case DESCRIBED:
                p.setSequence(entry.getSplicedSequence(isoform.getName().getValue())); break;
            case DISPLAYED:
                p.setSequence(entry.getSplicedSequence(isoform.getName().getValue())); break;
            case EXTERNAL:
                // When an isoform is retrieved, it will be retrieved with the master which matches its identifier.
                log.warn("Isoform ["+isoform.getName().getValue()+"] has an unexpected external sequence.");
                break;
        }

        //CHECKSUMS
        generateChecksums(p);

        // ALIASES - gene name, gene name synonyms, orf, locus
        if(entry.getGenes() != null && entry.getGenes().size() > 0){
            for(Gene g : entry.getGenes()){
                //Gene Name
                if(g.hasGeneName()) p.getAliases().add(AliasUtils.createGeneName(g.getGeneName().getValue()));
                //Gene Name Synonym
                if(g.getGeneNameSynonyms() != null && g.getGeneNameSynonyms().size() > 0){
                    for(GeneNameSynonym gns : g.getGeneNameSynonyms()){
                        p.getAliases().add( AliasUtils.createGeneNameSynonym(gns.getValue()));
                    }
                }
                //ORF names
                if(g.getORFNames() != null && g.getORFNames().size() > 0){
                    for(ORFName orf : g.getORFNames()){
                        p.getAliases().add( AliasUtils.createOrfName(orf.getValue()));
                    }
                }
                //Locus Names
                if(g.getOrderedLocusNames() != null && g.getOrderedLocusNames().size() > 0){
                    for(OrderedLocusName oln : g.getOrderedLocusNames()){
                        p.getAliases().add( AliasUtils.createLocusName(oln.getValue()));
                    }
                }
            }
        }

        // ALIASES - isoform synonyms
        for ( IsoformSynonym syn : isoform.getSynonyms() ) {
            p.getAliases().add( AliasUtils.createIsoformSynonym( syn.getValue() ));
        }

        //TODO set the alternative id as the secondary xrefs


        // XREF - uniprotMaster
        p.getXrefs().add(XrefUtils.createXrefWithQualifier(Xref.UNIPROTKB , Xref.UNIPROTKB_MI,
                entry.getPrimaryUniProtAccession().toString() , ISOFORM_PARENT, ISOFORM_PARENT_MI));

        return p;
    }

    /**
     *
     * Entry => comments => Isoforms => ids
     * There will be one ID matching the search identifier for each entry.
     *
     * @param entry
     * @param identifier
     */
    public static AlternativeProductsIsoform findIsoformInEntry(UniProtEntry entry, String identifier){

        List<AlternativeProductsComment> comments = entry.getComments(CommentType.ALTERNATIVE_PRODUCTS );

        for ( AlternativeProductsComment comment : comments ) {
            List<AlternativeProductsIsoform> isoforms = comment.getIsoforms();
            for ( AlternativeProductsIsoform isoform : isoforms ){
                for( IsoformId id :  isoform.getIds()){
                    if(identifier.equals(id.getValue())) return isoform;
                }
            }
        }
        return null;
    }

    /**
     *
     * The mapping of fields for features is as follows:
     * SHORTNAME = pro identifier number
     * FULLNAME = feature chain description.
     * UNIPROTAC = uniprotIdMaster-chainId
     * IDENTIFIERS + ?
     * IDENTIFIERS + ?
     * ALIASES = genes, ORF, Locus
     * ALIASES = isoform synonyms
     * SEQUENCE = feature sequence
     * ORGANISM = organism
     * CHECKSUMS = generated ROGID, supplied CRC64
     * XREF = ONLY: MasterProtein (db = uniprotkb, id = masterId and qualifier = chain-parent (MI:0951))
     *
     * @param entry
     * @param feature
     * @param identifier
     * @return
     */
    public static Protein getProteinFeatureFromEntry(
            UniProtEntry entry, Feature feature, String identifier) throws BridgeFailedException {


        if(entry == null) throw new IllegalArgumentException("Uniprot entry was null.");
        if(feature == null) throw new IllegalArgumentException("Feature entry was null.");

        // SHORT NAME - identifier
        Protein p = new DefaultProtein(identifier);


        // FULL NAME - feature description
        FeatureLocation location = feature.getFeatureLocation();
        if(feature.getType() == FeatureType.CHAIN) {
            ChainFeature chainFeature = (ChainFeature)feature;
            p.setFullName(chainFeature.getFeatureDescription().getValue());
        }else if(feature.getType() == FeatureType.PEPTIDE) {
            PeptideFeature peptideFeature = (PeptideFeature)feature;
            p.setFullName(peptideFeature.getFeatureDescription().getValue());
        }else if(feature.getType() == FeatureType.PROPEP)  {
            ProPepFeature proPepFeature = (ProPepFeature)feature;
            p.setFullName(proPepFeature.getFeatureDescription().getValue());
        }

        // PRIMARY AC - uniprotIdMaster-chainId
        if(entry.getPrimaryUniProtAccession() != null){
            String primaryAc = entry.getPrimaryUniProtAccession().getValue()+"-"+identifier;
            p.setUniprotkb(primaryAc);
        }

        // SEQUENCE
        if (location != null){
            int begin = location.getStart()-1;
            int end = location.getEnd();

            if(location.getStart() == -1) begin = 0;
            if(location.getEnd() == -1) end = entry.getSequence().getValue().length() -1;

            if(begin > end) throw new IllegalArgumentException(
                    "Sequence has a start ("+begin+") larger than end ("+end+").");

            if(end > entry.getSequence().getValue().length()) throw new IllegalArgumentException(
                    "Sequence has end ("+end+") larger than " +
                            "length ("+entry.getSequence().getValue().length()+").");

            if(begin < 0 || end < 0) throw new IllegalArgumentException(
                    "Sequence has a start ("+begin+") or end ("+end+") lower than 0.");

            p.setSequence(entry.getSequence().subSequence(begin,end).getValue());
        }

        // ORGANISM
        p.setOrganism(getOrganismFromEntry(entry));

        //CHECKSUMS
        generateChecksums(p);

        // ALIASES - gene name, gene name synonyms, orf, locus
        if(entry.getGenes() != null && entry.getGenes().size() > 0){
            for(Gene g : entry.getGenes()){
                //Gene Name
                if(g.hasGeneName()) p.getAliases().add(AliasUtils.createGeneName(g.getGeneName().getValue()));
                //Gene Name Synonym
                if(g.getGeneNameSynonyms() != null && g.getGeneNameSynonyms().size() > 0){
                    for(GeneNameSynonym gns : g.getGeneNameSynonyms()){
                        p.getAliases().add( AliasUtils.createGeneNameSynonym(gns.getValue()));
                    }
                }
                //ORF names
                if(g.getORFNames() != null && g.getORFNames().size() > 0){
                    for(ORFName orf : g.getORFNames()){
                        p.getAliases().add( AliasUtils.createOrfName(orf.getValue()));
                    }
                }
                //Locus Names
                if(g.getOrderedLocusNames() != null && g.getOrderedLocusNames().size() > 0){
                    for(OrderedLocusName oln : g.getOrderedLocusNames()){
                        p.getAliases().add( AliasUtils.createLocusName(oln.getValue()));
                    }
                }
            }
        }

        // XREF - uniprotMaster
        p.getXrefs().add(XrefUtils.createXrefWithQualifier(Xref.UNIPROTKB , Xref.UNIPROTKB_MI,
                entry.getPrimaryUniProtAccession().toString() , CHAIN_PARENT, CHAIN_PARENT_MI));

        return p;
    }

    /**
     * Searches a uniprot entry to find the feature with a matching identifier.
     * @param entry
     * @param identifier
     * @return
     */
    public static Feature findFeatureInEntry(UniProtEntry entry, String identifier){
        Collection<ChainFeature> chainFeatures = entry.getFeatures( FeatureType.CHAIN );
        for(ChainFeature f : chainFeatures){
            if(f.getFeatureId().getValue().contains(identifier)) return f;
        }

        Collection<PeptideFeature> peptideFeatures = entry.getFeatures( FeatureType.PEPTIDE );
        for(PeptideFeature f : peptideFeatures){
            if(f.getFeatureId().getValue().contains(identifier))return f;
        }

        Collection<ProPepFeature> proPepFeatures = entry.getFeatures( FeatureType.PROPEP );
        for(ProPepFeature f : proPepFeatures){
            if(f.getFeatureId().getValue().contains(identifier)) return f;
        }
        return null;
    }

    private Map<DatabaseType,CvTerm> uniprotDatabases = null;


    private void initiateDatabaseMap(){
        uniprotDatabases = new HashMap<DatabaseType, CvTerm>();
        uniprotDatabases.put( DatabaseType.GO,       new DefaultCvTerm("go" , "MI:0448"));
        uniprotDatabases.put( DatabaseType.INTERPRO, new DefaultCvTerm("interpro" , "MI:0449"));
        uniprotDatabases.put( DatabaseType.PDB,      new DefaultCvTerm("pdb" , "MI:0460"));
        uniprotDatabases.put( DatabaseType.REACTOME, new DefaultCvTerm("reactome" , "MI:0467"));
        uniprotDatabases.put( DatabaseType.ENSEMBL,  new DefaultCvTerm("ensembl" , "MI:0476"));
        uniprotDatabases.put( DatabaseType.WORMBASE, new DefaultCvTerm("wormbase" , "MI:0487" ));
        uniprotDatabases.put( DatabaseType.FLYBASE,  new DefaultCvTerm("flybase" , "MI:0478" ));
        uniprotDatabases.put( DatabaseType.REFSEQ,   new DefaultCvTerm("refseq" , "MI:0481" ));
        uniprotDatabases.put( DatabaseType.IPI,      new DefaultCvTerm("ipi" , "MI:0675" ));
    }

    public Map<DatabaseType,CvTerm> getUniprotDatabases(){
        if (uniprotDatabases == null) initiateDatabaseMap();
        return uniprotDatabases;
    }


    /**
     * For each UniprotEntry DatabaseCrossReference,
     * find the matching CvTerm and return it in an Xref with the identifier.
     *
     * For each type of DatabaseCrossReference in Uniprot there is a different method of access.
     * Each of these which are considered relevant have been implemented here.
     *
     * @param dbxref
     * @return
     */
    protected Xref getDatabaseXref(DatabaseCrossReference dbxref){
        if(uniprotDatabases == null) initiateDatabaseMap();

        if (uniprotDatabases.containsKey(dbxref.getDatabase())){
            CvTerm database = uniprotDatabases.get(dbxref.getDatabase());
            String id = null;

            switch(dbxref.getDatabase()){
                case GO :
                    Go goDB = (Go)dbxref;
                    if(goDB.hasGoId()) id = goDB.getGoId().getValue(); break;
                case INTERPRO :
                    InterPro interProDB = (InterPro)dbxref;
                    if(interProDB.hasInterProId()) id = interProDB.getInterProId().getValue();  break;
                case PDB :
                    Pdb pdbDB = (Pdb)dbxref;
                    if(pdbDB.hasPdbAccessionNumber()) id = pdbDB.getPdbAccessionNumber().getValue(); break;
                case REACTOME:
                    Reactome reactomeDB = (Reactome)dbxref;
                    if(reactomeDB.hasReactomeAccessionNumber()) id = reactomeDB.getReactomeAccessionNumber().getValue();
                    break;
                case ENSEMBL :
                    Ensembl ensemblDB = (Ensembl)dbxref;
                    if(ensemblDB.hasEnsemblProteinIdentifier()) id = ensemblDB.getEnsemblProteinIdentifier().getValue();
                    else if(ensemblDB.hasEnsemblTranscriptIdentifier()) id = ensemblDB.getEnsemblTranscriptIdentifier().getValue();
                    else if(ensemblDB.hasEnsemblGeneIdentifier()) id = ensemblDB.getEnsemblGeneIdentifier().getValue();
                    break;
                case WORMBASE :
                    WormBase wormBaseDB = (WormBase)dbxref;
                    if(wormBaseDB.hasWormBaseAccessionNumber()) id = wormBaseDB.getWormBaseAccessionNumber().getValue();
                    break;
                case FLYBASE :
                    FlyBase flyBaseDB = (FlyBase)dbxref;
                    if(flyBaseDB.hasFlyBaseAccessionNumber()) id = flyBaseDB.getFlyBaseAccessionNumber().getValue();
                    break;
                case REFSEQ :
                    RefSeq refSeqDB = (RefSeq)dbxref;
                    if(refSeqDB.hasRefSeqAccessionNumber()) id = refSeqDB.getRefSeqAccessionNumber().getValue();
                    break;
                case IPI :
                    Ipi ipiDB = (Ipi)dbxref;
                    if(ipiDB.hasIpiAcNumber()) id = ipiDB.getIpiAcNumber().getValue();
                    break;
            }

            if(id != null) return new DefaultXref(database, id);
        }
        return null;
    }


    public static Organism getOrganismFromEntry(UniProtEntry e){

        Organism o = null;

        if(e.getNcbiTaxonomyIds() == null
                || e.getNcbiTaxonomyIds().isEmpty()){
            o = new DefaultOrganism(-3); //Unknown
        } else if(e.getNcbiTaxonomyIds().size() > 1){
            throw new IllegalArgumentException(
                    "Uniprot entry ["+e.getPrimaryUniProtAccession().getValue()+"] "
                            +"has multiple organisms.");
        } else {
            String id = e.getNcbiTaxonomyIds().get(0).getValue();
            try{
                o = new DefaultOrganism( Integer.parseInt( id ) );
                if(e.getOrganism().hasCommonName())
                    o.setCommonName(e.getOrganism().getCommonName().getValue());
                if(e.getOrganism().getScientificName() != null)
                    o.setScientificName(e.getOrganism().getScientificName().getValue());
                if(e.getOrganism().hasSynonym())
                    o.getAliases().add(AliasUtils.createAlias(Alias.SYNONYM, Alias.SYNONYM_MI, e.getOrganism().getSynonym().getValue()));
            }catch(NumberFormatException n){
                throw new IllegalArgumentException("Uniprot entry ["+e.getPrimaryUniProtAccession().getValue()+"] " +
                        "has a TaxonomyID which could not be cast to an integer: ("+id+").",n);
            }
        }
        return o;
    }
}
