package ua.service.impl;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ua.entity.Comment;
import ua.entity.User;
import ua.model.request.CommentRequest;
import ua.repository.CommentRepository;
import ua.repository.UserRepository;
import ua.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {

	private final CommentRepository repository;
	
	private final UserRepository userRepository;

	@Autowired
	public CommentServiceImpl(CommentRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}	

	@Override
	public Integer save(CommentRequest request, Principal principal) {
		User user = userRepository.findUserByEmail(principal.getName());
		Comment comment = new Comment();
		comment.setText(request.getText());
		comment.setUser(user);
		repository.save(comment);
		return comment.getId();
	}

	@Override
	public CommentRequest findOneRequest(Integer id) {
		Comment comment = repository.findOneCommentRequest(id);
		CommentRequest request = new CommentRequest();
		request.setId(comment.getId());
		request.setText(comment.getText());
		return request;
	}
	
	@Override
	public Comment findById(Integer id) {
		return repository.findCommentById(id);
	}

}
