package br.com.raynerweb.arquivos.repository.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public interface ArquivoBinarioStrategy {

    Logger LOG = LoggerFactory.getLogger(ArquivoBinarioStrategy.class);

    void salvar(MultipartFile multipartFile, String destino);

}
