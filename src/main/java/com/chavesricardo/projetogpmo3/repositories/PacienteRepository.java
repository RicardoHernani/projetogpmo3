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

	@Query(value = "SELECT * FROM PACIENTE, CIRURGIA WHERE CIRURGIA.PACIENTE_ID = PACIENTE.ID AND (CIRURGIA.DATA>= :data AND CIRURGIA.DATA<= :data1)", nativeQuery = true)
	Page<Paciente> search(@Param("data") String data, @Param("data1") String data1, Pageable pageRequest);
}




