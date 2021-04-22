package br.com.raynerweb.arquivos.component;

import br.com.raynerweb.arquivos.exception.VirusDetectedException;
import fi.solita.clamav.ClamAVClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Component
public class AntivirusComponent {

    private static final Logger LOG = LoggerFactory.getLogger(AntivirusComponent.class);

    public void verifiy(byte[] file) {
        try {
            ClamAVClient a = new ClamAVClient("127.0.0.1", 3310, 60000);
            byte[] r = a.scan(file);
            if (!ClamAVClient.isCleanReply(r)) {
                LOG.error("Virus Detectado");
                throw new VirusDetectedException("Virus Detectado");
            }
        } catch (IOException e) {
            LOG.error("Arquivo nao pode ser verificado");
            throw new IllegalStateException("Arquivo nao pode ser verificado");
        }
    }

    public void verifyMultipartFile(MultipartFile multipartFile) {
        try {
            verifiy(multipartFile.getBytes());
        } catch (IOException e) {
            LOG.error("Arquivo nao pode ser verificado");
            throw new IllegalStateException("Arquivo nao pode ser verificado");
        }
    }

}
