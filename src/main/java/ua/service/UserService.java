package ua.service;

import java.io.File;
import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Role;
import ua.entity.User;
import ua.model.filter.UserFilter;
import ua.model.request.RegistrationRequest;
import ua.model.view.MealView;

public interface UserService extends CrudService<User, Integer> {
	
	Page<User> findAllUsers(Pageable pageable, UserFilter filter);

	void saveUser(RegistrationRequest request);
	
	void setDefaultPhoto(Integer userId);
	
	void updateRole(Integer userId, Role role);

	User findUserByEmail(String email);
	
	void uploadPhotoToCloudinary(File toUpload, Principal principal);
	
	boolean findMealInUserOrders(Page<MealView> mealViews, Principal principal);
	
	List<Integer> findUserMealsIds(Principal principal);
	
	List<Integer> findUserMealViews(Principal principal);
}
