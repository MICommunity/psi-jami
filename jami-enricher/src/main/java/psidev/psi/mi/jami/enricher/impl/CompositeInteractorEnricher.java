package psidev.psi.mi.jami.enricher.impl;

import psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher;
import psidev.psi.mi.jami.enricher.*;
import psidev.psi.mi.jami.enricher.exception.EnricherException;
import psidev.psi.mi.jami.enricher.listener.InteractorEnricherListener;
import psidev.psi.mi.jami.model.*;

import java.util.Collection;

/**
 * General enricher for interactors that can use sub enrichers for enriching specific interactors
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>11/02/14</pre>
 */
public class CompositeInteractorEnricher implements InteractorEnricher<Interactor> {

    private AbstractInteractorEnricher<Interactor> interactorBaseEnricher;
    private InteractorEnricher<Polymer> polymerBaseEnricher;
    private InteractorPoolEnricher interactorPoolEnricher;
    private ProteinEnricher proteinEnricher;
    private InteractorEnricher<BioactiveEntity> bioactiveEntityEnricher;
    private InteractorEnricher<Gene> geneEnricher;
    private InteractorEnricher<NucleicAcid> nucleicAcidEnricher;
    private ComplexEnricher complexEnricher;

    /**
     * <p>Constructor for CompositeInteractorEnricher.</p>
     *
     * @param interactorBaseEnricher a {@link psidev.psi.mi.jami.enricher.impl.AbstractInteractorEnricher} object.
     */
    public CompositeInteractorEnricher(AbstractInteractorEnricher<Interactor> interactorBaseEnricher) {
        super();
        if (interactorBaseEnricher == null) {
            throw new IllegalArgumentException("At least the interactor base enricher is needed and cannot be null");
        }
        this.interactorBaseEnricher = interactorBaseEnricher;
    }

    /**
     * <p>Getter for the field <code>polymerBaseEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.InteractorEnricher} object.
     */
    public InteractorEnricher<Polymer> getPolymerBaseEnricher() {
        return polymerBaseEnricher;
    }

    /**
     * <p>Setter for the field <code>polymerBaseEnricher</code>.</p>
     *
     * @param polymerBaseEnricher a {@link psidev.psi.mi.jami.enricher.InteractorEnricher} object.
     */
    public void setPolymerBaseEnricher(InteractorEnricher<Polymer> polymerBaseEnricher) {
        this.polymerBaseEnricher = polymerBaseEnricher;
    }

    /**
     * <p>Getter for the field <code>interactorPoolEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.InteractorPoolEnricher} object.
     */
    public InteractorPoolEnricher getInteractorPoolEnricher() {
        return interactorPoolEnricher;
    }

    /**
     * <p>Setter for the field <code>interactorPoolEnricher</code>.</p>
     *
     * @param interactorPoolEnricher a {@link psidev.psi.mi.jami.enricher.InteractorPoolEnricher} object.
     */
    public void setInteractorPoolEnricher(InteractorPoolEnricher interactorPoolEnricher) {
        this.interactorPoolEnricher = interactorPoolEnricher;
    }

    /**
     * <p>Getter for the field <code>proteinEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.ProteinEnricher} object.
     */
    public ProteinEnricher getProteinEnricher() {
        return proteinEnricher;
    }

    /**
     * <p>Setter for the field <code>proteinEnricher</code>.</p>
     *
     * @param proteinEnricher a {@link psidev.psi.mi.jami.enricher.ProteinEnricher} object.
     */
    public void setProteinEnricher(ProteinEnricher proteinEnricher) {
        this.proteinEnricher = proteinEnricher;
    }

    /**
     * <p>Getter for the field <code>bioactiveEntityEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.InteractorEnricher} object.
     */
    public InteractorEnricher<BioactiveEntity> getBioactiveEntityEnricher() {
        return bioactiveEntityEnricher;
    }

    /**
     * <p>Setter for the field <code>bioactiveEntityEnricher</code>.</p>
     *
     * @param bioactiveEntityEnricher a {@link psidev.psi.mi.jami.enricher.InteractorEnricher} object.
     */
    public void setBioactiveEntityEnricher(InteractorEnricher<BioactiveEntity> bioactiveEntityEnricher) {
        this.bioactiveEntityEnricher = bioactiveEntityEnricher;
    }

