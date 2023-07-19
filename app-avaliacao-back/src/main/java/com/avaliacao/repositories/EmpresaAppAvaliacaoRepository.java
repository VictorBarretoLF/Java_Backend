package com.avaliacao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.avaliacao.entities.EmpresaAppAvaliacao;

@Repository
public interface EmpresaAppAvaliacaoRepository extends JpaRepository<EmpresaAppAvaliacao, Long> {

}
