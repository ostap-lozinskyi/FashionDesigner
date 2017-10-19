package ua.service;

import java.security.Principal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.PlaceFilter;
import ua.model.request.PlaceRequest;
import ua.model.view.PlaceView;

public interface PlaceService {

	Page<PlaceView> findAllView(Pageable pageable, PlaceFilter filter);
	
	List<PlaceView> findAllPlaceViews();
	
	List<String> findAllPlacesCountOfPeople();
	
	List<PlaceView> findPlaceIdByUserId (Principal principal);
	
	void savePlace(PlaceRequest request);

	PlaceRequest findOneRequest(Integer id);

	void deletePlace(Integer id);

	void updatePlaceUserId(Integer placeId, Principal principal);
	
}
