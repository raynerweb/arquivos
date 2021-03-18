package br.com.raynerweb.arquivos.repository;

import br.com.raynerweb.arquivos.entity.TipoArquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoArquivoRepository extends JpaRepository<TipoArquivo, Long> {
}
