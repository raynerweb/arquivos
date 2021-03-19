package br.com.raynerweb.arquivos.service;

import br.com.raynerweb.arquivos.dto.ArquivoResponse;
import br.com.raynerweb.arquivos.entity.Arquivo;
import br.com.raynerweb.arquivos.entity.TipoArquivo;
import br.com.raynerweb.arquivos.repository.ArquivoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArquivoServiceTest {

    @Mock
    private ArquivoRepository repository;

    @InjectMocks
    private ArquivoService service;

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
