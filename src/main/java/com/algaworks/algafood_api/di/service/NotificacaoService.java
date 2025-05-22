package com.algaworks.algafood_api.di.service;

import com.algaworks.algafood_api.di.notificacao.NivelUrgencia;
import com.algaworks.algafood_api.di.notificacao.Notificador;
import com.algaworks.algafood_api.di.notificacao.TipoDoNotificador;
import com.algaworks.algafood_api.listener.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class NotificacaoService {

    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
    @Autowired
    private Notificador notificador;

    @EventListener
    public void clienteAtivadoListener(ClienteAtivadoEvent event) {
        notificador.notificar(event.getCliente(), "Seu cadastro no sistema está ativo!");
    }

}
