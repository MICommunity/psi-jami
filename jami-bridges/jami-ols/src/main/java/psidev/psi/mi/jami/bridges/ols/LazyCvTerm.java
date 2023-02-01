package psidev.psi.mi.jami.bridges.ols;


import psidev.psi.mi.jami.bridges.ols.utils.OlsUtils;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultAnnotation;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.utils.AliasUtils;
import psidev.psi.mi.jami.utils.AnnotationUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import uk.ac.ebi.pride.utilities.ols.web.service.client.OLSClient;
import uk.ac.ebi.pride.utilities.ols.web.service.model.Identifier;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import static psidev.psi.mi.jami.bridges.ols.utils.OlsUtils.META_XREF_SEPARATOR;
import static psidev.psi.mi.jami.bridges.ols.utils.OlsUtils.XREF_DEFINITION_KEY;
import static psidev.psi.mi.jami.model.Annotation.SEARCH_URL;
import static psidev.psi.mi.jami.model.Annotation.VALIDATION_REGEXP;

/**
 * A lazy cvTerm which will only fetch metadata when required.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 14/08/13

 */
public class LazyCvTerm extends DefaultCvTerm {

    protected final Logger log = Logger.getLogger(LazyCvTerm.class.getName());

    private OLSClient olsClient;

    private boolean hasShortName = false;
    private boolean hasSynonyms = false;
    private boolean hasLoadedMetadata = false;
    private boolean hasLoadedXrefs = false;

    private String ontologyName;
    private Xref originalXref;

    /**
     * <p>Constructor for LazyCvTerm.</p>
     *
     * @param olsClient a {@link uk.ac.ebi.pride.utilities.ols.web.service.client.OLSClient} object.
     * @param fullName a {@link java.lang.String} object.
     * @param identityRef a {@link psidev.psi.mi.jami.model.Xref} object.
     * @param ontologyName a {@link java.lang.String} object.
     */
    public LazyCvTerm(OLSClient olsClient, String fullName, Xref identityRef, String ontologyName) {
        super("");
        if (olsClient == null){
            throw new IllegalArgumentException("The lazy cv term needs the Ols query service which cannot be null.");
        }
        this.olsClient = olsClient;

        setFullName(fullName);
        originalXref = identityRef;
        if (identityRef != null){
            getIdentifiers().add(identityRef);
        }

        this.ontologyName = ontologyName;
    }

    /**
     * {@inheritDoc}
     *
     * If the shortName is not yet known, a query will be made to OLS.
     * If no shortName is found, the fullName is used instead.
     */
    @Override
    public String getShortName() {
        if (!hasShortName){
            initialiseMetaData(originalXref);
        }
        return super.getShortName();
    }

    /** {@inheritDoc} */
    public void setShortName(String name) {
        if (name != null){
            hasShortName = true;
        }
        super.setShortName(name);
    }

    /**
     * <p>getXrefs.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Xref> getXrefs() {
        if (!hasLoadedXrefs){
            initialiseOlsXrefs(originalXref);
        }
        return super.getXrefs();
    }

    /**
     * <p>getAnnotations.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Annotation> getAnnotations() {
        if (!hasLoadedXrefs){
            initialiseOlsXrefs(originalXref);
        }
        if (!hasLoadedMetadata){
            initialiseMetaData(originalXref);
        }
        return super.getAnnotations();
    }

    /**
     * <p>getSynonyms.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<Alias> getSynonyms() {
        if (!hasSynonyms) {
            initialiseMetaData(originalXref);
        }

        return super.getSynonyms();
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return (getMIIdentifier() != null ? getMIIdentifier() : (getMODIdentifier() != null ? getMODIdentifier() : (getPARIdentifier() != null ? getPARIdentifier() : "-"))) + " ("+getFullName()+")";
    }

    /**
     * <p>Getter for the field <code>originalXref</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    protected Xref getOriginalXref() {
        return originalXref;
    }

    /**
     * <p>Getter for the field <code>ontologyName</code>.</p>
     *
     * @return a {@link java.lang.String} object.
     */
    protected String getOntologyName() {
        return ontologyName;
    }

