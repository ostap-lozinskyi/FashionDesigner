package ua.controller;

import java.security.Principal;

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
import ua.model.view.ArticleView;
import ua.service.CommentService;
import ua.service.ArticleService;
import ua.service.UserService;

@Controller
@RequestMapping("/article/{id}")
public class ArticleIdController {
	
	private final ArticleService articleService;

	private final CommentService commentService;
	
	private final UserService userService;
	
	String error="";

	@Autowired
	public ArticleIdController(ArticleService service, CommentService commentService, UserService userService) {
		this.articleService = service;
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
		ArticleView articleView = articleService.findMealViewById(id);
		articleView.setComments(articleService.findCommentList(id));
		model.addAttribute("articleView", articleView);
		model.addAttribute("tasteMeal", error);
		error="";
		return "articleId";
	}
	
	/**
	 * Commenting article
	 */
	@PostMapping
	public String articleIdComment(Model model, @PathVariable Integer id,
			@ModelAttribute("comment") CommentRequest commentRequest, Principal principal) {
		if (!commentRequest.getText().isEmpty()) {
			Integer commentId = commentService.save(commentRequest, principal);
			Comment comment = commentService.findById(commentId);
			articleService.updateComments(id, comment);
		}
		return "redirect:/article/{id}";
	}
	
}