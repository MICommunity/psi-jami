package psidev.psi.mi.jami.xml.model.extension.factory;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.factory.DefaultInteractorFactory;
import psidev.psi.mi.jami.factory.InteractorCategory;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.clone.InteractorCloner;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.model.extension.AbstractBaseXmlInteractor;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractor;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * Interactor factory for XML interactors
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/07/13</pre>
 */
public class XmlInteractorFactory {

    private XmlInteractorFactory delegate;

    private Map<String, InteractorCategory> deterministicInteractorNameMap;
    private Map<String, InteractorCategory> deterministicInteractorIdMap;

    /**
     * <p>Constructor for XmlInteractorFactory.</p>
     */
    public XmlInteractorFactory() {
        initialiseDeterministicInteractorMaps();
    }

    /**
     * <p>Constructor for XmlInteractorFactory.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.xml.model.extension.factory.XmlInteractorFactory} object.
     */
    public XmlInteractorFactory(XmlInteractorFactory delegate) {
        initialiseDeterministicInteractorMaps();
        this.delegate = delegate;
    }

    /** {@inheritDoc} */
    public Protein createProtein(String name, CvTerm type, PsiXmlVersion version) {
        return delegate != null ? delegate.createProtein(name, type, version) : createXmlProtein(name, type, version);
    }

    /** {@inheritDoc} */
    public NucleicAcid createNucleicAcid(String name, CvTerm type, PsiXmlVersion version) {
        return delegate != null ? delegate.createNucleicAcid(name, type, version) : createXmlNucleicAcid(name, type, version);
    }

    /** {@inheritDoc} */
    public Gene createGene(String name, PsiXmlVersion version) {
        return delegate != null ? delegate.createGene(name, version) : createXmlGene(name, version);
    }

    /** {@inheritDoc} */
    public Complex createComplex(String name, CvTerm type, PsiXmlVersion version) {
        return delegate != null ? delegate.createComplex(name, type, version) : createXmlComplex(name, type, version);
    }

    /** {@inheritDoc} */
    public BioactiveEntity createBioactiveEntity(String name, CvTerm type, PsiXmlVersion version) {
        return delegate != null ? delegate.createBioactiveEntity(name, type, version) : createXmlBioactiveEntity(name, type, version);
    }

    /** {@inheritDoc} */
    public Polymer createPolymer(String name, CvTerm type, PsiXmlVersion version) {
        return delegate != null ? delegate.createPolymer(name, type, version) : createXmlPolymer(name, type, version);
    }

    /** {@inheritDoc} */
    public Interactor createInteractor(String name, CvTerm type, PsiXmlVersion version) {
        return delegate != null ? delegate.createInteractor(name, type, version) : createXmlInteractor(name, type, version);
    }

    /** {@inheritDoc} */
    public InteractorPool createInteractorSet(String name, CvTerm type, PsiXmlVersion version) {
        return delegate != null ? delegate.createInteractorSet(name, type, version) : createXmlInteractorSet(name, type, version);
    }

    /**
     * {@inheritDoc}
     *
     * Return the proper instance of the interactor if the type is recognized and not null. It returns null otherwise.
     */
    public Interactor createInteractorFromInteractorType(CvTerm type, String name, PsiXmlVersion version) {
        if (type == null){
            return null;
        }

        String typeName = type.getShortName().toLowerCase().trim();
        String typeMI = type.getMIIdentifier();

        if (typeMI != null && this.deterministicInteractorIdMap.containsKey(typeMI)){
            InteractorCategory recognizedType = this.deterministicInteractorIdMap.get(typeMI);

            return createInteractorFromRecognizedCategory(recognizedType, name, type, version);
        }
        else if (typeMI == null && this.deterministicInteractorNameMap.containsKey(typeName)){
            InteractorCategory recognizedType = this.deterministicInteractorNameMap.get(typeName);

            return createInteractorFromRecognizedCategory(recognizedType, name, type, version);
        }
        // we have a valid type that is not unknown or null
        else {
            return createInteractor(name, type, version);
        }
    }

