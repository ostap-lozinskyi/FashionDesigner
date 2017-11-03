package ua.service;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import ua.entity.Role;
import ua.entity.User;
import ua.model.filter.UserFilter;
import ua.model.request.RegistrationRequest;

public interface UserService extends CrudService<User, Integer> {
	
	Page<User> findAllUsers(Pageable pageable, UserFilter filter);

	void saveUser(RegistrationRequest request);
	
	void setDefaultPhoto(Integer userId);
	
	void updateRole(Integer userId, Role role);

	User findUserByEmail(String email);
	
	void uploadPhotoToCloudinary(MultipartFile multipartFile, Principal principal);
	
}
