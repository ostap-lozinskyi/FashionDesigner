package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.CollectionFilter;
import ua.model.request.CollectionRequest;
import ua.model.view.CollectionView;

public interface CollectionService {

//	List<CollectionView> findCollectionsViewsByDate();
	
	Page<CollectionView> findAllCollectionViews(CollectionFilter filter, Pageable pageable);

	void saveCollection(CollectionRequest request);

	CollectionRequest findOneRequest(Integer id);

	void deleteCollection(Integer id);
	
	CollectionView findCollectionViewById(Integer id);
	
}