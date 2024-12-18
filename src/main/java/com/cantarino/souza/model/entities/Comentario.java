package com.cantarino.souza.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor

public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String conteudo;
    private LocalDateTime data;
    private boolean isAnonimo;
    @ManyToOne
    @JoinColumn(name = "publicacao_id")
    private Publicacao publicacao;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private LocalDateTime deletadoEm;

    public Comentario(String conteudo, LocalDateTime data, boolean isAnonimo, Publicacao publicacao, Usuario autor,
            LocalDateTime deletadoEm) {
        this.id = 0;
        this.conteudo = conteudo;
        this.data = data;
        this.isAnonimo = isAnonimo;
        this.publicacao = publicacao;
        this.autor = autor;
        this.deletadoEm = deletadoEm;
    }

}
