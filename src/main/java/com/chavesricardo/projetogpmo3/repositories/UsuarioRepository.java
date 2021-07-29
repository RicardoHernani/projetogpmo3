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

	@Query(value = "SELECT * FROM USUARIO, PACIENTE WHERE PACIENTE.USUARIO_ID = USUARIO.ID AND (PACIENTE.CIRURGIA.DATA>= :dataInicial AND PACIENTE.CIRURGIA.DATA<= :dataFinal)", nativeQuery = true)
	Page<Usuario> search(@Param("dataInicial") String dataInicial, @Param("dataFinal") String dataFinal, Pageable pageRequest);
	
}




