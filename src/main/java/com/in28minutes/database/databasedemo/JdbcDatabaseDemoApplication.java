package com.in28minutes.database.databasedemo;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import com.in28minutes.database.databasedemo.entity.Person;
import com.in28minutes.database.databasedemo.jdbc.PersonDAO;

// @SpringBootApplication
public class JdbcDatabaseDemoApplication implements CommandLineRunner {

	@Autowired
	private PersonDAO personService;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	public static void main(String[] args) {
		SpringApplication.run(JdbcDatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("----------------- JDBC ----------------");
		logger.info("All people: {}", personService.findAll());
		logger.info("Person: {}", personService.findById(10001));

		logger.info("Delete Person with id 10001");
		personService.deleteById(10001);
		logger.info("All people: {}", personService.findAll());

		logger.info("Delete Person with ids: 10002 or 10003");
		personService.deleteByMultipleIds(Arrays.asList(10002, 10003));
		logger.info("All people: {}", personService.findAll());

		Person ryan = new Person(10004, "Ryan", "Hout Bay", new Timestamp(new Date().getTime()));

		logger.info("Insert Person");
		personService.insert(ryan);
		logger.info("All people: {}", personService.findAll());

		logger.info("Update Person: Ryan");
		ryan.setName("Ryan Johnson");
		ryan.setLocation("Muizenberg");
		personService.update(ryan);
		logger.info("All people: {}", personService.findAll());
	}
}
