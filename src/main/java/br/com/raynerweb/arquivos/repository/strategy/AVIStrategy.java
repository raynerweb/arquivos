package br.com.raynerweb.arquivos.repository.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component("video/x-msvideo")
public class AVIStrategy extends PadraoStrategy implements ArquivoBinarioStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(AVIStrategy.class);

    @Override
    public void salvar(MultipartFile multipartFile, String destino) {
        LOG.warn("Salvando arquivo do TIPO AVI");
        super.salvar(multipartFile, destino);
    }

}
