package com.chavesricardo.projetogpmo3.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chavesricardo.projetogpmo3.domain.Referencia;

@Repository
public interface ReferenciaRepository extends JpaRepository<Referencia, Integer> {
	Page<Referencia> findByDescricaoContainingIgnoreCase(String descricao, Pageable PageRequest);
}

/*Posso fazer usando o jpql também
@Query("SELECT obj FROM Referencia obj WHERE obj.descricao LIKE %:descricao%")
Page<Referencia> search(@Param("descricao") String descricao, Pageable PageRequest);
*/
