package ua.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import ua.entity.Comment;
import ua.entity.Role;
import ua.model.filter.MealFilter;
import ua.model.request.CommentRequest;
import ua.model.view.IngredientView;
import ua.model.view.MealView;
import ua.service.CommentService;
import ua.service.IngredientService;
import ua.service.UserService;
import ua.validation.flag.CommentFlag;

@Controller
public class IngredientIdController {

	private final IngredientService ingredientService;

	private final CommentService commentService;

	private final UserService userService;

	Page<MealView> mealViews;

	String error = "";

	@Autowired
	public IngredientIdController(IngredientService service, CommentService commentService, UserService userService) {
		this.ingredientService = service;
		this.commentService = commentService;
		this.userService = userService;
	}

	@ModelAttribute("mealFilter")
	public MealFilter getFilter() {
		return new MealFilter();
	}

	@ModelAttribute("comment")
	public CommentRequest getForm() {
		return new CommentRequest();
	}

	/**
	 * Show Ingredient page
	 */
	@GetMapping("/ingredient/{id}")
	public String show(Model model, @PathVariable Integer id, @PageableDefault Pageable pageable,
			@ModelAttribute("mealFilter") MealFilter filter) {
		IngredientView ingredient = ingredientService.findIngredientViewById(id);
		ingredient.setComments(ingredientService.findCommentList(id));
		model.addAttribute("ingredient", ingredient);
		mealViews = ingredientService.searchMealsWithIngredient(id, filter, pageable);
		model.addAttribute("meals", mealViews);
		model.addAttribute("tasteMeal", error);
		error = "";
		if (ingredientService.findAllMealView(filter, pageable).hasContent() || pageable.getPageNumber() == 0)
			return "ingredientId";
		else
			return "redirect:/ingredient/{id}" + buildParams(pageable, filter);
	}

	/**
	 * Verifying comment possibility
	 */
	@PostMapping("/ingredient/{id}")
	public String ingredientIdComment(Model model, @PathVariable Integer id,
			@ModelAttribute("comment") @Validated(CommentFlag.class) CommentRequest commentRequest,
			Principal principal) {
		if (userService.findUserByEmail(principal.getName()).getRole() == Role.ROLE_ADMIN) {
			saveComment(id, commentRequest, principal);
			return "redirect:/ingredient/{id}";
		}
		if (mealViews != null) {
			if (userService.findMealInUserOrders(mealViews, principal) == true) {
				saveComment(id, commentRequest, principal);
			} else {
				error = "Taste the ingredient before the evaluation";
			}
		} else {
			error = "This ingredient is not used";
		}
		return "redirect:/ingredient/{id}";
	}

	/**
	 * Saving comment
	 */
	private void saveComment(Integer id, CommentRequest commentRequest, Principal principal) {
		Integer commentId = commentService.save(commentRequest, principal);
		Comment comment = commentService.findById(commentId);
		ingredientService.updateCommentsList(id, comment);
	}

	private String buildParams(Pageable pageable, MealFilter filter) {
		StringBuilder buffer = new StringBuilder();
		buffer.append("?page=");
		if (!(ingredientService.findAllMealView(filter, pageable).hasContent()))
			buffer.append(String.valueOf(pageable.getPageNumber()));
		else {
			buffer.append(String.valueOf(pageable.getPageNumber()));
		}
		buffer.append("&size=");
		buffer.append(String.valueOf(pageable.getPageSize()));
		if (pageable.getSort() != null) {
			buffer.append("&sort=");
			Sort sort = pageable.getSort();
			sort.forEach((order) -> {
				buffer.append(order.getProperty());
				if (order.getDirection() != Direction.ASC)
					buffer.append(",desc");
			});
		}
		buffer.append("&search=");
		buffer.append(filter.getSearch());
		return buffer.toString();
	}

}