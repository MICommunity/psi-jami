package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;

/**
 * The Xml implementation of Interactor
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/07/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
@XmlType(namespace = "http://psi.hupo.org/mi/mif", name = "defaultInteractor")
public class DefaultXmlInteractor extends AbstractXmlInteractor {

    /**
     * <p>Constructor for XmlInteractor.</p>
     */
    public DefaultXmlInteractor() {
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public DefaultXmlInteractor(String name, CvTerm type) {
        super(name, type);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public DefaultXmlInteractor(String name, String fullName, CvTerm type) {
        super(name, fullName, type);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     */
    public DefaultXmlInteractor(String name, CvTerm type, Organism organism) {
        super(name, type, organism);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     */
    public DefaultXmlInteractor(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type, organism);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlInteractor(String name, CvTerm type, Xref uniqueId) {
        super(name, type, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlInteractor(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlInteractor(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlInteractor(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     */
    public DefaultXmlInteractor(String name) {
        super(name);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     */
    public DefaultXmlInteractor(String name, String fullName) {
        super(name, fullName);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param organism a {@link Organism} object.
     */
    public DefaultXmlInteractor(String name, Organism organism) {
        super(name, organism);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param organism a {@link Organism} object.
     */
    public DefaultXmlInteractor(String name, String fullName, Organism organism) {
        super(name, fullName, organism);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlInteractor(String name, Xref uniqueId) {
        super(name, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlInteractor(String name, String fullName, Xref uniqueId) {
        super(name, fullName, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlInteractor(String name, Organism organism, Xref uniqueId) {
        super(name, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlInteractor.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlInteractor(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, organism, uniqueId);
    }
}