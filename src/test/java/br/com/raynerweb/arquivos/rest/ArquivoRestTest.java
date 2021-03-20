package br.com.raynerweb.arquivos.rest;

import br.com.raynerweb.arquivos.dto.ArquivoResponse;
import br.com.raynerweb.arquivos.service.ArquivoService;
import org.apache.commons.io.FileUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.io.File;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArquivoRestTest {

    private MockMvc mockMvc;

    @InjectMocks
    private ArquivoRest arquivoRest;

    @Mock
    private ArquivoService service;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(arquivoRest).build();
    }

    @Test
    public void deveSalvarUmArquivo() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );
        doNothing().when(service).salvar(any());
        mockMvc.perform(multipart("/arquivos").file("files", file.getBytes()))
                .andExpect(status().isOk());
    }

    @Test
    public void deveRecuperarUmArquivo() throws Exception {
        String nomeArquivo = "nomeArquivo.txt";
        ArquivoResponse arquivoResponse = new ArquivoResponse(FileUtils.readFileToByteArray(new File("src/test/resources/arquivo.txt")), nomeArquivo, "text/txt");
        when(service.recuperar(anyLong())).thenReturn(arquivoResponse);
        mockMvc.perform(get("/arquivos/10"))
                .andExpect(status().isOk())
                .andExpect(header().string("Content-Disposition", "attachment; filename=" +
                        nomeArquivo));
    }


}