    /**
     * <p>Getter for the field <code>olsClient</code>.</p>
     *
     * @return a {@link uk.ac.ebi.pride.utilities.ols.web.service.client.OLSClient} object.
     */
    protected OLSClient getOlsClient() {
        return olsClient;
    }

    // == QUERY METHODS =======================================================================

    /**
     * Retrieve the metadata for an entry.
     * <p>
     * The identifier is used to find the metadata
     * which can be used to find the identifier phrases for short labels and synonyms.
     *
     * @param xref    The identifier that is being used.
     */
    protected void initialiseMetaData(Xref xref){
        Map metaDataMap = null;
        Identifier identifier = new Identifier(xref.getId(), Identifier.IdentifierType.OBO);
        metaDataMap = olsClient.getMetaData(identifier, ontologyName);

        if (metaDataMap != null) {
            if (!hasSynonyms) {
                for (Object key : metaDataMap.keySet()) {
                    String keyName = (String) key;
                    // definition
                    if (OlsUtils.DEFINITION_KEY.equalsIgnoreCase(keyName)) {
                        String description = (String) metaDataMap.get(keyName);
                        Annotation url = processDefinition(description);
                        if (url != null) {
                            description = description.replaceAll(url.getValue(), "");
                        }
                        initialiseDefinition(description);
                    }
                    // comment
                    else if (OlsUtils.DEFINITION_KEY.equalsIgnoreCase(keyName) || keyName.startsWith(OlsUtils.COMMENT_KEY)) {
                        String comment = (String) metaDataMap.get(keyName);
                        initialiseDefinition(comment);
                    } else {
                        Map synonyms = (Map) metaDataMap.get(keyName);
                        for(Object synonym : synonyms.keySet()){
                            processSynonym((String)synonym, (String)synonyms.get(synonym));
                        }
                    }
                }
            }
        }
        hasLoadedMetadata = true;
        hasSynonyms = true;

        if (!hasShortName) {
            super.setShortName(getFullName());
        }
    }

    /**
     * <p>initialiseDefinition.</p>
     *
     * @param description a {@link java.lang.String} object.
     */
    protected void initialiseDefinition(String description) {
        super.getAnnotations().add(new DefaultAnnotation(new DefaultCvTerm("definition"), description));
    }

    /**
     * <p>hasLoadedMetadata.</p>
     *
     * @return a boolean.
     */
    protected boolean hasLoadedMetadata() {
        return hasLoadedMetadata;
    }

    /**
     * <p>hasLoadedXrefs.</p>
     *
     * @return a boolean.
     */
    protected boolean hasLoadedXrefs() {
        return hasLoadedXrefs;
    }

    private void processSynonym(String synonymName, String synonym) {
        if(synonym != null){
            if (synonym.equals(OlsUtils.MI_SHORTLABEL_IDENTIFIER)
                    || synonym.equals(OlsUtils.MOD_SHORTLABEL_IDENTIFIER )){
                if (!hasShortName){
                    super.setShortName(synonymName.toLowerCase());
                    hasShortName = true;
                }
            }
        }
        super.getSynonyms().add(AliasUtils.createAlias(Alias.SYNONYM, Alias.SYNONYM_MI, synonymName));
    }

    /**
     * Process the definition String
     * @param definition
     * @return
     */
    private Annotation processDefinition(String definition) {
        if ( definition != null && definition.contains( OlsUtils.LINE_BREAK ) ) {
            String[] defArray = definition.split( OlsUtils.LINE_BREAK );

            String otherInfoString = null;
            if ( defArray.length == 2 ) {
                otherInfoString = defArray[1];
                return processInfoInDescription(otherInfoString, defArray[0].length());
            } else if ( defArray.length > 2 ) {

                for (int i = 1; i < defArray.length; i++){
                    otherInfoString = defArray[i];
                    Annotation annot = processInfoInDescription(otherInfoString, defArray[0].length());
                    if (annot != null){
                         return annot;
                    }
                }
            }
        }
        return null;
    }

