package ua.repository.impl;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import ua.entity.Component;
import ua.entity.Component_;
import ua.entity.Ingredient;
import ua.entity.Ms;
import ua.model.filter.ComponentFilter;
import ua.model.view.ComponentView;
import ua.repository.ComponentViewRepository;

@Repository
public class ComponentViewRepositoryImpl implements ComponentViewRepository{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<ComponentView> findAllView(ComponentFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ComponentView> cq = cb.createQuery(ComponentView.class);
		Root<Component> root = cq.from(Component.class);
		Join<Component, Ms> joinMs = root.join(Component_.ms);
		Join<Component, Ingredient> joinIngredient = root.join(Component_.ingredient);
		cq.multiselect(root.get(Component_.id), root.get("amount"), joinIngredient.get("name"), joinMs.get("name"));
		Predicate predicate = new PredicateBuilder(cb, root, filter).toPredicate();
		if(predicate!=null) cq.where(predicate);
		cq.orderBy(toOrders(pageable.getSort(), root, cb));
		List<ComponentView> content = em.createQuery(cq)
				.setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
				.setMaxResults(pageable.getPageSize())
				.getResultList();		
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Component> countRoot = countQuery.from(Component.class);
		countQuery.select(cb.count(countRoot));
		Predicate countPredicate = new PredicateBuilder(cb, countRoot, filter).toPredicate();
		if(countPredicate!=null) countQuery.where(countPredicate);
		return PageableExecutionUtils.getPage(content, pageable, ()->em.createQuery(countQuery).getSingleResult());
	}
	
	private static class PredicateBuilder {
		
		final CriteriaBuilder cb;
		
		final Root<Component> root;
		
		final ComponentFilter filter;
		
		final List<Predicate> predicates = new ArrayList<>();

		public PredicateBuilder(CriteriaBuilder cb, Root<Component> root, ComponentFilter filter) {
			this.cb = cb;
			this.root = root;
			this.filter = filter;
		}
		
		void findByMinAmount() {
			if(!filter.getMinAmount().isEmpty()) {
				predicates.add(cb.ge(root.get("amount"), new Integer(filter.getMinAmount())));
			}
		}
		
		void findByMaxAmount() {
			if(!filter.getMaxAmount().isEmpty()) {
				predicates.add(cb.le(root.get("amount"), new Integer(filter.getMaxAmount())));
			}
		}
		
		void findByMsId() {
			if(!filter.getMsName().isEmpty()) {
				Join<Component, Ms> join = root.join(Component_.ms);
				predicates.add(join.get("name").in(filter.getMsName()));
			}
		}
		
		void findByIngredientId() {
			if(!filter.getIngredientName().isEmpty()) {
				Join<Component, Ingredient> join = root.join(Component_.ingredient);
				predicates.add(join.get("name").in(filter.getIngredientName()));
			}
		}
		
		Predicate toPredicate() {
			findByMinAmount();
			findByMaxAmount();
			findByMsId();
			findByIngredientId();
			return predicates.isEmpty() ? null : cb.and(predicates.stream().toArray(Predicate[]::new));
		}		
		
	}

}
