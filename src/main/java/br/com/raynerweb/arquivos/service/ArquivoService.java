package br.com.raynerweb.arquivos.service;

import br.com.raynerweb.arquivos.dto.ArquivoResponse;
import br.com.raynerweb.arquivos.entity.Arquivo;
import br.com.raynerweb.arquivos.entity.TipoArquivo;
import br.com.raynerweb.arquivos.repository.ArquivoFisicoRepository;
import br.com.raynerweb.arquivos.repository.ArquivoRepository;
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

@Service
public class ArquivoService {

    private static final Logger LOG = LoggerFactory.getLogger(ArquivoService.class);

    @Autowired
    private ArquivoRepository arquivoRepository;

    @Autowired
    private ArquivoFisicoRepository arquivoFisicoRepository;

    /**
     * READ
     * (S) OLID :: Single Responsibility Principle
     * O metodo de recuperar tipoArquivo pertence à service TipoArquivo
     * e sua regra de recuperacao nao deve estar aqui
     */
    @Autowired
    private TipoArquivoService tipoArquivoService;

    @Transactional
    public void salvar(MultipartFile[] files) {
        for (MultipartFile file : files) {
            this.salvar(file);
        }
    }

    public ArquivoResponse recuperar(Long idArquivo) {
        try {
            Arquivo arquivo = arquivoRepository.findById(idArquivo).orElseThrow(() -> new IllegalArgumentException("Arquivo não encontrado"));
            String caminhoArquivo = arquivo.getTipoArquivo().getCaminhoArmazenamento() + arquivo.getNomeArquivo();
            return new ArquivoResponse(FileUtils.readFileToByteArray(new File(caminhoArquivo)), arquivo.getNomeArquivo(), arquivo.getContentType());
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException("Arquivo não encontrado");
        } catch (IOException e) {
            throw new IllegalArgumentException("Falha ao carrefar arquivo");
        }
    }

    private void salvar(MultipartFile multipartFile) {
        TipoArquivo tipoArquivo = tipoArquivoService.getTipoArquivo(multipartFile.getContentType());
        arquivoFisicoRepository.salvar(multipartFile, tipoArquivo.getCaminhoArmazenamento());
        salvarArquivo(multipartFile, tipoArquivo);
    }

    private void salvarArquivo(MultipartFile multipartFile, TipoArquivo tipoArquivo) {
        Arquivo arquivo = new Arquivo();
        arquivo.setContentType(multipartFile.getContentType());
        arquivo.setDataHoraUpload(new Date());
        arquivo.setNomeArquivo(multipartFile.getOriginalFilename());
        arquivo.setTamanho(multipartFile.getSize());
        arquivo.setTipoArquivo(tipoArquivo);
        arquivoRepository.save(arquivo);
    }

}
