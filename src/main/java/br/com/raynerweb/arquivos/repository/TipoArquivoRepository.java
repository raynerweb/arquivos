package br.com.raynerweb.arquivos.repository;

import br.com.raynerweb.arquivos.entity.TipoArquivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TipoArquivoRepository extends JpaRepository<TipoArquivo, Long> {

    Optional<TipoArquivo> findByContentType(String contentType);

}
