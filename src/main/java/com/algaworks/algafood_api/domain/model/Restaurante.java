package com.algaworks.algafood_api.domain.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Restaurante {

    @Id
    @EqualsAndHashCode.Include
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(name = "taxa_frete",nullable = false) //Opcional: Se o nome do atributo de classe for o mesmo da coluna da tabela
    private BigDecimal taxaFrete;

    @ManyToOne
    @JoinColumn(name = "cozinha_id", nullable = false) // Annotation par setar nome da coluna (chave estrangeira) na tabela Restaurante. É opcional, pois o padrão é "<nome da entidade>_id"
    private Cozinha cozinha;

}
