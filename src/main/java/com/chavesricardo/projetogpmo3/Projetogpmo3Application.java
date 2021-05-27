package com.chavesricardo.projetogpmo3;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;
import com.chavesricardo.projetogpmo3.domain.Paciente;
import com.chavesricardo.projetogpmo3.repositories.CirurgiaRepository;
import com.chavesricardo.projetogpmo3.repositories.PacienteRepository;

@SpringBootApplication
public class Projetogpmo3Application implements CommandLineRunner {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private CirurgiaRepository cirurgiaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Projetogpmo3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Paciente pct1 = new Paciente(null, 11111111);
		Paciente pct2 = new Paciente(null, 22222222);

		Cirurgia cir1 = new Cirurgia(null, new java.util.Date(sdf.parse("20/02/2020").getTime()), pct1);
		Cirurgia cir2 = new Cirurgia(null, new java.util.Date(sdf.parse("26/05/2021").getTime()), pct1);
		Cirurgia cir3 = new Cirurgia(null, new java.util.Date(sdf.parse("10/10/2009").getTime()), pct2);
		
		pct1.getCirurgias().addAll(Arrays.asList(cir1, cir2));
		pct2.getCirurgias().addAll(Arrays.asList(cir3));
		
		pacienteRepository.saveAll(Arrays.asList(pct1, pct2));
		cirurgiaRepository.saveAll(Arrays.asList(cir1, cir2, cir3));

	}

}
