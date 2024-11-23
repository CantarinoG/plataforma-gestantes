package com.cantarino.souza.model.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

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
    private LocalDateTime data;
    private boolean isMedico;
    private Usuario autor;
    private LocalDateTime deletadoEm;
    
    public Publicacao(String titulo, String corpo, LocalDateTime data, boolean isMedico, Usuario autor, LocalDateTime deletadoEm){
        this.id = 0;
        this.titulo = titulo;
        this.corpo = corpo;
        this.data = data;
        this.isMedico = isMedico;
        this.autor = autor;
        this.deletadoEm = deletadoEm;
    }
}
