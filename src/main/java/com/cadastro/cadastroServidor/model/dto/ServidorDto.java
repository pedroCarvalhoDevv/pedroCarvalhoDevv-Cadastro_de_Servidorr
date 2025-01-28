package com.cadastro.cadastroServidor.model.dto;

public class ServidorDto {

    private Long matricula;
    private String nome;
    private Long idLotacao;

    public ServidorDto() {
    }

    public ServidorDto(Long matricula, String nome, Long idLotacao) {
        this.matricula = matricula;
        this.nome = nome;
        this.idLotacao = idLotacao;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdLotacao() {
        return idLotacao;
    }

    public void setIdLotacao(Long idLotacao) {
        this.idLotacao = idLotacao;
    }
}

