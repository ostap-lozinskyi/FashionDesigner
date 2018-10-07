package ua.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.EmailCredentials;

public interface EmailRepository extends JpaRepository<EmailCredentials, Integer> {
	
	@Query("SELECT e FROM EmailCredentials e WHERE e.id=?1")
	EmailCredentials findEmailCredentialsById(Integer id);
}