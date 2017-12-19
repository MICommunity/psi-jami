package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.model.impl.DefaultXref;

/**
 * Utility class for CvTerms
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>05/02/13</pre>
 */
public class CvTermUtils {

    private static CvTerm gene;
    private static CvTerm allosteryMechanism;
    private static CvTerm psimi;
    private static CvTerm psimod;
    private static CvTerm psipar;
    private static CvTerm identity;
    private static CvTerm secondary;
    private static CvTerm imexPrimary;
    private static CvTerm undetermined;
    private static CvTerm nTerminalRange;
    private static CvTerm cTerminalRange;
    private static CvTerm nTerminal;
    private static CvTerm cTerminal;
    private static CvTerm nTerminalRagged;
    private static CvTerm greaterThan;
    private static CvTerm lessThan;
    private static CvTerm certain;
    private static CvTerm range;
    private static CvTerm putative_self;
    private static CvTerm self;
    private static CvTerm unknownInteractorType;

    /**
     * Gets the unique identifier of a CvObject. If it has PSI MI Identifier (miIdentifier) return it, otherwise returns the MOD identifier,
     * If no MOD identifier, returns the PAR identifier.
     * if no PAR identifier, returns the first identifier in the list of identifiers.
     *
     * @param cvObject The object to get the identifier from.
     * @return The identifier. Will be null if no miIdentifier or identity xref is found.
     * @since 1.8.0
     */
    public static String getBestIdentifier(CvTerm cvObject) {
        if (cvObject == null) return null;

        // try the PSI MI first
        if (cvObject.getMIIdentifier() != null) {
            return cvObject.getMIIdentifier();
        }
        // try the PSI MOD second
        else if (cvObject.getMODIdentifier() != null) {
            return cvObject.getMODIdentifier();
        }
        // try the PSI PAR third
        else if (cvObject.getPARIdentifier() != null) {
            return cvObject.getPARIdentifier();
        }
        else if (!cvObject.getIdentifiers().isEmpty()) {
           return cvObject.getIdentifiers().iterator().next().getId();
        }
        else{
            return null;
        }
    }

