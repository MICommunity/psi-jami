package psidev.psi.mi.jami.bridges.obo;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import uk.ac.ebi.ols.loader.impl.BaseOBO2AbstractLoader;
import uk.ac.ebi.ols.loader.parser.OBO2FormatParser;
import uk.ac.ebi.ols.model.interfaces.DbXref;
import uk.ac.ebi.ols.model.interfaces.Term;
import uk.ac.ebi.ols.model.interfaces.TermSynonym;

import java.io.File;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

/**
 * Abstract obo loader
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>17/07/13</pre>
 */
public abstract class AbstractOboLoader<T extends CvTerm> extends BaseOBO2AbstractLoader {

    /** Constant <code>SHORTLABEL_IDENTIFIER="Unique short label curated by PSI-MI"</code> */
    public static final String SHORTLABEL_IDENTIFIER = "Unique short label curated by PSI-MI";
    /** Constant <code>ALIAS_IDENTIFIER="Alternate label curated by PSI-MI"</code> */
    public static final String ALIAS_IDENTIFIER = "Alternate label curated by PSI-MI";
    /** Constant <code>MOD_SHORTLABEL_IDENTIFIER="Short label curated by PSI-MOD"</code> */
    public static final String MOD_SHORTLABEL_IDENTIFIER = "Short label curated by PSI-MOD";
    /** Constant <code>EXACT_KEY="exact"</code> */
    public static final String EXACT_KEY = "exact";
    /** Constant <code>MOD_ALIAS_IDENTIFIER="Alternate name curated by PSI-MOD"</code> */
    public static final String MOD_ALIAS_IDENTIFIER = "Alternate name curated by PSI-MOD"; //
    /** Constant <code>RESID_IDENTIFIER="Alternate name from RESID"</code> */
    public static final String RESID_IDENTIFIER = "Alternate name from RESID"; //
    /** Constant <code>RESID_MISNOMER_IDENTIFIER="Misnomer tagged alternate name from RES"{trunked}</code> */
    public static final String RESID_MISNOMER_IDENTIFIER = "Misnomer tagged alternate name from RESID";
    /** Constant <code>RESID_NAME_IDENTIFIER="Name from RESID"</code> */
    public static final String RESID_NAME_IDENTIFIER = "Name from RESID"; //
    /** Constant <code>RESID_SYSTEMATIC_IDENTIFIER="Systematic name from RESID"</code> */
    public static final String RESID_SYSTEMATIC_IDENTIFIER = "Systematic name from RESID";   //
    /** Constant <code>UNIPROT_FEATURE_IDENTIFIER="Protein feature description from UniPro"{trunked}</code> */
    public static final String UNIPROT_FEATURE_IDENTIFIER = "Protein feature description from UniProtKB";
    /** Constant <code>XREF_TYPE=3</code> */
    public static final int XREF_TYPE = 3;
    /** Constant <code>PMID_APPLICATION="PMID for application instance"</code> */
    public static final String PMID_APPLICATION = "PMID for application instance";
    /** Constant <code>SO="so"</code> */
    public static final String SO = "so";
    /** Constant <code>SO_MI_REF="MI:0601"</code> */
    public static final String SO_MI_REF = "MI:0601";
    /** Constant <code>HTTP_DEF="http"</code> */
    public static final String HTTP_DEF = "http";
    /** Constant <code>XREF_VALIDATION_REGEXP="id-validation-regexp"</code> */
    public static final String XREF_VALIDATION_REGEXP = "id-validation-regexp";
    /** Constant <code>XREF_VALIDATION_REGEXP_MI_REF="MI:0628"</code> */
    public static final String XREF_VALIDATION_REGEXP_MI_REF = "MI:0628";
    /** Constant <code>SEARCH_URL="search-url"</code> */
    public static final String SEARCH_URL = "search-url";
    /** Constant <code>SEARCH_URL_MI_REF="MI:0615"</code> */
    public static final String SEARCH_URL_MI_REF = "MI:0615";
    /** Constant <code>QUOTE="&quot;"</code> */
    public static final String QUOTE = "&quot;";
    /** Constant <code>META_XREF_SEPARATOR=":"</code> */
    public static final String META_XREF_SEPARATOR = ":";
    /** Constant <code>LINE_BREAK="\n"</code> */
    public static final String LINE_BREAK = "\n";
    /** Constant <code>COMMENT_KEY="comment"</code> */
    public static final String COMMENT_KEY = "comment";
    /** Constant <code>PMID="PMID"</code> */
    public static final String PMID = "PMID";
    /** Constant <code>METHOD_REFERENCE="method reference"</code> */
    public static final String METHOD_REFERENCE = "method reference";
    /** Constant <code>METHOD_REFERENCE_MI_REF="MI:0357"</code> */
    public static final String METHOD_REFERENCE_MI_REF = "MI:0357";
    /** Constant <code>GO="go"</code> */
    public static final String GO = "go";
    /** Constant <code>GO_MI_REF="MI:0448"</code> */
    public static final String GO_MI_REF = "MI:0448";
    /** Constant <code>RESID="resid"</code> */
    public static final String RESID = "resid";
    /** Constant <code>RESID_MI_REF="MI:0248"</code> */
    public static final String RESID_MI_REF = "MI:0248";

