package ua.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.ComponentFilter;
import ua.model.request.ComponentRequest;
import ua.model.view.ComponentView;

public interface ComponentService {

	List<String> findAllIngredients();

	List<String> findAllMss();

//	Page<ComponentView> findAllView(Pageable pageable);
	
//	Page<Component> findAll(Pageable pageable, SimpleFilter filter);
	
	Page<ComponentView> findAllView(Pageable pageable, ComponentFilter filter);

	void save(ComponentRequest request);

	ComponentRequest findOneRequest(Integer id);

	void delete(Integer id);
}
