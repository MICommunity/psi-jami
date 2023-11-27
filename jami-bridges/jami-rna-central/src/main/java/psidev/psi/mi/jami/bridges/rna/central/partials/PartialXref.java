package psidev.psi.mi.jami.bridges.rna.central.partials;

import lombok.Builder;
import lombok.Data;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultXref;

@Data
@Builder
public class PartialXref implements Partial<Xref> {
    private PartialCvTerm database;
    private String identifier;
    private PartialCvTerm qualifier;

    @Override
    public Xref complete() {
        return new DefaultXref(database.complete(), identifier, qualifier.complete());
    }
}
