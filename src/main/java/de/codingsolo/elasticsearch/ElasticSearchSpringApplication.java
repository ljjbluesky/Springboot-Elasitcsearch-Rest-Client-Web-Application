package de.codingsolo.elasticsearch;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElasticSearchSpringApplication {

	private static final Logger LOGGER = LogManager.getLogger(ElasticSearchSpringApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ElasticSearchSpringApplication.class, args);
	}
}
