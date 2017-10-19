package ua.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.impl.GoogleTemplate;
import org.springframework.social.google.connect.GoogleConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Operations;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

import ua.entity.Role;
import ua.model.request.RegistrationRequest;
import ua.service.GoogleService;
import ua.service.UserService;

@Service
public class GoogleServiceImpl implements GoogleService {
    
	@Value("${spring.social.google.appId}")
	String googleAppId;

	@Value("${spring.social.google.appSecret}")
	String googleSecret;

	private final UserService userService;

	private final HttpServletRequest httpServletRequest;

	@Autowired
	public GoogleServiceImpl(UserService userService, HttpServletRequest httpServletRequest) {
		this.userService = userService;
		this.httpServletRequest = httpServletRequest;
	}

	private String makeRedirectUrl() {
		String serverName = httpServletRequest.getServerName();
		String serverPort = "";
		if (serverName.equals("localhost")) {
			serverPort = ":" + String.valueOf(httpServletRequest.getServerPort());
		}
		return "http://" + serverName + serverPort + "/google";
	}
    
	/**
	 * Creating Google Authorization URL 
	 */
	@Override
	public String createGoogleAuthorizationURL() {
		GoogleConnectionFactory connectionFactory = new GoogleConnectionFactory(googleAppId, googleSecret);
		OAuth2Operations oauthOperations = connectionFactory.getOAuthOperations();
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri(makeRedirectUrl());
		params.setScope("profile");
		return oauthOperations.buildAuthorizeUrl(params);
	}
    
    /**
	 * Getting User credentials from Google 
	 */
	@Override
	public List<String> createGoogleAccessToken(String code) {
		GoogleConnectionFactory connectionFactory = new GoogleConnectionFactory(googleAppId,
				googleSecret);
		AccessGrant accessGrant = connectionFactory.getOAuthOperations().exchangeForAccess(code, 
				makeRedirectUrl(), null);
		Google google = new GoogleTemplate(accessGrant.getAccessToken());
		String email = google.userOperations().getUserInfo().getName();
		String password = "1";
		String photoUrl = google.userOperations().getUserInfo().getProfilePictureUrl();
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
    	request.setRole(Role.ROLE_CLIENT);
	    request.setPassword(password);
	    request.setPhotoUrl(photoUrl);
	    userService.saveUser(request);
    }

}
