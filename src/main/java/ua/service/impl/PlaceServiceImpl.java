package ua.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ua.entity.Place;
import ua.entity.Role;
import ua.entity.User;
import ua.model.filter.PlaceFilter;
import ua.model.request.PlaceRequest;
import ua.model.view.PlaceView;
import ua.repository.PlaceRepository;
import ua.repository.PlaceViewRepository;
import ua.repository.UserRepository;
import ua.service.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {

	private final PlaceRepository repository;

	private final PlaceViewRepository placeViewRepository;
	
	private final UserRepository userRepository;

	@Autowired
	public PlaceServiceImpl(PlaceRepository repository, PlaceViewRepository placeViewRepository,
			UserRepository userRepository) {
		this.repository = repository;
		this.placeViewRepository = placeViewRepository;
		this.userRepository = userRepository;
	}

	@Override
	public Page<PlaceView> findAllView(Pageable pageable, PlaceFilter filter) {
		return placeViewRepository.findAllView(filter, pageable);
	}
	
	@Override
	public List<String> findAllPlacesCountOfPeople() {
		return repository.findAllPlacesCountOfPeople();
	}
	
	@Override
	public List<PlaceView> findAllPlaceViews() {
		return repository.findAllPlaceViews();
	}
	
	@Override
	public List<PlaceView> findPlaceIdByUserId (Principal principal) {
		if (principal != null) {
			User user = userRepository.findUserByEmail(principal.getName());
			if (!user.getRole().equals(Role.ROLE_ADMIN)) {
				return repository.findPlaceIdByUserId(user.getId());
			}
		}
		return null;
	};

	@Override
	public void savePlace(PlaceRequest request) {
		Place place = new Place();
		place.setCountOfPeople(Integer.valueOf(request.getCountOfPeople()));
		place.setId(request.getId());
		place.setNumber(Integer.valueOf(request.getNumber()));
		place.setFree(true);
		place.setUser(userRepository.findOne(1));
		repository.save(place);
	}

	@Override
	public PlaceRequest findOneRequest(Integer id) {
		Place place = repository.findOneRequest(id);
		PlaceRequest request = new PlaceRequest();
		request.setCountOfPeople(String.valueOf(place.getCountOfPeople()));
		request.setId(place.getId());
		request.setNumber(String.valueOf(place.getNumber()));
		return request;
	}

	@Override
	public void deletePlace(Integer id) {
		repository.delete(id);
	}
	
	@Override
	public void updatePlaceUserId(Integer placeId, Principal principal) {
		Place place = repository.findPlaceById(placeId);
		User user = userRepository.findUserByEmail(principal.getName());
		place.setUser(user);
		if (place.getUser().getId() == 1) {
			place.setFree(true);
		} else {
			place.setFree(false);
		}		
		repository.save(place);		
	}

}
