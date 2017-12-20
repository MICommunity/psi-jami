package psidev.psi.mi.jami.batch;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.*;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * The psi mi enricher
 *
 * @author Marine Dumousseau (marine@ebi.ac.uk)
 * @version $Id$
 * @since <pre>02/08/13</pre>
 */
public class MIBatchJobManager {

    private static final Log log = LogFactory.getLog(MIBatchJobManager.class);

    private JobRepository jobRepository;

    private JobOperator jobOperator;

    /**
     * <p>Constructor for MIBatchJobManager.</p>
     */
    public MIBatchJobManager() {
    }

    /**
     * <p>main.</p>
     *
     * @param args an array of {@link java.lang.String} objects.
     * @throws org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException if any.
     * @throws org.springframework.batch.core.launch.NoSuchJobException if any.
     * @throws org.springframework.batch.core.JobParametersInvalidException if any.
     * @throws org.springframework.batch.core.repository.JobRestartException if any.
     * @throws org.springframework.batch.core.launch.JobInstanceAlreadyExistsException if any.
     * @throws org.springframework.batch.core.launch.NoSuchJobInstanceException if any.
     * @throws org.springframework.batch.core.launch.NoSuchJobExecutionException if any.
     */
    public static void main(String[] args) throws JobInstanceAlreadyCompleteException, NoSuchJobException, JobParametersInvalidException, JobRestartException, JobInstanceAlreadyExistsException, NoSuchJobInstanceException, NoSuchJobExecutionException {
        String springApplicationContext = null;
        String jobName = null;
        String jobId = null;

        // only one argument which is the spring file
        if ( args.length == 2){
            springApplicationContext = args[0];
            jobName = args[1];

        }
        else if (args.length == 3) {
            springApplicationContext = args[0];
            jobName = args[1];
            jobId = args[2];
        }
        else {
            throw new IllegalArgumentException("We found " + args.length + " arguments where only two (spring context file, job name) or three (spring context file, job name, job id) are expected.");
        }

        // loads the spring context defining beans and jobs
        ApplicationContext context = new ClassPathXmlApplicationContext(
                new String[] {springApplicationContext});

        MIBatchJobManager rm = (MIBatchJobManager)
                context.getBean("psiMIJobManager");

        if ( jobId== null){
            String generatedJobId = rm.startJob(jobName);
            log.info("Generated job id: "+generatedJobId);
        }
        else {
            Long executionId = rm.restartJob(jobName, jobId);
            log.info("Restarted job id: "+jobId + " with new execution id " + executionId);
        }
    }

    /**
     * Find the last JobExecution having this name and this id
     *
     * @param jobName a {@link java.lang.String} object.
     * @param jobId a {@link java.lang.String} object.
     * @return a {@link org.springframework.batch.core.JobExecution} object.
     */
    public JobExecution findLastJobExecutionFor(String jobName, String jobId) {

        JobParametersBuilder jobParamBuilder = new JobParametersBuilder();
        jobParamBuilder.addString("MIJobId", jobId).toJobParameters();

        return jobRepository.getLastJobExecution(jobName, jobParamBuilder.toJobParameters());
    }

    /**
     * This method will restart the last job which has been executed with a given name
     *
     * @param jobName a {@link java.lang.String} object.
     * @param jobId:  jobid generated for the job
     * @return the new job execution id
     * @throws org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException if any.
     * @throws org.springframework.batch.core.JobParametersInvalidException if any.
     * @throws org.springframework.batch.core.launch.NoSuchJobExecutionException if any.
     * @throws org.springframework.batch.core.repository.JobRestartException if any.
     * @throws org.springframework.batch.core.launch.NoSuchJobException if any.
     * @throws org.springframework.batch.core.launch.NoSuchJobInstanceException if any.
     */
    public Long restartJob(String jobName, String jobId) throws JobInstanceAlreadyCompleteException, JobParametersInvalidException, NoSuchJobExecutionException, JobRestartException, NoSuchJobException, NoSuchJobInstanceException {
        JobExecution jobExecution = findLastJobExecutionFor(jobName, jobId);

        if (jobExecution == null) {
            throw new IllegalStateException("No job execution found for Job Id:"+jobId+", Name:"+jobName);
        }

        return jobOperator.restart(jobExecution.getId());
    }

    /**
     * Find all job ids having a given name. Start will allow to search jobs from a certain starting index and numberJobs indicates the number of job ids to return
     *
     * @param jobName a {@link java.lang.String} object.
     * @param start a int.
     * @param numberJobs a int.
     * @throws org.springframework.batch.core.launch.NoSuchJobException if any.
     * @return a {@link java.util.List} object.
     */
    public List<Long> findJobIdsByName(String jobName, int start, int numberJobs) throws NoSuchJobException {
        return jobOperator.getJobInstances(jobName, start, numberJobs);
    }