    /**
     * <p>Getter for the field <code>geneEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.InteractorEnricher} object.
     */
    public InteractorEnricher<Gene> getGeneEnricher() {
        return geneEnricher;
    }

    /**
     * <p>Setter for the field <code>geneEnricher</code>.</p>
     *
     * @param geneEnricher a {@link psidev.psi.mi.jami.enricher.InteractorEnricher} object.
     */
    public void setGeneEnricher(InteractorEnricher<Gene> geneEnricher) {
        this.geneEnricher = geneEnricher;
    }

    public InteractorEnricher<NucleicAcid> getNucleicAcidEnricher() {
        return nucleicAcidEnricher;
    }

    public void setNucleicAcidEnricher(InteractorEnricher<NucleicAcid> nucleicAcidEnricher) {
        this.nucleicAcidEnricher = nucleicAcidEnricher;
    }

    /**
     * <p>Getter for the field <code>complexEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.ComplexEnricher} object.
     */
    public ComplexEnricher getComplexEnricher() {
        return complexEnricher;
    }

    /**
     * <p>Setter for the field <code>complexEnricher</code>.</p>
     *
     * @param complexEnricher a {@link psidev.psi.mi.jami.enricher.ComplexEnricher} object.
     */
    public void setComplexEnricher(ComplexEnricher complexEnricher) {
        this.complexEnricher = complexEnricher;
    }

    /**
     * <p>Getter for the field <code>interactorBaseEnricher</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.impl.AbstractInteractorEnricher} object.
     */
    public AbstractInteractorEnricher<Interactor> getInteractorBaseEnricher() {
        return interactorBaseEnricher;
    }

    /**
     * <p>enrich.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(Interactor object) throws EnricherException {
        if (object == null)
            throw new IllegalArgumentException("Cannot enrich a null interactor.");
        if (object instanceof Polymer) {
            if (object instanceof Protein && this.proteinEnricher != null) {
                this.proteinEnricher.enrich((Protein) object);
            } else if (object instanceof NucleicAcid && this.nucleicAcidEnricher != null) {
                this.nucleicAcidEnricher.enrich((NucleicAcid) object);
            } else if (this.polymerBaseEnricher != null) {
                this.polymerBaseEnricher.enrich((Polymer) object);
            } else {
                this.interactorBaseEnricher.enrich(object);
            }
        } else if (object instanceof Gene && this.geneEnricher != null) {
            this.geneEnricher.enrich((Gene) object);
        } else if (object instanceof BioactiveEntity && this.bioactiveEntityEnricher != null) {
            this.bioactiveEntityEnricher.enrich((BioactiveEntity) object);
        } else if (object instanceof Complex && this.complexEnricher != null) {
            this.complexEnricher.enrich((Complex) object);
        } else if (object instanceof InteractorPool && this.interactorPoolEnricher != null) {
            this.interactorPoolEnricher.enrich((InteractorPool) object);
        } else {
            this.interactorBaseEnricher.enrich(object);
        }
    }

    /**
     * <p>enrich.</p>
     *
     * @param objects a {@link java.util.Collection} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(Collection<Interactor> objects) throws EnricherException {
        if (objects == null)
            throw new IllegalArgumentException("Cannot enrich a null collection of interactors.");

        for (Interactor object : objects) {
            enrich(object);
        }
    }

    /**
     * <p>enrich.</p>
     *
     * @param object       a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @param objectSource a {@link psidev.psi.mi.jami.model.Interactor} object.
     * @throws psidev.psi.mi.jami.enricher.exception.EnricherException if any.
     */
    public void enrich(Interactor object, Interactor objectSource) throws EnricherException {
        if (object instanceof Polymer && objectSource instanceof Polymer) {
            if (object instanceof Protein && objectSource instanceof Protein && this.proteinEnricher != null) {
                this.proteinEnricher.enrich((Protein) object, (Protein) objectSource);
            } else if (object instanceof NucleicAcid && objectSource instanceof NucleicAcid && this.nucleicAcidEnricher != null) {
                this.nucleicAcidEnricher.enrich((NucleicAcid) object, (NucleicAcid) objectSource);
            } else if (this.polymerBaseEnricher != null) {
                this.polymerBaseEnricher.enrich((Polymer) object, (Polymer) objectSource);
            } else {
                this.interactorBaseEnricher.enrich(object, objectSource);
            }
        } else if (object instanceof Gene && objectSource instanceof Gene && this.geneEnricher != null) {
            this.geneEnricher.enrich((Gene) object, (Gene) objectSource);
        } else if (object instanceof BioactiveEntity && objectSource instanceof BioactiveEntity && this.bioactiveEntityEnricher != null) {
            this.bioactiveEntityEnricher.enrich((BioactiveEntity) object, (BioactiveEntity) objectSource);
        } else if (object instanceof Complex && objectSource instanceof Complex && this.complexEnricher != null) {
            this.complexEnricher.enrich((Complex) object, (Complex) objectSource);
        } else if (object instanceof InteractorPool && objectSource instanceof InteractorPool && this.interactorPoolEnricher != null) {
            this.interactorPoolEnricher.enrich((InteractorPool) object, (InteractorPool) objectSource);
        } else {
            this.interactorBaseEnricher.enrich(object, objectSource);
        }
    }

