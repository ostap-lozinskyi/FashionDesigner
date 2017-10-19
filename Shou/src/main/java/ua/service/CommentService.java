package ua.service;

import java.security.Principal;

import ua.entity.Comment;
import ua.model.request.CommentRequest;

public interface CommentService {

	Integer save(CommentRequest request, Principal principal);
	
	CommentRequest findOneRequest(Integer id);
	
	Comment findById(Integer id);

}
