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

public class Relatorio {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private LocalDateTime dataEmissao;
    private String resultado;
    private String obeservacoes;
    private LocalDateTime deletadoEm;

    public Relatorio(LocalDateTime dataEmissao, String resultado, String observacoes, LocalDateTime deletadoEm) {
        this.id = 0;
        this.dataEmissao = dataEmissao;
        this.resultado = resultado;
        this.obeservacoes = observacoes;
        this.deletadoEm = deletadoEm;
    }
}
