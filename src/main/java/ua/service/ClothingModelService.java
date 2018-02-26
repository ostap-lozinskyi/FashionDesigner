package ua.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import ua.model.filter.ClothingModelFilter;
import ua.model.request.ClothingModelRequest;
import ua.model.view.ClothingModelView;

public interface ClothingModelService {

	List<ClothingModelView> findClothingModelViewsByDate();
	
	Page<ClothingModelView> findAllClothingModelViews(ClothingModelFilter clothingModelFilter, Pageable pageable);

	void saveClothingModel(ClothingModelRequest clothingModelRequest);

	ClothingModelRequest findOneRequest(Integer id);

	void deleteClothingModel(Integer id);
	
	ClothingModelView findClothingModelViewById(Integer id);
	
	ClothingModelRequest uploadPhotoToCloudinary(ClothingModelRequest clothingModelRequest, MultipartFile multipartFile) throws IOException;
	
}