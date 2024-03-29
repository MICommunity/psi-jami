package psidev.psi.mi.jami.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.ListableJobLocator;
import org.springframework.batch.core.launch.NoSuchJobException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Collection;

/**
 * Job registry for MIBatchJobManager
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>23/07/13</pre>
 */
public class SimpleJobRegistry implements ListableJobLocator {

    @Autowired
    private ApplicationContext applicationContext;

    /** {@inheritDoc} */
    public Job getJob(String name) throws NoSuchJobException {
        Job job = (Job) applicationContext.getBean(name);

        if (job == null) {
            throw new NoSuchJobException("The simple job registry is not aware of this job: "+name);
        }

        return job;
    }

    /**
     * <p>getJobNames.</p>
     *
     * @return a {@link java.util.Collection} object.
     */
    public Collection<String> getJobNames() {
        return Arrays.asList(applicationContext.getBeanNamesForType(Job.class));
    }
}
