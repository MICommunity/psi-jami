package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.collection.AbstractListHavingProperties;

/**
 * Default implementation for Source
 *
 * Notes: The equals and hashcode methods have been overridden to be consistent with UnambiguousCvTermComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/01/13</pre>
 */
public class DefaultSource extends DefaultCvTerm implements Source {

    private Annotation url;
    private Annotation postalAddress;
    private Publication bibRef;

    /**
     * <p>Constructor for DefaultSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     */
    public DefaultSource(String shortName) {
        super(shortName);
    }

    /**
     * <p>Constructor for DefaultSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultSource(String shortName, Xref ontologyId) {
        super(shortName, ontologyId);
    }

    /**
     * <p>Constructor for DefaultSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public DefaultSource(String shortName, String fullName, Xref ontologyId) {
        super(shortName, fullName, ontologyId);
    }

    /**
     * <p>Constructor for DefaultSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param url a {@link java.lang.String} object.
     * @param address a {@link java.lang.String} object.
     * @param bibRef a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public DefaultSource(String shortName, String url, String address, Publication bibRef) {
        super(shortName);
        setUrl(url);
        setPostalAddress(address);
        this.bibRef = bibRef;
    }

    /**
     * <p>Constructor for DefaultSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param url a {@link java.lang.String} object.
     * @param address a {@link java.lang.String} object.
     * @param bibRef a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public DefaultSource(String shortName, Xref ontologyId, String url, String address, Publication bibRef) {
        super(shortName, ontologyId);
        setUrl(url);
        setPostalAddress(address);
        this.bibRef = bibRef;
    }

    /**
     * <p>Constructor for DefaultSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param ontologyId a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param url a {@link java.lang.String} object.
     * @param address a {@link java.lang.String} object.
     * @param bibRef a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public DefaultSource(String shortName, String fullName, Xref ontologyId, String url, String address, Publication bibRef) {
        super(shortName, fullName, ontologyId);
        setUrl(url);
        setPostalAddress(address);
        this.bibRef = bibRef;
    }

    /**
     * <p>Constructor for DefaultSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param miId a {@link java.lang.String} object.
     */
    public DefaultSource(String shortName, String miId) {
        super(shortName, miId);
    }

    /**
     * <p>Constructor for DefaultSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param miId a {@link java.lang.String} object.
     */
    public DefaultSource(String shortName, String fullName, String miId) {
        super(shortName, fullName, miId);
    }

    /**
     * <p>Constructor for DefaultSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param miId a {@link java.lang.String} object.
     * @param url a {@link java.lang.String} object.
     * @param address a {@link java.lang.String} object.
     * @param bibRef a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public DefaultSource(String shortName, String miId, String url, String address, Publication bibRef) {
        super(shortName, miId);
        setUrl(url);
        setPostalAddress(address);
        this.bibRef = bibRef;
    }

    /**
     * <p>Constructor for DefaultSource.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param miId a {@link java.lang.String} object.
     * @param url a {@link java.lang.String} object.
     * @param address a {@link java.lang.String} object.
     * @param bibRef a {@link psidev.psi.mi.jami.model.Publication} object.
     */
    public DefaultSource(String shortName, String fullName, String miId, String url, String address, Publication bibRef) {
        super(shortName, fullName, miId);
        setUrl(url);
        setPostalAddress(address);
        this.bibRef = bibRef;
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
     * <p>getPublication</p>
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
     * <p>processAddedAnnotationEvent</p>
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
     * <p>processRemovedAnnotationEvent</p>
     *
     * @param removed a {@link psidev.psi.mi.jami.model.Annotation} object.
     */
    protected void processRemovedAnnotationEvent(Annotation removed) {
        if (url != null && url.equals(removed)){
            url = AnnotationUtils.collectFirstAnnotationWithTopic(getAnnotations(), Annotation.URL_MI, Annotation.URL);
        }
        else if (postalAddress != null && postalAddress.equals(removed)){
            postalAddress = AnnotationUtils.collectFirstAnnotationWithTopic(getAnnotations(), null, Annotation.POSTAL_ADDRESS);;
        }
    }

    /**
     * <p>clearPropertiesLinkedToAnnotations</p>
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
