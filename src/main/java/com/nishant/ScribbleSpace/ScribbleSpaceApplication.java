package com.nishant.ScribbleSpace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScribbleSpaceApplication {

	public static void main(String[] args) {
		System.out.println(System.getenv());
		System.out.println(System.getProperties());
		SpringApplication.run(ScribbleSpaceApplication.class, args);
	}

}
