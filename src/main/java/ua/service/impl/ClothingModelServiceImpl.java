package ua.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import ua.entity.ClothingModel;
import ua.model.filter.ClothingModelFilter;
import ua.model.request.ClothingModelRequest;
import ua.model.view.ClothingModelView;
import ua.repository.ClothingModelRepository;
import ua.repository.ClothingModelViewRepository;
import ua.repository.CollectionRepository;
import ua.service.ClothingModelService;

@Service
public class ClothingModelServiceImpl implements ClothingModelService {

	private final ClothingModelRepository clothingModelRepository;
	
	private final ClothingModelViewRepository clothingModelViewRepository;
	
	private final CollectionRepository collectionRepository;
	
	@Value("${cloudinary.url}")
	Cloudinary cloudinary = new Cloudinary();

	@Autowired
	public ClothingModelServiceImpl(ClothingModelRepository clothingModelRepository, ClothingModelViewRepository clothingModelViewRepository,
			CollectionRepository collectionRepository) {
		this.clothingModelRepository = clothingModelRepository;
		this.clothingModelViewRepository = clothingModelViewRepository;
		this.collectionRepository = collectionRepository;
	}


	@Override
	public List<ClothingModelView> findClothingModelViewsByDate() {
		return clothingModelRepository.findClothingModelViewsByDate();
	}
	
	@Override
	public Page<ClothingModelView> findAllClothingModelViews(ClothingModelFilter filter, Pageable pageable) {
		return clothingModelViewRepository.findAllClothingModelView(filter, pageable);
	}

	@Override
	public void saveClothingModel(ClothingModelRequest clothingModelRequest) {
		ClothingModel clothingModel = new ClothingModel();
		clothingModel.setId(clothingModelRequest.getId());
		clothingModel.setName(clothingModelRequest.getName());
		clothingModel.setText(clothingModelRequest.getText());
		clothingModel.setDate(clothingModelRequest.getDate());
		clothingModel.setCollection(clothingModelRequest.getCollection());
		clothingModel.setPhotoUrl(clothingModelRequest.getPhotoUrl());
		clothingModel.setVersion(clothingModelRequest.getVersion());
		clothingModelRepository.save(clothingModel);
	}

	@Override
	public ClothingModelRequest findOneRequest(Integer id) {
		ClothingModel clothingModel = clothingModelRepository.findOneRequest(id);
		ClothingModelRequest clothingModelRequest = new ClothingModelRequest();
		clothingModelRequest.setId(clothingModel.getId());
		clothingModelRequest.setName(clothingModel.getName());
		clothingModelRequest.setText(clothingModel.getText());
		clothingModelRequest.setDate(clothingModel.getDate());
		clothingModelRequest.setCollection(clothingModel.getCollection());
		clothingModelRequest.setPhotoUrl(clothingModel.getPhotoUrl());
		clothingModelRequest.setVersion(clothingModel.getVersion());
		return clothingModelRequest;
	}

	@Override
	public void deleteClothingModel(Integer id) {
		clothingModelRepository.delete(id);
	}
	
	@Override
	public ClothingModelView findClothingModelViewById(Integer id) {
		return clothingModelRepository.findClothingModelViewById(id);
	}
	
	public ClothingModelRequest uploadPhotoToCloudinary(ClothingModelRequest clothingModelRequest, MultipartFile multipartFile) throws IOException {
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
					ObjectUtils.asMap("use_filename", "true", "unique_filename", "false"));
			String cloudinaryUrl = (String) uploadResult.get("url");
			String oldPhotoUrl = clothingModelRequest.getPhotoUrl();
			if ((oldPhotoUrl != null) && (oldPhotoUrl.equals(cloudinaryUrl))) {
				clothingModelRequest.setVersion(clothingModelRequest.getVersion() + 1);
			} else {
				clothingModelRequest.setVersion(0);
			}
			clothingModelRequest.setPhotoUrl(cloudinaryUrl);
		return clothingModelRequest;
	}
	
	@Override
	public List<String> findAllCollectionNames() {
		return collectionRepository.findAllCollectionsNames();
	}
	
}
