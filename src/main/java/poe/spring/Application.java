package poe.spring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	private static Logger log = LoggerFactory.getLogger(Application.class);

	public static void main(String[] args) {
		log.info("Démarrage de l'application");
		log.trace("trace log");
		log.debug("debug log");
		log.info("info log");
		log.warn("warn log");
		log.error("error log");

		SpringApplication.run(Application.class, args);
	}
}