    /**
     * {@inheritDoc}
     *
     * Return the proper instance of the interactor if the database is recognized. It returns null otherwise.
     */
    public Interactor createInteractorFromDatabase(CvTerm database, String name, PsiXmlVersion version) {
        if (database == null){
            return null;
        }
        String databaseName = database.getShortName().toLowerCase().trim();
        String databaseMI = database.getMIIdentifier();

        if (databaseMI != null && this.deterministicInteractorIdMap.containsKey(databaseMI)){
            InteractorCategory recognizedType = this.deterministicInteractorIdMap.get(databaseMI);

            return createInteractorFromRecognizedCategory(recognizedType, name, null, version);
        }
        else if (databaseMI == null && this.deterministicInteractorNameMap.containsKey(databaseName)){
            InteractorCategory recognizedType = this.deterministicInteractorNameMap.get(databaseName);

            return createInteractorFromRecognizedCategory(recognizedType, name, null, version);
        }

        return null;
    }

    /**
     * {@inheritDoc}
     *
     * Return the proper instance of the interactor if the database is recognized (the interactor will be returned on the first database which is recognized). It returns null otherwise.
     */
    public Interactor createInteractorFromIdentityXrefs(Collection<? extends Xref> xrefs, String name, PsiXmlVersion version) {

        Interactor interactor = null;
        Iterator<? extends Xref> xrefsIterator = xrefs.iterator();
        while (interactor == null && xrefsIterator.hasNext()){

            interactor = createInteractorFromDatabase(xrefsIterator.next().getDatabase(), name, version);
        }

        return interactor;
    }

