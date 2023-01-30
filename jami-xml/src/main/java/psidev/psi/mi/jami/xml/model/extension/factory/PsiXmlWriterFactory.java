package psidev.psi.mi.jami.xml.model.extension.factory;

import psidev.psi.mi.jami.datasource.InteractionWriter;
import psidev.psi.mi.jami.model.ComplexType;
import psidev.psi.mi.jami.model.InteractionCategory;
import psidev.psi.mi.jami.xml.PsiXmlType;
import psidev.psi.mi.jami.xml.PsiXmlVersion;
import psidev.psi.mi.jami.xml.io.writer.compact.*;
import psidev.psi.mi.jami.xml.io.writer.compact.extended.LightCompactXmlBinaryWriter;
import psidev.psi.mi.jami.xml.io.writer.expanded.*;

/**
 * Factory for creating a PSI-XML writer
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>02/05/14</pre>
 */
public class PsiXmlWriterFactory {

    private static final PsiXmlWriterFactory instance = new PsiXmlWriterFactory();

    private PsiXmlWriterFactory(){
    }

    /**
     * <p>Getter for the field <code>instance</code>.</p>
     *
     * @return a {@link psidev.psi.mi.jami.xml.model.extension.factory.PsiXmlWriterFactory} object.
     */
    public static PsiXmlWriterFactory getInstance() {
        return instance;
    }

    /**
     * <p>createPsiXmlWriter.</p>
     *
     * @param interactionCategory a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param complexType a {@link psidev.psi.mi.jami.model.ComplexType} object.
     * @param type a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @param extended a boolean.
     * @param named a boolean.
     * @return a {@link psidev.psi.mi.jami.datasource.InteractionWriter} object.
     */
    public InteractionWriter createPsiXmlWriter(InteractionCategory interactionCategory, ComplexType complexType,
                                                PsiXmlType type, PsiXmlVersion version, boolean extended, boolean named){
        switch (complexType){
            case binary:
                return createPsiXmlBinaryWriter(interactionCategory, version, type, extended, named);
            default:
                return createPsiXmlWriter(interactionCategory, type, version, extended, named);
        }
    }

    /**
     * <p>createPsiXmlBinaryWriter.</p>
     *
     * @param interactionCategory a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param type a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @param extended a boolean.
     * @param named a boolean.
     * @return a {@link psidev.psi.mi.jami.datasource.InteractionWriter} object.
     */
    public InteractionWriter createPsiXmlBinaryWriter(InteractionCategory interactionCategory,
                                                      PsiXmlVersion version,
                                                      PsiXmlType type, boolean extended, boolean named){
        if (interactionCategory == null){
            interactionCategory = InteractionCategory.mixed;
        }

        if (extended){
            switch (type){
                case compact:
                    switch (interactionCategory){
                        case evidence:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlBinaryEvidenceWriter(version);
                        case modelled:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlModelledBinaryWriter(version);
                        case basic:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.LightCompactXmlBinaryWriter(version);
                        case mixed:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlBinaryWriter(version);
                        default:
                            throw new IllegalArgumentException("Cannot find a XML binary writer for interaction category: "+interactionCategory);
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.ExpandedXmlBinaryEvidenceWriter(version);
                        case modelled:
                            return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.ExpandedXmlModelledBinaryWriter(version);
                        case basic:
                            return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.LightExpandedXmlBinaryWriter(version);
                        case mixed:
                            return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.ExpandedXmlBinaryWriter(version);
                        default:
                            throw new IllegalArgumentException("Cannot find a XML binary writer for interaction category: "+interactionCategory);
                    }
            }
        }
        else if (named){
            switch (type){
                case compact:
                    switch (interactionCategory){
                        case evidence:
                            return new CompactXmlNamedBinaryEvidenceWriter(version);
                        case modelled:
                            return new CompactXmlNamedModelledBinaryWriter(version);
                        case basic:
                            return new LightCompactXmlNamedBinaryWriter(version);
                        case mixed:
                            return new CompactXmlNamedBinaryWriter(version);
                        default:
                            throw new IllegalArgumentException("Cannot find a XML binary writer for interaction category: "+interactionCategory);
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            return new ExpandedXmlNamedBinaryEvidenceWriter(version);
                        case modelled:
                            return new ExpandedXmlNamedModelledBinaryWriter(version);
                        case basic:
                            return new LightExpandedXmlNamedBinaryWriter(version);
                        case mixed:
                            return new ExpandedXmlNamedBinaryWriter(version);
                        default:
                            throw new IllegalArgumentException("Cannot find a XML binary writer for interaction category: "+interactionCategory);
                    }
            }
        }
        else{
            switch (type){
                case compact:
                    switch (interactionCategory){
                        case evidence:
                            return new CompactXmlBinaryEvidenceWriter(version);
                        case modelled:
                            return new CompactXmlModelledBinaryWriter(version);
                        case basic:
                            return new LightCompactXmlBinaryWriter(version);
                        case mixed:
                            return new CompactXmlBinaryWriter(version);
                        default:
                            throw new IllegalArgumentException("Cannot find a XML binary writer for interaction category: "+interactionCategory);
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            return new ExpandedXmlBinaryEvidenceWriter(version);
                        case modelled:
                            return new ExpandedXmlModelledBinaryWriter(version);
                        case basic:
                            return new LightExpandedXmlBinaryWriter(version);
                        case mixed:
                            return new ExpandedXmlBinaryWriter(version);
                        default:
                            throw new IllegalArgumentException("Cannot find a XML binary writer for interaction category: "+interactionCategory);
                    }
            }
        }
    }

