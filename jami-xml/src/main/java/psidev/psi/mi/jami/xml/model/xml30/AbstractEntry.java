package psidev.psi.mi.jami.xml.model.xml30;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import psidev.psi.mi.jami.model.Annotation;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.model.AbstractBaseEntry;
import psidev.psi.mi.jami.xml.model.extension.xml300.DefaultXmlAnnotation;
import psidev.psi.mi.jami.xml.model.extension.xml300.DefaultXmlInteractor;

import java.util.List;

/**
 * Abstract class for Entry
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/11/13</pre>
 */
@XmlTransient
public abstract class AbstractEntry<T extends Interaction> extends AbstractBaseEntry<T> {

    /**
     * <p>Constructor for AbstractEntry.</p>
     */
    public AbstractEntry() {
        super();
    }

    //////////////////////////////////////////////////// Inner classes
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="interactorsWrapper")
    public static class JAXBInteractorsWrapper extends AbstractJAXBInteractorsWrapper {

        public JAXBInteractorsWrapper(){
            super(PsiXmlVersion.v3_0_0);
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= DefaultXmlInteractor.class, name="interactor", required = true)
        public List<Interactor> getJAXBInteractors() {
            return super.getJAXBInteractors();
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="entryAnnotationsWrapper")
    public static class JAXBAnnotationsWrapper extends AbstractJAXBAnnotationsWrapper {

        public JAXBAnnotationsWrapper(){
            super();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= DefaultXmlAnnotation.class, name="attribute", required = true)
        public List<Annotation> getJAXBAttributes() {
            return super.getJAXBAttributes();
        }
    }

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="interactionsWrapper")
    public static class JAXBInteractionsWrapper<T extends Object> extends AbstractJAXBInteractionsWrapper {

        public JAXBInteractionsWrapper(){
            super();
        }
    }
}
