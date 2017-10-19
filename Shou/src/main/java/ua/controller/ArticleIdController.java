package ua.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.entity.Comment;
import ua.model.request.CommentRequest;
import ua.model.view.MealView;
import ua.service.CommentService;
import ua.service.MealService;
import ua.service.UserService;

@Controller
@RequestMapping("/article/{id}")
public class ArticleIdController {
	
	private final MealService service;

	private final CommentService commentService;
	
	private final UserService userService;
	
	String error="";

	@Autowired
	public ArticleIdController(MealService service, CommentService commentService, UserService userService) {
		this.service = service;
		this.commentService = commentService;
		this.userService = userService;
	}
	
	@ModelAttribute("comment")
	public CommentRequest getForm() {
		return new CommentRequest();
	}

	/**
	 * Show Article page
	 */
	@GetMapping
	public String show(Model model, @PathVariable Integer id) {
		MealView meal = service.findMealViewById(id);
		meal.setComments(service.findCommentList(id));
		model.addAttribute("meal", meal);
		model.addAttribute("tasteMeal", error);
		error="";
		return "articleId";
	}
	
	/**
	 * Commenting and setting rate
	 */
	@PostMapping
	public String mealIdCommentAndRate(Model model, @PathVariable Integer id,
			@ModelAttribute("comment") CommentRequest commentRequest, Principal principal) {
		List<Integer> userMealsIds = userService.findUserMealsIds(principal);
		if (userMealsIds.contains(id)) {
			if (commentRequest.getRate() != null) {
				Integer commentId = commentService.save(commentRequest, principal);
				Comment comment = commentService.findById(commentId);
				if (!commentRequest.getText().isEmpty()) {
					service.updateComments(id, comment);
				}
				service.updateMealRate(id, commentRequest.getRate());
			} else {
				error = "You must enter a rate!";
			}
		} else {
			error = "Taste the ingredient before the evaluation";
		}
		return "redirect:/meal/{id}";
	}
	
}