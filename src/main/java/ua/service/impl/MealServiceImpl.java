package ua.service.impl;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ua.entity.Comment;
import ua.entity.Meal;
import ua.model.filter.MealFilter;
import ua.model.request.MealRequest;
import ua.model.view.ComponentView;
import ua.model.view.MealIndexView;
import ua.model.view.MealView;
import ua.repository.CommentRepository;
import ua.repository.ComponentRepository;
import ua.repository.CuisineRepository;
import ua.repository.MealRepository;
import ua.repository.MealViewRepository;
import ua.repository.UserRepository;
import ua.service.MealService;

@Service
public class MealServiceImpl implements MealService {

	private final MealRepository mealRepository;
	
	private final MealViewRepository mealViewRepository;
	
	private final CuisineRepository cuisineRepository;
	
	private final ComponentRepository componentRepository;
	
	private final UserRepository userRepository;
	
	@Value("${cloudinary.url}")
	Cloudinary cloudinary = new Cloudinary();

	@Autowired
	public MealServiceImpl(MealRepository repository, MealViewRepository mealViewrepository, 
			CuisineRepository cuisineRepository, ComponentRepository componentRepository, 
			UserRepository userRepository, CommentRepository commentRepository) {
		this.mealRepository = repository;
		this.mealViewRepository = mealViewrepository;
		this.cuisineRepository = cuisineRepository;
		this.componentRepository = componentRepository;
		this.userRepository = userRepository;
	}

	@Override
	public List<String> findAllCuisinesNames() {
		return cuisineRepository.findAllCuisinesNames();
	}

	@Override
	public List<ComponentView> findAllСomponentsView() {
		return componentRepository.findAllComponentsView();
	}

	@Override
	public Page<MealIndexView> findAllMealIndexView(MealFilter filter, Pageable pageable) {
		return mealViewRepository.findAllMealIndexView(filter, pageable);
	}
	
	@Override
	public List<MealIndexView> find5MealIndexViewsByRate() {
		return mealRepository.find5MealIndexViewsByRate();
	}
	
	@Override
	public Page<MealView> findAllMealView(MealFilter filter, Pageable pageable) {
		return mealViewRepository.findAllMealView(filter, pageable);
	}

	@Override
	public void saveMeal(MealRequest request) {
		Meal meal = new Meal();
		meal.setCuisine(request.getCuisine());
		meal.setFullDescription(request.getFullDescription());
		meal.setShortDescription(request.getShortDescription());
		meal.setId(request.getId());
		meal.setName(request.getName());
		meal.setPrice(new BigDecimal(request.getPrice()));
		meal.setWeight(Integer.valueOf(request.getWeight()));
		meal.setComponents(request.getComponents());
		meal.setPhotoUrl(request.getPhotoUrl());
		meal.setVersion(request.getVersion());
		mealRepository.save(meal);
	}

	@Override
	public MealRequest findOneRequest(Integer id) {
		Meal meal = mealRepository.findOneRequest(id);
		MealRequest request = new MealRequest();
		request.setCuisine(meal.getCuisine());
		request.setFullDescription(meal.getFullDescription());
		request.setShortDescription(meal.getShortDescription());
		request.setId(meal.getId());
		request.setName(meal.getName());
		request.setPrice(meal.getPrice().toString());
		request.setWeight(String.valueOf(meal.getWeight()));
		request.setComponents(meal.getComponents());
		request.setPhotoUrl(meal.getPhotoUrl());
		request.setVersion(meal.getVersion());
		return request;
	}

	@Override
	public void deleteMeal(Integer id) {
		mealRepository.delete(id);
	}
	
	@Override
	public void updateMealRate(Integer id, Integer newRate) {
		Meal meal = mealRepository.findMealById(id);
		meal.setVotesCount(meal.getVotesCount()+1);
		meal.setVotesAmount(meal.getVotesAmount()+newRate);
		mealRepository.save(meal);
		BigDecimal votesAmount=new BigDecimal(meal.getVotesAmount());
		BigDecimal votesCount=new BigDecimal(meal.getVotesCount());
		BigDecimal saveRate=votesAmount.divide(votesCount, 2, BigDecimal.ROUND_HALF_UP);
		meal.setRate(saveRate);		
		mealRepository.save(meal);		
	}
	
	@Override
	public void updateComments(Integer id, Comment comment) {
		Meal meal = mealRepository.findMealById(id);
		List<Comment> comments = meal.getComments();
		comments.add(comment);
		meal.setComments(comments);
		mealRepository.save(meal);
	}
	
	@Override
	public MealView findMealViewById(Integer id) {
		return mealRepository.findMealViewById(id);
	}
	
	@Override
	public List<Integer> findMealIdByUserId(Integer id) {
		return userRepository.findMealIdByUserId(id);
	}
	
	public MealRequest uploadPhotoToCloudinary(MealRequest request, File toUpload) throws IOException {
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(toUpload,
					ObjectUtils.asMap("use_filename", "true", "unique_filename", "false"));
			String cloudinaryUrl = (String) uploadResult.get("url");
			String oldPhotoUrl = request.getPhotoUrl();
			if ((oldPhotoUrl != null) && (oldPhotoUrl.equals(cloudinaryUrl))) {
				request.setVersion(request.getVersion() + 1);
			} else {
				request.setVersion(0);
			}
			request.setPhotoUrl(cloudinaryUrl);
		return request;
	}
	
	@Override
	public List<Comment> findCommentList(Integer id) {
		return mealRepository.findCommentList(id);
	}

}
