package com.cantarino.souza.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMExame;
import com.cantarino.souza.model.dao.ExameDao;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.model.exceptions.ExameException;
import com.cantarino.souza.model.utils.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateExame;

public class ExameController {

    private ExameDao repositorio;
    private ValidateExame validador;
    private NotificadorEmail notificador;

    public ExameController() {
        repositorio = new ExameDao();
        validador = new ValidateExame();
        notificador = new NotificadorEmail();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMExame(repositorio.buscarTodos()), TMExame.getCustomRenderer());
    }

    private void verificarConflitos(Exame novoExame) {
        List<Consulta> consultasPaciente = new ConsultaController().buscarPorGestante(novoExame.getPaciente().getId());
        for (Consulta consulta : consultasPaciente) {
            LocalDateTime consultaInicio = consulta.getData();
            LocalDateTime consultaFim = consulta.getData().plusMinutes(consulta.getDuracao());
            LocalDateTime novoInicio = novoExame.getData();
            LocalDateTime novoFim = novoExame.getData().plusMinutes(novoExame.getDuracao());

            if ((novoInicio.isAfter(consultaInicio) && novoInicio.isBefore(consultaFim)) ||
                    (novoFim.isAfter(consultaInicio) && novoFim.isBefore(consultaFim)) ||
                    (novoInicio.isEqual(consultaInicio)) ||
                    (novoInicio.isBefore(consultaInicio) && novoFim.isAfter(consultaFim))) {
                throw new ExameException("ERRO: Existe um conflito com uma consulta agendada para o paciente.");
            }
        }

        List<Exame> examesPaciente = buscarPorGestante(novoExame.getPaciente().getId());
        for (Exame exame : examesPaciente) {
            if (exame.getId() == novoExame.getId())
                continue;

            LocalDateTime exameInicio = exame.getData();
            LocalDateTime exameFim = exame.getData().plusMinutes(exame.getDuracao());
            LocalDateTime novoInicio = novoExame.getData();
            LocalDateTime novoFim = novoExame.getData().plusMinutes(novoExame.getDuracao());

            if ((novoInicio.isAfter(exameInicio) && novoInicio.isBefore(exameFim)) ||
                    (novoFim.isAfter(exameInicio) && novoFim.isBefore(exameFim)) ||
                    (novoInicio.isEqual(exameInicio)) ||
                    (novoInicio.isBefore(exameInicio) && novoFim.isAfter(exameFim))) {
                throw new ExameException("ERRO: Existe um conflito com outro exame agendado para o paciente.");
            }
        }

        if (novoExame.getMedico() != null) {
            List<Consulta> consultasMedico = new ConsultaController().buscarPorMedico(novoExame.getMedico().getId());
            for (Consulta consulta : consultasMedico) {
                LocalDateTime consultaInicio = consulta.getData();
                LocalDateTime consultaFim = consulta.getData().plusMinutes(consulta.getDuracao());
                LocalDateTime novoInicio = novoExame.getData();
                LocalDateTime novoFim = novoExame.getData().plusMinutes(novoExame.getDuracao());

                if ((novoInicio.isAfter(consultaInicio) && novoInicio.isBefore(consultaFim)) ||
                        (novoFim.isAfter(consultaInicio) && novoFim.isBefore(consultaFim)) ||
                        (novoInicio.isEqual(consultaInicio)) ||
                        (novoInicio.isBefore(consultaInicio) && novoFim.isAfter(consultaFim))) {
                    throw new ExameException(
                            "ERRO: Existe um conflito com uma consulta agendada para o médico executante.");
                }
            }

            List<Exame> examesMedico = buscarPorMedico(novoExame.getMedico().getId());
            for (Exame exame : examesMedico) {
                if (exame.getId() == novoExame.getId())
                    continue;

                LocalDateTime exameInicio = exame.getData();
                LocalDateTime exameFim = exame.getData().plusMinutes(exame.getDuracao());
                LocalDateTime novoInicio = novoExame.getData();
                LocalDateTime novoFim = novoExame.getData().plusMinutes(novoExame.getDuracao());

                if ((novoInicio.isAfter(exameInicio) && novoInicio.isBefore(exameFim)) ||
                        (novoFim.isAfter(exameInicio) && novoFim.isBefore(exameFim)) ||
                        (novoInicio.isEqual(exameInicio)) ||
                        (novoInicio.isBefore(exameInicio) && novoFim.isAfter(exameFim))) {
                    throw new ExameException(
                            "ERRO: Existe um conflito com outro exame agendado para o médico executante.");
                }
            }
        }
    }

    public void salvar(Gestante paciente, String descricao, String data, String duracao, String valor, String status,
            Relatorio relatorio, String deletadoEm, String dataResultado, Usuario requisitadoPor, String laboratorio,
            Medico medicoExecutante) {

        Exame novoExame = validador.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm,
                dataResultado,
                laboratorio, paciente, requisitadoPor, medicoExecutante);

        verificarConflitos(novoExame);

        repositorio.salvar(novoExame);

        String conteudoEmail = "Foi agendada um novo exame: " + novoExame.getDescricao() + ". Data: "
                + novoExame.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ".";
        notificador.notificar(paciente, "Bem Gestar | Agendamento de Exame", conteudoEmail);

    }

    public Exame buscar(int id) {
        return repositorio.buscar(id);
    }

    public void editar(int id, Gestante paciente, String descricao, String data, String duracao, String valor,
            String status,
            Relatorio relatorio, String deletadoEm, String dataResultado, Usuario requisitadoPor, String laboratorio,
            Medico medicoExecutante) {

        Exame novoExame = validador.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm,
                dataResultado,
                laboratorio, paciente, requisitadoPor, medicoExecutante);
        novoExame.setId(id);

        verificarConflitos(novoExame);

        repositorio.editar(novoExame);
    }

    public List<Exame> buscarTodas() {
        return repositorio.buscarTodos();
    }

    public List<Exame> buscarPorGestante(int id) {
        return repositorio.buscarPorGestante(id);
    }

    public List<Exame> buscarPorMedico(int id) {
        return repositorio.buscarPorMedico(id);
    }

    public void deletar(int id) {
        Exame exame = repositorio.buscar(id);
        repositorio.deletar(exame);
    }

    public void atualizarTabelaPorGestante(JTable grd, int id) {
        Util.jTableShow(grd, new TMExame(repositorio.buscarPorGestante(id)), TMExame.getCustomRenderer());
    }

    public void atualizarTabelaPorGestanteEStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMExame(repositorio.buscarPorGestanteEStatus(id, status)),
                TMExame.getCustomRenderer());
    }

    public void atualizarTabelaPorMedico(JTable grd, int id) {
        Util.jTableShow(grd, new TMExame(repositorio.buscarPorMedico(id)), TMExame.getCustomRenderer());
    }

    public void atualizarTabelaPorMedicoEStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMExame(repositorio.buscarPorMedicoEStatus(id, status)),
                TMExame.getCustomRenderer());
    }

    public void atualizarTabelaPorNomeGestante(JTable grd, String substring) {
        Util.jTableShow(grd, new TMExame(repositorio.buscarPorNomeGestante(substring)),
                TMExame.getCustomRenderer());
    }

    public void cancelar(int id) {
        Exame exame = repositorio.buscar(id);

        validador.validarCancelamento(exame);

        exame.setStatus(StatusProcedimentos.CANCELADA.getValor());
        repositorio.editar(exame);

        String conteudoEmail = "Foi concelado o exame de id: " + exame.getId() + ": "
                + exame.getDescricao() + ".";
        notificador.notificar(exame.getPaciente(), "Bem Gestar | Cancelamento de Exame", conteudoEmail);
    }

    public void adicionarRelatorio(int id, Gestante paciente, Relatorio relatorio) {
        Exame exame = repositorio.buscar(id);
        exame.setStatus(StatusProcedimentos.CONCLUIDA.getValor());
        exame.setRelatorio(relatorio);
        repositorio.editar(exame);

        String conteudoEmail = "Foi cadastrado um relatório para o exame de id: " + exame.getId() + ": "
                + exame.getDescricao() + ".";
        notificador.notificar(paciente, "Bem Gestar | Relatório de Exame", conteudoEmail);
    }

}
