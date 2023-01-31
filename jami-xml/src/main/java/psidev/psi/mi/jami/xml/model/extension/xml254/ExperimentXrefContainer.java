package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

/**
 * Xref container for Experiment
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>25/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif", name = "experimentXrefContainer")
public class ExperimentXrefContainer extends XrefContainer {

    private Xref imexId;
    private Publication publication;

    /**
     * <p>Getter for the field <code>publication</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        return publication;
    }

    /**
     * <p>Setter for the field <code>publication</code>.</p>
     *
     * @param publication a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public void setPublication(Publication publication) {
        this.publication = publication;
    }

    /**
     * <p>processAddedPotentialImex.</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processAddedPotentialImex(Xref added) {
        if (publication != null){
            // the added identifier is imex and the current imex is not set
            if (publication.getImexId() == null && XrefUtils.isXrefFromDatabase(added, Xref.IMEX_MI, Xref.IMEX)){
                // the added xrefContainer is imex-primary
                if (XrefUtils.doesXrefHaveQualifier(added, Xref.IMEX_PRIMARY_MI, Xref.IMEX_PRIMARY)){
                    imexId = added;
                    publication.getXrefs().add(added);
                }
            }
        }
    }

    /**
     * <p>processRemovedPotentialImex.</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected void processRemovedPotentialImex(Xref removed) {
        if (publication != null){
            // the removed identifier is pubmed
            if (imexId != null && imexId.equals(removed)){
                imexId = null;
                publication.getXrefs().remove(removed);
            }
        }
    }

    /**
     * <p>clearImexId.</p>
     */
    protected void clearImexId() {
        if (imexId != null){
            publication.getXrefs().remove(imexId);
        }
        imexId = null;
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseXrefs() {
        super.initialiseXrefsWith(new FullXrefList());
    }

    ///////////////////////////// classes
    //////////////////////////////// private class

    private class FullXrefList extends AbstractListHavingProperties<Xref> {
        public FullXrefList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Xref added) {
            processAddedPotentialImex(added);
        }

        @Override
        protected void processRemovedObjectEvent(Xref removed) {
            processRemovedPotentialImex(removed);
        }

        @Override
        protected void clearProperties() {
            clearImexId();
        }
    }
}
