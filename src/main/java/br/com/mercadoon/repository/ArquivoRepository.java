package br.com.mercadoon.repository;

import br.com.mercadoon.entity.Arquivo;
import br.com.mercadoon.entity.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository  extends JpaRepository<Arquivo, Long> {
}
