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
    private ValidateConsulta validador;
    private NotificadorEmail notificador;

    public ConsultaController() {
        repositorio = new ConsultaDao();
        validador = new ValidateConsulta();
        notificador = new NotificadorEmail();
    }

    public void atualizarTabela(JTable grd) {
        Util.jTableShow(grd, new TMConsulta(repositorio.buscarTodos()), TMConsulta.getCustomRenderer());
    }

    public List<Consulta> buscarTodas() {
        return repositorio.buscarTodos();
    }

    List<Consulta> buscarPorGestante(int id) {
        return repositorio.buscarPorGestante(id);
    }

    private void verificarConflitos(Consulta novaConsulta) {
        List<Consulta> consultaPaciente = repositorio.buscarPorGestante(novaConsulta.getPaciente().getId());
        for (Consulta consulta : consultaPaciente) {
            if (consulta.getId() == novaConsulta.getId())
                continue; // Pula se é a mesma consulta

            LocalDateTime consultaInicio = consulta.getData();
            LocalDateTime consultaFim = consulta.getData().plusMinutes(consulta.getDuracao());
            LocalDateTime novoInicio = novaConsulta.getData();
            LocalDateTime novoFim = novaConsulta.getData().plusMinutes(novaConsulta.getDuracao());

            if ((novoInicio.isAfter(consultaInicio) && novoInicio.isBefore(consultaFim)) ||
                    (novoFim.isAfter(consultaInicio) && novoFim.isBefore(consultaFim)) ||
                    (novoInicio.isEqual(consultaInicio)) ||
                    (novoInicio.isBefore(consultaInicio) && novoFim.isAfter(consultaFim))) {
                throw new ConsultaException("ERRO: Existe um conflito com outra consulta agendada para o paciente.");
            }
        }

        List<Consulta> consultaMedico = repositorio.buscarPorGestante(novaConsulta.getMedico().getId());
        for (Consulta consulta : consultaMedico) {
            if (consulta.getId() == novaConsulta.getId())
                continue; // Pula se é a mesma consulta

            LocalDateTime consultaInicio = consulta.getData();
            LocalDateTime consultaFim = consulta.getData().plusMinutes(consulta.getDuracao());
            LocalDateTime novoInicio = novaConsulta.getData();
            LocalDateTime novoFim = novaConsulta.getData().plusMinutes(novaConsulta.getDuracao());

            if ((novoInicio.isAfter(consultaInicio) && novoInicio.isBefore(consultaFim)) ||
                    (novoFim.isAfter(consultaInicio) && novoFim.isBefore(consultaFim)) ||
                    (novoInicio.isEqual(consultaInicio)) ||
                    (novoInicio.isBefore(consultaInicio) && novoFim.isAfter(consultaFim))) {
                throw new ConsultaException("ERRO: Existe um conflito com outra consulta agendada para o médico.");
            }
        }

        List<Exame> examePaciente = new ExameController().buscarPorGestante(novaConsulta.getPaciente().getId());
        for (Exame exame : examePaciente) {
            LocalDateTime exameInicio = exame.getData();
            LocalDateTime exameFim = exame.getData().plusMinutes(exame.getDuracao());
            LocalDateTime novoInicio = novaConsulta.getData();
            LocalDateTime novoFim = novaConsulta.getData().plusMinutes(novaConsulta.getDuracao());

            if ((novoInicio.isAfter(exameInicio) && novoInicio.isBefore(exameFim)) ||
                    (novoFim.isAfter(exameInicio) && novoFim.isBefore(exameFim)) ||
                    (novoInicio.isEqual(exameInicio)) ||
                    (novoInicio.isBefore(exameInicio) && novoFim.isAfter(exameFim))) {
                throw new ConsultaException("ERRO: Existe um conflito com um exame agendado para o paciente.");
            }
        }
    }

    public void salvar(Gestante paciente, String descricao, String data, String duracao, String valor, String status,
            Relatorio relatorio, String deletadoEm, Medico medico, Consulta retorno) {

        Consulta novaConsulta = validador.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm,
                paciente, medico, retorno);

        verificarConflitos(novaConsulta);

        repositorio.salvar(novaConsulta);

        String conteudoEmail = "Foi agendada uma nova consulta: " + novaConsulta.getDescricao() + ". Data: "
                + novaConsulta.getData().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + ".";
        notificador.notificar(paciente, "Bem Gestar | Agendamento de Consulta", conteudoEmail);
        notificador.notificar(medico, "Bem Gestar | Agendamento de Consulta", conteudoEmail);

    }

    public void editar(int id, Gestante paciente, String descricao, String data, String duracao, String valor,
            String status,
            Relatorio relatorio, String deletadoEm, Medico medico, Consulta retorno) {

        Consulta novaConsulta = validador.validaCamposEntrada(descricao, data, duracao, valor, status, deletadoEm,
                paciente, medico, retorno);
        novaConsulta.setId(id);

        verificarConflitos(novaConsulta);

        repositorio.editar(novaConsulta);
    }

    public void deletar(int id) {
        Consulta consulta = repositorio.buscar(id);
        repositorio.deletar(consulta);
    }

    public Consulta buscar(int id) {
        return repositorio.buscar(id);
    }

    public void atualizarTabelaPorMedico(JTable grd, int id) {
        Util.jTableShow(grd, new TMConsulta(repositorio.buscarPorMedico(id)), TMConsulta.getCustomRenderer());
    }

    public void atualizarTabelaPorMedicoEStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMConsulta(repositorio.buscarPorMedicoEStatus(id, status)),
                TMConsulta.getCustomRenderer());
    }

    public void atualizarTabelaPorGestante(JTable grd, int id) {
        Util.jTableShow(grd, new TMConsulta(repositorio.buscarPorGestante(id)), TMConsulta.getCustomRenderer());
    }

    public void atualizarTabelaPorGestanteEStatus(JTable grd, int id, String status) {
        Util.jTableShow(grd, new TMConsulta(repositorio.buscarPorGestanteEStatus(id, status)),
                TMConsulta.getCustomRenderer());
    }

    public void atualizarTabelaPorNomeGestante(JTable grd, String substring) {
        Util.jTableShow(grd, new TMConsulta(repositorio.buscarPorNomeGestante(substring)),
                TMConsulta.getCustomRenderer());
    }

    public void atualizarTabelaPorNomeMedico(JTable grd, String substring) {
        Util.jTableShow(grd, new TMConsulta(repositorio.buscarPorNomeMedico(substring)),
                TMConsulta.getCustomRenderer());
    }

    public void cancelar(int id) {
        Consulta consulta = repositorio.buscar(id);
        consulta.setStatus(StatusProcedimentos.CANCELADA.getValor());
        repositorio.editar(consulta);

        String conteudoEmail = "Foi concelada a consulta de id: " + consulta.getId() + ": "
                + consulta.getDescricao() + ".";
        notificador.notificar(consulta.getPaciente(), "Bem Gestar | Cancelamento de Consulta", conteudoEmail);
        notificador.notificar(consulta.getMedico(), "Bem Gestar | Cancelamento de Consulta", conteudoEmail);
    }

    public void adicionarRelatorio(int id, Gestante paciente, Relatorio relatorio) {
        Consulta consulta = repositorio.buscar(id);
        consulta.setStatus(StatusProcedimentos.CONCLUIDA.getValor());
        consulta.setRelatorio(relatorio);
        repositorio.editar(consulta);

        String conteudoEmail = "Foi cadastrado um relatório para a consulta de id: " + consulta.getId() + ": "
                + consulta.getDescricao() + ".";
        notificador.notificar(paciente, "Bem Gestar | Relatório de Consulta", conteudoEmail);
    }

}
