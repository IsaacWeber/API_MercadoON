package br.com.mercadoon.api.repository;

import br.com.mercadoon.api.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaoRepository extends JpaRepository<Cartao, Long> {
    List<Cartao> findAllByClienteId(Long id);
}