    /**
     * Sets up a logger for that class.
     */
    public static final Log log = LogFactory.getLog(AbstractOboLoader.class);

    private CvTerm ontologyDatabase;

    /**
     * <p>Constructor for AbstractOboLoader.</p>
     *
     * @param database a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public AbstractOboLoader(CvTerm database){
        super();
        this.ontologyDatabase = database != null ? database : new DefaultCvTerm("unknown");
    }

    /**
     * <p>Constructor for AbstractOboLoader.</p>
     *
     * @param databaseName a {@link java.lang.String} object.
     */
    public AbstractOboLoader(String databaseName){
        super();
        this.ontologyDatabase = databaseName != null ? new DefaultCvTerm(databaseName) : new DefaultCvTerm("unknown");
    }

    /////////////////////////////
    // AbstractLoader's methods


    /**
     * <p>Getter for the field <code>ontologyDatabase</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    protected CvTerm getOntologyDatabase() {
        return ontologyDatabase;
    }

    /**
     * <p>configure.</p>
     *
     * @param filePath a {@link java.lang.String} object.
     */
    protected void configure(String filePath) {
        /**
         * ensure we get the right logger
         */
        logger = Logger.getLogger(AbstractOboLoader.class);

        try {
            parser = new OBO2FormatParser(filePath);
        } catch (Exception e) {
            logger.fatal("Parse failed: " + e.getMessage(), e);
        }

        ONTOLOGY_DEFINITION = ontologyDatabase.getShortName();
        FULL_NAME = ontologyDatabase.getFullName();
        SHORT_NAME = ontologyDatabase.getShortName();
    }

    /**
     * <p>buildOntology.</p>
     *
     * @param id2Terms a {@link java.util.Map} object.
     * @param name2Terms a {@link java.util.Map} object.
     */
    public void buildOntology(Map<String, T> id2Terms, Map<String, T> name2Terms) {

        // 1. convert and index all terms (note: at this stage we don't handle the hierarchy)
        for ( Iterator iterator = ontBean.getTerms().iterator(); iterator.hasNext(); ) {
            Term term = ( Term ) iterator.next();

            // convert term into a OboTerm
            T ontologyTerm = createNewTerm(term);
            final Collection<TermSynonym> synonyms = term.getSynonyms();
            if( synonyms != null ) {
                for ( TermSynonym synonym : synonyms ) {
                    ontologyTerm.getSynonyms().add(AliasUtils.createAlias(Alias.SYNONYM, Alias.SYNONYM_MI, synonym.getSynonym()));
                }
            }

            id2Terms.put(term.getIdentifier(), ontologyTerm);
            name2Terms.put(term.getName(), ontologyTerm);
        }
    }

    /**
     * <p>createNewTerm.</p>
     *
     * @param t a {@link uk.ac.ebi.ols.model.interfaces.Term} object.
     * @return a T object.
     */
    protected T createNewTerm(Term t) {
        T ontologyTerm = instantiateNewTerm(t.getName(), XrefUtils.createIdentityXref(getOntologyDatabase().getShortName(), getOntologyDatabase().getMIIdentifier(), t.getIdentifier()));

        // load synonyms (alias and shortlabel)
        processSynonyms(t, ontologyTerm);

        // set shortlabel in case it was not set with synonyms
        processShortLabel(t, ontologyTerm);

        // load db xrefs
        processXrefs(t, ontologyTerm);

        // load definition, url, obsolete message
        processDefinition(t, ontologyTerm);

        // load annotations
        processAnnotations(t, ontologyTerm);

        return ontologyTerm;
    }

