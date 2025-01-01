package br.com.mercadoon.api.repository;

import br.com.mercadoon.api.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository  extends JpaRepository<Arquivo, Long> {
}