    /**
     * <p>createInteractorFromXmlInteractorInstance.</p>
     *
     * @param source a {@link psidev.psi.mi.jami.xml.model.extension.AbstractBaseXmlInteractor} object.
     * @return a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public Interactor createInteractorFromXmlInteractorInstance(AbstractBaseXmlInteractor source, PsiXmlVersion version) {
        Interactor reloadedInteractorDependingOnType = createInteractorFromInteractorType(source.getInteractorType(), source.getShortName(), version);
        if (reloadedInteractorDependingOnType == null){
            reloadedInteractorDependingOnType = createInteractorFromIdentityXrefs(source.getIdentifiers(), source.getShortName(), version);
        }

        if (reloadedInteractorDependingOnType != null){

            // interactor pool
            if (reloadedInteractorDependingOnType instanceof InteractorPool){
                Collection<Xref> components = XrefUtils.collectAllXrefsHavingQualifier(source.getXrefs(), Xref.INTERACTOR_SET_QUALIFIER_MI,
                        Xref.INTERACTOR_SET_QUALIFIER);

                if (!components.isEmpty()){
                    // remove component xrefs from source
                    source.getXrefs().removeAll(components);
                    // create interactor from component
                    processInteractorPool(components, (InteractorPool)reloadedInteractorDependingOnType, source.getSequence(), source.getOrganism(), version);
                }
            }
            // polymer
            else if (reloadedInteractorDependingOnType instanceof Polymer){
                ((Polymer)reloadedInteractorDependingOnType).setSequence(source.getSequence());
            }

            InteractorCloner.copyAndOverrideBasicInteractorProperties(source, reloadedInteractorDependingOnType);
            if (reloadedInteractorDependingOnType instanceof ExtendedPsiXmlInteractor){
                ((FileSourceContext)reloadedInteractorDependingOnType).setSourceLocator(source.getSourceLocator());
                ((ExtendedPsiXmlInteractor)reloadedInteractorDependingOnType).setId(source.getId());
            }

            // we don't have any identifiers, we look back at the xrefs
            if (reloadedInteractorDependingOnType.getIdentifiers().isEmpty() && !reloadedInteractorDependingOnType.getXrefs().isEmpty()){
                Iterator<Xref> refIterator = reloadedInteractorDependingOnType.getXrefs().iterator();
                while (refIterator.hasNext()){
                    Xref ref = refIterator.next();
                    if (CvTermUtils.isCvTerm(ref.getDatabase(), Xref.UNIPROTKB_MI, Xref.UNIPROTKB)
                            || CvTermUtils.isCvTerm(ref.getDatabase(), Xref.REFSEQ_MI, Xref.REFSEQ)
                            || CvTermUtils.isCvTerm(ref.getDatabase(), Xref.ENSEMBL_MI, Xref.ENSEMBL)
                            || CvTermUtils.isCvTerm(ref.getDatabase(), Xref.CHEBI_MI, Xref.CHEBI)
                            || CvTermUtils.isCvTerm(ref.getDatabase(), Xref.ENSEMBL_GENOMES_MI, Xref.ENSEMBL_GENOMES)
                            || CvTermUtils.isCvTerm(ref.getDatabase(), Xref.ENTREZ_GENE_MI, Xref.ENTREZ_GENE)
                            || CvTermUtils.isCvTerm(ref.getDatabase(), Xref.DDBJ_EMBL_GENBANK_MI, Xref.DDBJ_EMBL_GENBANK)
                            || CvTermUtils.isCvTerm(ref.getDatabase(), Xref.UNIPROTKB_SWISSPROT_MI, Xref.UNIPROTKB_SWISSPROT)
                            || CvTermUtils.isCvTerm(ref.getDatabase(), Xref.UNIPROTKB_TREMBL_MI, Xref.UNIPROTKB_TREMBL)){
                        refIterator.remove();
                        reloadedInteractorDependingOnType.getIdentifiers().add(ref);
                    }
                }
            }

            return reloadedInteractorDependingOnType;
        }
        else{
            return source;
        }
    }

    private void processInteractorPool(Collection<Xref> xref, InteractorPool pool, String sequence, Organism organism, PsiXmlVersion version) {
        for (Xref ref : xref){
            Interactor subInteractor = createInteractorFromDatabase(ref.getDatabase(), ref.getId().toLowerCase(), version);
            if (subInteractor != null){
                subInteractor.getIdentifiers().add(createXmlXref(ref.getDatabase(), ref.getId(), ref.getVersion(), CvTermUtils.createIdentityQualifier(), version));
                ((FileSourceContext)subInteractor).setSourceLocator(((FileSourceContext) ref).getSourceLocator());
            }
            // create a default interactor
            else{
                subInteractor = createInteractor(ref.getId().toLowerCase(), CvTermUtils.createUnknownInteractorType(), version);
                subInteractor.getIdentifiers().add(createXmlXref(ref.getDatabase(), ref.getId(), ref.getVersion(), CvTermUtils.createIdentityQualifier(), version));
                ((FileSourceContext)subInteractor).setSourceLocator(((FileSourceContext)ref).getSourceLocator());
            }

            // add sequence
            if (subInteractor instanceof Polymer){
                ((Polymer) subInteractor).setSequence(sequence);
            }

            // add organism
            subInteractor.setOrganism(organism);

            // add the component to the interactor pool
            pool.add(subInteractor);
        }
    }

    private Xref createXmlXref(CvTerm database, String id, String xRefVersion, CvTerm qualifier, PsiXmlVersion version) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlXref(database, id, xRefVersion, qualifier);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlXref(database, id, xRefVersion, qualifier);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlXref(database, id, xRefVersion, qualifier);
        }
    }

    private Protein createXmlProtein(String name, CvTerm type, PsiXmlVersion version) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlProtein(name, type);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlProtein(name, type);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlProtein(name, type);
        }
    }

    private NucleicAcid createXmlNucleicAcid(String name, CvTerm type, PsiXmlVersion version) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlNucleicAcid(name, type);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlNucleicAcid(name, type);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlNucleicAcid(name, type);
        }
    }

    private Gene createXmlGene(String name, PsiXmlVersion version) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlGene(name);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlGene(name);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlGene(name);
        }
    }

    private Complex createXmlComplex(String name, CvTerm type, PsiXmlVersion version) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.DefaultXmlComplex(name, type);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlComplex(name, type);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.DefaultXmlComplex(name, type);
        }
    }

    private BioactiveEntity createXmlBioactiveEntity(String name, CvTerm type, PsiXmlVersion version) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlBioactiveEntity(name, type);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlBioactiveEntity(name, type);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlBioactiveEntity(name, type);
        }
    }

    private Polymer createXmlPolymer(String name, CvTerm type, PsiXmlVersion version) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlPolymer(name, type);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlPolymer(name, type);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlPolymer(name, type);
        }
    }

    private Interactor createXmlInteractor(String name, CvTerm type, PsiXmlVersion version) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.DefaultXmlInteractor(name, type);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlInteractor(name, type);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.DefaultXmlInteractor(name, type);
        }
    }

    private InteractorPool createXmlInteractorSet(String name, CvTerm type, PsiXmlVersion version) {
        switch (version) {
            case v3_0_0:
                return new psidev.psi.mi.jami.xml.model.extension.xml300.XmlInteractorPool(name, type);
            case v2_5_3:
                return new psidev.psi.mi.jami.xml.model.extension.xml253.XmlInteractorPool(name, type);
            case v2_5_4:
            default:
                return new psidev.psi.mi.jami.xml.model.extension.xml254.XmlInteractorPool(name, type);
        }
    }

    /**
     * Loads some properties to recognize interactor type from different MI terms
     */
    protected void initialiseDeterministicInteractorMaps(){
        deterministicInteractorNameMap = new HashMap<String, InteractorCategory>();
        deterministicInteractorIdMap = new HashMap<String, InteractorCategory>();
        Properties prop = new Properties();

        try {
            //load a properties file
            prop.load(DefaultInteractorFactory.class.getResourceAsStream("/interactorType.properties"));
            loadProperties(prop);

            // load the second property file
            prop.clear();
            prop.load(DefaultInteractorFactory.class.getResourceAsStream("/interactorDatabase.properties"));
            loadProperties(prop);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * Loads the properties in the deterministicInteractorMap
     *
     * @param prop : properties
     */
    protected void loadProperties(Properties prop) {
        for (Map.Entry<Object, Object> entry : prop.entrySet()){
            String[] values = extractCvTermFromKey((String)entry.getKey());
            if (values.length != 2){
                this.deterministicInteractorNameMap.put(values[0], InteractorCategory.valueOf((String)entry.getValue()));
            }
            else {
                this.deterministicInteractorNameMap.put(values[1].replaceAll("\\)", ""), InteractorCategory.valueOf((String)entry.getValue()));
                this.deterministicInteractorIdMap.put(values[0], InteractorCategory.valueOf((String)entry.getValue()));
            }
        }
    }

    /**
     * Reads the cv term from the properties file
     *
     * @param key : the property ky
     * @return cv term values extracted from the key (name - MI identifier)
     */
    protected String[] extractCvTermFromKey(String key){
        if (key.contains("(") && key.contains(")")){
            String[] values = key.split("\\(");
            return values;
        }
        else{
            return new String[]{key};
        }
    }

    /**
     * Creates an interactor from a given category (should be the canonical name of an Interactor interface)
     *
     * @param category : the category of interactor
     * @param name : name
     * @param type : interactor type
     * @return the created interactor
     */
    protected Interactor createInteractorFromRecognizedCategory(InteractorCategory category, String name, CvTerm type, PsiXmlVersion version) {

        switch (category){
            case protein:
                return createProtein(name, type, version);
            case gene:
                return createGene(name, version);
            case nucleic_acid:
                return createNucleicAcid(name, type, version);
            case bioactive_entity:
                return createBioactiveEntity(name, type, version);
            case polymer:
                return createPolymer(name, type, version);
            case complex:
                return createComplex(name, type, version);
            case interactor_set:
                return createInteractorSet(name, type, version);
            default:
                return createInteractor(name, type, version);
        }
    }
}
