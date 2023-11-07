package com.example.demo;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/input")
	public String input(@RequestParam(value = "seed", defaultValue = "0") String seed,
			@RequestParam(value = "line", defaultValue = "1") String line) {
		InputGenerator inputGenerator = new InputGenerator(Integer.parseInt(seed), Integer.parseInt(line));
		String input = inputGenerator.generate();

		return input;
	}

	@GetMapping("/output")
	public String output(@RequestParam(value = "seed", defaultValue = "0") String seed,
			@RequestParam(value = "line", defaultValue = "0") String line) {
		OutputGenerator outputGenerator = new OutputGenerator(Integer.parseInt(seed), Integer.parseInt(line));
		String output = outputGenerator.generate();
		return output;
	}
}