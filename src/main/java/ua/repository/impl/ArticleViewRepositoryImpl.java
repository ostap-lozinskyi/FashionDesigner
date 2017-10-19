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

import ua.entity.Article;
import ua.entity.Article_;
import ua.model.filter.ArticleFilter;
import ua.model.view.ArticleView;
import ua.repository.ArticleViewRepository;

@Repository
public class ArticleViewRepositoryImpl implements ArticleViewRepository{

	@PersistenceContext
	private EntityManager em;	

	@Override
	public Page<ArticleView> findAllMealView(ArticleFilter filter, Pageable pageable) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ArticleView> cq = cb.createQuery(ArticleView.class).distinct(true);
		Root<Article> root = cq.from(Article.class);
		cq.multiselect(root.get(Article_.id), root.get("title"), root.get("text"), root.get("date"), 
				root.get("photoUrl"), root.get("version"));
		Predicate predicate = new PredicateBuilder(cb, root, filter).toPredicate();
		if(predicate!=null) cq.where(predicate);
		cq.orderBy(toOrders(pageable.getSort(), root, cb));
		List<ArticleView> content = em.createQuery(cq)
				.setFirstResult(pageable.getPageNumber()*pageable.getPageSize())
				.setMaxResults(pageable.getPageSize())
				.getResultList();		
		CriteriaQuery<Long> countQuery = cb.createQuery(Long.class);
		Root<Article> countRoot = countQuery.from(Article.class);
		countQuery.select(cb.count(countRoot));
		Predicate countPredicate = new PredicateBuilder(cb, countRoot, filter).toPredicate();
		if(countPredicate!=null) countQuery.where(countPredicate);
		return PageableExecutionUtils.getPage(content, pageable, ()->em.createQuery(countQuery).getSingleResult());
	}
	
	private static class PredicateBuilder {
		
		final CriteriaBuilder cb;
		
		final Root<Article> root;
		
		final ArticleFilter filter;
		
		final List<Predicate> predicates = new ArrayList<>();

		public PredicateBuilder(CriteriaBuilder cb, Root<Article> root, ArticleFilter filter) {
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
