package psidev.psi.mi.jami.xml.model.extension.xml300;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.xml.model.extension.IInteractorXrefContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Xref container for interactors
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif300", name = "interactorXrefContainer")
public class InteractorXrefContainer extends XrefContainer implements IInteractorXrefContainer {

    private List<Xref> identifiers;

    /** {@inheritDoc} */
    @Override
    protected void processAddedPrimaryRef(Xref added) {
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
     * @return a {@link Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        if (identifiers == null){
            initialiseIdentifiers();
        }
        return identifiers;
    }

    /**
     * <p>getPreferredIdentifier.</p>
     *
     * @return a {@link Xref} object.
     */
    public Xref getPreferredIdentifier() {
        return !getIdentifiers().isEmpty() ? identifiers.iterator().next() : null;
    }

    /**
     * <p>initialiseIdentifiers.</p>
     */
    protected void initialiseIdentifiers(){
        this.identifiers = new ArrayList<Xref>();
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
}
