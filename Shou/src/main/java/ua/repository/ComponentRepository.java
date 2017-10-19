package ua.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Component;
import ua.entity.Ingredient;
import ua.entity.Ms;
import ua.model.view.ComponentView;

public interface ComponentRepository extends JpaRepository<Component, Integer>, JpaSpecificationExecutor<Component> {

	@Query("SELECT new ua.model.view.ComponentView(c.id, c.amount, i.name, ms.name) FROM Component c JOIN c.ingredient i JOIN c.ms ms")
	List<ComponentView> findAllComponentsView();
	
	@Query(value = "SELECT new ua.model.view.ComponentView(c.id, c.amount, i.name, ms.name) FROM Component c JOIN c.ingredient i JOIN c.ms ms WHERE i.id=?1")
	List<ComponentView> findComponentViewByIngredientId(Integer id);
	
	@Query("SELECT c FROM Component c JOIN FETCH c.ingredient JOIN FETCH c.ms WHERE c.id=?1")
	Component findOneRequest(Integer id);
	
	@Query("SELECT c FROM Component c WHERE c.ingredient=?1 AND c.amount=?2 AND c.ms=?3")
	Component existsComponent(Ingredient ingredient, BigDecimal amount, Ms ms);	
	
}