    /**
     * <p>createPsiXmlWriter.</p>
     *
     * @param interactionCategory a {@link psidev.psi.mi.jami.model.InteractionCategory} object.
     * @param type a {@link psidev.psi.mi.jami.xml.PsiXmlType} object.
     * @param extended a boolean.
     * @param named a boolean.
     * @return a {@link psidev.psi.mi.jami.datasource.InteractionWriter} object.
     */
    public InteractionWriter createPsiXmlWriter(InteractionCategory interactionCategory, PsiXmlType type,
                                                PsiXmlVersion version,
                                                boolean extended, boolean named){
        if (interactionCategory == null){
            interactionCategory = InteractionCategory.mixed;
        }

        if (extended){
            switch (type){
                case compact:
                    switch (interactionCategory){
                        case evidence:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlEvidenceWriter(version);
                        case modelled:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlModelledWriter(version);
                        case basic:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.LightCompactXmlWriter(version);
                        case complex:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlComplexWriter(version);
                        default:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlWriter(version);
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.ExpandedXmlEvidenceWriter(version);
                        case modelled:
                            return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.ExpandedXmlModelledWriter(version);
                        case basic:
                            return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.LightExpandedXmlWriter(version);
                        case complex:
                            return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.ExpandedXmlComplexWriter(version);
                        default:
                            return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.ExpandedXmlWriter(version);
                    }
            }
        }
        else if (named){
            switch (type){
                case compact:
                    switch (interactionCategory){
                        case evidence:
                            return new CompactXmlNamedEvidenceWriter(version);
                        case modelled:
                            return new CompactXmlNamedModelledWriter(version);
                        case basic:
                            return new LightCompactXmlNamedWriter(version);
                        case complex:
                            return new CompactXmlComplexWriter(version);
                        default:
                            return new CompactXmlNamedWriter(version);
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            return new ExpandedXmlNamedEvidenceWriter(version);
                        case modelled:
                            return new ExpandedXmlNamedModelledWriter(version);
                        case basic:
                            return new LightExpandedXmlNamedWriter(version);
                        case complex:
                            return new ExpandedXmlComplexWriter(version);
                        default:
                            return new ExpandedXmlNamedWriter(version);
                    }
            }
        }
        else{
            switch (type){
                case compact:
                    switch (interactionCategory){
                        case evidence:
                            return new CompactXmlEvidenceWriter(version);
                        case modelled:
                            return new CompactXmlModelledWriter(version);
                        case basic:
                            return new LightCompactXmlWriter(version);
                        case complex:
                            return new CompactXmlComplexWriter(version);
                        default:
                            return new CompactXmlWriter(version);
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            return new ExpandedXmlEvidenceWriter(version);
                        case modelled:
                            return new ExpandedXmlModelledWriter(version);
                        case basic:
                            return new LightExpandedXmlWriter(version);
                        case complex:
                            return new ExpandedXmlComplexWriter(version);
                        default:
                            return new ExpandedXmlWriter(version);
                    }
            }
        }
    }
}
