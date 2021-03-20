package br.com.raynerweb.arquivos.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public interface ArquivoFisicoRepository {

    Logger LOG = LoggerFactory.getLogger(ArquivoFisicoRepository.class);

    void salvar(MultipartFile multipartFile, String destino);

}
