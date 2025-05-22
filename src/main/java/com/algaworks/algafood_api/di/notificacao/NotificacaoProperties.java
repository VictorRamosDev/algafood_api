package com.algaworks.algafood_api.di.notificacao;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("notificador.email")
public class NotificacaoProperties {

    /**
     * Host do servidor de email
     */
    private String hostServidor;

    /**
     * Porta do servidor de email
     */
    private Integer portServidor = 25;

    public String getHostServidor() {
        return hostServidor;
    }

    public void setHostServidor(String hostServidor) {
        this.hostServidor = hostServidor;
    }

    public Integer getPortServidor() {
        return portServidor;
    }

    public void setPortServidor(Integer portServidor) {
        this.portServidor = portServidor;
    }
}
