package com.redhat.war.sample.data;

import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.redhat.war.sample.model.Member;

@ApplicationScoped
public class MemberRepository {

    @Inject
    private EntityManager em;

    public Member findById(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAllOrderedByName() {
        // using Hibernate Session and Criteria Query via Hibernate Native API
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);
        Root<Member> members = query.from(Member.class);
        query.orderBy(cb.asc(members.get("name")));
        return em.createQuery(query).getResultList();
    }
}