    /**
     * Process the annotations of a term
     *
     * @param term a {@link uk.ac.ebi.ols.model.interfaces.Term} object.
     * @param ontologyTerm a T object.
     */
    protected void processAnnotations(Term term, T ontologyTerm) {
        Collection<uk.ac.ebi.ols.model.interfaces.Annotation> annotations = term.getAnnotations();

        if (annotations != null){
            for (uk.ac.ebi.ols.model.interfaces.Annotation annot : annotations){
                // only one comment with type null
                if (annot.getAnnotationType() != null && COMMENT_KEY.equals(annot.getAnnotationType())){
                    ontologyTerm.getAnnotations().add(AnnotationUtils.createComment(annot.getAnnotationStringValue()));
                }
            }
        }
    }

    /**
     * <p>processXrefs.</p>
     *
     * @param term a {@link uk.ac.ebi.ols.model.interfaces.Term} object.
     * @param ontologyTerm a T object.
     */
    protected void processXrefs(Term term, T ontologyTerm) {
        Collection<DbXref> dbXrefs = term.getXrefs();

        if (dbXrefs != null){
            String pubmedPrimary = null;
            for (DbXref xref : dbXrefs){

                if (xref.getXrefType() == XREF_TYPE){
                    if (xref.getAccession() == null){
                        processXref(xref.getDbName(), xref.getDescription(), ontologyTerm);
                    }
                    else {
                        processXref(xref.getDbName(), xref.getAccession(), ontologyTerm);
                    }
                }
                else {
                    pubmedPrimary = processXrefDefinition(xref.toString(), xref.getDbName(), xref.getAccession(), pubmedPrimary, ontologyTerm);
                }
            }
        }
    }

