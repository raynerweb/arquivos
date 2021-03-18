package br.com.raynerweb.arquivos.service;

import br.com.raynerweb.arquivos.entity.Arquivo;
import br.com.raynerweb.arquivos.entity.TipoArquivo;
import br.com.raynerweb.arquivos.repository.ArquivoRepository;
import br.com.raynerweb.arquivos.repository.TipoArquivoRepository;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

@Service
public class ArquivoService {

    private static final Logger LOG = LoggerFactory.getLogger(ArquivoService.class);

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private TipoArquivoRepository tipoArquivoRepository;

    @Transactional
    public void salvar(MultipartFile[] files, Long codigoTipoArquivo) {
        for (MultipartFile file : files) {
            this.salvar(file, codigoTipoArquivo);
        }
    }

    public ArquivoResponse recuperar(Long idArquivo) {
        try {
            Arquivo arquivo = arquivoRepository.findById(idArquivo).orElseThrow(() -> new IllegalArgumentException("Arquivo não encontrado"));
            String caminhoArquivo = arquivo.getTipoArquivo().getCaminhoArmazenamento() + arquivo.getNomeArquivo();
            return new ArquivoResponse(FileUtils.readFileToByteArray(new File(caminhoArquivo)), arquivo);
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Arquivo não encontrado");
        } catch (IOException e) {
            throw new IllegalArgumentException("Falha ao carrefar arquivo");
        }
    }

    private Arquivo salvar(MultipartFile multipartFile, Long codigoTipoArquivo) {
        try {
            TipoArquivo tipoArquivo = tipoArquivoRepository.findById(codigoTipoArquivo)
                    .orElseThrow(() -> new IllegalArgumentException("Código de tipo de arquivo inválido"));
            File file = new File(tipoArquivo.getCaminhoArmazenamento(), Objects.requireNonNull(multipartFile.getOriginalFilename()));
            FileUtils.writeByteArrayToFile(file, multipartFile.getBytes());
            Arquivo arquivo = new Arquivo();
            arquivo.setContentType(multipartFile.getContentType());
            arquivo.setDataHoraUpload(new Date());
            arquivo.setNomeArquivo(multipartFile.getOriginalFilename());
            arquivo.setTamanho(multipartFile.getSize());
            arquivo.setTipoArquivo(tipoArquivo);
            arquivoRepository.save(arquivo);
            return arquivo;
        } catch (IOException e) {
            LOG.error(e.getMessage());
            throw new IllegalArgumentException("Não foi possivel armazenar o arquivo ");
        }
    }

    public class ArquivoResponse {
        private byte[] bytes;
        private Arquivo arquivo;

        public ArquivoResponse(byte[] bytes, Arquivo arquivo) {
            this.bytes = bytes;
            this.arquivo = arquivo;
        }

        public byte[] getBytes() {
            return bytes;
        }

        public void setBytes(byte[] bytes) {
            this.bytes = bytes;
        }

        public Arquivo getArquivo() {
            return arquivo;
        }

        public void setArquivo(Arquivo arquivo) {
            this.arquivo = arquivo;
        }
    }
}
