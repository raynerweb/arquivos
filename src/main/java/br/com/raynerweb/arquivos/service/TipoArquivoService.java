package br.com.raynerweb.arquivos.service;

import br.com.raynerweb.arquivos.entity.TipoArquivo;
import br.com.raynerweb.arquivos.repository.TipoArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TipoArquivoService {

    @Autowired
    private TipoArquivoRepository repository;

    public TipoArquivo getTipoArquivo(String contentType) {
        return repository.findByContentType(contentType)
                .orElseThrow(() -> new IllegalArgumentException("Tipo de arquivo não mapeado"));
    }

}
