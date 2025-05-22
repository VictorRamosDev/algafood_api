package com.algaworks.algafood_api.di.notificacao;

import com.algaworks.algafood_api.di.modelo.Cliente;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("dev")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmailMock implements Notificador {

    private boolean caixaAlta;

    public NotificadorEmailMock() {
        System.out.println("Bean 'NotificadorEmailMock' criado!");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        if (caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }

        System.out.printf(
                "MOCK: Seria notificado %s através do email %s: %s\n",
                cliente.getNome(),
                cliente.getEmail(),
                mensagem
        );
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}
