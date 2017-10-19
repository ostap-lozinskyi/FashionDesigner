package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>, JpaSpecificationExecutor<User> {

	User findUserByEmail(String email);

	boolean existsUserByEmail(String email);
	
	@Query("SELECT m.id FROM User u JOIN u.meals m WHERE u.id=?1")
	List<Integer> findMealIdByUserId(Integer id);
	
	@Query("SELECT m.id FROM User u JOIN u.meals m WHERE u.id=?1")
	List<Integer> findUserMealsIds(Integer userId);
	
	@Query("SELECT new ua.model.view.MealView(m.id, m.photoUrl, m.version, m.name, m.fullDescription, m.price, m.weight, c.name, m.rate) FROM User u JOIN u.meals m JOIN m.cuisine c WHERE u.id=?1")
	List<Integer> findUserMealViews(Integer userId);
	
}
