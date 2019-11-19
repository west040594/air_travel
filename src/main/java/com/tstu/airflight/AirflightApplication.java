package com.tstu.airflight;

import com.tstu.airflight.utils.DataBaseInit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class AirflightApplication {

	@Autowired
	private DataBaseInit dataBaseInit;

	public static void main(String[] args) {
		SpringApplication.run(AirflightApplication.class, args);
	}

	@PostConstruct
	private void postConstruct() {
		dataBaseInit.init();
	}
}
