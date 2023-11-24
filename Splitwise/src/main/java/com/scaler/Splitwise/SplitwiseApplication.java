package com.scaler.Splitwise;

import com.scaler.Splitwise.service.InitializeServices.InitializeService;
import com.scaler.Splitwise.service.InitializeServices.InitializeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SplitwiseApplication implements CommandLineRunner {

	@Autowired
	private InitializeService initializeService;

	public static void main(String[] args) {
		SpringApplication.run(SplitwiseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		initializeService.init();
	}

	// CommandLineRunner overrides the run method
	// when the main is called it also calls the run method
	// no need to call the initialization methods
}
