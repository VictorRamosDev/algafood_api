package com.algaworks.algafood_api.di.notificacao;

import com.algaworks.algafood_api.di.modelo.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile("prod")
@TipoDoNotificador(NivelUrgencia.SEM_URGENCIA)
@Component
public class NotificadorEmail implements Notificador {

    private boolean caixaAlta;

    @Autowired
    private NotificacaoProperties notificacaoProperties;

    public NotificadorEmail() {
        System.out.println("Bean 'NotificadorEmail' criado!");
    }

    @Override
    public void notificar(Cliente cliente, String mensagem) {
        System.out.printf(
                "Host Servidor: %s\nPorta Servidor: %d\n",
                this.notificacaoProperties.getHostServidor(),
                this.notificacaoProperties.getPortServidor()
        );

        if (caixaAlta) {
            mensagem = mensagem.toUpperCase();
        }

        System.out.printf(
                "Notificando %s através do email %s: %s\n",
                cliente.getNome(),
                cliente.getEmail(),
                mensagem
        );
    }

    public void setCaixaAlta(boolean caixaAlta) {
        this.caixaAlta = caixaAlta;
    }
}
