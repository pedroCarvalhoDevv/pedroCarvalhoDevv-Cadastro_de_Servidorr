package com.cadastro.cadastroServidor.model.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "tb_servidor_log")
public class ServidorLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long matriculaServidor;
    private String campoAlterado;
    private String valorAnterior;
    private String novoValor;

    @Column(name = "data_alteracao")
    private Date dataAlteracao;

    public ServidorLog() {
    }

    public ServidorLog(Long matriculaServidor, String campoAlterado, String valorAnterior, String novoValor) {
        this.matriculaServidor = matriculaServidor;
        this.campoAlterado = campoAlterado;
        this.valorAnterior = valorAnterior;
        this.novoValor = novoValor;
        this.dataAlteracao = new Date();
    }

    public Long getId() {
        return id;
    }

    public Long getMatriculaServidor() {
        return matriculaServidor;
    }

    public String getCampoAlterado() {
        return campoAlterado;
    }

    public String getValorAnterior() {
        return valorAnterior;
    }

    public String getNovoValor() {
        return novoValor;
    }

    public Date getDataAlteracao() {
        return dataAlteracao;
    }
}

