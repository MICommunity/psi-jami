package psidev.psi.mi.jami.model.impl;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Entity;
import psidev.psi.mi.jami.model.Feature;

/**
 * Default implementation for feature
 *
 * Notes: The equals and hashcode methods have NOT been overridden because the Feature object is a complex object.
 * To compare Feature objects, you can use some comparators provided by default:
 * - DefaultFeatureBaseComparator
 * - UnambiguousFeatureBaseComparator
 * - DefaultFeatureComparator
 * - UnambiguousFeatureComparator
 * - AbstractFeatureBaseComparator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>01/02/13</pre>
 */
public class DefaultFeature extends AbstractFeature<Entity, Feature>{

    /**
     * <p>Constructor for DefaultFeature.</p>
     */
    public DefaultFeature(){
        super();
    }

    /**
     * <p>Constructor for DefaultFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public DefaultFeature(String shortName, String fullName){
        super(shortName, fullName);
    }

    /**
     * <p>Constructor for DefaultFeature.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultFeature(CvTerm type){
        super(type);
    }

    /**
     * <p>Constructor for DefaultFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public DefaultFeature(String shortName, String fullName, CvTerm type){
        super(shortName, fullName, type);
    }

    /**
     * <p>Constructor for DefaultFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public DefaultFeature(String shortName, String fullName, String interpro){
        super(shortName, fullName, interpro);
    }

    /**
     * <p>Constructor for DefaultFeature.</p>
     *
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public DefaultFeature(CvTerm type, String interpro){
        super(type, interpro);
    }

    /**
     * <p>Constructor for DefaultFeature.</p>
     *
     * @param shortName a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param interpro a {@link java.lang.String} object.
     */
    public DefaultFeature(String shortName, String fullName, CvTerm type, String interpro){
        super(shortName, fullName, type, interpro);
    }
}
