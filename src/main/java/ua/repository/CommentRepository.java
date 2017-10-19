package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>, JpaSpecificationExecutor<Comment> {
		
	@Query("SELECT c FROM Comment c WHERE c.id=?1")
	Comment findOneCommentRequest(Integer id);	
	
	@Query("SELECT c FROM Comment c WHERE c.id=?1")
	Comment findCommentById(Integer id);
}
