package goeurotest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import goeurotest.service.GoeuroApp;

@SpringBootApplication
public class Application implements CommandLineRunner {
	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private GoeuroApp goeuroApp;

	public static void main(String args[]) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {
		if (args.length == 0) {
			log.error("city name argument is required ");
			System.out.println("\n\n\n\n ####### city argument is required ");
			System.out.println(" usage example: java -jar gs-consuming-rest-0.1.0.jar berlin");
			System.out.println(" ####### \n\n\n\n");
		} else if (args.length > 1) {
			log.error("too many arguments! only city name argument is required");
			System.out.println("\n\n\n\n ####### too many arguments ");
			System.out.println(" usage example: java -jar gs-consuming-rest-0.1.0.jar berlin");
			System.out.println(" ####### \n\n\n\n");
		} else {
			String cityName = args[0];
			goeuroApp.runApp(cityName);
		}
	}
}