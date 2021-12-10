package com.santander.api.weather.specifications;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.santander.api.weather.model.Weather;
import org.springframework.data.jpa.domain.Specification;


public class WeatherSpecification {

    private WeatherSpecification() {
    }

    public static Specification<Weather> findWeatherByFilters(final Map<String, Object> filters) {
        return new Specification<Weather>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<Weather> root, CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {

                List<Predicate> predicates = new ArrayList<>();

                filters.forEach((campo, valor) -> {
                    Predicate predicate = buildPredicate(root, criteriaBuilder, campo, valor);
                    if (predicate != null) {
                        predicates.add(predicate);
                    }
                });
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    private static Predicate buildPredicate(Root<Weather> root, CriteriaBuilder criteriaBuilder, String campo, Object valor) {
        switch (campo) {
            case "date":
                return criteriaBuilder.equal(root.get(campo), (Date)valor);

            case "city":
                return criteriaBuilder.equal(root.get(campo), (String)valor);

            default:
                return criteriaBuilder.equal(root.get(campo), valor);
        }

    }
}
