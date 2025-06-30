package com.algaworks.algafood_api.jpa;

import com.algaworks.algafood_api.AlgafoodApiApplication;
import com.algaworks.algafood_api.domain.model.FormaPagamento;
import com.algaworks.algafood_api.domain.repository.FormaPagamentoRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;

public class ConsultaFormaPagamentoMain {

    public static void main(String[] args) {
        ConfigurableApplicationContext configurableApplicationContext =
                new SpringApplicationBuilder(AlgafoodApiApplication.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        FormaPagamentoRepository formaPagamentoRepository = configurableApplicationContext.getBean(FormaPagamentoRepository.class);

        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAll();
        for (FormaPagamento formaPagamento : formaPagamentos) {
            System.out.println(formaPagamento);
        }
    }
}
