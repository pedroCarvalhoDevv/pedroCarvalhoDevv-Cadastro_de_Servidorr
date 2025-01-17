package com.cadastro.cadastroServidor.model.dto;

import jakarta.validation.constraints.NotBlank;

public class LotacaoDto {

    private long id;
    @NotBlank(message = "Descrição Obrigatória")
    private String descricao;

    public LotacaoDto() {
    }

    public LotacaoDto(long id, String descricao) {
        this.id = id;
        this.descricao = descricao;
    }

    public LotacaoDto(String descricao) {
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
