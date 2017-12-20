package psidev.psi.mi.jami.bridges.mapper.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import psidev.psi.mi.jami.bridges.mapper.ProteinMapperListener;
import psidev.psi.mi.jami.datasource.FileSourceContext;
import psidev.psi.mi.jami.model.Protein;

import java.io.*;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 *
 * @author Gabriel Aldam (galdam@ebi.ac.uk)
 * @since 23/07/13

 */
public class ProteinMappingStatisticsWriter implements ProteinMapperListener {


    /** Constant <code>log</code> */
    protected static final Logger log = LoggerFactory.getLogger(ProteinMappingStatisticsWriter.class.getName());

    private Writer writer ;

    /** Constant <code>NEW_LINE="\n"</code> */
    public static final String NEW_LINE = "\n";
    /** Constant <code>NEW_EVENT="\t"</code> */
    public static final String NEW_EVENT = "\t";

    //protected int updateCount = 0, removedCount = 0, additionCount = 0;


    /**
     * <p>Constructor for ProteinMappingStatisticsWriter.</p>
     *
     * @param fileName a {@link java.lang.String} object.
     * @throws java.io.IOException if any.
     */
    public ProteinMappingStatisticsWriter(String fileName) throws IOException {
        if(fileName == null || fileName.length() == 0)
            throw new IllegalArgumentException("Provided a no file to write to.");


        writer = new BufferedWriter( new FileWriter(new File(fileName)) );


        writer.write("Protein"); writer.write(NEW_EVENT);
        writer.write("Status"); writer.write(NEW_EVENT);
        writer.write("Message"); writer.write(NEW_EVENT);
        writer.write("File Source");
        writer.flush();
    }

    /**
     * Close both files.
     *
     * @throws java.io.IOException if any.
     */
    public void close() throws IOException {
        if(writer != null) writer.close();
    }


    /** {@inheritDoc} */
    public void onSuccessfulMapping(Protein p, Collection<String> report) {
        try{
            writer.write(NEW_LINE);
            writer.write(p.toString());
            writer.write(NEW_EVENT);
            writer.write("Success");
            if(report.iterator().hasNext())
                writer.write(report.iterator().next());
            writer.write(NEW_EVENT);
            if (p instanceof FileSourceContext){
                FileSourceContext context = (FileSourceContext) p;
                if (context.getSourceLocator() != null)
                    writer.write(context.getSourceLocator().toString());
            }
            writer.flush();
        } catch (IOException e) {
            log.error("Could not write on successful mapping",e);
        }
    }



    /** {@inheritDoc} */
    public void onFailedMapping(Protein p, Collection<String> report) {
        try{
            writer.write(NEW_LINE);
            writer.write(p.toString()); writer.write(NEW_EVENT);
            writer.write("Failed");     writer.write(NEW_EVENT);
            if(report.iterator().hasNext())
                writer.write(report.iterator().next());
            writer.write(NEW_EVENT);
            if (p instanceof FileSourceContext){
                FileSourceContext context = (FileSourceContext) p;
                if (context.getSourceLocator() != null)
                    writer.write(context.getSourceLocator().toString());
            }
            writer.flush();
        } catch (IOException e) {
            log.error("Could not write on failed mapping",e);
        }
    }

    /** {@inheritDoc} */
    public void onToBeReviewedMapping(Protein p, Collection<String> report) {
        try{
            writer.write(NEW_LINE);
            writer.write(p.toString()); writer.write(NEW_EVENT);
            writer.write("To be reviewed");     writer.write(NEW_EVENT);
            if(report.iterator().hasNext())
                writer.write(report.iterator().next());
            writer.write(NEW_EVENT);
            if (p instanceof FileSourceContext){
                FileSourceContext context = (FileSourceContext) p;
                if (context.getSourceLocator() != null)
                    writer.write(context.getSourceLocator().toString());
            }
            writer.flush();
        } catch (IOException e) {
            log.error("Could not write on failed mapping",e);
        }
    }
}
