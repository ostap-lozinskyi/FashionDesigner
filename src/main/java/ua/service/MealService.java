package ua.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.Comment;
import ua.model.filter.MealFilter;
import ua.model.request.MealRequest;
import ua.model.view.ComponentView;
import ua.model.view.MealIndexView;
import ua.model.view.MealView;

public interface MealService {

	List<String> findAllCuisinesNames();

	List<ComponentView> findAllСomponentsView();
	
	Page<MealIndexView> findAllMealIndexView(MealFilter filter, Pageable pageable);
	
	List<MealIndexView> find5MealIndexViewsByRate();
	
	Page<MealView> findAllMealView(MealFilter filter, Pageable pageable);

	void saveMeal(MealRequest request);

	MealRequest findOneRequest(Integer id);

	void deleteMeal(Integer id);
	
	void updateMealRate(Integer id, Integer newRate);
	
	void updateComments(Integer id, Comment comment);
	
	MealView findMealViewById(Integer id);
	
	List<Integer> findMealIdByUserId(Integer id);
	
	MealRequest uploadPhotoToCloudinary(MealRequest request, File toUpload) throws IOException;
	
	List<Comment> findCommentList(Integer id);

}