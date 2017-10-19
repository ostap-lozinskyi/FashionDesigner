package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Comment;
import ua.entity.Ingredient;
import ua.model.filter.MealFilter;
import ua.model.filter.SimpleFilter;
import ua.model.view.ComponentView;
import ua.model.view.IngredientView;
import ua.model.view.MealView;

public interface IngredientService extends CrudService<Ingredient, Integer> {

	Page<Ingredient> findAll(Pageable pageable, SimpleFilter filter);
	
	IngredientView findIngredientViewById(Integer id);
	
	List<ComponentView> findComponentViewByIngredientId(Integer id);
	
	Page<MealView> findAllMealView(MealFilter filter, Pageable pageable);
	
	Page<MealView> searchMealsWithIngredient(Integer ingredientId, MealFilter mealFilter, Pageable pageable);
	
	void updateCommentsList(Integer id, Comment comment);
	
	List<Comment> findCommentList(Integer id);

}
