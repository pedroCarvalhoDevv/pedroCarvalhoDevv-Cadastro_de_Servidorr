package com.cadastro.cadastroServidor.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
@Table(name = "tb_servidor")
public class Servidor {
private static final int NOME_MAX_LENGTH = 400;
private static final int MATRICULA_MAX_LENGHT = 200;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,unique = true)
    private Long matricula;
    @Column(nullable = false)
    private String nome;
    @Column(columnDefinition = "date")
    private Date data;
    @ManyToOne
    @JoinColumn(name = "lotacao_id")
    private Lotacao lotacao;

    public Servidor() {
    }

    public Servidor(Long matricula, String nome, Date data, Lotacao lotacao) {
        this.matricula= matricula;
        this.nome=nome;
        this.data = data;
        this.lotacao = lotacao;
    }

    public Long getMatricula() {
        return matricula;
    }

    public void setMatricula(Long matricula) {
        if (matricula.longValue() > MATRICULA_MAX_LENGHT){
            this.matricula = (long) MATRICULA_MAX_LENGHT;
        } else {
            this.matricula = matricula;
        }
    }



    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome.length() > NOME_MAX_LENGTH){
            this.nome = nome.substring(0,NOME_MAX_LENGTH);
        } else {
            this.nome = nome;
        }
    }



    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Lotacao getLotacao() {
        return lotacao;
    }

    public void setLotacao(Lotacao lotacao) {
        this.lotacao = lotacao;
    }
}
