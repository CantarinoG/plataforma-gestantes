package com.cantarino.souza.model.entities;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor

public class Pagamento {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;
  private double valor;
  @ManyToOne
  @JoinColumn(name = "registrado_por_id")
  private Usuario registradoPor;
  @ManyToOne
  @JoinColumn(name = "paciente_id")
  private Gestante paciente;
  private String metodoPagamento;
  @OneToOne
  @JoinColumn(name = "procedimento_id")
  private Procedimento procedimento;
  private LocalDateTime deletadoEm;

  public Pagamento(double valor, Usuario registradoPor, Gestante paciente, String metodoPagamento,
      Procedimento procedimento, LocalDateTime deletadoEm) {
    this.id = 0;
    this.valor = valor;
    this.registradoPor = registradoPor;
    this.paciente = paciente;
    this.metodoPagamento = metodoPagamento;
    this.procedimento = procedimento;
    this.deletadoEm = deletadoEm;
  }
}
