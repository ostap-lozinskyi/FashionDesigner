package ua.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ua.entity.Comment;
import ua.entity.Article;
import ua.model.filter.ArticleFilter;
import ua.model.request.ArticleRequest;
import ua.model.view.ArticleView;
import ua.repository.CommentRepository;
import ua.repository.ArticleRepository;
import ua.repository.ArticleViewRepository;
import ua.service.CollectionService;

@Service
public class CollectionServiceImpl implements CollectionService {

	private final ArticleRepository articleRepository;
	
	private final ArticleViewRepository articleViewRepository;
	
	@Value("${cloudinary.url}")
	Cloudinary cloudinary = new Cloudinary();

	@Autowired
	public CollectionServiceImpl(ArticleRepository articleRepository, ArticleViewRepository articleViewRepository, 
			CommentRepository commentRepository) {
		this.articleRepository = articleRepository;
		this.articleViewRepository = articleViewRepository;
	}

//	@Override
//	public Page<MealIndexView> findAllMealIndexView(MealFilter filter, Pageable pageable) {
//		return mealViewRepository.findAllMealIndexView(filter, pageable);
//	}
	
	@Override
	public List<ArticleView> findArticlesViewsByDate() {
		return articleRepository.findArticlesViewsByDate();
	}
	
	@Override
	public Page<ArticleView> findAllArticleViews(ArticleFilter filter, Pageable pageable) {
		return articleViewRepository.findAllMealView(filter, pageable);
	}

	@Override
	public void saveArticle(ArticleRequest articleRequest) {
		Article article = new Article();
		article.setId(articleRequest.getId());
		article.setTitle(articleRequest.getTitle());
		article.setText(articleRequest.getText());
		article.setDate(articleRequest.getDate());
		article.setPhotoUrl(articleRequest.getPhotoUrl());
		article.setVersion(articleRequest.getVersion());
		articleRepository.save(article);
	}

	@Override
	public ArticleRequest findOneRequest(Integer id) {
		Article article = articleRepository.findOneRequest(id);
		ArticleRequest articleRequest = new ArticleRequest();
		articleRequest.setId(article.getId());
		articleRequest.setTitle(article.getTitle());
		articleRequest.setText(article.getText());
		articleRequest.setDate(article.getDate());
		articleRequest.setPhotoUrl(article.getPhotoUrl());
		articleRequest.setVersion(article.getVersion());
		return articleRequest;
	}

	@Override
	public void deleteMeal(Integer id) {
		articleRepository.delete(id);
	}
	
	@Override
	public void updateComments(Integer id, Comment comment) {
		Article article = articleRepository.findArticleById(id);
		List<Comment> comments = article.getComments();
		comments.add(comment);
		article.setComments(comments);
		articleRepository.save(article);
	}
	
	@Override
	public ArticleView findMealViewById(Integer id) {
		return articleRepository.findArticleViewById(id);
	}
	
	public ArticleRequest uploadPhotoToCloudinary(ArticleRequest request, MultipartFile multipartFile) throws IOException {
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
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
		return articleRepository.findCommentList(id);
	}

}
