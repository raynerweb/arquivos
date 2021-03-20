package br.com.raynerweb.arquivos.service;

import br.com.raynerweb.arquivos.dto.ArquivoResponse;
import br.com.raynerweb.arquivos.entity.Arquivo;
import br.com.raynerweb.arquivos.entity.TipoArquivo;
import br.com.raynerweb.arquivos.repository.ArquivoRepository;
import br.com.raynerweb.arquivos.repository.TipoArquivoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArquivoServiceTest {

    @Mock
    private ArquivoRepository repository;

    @Mock
    private TipoArquivoRepository tipoArquivoRepository;

    @InjectMocks
    private ArquivoService service;

    @Test
    public void deveSalvarArquivoSuportado() {
        TipoArquivo tipoArquivo = new TipoArquivo();
        tipoArquivo.setId(1L);
        tipoArquivo.setDescricao("Descriçao");
        tipoArquivo.setCaminhoArmazenamento("/tmp/txt");
        tipoArquivo.setContentType("plain/text");
        when(tipoArquivoRepository.findByContentType(any())).thenReturn(java.util.Optional.of(tipoArquivo));

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        service.salvar(new MultipartFile[]{file});
    }

    @Test(expected = IllegalArgumentException.class)
    public void arquivoNaoSuportadoNaoPodeSerSalvo() {
        when(tipoArquivoRepository.findByContentType(any())).thenReturn(java.util.Optional.empty());

        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        service.salvar(new MultipartFile[]{file});
    }

    @Test
    public void deveRecuperarArquivo() {
        Arquivo arquivo = getArquivo();
        when(repository.findById(10L)).thenReturn(java.util.Optional.of(arquivo));
        ArquivoResponse arquivoResponse = service.recuperar(10L);
        assertNotNull(arquivoResponse);
    }

    private Arquivo getArquivo() {
        TipoArquivo tipoArquivo = new TipoArquivo();
        tipoArquivo.setCaminhoArmazenamento("src/test/resources/");
        tipoArquivo.setDescricao("Arquivo de Texto");
        tipoArquivo.setId(1L);

        Arquivo arquivo = new Arquivo();
        arquivo.setTipoArquivo(tipoArquivo);
        arquivo.setNomeArquivo("arquivo.txt");
        arquivo.setContentType("plain/txt");
        arquivo.setTamanho(123L);
        arquivo.setDataHoraUpload(new Date());
        arquivo.setId(1L);
        return arquivo;
    }

}
