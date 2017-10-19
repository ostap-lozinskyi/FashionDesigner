package ua.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

import ua.entity.Role;
import ua.model.request.RegistrationRequest;
import ua.service.FacebookService;
import ua.service.UserService;

@Service
public class FacebookServiceImpl implements FacebookService{
    
	@Value("${spring.social.facebook.appId}")
	String facebookAppId;

	@Value("${spring.social.facebook.appSecret}")
	String facebookSecret;

	private final UserService userService;

	private final HttpServletRequest httpServletRequest;

	@Autowired
	public FacebookServiceImpl(UserService userService, HttpServletRequest httpServletRequest) {
		this.userService = userService;
		this.httpServletRequest = httpServletRequest;
	}

	private String makeRedirectUrl() {
		String serverName = httpServletRequest.getServerName();
		String serverPort = "";
		if (serverName.equals("localhost")) {
			serverPort = ":" + String.valueOf(httpServletRequest.getServerPort());
		}
		return "http://" + serverName + serverPort + "/facebook";
	}
    
	/**
	 * Creating Facebook Authorization URL 
	 */
	@Override
	public String createFacebookAuthorizationURL() {
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId, facebookSecret);
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri(makeRedirectUrl());
		params.setScope("public_profile,email,user_birthday");
		return oauthOperations.buildAuthorizeUrl(params);
	}
    
    /**
	 * Getting User credentials from Facebook 
	 */
	@Override
	public List<String> createFacebookAccessToken(String code) {
		FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory(facebookAppId,
				facebookSecret);
		AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, 
				makeRedirectUrl(), null);
		Facebook facebook = new FacebookTemplate(accessGrant.getAccessToken());
		User fbUser = facebook.fetchObject("me", User.class, "name");
		String email = fbUser.getName();
		String password = "1";
		String photoUrl = "http://graph.facebook.com/" + fbUser.getId() + "/picture?width=300&height=300";
		ua.entity.User user = userService.findUserByEmail(email);
		if (user == null) {
			createNewUser(email, password, photoUrl);
		}
		List<String> credentials = new ArrayList<>();
		credentials.add(email);
		credentials.add(password);
		return credentials;
	}
    
    /**
	 * Creating new User 
	 */
	private void createNewUser(String email, String password, String photoUrl) {
    	RegistrationRequest request = new RegistrationRequest();
    	request.setEmail(email);
	    request.setPassword(password);
	    request.setRole(Role.ROLE_CLIENT);
	    request.setPhotoUrl(photoUrl);
	    userService.saveUser(request);
    }

}
