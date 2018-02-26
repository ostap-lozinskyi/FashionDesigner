package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.ClothingModelFilter;
import ua.model.view.ClothingModelView;

public interface ClothingModelViewRepository {

	Page<ClothingModelView> findAllClothingModelView(ClothingModelFilter clothingModelFilter, Pageable pageable);
		
}
