package br.com.raynerweb.arquivos.repository;

import br.com.raynerweb.arquivos.entity.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArquivoRepository extends JpaRepository<Arquivo, Long> {


}
