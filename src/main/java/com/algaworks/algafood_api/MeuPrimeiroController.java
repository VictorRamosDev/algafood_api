package com.algaworks.algafood_api;

import com.algaworks.algafood_api.di.modelo.Cliente;
import com.algaworks.algafood_api.di.service.AtivacaoClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MeuPrimeiroController {

    private AtivacaoClienteService ativacaoClienteService;

    public MeuPrimeiroController(AtivacaoClienteService ativacaoClienteService) {
        this.ativacaoClienteService = ativacaoClienteService;
        System.out.println("MeuPrimeiroController: " + ativacaoClienteService);
    }

    @ResponseBody
    @GetMapping("/hello")
    public void hello() {
        Cliente cliente = new Cliente();
        cliente.setNome("Nikolas Ferreira");
        cliente.setEmail("nikolasferreira@teste.teste");
        cliente.setTelefone("83999999999");

        ativacaoClienteService.ativar(cliente);
    }

}
