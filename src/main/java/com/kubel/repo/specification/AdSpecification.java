package com.kubel.repo.specification;

import com.kubel.entity.Account;
import com.kubel.entity.Ad;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.Objects;

public class AdSpecification implements Specification<Ad> {
    private String city;
    private String topic;
    private Long userId;


    public AdSpecification(String city, String topic, Long userId) {
        this.city = city;
        this.topic = topic;
        this.userId = userId;
    }
    private Subquery<Account> getAccountQuery(final CriteriaQuery<?> query, final CriteriaBuilder builder) {
        var subQuery = query.subquery(Account.class);
        var subRoot = subQuery.from(Account.class);
        return subQuery.select(subRoot).where(builder.equal(subRoot.get("id"), userId));
    }


    @Override
    public Predicate toPredicate(Root<Ad> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

        var predicate = builder.isTrue(builder.literal(true));

        if (Objects.nonNull(userId)){
            var accountQuery = getAccountQuery(query, builder);
            predicate = builder.and(predicate, builder.equal(root.join("account"), accountQuery));
        }

        if (Objects.nonNull(city)) {
            city = city.toUpperCase();
            predicate = builder.and(predicate, builder.equal(builder.upper(root.get("city")), city));
        }
        if (Objects.nonNull(topic)) {
            topic = topic.toUpperCase();
            predicate = builder.and(predicate, builder.like(builder.upper(root.get("topic")), "%" + topic + "%"));
        }
        return predicate;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
