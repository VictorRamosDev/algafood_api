package com.algaworks.algafood_api.listener;

import com.algaworks.algafood_api.di.modelo.Cliente;

public class ClienteAtivadoEvent {

    private Cliente cliente;

    public ClienteAtivadoEvent(Cliente cliente) {
        super();
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

}
