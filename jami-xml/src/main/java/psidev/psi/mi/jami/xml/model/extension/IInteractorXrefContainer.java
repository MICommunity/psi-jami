package psidev.psi.mi.jami.xml.model.extension;

import psidev.psi.mi.jami.model.Xref;

import java.util.Collection;
import java.util.List;

public interface IInteractorXrefContainer {

    Collection<Xref> getIdentifiers();

    List<Xref> getXrefs();
}