    /**
     * <p>processXrefDefinition.</p>
     *
     * @param xref a {@link java.lang.String} object.
     * @param database a {@link java.lang.String} object.
     * @param accession a {@link java.lang.String} object.
     * @param pubmedPrimary a {@link java.lang.String} object.
     * @param ontologyTerm a T object.
     * @return a {@link java.lang.String} object.
     */
    protected String processXrefDefinition(String xref, String database, String accession, String pubmedPrimary, T ontologyTerm) {

        if ( PMID.equalsIgnoreCase(database) ) {
            if (pubmedPrimary == null){
                pubmedPrimary = xref;

                Xref primaryPubmedRef = XrefUtils.createXrefWithQualifier(Xref.PUBMED, Xref.PUBMED_MI, accession, Xref.PRIMARY, Xref.PRIMARY_MI);
                ontologyTerm.getXrefs().add(primaryPubmedRef);
            }
            else {
                Xref pubmedRef = XrefUtils.createXrefWithQualifier(Xref.PUBMED, Xref.PUBMED_MI, accession, METHOD_REFERENCE, METHOD_REFERENCE_MI_REF);
                ontologyTerm.getXrefs().add(pubmedRef);
            }
        }
        else if ( PMID_APPLICATION.equalsIgnoreCase(database) ) {
            Xref pubmedRef = XrefUtils.createXrefWithQualifier(Xref.PUBMED, Xref.PUBMED_MI, accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            ontologyTerm.getXrefs().add(pubmedRef); // MI not MOD
        } else if ( GO.equalsIgnoreCase(database) ) {
            Xref goRef = XrefUtils.createXrefWithQualifier(GO, GO_MI_REF, database + ":" + accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            ontologyTerm.getXrefs().add(goRef); // MI not MOD
        } else if ( RESID.equalsIgnoreCase(database) ) {
            Xref resXref = XrefUtils.createXrefWithQualifier(RESID, RESID_MI_REF, accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            ontologyTerm.getXrefs().add(resXref);
        } else if ( SO.equalsIgnoreCase(database) ) {
            Xref soRef = XrefUtils.createXrefWithQualifier(SO, SO_MI_REF, database + ":" + accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            ontologyTerm.getXrefs().add(soRef);  // MI not MOD
        }else if ( Xref.PUBMED.equalsIgnoreCase(database) ) {
            if (pubmedPrimary == null){
                pubmedPrimary = xref;

                Xref primaryPubmedRef = XrefUtils.createXrefWithQualifier(Xref.PUBMED, Xref.PUBMED_MI, accession, Xref.PRIMARY, Xref.PRIMARY_MI);
                ontologyTerm.getXrefs().add(primaryPubmedRef);
            }
            else {
                Xref pubmedRef = XrefUtils.createXrefWithQualifier(Xref.PUBMED, Xref.PUBMED_MI, accession, METHOD_REFERENCE, METHOD_REFERENCE_MI_REF);
                ontologyTerm.getXrefs().add(pubmedRef);
            }
        }
        else if ( RESID.equalsIgnoreCase(database) ) {
            Xref resXref = XrefUtils.createXrefWithQualifier(RESID, RESID_MI_REF, accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            ontologyTerm.getXrefs().add(resXref);
        }else if ( Xref.CHEBI.equalsIgnoreCase(database) ) {
            Xref chebiRef = XrefUtils.createXrefWithQualifier(Xref.CHEBI, Xref.CHEBI_MI, accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            ontologyTerm.getXrefs().add(chebiRef);  // MOD xref
        } else if ( Annotation.URL.equalsIgnoreCase(database) ) {
            ontologyTerm.getAnnotations().add(AnnotationUtils.createAnnotation(Annotation.URL, Annotation.URL_MI, accession));
        }

        return pubmedPrimary;
    }

    /**
     * <p>processXref.</p>
     *
     * @param db a {@link java.lang.String} object.
     * @param accession a {@link java.lang.String} object.
     * @param ontologyTerm a T object.
     */
    protected void processXref(String db, String accession, T ontologyTerm) {
        // xref validation regexp
        if (XREF_VALIDATION_REGEXP.equalsIgnoreCase(db)){

            String annotationText = accession.trim();

            if (annotationText.startsWith(QUOTE)){
                annotationText = annotationText.substring(QUOTE.length());
            }
            if (annotationText.endsWith(QUOTE)){
                annotationText = annotationText.substring(0, annotationText.indexOf(QUOTE));
            }

            Annotation validation = AnnotationUtils.createAnnotation(XREF_VALIDATION_REGEXP, XREF_VALIDATION_REGEXP_MI_REF, annotationText);  // MI xref
            ontologyTerm.getAnnotations().add(validation);
        }
        // search url
        else if (db == null && accession.startsWith(SEARCH_URL)){
            String url = accession.substring(SEARCH_URL.length());

            if (url.startsWith("\\")){
                url = url.substring(1);
            }
            if (url.endsWith("\\")){
                url = url.substring(0, url.length() - 1);
            }

            Annotation validation = AnnotationUtils.createAnnotation(SEARCH_URL, SEARCH_URL_MI_REF, url);  // MI xref
            ontologyTerm.getAnnotations().add(validation);
        }
        else if (db.equalsIgnoreCase(SEARCH_URL)){
            String url = accession.trim();

            Annotation validation = AnnotationUtils.createAnnotation(SEARCH_URL, SEARCH_URL_MI_REF, url);  // MI xref
            ontologyTerm.getAnnotations().add(validation);
        }
        else if (db.startsWith(SEARCH_URL)){
            String prefix = db.substring(SEARCH_URL.length());
            String url = prefix + META_XREF_SEPARATOR + accession;

            if (url.startsWith("\"")){
                url = url.substring(1);
            }
            if (url.endsWith("\"")){
                url = url.substring(0, url.length() - 1);
            }

            Annotation validation = AnnotationUtils.createAnnotation(SEARCH_URL, SEARCH_URL_MI_REF, url);  // MI xref
            ontologyTerm.getAnnotations().add(validation);
        }
    }

    /**
     * Process the definition of a term
     *
     * @param term a {@link uk.ac.ebi.ols.model.interfaces.Term} object.
     * @param ontologyTerm a T object.
     */
    protected void processDefinition(Term term, T ontologyTerm) {

        String definition = term.getDefinition();
        processDefinition(definition, ontologyTerm);
    }

    /**
     * Process the definition String
     *
     * @param definition a {@link java.lang.String} object.
     * @param ontologyTerm a T object.
     */
    protected void processDefinition(String definition, T ontologyTerm) {
        if ( definition.contains( LINE_BREAK ) ) {
            String[] defArray = definition.split( LINE_BREAK );

            String otherInfoString = null;

            if ( defArray.length == 2 ) {
                createDefinitionFor(defArray[0], ontologyTerm);
                otherInfoString = defArray[1];
                processInfoInDescription(definition, otherInfoString, ontologyTerm);
            } else if ( defArray.length > 2 ) {
                createDefinitionFor(defArray[0], ontologyTerm);

                for (int i = 1; i < defArray.length; i++){
                    otherInfoString = defArray[i];
                    processInfoInDescription(definition, otherInfoString, ontologyTerm);
                }
            }
        }
        else {
            processInfoInDescription(definition, definition, ontologyTerm);
        }
    }

    /**
     * Process the other information in the description
     *
     * @param definition a {@link java.lang.String} object.
     * @param otherInfoString a {@link java.lang.String} object.
     * @param ontologyTerm a T object.
     */
    protected void processInfoInDescription(String definition, String otherInfoString, T ontologyTerm) {

        // URL
        if ( otherInfoString.startsWith( HTTP_DEF ) ) {
            ontologyTerm.getAnnotations().add(AnnotationUtils.createAnnotation(Annotation.URL, Annotation.URL_MI, otherInfoString));
        }
        else if (otherInfoString.contains( HTTP_DEF )){
            String[] defArray = otherInfoString.split( HTTP_DEF );
            String def = null;

            if ( defArray.length == 2 ) {
                def = defArray[0];
                ontologyTerm.getAnnotations().add(AnnotationUtils.createAnnotation(Annotation.URL, Annotation.URL_MI, HTTP_DEF + defArray[1]));
            } else if ( defArray.length > 2 ) {
                def = defArray[0];
                ontologyTerm.getAnnotations().add(AnnotationUtils.createAnnotation(Annotation.URL, Annotation.URL_MI, otherInfoString.substring(def.length())));
            }

            createDefinitionFor(otherInfoString, ontologyTerm);
        }
        // simple definition
        else {
            createDefinitionFor(otherInfoString, ontologyTerm);

        }
    }

    /**
     * <p>processSynonyms.</p>
     *
     * @param term a {@link uk.ac.ebi.ols.model.interfaces.Term} object.
     * @param ontologyTerm a T object.
     */
    protected void processSynonyms(Term term, T ontologyTerm) {
        Collection<TermSynonym> synonyms = term.getSynonyms();

        if (synonyms != null){
            for (TermSynonym synonym : synonyms){
                Term synonymType = synonym.getSynonymType();
                //PSI-MOD-label for MOD
                if (synonymType != null){
                    if (SHORTLABEL_IDENTIFIER.equalsIgnoreCase(synonymType.getName()) || MOD_SHORTLABEL_IDENTIFIER.equalsIgnoreCase(synonymType.getName())){
                        ontologyTerm.setShortName(synonym.getSynonym());
                    }

                    else if (ALIAS_IDENTIFIER.equalsIgnoreCase(synonymType.getName())
                            || MOD_ALIAS_IDENTIFIER.equalsIgnoreCase(synonymType.getName())
                            || RESID_IDENTIFIER.equalsIgnoreCase(synonymType.getName())
                            || RESID_MISNOMER_IDENTIFIER.equalsIgnoreCase(synonymType.getName())
                            || RESID_NAME_IDENTIFIER.equalsIgnoreCase(synonymType.getName())
                            || RESID_SYSTEMATIC_IDENTIFIER.equalsIgnoreCase(synonymType.getName())
                            || UNIPROT_FEATURE_IDENTIFIER.equalsIgnoreCase(synonymType.getName())
                            || EXACT_KEY.equalsIgnoreCase(synonymType.getName())){
                        ontologyTerm.getSynonyms().add(AliasUtils.createAlias(Alias.SYNONYM, Alias.SYNONYM_MI, synonym.getSynonym()));
                    }
                }
            }
        }
    }

    /**
     * <p>processShortLabel.</p>
     *
     * @param term a {@link uk.ac.ebi.ols.model.interfaces.Term} object.
     * @param ontologyTerm a T object.
     */
    protected void processShortLabel(Term term, T ontologyTerm) {
        if (ontologyTerm.getShortName().length() == 0){
            ontologyTerm.setShortName(term.getName());
        }
    }

    /**
     * <p>instantiateNewTerm.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param identity a {@link psidev.psi.mi.jami.model.Xref} object.
     * @return a T object.
     */
    protected abstract T instantiateNewTerm(String name, Xref identity);

    /**
     * <p>createDefinitionFor.</p>
     *
     * @param def a {@link java.lang.String} object.
     * @param term a T object.
     */
    protected abstract void createDefinitionFor(String def, T term);

    /**
     * Parse the given OBO file and build a representation of the DAG into an Ontology.
     *
     * @param file the input file. It has to exist and to be readable, otherwise it will break.
     * @param id2Terms : the map id2Term to populate
     * @param name2Terms : the map name2Term to populate
     */
    public void parseOboFile( File file, Map<String, T> id2Terms, Map<String, T> name2Terms) {

        if ( !file.exists() ) {
            throw new IllegalArgumentException( file.getAbsolutePath() + " doesn't exist." );
        }

        if ( !file.canRead() ) {
            throw new IllegalArgumentException( file.getAbsolutePath() + " could not be read." );
        }

        //setup vars and parse file
        configure(file.getAbsolutePath());

        //process into relations
        process();

        // build ontology
        buildOntology(id2Terms, name2Terms);
    }
}
