package br.com.raynerweb.arquivos.rest;

import br.com.raynerweb.arquivos.repository.TipoArquivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("tipos-arquivos")
public class TipoArquivoRest {

    @Autowired
    private TipoArquivoRepository repository;

    @GetMapping
    public List<Long> get() {
        return repository.findAll().stream().map(tipoArquivo -> tipoArquivo.getId()).collect(Collectors.toList());
    }

}
