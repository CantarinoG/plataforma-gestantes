package com.cantarino.souza.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.swing.JTable;

import com.cantarino.souza.controller.tablemodels.TMConsulta;
import com.cantarino.souza.model.dao.ConsultaDao;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.model.entities.Exame;
import com.cantarino.souza.model.entities.Gestante;
import com.cantarino.souza.model.entities.Medico;
import com.cantarino.souza.model.entities.Relatorio;
import com.cantarino.souza.model.enums.StatusProcedimentos;
import com.cantarino.souza.model.exceptions.ConsultaException;
import com.cantarino.souza.model.services.NotificadorEmail;
import com.cantarino.souza.model.valid.ValidateConsulta;

public class ConsultaController {

    private ConsultaDao repositorio;
    private ValidateConsulta validator;
    private NotificadorEmail notificador;

    public ConsultaController() {
        repositorio = new ConsultaDao();
        validator = new ValidateConsulta();
        notificador = new NotificadorEmail();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMConsulta(repositorio.findAll()), TMConsulta.getCustomRenderer());
    }

    private void verificarConflitos(Consulta novaConsulta, Gestante paciente, Medico medico) {
        List<Consulta> consultaPaciente = repositorio.filterGestanteId(paciente.getId());
        for (Consulta consulta : consultaPaciente) {
            if (consulta.getId() == novaConsulta.getId())
                continue; // Skip if updating same consultation

            LocalDateTime consultaInicio = consulta.getData();
            LocalDateTime consultaFim = consulta.getData().plusMinutes(consulta.getDuracao());
            LocalDateTime novoInicio = novaConsulta.getData();
            LocalDateTime novoFim = novaConsulta.getData().plusMinutes(novaConsulta.getDuracao());

            if ((novoInicio.isAfter(consultaInicio) && novoInicio.isBefore(consultaFim)) ||
                    (novoFim.isAfter(consultaInicio) && novoFim.isBefore(consultaFim)) ||
                    (novoInicio.isEqual(consultaInicio)) ||
                    (novoInicio.isBefore(consultaInicio) && novoFim.isAfter(consultaFim))) {
                throw new ConsultaException("Existe um conflito com outra consulta agendada para o paciente.");
            }
        }

        List<Consulta> consultaMedico = repositorio.filterMedicoId(medico.getId());
        for (Consulta consulta : consultaMedico) {
            if (consulta.getId() == novaConsulta.getId())
                continue; // Skip if updating same consultation

            LocalDateTime consultaInicio = consulta.getData();
            LocalDateTime consultaFim = consulta.getData().plusMinutes(consulta.getDuracao());
            LocalDateTime novoInicio = novaConsulta.getData();
            LocalDateTime novoFim = novaConsulta.getData().plusMinutes(novaConsulta.getDuracao());

            if ((novoInicio.isAfter(consultaInicio) && novoInicio.isBefore(consultaFim)) ||
                    (novoFim.isAfter(consultaInicio) && novoFim.isBefore(consultaFim)) ||
                    (novoInicio.isEqual(consultaInicio)) ||
                    (novoInicio.isBefore(consultaInicio) && novoFim.isAfter(consultaFim))) {
                throw new ConsultaException("Existe um conflito com outra consulta agendada para o médico.");
            }
        }

        List<Exame> examePaciente = new ExameController().buscarTodasPorIdGestante(paciente.getId());
        for (Exame exame : examePaciente) {
            LocalDateTime exameInicio = exame.getData();
            LocalDateTime exameFim = exame.getData().plusMinutes(exame.getDuracao());
            LocalDateTime novoInicio = novaConsulta.getData();
            LocalDateTime novoFim = novaConsulta.getData().plusMinutes(novaConsulta.getDuracao());

            if ((novoInicio.isAfter(exameInicio) && novoInicio.isBefore(exameFim)) ||
                    (novoFim.isAfter(exameInicio) && novoFim.isBefore(exameFim)) ||
                    (novoInicio.isEqual(exameInicio)) ||
                    (novoInicio.isBefore(exameInicio) && novoFim.isAfter(exameFim))) {
                throw new ConsultaException("Existe um conflito com um exame agendado para o paciente.");
            }
        }
    }

    List<Consulta> buscarTodosPorIdGestante(int id) {
        return repositorio.filterGestanteId(id);
    }

    public void cadastrar(Gestante paciente, String descricao, String data, String duracao, String valor, String status,
            Relatorio relatorio, String deletadoEm, Medico medico, Consulta retorno) {

        if (paciente == null) {
            throw new ConsultaException("Paciente não pode ser nulo");
        }
        if (medico == null) {
            throw new ConsultaException("Médico não pode ser nulo");
        }

        Consulta novaConsulta = validator.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm);
        novaConsulta.setPaciente(paciente);
        novaConsulta.setMedico(medico);
        novaConsulta.setRetorno(retorno);

        verificarConflitos(novaConsulta, paciente, medico);

        repositorio.save(novaConsulta);

        String conteudoEmail = "Foi agendada uma nova consulta: " + novaConsulta.getDescricao() + ". Data: "
                + novaConsulta.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ".";
        notificador.notificar(paciente, "Bem Gestar | Agendamento de Consulta", conteudoEmail);
        notificador.notificar(medico, "Bem Gestar | Agendamento de Consulta", conteudoEmail);

    }

    public void atualizar(int id, Gestante paciente, String descricao, String data, String duracao, String valor,
            String status,
            Relatorio relatorio, String deletadoEm, Medico medico, Consulta retorno) {

        if (paciente == null) {
            throw new ConsultaException("Paciente não pode ser nulo");
        }
        if (medico == null) {
            throw new ConsultaException("Médico não pode ser nulo");
        }

        Consulta novaConsulta = validator.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm);
        novaConsulta.setPaciente(paciente);
        novaConsulta.setMedico(medico);
        novaConsulta.setRetorno(retorno);
        novaConsulta.setId(id);

        verificarConflitos(novaConsulta, paciente, medico);

        repositorio.update(novaConsulta);
    }

    public List<Consulta> buscarTodas() {
        return repositorio.findAll();
    }

    public void excluir(int id) {
        Consulta consulta = repositorio.find(id);
        repositorio.delete(consulta);
    }

    public Consulta buscarPorId(int id) {
        return repositorio.find(id);
    }

    public void filtrarTabelaPorIdMedico(JTable grd, int id) {
        Util.jTableShow(grd, new TMConsulta(repositorio.filterMedicoId(id)), TMConsulta.getCustomRenderer());
    }

    public void filtrarTabelaPorIdMedicoStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMConsulta(repositorio.filterMedicoIdStatus(id, status)),
                TMConsulta.getCustomRenderer());
    }

    public void filtrarTabelaPorIdGestante(JTable grd, int id) {
        Util.jTableShow(grd, new TMConsulta(repositorio.filterGestanteId(id)), TMConsulta.getCustomRenderer());
    }

    public void filtrarTabelaPorIdGestanteStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMConsulta(repositorio.filterGestanteIdStatus(id, status)),
                TMConsulta.getCustomRenderer());
    }

    public void filtrarTabelaPorInicioNomeGestante(JTable grd, String substring) {
        Util.jTableShow(grd, new TMConsulta(repositorio.filterGestanteNameStartsWith(substring)),
                TMConsulta.getCustomRenderer());
    }

    public void filtrarTabelaPorInicioNomeMedico(JTable grd, String substring) {
        Util.jTableShow(grd, new TMConsulta(repositorio.filterMedicoNameStartsWith(substring)),
                TMConsulta.getCustomRenderer());
    }

    public void cancelar(int id) {
        Consulta consulta = repositorio.find(id);
        consulta.setStatus(StatusProcedimentos.CANCELADA.getValue());
        repositorio.update(consulta);

        String conteudoEmail = "Foi concelada a consulta de id: " + consulta.getId() + ": "
                + consulta.getDescricao() + ".";
        notificador.notificar(consulta.getPaciente(), "Bem Gestar | Cancelamento de Consulta", conteudoEmail);
        notificador.notificar(consulta.getMedico(), "Bem Gestar | Cancelamento de Consulta", conteudoEmail);
    }

    public void adicionarRelatorio(int id, Gestante paciente, Relatorio relatorio) {
        Consulta consulta = repositorio.find(id);
        consulta.setStatus(StatusProcedimentos.CONCLUIDA.getValue());
        consulta.setRelatorio(relatorio);
        repositorio.update(consulta);

        String conteudoEmail = "Foi cadastrado um relatório para a consulta de id: " + consulta.getId() + ": "
                + consulta.getDescricao() + ".";
        notificador.notificar(paciente, "Bem Gestar | Relatório de Consulta", conteudoEmail);
    }

}
