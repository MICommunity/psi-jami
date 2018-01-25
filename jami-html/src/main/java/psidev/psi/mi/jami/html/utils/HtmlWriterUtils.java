package psidev.psi.mi.jami.html.utils;

import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.datasource.FileSourceLocator;

/**
 * Utility class for writing molecular interactions in html
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>02/04/13</pre>
 */
public class HtmlWriterUtils {

    /** Constant <code>NEW_LINE="System.getProperty(line.separator)"</code> */
    public final static String NEW_LINE = System.getProperty("line.separator");

    /**
     * <p>getHtmlAnchorFor</p>
     *
     * @param object a {@link java.lang.Object} object.
     * @return a {@link java.lang.String} object.
     */
    public static String getHtmlAnchorFor(Object object){

        if (object != null){
            // first extract file context
            if (object instanceof FileSourceContext){
                FileSourceContext context = (FileSourceContext) object;

                if (context.getSourceLocator() != null){
                    FileSourceLocator locator = context.getSourceLocator();

                    return locator.toString();
                }
            }

            return Integer.toString(object.hashCode());
        }
        return null;
    }
}
