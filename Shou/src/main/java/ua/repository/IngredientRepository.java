package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Comment;
import ua.entity.Ingredient;
import ua.model.view.IngredientView;

public interface IngredientRepository extends JpaNameRepository<Ingredient>, JpaSpecificationExecutor<Ingredient> {

	@Query("SELECT i.name FROM Ingredient i")
	List<String> findAllIngredientNames();
	
	@Query("SELECT new ua.model.view.IngredientView(i.id, i.name) FROM Ingredient i WHERE i.id=?1")
	IngredientView findIngredientViewById(Integer id);
	
	@Query("SELECT i FROM Ingredient i WHERE i.id=?1")
	Ingredient findIngredientById(Integer id);
	
	@Query("SELECT c FROM Ingredient i JOIN i.comments c WHERE i.id=?1")
	List<Comment> findCommentList(Integer id);
}
