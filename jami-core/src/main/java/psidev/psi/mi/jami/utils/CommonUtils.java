package psidev.psi.mi.jami.utils;

import com.thoughtworks.xstream.XStream;
import psidev.psi.mi.jami.model.Interactor;
import psidev.psi.mi.jami.model.impl.DefaultInteractor;

public class CommonUtils {

    public static Object cloneAnObject(Object objectToBeCloned){
        XStream xstream=new XStream();
        XStream.setupDefaultSecurity(xstream);
        xstream.allowTypesByRegExp(new String[] { ".*" });
        return xstream.fromXML(new XStream().toXML(objectToBeCloned));
    }
}
