package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

import java.util.Collection;
import java.util.List;

/**
 * Xref container for Feature
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif300", name = "featureXrefContainer")
public class FeatureXrefContainer extends XrefContainer {

    private Xref interpro;
    private List<Xref> identifiers;

    /**
     * <p>isAnIdentifier.</p>
     *
     * @param ref a {@link psidev.psi.mi.jami.model.Xref} object.
     * @return a boolean.
     */
    protected boolean isAnIdentifier(Xref ref){
        if (XrefUtils.isXrefAnIdentifier(ref)){
            return true;
        }
        else if (ref.getQualifier() == null && CvTermUtils.isCvTerm(ref.getDatabase(), Xref.INTERPRO_MI, Xref.INTERPRO)){
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
     * @return a {@link java.util.Collection} object.
     */
    @XmlTransient
    public Collection<Xref> getIdentifiers() {
        if (identifiers == null){
            identifiers = new FullIdentifierList();
        }
        return identifiers;
    }

    /**
     * <p>Getter for the field <code>interpro</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    @XmlTransient
    public String getInterpro() {
        return this.interpro != null ? this.interpro.getId() : null;
    }

    /**
     * <p>Setter for the field <code>interpro</code>.</p>
     *
     * @param interpro a {@link java.lang.String} object.
     */
    public void setInterpro(String interpro) {
        FullIdentifierList featureIdentifiers = (FullIdentifierList) getIdentifiers();

        // add new interpro if not null
        if (interpro != null){
            CvTerm interproDatabase = CvTermUtils.createInterproDatabase();
            CvTerm identityQualifier = CvTermUtils.createIdentityQualifier();
            // first remove old chebi if not null
            if (this.interpro != null){
                featureIdentifiers.removeOnly(this.interpro);
            }
            this.interpro = new XmlXref(interproDatabase, interpro, identityQualifier);
            featureIdentifiers.addOnly(this.interpro);
        }
        // remove all interpro if the collection is not empty
        else if (!featureIdentifiers.isEmpty()) {
            XrefUtils.removeAllXrefsWithDatabase(featureIdentifiers, Xref.INTERPRO_MI, Xref.INTERPRO);
            this.interpro = null;
        }
    }


    /**
     * <p>processAddedIdentifierEvent.</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processAddedIdentifierEvent(Xref added) {
        // the added identifier is interpro and it is not the current interpro identifier
        if (interpro != added && XrefUtils.isXrefFromDatabase(added, Xref.INTERPRO_MI, Xref.INTERPRO)){
            // the current interpro identifier is not identity, we may want to set interpro Identifier
            if (!XrefUtils.doesXrefHaveQualifier(interpro, Xref.IDENTITY_MI, Xref.IDENTITY)){
                // the interpro identifier is not set, we can set the interpro identifier
                if (interpro == null){
                    interpro = added;
                }
                else if (XrefUtils.doesXrefHaveQualifier(added, Xref.IDENTITY_MI, Xref.IDENTITY)){
                    interpro = added;
                }
                // the added xref is secondary object and the current interpro identifier is not a secondary object, we reset interpro identifier
                else if (!XrefUtils.doesXrefHaveQualifier(interpro, Xref.SECONDARY_MI, Xref.SECONDARY)
                        && XrefUtils.doesXrefHaveQualifier(added, Xref.SECONDARY_MI, Xref.SECONDARY)){
                    interpro = added;
                }
            }
            else if (added.getQualifier() == null){
                interpro = added;
            }
        }
    }

    /**
     * <p>processRemovedIdentifierEvent.</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processRemovedIdentifierEvent(Xref removed) {
        if (interpro != null && interpro.equals(removed)){
            interpro = XrefUtils.collectFirstIdentifierWithDatabase(getIdentifiers(), Xref.INTERPRO_MI, Xref.INTERPRO);
        }
    }

    /**
     * <p>clearPropertiesLinkedToIdentifiers.</p>
     */
    protected void clearPropertiesLinkedToIdentifiers() {
        interpro = null;
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

        private JAXBSecondaryXrefList() {
            super();
            if (identifiers == null){
                initialiseIdentifiers();
            }
        }

        protected boolean addXref(Integer index, Xref xref) {
            if (XrefUtils.isXrefAnIdentifier(xref)){
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
        public FullIdentifierList(){
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
