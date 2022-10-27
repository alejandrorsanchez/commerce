package com.example.demo;

import com.example.demo.infraestructure.adapter_h2db.seeder.H2dbSeeder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext app = SpringApplication.run(DemoApplication.class, args);
		H2dbSeeder seederDBService = (H2dbSeeder) app.getBean("h2dbSeeder");
		seederDBService.populateDB();
	}

}
