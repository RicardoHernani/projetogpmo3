package com.chavesricardo.projetogpmo3.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.chavesricardo.projetogpmo3.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	@Query(value = "SELECT * FROM USUARIO, PACIENTE, CIRURGIA WHERE USUARIO.ID = PACIENTE.USUARIO_ID AND PACIENTE.ID = CIRURGIA.PACIENTE_ID AND USUARIO.ID= :usuario AND (CIRURGIA.DATA BETWEEN :dataInicial AND :dataFinal)", nativeQuery = true)
	Page<Usuario> search(@Param("usuario") String usuario, @Param("dataInicial") String dataInicial, @Param("dataFinal") String dataFinal, Pageable pageRequest);
	
}


//	@Query(value = "SELECT * FROM USUARIO, PACIENTE, CIRURGIA WHERE (USUARIO.ID = PACIENTE.USUARIO_ID = CIRURGIA.PACIENTE_ID) AND (USUARIO.ID= :usuario) AND (CIRURGIA.DATA BETWEEN :dataInicial AND :dataFinal)", nativeQuery = true)
//	@Query(value = "SELECT * FROM USUARIO, PACIENTE, CIRURGIA WHERE (USUARIO.ID = PACIENTE.USUARIO_ID = CIRURGIA.PACIENTE_ID) AND (USUARIO.ID= :usuario) AND (CIRURGIA.DATA>= :dataInicial AND CIRURGIA.DATA<= :dataFinal)", nativeQuery = true)

/*
SELECT *
FROM  USUARIO, PACIENTE, CIRURGIA
WHERE USUARIO.ID=PACIENTE.USUARIO_ID AND PACIENTE.ID=CIRURGIA.PACIENTE_ID
AND USUARIO.ID=3
AND (CIRURGIA.DATA BETWEEN '2000-02-07' AND '2010-12-24')
*/
