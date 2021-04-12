package br.com.raynerweb.arquivos.service;

import br.com.raynerweb.arquivos.dto.ArquivoResponse;
import br.com.raynerweb.arquivos.entity.Arquivo;
import br.com.raynerweb.arquivos.repository.ArquivoRepository;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArquivoServiceTest {

    @Mock
    private ArquivoRepository repository;

    @Mock
    private ArquivoBinarioRepository arquivoBinarioRepository;

    @InjectMocks
    private ArquivoService service;

    @Test
    public void deveSalvarArquivoSuportado() {
        String destino = "src/test/resources/";
        when(arquivoBinarioRepository.getDestino(anyString())).thenReturn(destino);

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
    public void arquivoInfectadoNaoPodeSerSalvo() {
        String destino = "src/test/resources/";
        when(arquivoBinarioRepository.getDestino(anyString())).thenReturn(destino);

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
        String destino = "src/test/resources/";
        when(arquivoBinarioRepository.getDestino(anyString())).thenReturn(destino);

        Arquivo arquivo = getArquivo();
        when(repository.findById(10L)).thenReturn(java.util.Optional.of(arquivo));
        ArquivoResponse arquivoResponse = service.recuperar(10L);
        assertNotNull(arquivoResponse);
    }

    private Arquivo getArquivo() {
        Arquivo arquivo = new Arquivo();
        arquivo.setNomeArquivo("arquivo.txt");
        arquivo.setContentType("plain/txt");
        arquivo.setTamanho(123L);
        arquivo.setDataHoraUpload(new Date());
        arquivo.setId(1L);
        return arquivo;
    }

}
