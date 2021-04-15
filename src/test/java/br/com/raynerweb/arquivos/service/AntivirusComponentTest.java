package br.com.raynerweb.arquivos.service;

import br.com.raynerweb.arquivos.component.AntivirusComponent;
import br.com.raynerweb.arquivos.exception.VirusDetectedException;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class AntivirusComponentTest {

    @Test(expected = VirusDetectedException.class)
    public void deveLancarVirusDetectedException() throws IOException {
        File virus = new File("src/test/resources/virus.txt");
        FileInputStream fis = new FileInputStream(virus);

        AntivirusComponent component = new AntivirusComponent();
        component.verifiy(fis.readAllBytes());
    }

    @Test
    public void devePassarPelaValidacao() throws IOException {
        File arquivo = new File("src/test/resources/arquivo.txt");
        FileInputStream fis = new FileInputStream(arquivo);
        AntivirusComponent component = new AntivirusComponent();
        component.verifiy(fis.readAllBytes());
    }

    @Test(expected = VirusDetectedException.class)
    public void deveLancarVirusDetectedExceptionMultipartFile() throws IOException {
        File virus = new File("src/test/resources/virus.txt");
        FileInputStream fis = new FileInputStream(virus);
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "virus.txt",
                MediaType.TEXT_PLAIN_VALUE,
                fis.readAllBytes()
        );
        AntivirusComponent component = new AntivirusComponent();
        component.verifyMultipartFile(file);
    }

    @Test
    public void devePassarPelaValidacaoMultipartFile() throws IOException {
        File arquivo = new File("src/test/resources/arquivo.txt");
        FileInputStream fis = new FileInputStream(arquivo);
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "virus.txt",
                MediaType.TEXT_PLAIN_VALUE,
                fis.readAllBytes()
        );
        AntivirusComponent component = new AntivirusComponent();
        component.verifyMultipartFile(file);
    }
}
