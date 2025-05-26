package com.algaworks.algafood_api.jpa;

import com.algaworks.algafood_api.AlgafoodApiApplication;
import com.algaworks.algafood_api.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ExclusaoCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                new SpringApplicationBuilder(AlgafoodApiApplication.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        CadastroCozinha cadastroCozinha = configurableApplicationContext.getBean(CadastroCozinha.class);
        Cozinha cozinha = new Cozinha();
        cozinha.setId(1L);
        cadastroCozinha.remover(cozinha);
    }
}