    /**
     * <p>Getter for the field <code>gene</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getGene() {
        if (gene == null){
            gene = createGeneNameAliasType();
        }
        return gene;
    }

    /**
     * <p>Getter for the field <code>allosteryMechanism</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getAllosteryMechanism() {
        if (allosteryMechanism == null){
            allosteryMechanism = createAllosteryCooperativeMechanism();
        }
        return allosteryMechanism;
    }

    /**
     * <p>Getter for the field <code>psimi</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getPsimi() {
        if (psimi == null){
            psimi = createPsiMiDatabase();
        }
        return psimi;
    }

    /**
     * <p>Getter for the field <code>psimod</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getPsimod() {
        if (psimod == null){
            psimod = createPsiModDatabase();
        }
        return psimod;
    }

    /**
     * <p>Getter for the field <code>psipar</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getPsipar() {
        if (psipar == null){
            psipar = createPsiParDatabase();
        }
        return psipar;
    }

    /**
     * <p>Getter for the field <code>identity</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getIdentity() {
        if (identity == null){
            identity = createIdentityQualifier();
        }
        return identity;
    }

    /**
     * <p>Getter for the field <code>secondary</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getSecondary() {
        if (secondary == null){
            secondary = createSecondaryXrefQualifier();
        }
        return secondary;
    }

    /**
     * <p>Getter for the field <code>imexPrimary</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getImexPrimary() {
        if (imexPrimary == null){
            imexPrimary = createImexPrimaryQualifier();
        }
        return imexPrimary;
    }

    /**
     * <p>Getter for the field <code>undetermined</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getUndetermined() {
        if (undetermined == null){
            undetermined = createUndeterminedStatus();
        }
        return undetermined;
    }

    /**
     * <p>Getter for the field <code>nTerminalRange</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getNTerminalRange() {
        if (nTerminalRange == null){
            nTerminalRange = createNTerminalRangeStatus();
        }
        return nTerminalRange;
    }

    /**
     * <p>Getter for the field <code>cTerminalRange</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getCTerminalRange() {
        if (cTerminalRange == null){
            cTerminalRange = createCTerminalRangeStatus();
        }
        return cTerminalRange;
    }

    /**
     * <p>getFuzzyRange</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getFuzzyRange() {
        if (range == null){
            range = createRangeStatus();
        }
        return range;
    }

    /**
     * <p>Getter for the field <code>nTerminal</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getNTerminal() {
        if (nTerminal == null){
            nTerminal = createNTerminalStatus();
        }
        return nTerminal;
    }

    /**
     * <p>Getter for the field <code>cTerminal</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getCTerminal() {
        if (cTerminal == null){
            cTerminal = createCTerminalStatus();
        }
        return cTerminal;
    }

    /**
     * <p>Getter for the field <code>nTerminalRagged</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getNTerminalRagged() {
        if (nTerminalRagged == null){
            nTerminalRagged = createRaggedNTerminalStatus();
        }
        return nTerminalRagged;
    }

    /**
     * <p>Getter for the field <code>greaterThan</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getGreaterThan() {
        if (greaterThan == null){
            greaterThan = createGreaterThanRangeStatus();
        }
        return greaterThan;
    }

    /**
     * <p>Getter for the field <code>lessThan</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getLessThan() {
        if (lessThan == null){
            lessThan = createLessThanRangeStatus();
        }
        return lessThan;
    }

    /**
     * <p>Getter for the field <code>certain</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getCertain() {
        if (certain == null){
            certain = createCertainStatus();
        }
        return certain;
    }

    /**
     * <p>getPutativeSelf</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getPutativeSelf() {
        if (putative_self == null){
            putative_self = createPutativeSelf();
        }
        return putative_self;
    }

    /**
     * <p>Getter for the field <code>self</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getSelf() {
        if (self == null){
            self = createSelf();
        }
        return self;
    }

    /**
     * <p>Getter for the field <code>unknownInteractorType</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm getUnknownInteractorType() {
        if (unknownInteractorType == null){
            unknownInteractorType = createUnknownInteractorType();
        }
        return unknownInteractorType;
    }

    /**
     * <p>createPsiMiDatabaseNameOnly</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createPsiMiDatabaseNameOnly(){
        return new DefaultCvTerm(CvTerm.PSI_MI);
    }

    /**
     * <p>createIdentityQualifierNameOnly</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createIdentityQualifierNameOnly(){
        return new DefaultCvTerm(Xref.IDENTITY);
    }

    /**
     * <p>createMICvTerm</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param MI a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createMICvTerm(String name, String MI){
        if (MI != null){
            CvTerm psimi = createPsiMiDatabase();
            return new DefaultCvTerm(name, new DefaultXref(psimi, MI, createIdentityQualifier(psimi)));
        }
        else {
            return new DefaultCvTerm(name);
        }
    }

    /**
     * <p>createECOCvTerm</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param ECO a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createECOCvTerm(String name, String ECO){
        if (ECO != null){
            return new DefaultCvTerm(name, new DefaultXref(createMICvTerm(Complex.ECO, Complex.ECO_MI), ECO, createIdentityQualifier()));
        }
        else {
            return new DefaultCvTerm(name);
        }
    }

    /**
     * <p>createMODCvTerm</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param MOD a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createMODCvTerm(String name, String MOD){
        if (MOD != null){
            return new DefaultCvTerm(name, new DefaultXref(createPsiModDatabase(), MOD, createIdentityQualifier()));
        }
        else {
            return new DefaultCvTerm(name);
        }
    }

    /**
     * <p>createPARCvTerm</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param PAR a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createPARCvTerm(String name, String PAR){
        if (PAR != null){
            return new DefaultCvTerm(name, new DefaultXref(createPsiParDatabase(), PAR, createIdentityQualifier()));
        }
        else {
            return new DefaultCvTerm(name);
        }
    }

    /**
     * <p>createPsiMiDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createPsiMiDatabase(){
        CvTerm psiMi = new DefaultCvTerm(CvTerm.PSI_MI);
        Xref psiMiXref = new DefaultXref(psiMi, CvTerm.PSI_MI_MI, createIdentityQualifier(psiMi));
        psiMi.getIdentifiers().add(psiMiXref);
        return psiMi;
    }

    /**
     * <p>createPsiModDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createPsiModDatabase(){
        return createMICvTerm(CvTerm.PSI_MOD, CvTerm.PSI_MOD_MI);
    }

    /**
     * <p>createPsiParDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createPsiParDatabase(){
        return createMICvTerm(CvTerm.PSI_PAR, null);
    }

    /**
     * <p>createIdentityQualifier</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createIdentityQualifier(){
        CvTerm identity = new DefaultCvTerm(Xref.IDENTITY);
        Xref psiMiXref = new DefaultXref(createPsiMiDatabase(identity), Xref.IDENTITY_MI, identity);
        identity.getIdentifiers().add(psiMiXref);
        return identity;
    }

    /**
     * <p>createChebiDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createChebiDatabase(){
        return createMICvTerm(Xref.CHEBI, Xref.CHEBI_MI);
    }

    /**
     * <p>createEnsemblDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createEnsemblDatabase(){
        return createMICvTerm(Xref.ENSEMBL, Xref.ENSEMBL_MI);
    }

    /**
     * <p>createEnsemblGenomesDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createEnsemblGenomesDatabase(){
        return createMICvTerm(Xref.ENSEMBL_GENOMES, Xref.ENSEMBL_GENOMES_MI);
    }

    /**
     * <p>createEntrezGeneIdDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createEntrezGeneIdDatabase(){
        return createMICvTerm(Xref.ENTREZ_GENE, Xref.ENTREZ_GENE_MI);
    }

    /**
     * <p>createRefseqDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createRefseqDatabase(){
        return createMICvTerm(Xref.REFSEQ, Xref.REFSEQ_MI);
    }

    /**
     * <p>createDdbjEmblGenbankDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createDdbjEmblGenbankDatabase(){
        return createMICvTerm(Xref.DDBJ_EMBL_GENBANK, Xref.DDBJ_EMBL_GENBANK_MI);
    }

    /**
     * <p>createUniprotkbDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createUniprotkbDatabase(){
        return createMICvTerm(Xref.UNIPROTKB, Xref.UNIPROTKB_MI);
    }

    /**
     * <p>createImexDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createImexDatabase(){
        return createMICvTerm(Xref.IMEX, Xref.IMEX_MI);
    }

    /**
     * <p>createPubmedDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createPubmedDatabase(){
        return createMICvTerm(Xref.PUBMED, Xref.PUBMED_MI);
    }

    /**
     * <p>createDoiDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createDoiDatabase(){
        return createMICvTerm(Xref.DOI, Xref.DOI_MI);
    }

    /**
     * <p>createInterproDatabase</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createInterproDatabase(){
        return createMICvTerm(Xref.INTERPRO, Xref.INTERPRO_MI);
    }

    /**
     * <p>createSmile</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createSmile(){
        return createMICvTerm(Checksum.SMILE, Checksum.SMILE_MI);
    }

    /**
     * <p>createStandardInchi</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createStandardInchi(){
        return createMICvTerm(Checksum.INCHI, Checksum.INCHI_MI);
    }

    /**
     * <p>createStandardInchiKey</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createStandardInchiKey(){
        return createMICvTerm(Checksum.STANDARD_INCHI_KEY, Checksum.STANDARD_INCHI_KEY_MI);
    }

    /**
     * <p>createRogid</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createRogid(){
        return createMICvTerm(Checksum.ROGID, Checksum.ROGID_MI);
    }

    /**
     * <p>createRigid</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createRigid(){
        return createMICvTerm(Checksum.RIGID, Checksum.RIGID_MI);
    }

    /**
     * <p>createCertainStatus</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createCertainStatus(){
        return createMICvTerm(Position.CERTAIN, Position.CERTAIN_MI);
    }

    /**
     * <p>createPutativeSelf</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createPutativeSelf(){
        return createMICvTerm(Participant.PUTATIVE_SELF_ROLE, Participant.PUTATIVE_SELF_ROLE_MI);
    }

    /**
     * <p>createSelf</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createSelf(){
        return createMICvTerm(Participant.SELF_ROLE, Participant.SELF_ROLE_MI);
    }

    /**
     * <p>createRangeStatus</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createRangeStatus(){
        return createMICvTerm(Position.RANGE, Position.RANGE_MI);
    }

    /**
     * <p>createUndeterminedStatus</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createUndeterminedStatus(){
        return createMICvTerm(Position.UNDETERMINED, Position.UNDETERMINED_MI);
    }

    /**
     * <p>createNTerminalRangeStatus</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createNTerminalRangeStatus(){
        return createMICvTerm(Position.N_TERMINAL_RANGE, Position.N_TERMINAL_RANGE_MI);
    }

    /**
     * <p>createCTerminalRangeStatus</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createCTerminalRangeStatus(){
        return createMICvTerm(Position.C_TERMINAL_RANGE, Position.C_TERMINAL_RANGE_MI);
    }

    /**
     * <p>createNTerminalStatus</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createNTerminalStatus(){
        return createMICvTerm(Position.N_TERMINAL, Position.N_TERMINAL_MI);
    }

    /**
     * <p>createCTerminalStatus</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createCTerminalStatus(){
        return createMICvTerm(Position.C_TERMINAL, Position.C_TERMINAL_MI);
    }

    /**
     * <p>createRaggedNTerminalStatus</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createRaggedNTerminalStatus(){
        return createMICvTerm(Position.RAGGED_N_TERMINAL, Position.RAGGED_N_TERMINAL_MI);
    }

    /**
     * <p>createGreaterThanRangeStatus</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createGreaterThanRangeStatus(){
        return createMICvTerm(Position.GREATER_THAN, Position.GREATER_THAN_MI);
    }

    /**
     * <p>createLessThanRangeStatus</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createLessThanRangeStatus(){
        return createMICvTerm(Position.LESS_THAN, Position.LESS_THAN_MI);
    }

    /**
     * <p>createGeneInteractorType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createGeneInteractorType(){
        return createMICvTerm(Gene.GENE, Gene.GENE_MI);
    }

    /**
     * <p>createPolymerInteractorType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createPolymerInteractorType(){
        return createMICvTerm(Polymer.POLYMER, Polymer.POLYMER_MI);
    }

    /**
     * <p>createProteinInteractorType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createProteinInteractorType(){
        return createMICvTerm(Protein.PROTEIN, Protein.PROTEIN_MI);
    }

    /**
     * <p>createNucleicAcidInteractorType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createNucleicAcidInteractorType(){
        return createMICvTerm(NucleicAcid.NULCEIC_ACID, NucleicAcid.NULCEIC_ACID_MI);
    }

    /**
     * <p>createBioactiveEntityType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createBioactiveEntityType(){
        return createMICvTerm(BioactiveEntity.BIOACTIVE_ENTITY, BioactiveEntity.BIOACTIVE_ENTITY_MI);
    }

    /**
     * <p>createMoleculeSetType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createMoleculeSetType(){
        return createMICvTerm(InteractorPool.MOLECULE_SET, InteractorPool.MOLECULE_SET_MI);
    }

    /**
     * <p>createGeneNameAliasType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createGeneNameAliasType(){
        return createMICvTerm(Alias.GENE_NAME, Alias.GENE_NAME_MI);
    }

    /**
     * <p>createComplexInteractorType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createComplexInteractorType(){
        return createMICvTerm(Complex.COMPLEX, Complex.COMPLEX_MI);
    }

    /**
     * <p>createComplexSynonym</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createComplexSynonym(){
        return createMICvTerm(Alias.COMPLEX_SYNONYM, Alias.COMPLEX_SYNONYM_MI);
    }

    /**
     * <p>createAuthorAssignedName</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createAuthorAssignedName(){
        return createMICvTerm(Alias.AUTHOR_ASSIGNED_NAME, Alias.AUTHOR_ASSIGNED_NAME_MI);
    }

    /**
     * <p>createGeneNameSynonym</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createGeneNameSynonym(){
        return createMICvTerm(Alias.GENE_NAME_SYNONYM, Alias.GENE_NAME_SYNONYM_MI);
    }

    /**
     * <p>createIsoformSynonym</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createIsoformSynonym(){
        return createMICvTerm(Alias.ISOFORM_SYNONYM, Alias.ISOFORM_SYNONYM_MI);
    }

    /**
     * <p>createOrfName</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createOrfName(){
        return createMICvTerm(Alias.ORF_NAME, Alias.ORF_NAME_MI);
    }

    /**
     * <p>createLocusName</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createLocusName(){
        return createMICvTerm(Alias.LOCUS_NAME, Alias.LOCUS_NAME_MI);
    }
    /**
     * <p>createComplexRecommendedName</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createComplexRecommendedName(){
        return createMICvTerm(Alias.COMPLEX_RECOMMENDED_NAME, Alias.COMPLEX_RECOMMENDED_NAME_MI);
    }

    /**
     * <p>createComplexSystematicName</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createComplexSystematicName(){
        return createMICvTerm(Alias.COMPLEX_SYSTEMATIC_NAME, Alias.COMPLEX_SYSTEMATIC_NAME_MI);
    }

    /**
     * <p>createUnspecifiedRole</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createUnspecifiedRole(){
        return createMICvTerm(Participant.UNSPECIFIED_ROLE, Participant.UNSPECIFIED_ROLE_MI);
    }

    /**
     * <p>createComplexPhysicalProperties</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createComplexPhysicalProperties(){
        return createMICvTerm(Annotation.COMPLEX_PROPERTIES, Annotation.COMPLEX_PROPERTIES_MI);
    }

    /**
     * <p>createImexPrimaryQualifier</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createImexPrimaryQualifier(){
        return createMICvTerm(Xref.IMEX_PRIMARY, Xref.IMEX_PRIMARY_MI);
    }

    /**
     * <p>createAllosteryCooperativeMechanism</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createAllosteryCooperativeMechanism(){
        return createMICvTerm(CooperativeEffect.ALLOSTERY, CooperativeEffect.ALLOSTERY_ID);
    }

    /**
     * <p>createIdentityXrefQualifier</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createIdentityXrefQualifier(){
        return createMICvTerm(Xref.IDENTITY, Xref.IDENTITY_MI);
    }

    /**
     * <p>createSecondaryXrefQualifier</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createSecondaryXrefQualifier(){
        return createMICvTerm(Xref.SECONDARY, Xref.SECONDARY_MI);
    }

    /**
     * <p>createBiologicalFeatureType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createBiologicalFeatureType(){
        return createMICvTerm(Feature.BIOLOGICAL_FEATURE, Feature.BIOLOGICAL_FEATURE_MI);
    }

    /**
     * <p>createUnspecifiedMethod</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createUnspecifiedMethod(){
        return createMICvTerm(Experiment.UNSPECIFIED_METHOD, Experiment.UNSPECIFIED_METHOD_MI);
    }

    /**
     * <p>createUnknownInteractorType</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createUnknownInteractorType(){
        return createMICvTerm(Interactor.UNKNOWN_INTERACTOR, Interactor.UNKNOWN_INTERACTOR_MI);
    }

    /**
     * Check if the CvTerm natches the MI term and/or the name if no MI term exist
     *
     * @param term : the term to check
     * @param mi : the mi id to compare
     * @param name : the name to compare
     * @return true if the CvTerm natches the MI term and/or the name
     */
    public static boolean isCvTerm(CvTerm term, String mi, String name){

        if (term.getMIIdentifier() != null && mi != null){
            return term.getMIIdentifier().equals(mi);
        }
        else{
            if (term.getShortName().equalsIgnoreCase(name) || (term.getFullName() != null && term.getFullName().equalsIgnoreCase(name))){
                return true;
            }
            else {
                return false;
            }
        }
    }