    /**
     * Process the other information in the description
     * @param otherInfoString
     * @return true if an obsolete annotation has been added
     */
    private Annotation processInfoInDescription(String otherInfoString, int defLength) {

        // URL
        if ( otherInfoString.startsWith( OlsUtils.HTTP_DEF ) ) {
            Annotation annot = AnnotationUtils.createAnnotation(Annotation.URL, Annotation.URL_MI, otherInfoString);
            super.getAnnotations().add(annot);
            return annot;
        }
        else if (otherInfoString.contains( OlsUtils.HTTP_DEF )){
            String[] defArray = otherInfoString.split( OlsUtils.HTTP_DEF );

            if ( defArray.length == 2 ) {
                Annotation annot = AnnotationUtils.createAnnotation(Annotation.URL, Annotation.URL_MI, OlsUtils.HTTP_DEF + defArray[1]);
                        super.getAnnotations().add(annot);
                return annot;
            } else if ( defArray.length > 2 ) {
                Annotation annot = AnnotationUtils.createAnnotation(Annotation.URL, Annotation.URL_MI, otherInfoString.substring(defLength));
                        super.getAnnotations().add(annot);
                return annot;
            }

        }
        return null;
    }

    /**
     * This method will initialize the xrefs of this object from the map of xrefs
     */
    private void initialiseOlsXrefs(Xref xref) {
        Map<String, String> metaDataRefs = null;
        Identifier identifier = new Identifier(xref.getId(), Identifier.IdentifierType.OBO);
        metaDataRefs = olsClient.getTermXrefs(identifier, ontologyName);

        if (metaDataRefs != null) {
                for (Object key : metaDataRefs.keySet()) {
                    String keyName = (String) key;

                    // xref definitions
                    if (XREF_DEFINITION_KEY.equalsIgnoreCase(keyName) || keyName.startsWith(XREF_DEFINITION_KEY)) {
                        String value = metaDataRefs.get(keyName);

                        if (value.contains(META_XREF_SEPARATOR)) {
                            String[] xrefDef = value.split(META_XREF_SEPARATOR);
                            String database = null;
                            String accession = null;
                            String pubmedPrimary = null;

                            if (xrefDef.length == 2) {
                                database = xrefDef[0];
                                accession = xrefDef[1].trim();
                            } else if (xrefDef.length > 2) {
                                database = xrefDef[0];
                                accession = value.substring(database.length() + 1).trim();
                            }

                            if (database != null && accession != null) {
                                pubmedPrimary = processXrefDefinition(value, database, accession, pubmedPrimary);
                            }
                        }
                    } else {
                        String value = metaDataRefs.get(keyName);
                        if ((keyName.startsWith(VALIDATION_REGEXP) || keyName.startsWith(SEARCH_URL)) && value != null) {
                            processXref(keyName.replace(":", ""), value);
                        } else {
                            processXref(null, value);
                        }
                    }
                }

            }
        hasLoadedXrefs = true;
    }

    private void processXref(String db, String accession) {
        // xref validation regexp
        if (VALIDATION_REGEXP.equalsIgnoreCase(db)){

            String annotationText = accession.trim();

            if (annotationText.startsWith(OlsUtils.QUOTE)){
                annotationText = annotationText.substring(OlsUtils.QUOTE.length());
            }
            if (annotationText.endsWith(OlsUtils.QUOTE)){
                annotationText = annotationText.substring(0, annotationText.indexOf(OlsUtils.QUOTE));
            }

            Annotation validation = AnnotationUtils.createAnnotation(VALIDATION_REGEXP, Annotation.VALIDATION_REGEXP_MI, annotationText);  // MI xref
            super.getAnnotations().add(validation);
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

            Annotation validation = AnnotationUtils.createAnnotation(SEARCH_URL, Annotation.SEARCH_URL_MI, url);  // MI xref
            super.getAnnotations().add(validation);
        }
        else if (db != null && db.equalsIgnoreCase(SEARCH_URL)){
            String url = accession.trim();

            Annotation validation = AnnotationUtils.createAnnotation(SEARCH_URL, Annotation.SEARCH_URL_MI, url);  // MI xref
            super.getAnnotations().add(validation);
        }
        else if (db != null && db.startsWith(SEARCH_URL)){
            String prefix = db.substring(SEARCH_URL.length());
            String url = prefix + META_XREF_SEPARATOR + accession;

            if (url.startsWith("\"")){
                url = url.substring(1);
            }
            if (url.endsWith("\"")){
                url = url.substring(0, url.length() - 1);
            }

            Annotation validation = AnnotationUtils.createAnnotation(SEARCH_URL, Annotation.SEARCH_URL_MI, url);  // MI xref
            super.getAnnotations().add(validation);
        }
    }

