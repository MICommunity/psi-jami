package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.model.Xref;

import java.util.Collection;

public class CommonUtils {

    public static String extractIntactAcFromIdentifier(Collection<Xref> identifiers) {
        String ac = null;
        for(Xref identifier:identifiers){
            if(identifier.getDatabase().getMIIdentifier().equals("MI:0469")){ // database intact
                ac = identifier.getId();
                break;
            }
        }
        return ac;
    }


}