    /**
     * Restart a job with a given execution id
     *
     * @param executionId a long.
     * @return the new job execution id
     * @throws org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException if any.
     * @throws org.springframework.batch.core.JobParametersInvalidException if any.
     * @throws org.springframework.batch.core.launch.NoSuchJobExecutionException if any.
     * @throws org.springframework.batch.core.repository.JobRestartException if any.
     * @throws org.springframework.batch.core.launch.NoSuchJobException if any.
     */
    public Long restartJob(long executionId) throws JobInstanceAlreadyCompleteException, JobParametersInvalidException, NoSuchJobExecutionException, JobRestartException, NoSuchJobException {
        return jobOperator.restart(executionId);
    }

    /**
     * This method will start a job having a specific name and no job parameters. It will return the generated job id of the job that have been started
     *
     * @param jobName a {@link java.lang.String} object.
     * @throws org.springframework.batch.core.JobParametersInvalidException if any.
     * @return a {@link java.lang.String} object.
     * @throws org.springframework.batch.core.launch.JobInstanceAlreadyExistsException if any.
     * @throws org.springframework.batch.core.launch.NoSuchJobException if any.
     */
    public String startJob(String jobName) throws JobParametersInvalidException, JobInstanceAlreadyExistsException, NoSuchJobException {

        String jobId = jobName+"_"+System.currentTimeMillis();

        if (log.isInfoEnabled()) log.info("Starting job: "+jobName+", job id: "+jobId);

        jobOperator.start(jobName, "MIJobId=" + jobId);

        return jobId;
    }

    /**
     * <p>startJobWithParameters.</p>
     *
     * @param jobName a {@link java.lang.String} object.
     * @param parameters the parameters of type key-value separated by comma
     * @return a {@link java.lang.String} object.
     * @throws org.springframework.batch.core.JobParametersInvalidException if any.
     * @throws org.springframework.batch.core.launch.JobInstanceAlreadyExistsException if any.
     * @throws org.springframework.batch.core.launch.NoSuchJobException if any.
     */
    public String startJobWithParameters(String jobName, String parameters) throws JobParametersInvalidException, JobInstanceAlreadyExistsException, NoSuchJobException {
        String jobId = jobName+"_"+System.currentTimeMillis();

        if (log.isInfoEnabled()) log.info("Starting job: "+jobName+", job id: "+jobId);

        jobOperator.start(jobName, "MIJobId=" + jobId+(parameters != null ? ","+parameters:""));

        return jobId;
    }

    /**
     * The job status of a given job name and id
     *
     * @param jobName a {@link java.lang.String} object.
     * @param jobId a {@link java.lang.String} object.
     * @return null if the job does not exist, the job batchStatus if
     */
    public BatchStatus getJobStatusFor(String jobName, String jobId){

        JobExecution execution = findLastJobExecutionFor(jobName, jobId);

        if (execution == null){
            return null;
        }

        return execution.getStatus();
    }

    /**
     * Stop a give job name and id.
     *
     * @param jobName a {@link java.lang.String} object.
     * @param jobId a {@link java.lang.String} object.
     * @return true if the job does not exist or has been stopped, false otherwise
     * @throws org.springframework.batch.core.launch.NoSuchJobExecutionException if any.
     * @throws org.springframework.batch.core.launch.JobExecutionNotRunningException if any.
     */
    public boolean stopJob(String jobName, String jobId) throws NoSuchJobExecutionException, JobExecutionNotRunningException {
        JobExecution execution = findLastJobExecutionFor(jobName, jobId);

        if (execution == null){
            return true;
        }

        return jobOperator.stop(execution.getId());
    }

    /**
     * Stops a job and returns the jobexecution
     *
     * @param jobName a {@link java.lang.String} object.
     * @param jobId a {@link java.lang.String} object.
     * @throws org.springframework.batch.core.launch.NoSuchJobExecutionException if any.
     * @throws org.springframework.batch.core.repository.JobExecutionAlreadyRunningException if any.
     * @return a {@link org.springframework.batch.core.JobExecution} object.
     */
    public JobExecution abandonJob(String jobName, String jobId) throws NoSuchJobExecutionException, JobExecutionAlreadyRunningException {
        JobExecution execution = findLastJobExecutionFor(jobName, jobId);

        if (execution == null){
            return null;
        }

        return jobOperator.abandon(execution.getId());
    }

    /**
     * <p>Setter for the field <code>jobRepository</code>.</p>
     *
     * @param jobRepository a {@link org.springframework.batch.core.repository.JobRepository} object.
     */
    public void setJobRepository(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    /**
     * <p>Setter for the field <code>jobOperator</code>.</p>
     *
     * @param jobOperator a {@link org.springframework.batch.core.launch.JobOperator} object.
     */
    public void setJobOperator(JobOperator jobOperator) {
        this.jobOperator = jobOperator;
    }

    /**
     * <p>Getter for the field <code>jobRepository</code>.</p>
     *
     * @return a {@link org.springframework.batch.core.repository.JobRepository} object.
     */
    public JobRepository getJobRepository() {
        return jobRepository;
    }

    /**
     * <p>Getter for the field <code>jobOperator</code>.</p>
     *
     * @return a {@link org.springframework.batch.core.launch.JobOperator} object.
     */
    public JobOperator getJobOperator() {
        return jobOperator;
    }
}
