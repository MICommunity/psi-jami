package psidev.psi.mi.jami.utils;

import psidev.psi.mi.jami.datasource.MIFileDataSource;
import psidev.psi.mi.jami.datasource.SourceCategory;

import java.util.regex.Matcher;

/**
 * Utilisty class for MIFileDataSource
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>16/10/13</pre>
 */
public class MIFileDatasourceUtils {

    /**
     * <p>findSourceCategoryFromString</p>
     *
     * @param source a {@link java.lang.String} object.
     * @return a {@link psidev.psi.mi.jami.datasource.SourceCategory} object.
     */
    public static SourceCategory findSourceCategoryFromString(String source){
        if (source == null){
            return null;
        }

        // file uri
        if (source.startsWith(MIFileDataSource.FILE_URI_PREFIX)){
            return SourceCategory.file_URI;
        }
        // check if url
        else {
            Matcher matcher = MIFileDataSource.URL_PREFIX_REGEXP.matcher(source);

            // we have a url
            if (matcher.find()){
                return SourceCategory.URL;
            }
            // we have a file
            else{
                return SourceCategory.file;
            }
        }

    }
}
