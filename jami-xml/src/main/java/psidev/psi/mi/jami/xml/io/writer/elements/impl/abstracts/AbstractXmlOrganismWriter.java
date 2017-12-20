package psidev.psi.mi.jami.xml.io.writer.elements.impl.abstracts;

import psidev.psi.mi.jami.exception.MIIOException;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.Organism;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter;
import psidev.psi.mi.jami.xml.io.writer.elements.impl.*;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 * Abstract class forPSI-XML 2.5 writer for organism
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>12/11/13</pre>
 */
public abstract class AbstractXmlOrganismWriter implements PsiXmlElementWriter<Organism> {

    private XMLStreamWriter streamWriter;
    private PsiXmlElementWriter<Alias> aliasWriter;
    private PsiXmlVariableNameWriter<CvTerm> cvWriter;

    /**
     * <p>Constructor for AbstractXmlOrganismWriter.</p>
     *
     * @param writer a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    public AbstractXmlOrganismWriter(XMLStreamWriter writer){
        if (writer == null){
            throw new IllegalArgumentException("The XML stream writer is mandatory for the AbstractXmlOrganismWriter");
        }
        this.streamWriter = writer;
    }

    /**
     * <p>Getter for the field <code>aliasWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public PsiXmlElementWriter<Alias> getAliasWriter() {
        if (this.aliasWriter == null){
            this.aliasWriter = new XmlAliasWriter(streamWriter);
        }
        return aliasWriter;
    }

    /**
     * <p>Setter for the field <code>aliasWriter</code>.</p>
     *
     * @param aliasWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlElementWriter} object.
     */
    public void setAliasWriter(PsiXmlElementWriter<Alias> aliasWriter) {
        this.aliasWriter = aliasWriter;
    }

    /**
     * <p>Getter for the field <code>cvWriter</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public PsiXmlVariableNameWriter<CvTerm> getCvWriter() {
        if (this.cvWriter == null){
           initialiseCvWriter();
        }
        return cvWriter;
    }

    /**
     * <p>initialiseCvWriter.</p>
     */
    protected abstract void initialiseCvWriter();

    /**
     * <p>Setter for the field <code>cvWriter</code>.</p>
     *
     * @param cvWriter a {@link psidev.psi.mi.jami.xml.io.writer.elements.PsiXmlVariableNameWriter} object.
     */
    public void setCvWriter(PsiXmlVariableNameWriter<CvTerm> cvWriter) {
        this.cvWriter = cvWriter;
    }

    /** {@inheritDoc} */
    @Override
    public void write(Organism object) throws MIIOException {
        try {
            // write start
            writeStartOrganism();

            // write taxid
            this.streamWriter.writeAttribute("ncbiTaxId", Integer.toString(object.getTaxId()));

            // write names
            boolean hasShortLabel = object.getCommonName() != null;
            boolean hasExperimentFullLabel = object.getScientificName() != null;
            boolean hasAliases = !object.getAliases().isEmpty();
            if (hasShortLabel || hasExperimentFullLabel || hasAliases){
                this.streamWriter.writeStartElement("names");
                // write shortname
                if (hasShortLabel){
                    this.streamWriter.writeStartElement("shortLabel");
                    this.streamWriter.writeCharacters(object.getCommonName());
                    this.streamWriter.writeEndElement();
                }
                // write fullname
                if (hasExperimentFullLabel){
                    this.streamWriter.writeStartElement("fullName");
                    this.streamWriter.writeCharacters(object.getScientificName());
                    this.streamWriter.writeEndElement();
                }
                // write aliases
                for (Alias alias : object.getAliases()){
                    getAliasWriter().write(alias);
                }
                // write end names
                this.streamWriter.writeEndElement();
            }

            // write celltype
            if (object.getCellType() != null){
                getCvWriter().write(object.getCellType(), "cellType");
            }
            //write compartment
            if (object.getCompartment() != null){
                getCvWriter().write(object.getCompartment(), "compartment");
            }
            // write tissue
            if (object.getTissue()!= null){
                getCvWriter().write(object.getTissue(), "tissue");
            }

            // write other properties
            writeOtherProperties(object);

            // write end organism
            this.streamWriter.writeEndElement();

        } catch (XMLStreamException e) {
            throw new MIIOException("Impossible to write the db reference : "+object.toString(), e);
        }
    }

    /**
     * <p>writeOtherProperties.</p>
     *
     * @param object a {@link psidev.psi.mi.jami.model.Organism} object.
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeOtherProperties(Organism object) throws XMLStreamException;

    /**
     * <p>writeStartOrganism.</p>
     *
     * @throws javax.xml.stream.XMLStreamException if any.
     */
    protected abstract void writeStartOrganism() throws XMLStreamException;

    /**
     * <p>Getter for the field <code>streamWriter</code>.</p>
     *
     * @return a {@link javax.xml.stream.XMLStreamWriter} object.
     */
    protected XMLStreamWriter getStreamWriter() {
        return streamWriter;
    }
}
