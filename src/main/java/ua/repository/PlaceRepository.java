package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Place;
import ua.model.view.PlaceView;

public interface PlaceRepository extends JpaRepository<Place, Integer> {

	@Query("SELECT new ua.model.view.PlaceView(p.id, p.countOfPeople, p.number, p.isFree) FROM Place p ORDER BY number")
	List<PlaceView> findAllPlaceViews();
	
	@Query("SELECT distinct p.countOfPeople FROM Place p")
	List<String> findAllPlacesCountOfPeople();
	
	@Query("SELECT p FROM Place p WHERE p.id=?1")
	Place findOneRequest(Integer id);
	
	@Query("SELECT p FROM Place p WHERE p.id=?1")
	Place findPlaceById(Integer id);
	
	@Query("SELECT new ua.model.view.PlaceView(p.id, p.countOfPeople, p.number, p.isFree) FROM Place p WHERE p.id=?1")
	PlaceView findPlaceViewById(Integer id);
	
	@Query("SELECT new ua.model.view.PlaceView(p.id, p.countOfPeople, p.number, p.isFree) FROM Place p JOIN p.user u WHERE u.id=?1")
	List<PlaceView> findPlaceIdByUserId (Integer id);
	
	boolean existsByNumber(Integer number);
}
