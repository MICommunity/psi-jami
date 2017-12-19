package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.NucleicAcid;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

/**
 * Default implementation for NucleicAcid.
 * <p>
 * Notes: The equals and hashcode methods have NOT been overridden because the NucleicAcid object is a complex object.
 * To compare NucleicAcid objects, you can use some comparators provided by default:
 * - DefaultNucleicAcidComparator
 * - UnambiguousNucleicAcidComparator
 * - DefaultExactNucleicAcidComparator
 * - UnambiguousExactNucleicAcidComparator
 * - NucleicAcidComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/02/13</pre>
 */
public class DefaultNucleicAcid extends DefaultPolymer implements NucleicAcid {

    private Xref ddbjEmblGenbank;
    private Xref refseq;

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNucleicAcid(String name, CvTerm type) {
        super(name, type != null ? type : CvTermUtils.createNucleicAcidInteractorType());
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultNucleicAcid(String name, String fullName, CvTerm type) {
        super(name, fullName, type != null ? type : CvTermUtils.createNucleicAcidInteractorType());
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultNucleicAcid(String name, CvTerm type, Organism organism) {
        super(name, type != null ? type : CvTermUtils.createNucleicAcidInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultNucleicAcid(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type != null ? type : CvTermUtils.createNucleicAcidInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNucleicAcid(String name, CvTerm type, Xref uniqueId) {
        super(name, type != null ? type : CvTermUtils.createNucleicAcidInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNucleicAcid(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type != null ? type : CvTermUtils.createNucleicAcidInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNucleicAcid(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type != null ? type : CvTermUtils.createNucleicAcidInteractorType(), organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type     a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNucleicAcid(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type != null ? type : CvTermUtils.createNucleicAcidInteractorType(), organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public DefaultNucleicAcid(String name) {
        super(name, CvTermUtils.createNucleicAcidInteractorType());
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultNucleicAcid(String name, String fullName) {
        super(name, fullName, CvTermUtils.createNucleicAcidInteractorType());
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultNucleicAcid(String name, Organism organism) {
        super(name, CvTermUtils.createNucleicAcidInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public DefaultNucleicAcid(String name, String fullName, Organism organism) {
        super(name, fullName, CvTermUtils.createNucleicAcidInteractorType(), organism);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNucleicAcid(String name, Xref uniqueId) {
        super(name, CvTermUtils.createNucleicAcidInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNucleicAcid(String name, String fullName, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createNucleicAcidInteractorType(), uniqueId);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNucleicAcid(String name, Organism organism, Xref uniqueId) {
        super(name, CvTermUtils.createNucleicAcidInteractorType(), organism, uniqueId);
    }

    /**
     * <p>Constructor for DefaultNucleicAcid.</p>
     *
     * @param name     a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultNucleicAcid(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, CvTermUtils.createNucleicAcidInteractorType(), organism, uniqueId);
    }

    /**
     * {@inheritDoc}
     *
     * @return The first ddbjEmblGenbank if provided, then the first refseq identifier if provided, otherwise the first identifier in the list
     */
    @Override
    public Xref getPreferredIdentifier() {
        return ddbjEmblGenbank != null ? ddbjEmblGenbank : (refseq != null ? refseq : super.getPreferredIdentifier());
    }

    /**
     * <p>Getter for the field <code>ddbjEmblGenbank</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getDdbjEmblGenbank() {
        return this.ddbjEmblGenbank != null ? this.ddbjEmblGenbank.getId() : null;
    }

    /**
     * {@inheritDoc}
     */
    public void setDdbjEmblGenbank(String id) {
        NucleicAcidIdentifierList nucleicAcidIdentifiers = (NucleicAcidIdentifierList) getIdentifiers();

        // add new ddbj/embl/genbank if not null
        if (id != null) {
            CvTerm ddbjEmblGenbankDatabase = CvTermUtils.createDdbjEmblGenbankDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove old ddbj/embl/genbank if not null
            if (this.ddbjEmblGenbank != null) {
                nucleicAcidIdentifiers.removeOnly(this.ddbjEmblGenbank);
            }
            this.ddbjEmblGenbank = new DefaultXref(ddbjEmblGenbankDatabase, id, identityQualifier);
            nucleicAcidIdentifiers.addOnly(this.ddbjEmblGenbank);
        }
        // remove all ddbj/embl/genbank if the collection is not empty
        else if (!nucleicAcidIdentifiers.isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(nucleicAcidIdentifiers, Xref.DDBJ_EMBL_GENBANK_MI, Xref.DDBJ_EMBL_GENBANK);
            this.ddbjEmblGenbank = null;
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void initialiseIdentifiers() {
        initialiseIdentifiersWith(new NucleicAcidIdentifierList());
    }

    /**
     * <p>Getter for the field <code>refseq</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getRefseq() {
        return this.refseq != null ? this.refseq.getId() : null;
    }

    /**
     * {@inheritDoc}
     */
    public void setRefseq(String id) {
        NucleicAcidIdentifierList nucleicAcidIdentifiers = (NucleicAcidIdentifierList) getIdentifiers();

        // add new refseq if not null
        if (id != null) {
            CvTerm refseqDatabase = CvTermUtils.createRefseqDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove refseq if not null
            if (this.refseq != null) {
                nucleicAcidIdentifiers.removeOnly(this.refseq);
            }
            this.refseq = new DefaultXref(refseqDatabase, id, identityQualifier);
            nucleicAcidIdentifiers.addOnly(this.refseq);
        }
        // remove all ensembl genomes if the collection is not empty
        else if (!nucleicAcidIdentifiers.isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(nucleicAcidIdentifiers, Xref.REFSEQ_MI, Xref.REFSEQ);
            this.refseq = null;
        }
    }

    /**
     * <p>processAddedIdentifiersEvent</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processAddedIdentifiersEvent(Xref added) {
        // the added identifier is ddbj/embl/genbank and it is not the current ddbj/embl/genbank identifier
        if (ddbjEmblGenbank != added && XrefUtils.isXrefFromDatabase(added, Xref.DDBJ_EMBL_GENBANK_MI, Xref.DDBJ_EMBL_GENBANK)) {
            // the current ddbj/embl/genbank identifier is not identity, we may want to set ddbj/embl/genbank Identifier
            if (!XrefUtils.doesXrefHaveQualifier(ddbjEmblGenbank, Xref.IDENTITY_MI, Xref.IDENTITY)) {
                // the ddbj/embl/genbank identifier is not set, we can set the ddbj/embl/genbank identifier
                if (ddbjEmblGenbank == null) {
                    ddbjEmblGenbank = added;
                } else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)) {
                    ddbjEmblGenbank = added;
                }
                // the added xref is secondary object and the current ddbj/embl/genbank identifier is not a secondary object, we reset ddbj/embl/genbank identifier
                else if (!XrefUtils.doesXrefHaveQualifier(ddbjEmblGenbank, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)) {
                    ddbjEmblGenbank = added;
                }
            }
        }
        // the added identifier is refseq id and it is not the current refseq id
        else if (refseq != added && XrefUtils.isXrefFromDatabase(added, Xref.REFSEQ_MI, Xref.REFSEQ)) {
            // the current refseq id is not identity, we may want to set refseq id
            if (!XrefUtils.doesXrefHaveQualifier(refseq, Xref.IDENTITY_MI, Xref.IDENTITY)) {
                // the refseq id is not set, we can set the refseq id
                if (refseq == null) {
                    refseq = added;
                } else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)) {
                    refseq = added;
                }
                // the added xref is secondary object and the current refseq id is not a secondary object, we reset refseq id
                else if (!XrefUtils.doesXrefHaveQualifier(refseq, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)) {
                    refseq = added;
                }
            }
        }
    }

    /**
     * <p>processRemovedIdentifierEvent</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processRemovedIdentifierEvent(Xref removed) {
        if (ddbjEmblGenbank != null && ddbjEmblGenbank.equals(removed)) {
            ddbjEmblGenbank = XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), Xref.DDBJ_EMBL_GENBANK_MI, Xref.DDBJ_EMBL_GENBANK);
        } else if (refseq != null && refseq.equals(removed)) {
            refseq = XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), Xref.REFSEQ_MI, Xref.REFSEQ);
        }
    }


    protected void clearPropertiesLinkedToIdentifiers() {
        ddbjEmblGenbank = null;
        refseq = null;
    }

    @Override
    /**
     * Sets the interactor type of this NucleicAcid.
     * If the interactor type is null, it will set the interactor type to nucleic acid (MI:0318)
     */
    public void setInteractorType(CvTerm interactorType) {
        if (interactorType == null) {
            super.setInteractorType(CvTermUtils.createNucleicAcidInteractorType());
        } else {
            super.setInteractorType(interactorType);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return "Nucleic acid: "
                + (getDdbjEmblGenbank() != null ? getDdbjEmblGenbank()
                : (getRefseq() != null ? getRefseq() : super.toString()));
    }

    private class NucleicAcidIdentifierList extends AbstractListHavingProperties<Xref> {
        public NucleicAcidIdentifierList() {
            super();
        }

        @Override
        protected void processAddedObjectEvent(Xref added) {
            processAddedIdentifiersEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Xref removed) {
            processRemovedIdentifierEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToIdentifiers();
        }
    }
}
