package psidev.psi.mi.jami.xml.io.writer.elements.impl.xml30;

import psidev.psi.mi.jami.model.Allostery;
import psidev.psi.mi.jami.model.FeatureModificationEffector;
import psidev.psi.mi.jami.model.MoleculeEffector;
import psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts.xml30.AbstractXmlCooperativeEffectWriter;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Xml 3.0 writer for allostery
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public class XmlAllosteryWriter extends AbstractXmlCooperativeEffectWriter<Allostery> {

    /**
     * <p>Constructor for XmlAllosteryWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     * @param objectIndex a {@link psidev.psi.mi.jami.xml.cache.PsiXmlObjectCache} object.
     */
    public XmlAllosteryWriter(XMLStreamWriter writer, PsiXmlObjectCache objectIndex) {
        super(writer, objectIndex);
    }

    /** {@inheritDoc} */
    @Override
    protected void writeOtherProperties(Allostery object) throws XMLStreamException {
        // write allosteric molecule ref
        writeAllostericMolecule(object);
        // write allosteric effector
        writeAllostericEffector(object);
        // write allosteric mechanism
        writeAllostericMechanism(object);
        // write allostery type
        writeAllosteryType(object);
    }

    /**
     * <p>writeAllosteryType.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Allostery} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAllosteryType(Allostery object) throws XMLStreamException {
        if (object.getAllosteryType() != null){
            getCvWriter().write(object.getAllosteryType(), "allosteryType");
        }
    }

    /**
     * <p>writeAllostericMechanism.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Allostery} object.
     */
    protected void writeAllostericMechanism(Allostery object) {
        if (object.getAllostericMechanism() != null){
            getCvWriter().write(object.getAllostericMechanism(), "allostericMechanism");
        }
    }

    /**
     * <p>writeAllostericEffector.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Allostery} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAllostericEffector(Allostery object) throws XMLStreamException {
        switch (object.getAllostericEffector().getEffectorType()){
            case molecule:
                // write start molecule
                getStreamWriter().writeStartElement("allostericEffectorRef");
                getStreamWriter().writeCharacters(Integer.toString(getObjectIndex().
                        extractIdForParticipant(((MoleculeEffector) object.getAllostericEffector()).getMolecule())));
                // write end molecule
                getStreamWriter().writeEndElement();
                break;
            case feature_modification:
                // write start feature modification
                getStreamWriter().writeStartElement("allostericModificationRef");
                getStreamWriter().writeCharacters(Integer.toString(getObjectIndex().
                        extractIdForFeature(((FeatureModificationEffector) object.getAllostericEffector()).getFeatureModification())));
                // write end molecule
                getStreamWriter().writeEndElement();
                break;
            default:
                // nothing to write here
                break;
        }
    }

    /**
     * <p>writeAllostericMolecule.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Allostery} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected void writeAllostericMolecule(Allostery object) throws XMLStreamException {
        // write start molecule
        getStreamWriter().writeStartElement("allostericMoleculeRef");
        getStreamWriter().writeCharacters(Integer.toString(getObjectIndex().extractIdForParticipant(object.getAllostericMolecule())));
        // write end molecule
        getStreamWriter().writeEndElement();
    }

    /** {@inheritDoc} */
    @Override
    protected void writeStartCooperativeEffect() throws XMLStreamException {
        getStreamWriter().writeStartElement("allostery");
    }
}
