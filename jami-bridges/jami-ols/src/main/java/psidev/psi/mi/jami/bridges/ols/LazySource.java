package psidev.psi.mi.jami.bridges.ols;


import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.model.impl.DefaultAnnotation;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;
import uk.ac.ebi.pride.utilities.ols.web.service.client.OLSClient;

import java.util.logging.Logger;

/**
 * A lazy cvTerm which will only fetch metadata when required.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 14/08/13

 */
public class LazySource extends LazyCvTerm implements Source {

    protected final Logger log = Logger.getLogger(LazySource.class.getName());

    private Annotation url;
    private Annotation postalAddress;
    private Publication bibRef;

    /**
     * <p>Constructor for LazySource.</p>
     *
     * @param olsClient a {@link uk.ac.ebi.pride.utilities.ols.web.service.client.OLSClient} object.
     * @param fullName a {@link java.lang.String} object.
     * @param identityRef a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param ontologyName a {@link java.lang.String} object.
     */
    public LazySource(OLSClient olsClient, String fullName, Xref identityRef, String ontologyName) {
        super(olsClient, fullName, identityRef, ontologyName);
    }

    /** {@inheritDoc} */
    @Override
    protected void initialiseAnnotations() {
        initialiseAnnotationsWith(new SourceAnnotationList());
    }

    /**
     * <p>Getter for the field <code>url</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getUrl() {
        return this.url != null ? this.url.getValue() : null;
    }

    /** {@inheritDoc} */
    public void setUrl(String url) {
        SourceAnnotationList sourceAnnotationList = (SourceAnnotationList)getAnnotations();

        // add new url if not null
        if (url != null){
            CvTerm urlTopic = CvTermUtils.createMICvTerm(Annotation.URL, Annotation.URL_MI);
            // first remove old url if not null
            if (this.url != null){
                sourceAnnotationList.removeOnly(this.url);
            }
            this.url = new DefaultAnnotation(urlTopic, url);
            sourceAnnotationList.addOnly(this.url);
        }
        // remove all url if the collection is not empty
        else if (!sourceAnnotationList.isEmpty()) {
            AnnotationUtils.removeAllAnnotationsWithTopic(sourceAnnotationList, Annotation.URL_MI, Annotation.URL);
            this.url = null;
        }
    }

    /**
     * <p>Getter for the field <code>postalAddress</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    public String getPostalAddress() {
        return this.postalAddress != null ? this.postalAddress.getValue() : null;
    }

    /** {@inheritDoc} */
    public void setPostalAddress(String address) {
        SourceAnnotationList sourceAnnotationList = (SourceAnnotationList)getAnnotations();

        // add new url if not null
        if (address != null){
            CvTerm addressTopic = new DefaultCvTerm(Annotation.POSTAL_ADDRESS);
            // first remove old url if not null
            if (this.postalAddress != null){
                sourceAnnotationList.removeOnly(this.postalAddress);
            }
            this.postalAddress = new DefaultAnnotation(addressTopic, address);
            sourceAnnotationList.addOnly(this.postalAddress);
        }
        // remove all url if the collection is not empty
        else if (!sourceAnnotationList.isEmpty()) {
            AnnotationUtils.removeAllAnnotationsWithTopic(sourceAnnotationList, null, Annotation.POSTAL_ADDRESS);
            this.postalAddress = null;
        }
    }

    /**
     * <p>getPublication.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public Publication getPublication() {
        return this.bibRef;
    }

    /** {@inheritDoc} */
    public void setPublication(Publication ref) {
        this.bibRef = ref;
    }

    /**
     * <p>processAddedAnnotationEvent.</p>
     *
     * @param added a {@link psidev.psi.mi.jami.model.Annotation} object.
     */
    protected void processAddedAnnotationEvent(Annotation added) {
        if (url == null && AnnotationUtils.doesAnnotationHaveTopic(added, Annotation.URL_MI, Annotation.URL)){
            url = added;
        }
        else if (postalAddress == null && AnnotationUtils.doesAnnotationHaveTopic(added, null, Annotation.POSTAL_ADDRESS)){
            postalAddress = added;
        }
    }

    /**
     * <p>processRemovedAnnotationEvent.</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Annotation} object.
     */
    protected void processRemovedAnnotationEvent(Annotation removed) {
        if (url != null && url.equals(removed)){
            url = null;
        }
        else if (postalAddress != null && postalAddress.equals(removed)){
            postalAddress = null;
        }
    }

    /**
     * <p>clearPropertiesLinkedToAnnotations.</p>
     */
    protected void clearPropertiesLinkedToAnnotations() {
        url = null;
        postalAddress = null;
    }

    private class SourceAnnotationList extends AbstractListHavingProperties<Annotation> {
        public SourceAnnotationList(){
            super();
        }

        @Override
        protected void processAddedObjectEvent(Annotation added) {
            processAddedAnnotationEvent(added);
        }

        @Override
        protected void processRemovedObjectEvent(Annotation removed) {
            processRemovedAnnotationEvent(removed);
        }

        @Override
        protected void clearProperties() {
            clearPropertiesLinkedToAnnotations();
        }
    }
}
