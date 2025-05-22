package com.algaworks.algafood_api.di.notificacao;

import com.algaworks.algafood_api.di.modelo.Cliente;
import org.springframework.stereotype.Component;

@TipoDoNotificador(NivelUrgencia.URGENTE)
@Component
public class NotificadorSMS implements Notificador {

    private boolean caixaAlta;

    public NotificadorSMS() {
        System.out.println("Bean 'NotificadorSMS' criado!");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        if (caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }

        System.out.printf(
                "Notificando %s através do SMS %s: %s\n",
                cliente.getNome(),
                cliente.getTelefone(),
                mensagem
        );
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}
