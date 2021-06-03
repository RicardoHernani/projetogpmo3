
package com.chavesricardo.projetogpmo3;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;
import com.chavesricardo.projetogpmo3.domain.Paciente;
import com.chavesricardo.projetogpmo3.domain.Procedimento;
import com.chavesricardo.projetogpmo3.domain.Referencia;
import com.chavesricardo.projetogpmo3.repositories.CirurgiaRepository;
import com.chavesricardo.projetogpmo3.repositories.PacienteRepository;
import com.chavesricardo.projetogpmo3.repositories.ProcedimentoRepository;
import com.chavesricardo.projetogpmo3.repositories.ReferenciaRepository;

@SpringBootApplication
public class Projetogpmo3Application implements CommandLineRunner {
	
	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private CirurgiaRepository cirurgiaRepository;
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	@Autowired
	private ReferenciaRepository referenciaRepository;

	public static void main(String[] args) {
		SpringApplication.run(Projetogpmo3Application.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Referencia ref1 = new Referencia(31102360, "ureterorrenolitotripsia flexível", 41.67, 297.83);
		Referencia ref2 = new Referencia(31102077, "Colocação de duplo J", 20.00, 112.56);
		Referencia ref3 = new Referencia(30908027, "Fístula arteriovenosa", 27.78, 195.07);
		Referencia ref4 = new Referencia(30202094, "Palatoplastia com enxerto ósseo", 33.33, 717.27);

		Paciente pct1 = new Paciente(null, 11111111);
		Paciente pct2 = new Paciente(null, 22222222);

		Cirurgia cir1 = new Cirurgia(null, new java.util.Date(sdf.parse("20/02/2020").getTime()), pct1);
		Cirurgia cir2 = new Cirurgia(null, new java.util.Date(sdf.parse("26/05/2021").getTime()), pct1);
		Cirurgia cir3 = new Cirurgia(null, new java.util.Date(sdf.parse("10/10/2009").getTime()), pct2);
		
		pct1.getCirurgias().addAll(Arrays.asList(cir1, cir2));
		pct2.getCirurgias().addAll(Arrays.asList(cir3));
		
		Procedimento pro1 = new Procedimento(null, 1, 1, ref1, cir1);
		Procedimento pro2 = new Procedimento(null, 2, 1, ref2, cir1);
		Procedimento pro3 = new Procedimento(null, 1, 1, ref3, cir2);
		Procedimento pro4 = new Procedimento(null, 1, 2, ref4, cir3);
		
		referenciaRepository.saveAll(Arrays.asList(ref1, ref2, ref3, ref4));
		pacienteRepository.saveAll(Arrays.asList(pct1, pct2));
		cirurgiaRepository.saveAll(Arrays.asList(cir1, cir2, cir3));
		procedimentoRepository.saveAll(Arrays.asList(pro1, pro2, pro3, pro4));
	
	}

}
