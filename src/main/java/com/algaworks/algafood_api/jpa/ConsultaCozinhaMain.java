package com.algaworks.algafood_api.jpa;

import com.algaworks.algafood_api.AlgafoodApiApplication;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class ConsultaCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                new SpringApplicationBuilder(AlgafoodApiApplication.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        CozinhaRepository cozinhaRepository = configurableApplicationContext.getBean(CozinhaRepository.class);

        List<Cozinha> cozinhas = cozinhaRepository.listar();
        for (Cozinha cozinha : cozinhas) {
            System.out.println(cozinha);
        }

        List<Cozinha> list = cozinhaRepository.consultarPorNome("Tailandesa");
        System.out.println("Lista consultada por nome (Tailandesa):");
        System.out.println(list);
    }
}
