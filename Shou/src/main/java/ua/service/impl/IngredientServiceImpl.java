package ua.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import ua.entity.Comment;
import ua.entity.Ingredient;
import ua.model.filter.MealFilter;
import ua.model.filter.SimpleFilter;
import ua.model.view.ComponentView;
import ua.model.view.IngredientView;
import ua.model.view.MealView;
import ua.repository.ComponentRepository;
import ua.repository.IngredientRepository;
import ua.repository.MealViewRepository;
import ua.service.IngredientService;

@Service
public class IngredientServiceImpl extends CrudServiceImpl<Ingredient, Integer> implements IngredientService {

	private final IngredientRepository repository;
	
	private final ComponentRepository componentRepository;
	
	private final MealViewRepository mealViewRepository;

	@Autowired
	public IngredientServiceImpl(IngredientRepository repository, ComponentRepository componentRepository,
			MealViewRepository mealViewRepository) {
		super(repository);
		this.repository = repository;
		this.componentRepository = componentRepository;
		this.mealViewRepository = mealViewRepository;
	}

	@Override
	public Page<Ingredient> findAll(Pageable pageable, SimpleFilter filter) {
		return repository.findAll(filter(filter), pageable);
	}
	
	private Specification<Ingredient> filter(SimpleFilter filter){
		return (root, query, cb) -> {
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(root.get("name"), filter.getSearch()+"%");
		};
	}
	
	@Override
	public IngredientView findIngredientViewById(Integer id) {
		return repository.findIngredientViewById(id);
	}
	
	@Override
	public List<ComponentView> findComponentViewByIngredientId(Integer id) {
		return componentRepository.findComponentViewByIngredientId(id);
	}
	
	@Override
	public Page<MealView> findAllMealView(MealFilter filter, Pageable pageable) {
		return mealViewRepository.findAllMealView(filter, pageable);
	}
	
	/**
	 * Searching meals with ingredient	 
	 */
	@Override
	public Page<MealView> searchMealsWithIngredient(Integer ingredientId, MealFilter mealFilter, Pageable pageable) {
		List<ComponentView> componentsList = findComponentViewByIngredientId(ingredientId);
		if (!componentsList.isEmpty()) {
			List<Integer> componentsIds = new ArrayList<>();
			for (ComponentView componentView : componentsList) {
				componentsIds.add(componentView.getId());
			}
			mealFilter.setComponentsId(componentsIds);
			return findAllMealView(mealFilter, pageable);			
		} else {
			return null;
		}		
	}
	
	@Override
	public void updateCommentsList(Integer id, Comment newComment) {
		Ingredient ingredient = repository.findIngredientById(id);
		List<Comment> comments = ingredient.getComments();
		comments.add(newComment);
		ingredient.setComments(comments);
		repository.save(ingredient);
	}
	
	@Override
	public List<Comment> findCommentList(Integer id) {
		return repository.findCommentList(id);
	}

}
