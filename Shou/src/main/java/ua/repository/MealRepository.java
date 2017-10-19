package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Comment;
import ua.entity.Meal;
import ua.model.view.MealIndexView;
import ua.model.view.MealView;

public interface MealRepository extends JpaNameRepository<Meal>, JpaSpecificationExecutor<Meal> {
	
	@Query("SELECT m.name FROM Meal m")
	List<String> findAllMealsNames();
	
	@Query("SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.name, m.fullDescription, m.price, m.weight, c.name, m.rate) FROM Meal m JOIN m.cuisine c JOIN m.orders o WHERE o.id=?1")
	List<MealView> findMealViewsForOrder(Integer orderId);
	
	@Query("SELECT new ua.model.view.MealIndexView(m.id, m.photoUrl, m.version, m.rate, m.price, m.weight, m.name, m.shortDescription) FROM Meal m ORDER BY m.rate DESC")
	List<MealIndexView> find5MealIndexViewsByRate();
	
	@Query("SELECT m FROM Meal m JOIN FETCH m.cuisine WHERE m.id=?1")
	Meal findOneRequest(Integer id);
	
	@Query("SELECT m FROM Meal m WHERE m.id=?1")
	Meal findMealById(Integer id);
	
	@Query("SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.name, m.fullDescription, m.price, m.weight, c.name, m.rate) FROM Meal m JOIN m.cuisine c WHERE m.id=?1")
	MealView findMealViewById(Integer id);
	
	@Query("SELECT distinct new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.name, m.fullDescription, m.price, m.weight, c.name, m.rate) FROM Meal m JOIN m.cuisine c JOIN m.components com WHERE components_id in ?1")
	List<MealView> findMealViewByComponentId(List<Integer> id);
	
	@Query("SELECT c FROM Meal m JOIN m.comments c WHERE m.id=?1")
	List<Comment> findCommentList(Integer id);
}
