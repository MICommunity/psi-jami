package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

import java.util.Collection;
import java.util.List;

/**
 * Container for both xrefs and identifiers  in a CvTerm
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>19/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif300", name = "cvTermXrefContainer")
public class CvTermXrefContainer extends XrefContainer {

    private Xref miIdentifier;
    private Xref modIdentifier;
    private Xref parIdentifier;
    private List<Xref> identifiers;

    /**
     * <p>isAnIdentifier.</p>
     *
     * @param ref a {@link Xref} object.
     * @return a boolean.
     */
    protected boolean isAnIdentifier(Xref ref){
        if (XrefUtils.isXrefAnIdentifier(ref)){
           return true;
        }
        else if (ref.getQualifier() == null && (CvTermUtils.isCvTerm(ref.getDatabase(), CvTerm.PSI_MI_MI, CvTerm.PSI_MI)
        || CvTermUtils.isCvTerm(ref.getDatabase(), CvTerm.PSI_MOD_MI, CvTerm.PSI_MOD)
        || CvTermUtils.isCvTerm(ref.getDatabase(), null, CvTerm.PSI_PAR))){
            return true;
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    protected void processAddedPrimaryRef(Xref added) {
        if (isAnIdentifier(added)){
            getIdentifiers().add(added);
        }
        else {
            getXrefs().add(added);
        }
    }

    /**
     * <p>Getter for the field <code>identifiers</code>.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        if (identifiers == null){
            initialiseIdentifiers();
        }
        return identifiers;
    }

    /**
     * <p>getMIIdentifier.</p>
     *
     * @return a {@link String} object.
     */
    public String getMIIdentifier() {
        return this.miIdentifier != null ? this.miIdentifier.getId() : null;
    }

    /**
     * <p>getMODIdentifier.</p>
     *
     * @return a {@link String} object.
     */
    public String getMODIdentifier() {
        return this.modIdentifier != null ? this.modIdentifier.getId() : null;
    }

    /**
     * <p>getPARIdentifier.</p>
     *
     * @return a {@link String} object.
     */
    public String getPARIdentifier() {
        return this.parIdentifier != null ? this.parIdentifier.getId() : null;
    }

    /**
     * <p>setMIIdentifier.</p>
     *
     * @param mi a {@link String} object.
     */
    public void setMIIdentifier(String mi) {
        FullIdentifierList cvTermIdentifiers = (FullIdentifierList) getIdentifiers();

        // add new mi if not null
        if (mi != null){
            CvTerm psiMiDatabase = CvTermUtils.createPsiMiDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove old psi mi if not null
            if (this.miIdentifier != null){
                cvTermIdentifiers.removeOnly(this.miIdentifier);
            }
            this.miIdentifier = new XmlXref(psiMiDatabase, mi, identityQualifier);
            cvTermIdentifiers.addOnly(this.miIdentifier);
        }
        // remove all mi if the collection is not empty
        else if (!getIdentifiers().isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(getIdentifiers(), CvTerm.PSI_MI_MI, CvTerm.PSI_MI);
            this.miIdentifier = null;
        }
    }

    /**
     * <p>setMODIdentifier.</p>
     *
     * @param mod a {@link String} object.
     */
    public void setMODIdentifier(String mod) {
        FullIdentifierList cvTermIdentifiers = (FullIdentifierList) getIdentifiers();

        // add new mod if not null
        if (mod != null){

            CvTerm psiModDatabase = CvTermUtils.createPsiModDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove old psi mod if not null
            if (this.modIdentifier != null){
                cvTermIdentifiers.removeOnly(this.modIdentifier);
            }
            this.modIdentifier = new XmlXref(psiModDatabase, mod, identityQualifier);
            cvTermIdentifiers.addOnly(this.modIdentifier);
        }
        // remove all mod if the collection is not empty
        else if (!getIdentifiers().isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(getIdentifiers(), CvTerm.PSI_MOD_MI, CvTerm.PSI_MOD);
            this.modIdentifier = null;
        }
    }

    /**
     * <p>setPARIdentifier.</p>
     *
     * @param par a {@link String} object.
     */
    public void setPARIdentifier(String par) {
        FullIdentifierList cvTermIdentifiers = (FullIdentifierList) getIdentifiers();

        // add new mod if not null
        if (par != null){

            CvTerm psiModDatabase = CvTermUtils.createPsiParDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove old psi mod if not null
            if (this.parIdentifier != null){
                cvTermIdentifiers.removeOnly(this.parIdentifier);
            }
            this.parIdentifier = new XmlXref(psiModDatabase, par, identityQualifier);
            cvTermIdentifiers.addOnly(this.parIdentifier);
        }
        // remove all mod if the collection is not empty
        else if (!getIdentifiers().isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(getIdentifiers(), null, CvTerm.PSI_PAR);
            this.parIdentifier = null;
        }
    }

    /**
     * <p>processAddedIdentifierEvent.</p>
     *
     * @param added a {@link Xref} object.
     */
    protected void processAddedIdentifierEvent(Xref added) {

        // the added identifier is psi-mi and it is not the current mi identifier
        if (miIdentifier != added && XrefUtils.isXrefFromDatabase(added, CvTerm.PSI_MI_MI, CvTerm.PSI_MI)){
            // the current psi-mi identifier is not identity, we may want to set miIdentifier
            if (!XrefUtils.doesXrefHaveQualifier(miIdentifier, Xref.IDENTITY_MI, Xref.IDENTITY)){
                // the miidentifier is not set, we can set the miidentifier
                if (miIdentifier == null){
                    miIdentifier = added;
                }
                else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)){
                    miIdentifier = added;
                }
                // the added xrefContainer is secondary object and the current mi is not a secondary object, we reset miidentifier
                else if (!XrefUtils.doesXrefHaveQualifier(miIdentifier, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)){
                    miIdentifier = added;
                }
            }
            else if (added.getQualifier() == null){
                miIdentifier = added;
            }
        }
        // the added identifier is psi-mod and it is not the current mod identifier
        else if (modIdentifier != added && XrefUtils.isXrefFromDatabase(added, CvTerm.PSI_MOD_MI, CvTerm.PSI_MOD)){
            // the current psi-mod identifier is not identity, we may want to set modIdentifier
            if (!XrefUtils.doesXrefHaveQualifier(modIdentifier, Xref.IDENTITY_MI, Xref.IDENTITY)){
                // the modIdentifier is not set, we can set the modIdentifier
                if (modIdentifier == null){
                    modIdentifier = added;
                }
                else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)){
                    modIdentifier = added;
                }
                // the added xrefContainer is secondary object and the current mi is not a secondary object, we reset miidentifier
                else if (!XrefUtils.doesXrefHaveQualifier(modIdentifier, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)){
                    modIdentifier = added;
                }
            }
            else if (added.getQualifier() == null){
                modIdentifier = added;
            }
        }
        // the added identifier is psi-par and it is not the current par identifier
        else if (parIdentifier != added && XrefUtils.isXrefFromDatabase(added, null, CvTerm.PSI_PAR)){
            // the current psi-par identifier is not identity, we may want to set parIdentifier
            if (!XrefUtils.doesXrefHaveQualifier(parIdentifier, Xref.IDENTITY_MI, Xref.IDENTITY)){
                // the parIdentifier is not set, we can set the parIdentifier
                if (parIdentifier == null){
                    parIdentifier = added;
                }
                else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)){
                    parIdentifier = added;
                }
                // the added xrefContainer is secondary object and the current par is not a secondary object, we reset paridentifier
                else if (!XrefUtils.doesXrefHaveQualifier(parIdentifier, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)){
                    parIdentifier = added;
                }
            }
            else if (added.getQualifier() == null){
                parIdentifier = added;
            }
        }
    }

    /**
     * <p>processRemovedIdentifierEvent.</p>
     *
     * @param removed a {@link Xref} object.
     */
    protected void processRemovedIdentifierEvent(Xref removed) {
        // the removed identifier is psi-mi
        if (miIdentifier != null && miIdentifier.equals(removed)){
            miIdentifier = (XmlXref)XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), CvTerm.PSI_MI_MI, CvTerm.PSI_MI);
        }
        // the removed identifier is psi-mod
        else if (modIdentifier != null && modIdentifier.equals(removed)){
            modIdentifier = (XmlXref)XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), CvTerm.PSI_MOD_MI, CvTerm.PSI_MOD);
        }
        // the removed identifier is psi-par
        else if (parIdentifier != null && parIdentifier.equals(removed)){
            parIdentifier = (XmlXref)XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), null, CvTerm.PSI_PAR);
        }
    }

    /**
     * <p>clearPropertiesLinkedToIdentifiers.</p>
     */
    protected void clearPropertiesLinkedToIdentifiers() {
        miIdentifier = null;
        modIdentifier = null;
        parIdentifier = null;
    }

    /**
     * <p>initialiseIdentifiers.</p>
     */
    protected void initialiseIdentifiers(){
        this.identifiers = new FullIdentifierList();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseSecondaryRefs() {
        super.initialiseSecondaryResWith(new JAXBSecondaryXrefList());
    }

    ///////////////////////////// classes
    //////////////////////////////// private class
    private class JAXBSecondaryXrefList extends XrefContainer.JAXBSecondaryXrefList{

        protected JAXBSecondaryXrefList() {
            super();
            if (identifiers == null){
                initialiseIdentifiers();
            }
        }

        protected boolean addXref(Integer index, Xref xref) {
            if (isAnIdentifier(xref)){
                return addIdentifier(index, xref);
            }
            else{
                return processXref(index, xref);
            }
        }
        protected boolean addIdentifier(Integer index, Xref xref) {
            if (index == null){
                return identifiers.add(xref);
            }
            identifiers.add(index, xref);
            return true;
        }

        private boolean processXref(Integer index, Xref xref) {
            if (index == null){
                return getXrefs().add(xref);
            }
            getXrefs().add(index, xref);
            return true;
        }
    }
    private class FullIdentifierList extends AbstractListHavingProperties<Xref> {
        private FullIdentifierList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Xref added) {
            processAddedIdentifierEvent(added);
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
