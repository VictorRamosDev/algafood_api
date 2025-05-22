package com.algaworks.algafood_api.di.notificacao;

import com.algaworks.algafood_api.di.modelo.Cliente;

public interface Notificador {
    void notificar(Cliente cliente, String mensagem);
}
