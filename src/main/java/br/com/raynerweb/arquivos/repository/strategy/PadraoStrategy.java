package br.com.raynerweb.arquivos.repository.strategy;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class PadraoStrategy implements ArquivoBinarioStrategy {

    private static final Logger LOG = LoggerFactory.getLogger(PadraoStrategy.class);

    @Override
    public void salvar(MultipartFile multipartFile, String destino) {
        LOG.warn("SALVANDO ARQUIVO PADRAO");
        try {
            criarDiretorioDestino(destino);

            File file = new File(destino, Objects.requireNonNull(multipartFile.getOriginalFilename()));
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new IllegalArgumentException("Não foi possivel armazenar o arquivo ");
        }
    }

    private void criarDiretorioDestino(String destino) {
        File path = new File(destino);
        if (!path.exists()) {
            if (!path.mkdir()) {
                throw new IllegalArgumentException("Não foi possivel armazenar o arquivo ");
            }
        }
    }

}