    /**
     * <p>getPsiMiReference</p>
     *
     * @param term a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public static Xref getPsiMiReference(CvTerm term){
        if (term == null){
            return null;
        }
        else if (term.getMIIdentifier() == null){
            return null;
        }
        else{
            return XrefUtils.collectFirstIdentifierWithDatabaseAndId(term.getIdentifiers(), CvTerm.PSI_MI_MI, CvTerm.PSI_MI, term.getMIIdentifier());
        }
    }

    /**
     * <p>getPsiModReference</p>
     *
     * @param term a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public static Xref getPsiModReference(CvTerm term){
        if (term == null){
            return null;
        }
        else if (term.getMODIdentifier() == null){
            return null;
        }
        else{
            return XrefUtils.collectFirstIdentifierWithDatabaseAndId(term.getIdentifiers(), CvTerm.PSI_MOD_MI, CvTerm.PSI_MOD, term.getMODIdentifier());
        }
    }

    /**
     * <p>getPsiParReference</p>
     *
     * @param term a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public static Xref getPsiParReference(CvTerm term){
        if (term == null){
            return null;
        }
        else if (term.getPARIdentifier() == null){
            return null;
        }
        else{
            return XrefUtils.collectFirstIdentifierWithDatabaseAndId(term.getIdentifiers(), null, CvTerm.PSI_PAR, term.getPARIdentifier());
        }
    }

    /**
     * <p>createPsiMiDatabase</p>
     *
     * @param identity a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createPsiMiDatabase(CvTerm identity){
        CvTerm psiMi = new DefaultCvTerm(CvTerm.PSI_MI);
        Xref psiMiXref = new DefaultXref(psiMi, CvTerm.PSI_MI_MI, identity);
        psiMi.getIdentifiers().add(psiMiXref);
        return psiMi;
    }

    /**
     * <p>createIdentityQualifier</p>
     *
     * @param psiMi a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public static CvTerm createIdentityQualifier(CvTerm psiMi){
        CvTerm identity = new DefaultCvTerm(Xref.IDENTITY);
        Xref psiMiXref = new DefaultXref(psiMi, Xref.IDENTITY_MI, identity);
        identity.getIdentifiers().add(psiMiXref);
        return identity;
    }
}
