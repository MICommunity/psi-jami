package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.Locatable;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CurationDepth;
import psidev.psi.mi.jami.model.Experiment;
import psidev.psi.mi.jami.model.Publication;
import psidev.psi.mi.jami.model.Source;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.utils.PsiXmlUtils;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Xml implementation of a Publication
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>22/07/13</pre>
 */

@XmlAccessorType(XmlAccessType.NONE)
public class BibRef
        implements Publication, FileSourceContext, Locatable
{

    private static final Logger logger = Logger.getLogger("BibRef");

    private PublicationXrefContainer xrefContainer;
    @XmlLocation
    @XmlTransient
    private Locator locator;
    private PsiXmlLocator sourceLocator;
    private Collection<Experiment> experiments;
    private Date releasedDate;
    private Source source;

    private JAXBAttributeWrapper jaxbAttributeWrapper;

    /**
     * <p>Constructor for BibRef.</p>
     */
    public BibRef(){
    }

    /**
     * <p>Constructor for BibRef.</p>
     *
     * @param identifier a {@link Xref} object.
     */
    public BibRef(Xref identifier){
        this();

        if (identifier != null){
            getIdentifiers().add(identifier);
        }
    }

    /**
     * <p>Constructor for BibRef.</p>
     *
     * @param identifier a {@link Xref} object.
     * @param curationDepth a {@link CurationDepth} object.
     * @param source a {@link Source} object.
     */
    public BibRef(Xref identifier, CurationDepth curationDepth, Source source){
        this(identifier);
        if (curationDepth != null){
            setCurationDepth(curationDepth);
        }
        this.source = source;
    }

    /**
     * <p>Constructor for BibRef.</p>
     *
     * @param identifier a {@link Xref} object.
     * @param imexId a {@link String} object.
     * @param source a {@link Source} object.
     */
    public BibRef(Xref identifier, String imexId, Source source){
        this(identifier, CurationDepth.IMEx, source);
        assignImexId(imexId);
    }

    /**
     * <p>Constructor for BibRef.</p>
     *
     * @param pubmed a {@link String} object.
     */
    public BibRef(String pubmed){

        if (pubmed != null){
            setPubmedId(pubmed);
        }
    }

    /**
     * <p>Constructor for BibRef.</p>
     *
     * @param pubmed a {@link String} object.
     * @param curationDepth a {@link CurationDepth} object.
     * @param source a {@link Source} object.
     */
    public BibRef(String pubmed, CurationDepth curationDepth, Source source){
        this(pubmed);
        if (curationDepth != null){
            setCurationDepth(curationDepth);
        }
        this.source = source;
    }

    /**
     * <p>Constructor for BibRef.</p>
     *
     * @param pubmed a {@link String} object.
     * @param imexId a {@link String} object.
     * @param source a {@link Source} object.
     */
    public BibRef(String pubmed, String imexId, Source source){
        this(pubmed, CurationDepth.IMEx, source);
        assignImexId(imexId);
    }

    /**
     * <p>Constructor for BibRef.</p>
     *
     * @param title a {@link String} object.
     * @param journal a {@link String} object.
     * @param publicationDate a {@link Date} object.
     */
    public BibRef(String title, String journal, Date publicationDate){
        setTitle(title);
        setJournal(journal);
        setPublicationDate(publicationDate);
    }

    /**
     * <p>Constructor for BibRef.</p>
     *
     * @param title a {@link String} object.
     * @param journal a {@link String} object.
     * @param publicationDate a {@link Date} object.
     * @param curationDepth a {@link CurationDepth} object.
     * @param source a {@link Source} object.
     */
    public BibRef(String title, String journal, Date publicationDate, CurationDepth curationDepth, Source source){
        this(title, journal, publicationDate);
        setCurationDepth(curationDepth);
        this.source = source;
    }

    /**
     * <p>Constructor for BibRef.</p>
     *
     * @param title a {@link String} object.
     * @param journal a {@link String} object.
     * @param publicationDate a {@link Date} object.
     * @param imexId a {@link String} object.
     * @param source a {@link Source} object.
     */
    public BibRef(String title, String journal, Date publicationDate, String imexId, Source source){
        this(title, journal, publicationDate, CurationDepth.IMEx, source);
        assignImexId(imexId);
    }

    /**
     * <p>getPubmedId.</p>
     *
     * @return a {@link String} object.
     */
    public String getPubmedId() {
        return xrefContainer != null ? xrefContainer.getPubmedId() : null;
    }

    /** {@inheritDoc} */
    public void setPubmedId(String pubmedId) {
        if (xrefContainer == null && pubmedId != null){
            xrefContainer = new PublicationXrefContainer();
        }
        xrefContainer.setPubmedId(pubmedId);
    }

    /**
     * <p>getDoi.</p>
     *
     * @return a {@link String} object.
     */
    public String getDoi() {
        return xrefContainer != null ? xrefContainer.getDoi() : null;
    }

    /** {@inheritDoc} */
    public void setDoi(String doi) {
        if (xrefContainer == null && doi != null){
            xrefContainer = new PublicationXrefContainer();
        }
        xrefContainer.setDoi(doi);
    }

    /**
     * <p>getIdentifiers.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Xref> getIdentifiers() {
        if (xrefContainer == null){
            xrefContainer = new PublicationXrefContainer();
        }
        return xrefContainer.getIdentifiers();
    }

    /**
     * <p>getImexId.</p>
     *
     * @return a {@link String} object.
     */
    public String getImexId() {
        return this.xrefContainer != null ? this.xrefContainer.getImexId() : null;
    }

    /** {@inheritDoc} */
    public void assignImexId(String identifier) {
        if (xrefContainer == null && identifier != null){
            xrefContainer = new PublicationXrefContainer();
            setCurationDepth(CurationDepth.IMEx);
        }
        this.xrefContainer.assignImexId(identifier);
    }

    /**
     * <p>getTitle.</p>
     *
     * @return a {@link String} object.
     */
    public String getTitle() {
        return this.jaxbAttributeWrapper != null ? this.jaxbAttributeWrapper.title : null;
    }

    /** {@inheritDoc} */
    public void setTitle(String title) {
        if (jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        this.jaxbAttributeWrapper.title = title;
    }

    /**
     * <p>getJournal.</p>
     *
     * @return a {@link String} object.
     */
    public String getJournal() {
        return this.jaxbAttributeWrapper != null ? this.jaxbAttributeWrapper.journal : null;
    }

    /** {@inheritDoc} */
    public void setJournal(String journal) {
        if (jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        this.jaxbAttributeWrapper.journal = journal;
    }

    /**
     * <p>getPublicationDate.</p>
     *
     * @return a {@link Date} object.
     */
    public Date getPublicationDate() {
        return this.jaxbAttributeWrapper != null ? this.jaxbAttributeWrapper.publicationDate : null;
    }

    /** {@inheritDoc} */
    public void setPublicationDate(Date date) {
        if (jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        this.jaxbAttributeWrapper.publicationDate = date;
    }

    /**
     * <p>getAuthors.</p>
     *
     * @return a {@link List} object.
     */
    public List<String> getAuthors() {
        if (jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        return this.jaxbAttributeWrapper.authors;
    }

    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Xref> getXrefs() {
        if (xrefContainer == null){
            xrefContainer = new PublicationXrefContainer();
        }
        return xrefContainer.getXrefs();
    }

    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        return this.jaxbAttributeWrapper.annotations;
    }

    /**
     * <p>Getter for the field <code>experiments</code>.</p>
     *
     * @return a {@link Collection} object.
     */
    public Collection<Experiment> getExperiments() {
        if (experiments == null){
            initialiseExperiments();
        }
        return this.experiments;
    }

    /**
     * <p>getCurationDepth.</p>
     *
     * @return a {@link CurationDepth} object.
     */
    public CurationDepth getCurationDepth() {
        return this.jaxbAttributeWrapper != null ? this.jaxbAttributeWrapper.curationDepth : CurationDepth.undefined;
    }

    /** {@inheritDoc} */
    public void setCurationDepth(CurationDepth curationDepth) {

        if (getImexId() != null && curationDepth != null && !curationDepth.equals(CurationDepth.IMEx)){
            throw new IllegalArgumentException("The curationDepth " + curationDepth.toString() + " is not allowed because the publication has an IMEx id so it has IMEx curation depth.");
        }
        else if (getImexId() != null && curationDepth == null){
            throw new IllegalArgumentException("The curationDepth cannot be null/not specified because the publication has an IMEx id so it has IMEx curation depth.");
        }
        if (jaxbAttributeWrapper == null){
            initialiseAnnotationWrapper();
        }
        if (curationDepth == null) {
            this.jaxbAttributeWrapper.curationDepth = CurationDepth.undefined;
        }
        else {
            this.jaxbAttributeWrapper.curationDepth = curationDepth;
        }
    }

    /**
     * <p>Getter for the field <code>releasedDate</code>.</p>
     *
     * @return a {@link Date} object.
     */
    public Date getReleasedDate() {
        return this.releasedDate;
    }

    /** {@inheritDoc} */
    public void setReleasedDate(Date released) {
        this.releasedDate = released;
    }

    /**
     * <p>Getter for the field <code>source</code>.</p>
     *
     * @return a {@link Source} object.
     */
    public Source getSource() {
        return this.source;
    }

    /** {@inheritDoc} */
    public void setSource(Source source) {
        this.source = source;
    }

    /** {@inheritDoc} */
    public boolean addExperiment(Experiment exp) {
        if (exp == null){
            return false;
        }
        else {
            if (getExperiments().add(exp)){
                exp.setPublication(this);
                return true;
            }
            return false;
        }
    }

    /** {@inheritDoc} */
    public boolean removeExperiment(Experiment exp) {
        if (exp == null){
            return false;
        }
        else {
            if (getExperiments().remove(exp)){
                exp.setPublication(null);
                return true;
            }
            return false;
        }
    }

    /** {@inheritDoc} */
    public boolean addAllExperiments(Collection<? extends Experiment> exps) {
        if (exps == null){
            return false;
        }
        else {
            boolean added = false;

            for (Experiment exp : exps){
                if (addExperiment(exp)){
                    added = true;
                }
            }
            return added;
        }
    }

    /** {@inheritDoc} */
    public boolean removeAllExperiments(Collection<? extends Experiment> exps) {
        if (exps == null){
            return false;
        }
        else {
            boolean removed = false;

            for (Experiment exp : exps){
                if (removeExperiment(exp)){
                    removed = true;
                }
            }
            return removed;
        }
    }

    /**
     * <p>setJAXBXref.</p>
     *
     * @param xrefContainer a {@link PublicationXrefContainer} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name = "xref")
    public void setJAXBXref(PublicationXrefContainer xrefContainer) {
        this.xrefContainer = xrefContainer;
    }

    /**
     * <p>setJAXBAttributeWrapper.</p>
     *
     * @param wrapper a {@link JAXBAttributeWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif", name="attributeList")
    public void setJAXBAttributeWrapper(JAXBAttributeWrapper wrapper) {
        this.jaxbAttributeWrapper = wrapper;
    }

    /** {@inheritDoc} */
    @Override
    public Locator sourceLocation() {
        return (Locator)getSourceLocator();
    }

    /**
     * <p>Getter for the field <code>sourceLocator</code>.</p>
     *
     * @return a {@link FileSourceLocator} object.
     */
    public FileSourceLocator getSourceLocator() {
        if (sourceLocator == null && locator != null){
            sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
        }
        return sourceLocator;
    }

    /** {@inheritDoc} */
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            this.sourceLocator = null;
        }
        else if (sourceLocator instanceof PsiXmlLocator){
            this.sourceLocator = (PsiXmlLocator)sourceLocator;
        }
        else {
            this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
        }
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getSourceLocator() != null ? "Bibref: "+getSourceLocator().toString():super.toString());
    }

    /**
     * <p>initialiseAnnotationWrapper.</p>
     */
    protected void initialiseAnnotationWrapper(){
        this.jaxbAttributeWrapper = new JAXBAttributeWrapper();
    }

    /**
     * <p>initialiseExperiments.</p>
     */
    protected void initialiseExperiments(){
        this.experiments = new ArrayList<Experiment>();
    }

    ////////////////////////////////////////////////////////////////// classes

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif", name="bibrefAttributeWrapper")
    public static class JAXBAttributeWrapper implements Locatable, FileSourceContext{
        private PsiXmlLocator sourceLocator;
        @XmlLocation
        @XmlTransient
        private Locator locator;
        private List<Annotation> annotations;
        private JAXBAttributeList jaxbAttributeList;
        private String title;
        private String journal;
        private Date publicationDate;
        private List<String> authors;
        private CurationDepth curationDepth;

        public JAXBAttributeWrapper(){
            initialiseAnnotations();
            initialiseAuthors();
            this.curationDepth = CurationDepth.undefined;
            this.title = null;
            this.journal = null;
            this.publicationDate = null;
        }

        @Override
        public Locator sourceLocation() {
            return (Locator)getSourceLocator();
        }

        public FileSourceLocator getSourceLocator() {
            if (sourceLocator == null && locator != null){
                sourceLocator = new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null);
            }
            return sourceLocator;
        }

        public void setSourceLocator(FileSourceLocator sourceLocator) {
            if (sourceLocator == null){
                this.sourceLocator = null;
            }
            else if (sourceLocator instanceof PsiXmlLocator){
                this.sourceLocator = (PsiXmlLocator)sourceLocator;
            }
            else {
                this.sourceLocator = new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null);
            }
        }

        protected void initialiseAnnotations(){
            annotations = new ArrayList<Annotation>();
        }

        protected void initialiseAuthors(){
            this.authors = new ArrayList<String>();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif", type= DefaultXmlAnnotation.class, name="attribute", required = true)
        public List<Annotation> getJAXBAttributes() {
            if (this.jaxbAttributeList == null){
                this.jaxbAttributeList = new JAXBAttributeList();
            }
            return this.jaxbAttributeList;
        }

        /**
         * The attribute list used by JAXB to populate participant annotations
         */
        private class JAXBAttributeList extends ArrayList<Annotation>{

            public JAXBAttributeList(){
                super();
            }

            @Override
            public boolean add(Annotation annot) {
                return processAnnotation(null, annot);
            }

            @Override
            public boolean addAll(Collection<? extends Annotation> c) {
                if (c == null){
                    return false;
                }
                boolean added = false;

                for (Annotation a : c){
                    if (add(a)){
                        added = true;
                    }
                }
                return added;
            }

            @Override
            public void add(int index, Annotation element) {
                processAnnotation(index, element);
            }

            @Override
            public boolean addAll(int index, Collection<? extends Annotation> c) {
                int newIndex = index;
                if (c == null){
                    return false;
                }
                boolean add = false;
                for (Annotation a : c){
                    if (processAnnotation(newIndex, a)){
                        newIndex++;
                        add = true;
                    }
                }
                return add;
            }

            private boolean processAnnotation(Integer index, Annotation annot) {
                if (annot == null){
                    return false;
                }
                if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.PUBLICATION_TITLE_MI, Annotation.PUBLICATION_TITLE)){
                    title = annot.getValue();
                    return false;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.PUBLICATION_JOURNAL_MI, Annotation.PUBLICATION_JOURNAL)){
                    journal = annot.getValue();
                    return false;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.PUBLICATION_YEAR_MI, Annotation.PUBLICATION_YEAR)){
                    if (annot.getValue() == null){
                        publicationDate = null;
                        return false;
                    }
                    else {
                        try {
                            publicationDate = PsiXmlUtils.YEAR_FORMAT.parse(annot.getValue().trim());
                            return false;
                        } catch (ParseException e) {
                            e.printStackTrace();
                            publicationDate = null;
                            return addAnnotation(index, annot);
                        }
                    }
                } else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.CURATION_DEPTH_MI, Annotation.CURATION_DEPTH) && annot.getValue() != null) {
                    if (Annotation.IMEX_CURATION.equalsIgnoreCase(annot.getValue())) {
                        curationDepth = CurationDepth.IMEx;
                        return false;
                    } else if (Annotation.MIMIX_CURATION.equalsIgnoreCase(annot.getValue())) {
                        curationDepth = CurationDepth.MIMIx;
                        return false;
                    } else if (Annotation.RAPID_CURATION.equalsIgnoreCase(annot.getValue())) {
                        curationDepth = CurationDepth.rapid_curation;
                        return false;
                    } else {
                        curationDepth = CurationDepth.undefined;
                        return false;
                    }
                } else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.IMEX_CURATION_MI, Annotation.IMEX_CURATION)) {
                    if(curationDepth!=null && !curationDepth.equals(CurationDepth.undefined) && !curationDepth.equals(CurationDepth.IMEx)) {
                        //just in case was annotated twice in the file we check for consistency
                        logger.log(Level.WARNING, "The curationDepth has already assigned a different value: " +  curationDepth + " it will be overwritten with " +  CurationDepth.IMEx);
                    }
                    curationDepth = CurationDepth.IMEx;
                    return false;
                } else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.MIMIX_CURATION_MI, Annotation.MIMIX_CURATION)) {
                    if(curationDepth!=null && !curationDepth.equals(CurationDepth.undefined) && !curationDepth.equals(CurationDepth.MIMIx)) {
                        //just in case was annotated twice in the file we check for consistency
                        logger.log(Level.WARNING, "The curationDepth has already assigned a different value: " +  curationDepth + " it will be overwritten with " +  CurationDepth.MIMIx);
                    }
                    curationDepth = CurationDepth.MIMIx;
                    return false;
                } else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.RAPID_CURATION_MI, Annotation.RAPID_CURATION)) {
                    if (curationDepth != null && !curationDepth.equals(CurationDepth.undefined) && !curationDepth.equals(CurationDepth.rapid_curation)) {
                        //just in case was annotated twice in the file we check for consistency
                        logger.log(Level.WARNING, "The curationDepth has already assigned a different value: " + curationDepth + " it will be overwritten with " + CurationDepth.rapid_curation);
                    }
                    curationDepth = CurationDepth.rapid_curation;
                    return false;
                }
                else if (AnnotationUtils.doesAnnotationHaveTopic(annot, Annotation.AUTHOR_MI, Annotation.AUTHOR)){
                    if (annot.getValue() == null){
                        authors.clear();
                    }
                    else if (annot.getValue().contains(",")){
                        authors.addAll(Arrays.asList(annot.getValue().split(",")));
                    }
                    else {
                        authors.add(annot.getValue());
                    }
                    return false;
                }
                else {
                    return addAnnotation(index, annot);
                }
            }

            private boolean addAnnotation(Integer index, Annotation annot) {
                if (index == null){
                    return annotations.add(annot);
                }
                annotations.add(index, annot);
                return true;
            }
        }

        @Override
        public String toString() {
            return "BibRef Attribute List: "+(getSourceLocator() != null ? getSourceLocator().toString():super.toString());
        }
    }
}
