package com.algaworks.algafood_api.di.service;

import com.algaworks.algafood_api.di.modelo.Cliente;
import com.algaworks.algafood_api.di.notificacao.NivelUrgencia;
import com.algaworks.algafood_api.di.notificacao.Notificador;
import com.algaworks.algafood_api.di.notificacao.TipoDoNotificador;
import com.algaworks.algafood_api.listener.ClienteAtivadoEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Service
public class AtivacaoClienteService {

//    @TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
//    @Autowired(required = false)
//    private Notificador notificador;

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    @PostConstruct
    public void init() {
        System.out.println("INIT");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("DESTROY");
    }

    public void ativar(Cliente cliente) {
        cliente.ativar();

        applicationEventPublisher.publishEvent(new ClienteAtivadoEvent(cliente));

//        if (notificador != null) {
//                notificador.notificar(cliente, "Cliente ativado!");
//        } else {
//            System.out.println("Não existe notificador, mas cliente foi ativado!");
//        }
    }
}
