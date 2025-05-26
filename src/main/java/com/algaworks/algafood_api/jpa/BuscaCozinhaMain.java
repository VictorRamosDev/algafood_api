package com.algaworks.algafood_api.jpa;

import com.algaworks.algafood_api.AlgafoodApiApplication;
import com.algaworks.algafood_api.model.Cozinha;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class BuscaCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                new SpringApplicationBuilder(AlgafoodApiApplication.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        CadastroCozinha cadastroCozinha = configurableApplicationContext.getBean(CadastroCozinha.class);

        Cozinha cozinha = cadastroCozinha.buscar(1L);

        System.out.println(cozinha);
    }
}
