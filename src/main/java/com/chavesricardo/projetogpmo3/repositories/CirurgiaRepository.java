package com.chavesricardo.projetogpmo3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chavesricardo.projetogpmo3.domain.Cirurgia;

@Repository
public interface CirurgiaRepository extends JpaRepository<Cirurgia, Integer> {

}
