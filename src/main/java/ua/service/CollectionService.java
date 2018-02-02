package ua.service;

import java.io.IOException;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import ua.model.filter.CollectionFilter;
import ua.model.request.CollectionRequest;
import ua.model.view.CollectionView;

public interface CollectionService {

	List<CollectionView> findCollectionsViewsByDate();
	
	Page<CollectionView> findAllCollectionViews(CollectionFilter filter, Pageable pageable);

	void saveCollection(CollectionRequest request);

	CollectionRequest findOneRequest(Integer id);

	void deleteCollection(Integer id);
	
	CollectionView findCollectionViewById(Integer id);
	
	CollectionRequest uploadPhotoToCloudinary(CollectionRequest request, MultipartFile multipartFile) throws IOException;
	
}