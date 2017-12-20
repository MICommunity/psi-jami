package psidev.psi.mi.jami.listener;

import psidev.psi.mi.jami.model.CurationDepth;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.Xref;

import java.util.Date;

/**
 * Listener to publication changes
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 01/08/13
 * @version $Id: $
 */
public interface PublicationChangeListener extends IdentifiersChangeListener<Publication>, XrefsChangeListener<Publication>, AnnotationsChangeListener<Publication> {

    /**
     * <p>onPubmedIdUpdate</p>
     *
     * @param publication : updated publication
     * @param oldPubmedId : old pubmed
     */
    public void onPubmedIdUpdate(Publication publication , String oldPubmedId);

    /**
     * <p>onDoiUpdate</p>
     *
     * @param publication : updated publication
     * @param oldDoi : old doi
     */
    public void onDoiUpdate(Publication publication , String oldDoi);

    /**
     * <p>onImexIdentifierUpdate</p>
     *
     * @param publication : updated publication
     * @param addedXref : added xref
     */
    public void onImexIdentifierUpdate(Publication publication, Xref addedXref);

    /**
     * <p>onTitleUpdated</p>
     *
     * @param publication : updated publication
     * @param oldTitle : old title
     */
    public void onTitleUpdated(Publication publication , String oldTitle);

    /**
     * <p>onJournalUpdated</p>
     *
     * @param publication : updated publication
     * @param oldJournal : old journal
     */
    public void onJournalUpdated(Publication publication , String oldJournal);

    /**
     * <p>onCurationDepthUpdate</p>
     *
     * @param publication : updated publication
     * @param oldDepth : old depth
     */
    public void onCurationDepthUpdate(Publication publication , CurationDepth oldDepth);

    /**
     * <p>onPublicationDateUpdated</p>
     *
     * @param publication : updated publication
     * @param oldDate : old date
     */
    public void onPublicationDateUpdated(Publication publication , Date oldDate);

    /**
     * <p>onAuthorAdded</p>
     *
     * @param publication : updated publication
     * @param addedAuthor  : added author
     */
    public void onAuthorAdded(Publication publication , String addedAuthor);

    /**
     * <p>onAuthorRemoved</p>
     *
     * @param publication  : updated publication
     * @param removedAuthor : removed author
     */
    public void onAuthorRemoved(Publication publication , String removedAuthor);

    /**
     * <p>onReleaseDateUpdated</p>
     *
     * @param publication  : updated publication
     * @param oldDate : old release date
     */
    public void onReleaseDateUpdated(Publication publication , Date oldDate);

    /**
     * <p>onSourceUpdated</p>
     *
     * @param publication : updated publication
     * @param oldSource : old source
     */
    public void onSourceUpdated(Publication publication , Source oldSource);
}
