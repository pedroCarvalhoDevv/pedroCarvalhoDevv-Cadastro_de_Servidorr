package com.cadastro.cadastroServidor.repository;

import com.cadastro.cadastroServidor.model.entity.Lotacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LotacaoRepository extends JpaRepository<Lotacao , Long> {
    boolean existsByDescricao (String descricao);
}
