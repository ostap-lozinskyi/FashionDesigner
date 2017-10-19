package ua.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import ua.entity.Meal;
import ua.entity.Order;
import ua.entity.Order_;
import ua.entity.Place;
import ua.model.filter.OrderFilter;
import ua.model.view.OrderView;
import ua.repository.OrderViewRepository;

@Repository
public class OrderViewRepositoryImpl implements OrderViewRepository{

	@PersistenceContext
	private EntityManager em;

	@Override
	public Page<OrderView> findAllView(OrderFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<OrderView> cq = cb.createQuery(OrderView.class);
		Root<Order> root = cq.from(Order.class);
		Join<Order, Place> joinPlace = root.join(Order_.place);
		cq.multiselect(root.get(Order_.id), joinPlace.get("number"), root.get("status"));
		Predicate predicate = new PredicateBuilder(cb, root, filter).toPredicate();
		if(predicate!=null) cq.where(predicate);
//		cq.orderBy(toOrders(pageable.getSort(), root, cb));
		cq.orderBy(cb.asc(root.get("status")));
		List<OrderView> content = em.createQuery(cq)
				.setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
				.setMaxResults(pageable.getPageSize())
				.getResultList();		
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Order> countRoot = countQuery.from(Order.class);
		countQuery.select(cb.count(countRoot));
		Predicate countPredicate = new PredicateBuilder(cb, countRoot, filter).toPredicate();
		if(countPredicate!=null) countQuery.where(countPredicate);
		return PageableExecutionUtils.getPage(content, pageable, ()->em.createQuery(countQuery).getSingleResult());
	}
	
	private static class PredicateBuilder {
		
		final CriteriaBuilder cb;
		
		final Root<Order> root;
		
		final OrderFilter filter;
		
		final List<Predicate> predicates = new ArrayList<>();

		public PredicateBuilder(CriteriaBuilder cb, Root<Order> root, OrderFilter filter) {
			this.cb = cb;
			this.root = root;
			this.filter = filter;
		}
		
		void findByPlacelId() {
			if(!filter.getPlaceNumber().isEmpty()) {
				Join<Order, Place> join = root.join(Order_.place);
				predicates.add(join.get("number").in(filter.getPlaceNumber()));
			}
		}
		
		void findByMealList() {
			if(!filter.getMealName().isEmpty()) {
				ListJoin<Order, Meal> join = root.join(Order_.meals);
				predicates.add(join.get("name").in(filter.getMealName()));
			}
		}
		
		void findByStatus() {
			if(!filter.getStatus().isEmpty()) {
				predicates.add(root.get("status").in(filter.getStatus()));
			}
		}
		
		Predicate toPredicate() {
			findByPlacelId();
			findByMealList();
			findByStatus();
			return predicates.isEmpty() ? null : cb.and(predicates.stream().toArray(Predicate[]::new));
		}		
		
	}

}
