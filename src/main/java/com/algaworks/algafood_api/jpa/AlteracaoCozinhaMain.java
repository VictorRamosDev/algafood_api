package com.algaworks.algafood_api.jpa;

import com.algaworks.algafood_api.AlgafoodApiApplication;
import com.algaworks.algafood_api.domain.model.Cozinha;
import com.algaworks.algafood_api.domain.repository.CozinhaRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class AlteracaoCozinhaMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                new SpringApplicationBuilder(AlgafoodApiApplication.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        CozinhaRepository cozinhaRepository = configurableApplicationContext.getBean(CozinhaRepository.class);

        Cozinha cozinha1 = new Cozinha();
        cozinha1.setId(1L);
        cozinha1.setNome("Brasileira");

        cozinhaRepository.save(cozinha1);
    }
}
