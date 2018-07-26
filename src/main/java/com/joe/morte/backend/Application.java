package com.joe.morte.backend;

import com.joe.morte.backend.implementation.AzureEmotionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

	@Autowired
 	private static AzureEmotionImpl azureTest;

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);

		Object testMsg = context.getBean(AzureEmotionImpl.class).demo();
		System.out.println(testMsg);
	}

}
