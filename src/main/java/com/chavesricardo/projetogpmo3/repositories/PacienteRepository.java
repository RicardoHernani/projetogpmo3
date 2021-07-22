package com.chavesricardo.projetogpmo3.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chavesricardo.projetogpmo3.domain.Paciente;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

	@Query(value = "SELECT * FROM PACIENTE, CIRURGIA WHERE CIRURGIA.PACIENTE_ID = PACIENTE.ID AND (CIRURGIA.DATA>= :dataInicial AND CIRURGIA.DATA<= :dataFinal)", nativeQuery = true)
	Page<Paciente> search(@Param("dataInicial") String dataInicial, @Param("dataFinal") String dataFinal, Pageable pageRequest);
}




