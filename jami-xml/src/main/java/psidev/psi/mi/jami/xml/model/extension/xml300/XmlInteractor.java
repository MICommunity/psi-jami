package psidev.psi.mi.jami.xml.model.extension.xml300;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.xml.model.extension.AbstractXmlInteractor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Xml implementation of Interactor
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlRootElement(name = "interactor", namespace = "http://psi.hupo.org/mi/mif300")
public class XmlInteractor extends AbstractXmlInteractor{

    /**
     * <p>Constructor for XmlInteractor.</p>
     */
    public XmlInteractor() {
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlInteractor(String name, CvTerm type) {
        super(name, type);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlInteractor(String name, String fullName, CvTerm type) {
        super(name, fullName, type);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlInteractor(String name, CvTerm type, Organism organism) {
        super(name, type, organism);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlInteractor(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type, organism);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlInteractor(String name, CvTerm type, Xref uniqueId) {
        super(name, type, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlInteractor(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlInteractor(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param type a {@link psidev.psi.mi.jami.model.CvTerm} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlInteractor(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     */
    public XmlInteractor(String name) {
        super(name);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     */
    public XmlInteractor(String name, String fullName) {
        super(name, fullName);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlInteractor(String name, Organism organism) {
        super(name, organism);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     */
    public XmlInteractor(String name, String fullName, Organism organism) {
        super(name, fullName, organism);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlInteractor(String name, Xref uniqueId) {
        super(name, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlInteractor(String name, String fullName, Xref uniqueId) {
        super(name, fullName, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlInteractor(String name, Organism organism, Xref uniqueId) {
        super(name, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link java.lang.String} object.
     * @param fullName a {@link java.lang.String} object.
     * @param organism a {@link psidev.psi.mi.jami.model.Organism} object.
     * @param uniqueId a {@link psidev.psi.mi.jami.model.Xref} object.
     */
    public XmlInteractor(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, organism, uniqueId);
    }
}
