package psidev.psi.mi.jami.utils;

import com.thoughtworks.xstream.XStream;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.Xref;
import psidev.psi.mi.jami.model.impl.DefaultInteractor;

import java.util.Collection;

public class CommonUtils {

    public static Object cloneAnObject(Object objectToBeCloned){
        XStream xstream=new XStream();
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypesByRegExp(new String[] { ".*" });
        return xstream.fromXML(new XStream().toXML(objectToBeCloned));
    }

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
