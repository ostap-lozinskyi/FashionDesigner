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

import ua.entity.Collection;
import ua.model.filter.CollectionFilter;
import ua.model.request.CollectionRequest;
import ua.model.view.CollectionView;
import ua.repository.CollectionRepository;
import ua.repository.CollectionViewRepository;
import ua.service.CollectionService;

@Service
public class CollectionServiceImpl implements CollectionService {

	private final CollectionRepository collectionRepository;
	
	private final CollectionViewRepository collectionViewRepository;
	
	@Value("${cloudinary.url}")
	Cloudinary cloudinary = new Cloudinary();

	@Autowired
	public CollectionServiceImpl(CollectionRepository collectionRepository, CollectionViewRepository collectionViewRepository) {
		this.collectionRepository = collectionRepository;
		this.collectionViewRepository = collectionViewRepository;
	}


	@Override
	public List<CollectionView> findCollectionsViewsByDate() {
		return collectionRepository.findCollectionViewsByDate();
	}
	
	@Override
	public Page<CollectionView> findAllCollectionViews(CollectionFilter filter, Pageable pageable) {
		return collectionViewRepository.findAllCollectionView(filter, pageable);
	}

	@Override
	public void saveCollection(CollectionRequest collectionRequest) {
		Collection collection = new Collection();
		collection.setId(collectionRequest.getId());
		collection.setTitle(collectionRequest.getTitle());
		collection.setText(collectionRequest.getText());
		collection.setDate(collectionRequest.getDate());
		collection.setPhotoUrl(collectionRequest.getPhotoUrl());
		collection.setVersion(collectionRequest.getVersion());
		collectionRepository.save(collection);
	}

	@Override
	public CollectionRequest findOneRequest(Integer id) {
		Collection collection = collectionRepository.findOneRequest(id);
		CollectionRequest collectionRequest = new CollectionRequest();
		collectionRequest.setId(collection.getId());
		collectionRequest.setTitle(collection.getTitle());
		collectionRequest.setText(collection.getText());
		collectionRequest.setDate(collection.getDate());
		collectionRequest.setPhotoUrl(collection.getPhotoUrl());
		collectionRequest.setVersion(collection.getVersion());
		return collectionRequest;
	}

	@Override
	public void deleteCollection(Integer id) {
		collectionRepository.delete(id);
	}
	
	@Override
	public CollectionView findCollectionViewById(Integer id) {
		return collectionRepository.findCollectionViewById(id);
	}
	
	public CollectionRequest uploadPhotoToCloudinary(CollectionRequest request, MultipartFile multipartFile) throws IOException {
			@SuppressWarnings("rawtypes")
			Map uploadResult = cloudinary.uploader().upload(multipartFile.getBytes(),
					ObjectUtils.asMap("use_filename", "true", "unique_filename", "false"));
			String cloudinaryUrl = (String) uploadResult.get("url");
			String oldPhotoUrl = request.getPhotoUrl();
			if ((oldPhotoUrl != null) && (oldPhotoUrl.equals(cloudinaryUrl))) {
				request.setVersion(request.getVersion() + 1);
			} else {
				request.setVersion(0);
			}
			request.setPhotoUrl(cloudinaryUrl);
		return request;
	}
	
}
