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

public class Publicacao {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String titulo;
    private String corpo;
    private boolean isAnonimo;
    private LocalDateTime data;
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Usuario autor;
    private LocalDateTime deletadoEm;

    public Publicacao(String titulo, String corpo, boolean isAnonimo, LocalDateTime data, Usuario autor,
            LocalDateTime deletadoEm) {
        this.id = 0;
        this.titulo = titulo;
        this.corpo = corpo;
        this.isAnonimo = isAnonimo;
        this.data = data;
        this.autor = autor;
        this.deletadoEm = deletadoEm;
    }
}
