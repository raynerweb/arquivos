package br.com.raynerweb.arquivos.rest;

import br.com.raynerweb.arquivos.dto.ArquivoResponse;
import br.com.raynerweb.arquivos.entity.Arquivo;
import br.com.raynerweb.arquivos.service.ArquivoService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/arquivos")
public class ArquivoRest {

    @Autowired
    private ArquivoService arquivoService;

    @PostMapping
    public void post(@RequestParam("files") MultipartFile[] files,
                     @RequestParam("codigoTipoArquivo") Long codigoTipoArquivo) {
        arquivoService.salvar(files, codigoTipoArquivo);
    }

    @GetMapping("{id}")
    public void get(@PathVariable Long id, HttpServletResponse response) {
        ArquivoResponse arquivo = arquivoService.recuperar(id);
        response.setHeader("Content-disposition", "attachment; filename=" + arquivo.getNomeArquivo());
        response.setHeader("Cache-Control", "max-age=86400, public");
        response.setContentType(arquivo.getContentType());
        try {
            IOUtils.copy(new ByteArrayInputStream(arquivo.getBytes()), response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
