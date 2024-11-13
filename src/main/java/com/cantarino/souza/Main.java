package com.cantarino.souza;

import com.cantarino.souza.model.dao.ConsultaDao;
import com.cantarino.souza.model.entities.Consulta;
import com.cantarino.souza.view.screens.FrMenu;

public class Main {
        public static void main(String[] args) {

                ConsultaDao consultaDao = new ConsultaDao();
                Consulta consulta = new Consulta(null, "aaaa", null, 0, null, null, null, null, null);
                consultaDao.save(consulta);

                FrMenu menu = new FrMenu();
                menu.setVisible(true);
        }
}
