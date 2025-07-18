package com.algaworks.algafood_api.infrastructure.repository;

import com.algaworks.algafood_api.domain.model.Restaurante;
import com.algaworks.algafood_api.domain.repository.RestauranteRepositoryCustom;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Restaurante> findByJpql(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
        var jpql = new StringBuilder("from Restaurante where 1=1 ");
        var parametros = new HashMap<String, Object>();

        if (StringUtils.hasLength(nome)) {
            jpql.append("and nome like :nome ");
            parametros.put("nome", "%" + nome + "%");
        }
        if (taxaFreteInicial != null) {
            jpql.append("and taxaFrete >= :taxaInicial ");
            parametros.put("taxaInicial", taxaFreteInicial);
        }
        if (taxaFreteFinal != null) {
            jpql.append("and taxaFrete <= :taxaFinal ");
            parametros.put("taxaFinal", taxaFreteFinal);
        }

        TypedQuery<Restaurante> query = entityManager.createQuery(jpql.toString(), Restaurante.class);

        parametros.forEach((key, value) -> query.setParameter(key, value));
        return query.getResultList();
    }

    @Override
    public List<Restaurante> findByCriteria(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<Restaurante> criteriaQuery = builder.createQuery(Restaurante.class);

        Root<Restaurante> root = criteriaQuery.from(Restaurante.class);
        Predicate nomePredicate = builder.like(root.get("nome"), "%" + nome + "%");
        Predicate taxaInicialPredicate = builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial);
        Predicate taxaFinalPredicate = builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal);

        criteriaQuery.where(nomePredicate, taxaInicialPredicate, taxaFinalPredicate);

        TypedQuery<Restaurante> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    @Override
    public List<Restaurante> findByCriteriaOptimized(String nome, BigDecimal taxaInicial, BigDecimal taxaFinal) {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
        Root<Restaurante> root = criteria.from(Restaurante.class);

        List<Predicate> predicates = new ArrayList<>();
        if (StringUtils.hasLength(nome)) {
            predicates.add(builder.like(root.get("nome"), "%" + nome + "%"));
        }
        if (taxaInicial != null) {
            predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaInicial));
        }
        if (taxaFinal != null) {
            predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFinal));
        }

        criteria.where(predicates.toArray(new Predicate[0]));
        TypedQuery<Restaurante> query = entityManager.createQuery(criteria);
        return query.getResultList();
    }

}