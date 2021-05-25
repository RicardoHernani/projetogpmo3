package com.chavesricardo.projetogpmo3;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chavesricardo.projetogpmo3.domain.Paciente;
import com.chavesricardo.projetogpmo3.repositories.PacienteRepository;

@SpringBootApplication
public class Projetogpmo3Application implements CommandLineRunner {
	
	@Autowired
	private PacienteRepository pacienteRepository;

	public static void main(String[] args) {
		SpringApplication.run(Projetogpmo3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Paciente pct1 = new Paciente(null, 11111111);
		Paciente pct2 = new Paciente(null, 22222222);
		
		pacienteRepository.saveAll(Arrays.asList(pct1, pct2));
		
	}

}
