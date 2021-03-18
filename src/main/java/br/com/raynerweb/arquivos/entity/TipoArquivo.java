package br.com.raynerweb.arquivos.entity;

import javax.persistence.*;

@Entity
@Table(name = "TB_TIPO_ARQUIVO", schema = "ARQUIVOS")
public class TipoArquivo {

    @Id
    @SequenceGenerator(name = "ARQUIVOS.TB_TIPO_ARQUIVO_COD_TIPO_ARQUIVO_SEQ", sequenceName = "ARQUIVOS.TB_TIPO_ARQUIVO_COD_TIPO_ARQUIVO_SEQ", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "ARQUIVOS.TB_TIPO_ARQUIVO_COD_TIPO_ARQUIVO_SEQ")
    @Column(name = "COD_TIPO_ARQUIVO", updatable = false)
    private Long id;

    @Column(name = "TXT_DESCRICAO", nullable = false, columnDefinition = "VARCHAR(250)")
    private String descricao;

    @Column(name = "TXT_CAMINHO_ARMAZENAMENTO", nullable = false, columnDefinition = "VARCHAR(1000)")
    private String caminhoArmazenamento;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getCaminhoArmazenamento() {
        return caminhoArmazenamento;
    }

    public void setCaminhoArmazenamento(String caminhoArmazenamento) {
        this.caminhoArmazenamento = caminhoArmazenamento;
    }
}