    private String processXrefDefinition(String xref, String database, String accession, String pubmedPrimary) {

        if ( OlsUtils.PMID.equalsIgnoreCase(database) ) {
            if (pubmedPrimary == null){
                pubmedPrimary = xref;

                Xref primaryPubmedRef = XrefUtils.createXrefWithQualifier(Xref.PUBMED, Xref.PUBMED_MI, accession, Xref.PRIMARY, Xref.PRIMARY_MI);
                super.getXrefs().add(primaryPubmedRef);
            }
            else {
                Xref pubmedRef = XrefUtils.createXrefWithQualifier(Xref.PUBMED, Xref.PUBMED_MI, accession, Xref.METHOD_REFERENCE, Xref.METHOD_REFERENCE_MI);
                super.getXrefs().add(pubmedRef);
            }
        }
        else if ( OlsUtils.PMID_APPLICATION.equalsIgnoreCase(database) ) {
            Xref pubmedRef = XrefUtils.createXrefWithQualifier(Xref.PUBMED, Xref.PUBMED_MI, accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            super.getXrefs().add(pubmedRef); // MI not MOD
        } else if ( Xref.GO.equalsIgnoreCase(database) ) {
            Xref goRef = XrefUtils.createXrefWithQualifier(Xref.GO, Xref.GO_MI, database + ":" + accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            super.getXrefs().add(goRef); // MI not MOD
        } else if ( Xref.RESID.equalsIgnoreCase(database) ) {
            Xref resXref = XrefUtils.createXrefWithQualifier(Xref.RESID, Xref.RESID_MI, accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            super.getXrefs().add(resXref);
        } else if ( Xref.SO.equalsIgnoreCase(database) ) {
            Xref soRef = XrefUtils.createXrefWithQualifier(Xref.SO, Xref.SO_MI, database + ":" + accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            super.getXrefs().add(soRef);  // MI not MOD
        }
        else if ( Xref.PUBMED.equalsIgnoreCase(database) ) {
            if (pubmedPrimary == null){
                pubmedPrimary = xref;

                Xref primaryPubmedRef = XrefUtils.createXrefWithQualifier(Xref.PUBMED, Xref.PUBMED_MI, accession, Xref.PRIMARY, Xref.PRIMARY_MI);
                super.getXrefs().add(primaryPubmedRef);
            }
            else {
                Xref pubmedRef = XrefUtils.createXrefWithQualifier(Xref.PUBMED, Xref.PUBMED_MI, accession, Xref.METHOD_REFERENCE, Xref.METHOD_REFERENCE_MI);
                super.getXrefs().add(pubmedRef);
            }
        }
        else if ( Xref.CHEBI.equalsIgnoreCase(database) ) {
            Xref chebiRef = XrefUtils.createXrefWithQualifier(Xref.CHEBI, Xref.CHEBI_MI, accession, Xref.SEE_ALSO, Xref.SEE_ALSO_MI);
            super.getXrefs().add(chebiRef);  // MOD xref
        } else if ( Annotation.URL.equalsIgnoreCase(database) ) {
            super.getAnnotations().add(AnnotationUtils.createAnnotation(Annotation.URL, Annotation.URL_MI, accession));
        }

        return pubmedPrimary;
    }
}
