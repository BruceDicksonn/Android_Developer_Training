package com.example.appimportarmultiplosarquivos.commons;

import com.example.appimportarmultiplosarquivos.model.Arquivo;

public interface ArquivoListeners {

    void visualizarArquivo(int pos);
    void deletarArquivo(Arquivo arquivo);

}
