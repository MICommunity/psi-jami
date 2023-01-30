package psidev.psi.mi.jami.xml.model.extension.xml254;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.model.Xref;

/**
 * Xml implementation for complex
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>08/10/13</pre>
 */
@XmlType(namespace = "http://psi.hupo.org/mi/mif", name = "defaultComplex")
@XmlAccessorType(XmlAccessType.NONE)
public class DefaultXmlComplex extends AbstractXmlComplex {

    /**
     * <p>Constructor for XmlComplex.</p>
     */
    public DefaultXmlComplex(){
        super();
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public DefaultXmlComplex(String name, CvTerm type) {
        super(name, type);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlComplex(String name, String fullName, Organism organism, Xref uniqueId) {
        super(name, fullName, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlComplex(String name, Organism organism, Xref uniqueId) {
        super(name, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlComplex(String name, String fullName, Xref uniqueId) {
        super(name, fullName, uniqueId);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlComplex(String name, Xref uniqueId) {
        super(name, uniqueId);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param organism a {@link Organism} object.
     */
    public DefaultXmlComplex(String name, String fullName, Organism organism) {
        super(name, fullName, organism);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param organism a {@link Organism} object.
     */
    public DefaultXmlComplex(String name, Organism organism) {
        super(name, organism);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     */
    public DefaultXmlComplex(String name, String fullName) {
        super(name, fullName);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     */
    public DefaultXmlComplex(String name) {
        super(name);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlComplex(String name, String fullName, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, fullName, type, organism, uniqueId);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     */
    public DefaultXmlComplex(String name, String fullName, CvTerm type) {
        super(name, fullName, type);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     */
    public DefaultXmlComplex(String name, CvTerm type, Organism organism) {
        super(name, type, organism);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     */
    public DefaultXmlComplex(String name, String fullName, CvTerm type, Organism organism) {
        super(name, fullName, type, organism);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlComplex(String name, CvTerm type, Xref uniqueId) {
        super(name, type, uniqueId);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param fullName a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlComplex(String name, String fullName, CvTerm type, Xref uniqueId) {
        super(name, fullName, type, uniqueId);
    }

    /**
     * <p>Constructor for XmlComplex.</p>
     *
     * @param name a {@link String} object.
     * @param type a {@link CvTerm} object.
     * @param organism a {@link Organism} object.
     * @param uniqueId a {@link Xref} object.
     */
    public DefaultXmlComplex(String name, CvTerm type, Organism organism, Xref uniqueId) {
        super(name, type, organism, uniqueId);
    }
}