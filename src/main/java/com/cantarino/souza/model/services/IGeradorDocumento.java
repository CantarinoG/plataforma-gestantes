package com.cantarino.souza.model.services;

import java.util.List;

public interface IGeradorDocumento {

    public void gerarDocumento(String caminho, String... conteudo);

    public void combinarDocumentos(String caminhoSaida, List<String> caminhosEntrada);

}
