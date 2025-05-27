package com.algaworks.algafood_api.jpa;

import com.algaworks.algafood_api.AlgafoodApiApplication;
import com.algaworks.algafood_api.infrastructure.FormaPagamentoRepositoryImpl;
import com.algaworks.algafood_api.model.FormaPagamento;
import com.algaworks.algafood_api.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

public class ExclusaoFormaPagamentoMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(AlgafoodApiApplication.class)
                .web(WebApplicationType.NONE)
                .run(args);

        FormaPagamentoRepository formaPagamentoRepository = applicationContext.getBean(FormaPagamentoRepository.class);

        FormaPagamento formaPagamento = new FormaPagamento();
        formaPagamento.setId(1L);

        formaPagamentoRepository.remover(formaPagamento);
    }
}