    /**
     * <p>getInteractorFetcher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.bridges.fetcher.InteractorFetcher} object.
     */
    public InteractorFetcher<Interactor> getInteractorFetcher() {
        return this.interactorBaseEnricher.getInteractorFetcher();
    }

    /**
     * <p>getListener.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.listener.InteractorEnricherListener} object.
     */
    public InteractorEnricherListener<Interactor> getListener() {
        return this.interactorBaseEnricher.getListener();
    }

    /**
     * <p>getCvTermEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.CvTermEnricher} object.
     */
    public CvTermEnricher<CvTerm> getCvTermEnricher() {
        return this.interactorBaseEnricher.getCvTermEnricher();
    }

    /**
     * <p>getOrganismEnricher.</p>
     *
     * @return a {@link psidev.psi.mi.jami.enricher.OrganismEnricher} object.
     */
    public OrganismEnricher getOrganismEnricher() {
        return this.interactorBaseEnricher.getOrganismEnricher();
    }

    /**
     * {@inheritDoc}
     */
    public void setListener(InteractorEnricherListener<Interactor> listener) {
        this.interactorBaseEnricher.setListener(listener);
    }

    /**
     * {@inheritDoc}
     */
    public void setCvTermEnricher(CvTermEnricher<CvTerm> enricher) {
        this.interactorBaseEnricher.setCvTermEnricher(enricher);
        if (getProteinEnricher() != null) {
            getProteinEnricher().setCvTermEnricher(enricher);
        }
        if (getPolymerBaseEnricher() != null) {
            getPolymerBaseEnricher().setCvTermEnricher(enricher);
        }
        if (getNucleicAcidEnricher() != null) {
            getNucleicAcidEnricher().setCvTermEnricher(enricher);
        }
        if (getGeneEnricher() != null) {
            getGeneEnricher().setCvTermEnricher(enricher);
        }
        if (getBioactiveEntityEnricher() != null) {
            getBioactiveEntityEnricher().setCvTermEnricher(enricher);
        }
        if (getComplexEnricher() != null) {
            getComplexEnricher().setCvTermEnricher(enricher);
        }
        if (getInteractorPoolEnricher() != null) {
            getInteractorPoolEnricher().setCvTermEnricher(enricher);
        }
        if (getNucleicAcidEnricher() != null) {
            getNucleicAcidEnricher().setCvTermEnricher(enricher);
        }
    }

    /**
     * {@inheritDoc}
     */
    public void setOrganismEnricher(OrganismEnricher enricher) {
        this.interactorBaseEnricher.setOrganismEnricher(enricher);
        if (getProteinEnricher() != null) {
            getProteinEnricher().setOrganismEnricher(enricher);
        }
        if (getPolymerBaseEnricher() != null) {
            getPolymerBaseEnricher().setOrganismEnricher(enricher);
        }
        if (getNucleicAcidEnricher() != null) {
            getNucleicAcidEnricher().setOrganismEnricher(enricher);
        }
        if (getGeneEnricher() != null) {
            getGeneEnricher().setOrganismEnricher(enricher);
        }
        if (getBioactiveEntityEnricher() != null) {
            getBioactiveEntityEnricher().setOrganismEnricher(enricher);
        }
        if (getComplexEnricher() != null) {
            getComplexEnricher().setOrganismEnricher(enricher);
        }
        if (getInteractorPoolEnricher() != null) {
            getInteractorPoolEnricher().setOrganismEnricher(enricher);
        }
        if (getNucleicAcidEnricher() != null) {
            getNucleicAcidEnricher().setOrganismEnricher(enricher);
        }
    }
}
