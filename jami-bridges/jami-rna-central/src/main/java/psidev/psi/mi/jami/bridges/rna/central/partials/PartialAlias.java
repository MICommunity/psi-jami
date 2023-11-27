package psidev.psi.mi.jami.bridges.rna.central.partials;

import lombok.Builder;
import lombok.Data;
import psidev.psi.mi.jami.model.Alias;
import psidev.psi.mi.jami.model.impl.DefaultAlias;

@Data
@Builder
public class PartialAlias implements Partial<Alias> {
    private String name;
    private PartialCvTerm type;

    public Alias complete() {
        return new DefaultAlias(type.complete(), name);
    }
}
