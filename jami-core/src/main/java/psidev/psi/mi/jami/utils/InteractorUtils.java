package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.*;

/**
 * Factory for interactors
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/13</pre>
 */
public class InteractorUtils {

    /**
     * <p>createUnknownBasicInteractor</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public static Interactor createUnknownBasicInteractor(){
        return new DefaultInteractor("unknown", CvTermUtils.createMICvTerm(Interactor.UNKNOWN_INTERACTOR, Interactor.UNKNOWN_INTERACTOR_MI));
    }

    /**
     * To know if an interactor have a specific interactor type.
     *
     * @param interactor a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param typeId a {@link java.lang.String} object.
     * @param typeName a {@link java.lang.String} object.
     * @return true if the interactor has the type with given name/identifier
     */
    public static boolean doesInteractorHaveType(Interactor interactor, String typeId, String typeName){

        if (interactor == null){
            return false;
        }
        return CvTermUtils.isCvTerm(interactor.getInteractorType(), typeId, typeName);
    }

    /**
     * <p>createProteinUniprot</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniprot a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Protein} object.
     */
    public static Protein createProteinUniprot(String name, String uniprot){
        return new DefaultProtein(name, XrefUtils.createUniprotIdentity(uniprot));
    }

    /**
     * <p>createProteinRefseq</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param refseq a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Protein} object.
     */
    public static Protein createProteinRefseq(String name, String refseq){
        return new DefaultProtein(name, XrefUtils.createRefseqIdentity(refseq));
    }

    /**
     * <p>createProteinGeneName</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param geneName a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Protein} object.
     */
    public static Protein createProteinGeneName(String name, String geneName){
        Protein protein = new DefaultProtein(name);
        protein.setGeneName(geneName);
        return protein;
    }

    /**
     * <p>createProteinRogid</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param rogid a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Protein} object.
     */
    public static Protein createProteinRogid(String name, String rogid){
        Protein protein = new DefaultProtein(name);
        protein.setRogid(rogid);
        return protein;
    }

    /**
     * <p>createBioactiveEntityChebi</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param chebi a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.BioactiveEntity} object.
     */
    public static BioactiveEntity createBioactiveEntityChebi(String name, String chebi){
        return new DefaultBioactiveEntity(name, XrefUtils.createChebiIdentity(chebi));
    }

    /**
     * <p>createBioactiveEntitySmile</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param smile a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.BioactiveEntity} object.
     */
    public static BioactiveEntity createBioactiveEntitySmile(String name, String smile){
        BioactiveEntity entity = new DefaultBioactiveEntity(name);
        entity.setSmile(smile);
        return entity;
    }

    /**
     * <p>createBioactiveEntityStandardInchi</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param standard a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.BioactiveEntity} object.
     */
    public static BioactiveEntity createBioactiveEntityStandardInchi(String name, String standard){
        BioactiveEntity entity = new DefaultBioactiveEntity(name);
        entity.setStandardInchi(standard);
        return entity;
    }

    /**
     * <p>createBioactiveEntityStandardInchiKey</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param key a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.BioactiveEntity} object.
     */
    public static BioactiveEntity createBioactiveEntityStandardInchiKey(String name, String key){
        BioactiveEntity entity = new DefaultBioactiveEntity(name);
        entity.setStandardInchiKey(key);
        return entity;
    }

    /**
     * <p>createNucleicAcidDdbjEmblGenbank</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param identity a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.NucleicAcid} object.
     */
    public static NucleicAcid createNucleicAcidDdbjEmblGenbank(String name, String identity){
        return new DefaultNucleicAcid(name, XrefUtils.createDdbjEmblGenbankIdentity(identity));
    }

    /**
     * <p>createNucleicAcidRefseq</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param identity a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.NucleicAcid} object.
     */
    public static NucleicAcid createNucleicAcidRefseq(String name, String identity){
        return new DefaultNucleicAcid(name, XrefUtils.createRefseqIdentity(identity));
    }

    /**
     * <p>createGeneRefseq</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param identity a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Gene} object.
     */
    public static Gene createGeneRefseq(String name, String identity){
        return new DefaultGene(name, XrefUtils.createRefseqIdentity(identity));
    }

    /**
     * <p>createGeneEnsembl</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param identity a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Gene} object.
     */
    public static Gene createGeneEnsembl(String name, String identity){
        return new DefaultGene(name, XrefUtils.createEnsemblIdentity(identity));
    }
    /**
     * <p>createGeneEnsemblGenomes</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param identity a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Gene} object.
     */
    public static Gene createGeneEnsemblGenomes(String name, String identity){
        return new DefaultGene(name, XrefUtils.createEnsemblGenomesIdentity(identity));
    }
    /**
     * <p>createGeneEntrezGene</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param identity a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Gene} object.
     */
    public static Gene createGeneEntrezGene(String name, String identity){
        return new DefaultGene(name, XrefUtils.createEntrezGeneIdIdentity(identity));
    }

    /**
     * <p>createPolymer</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param sequence a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Polymer} object.
     */
    public static Polymer createPolymer(String name, String sequence){
        Polymer polymer = new DefaultPolymer(name);
        polymer.setSequence(sequence);
        return polymer;
    }
}
