package com.talislingua.dictionary_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DictionaryServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DictionaryServiceApplication.class, args);
	}

}
