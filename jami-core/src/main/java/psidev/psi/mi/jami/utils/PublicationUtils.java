package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultPublication;

import java.util.Date;

/**
 * Factory for publications
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/13</pre>
 */
public class PublicationUtils {
    
    /**
     * <p>createUnknownBasicPublication</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public static Publication createUnknownBasicPublication(){
        return new DefaultPublication("Mock publication for experiments that do not have a publication reference", (String)null, (Date)null);
    }

    /**
     * <p>createBasicPublicationForModelledInteractions</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public static Publication createBasicPublicationForModelledInteractions(){
        return new DefaultPublication("Mock publication and experiment for modelled interactions that are not interaction evidences.", (String)null, (Date)null);
    }

    /**
     * <p>createPublicationPubmed</p>
     *
     * @param pubmed a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public static Publication createPublicationPubmed(String pubmed){
        return new DefaultPublication(pubmed);
    }

    /**
     * <p>createPublicationDoi</p>
     *
     * @param doi a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public static Publication createPublicationDoi(String doi){
        return new DefaultPublication(XrefUtils.createDoiIdentity(doi));
    }

    /**
     * <p>getPubmedReference</p>
     *
     * @param pub a {@link psidev.psi.mi.jami.model.Publication} object.
     * @return a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public static Xref getPubmedReference(Publication pub){
        if (pub == null){
           return null;
        }
        else if (pub.getPubmedId() == null){
            return null;
        }
        else{
            return XrefUtils.collectFirstIdentifierWithDatabaseAndId(pub.getIdentifiers(), Xref.PUBMED_MI, Xref.PUBMED, pub.getPubmedId());
        }
    }

    /**
     * <p>getDoiReference</p>
     *
     * @param pub a {@link psidev.psi.mi.jami.model.Publication} object.
     * @return a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public static Xref getDoiReference(Publication pub){
        if (pub == null){
            return null;
        }
        else if (pub.getDoi() == null){
            return null;
        }
        else{
            return XrefUtils.collectFirstIdentifierWithDatabaseAndId(pub.getIdentifiers(), Xref.DOI_MI, Xref.DOI, pub.getDoi());
        }
    }
}
