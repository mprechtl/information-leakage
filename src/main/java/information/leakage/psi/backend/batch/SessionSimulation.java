
package information.leakage.psi.backend.batch;

import information.leakage.psi.Constants;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Singleton;
import javax.ejb.Startup;


/**
 *
 * @author Mike Prechtl
 */
@Singleton
@Startup
public class SessionSimulation {

    private final ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @PostConstruct
    public void init() {
	final Runnable batchJob = () -> {
	    BatchRuntime.getJobOperator().start(Constants.Batchlet.JOB_XML_NAME, new Properties());
	};

	scheduler.scheduleAtFixedRate(batchJob, 30, Constants.Batchlet.BATCHLET_INTERVAL, TimeUnit.SECONDS);
    }

}
