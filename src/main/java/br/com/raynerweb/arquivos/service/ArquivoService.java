package br.com.raynerweb.arquivos.service;

import br.com.raynerweb.arquivos.component.AntivirusComponent;
import br.com.raynerweb.arquivos.dto.ArquivoResponse;
import br.com.raynerweb.arquivos.entity.Arquivo;
import br.com.raynerweb.arquivos.repository.ArquivoRepository;
import br.com.raynerweb.arquivos.repository.SistemaArquivosRepository;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Service
public class ArquivoService {

    private static final Logger LOG = LoggerFactory.getLogger(ArquivoService.class);

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private SistemaArquivosRepository sistemaArquivosRepository;

    @Autowired
    private AntivirusComponent antivirus;

    public void salvar(MultipartFile[] files) {
        for (MultipartFile file : files) {
            this.salvar(file);
        }
    }

    public List<Arquivo> recuperarTodos() {
        return arquivoRepository.findAll();
    }

    public ArquivoResponse recuperar(Long idArquivo) {
        try {
            Arquivo arquivo = arquivoRepository.findById(idArquivo).orElseThrow(() -> new IllegalArgumentException("Arquivo não encontrado"));
            File arquivoFisico = sistemaArquivosRepository.recuperar(arquivo.getNomeArquivo());
            return new ArquivoResponse(FileUtils.readFileToByteArray(arquivoFisico), arquivo.getNomeArquivo(), arquivo.getContentType());
        } catch (FileNotFoundException e) {
            String message = "Arquivo não encontrado";
            LOG.error(message);
            throw new IllegalArgumentException(message);
        } catch (IOException e) {
            String message = "Falha ao carrefar arquivo";
            LOG.error(message);
            throw new IllegalArgumentException(message);
        }
    }

    private void salvar(MultipartFile multipartFile) {
        antivirus.verifyMultipartFile(multipartFile);
//        sistemaArquivosRepository.salvar(multipartFile);
        salvarArquivo(multipartFile);
    }

    private void salvarArquivo(MultipartFile multipartFile) {
        Arquivo arquivo = new Arquivo();
        arquivo.setContentType(multipartFile.getContentType());
        arquivo.setDataHoraUpload(new Date());
        arquivo.setNomeArquivo(multipartFile.getOriginalFilename());
        arquivo.setTamanho(multipartFile.getSize());
        arquivoRepository.save(arquivo);
    }

}
