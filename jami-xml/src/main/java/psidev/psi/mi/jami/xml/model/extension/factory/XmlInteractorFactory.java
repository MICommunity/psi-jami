package psidev.psi.mi.jami.xml.model.extension.factory;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.factory.DefaultInteractorFactory;
import psidev.psi.mi.jami.factory.InteractorFactory;
import psidev.psi.mi.jami.model.*;
import psidev.psi.mi.jami.utils.CvTermUtils;
import psidev.psi.mi.jami.utils.XrefUtils;
import psidev.psi.mi.jami.utils.clone.InteractorCloner;
import psidev.psi.mi.jami.xml.model.extension.AbstractBaseXmlInteractor;
import psidev.psi.mi.jami.xml.model.extension.xml253.AbstractXmlInteractor;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlComplex;
import psidev.psi.mi.jami.xml.model.extension.xml253.DefaultXmlInteractor;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractor;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlBioactiveEntity;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlGene;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlInteractorPool;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlNucleicAcid;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlPolymer;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlProtein;
import psidev.psi.mi.jami.xml.model.extension.xml253.XmlXref;

import java.util.Collection;
import java.util.Iterator;

/**
 * Interactor factory for XML interactors
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>24/07/13</pre>
 */
public class XmlInteractorFactory extends DefaultInteractorFactory{

    private InteractorFactory delegate;

    /**
     * <p>Constructor for XmlInteractorFactory.</p>
     */
    public XmlInteractorFactory() {
        super();
    }

    /**
     * <p>Constructor for XmlInteractorFactory.</p>
     *
     * @param delegate a {@link psidev.psi.mi.jami.factory.InteractorFactory} object.
     */
    public XmlInteractorFactory(InteractorFactory delegate) {
        super();
        this.delegate = delegate;
    }

    /** {@inheritDoc} */
    @Override
    public Protein createProtein(String name, CvTerm type) {
        return delegate != null ? delegate.createProtein(name, type) : new XmlProtein(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public NucleicAcid createNucleicAcid(String name, CvTerm type) {
        return delegate != null ? delegate.createNucleicAcid(name, type) : new XmlNucleicAcid(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public Gene createGene(String name) {
        return delegate != null ? delegate.createGene(name) : new XmlGene(name);
    }

    /** {@inheritDoc} */
    @Override
    public Complex createComplex(String name, CvTerm type) {
        return delegate != null ? delegate.createComplex(name, type) : new DefaultXmlComplex(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public BioactiveEntity createBioactiveEntity(String name, CvTerm type) {
        return delegate != null ? delegate.createBioactiveEntity(name, type) : new XmlBioactiveEntity(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public Polymer createPolymer(String name, CvTerm type) {
        return delegate != null ? delegate.createPolymer(name, type) : new XmlPolymer(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public Interactor createInteractor(String name, CvTerm type) {
        return delegate != null ? delegate.createInteractor(name, type) : new DefaultXmlInteractor(name, type);
    }

    /** {@inheritDoc} */
    @Override
    public InteractorPool createInteractorSet(String name, CvTerm type) {
        return delegate != null ? delegate.createInteractorSet(name, type) : new XmlInteractorPool(name, type);
    }

    /**
     * <p>createInteractorFromXmlInteractorInstance.</p>
     *
     * @param source a {@link AbstractXmlInteractor} object.
     * @return a {@link psidev.psi.mi.jami.model.Interactor} object.
     */
    public Interactor createInteractorFromXmlInteractorInstance(AbstractBaseXmlInteractor source){
        Interactor reloadedInteractorDependingOnType = createInteractorFromInteractorType(source.getInteractorType(), source.getShortName());
        if (reloadedInteractorDependingOnType == null){
            reloadedInteractorDependingOnType = createInteractorFromIdentityXrefs(source.getIdentifiers(), source.getShortName());
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
                    processInteractorPool(components, (InteractorPool)reloadedInteractorDependingOnType, source.getSequence(), source.getOrganism());
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

    private void processInteractorPool(Collection<Xref> xref, InteractorPool pool, String sequence, Organism organism) {
        for (Xref ref : xref){
            Interactor subInteractor = createInteractorFromDatabase(ref.getDatabase(), ref.getId().toLowerCase());
            if (subInteractor != null){
                subInteractor.getIdentifiers().add(new XmlXref(ref.getDatabase(), ref.getId(), ref.getVersion(), CvTermUtils.createIdentityQualifier()));
                ((FileSourceContext)subInteractor).setSourceLocator(((XmlXref) ref).getSourceLocator());
            }
            // create a default interactor
            else{
                subInteractor = createInteractor(ref.getId().toLowerCase(), CvTermUtils.createUnknownInteractorType());
                subInteractor.getIdentifiers().add(new XmlXref(ref.getDatabase(), ref.getId(), ref.getVersion(), CvTermUtils.createIdentityQualifier()));
                ((FileSourceContext)subInteractor).setSourceLocator(((XmlXref)ref).getSourceLocator());
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
}
