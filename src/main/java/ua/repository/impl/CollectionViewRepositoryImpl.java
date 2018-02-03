package ua.repository.impl;

import static org.springframework.data.jpa.repository.query.QueryUtils.toOrders;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import ua.entity.Collection;
import ua.entity.Collection_;
import ua.model.filter.CollectionFilter;
import ua.model.view.CollectionView;
import ua.repository.CollectionViewRepository;

@Repository
public class CollectionViewRepositoryImpl implements CollectionViewRepository{

	@PersistenceContext
	private EntityManager em;	

	@Override
	public Page<CollectionView> findAllCollectionView(CollectionFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<CollectionView> cq = cb.createQuery(CollectionView.class).distinct(true);
		Root<Collection> root = cq.from(Collection.class);
		cq.multiselect(root.get(Collection_.id), root.get("name"), root.get("text"), root.get("date"), 
				root.get("photoUrl"), root.get("version"));
		Predicate predicate = new PredicateBuilder(cb, root, filter).toPredicate();
		if(predicate!=null) cq.where(predicate);
		cq.orderBy(toOrders(pageable.getSort(), root, cb));
		List<CollectionView> content = em.createQuery(cq)
				.setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
				.setMaxResults(pageable.getPageSize())
				.getResultList();		
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Collection> countRoot = countQuery.from(Collection.class);
		countQuery.select(cb.count(countRoot));
		Predicate countPredicate = new PredicateBuilder(cb, countRoot, filter).toPredicate();
		if(countPredicate!=null) countQuery.where(countPredicate);
		return PageableExecutionUtils.getPage(content, pageable, ()->em.createQuery(countQuery).getSingleResult());
	}
	
	private static class PredicateBuilder {
		
		final CriteriaBuilder cb;
		
		final Root<Collection> root;
		
		final CollectionFilter filter;
		
		final List<Predicate> predicates = new ArrayList<>();

		public PredicateBuilder(CriteriaBuilder cb, Root<Collection> root, CollectionFilter filter) {
			this.cb = cb;
			this.root = root;
			this.filter = filter;
		}
		
		void findBySearch() {
			if(!filter.getSearch().isEmpty()) {
				predicates.add(cb.like(root.get("name"), filter.getSearch()+"%"));
			}
		}
		
		Predicate toPredicate() {
			findBySearch();
			return predicates.isEmpty() ? null : cb.and(predicates.stream().toArray(Predicate[]::new));
		}		
		
	}

}
