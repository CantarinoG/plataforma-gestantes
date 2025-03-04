package com.cantarino.souza.model.dao;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.EntityManager;

import com.cantarino.souza.factory.DatabaseJPA;
import com.cantarino.souza.model.entities.Consulta;

public class ConsultaDao implements IDao<Consulta> {

        private EntityManager entityManager;

        @Override
        public void salvar(Consulta obj) {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                this.entityManager.getTransaction().begin();
                this.entityManager.persist(obj);
                this.entityManager.getTransaction().commit();

                this.entityManager.close();
        }

        @Override
        public void editar(Consulta obj) {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                this.entityManager.getTransaction().begin();
                this.entityManager.merge(obj);
                this.entityManager.getTransaction().commit();

                this.entityManager.close();
        }

        @Override
        public boolean deletar(Consulta obj) {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                this.entityManager.getTransaction().begin();
                obj.setDeletadoEm(LocalDateTime.now());
                this.entityManager.merge(obj);
                this.entityManager.getTransaction().commit();

                this.entityManager.close();
                return true;
        }

        @Override
        public Consulta buscar(int id) {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                Consulta consulta = this.entityManager.find(Consulta.class, id);

                this.entityManager.close();

                return consulta;
        }

        @Override
        public List<Consulta> buscarTodos() {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                List<Consulta> consultas = this.entityManager
                                .createQuery("FROM Consulta c WHERE c.deletadoEm IS NULL ORDER BY c.data DESC",
                                                Consulta.class)
                                .getResultList();

                this.entityManager.close();
                return consultas;
        }

        public List<Consulta> buscarPorMedico(int id) {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                List<Consulta> consultas = this.entityManager
                                .createQuery("FROM Consulta c WHERE c.deletadoEm IS NULL AND c.medico.id = :id ORDER BY c.data DESC",
                                                Consulta.class)
                                .setParameter("id", id)
                                .getResultList();

                this.entityManager.close();
                return consultas;
        }

        public List<Consulta> buscarPorGestante(int id) {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                List<Consulta> consultas = this.entityManager
                                .createQuery("FROM Consulta c WHERE c.deletadoEm IS NULL AND c.paciente.id = :id ORDER BY c.data DESC",
                                                Consulta.class)
                                .setParameter("id", id)
                                .getResultList();

                this.entityManager.close();
                return consultas;
        }

        public List<Consulta> buscarPorMedicoEStatus(int id, String status) {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                List<Consulta> consultas = this.entityManager
                                .createQuery(
                                                "FROM Consulta c WHERE c.deletadoEm IS NULL AND c.medico.id = :id AND c.status = :status ORDER BY c.data DESC",
                                                Consulta.class)
                                .setParameter("id", id)
                                .setParameter("status", status)
                                .getResultList();

                this.entityManager.close();
                return consultas;
        }

        public List<Consulta> buscarPorGestanteEStatus(int id, String status) {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                List<Consulta> consultas = this.entityManager
                                .createQuery(
                                                "FROM Consulta c WHERE c.deletadoEm IS NULL AND c.paciente.id = :id AND c.status = :status ORDER BY c.data DESC",
                                                Consulta.class)
                                .setParameter("id", id)
                                .setParameter("status", status)
                                .getResultList();

                this.entityManager.close();
                return consultas;
        }

        public List<Consulta> buscarPorNomeGestante(String substring) {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                List<Consulta> consultas = this.entityManager
                                .createQuery(
                                                "FROM Consulta c WHERE c.deletadoEm IS NULL AND LOWER(c.paciente.nome) LIKE LOWER(:substring) ORDER BY c.data DESC",
                                                Consulta.class)
                                .setParameter("substring", substring + "%")
                                .getResultList();

                this.entityManager.close();
                return consultas;
        }

        public List<Consulta> buscarPorNomeMedico(String subString) {
                this.entityManager = DatabaseJPA.getInstance().getEntityManager();

                List<Consulta> consultas = this.entityManager
                                .createQuery(
                                                "FROM Consulta c WHERE c.deletadoEm IS NULL AND LOWER(c.medico.nome) LIKE LOWER(:substring) ORDER BY c.data DESC",
                                                Consulta.class)
                                .setParameter("substring", subString + "%")
                                .getResultList();

                this.entityManager.close();
                return consultas;
        }

}
