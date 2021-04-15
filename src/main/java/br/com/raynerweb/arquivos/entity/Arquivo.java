package br.com.raynerweb.arquivos.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "TB_ARQUIVO", schema = "ARQUIVOS")
public class Arquivo {

    @Id
    @SequenceGenerator(name = "ARQUIVOS.TB_ARQUIVO_COD_ARQUIVO_SEQ", sequenceName = "ARQUIVOS.TB_ARQUIVO_COD_ARQUIVO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ARQUIVOS.TB_ARQUIVO_COD_ARQUIVO_SEQ")
    @Column(name = "COD_ARQUIVO", updatable = false)
    private Long id;

    @Column(name = "TXT_CONTENT_TYPE", nullable = false, columnDefinition = "VARCHAR(50)")
    private String contentType;

    @Column(name = "TXT_NOME_ARQUIVO", nullable = false)
    private String nomeArquivo;

    @Column(name = "NUM_TAMANHO", nullable = false)
    private Long tamanho;

    @Column(name = "DTH_UPLOAD", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataHoraUpload;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void setNomeArquivo(String nomeArquivo) {
        this.nomeArquivo = nomeArquivo;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public Date getDataHoraUpload() {
        if (dataHoraUpload != null) {
            return new Date(dataHoraUpload.getTime());
        }
        return new Date();
    }

    public void setDataHoraUpload(Date dataHoraUpload) {
        this.dataHoraUpload = new Date(dataHoraUpload.getTime());
    }

}