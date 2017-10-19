package ua.repository.impl;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.math.BigDecimal;
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
import ua.entity.Cuisine;
import ua.entity.Meal;
import ua.entity.Meal_;
import ua.model.filter.MealFilter;
import ua.model.view.MealIndexView;
import ua.model.view.MealView;
import ua.repository.MealViewRepository;

@Repository
public class MealViewRepositoryImpl implements MealViewRepository{

	@PersistenceContext
	private EntityManager em;	

	@Override
	public Page<MealIndexView> findAllMealIndexView(MealFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MealIndexView> cq = cb.createQuery(MealIndexView.class);
		Root<Meal> root = cq.from(Meal.class);
		cq.multiselect(root.get(Meal_.id), root.get("photoUrl"), root.get("version"), root.get("rate"),
				root.get("price"), root.get("weight"), root.get("name"), root.get("shortDescription"));
		Predicate predicate = new PredicateBuilder(cb, root, filter).toPredicate();
		if(predicate!=null) cq.where(predicate);
		cq.orderBy(toOrders(pageable.getSort(), root, cb));
		List<MealIndexView> content = em.createQuery(cq)
				.setFirstResult(pageable.getPageNumber())
				.setMaxResults(pageable.getPageSize())
				.getResultList();
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Meal> countRoot = countQuery.from(Meal.class);
		countQuery.select(cb.count(countRoot));
		Predicate countPredicate = new PredicateBuilder(cb, countRoot, filter).toPredicate();
		if(countPredicate!=null) countQuery.where(countPredicate);
		return PageableExecutionUtils.getPage(content, pageable, ()->em.createQuery(countQuery).getSingleResult());
	}
	
	@Override
	public Page<MealView> findAllMealView(MealFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MealView> cq = cb.createQuery(MealView.class).distinct(true);
		Root<Meal> root = cq.from(Meal.class);
		Join<Meal, Cuisine> join = root.join(Meal_.cuisine);
		cq.multiselect(root.get(Meal_.id), root.get("photoUrl"), root.get("version"), root.get("name"), 
				root.get("fullDescription"), root.get("price"), root.get("weight"), join.get("name"), root.get("rate"));
		Predicate predicate = new PredicateBuilder(cb, root, filter).toPredicate();
		if(predicate!=null) cq.where(predicate);
		cq.orderBy(toOrders(pageable.getSort(), root, cb));
		List<MealView> content = em.createQuery(cq)
				.setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
				.setMaxResults(pageable.getPageSize())
				.getResultList();		
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Meal> countRoot = countQuery.from(Meal.class);
		countQuery.select(cb.count(countRoot));
		Predicate countPredicate = new PredicateBuilder(cb, countRoot, filter).toPredicate();
		if(countPredicate!=null) countQuery.where(countPredicate);
		return PageableExecutionUtils.getPage(content, pageable, ()->em.createQuery(countQuery).getSingleResult());
	}
	
	private static class PredicateBuilder {
		
		final CriteriaBuilder cb;
		
		final Root<Meal> root;
		
		final MealFilter filter;
		
		final List<Predicate> predicates = new ArrayList<>();

		public PredicateBuilder(CriteriaBuilder cb, Root<Meal> root, MealFilter filter) {
			this.cb = cb;
			this.root = root;
			this.filter = filter;
		}
		
		void findByMinRate() {
			if(!filter.getMinRate().isEmpty()) {
				predicates.add(cb.ge(root.get("rate"), new BigDecimal(filter.getMinRate().replace(',', '.'))));
			}
		}
		
		void findByMaxRate() {
			if(!filter.getMaxRate().isEmpty()) {
				predicates.add(cb.le(root.get("rate"), new BigDecimal(filter.getMaxRate().replace(',', '.'))));
			}
		}
		
		void findBySearch() {
			if(!filter.getSearch().isEmpty()) {
				predicates.add(cb.like(root.get("name"), filter.getSearch()+"%"));
			}
		}
		
		void findByCusinesId() {
			if(!filter.getCuisineName().isEmpty()) {
				Join<Meal, Cuisine> join = root.join(Meal_.cuisine);
				predicates.add(join.get("name").in(filter.getCuisineName()));
			}
		}
		
		void findByMinPrice() {
			if(!filter.getMinPrice().isEmpty()) {
				predicates.add(cb.ge(root.get("price"), new BigDecimal(filter.getMinPrice().replace(',', '.'))));
			}
		}
		
		void findByMaxPrice() {
			if(!filter.getMaxPrice().isEmpty()) {
				predicates.add(cb.le(root.get("price"), new BigDecimal(filter.getMaxPrice().replace(',', '.'))));
			}
		}
		
		void findByMinWeight() {
			if(!filter.getMinWeight().isEmpty()) {
				predicates.add(cb.ge(root.get("weight"), new Integer(filter.getMinWeight())));
			}
		}
		
		void findByMaxWeight() {
			if(!filter.getMaxWeight().isEmpty()) {
				predicates.add(cb.le(root.get("weight"), new Integer(filter.getMaxWeight())));
			}
		}
		
		void findByComponentList() {
			if(!filter.getComponentsId().isEmpty()) {
				Join<Meal, Component> join = root.join(Meal_.components);
				predicates.add(join.get("id").in(filter.getComponentsId()));
			}
		}
		
		Predicate toPredicate() {
			findByMinRate();
			findByMaxRate();
			findBySearch();
			findByCusinesId();
			findByMinPrice();
			findByMaxPrice();
			findByMinWeight();
			findByMaxWeight();
			findByComponentList();
			return predicates.isEmpty() ? null : cb.and(predicates.stream().toArray(Predicate[]::new));
		}		
		
	}

}
