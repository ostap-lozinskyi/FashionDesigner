package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.PlaceFilter;
import ua.model.view.PlaceView;

public interface PlaceViewRepository {

	Page<PlaceView> findAllView(PlaceFilter filter, Pageable pageable);
		
}
