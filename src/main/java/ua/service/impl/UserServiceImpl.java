package ua.service.impl;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ua.entity.Role;
import ua.entity.User;
import ua.model.filter.UserFilter;
import ua.model.request.RegistrationRequest;
import ua.repository.UserRepository;
import ua.service.UserService;

@Service
public class UserServiceImpl extends CrudServiceImpl<User, Integer> implements UserService{

	private final UserRepository userRepository;
	
	private final PasswordEncoder encoder;
	
	@Value("${cloudinary.url}")
	Cloudinary cloudinary = new Cloudinary();
	
	@Value("${user.default.photoUrl}")
	private String defaultPhotoUrl;
	
	public UserServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
		super(userRepository);
		this.userRepository = userRepository;
		this.encoder = encoder;
	}
	
	@Override
	public Page<User> findAllUsers(Pageable pageable, UserFilter filter) {
		return userRepository.findAll(filter(filter), pageable);
	}
	
	private Specification<User> filter(UserFilter filter){
		return (root, query, cb) -> {
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(root.get("email"), filter.getSearch()+"%");
		};
	}

	/**
	 * Saving new User	 
	 */
	@Override
	public void saveUser(RegistrationRequest request) {
		User user = new User();
		user.setEmail(request.getEmail());
		user.setPassword(encoder.encode(request.getPassword()));
		user.setRole(request.getRole());
		user.setPhotoUrl(defaultPhotoUrl);		
		userRepository.save(user);		
	}	
	
	@Override
	public void setDefaultPhoto(Integer userId) {
		User user = userRepository.findOne(userId);
		user.setPhotoUrl(defaultPhotoUrl);		
		userRepository.save(user);		
	}
	
	@Override
	public void updateRole(Integer userId, Role role) {
		User user = userRepository.findOne(userId);
		user.setRole(role);		
		userRepository.save(user);		
	}	
	
	
	@Override
	public User findUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	};
	
	@Override
	public void uploadPhotoToCloudinary(MultipartFile multipartFile, Principal principal) {
		String email = principal.getName();
		User user = findUserByEmail(email);
		@SuppressWarnings("rawtypes")
		Map uploadResult;
		try {
			uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
					ObjectUtils.asMap("use_filename", "true", "unique_filename", "false", "transformation", "w_150,h_150,c_fill,g_face,r_max"));
			String cloudinaryUrl = (String) uploadResult.get("url");
			String oldPhotoUrl = user.getPhotoUrl();
			if ((oldPhotoUrl != null) && (oldPhotoUrl.equals(cloudinaryUrl))) {
				user.setVersion(user.getVersion() + 1);
			} else {
				user.setVersion(0);
			}
			user.setPhotoUrl(cloudinaryUrl);
			userRepository.save(user);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
