package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Comment;
import ua.entity.Article;
import ua.model.view.ArticleView;

public interface ArticleRepository extends JpaRepository<Article, Integer>, JpaSpecificationExecutor<Article> {
	
	Article findByTitle(String title);
	
	@Query("SELECT a.title FROM Article a")
	List<String> findAllArticlesTitles();
	
////	@Query("SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.name, m.fullDescription, m.price, m.weight, c.name, m.rate) FROM Meal m JOIN m.cuisine c JOIN m.orders o WHERE o.id=?1")
//	List<ArticleView> findMealViewsForOrder(Integer orderId);
//	
	@Query("SELECT new ua.model.view.ArticleView(a.id, a.title, a.text, a.date, a.photoUrl, a.version) FROM Article a ORDER BY a.date DESC")
	List<ArticleView> findArticlesViewsByDate();
	
	@Query("SELECT a FROM Article a WHERE a.id=?1")
	Article findOneRequest(Integer id);
	
	@Query("SELECT a FROM Article a WHERE a.id=?1")
	Article findArticleById(Integer id);
	
	@Query("SELECT new ua.model.view.ArticleView(a.id, a.title, a.text, a.date, a.photoUrl, a.version) FROM Article a WHERE a.id=?1")
	ArticleView findArticleViewById(Integer id);
//	
////	@Query("SELECT distinct new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.name, m.fullDescription, m.price, m.weight, c.name, m.rate) FROM Meal m JOIN m.cuisine c JOIN m.components com WHERE components_id in ?1")
//	List<ArticleView> findMealViewByComponentId(List<Integer> id);
	
	@Query("SELECT c FROM Article a JOIN a.comments c WHERE a.id=?1")
	List<Comment> findCommentList(Integer id);
}
