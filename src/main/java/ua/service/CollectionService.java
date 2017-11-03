package ua.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import ua.entity.Comment;
import ua.model.filter.ArticleFilter;
import ua.model.request.ArticleRequest;
import ua.model.view.ArticleView;

public interface CollectionService {

	List<ArticleView> findArticlesViewsByDate();
	
	Page<ArticleView> findAllArticleViews(ArticleFilter filter, Pageable pageable);

	void saveArticle(ArticleRequest request);

	ArticleRequest findOneRequest(Integer id);

	void deleteMeal(Integer id);
	
	void updateComments(Integer id, Comment comment);
	
	ArticleView findMealViewById(Integer id);
	
	ArticleRequest uploadPhotoToCloudinary(ArticleRequest request, MultipartFile multipartFile) throws IOException;
	
	List<Comment> findCommentList(Integer id);

}