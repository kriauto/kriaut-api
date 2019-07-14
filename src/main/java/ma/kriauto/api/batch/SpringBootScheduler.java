package ma.kriauto.api.batch;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;

import lombok.extern.slf4j.Slf4j;
import ma.kriauto.api.service.CarService;

@Configuration
@EnableScheduling
@Slf4j
public class SpringBootScheduler {
	
	@Autowired
    private CarService carService;
	
	@Bean
	public TaskScheduler taskScheduler() {
	    return new ConcurrentTaskScheduler();
	}
	
	@Scheduled(fixedDelay = 120000)
    public void calculateDailyDistance() throws ParseException {
       log.info("==> Start calculateDailyDistance");
       carService.calculateDailyDistance();
       log.info("==> Finished calculateDailyDistance");
    }
	
	@Scheduled(cron = "00 00 00 * * *")
    public void initDailyDistance() throws ParseException {
       log.info("==> Start calculateDailyDistance");
       carService.initDailyDistance();
       log.info("==> Finished calculateDailyDistance");
    }
	
	@Scheduled(cron = "00 00 05 * * *")
    public void calculateTotalDistance() throws ParseException {
       log.info("==> Start calculateDailyDistance");
       carService.calculateTotalDistance();
       log.info("==> Finished calculateDailyDistance");
    }

}
