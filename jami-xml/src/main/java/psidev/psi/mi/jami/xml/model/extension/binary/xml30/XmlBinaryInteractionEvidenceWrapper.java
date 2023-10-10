package psidev.psi.mi.jami.xml.model.extension.binary.xml30;

import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlInteractionEvidence;
import psidev.psi.mi.jami.xml.model.extension.xml300.ExtendedPsiXmlCausalRelationship;
import psidev.psi.mi.jami.xml.model.extension.xml300.XmlInteractionEvidence;

import javax.xml.bind.annotation.XmlTransient;
import java.util.List;

/**
 * Xml implementation of BinaryInteractionWrapper with a source locator
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
@XmlTransient
public class XmlBinaryInteractionEvidenceWrapper extends psidev.psi.mi.jami.xml.model.extension.binary.XmlBinaryInteractionEvidenceWrapper implements ExtendedPsiXmlInteractionEvidence {

    /**
     * <p>Constructor for XmlBinaryInteractionEvidenceWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence} object.
     */
    public XmlBinaryInteractionEvidenceWrapper(ExtendedPsiXmlInteractionEvidence interaction){
        super(interaction);
    }


    /**
     * <p>Constructor for XmlBinaryInteractionEvidenceWrapper.</p>
     *
     * @param interaction a {@link psidev.psi.mi.jami.xml.model.extension.ExtendedPsiXmlInteractionEvidence} object.
     * @param complexExpansion a {@link psidev.psi.mi.jami.model.CvTerm} object.
     */
    public XmlBinaryInteractionEvidenceWrapper(ExtendedPsiXmlInteractionEvidence interaction, CvTerm complexExpansion){
        super(interaction, complexExpansion);
    }

    /** {@inheritDoc} */
    @Override
    public List<ExtendedPsiXmlCausalRelationship> getCausalRelationships() {
        return ((XmlInteractionEvidence)getWrappedInteraction()).getCausalRelationships();
    }
}
