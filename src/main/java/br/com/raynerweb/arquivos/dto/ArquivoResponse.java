package br.com.raynerweb.arquivos.dto;

import br.com.raynerweb.arquivos.entity.Arquivo;

public class ArquivoResponse {
    private byte[] bytes;
    private String nomeArquivo;
    private String contentType;

    public ArquivoResponse(byte[] bytes, String nomeArquivo, String contentType) {
        this.bytes = bytes;
        this.nomeArquivo = nomeArquivo;
        this.contentType = contentType;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}