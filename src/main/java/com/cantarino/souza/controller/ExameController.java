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
import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.model.entities.Usuario;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.model.exceptions.ExameException;
import com.cantarino.souza.model.services.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateExame;

public class ExameController {

    private ExameDao repositorio;
    private ValidateExame validator;
    private NotificadorEmail notificador;

    public ExameController() {
        repositorio = new ExameDao();
        validator = new ValidateExame();
        notificador = new NotificadorEmail();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMExame(repositorio.findAll()), TMExame.getCustomRenderer());
    }

    private void verificarConflitos(Exame novoExame, Gestante paciente) {
        List<Consulta> consultasPaciente = new ConsultaController().buscarTodosPorIdGestante(paciente.getId());
        for (Consulta consulta : consultasPaciente) {
            LocalDateTime consultaInicio = consulta.getData();
            LocalDateTime consultaFim = consulta.getData().plusMinutes(consulta.getDuracao());
            LocalDateTime novoInicio = novoExame.getData();
            LocalDateTime novoFim = novoExame.getData().plusMinutes(novoExame.getDuracao());

            if ((novoInicio.isAfter(consultaInicio) && novoInicio.isBefore(consultaFim)) ||
                    (novoFim.isAfter(consultaInicio) && novoFim.isBefore(consultaFim)) ||
                    (novoInicio.isEqual(consultaInicio)) ||
                    (novoInicio.isBefore(consultaInicio) && novoFim.isAfter(consultaFim))) {
                throw new ExameException("Existe um conflito com uma consulta agendada para o paciente.");
            }
        }

        List<Exame> examesPaciente = buscarTodasPorIdGestante(paciente.getId());
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
                throw new ExameException("Existe um conflito com outro exame agendado para o paciente.");
            }
        }
    }

    public void cadastrar(Gestante paciente, String descricao, String data, String duracao, String valor, String status,
            Relatorio relatorio, String deletadoEm, String dataResultado, Usuario requisitadoPor, String laboratorio) {

        if (paciente == null) {
            throw new ExameException("Paciente não pode ser nulo");
        }

        Exame novoExame = validator.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm,
                dataResultado,
                laboratorio);

        novoExame.setPaciente(paciente);
        novoExame.setRequisitadoPor(requisitadoPor);

        verificarConflitos(novoExame, paciente);

        repositorio.save(novoExame);

        String conteudoEmail = "Foi agendada um novo exame: " + novoExame.getDescricao() + ". Data: "
                + novoExame.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ".";
        notificador.notificar(paciente, "Bem Gestar | Agendamento de Exame", conteudoEmail);

    }

    public Exame buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void atualizar(int id, Gestante paciente, String descricao, String data, String duracao, String valor,
            String status,
            Relatorio relatorio, String deletadoEm, String dataResultado, Usuario requisitadoPor, String laboratorio) {
        if (paciente == null) {
            throw new ExameException("Paciente não pode ser nulo");
        }

        Exame novoExame = validator.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm,
                dataResultado,
                laboratorio);
        novoExame.setPaciente(paciente);
        novoExame.setRequisitadoPor(requisitadoPor);
        novoExame.setId(id);

        verificarConflitos(novoExame, paciente);

        repositorio.update(novoExame);
    }

    public List<Exame> buscarTodas() {
        return repositorio.findAll();
    }

    public List<Exame> buscarTodasPorIdGestante(int id) {
        return repositorio.filterGestanteId(id);
    }

    public void excluir(int id) {
        Exame exame = repositorio.find(id);
        repositorio.delete(exame);
    }

    public void filtrarTabelaPorIdGestante(JTable grd, int id) {
        Util.jTableShow(grd, new TMExame(repositorio.filterGestanteId(id)), TMExame.getCustomRenderer());
    }

    public void filtrarTabelaPorIdGestanteStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMExame(repositorio.filterGestanteIdStatus(id, status)), TMExame.getCustomRenderer());
    }

    public void filtrarTabelaPorInicioNomeGestante(JTable grd, String substring) {
        Util.jTableShow(grd, new TMExame(repositorio.filterGestanteNameStartsWith(substring)),
                TMExame.getCustomRenderer());
    }

    public void cancelar(int id) {
        Exame exame = repositorio.find(id);
        exame.setStatus(StatusProcedimentos.CANCELADA.getValue());
        repositorio.update(exame);

        String conteudoEmail = "Foi concelado o exame de id: " + exame.getId() + ": "
                + exame.getDescricao() + ".";
        notificador.notificar(exame.getPaciente(), "Bem Gestar | Cancelamento de Exame", conteudoEmail);
    }

    public void adicionarRelatorio(int id, Gestante paciente, Relatorio relatorio) {
        Exame exame = repositorio.find(id);
        exame.setStatus(StatusProcedimentos.CONCLUIDA.getValue());
        exame.setRelatorio(relatorio);
        repositorio.update(exame);

        String conteudoEmail = "Foi cadastrado um relatório para o exame de id: " + exame.getId() + ": "
                + exame.getDescricao() + ".";
        notificador.notificar(paciente, "Bem Gestar | Relatório de Exame", conteudoEmail);
    }

}
