package psidev.psi.mi.jami.xml.model.xml30;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import com.sun.xml.bind.annotation.XmlLocation;
import org.xml.sax.Locator;
import psidev.psi.mi.jami.datasource.FileSourceLocator;
import psidev.psi.mi.jami.model.Interaction;
import psidev.psi.mi.jami.xml.model.extension.PsiXmlLocator;
import psidev.psi.mi.jami.xml.model.extension.xml300.DefaultXmlBasicInteraction;
import psidev.psi.mi.jami.xml.model.extension.xml300.DefaultXmlSource;
import psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlSource;

import java.util.List;

/**
 * Basic Entry implementation for JAXB read only.
 *
 * It does not take into account all experimental details
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>07/11/13</pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public class BasicEntry extends AbstractEntry<Interaction> {
    @XmlLocation
    @XmlTransient
    private Locator locator;

    /**
     * <p>setJAXBSource.</p>
     *
     * @param source a {@link ExtendedPsiXmlSource} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "source", type = DefaultXmlSource.class)
    public void setJAXBSource(ExtendedPsiXmlSource source) {
        super.setSource(source);
    }

    /**
     * <p>setJAXBInteractorsWrapper.</p>
     *
     * @param wrapper a JAXBInteractorsWrapper object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "interactorList")
    public void setJAXBInteractorsWrapper(JAXBInteractorsWrapper wrapper){
        super.setInteractorsWrapper(wrapper);
    }

    /**
     * <p>setJAXBInteractionsWrapper.</p>
     *
     * @param wrapper a {@link JAXBInteractionsWrapper} object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "interactionList", required = true)
    public void setJAXBInteractionsWrapper(JAXBInteractionsWrapper wrapper){
        super.setInteractionsWrapper(wrapper);
    }

    /**
     * <p>setJAXBAnnotationWrapper.</p>
     *
     * @param wrapper a JAXBAnnotationsWrapper object.
     */
    @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", name = "attributeList")
    public void setJAXBAnnotationWrapper(JAXBAnnotationsWrapper wrapper) {
        super.setAnnotationsWrapper(wrapper);
    }

    /** {@inheritDoc} */
    @Override
    public FileSourceLocator getSourceLocator() {
        if (super.getSourceLocator() == null && locator != null){
            super.setSourceLocator(new PsiXmlLocator(locator.getLineNumber(), locator.getColumnNumber(), null));
        }
        return super.getSourceLocator();
    }

    /** {@inheritDoc} */
    @Override
    public void setSourceLocator(FileSourceLocator sourceLocator) {
        if (sourceLocator == null){
            super.setSourceLocator(null);
        }
        else{
            super.setSourceLocator(new PsiXmlLocator(sourceLocator.getLineNumber(), sourceLocator.getCharNumber(), null));
        }
    }

    //////////////////////////////// class wrapper

    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(namespace = "http://psi.hupo.org/mi/mif300", name="basicInteractionsWrapper")
    public static class JAXBInteractionsWrapper extends AbstractEntry.JAXBInteractionsWrapper<Interaction>{

        public JAXBInteractionsWrapper(){
            super();
        }

        @XmlElement(namespace = "http://psi.hupo.org/mi/mif300", type= DefaultXmlBasicInteraction.class, name="interaction", required = true)
        public List<Interaction> getJAXBInteractions() {
            return super.getJAXBInteractions();
        }
    }
}
