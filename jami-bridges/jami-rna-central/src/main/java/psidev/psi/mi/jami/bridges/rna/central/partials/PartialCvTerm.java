package psidev.psi.mi.jami.bridges.rna.central.partials;

import lombok.Builder;
import lombok.Data;
import psidev.psi.mi.jami.bridges.rna.central.utils.OLSUtils;
import psidev.psi.mi.jami.model.CvTerm;
import psidev.psi.mi.jami.model.impl.DefaultCvTerm;
import psidev.psi.mi.jami.model.impl.DefaultXref;

@Data
@Builder
public class PartialCvTerm implements Partial<CvTerm> {
    private String name;
    private String id;
    @Builder.Default
    private CvTerm ontology = OLSUtils.miCV;

    @Override
    public CvTerm complete() {
        if (name != null) return new DefaultCvTerm(name, new DefaultXref(ontology, id, OLSUtils.identityCV));
        else if (id != null && ontology == OLSUtils.miCV) return OLSUtils.getCVByMIId(id);
        else return null;
    }

    public static PartialCvTerm from(CvTerm term) {
        return PartialCvTerm.builder()
                .id(term.getMIIdentifier())
                .name(term.getShortName())
                .build();
    }
}
