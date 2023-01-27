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
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlBinaryEvidenceWriter();
                        case modelled:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlModelledBinaryWriter();
                        case basic:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.LightCompactXmlBinaryWriter();
                        case mixed:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlBinaryWriter();
                        default:
                            throw new IllegalArgumentException("Cannot find a XML binary writer for interaction category: "+interactionCategory);
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            switch (version) {
                                case v3_0_0:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300.ExpandedXmlBinaryEvidenceWriter();
                                case v2_5_3:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253.ExpandedXmlBinaryEvidenceWriter();
                                case v2_5_4:
                                default:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml254.ExpandedXmlBinaryEvidenceWriter();
                            }
                        case modelled:
                            switch (version) {
                                case v3_0_0:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300.ExpandedXmlModelledBinaryWriter();
                                case v2_5_3:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253.ExpandedXmlModelledBinaryWriter();
                                case v2_5_4:
                                default:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml254.ExpandedXmlModelledBinaryWriter();
                            }
                        case basic:
                            switch (version) {
                                case v3_0_0:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300.LightExpandedXmlBinaryWriter();
                                case v2_5_3:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253.LightExpandedXmlBinaryWriter();
                                case v2_5_4:
                                default:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml254.LightExpandedXmlBinaryWriter();
                            }
                        case mixed:
                            switch (version) {
                                case v3_0_0:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300.ExpandedXmlBinaryWriter();
                                case v2_5_3:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253.ExpandedXmlBinaryWriter();
                                case v2_5_4:
                                default:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml254.ExpandedXmlBinaryWriter();
                            }
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
                            return new CompactXmlNamedBinaryEvidenceWriter();
                        case modelled:
                            return new CompactXmlNamedModelledBinaryWriter();
                        case basic:
                            return new LightCompactXmlNamedBinaryWriter();
                        case mixed:
                            return new CompactXmlNamedBinaryWriter();
                        default:
                            throw new IllegalArgumentException("Cannot find a XML binary writer for interaction category: "+interactionCategory);
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            return new ExpandedXmlNamedBinaryEvidenceWriter();
                        case modelled:
                            return new ExpandedXmlNamedModelledBinaryWriter();
                        case basic:
                            return new LightExpandedXmlNamedBinaryWriter();
                        case mixed:
                            return new ExpandedXmlNamedBinaryWriter();
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
                            return new CompactXmlBinaryEvidenceWriter();
                        case modelled:
                            return new CompactXmlModelledBinaryWriter();
                        case basic:
                            return new LightCompactXmlBinaryWriter();
                        case mixed:
                            return new CompactXmlBinaryWriter();
                        default:
                            throw new IllegalArgumentException("Cannot find a XML binary writer for interaction category: "+interactionCategory);
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            return new ExpandedXmlBinaryEvidenceWriter();
                        case modelled:
                            return new ExpandedXmlModelledBinaryWriter();
                        case basic:
                            return new LightExpandedXmlBinaryWriter();
                        case mixed:
                            return new ExpandedXmlBinaryWriter();
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
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlEvidenceWriter();
                        case modelled:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlModelledWriter();
                        case basic:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.LightCompactXmlWriter();
                        case complex:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlComplexWriter();
                        default:
                            return new psidev.psi.mi.jami.xml.io.writer.compact.extended.CompactXmlWriter();
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            switch (version) {
                                case v3_0_0:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300.ExpandedXmlEvidenceWriter();
                                case v2_5_3:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253.ExpandedXmlEvidenceWriter();
                                case v2_5_4:
                                default:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml254.ExpandedXmlEvidenceWriter();
                            }
                        case modelled:
                            switch (version) {
                                case v3_0_0:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300.ExpandedXmlModelledWriter();
                                case v2_5_3:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253.ExpandedXmlModelledWriter();
                                case v2_5_4:
                                default:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml254.ExpandedXmlModelledWriter();
                            }
                        case basic:
                            switch (version) {
                                case v3_0_0:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300.LightExpandedXmlWriter();
                                case v2_5_3:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253.LightExpandedXmlWriter();
                                case v2_5_4:
                                default:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml254.LightExpandedXmlWriter();
                            }
                        case complex:
                            switch (version) {
                                case v3_0_0:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300.ExpandedXmlComplexWriter();
                                case v2_5_3:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253.ExpandedXmlComplexWriter();
                                case v2_5_4:
                                default:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml254.ExpandedXmlComplexWriter();
                            }
                        default:
                            switch (version) {
                                case v3_0_0:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml300.ExpandedXmlWriter();
                                case v2_5_3:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml253.ExpandedXmlWriter();
                                case v2_5_4:
                                default:
                                    return new psidev.psi.mi.jami.xml.io.writer.expanded.extended.xml254.ExpandedXmlWriter();
                            }
                    }
            }
        }
        else if (named){
            switch (type){
                case compact:
                    switch (interactionCategory){
                        case evidence:
                            return new CompactXmlNamedEvidenceWriter();
                        case modelled:
                            return new CompactXmlNamedModelledWriter();
                        case basic:
                            return new LightCompactXmlNamedWriter();
                        case complex:
                            return new CompactXmlComplexWriter();
                        default:
                            return new CompactXmlNamedWriter();
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            return new ExpandedXmlNamedEvidenceWriter();
                        case modelled:
                            return new ExpandedXmlNamedModelledWriter();
                        case basic:
                            return new LightExpandedXmlNamedWriter();
                        case complex:
                            return new ExpandedXmlComplexWriter();
                        default:
                            return new ExpandedXmlNamedWriter();
                    }
            }
        }
        else{
            switch (type){
                case compact:
                    switch (interactionCategory){
                        case evidence:
                            return new CompactXmlEvidenceWriter();
                        case modelled:
                            return new CompactXmlModelledWriter();
                        case basic:
                            return new LightCompactXmlWriter();
                        case complex:
                            return new CompactXmlComplexWriter();
                        default:
                            return new CompactXmlWriter();
                    }
                default:
                    switch (interactionCategory){
                        case evidence:
                            return new ExpandedXmlEvidenceWriter();
                        case modelled:
                            return new ExpandedXmlModelledWriter();
                        case basic:
                            return new LightExpandedXmlWriter();
                        case complex:
                            return new ExpandedXmlComplexWriter();
                        default:
                            return new ExpandedXmlWriter();
                    }
            }
        }
    }
}
