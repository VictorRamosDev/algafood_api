package com.algaworks.algafood_api.jpa;

import com.algaworks.algafood_api.AlgafoodApiApplication;
import com.algaworks.algafood_api.model.Cozinha;
import com.algaworks.algafood_api.model.FormaPagamento;
import com.algaworks.algafood_api.repository.CozinhaRepository;
import com.algaworks.algafood_api.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class BuscaFormaPagamentoMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                new SpringApplicationBuilder(AlgafoodApiApplication.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        FormaPagamentoRepository formaPagamentoRepository = configurableApplicationContext.getBean(FormaPagamentoRepository.class);

        FormaPagamento formaPagamento = formaPagamentoRepository.buscar(1L);

        System.out.println(formaPagamento);
    }
}
