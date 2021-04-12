package br.com.raynerweb.arquivos.component;

import fi.solita.clamav.ClamAVClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Component
public class AntivirusComponent {

    private static final Logger LOG = LoggerFactory.getLogger(AntivirusComponent.class);

    public void check(File file) throws IOException {
        if (file != null) {
            ClamAVClient a = new ClamAVClient("127.0.0.1", 3310, 30);
            byte[] r = a.scan(new FileInputStream(file));
            if (!ClamAVClient.isCleanReply(r)) {
                if (file.delete()) {
                    LOG.warn("VIRUS REMOVIDO");
                } else {
                    LOG.warn("NÃO FOI POSSIVEL REMOVER O VIRUS");
                }
                throw new IllegalArgumentException("Virus detected");
            }
        }
    }

}
