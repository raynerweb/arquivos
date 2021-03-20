package br.com.raynerweb.arquivos.repository;

import br.com.raynerweb.arquivos.entity.TipoArquivo;
import br.com.raynerweb.arquivos.repository.strategy.ArquivoBinarioStrategy;
import br.com.raynerweb.arquivos.repository.strategy.PadraoStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * READ
 * S (O) LID :: Open Close Principle
 * Temos uma fábrica de armazenamento de arquivos,
 * a estratégia de armazenamento é selecionada dependendo do seu MimeType.
 */
@Component
public class ArquivoBinarioRepository {

    private static final Logger LOG = LoggerFactory.getLogger(ArquivoBinarioRepository.class);

    @Autowired
    private PadraoStrategy padraoStrategy;

    @Autowired
    private BeanFactory beanFactory;

    public void salvar(MultipartFile multipartFile, TipoArquivo tipoArquivo) {
        getStrategy(tipoArquivo.getContentType()).salvar(multipartFile, tipoArquivo.getCaminhoArmazenamento());
    }

    private ArquivoBinarioStrategy getStrategy(String strategy) {
        try {
            return beanFactory.getBean(strategy, ArquivoBinarioStrategy.class);
        } catch (NoSuchBeanDefinitionException e) {
            return padraoStrategy;
        }
    }

}
