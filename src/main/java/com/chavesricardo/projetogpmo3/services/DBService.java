package com.chavesricardo.projetogpmo3.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;
import com.chavesricardo.projetogpmo3.domain.Paciente;
import com.chavesricardo.projetogpmo3.domain.Procedimento;
import com.chavesricardo.projetogpmo3.domain.Referencia;
import com.chavesricardo.projetogpmo3.domain.Usuario;
import com.chavesricardo.projetogpmo3.repositories.CirurgiaRepository;
import com.chavesricardo.projetogpmo3.repositories.PacienteRepository;
import com.chavesricardo.projetogpmo3.repositories.ProcedimentoRepository;
import com.chavesricardo.projetogpmo3.repositories.ReferenciaRepository;
import com.chavesricardo.projetogpmo3.repositories.UsuarioRepository;

@Service
public class DBService {

	@Autowired
	private PacienteRepository pacienteRepository;
	@Autowired
	private CirurgiaRepository cirurgiaRepository;
	@Autowired
	private ProcedimentoRepository procedimentoRepository;
	@Autowired
	private ReferenciaRepository referenciaRepository;
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public void instantiateTestDatabase() throws ParseException {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		Referencia ref1 = new Referencia(31102360, "ureterorrenolitotripsia flexível", 41.67, 297.83);
		Referencia ref2 = new Referencia(31102077, "Colocação de duplo J", 20.00, 112.56);
		Referencia ref3 = new Referencia(30908027, "Fístula arteriovenosa", 27.78, 195.07);
		Referencia ref4 = new Referencia(30202094, "Palatoplastia com enxerto ósseo", 33.33, 717.27);
		Referencia ref5 = new Referencia(30715016, "Atrodese da coluna com instrumentação", 33.33, 364.76);
		Referencia ref6 = new Referencia(31003567, "Tumor anorretal", 16.67, 56.07);
		Referencia ref7 = new Referencia(31003141, "Cirurgia de acesso posterior", 13.33, 240.24);
		Referencia ref8 = new Referencia(30729017, "Amputação ao nível do pé", 33.33, 103.02);
		Referencia ref9 = new Referencia(31201113, "Prostatovesiculectomia radical", 41.67, 443.25);
		Referencia ref10 = new Referencia(30914070, "Linfadenectomia pélvica", 33.33, 249.79);

		Usuario uso1 = new Usuario(null, "Ricardo Hernani", "ricardohernani@yahoo.com.br", "12345");
		Usuario uso2 = new Usuario(null, "Débora Fóscolo", "deborafoscolo@gmail.com", "123456");
		Usuario uso3 = new Usuario(null, "Ana Garcia", "anagarcia@gmail.com", "1234567");
		Usuario uso4 = new Usuario(null, "Vanessa Cristina", "vanessapatricia@gmail.com", "12345678");
		Usuario uso5 = new Usuario(null, "Renata Chaves", "renatachaves@gmail.com", "123456789");	
		
		Paciente pct1 = new Paciente(null, 11111111, uso1);
		Paciente pct2 = new Paciente(null, 22222222, uso2);
		Paciente pct3 = new Paciente(null, 33333333, uso2);
		Paciente pct4 = new Paciente(null, 44444444, uso3);
		Paciente pct5 = new Paciente(null, 55555555, uso3);
		Paciente pct6 = new Paciente(null, 66666666, uso3);
		Paciente pct7 = new Paciente(null, 77777777, uso4);
		
		uso1.getPacientes().addAll(Arrays.asList(pct1));
		uso2.getPacientes().addAll(Arrays.asList(pct2, pct3));
		uso3.getPacientes().addAll(Arrays.asList(pct4, pct5, pct6));
		uso5.getPacientes().addAll(Arrays.asList(pct7));

		Cirurgia cir1 = new Cirurgia(null, new java.util.Date(sdf.parse("20/02/2020").getTime()), pct1);
		Cirurgia cir2 = new Cirurgia(null, new java.util.Date(sdf.parse("26/05/2021").getTime()), pct1);
		Cirurgia cir3 = new Cirurgia(null, new java.util.Date(sdf.parse("10/10/2009").getTime()), pct2);
		Cirurgia cir4 = new Cirurgia(null, new java.util.Date(sdf.parse("15/08/2013").getTime()), pct3);
		Cirurgia cir5 = new Cirurgia(null, new java.util.Date(sdf.parse("07/02/2000").getTime()), pct4);
		Cirurgia cir6 = new Cirurgia(null, new java.util.Date(sdf.parse("07/02/2000").getTime()), pct4);
		Cirurgia cir7 = new Cirurgia(null, new java.util.Date(sdf.parse("24/12/2010").getTime()), pct4);
		Cirurgia cir8 = new Cirurgia(null, new java.util.Date(sdf.parse("13/05/1998").getTime()), pct5);
		Cirurgia cir9 = new Cirurgia(null, new java.util.Date(sdf.parse("27/06/2019").getTime()), pct6);

		pct1.getCirurgias().addAll(Arrays.asList(cir1, cir2));
		pct2.getCirurgias().addAll(Arrays.asList(cir3));
		pct3.getCirurgias().addAll(Arrays.asList(cir4));
		pct4.getCirurgias().addAll(Arrays.asList(cir5, cir6, cir7));
		pct5.getCirurgias().addAll(Arrays.asList(cir8));
		pct6.getCirurgias().addAll(Arrays.asList(cir9));
		
		
		Procedimento pro1 = new Procedimento(null, 1, 1, ref1, cir1);
		Procedimento pro2 = new Procedimento(null, 2, 1, ref2, cir1);
		Procedimento pro3 = new Procedimento(null, 1, 1, ref3, cir2);
		Procedimento pro4 = new Procedimento(null, 1, 2, ref4, cir3);
		Procedimento pro5 = new Procedimento(null, 1, 1, ref5, cir4);
		Procedimento pro6 = new Procedimento(null, 1, 2, ref9, cir5);
		Procedimento pro7 = new Procedimento(null, 2, 2, ref10, cir6);
		Procedimento pro8 = new Procedimento(null, 1, 1, ref3, cir7);
		Procedimento pro9 = new Procedimento(null, 1, 2, ref6, cir8);
		Procedimento pro10 = new Procedimento(null, 1, 2, ref8, cir9);

		referenciaRepository.saveAll(Arrays.asList(ref1, ref2, ref3, ref4, ref5, ref6, ref7, ref8, ref9, ref10));
		usuarioRepository.saveAll(Arrays.asList(uso1, uso2, uso3, uso4, uso5));
		pacienteRepository.saveAll(Arrays.asList(pct1, pct2, pct3, pct4, pct5, pct6, pct7));
		cirurgiaRepository.saveAll(Arrays.asList(cir1, cir2, cir3, cir4, cir5, cir6, cir7, cir8, cir9));
		procedimentoRepository.saveAll(Arrays.asList(pro1, pro2, pro3, pro4, pro5, pro6, pro7, pro8, pro9, pro10));
	
	}
	
}
