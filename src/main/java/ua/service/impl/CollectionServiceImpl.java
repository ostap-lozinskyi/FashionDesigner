package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;

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


//	@Override
//	public List<CollectionView> findCollectionsViewsByDate() {
//		return collectionRepository.findCollectionViewsByDate();
//	}
	
	@Override
	public Page<CollectionView> findAllCollectionViews(CollectionFilter filter, Pageable pageable) {
		return collectionViewRepository.findAllCollectionView(filter, pageable);
	}

	@Override
	public void saveCollection(CollectionRequest collectionRequest) {
		Collection collection = new Collection();
		collection.setId(collectionRequest.getId());
		collection.setName(collectionRequest.getName());
		collection.setText(collectionRequest.getText());
		collectionRepository.save(collection);
	}

	@Override
	public CollectionRequest findOneRequest(Integer id) {
		Collection collection = collectionRepository.findOneRequest(id);
		CollectionRequest collectionRequest = new CollectionRequest();
		collectionRequest.setId(collection.getId());
		collectionRequest.setName(collection.getName());
		collectionRequest.setText(collection.getText());
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
	
}
