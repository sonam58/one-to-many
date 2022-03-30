package com.jpa.onetomanymapping.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class SayHelloTask {
	 private static final Logger LOG = LoggerFactory.getLogger(SayHelloTask.class);

//	    @Scheduled(fixedRate = 1000)
//	    public void sayHello(){
//	        LOG.info("Hello from our simple scheduled method");
//	    }
	    @Scheduled(cron = "0 * * * * ?")
	    public void scheduleTaskWithCronExpression() {
	        LOG.info("Example to show how cron expression can be used");
	    }
}
