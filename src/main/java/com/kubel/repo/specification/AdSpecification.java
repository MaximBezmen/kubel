package com.kubel.repo.specification;

import com.kubel.entity.Ad;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Objects;

public class AdSpecification implements Specification<Ad> {
    private String city;
    private String topic;


    public AdSpecification(String city, String topic) {
        this.city = city;
        this.topic = topic;
    }


    @Override
    public Predicate toPredicate(Root<Ad> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        var predicate = builder.isTrue(builder.literal(true));

        if (Objects.nonNull(city)) {
            city = city.toUpperCase();
            predicate = builder.and(predicate, builder.equal(builder.upper(root.get("city")), city));
        }
        if (Objects.nonNull(topic)) {
            topic = topic.toUpperCase();
            predicate = builder.and(predicate, builder.like(builder.upper(root.get("topic")), topic));
        }
        return predicate;
    }
}
