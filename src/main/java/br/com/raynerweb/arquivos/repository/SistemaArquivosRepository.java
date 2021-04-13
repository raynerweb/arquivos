package br.com.raynerweb.arquivos.repository;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Component
public class SistemaArquivosRepository {

    private static final Logger LOG = LoggerFactory.getLogger(SistemaArquivosRepository.class);

    @Value("${arquivo.destino}")
    private String destino;

    public void salvar(MultipartFile multipartFile) {
        LOG.warn("SALVANDO ARQUIVO PADRAO");
        try {
            criarDiretorioDestino();
            File file = new File(destino, Objects.requireNonNull(multipartFile.getOriginalFilename()));
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new IllegalArgumentException("Não foi possivel armazenar o arquivo ");
        }
    }

    public File recuperar(String nomeArquivo) {
        return new File(destino, nomeArquivo);
    }

    private void criarDiretorioDestino() {
        File path = new File(destino);
        if (!path.exists()) {
            if (!path.mkdir()) {
                throw new IllegalArgumentException("Não foi possivel armazenar o arquivo ");
            }
        }
    }

}
