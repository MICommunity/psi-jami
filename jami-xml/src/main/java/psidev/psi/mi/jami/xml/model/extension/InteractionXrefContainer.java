package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultXref;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Xref container for interactions
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(name = "interactionXrefContainer")
public class InteractionXrefContainer extends XrefContainer {
    private Xref imexId;
    private Xref complexAcXref;

    private List<Xref> identifiers;

    /** {@inheritDoc} */
    @Override
    protected void processAddedPrimaryRef(Xref added) {
        // identity ref
        if (XrefUtils.isXrefAnIdentifier(added)){
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
    public Collection<Xref> getIdentifiers() {
        if (identifiers == null){
            initialiseIdentifiers();
        }
        return identifiers;
    }

    /**
     * <p>Getter for the field <code>imexId</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getImexId() {
        return this.imexId != null ? this.imexId.getId() : null;
    }

    /**
     * <p>assignImexId.</p>
     *
     * @param identifier a {@link java.lang.String} object.
     */
    public void assignImexId(String identifier) {
        FullXrefList xrefs = (FullXrefList) getXrefs();
        // add new imex if not null
        if (identifier != null){
            CvTerm imexDatabase = CvTermUtils.createImexDatabase();
            CvTerm imexPrimaryQualifier = CvTermUtils.createImexPrimaryQualifier();
            // first remove old imex if not null
            if (this.imexId != null){
                xrefs.removeOnly(this.imexId);
            }
            this.imexId = new XmlXref(imexDatabase, identifier, imexPrimaryQualifier);
            xrefs.addOnly(this.imexId);
        }
        else if (this.imexId != null){
            throw new IllegalArgumentException("The imex id has to be non null.");
        }
    }

    /**
     * <p>Getter for the field <code>complexAc</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getComplexAc() {
        return this.complexAcXref != null ? this.complexAcXref.getId() : null;
    }

    /**
     * <p>Getter for the field <code>complexVersion</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getComplexVersion() {
        return this.complexAcXref != null ? this.complexAcXref.getVersion() : null;
    }

    /**
     * <p>assignComplexAc.</p>
     *
     * @param accession a {@link java.lang.String} object.
     * @param version a {@link java.lang.String} object.
     */
    public void assignComplexAc(String accession, String version) {
        // add new complex ac if not null
        if (accession != null) {
            FullXrefList interactionXrefs = (FullXrefList) getXrefs();

            CvTerm complexPortalDatabase = CvTermUtils.createComplexPortalDatabase();
            CvTerm complexPortalPrimaryQualifier = CvTermUtils.createComplexPortalPrimaryQualifier();
            // first remove old ac if not null
            if (this.complexAcXref != null) {
                if (!accession.equals(complexAcXref.getId())) {
                    // first remove old complexAcXref and creates the new one;
                    interactionXrefs.removeOnly(this.complexAcXref);
                    this.complexAcXref = new DefaultXref(complexPortalDatabase, accession, version, complexPortalPrimaryQualifier);
                    interactionXrefs.addOnly(this.complexAcXref);
                }
            } else {
                this.complexAcXref = new DefaultXref(complexPortalDatabase, accession, version, complexPortalPrimaryQualifier);
                interactionXrefs.addOnly(this.complexAcXref);
            }
        } else {
            throw new IllegalArgumentException("The complex ac has to be non null.");
        }
    }

    /**
     * <p>assignComplexAc.</p>
     *
     * @param accession a {@link java.lang.String} object.
     */
    public void assignComplexAc(String accession) {
        // add new complex ac if not null
        if (accession != null) {
            String id;
            String version;

            //It checks if the accession is valid and split the version if it is provided
            String[] splittedComplexAc = accession.split("\\.");
            if (splittedComplexAc.length == 1) {
                id = splittedComplexAc[0];
                version = "1";
            } else if (splittedComplexAc.length == 2) {
                {
                    id = splittedComplexAc[0];
                    version = splittedComplexAc[1];
                }
            } else {
                throw new IllegalArgumentException("The complex ac has a non valid format (e.g. CPX-12345.1)");
            }
            assignComplexAc(id, version);

        } else {
            throw new IllegalArgumentException("The complex ac has to be non null.");
        }
    }

    /**
     * <p>processAddedPotentialImex.</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processAddedPotentialImex(Xref added) {

        // the added identifier is imex and the current imex is not set
        if (imexId == null && XrefUtils.isXrefFromDatabase(added, Xref.IMEX_MI, Xref.IMEX)){
            // the added xrefContainer is imex-primary
            if (XrefUtils.doesXrefHaveQualifier(added, Xref.IMEX_PRIMARY_MI, Xref.IMEX_PRIMARY)){
                imexId = added;
            }
        }
    }

    /**
     * <p>processRemovedPotentialImex.</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processRemovedPotentialImex(Xref removed) {
        // the removed identifier is pubmed
        if (imexId != null && imexId.equals(removed)){
            imexId = null;
        }
    }

    /**
     * <p>clearImexId.</p>
     */
    protected void clearImexId() {
        imexId = null;
    }

    protected void processAddedPotentialComplexAc(Xref added) {

        // the added identifier is a complex portal accession and the current complex portal accession is not set
        if (complexAcXref == null && XrefUtils.isXrefFromDatabase(added, Xref.COMPLEX_PORTAL_MI, Xref.COMPLEX_PORTAL)){
            // the added xrefContainer is complex-primary
            if (XrefUtils.doesXrefHaveQualifier(added, Xref.COMPLEX_PRIMARY_MI, Xref.COMPLEX_PRIMARY)){
                complexAcXref = added;
            }
        }
    }

    protected void processRemovedPotentialComplexAc(Xref removed) {
        // the removed identifier is pubmed
        if (complexAcXref != null && complexAcXref.equals(removed)){
            complexAcXref = null;
        }
    }

    protected void clearComplexAc() {
        complexAcXref = null;
    }

    /**
     * <p>initialiseIdentifiers.</p>
     */
    protected void initialiseIdentifiers(){
        this.identifiers = new ArrayList<Xref>();
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefs() {
        super.initialiseXrefsWith(new FullXrefList());
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
    private class FullXrefList extends AbstractListHavingProperties<Xref> {
        public FullXrefList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Xref added) {
            processAddedPotentialImex(added);
            processAddedPotentialComplexAc(added);
        }

        @Override
        protected void processRemovedObjectEvent(Xref removed) {
            processRemovedPotentialImex(removed);
            processRemovedPotentialComplexAc(removed);
        }

        @Override
        protected void clearProperties() {
            clearImexId();
            clearComplexAc();
        }
    }
}
