package ua.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.cloudinary.Cloudinary;

import ua.entity.TypeOfCollection;
import ua.model.filter.SimpleFilter;
import ua.model.view.TypeOfCollectionView;
import ua.repository.TypeOfCollectionRepository;
import ua.service.TypeOfCollectionService;

@Service
public class TypeOfCollectionServiceImpl extends CrudServiceImpl<TypeOfCollection, Integer> implements TypeOfCollectionService {

	private final TypeOfCollectionRepository typeOfCollectionRepository;
	
	@Value("${cloudinary.url}")
	Cloudinary cloudinary = new Cloudinary();

	@Autowired
	public TypeOfCollectionServiceImpl(TypeOfCollectionRepository typeOfCollectionRepository) {
		super(typeOfCollectionRepository);
		this.typeOfCollectionRepository = typeOfCollectionRepository;
	}

	@Override
	public Page<TypeOfCollection> findAll(Pageable pageable, SimpleFilter filter) {
		return typeOfCollectionRepository.findAll(filter(filter), pageable);
	}
	
	private Specification<TypeOfCollection> filter(SimpleFilter filter){
		return (root, query, cb) -> {
			if(filter.getSearch().isEmpty()) return null;
			return cb.like(root.get("name"), filter.getSearch()+"%");
		};
	}

	@Override
	public TypeOfCollectionView findTypeOfCollectionViewById(Integer id) {
		return typeOfCollectionRepository.findTypeOfCollectionViewById(id);
	}
	
//	public Page<SeasonView> findCollectionViewByTypeOfCollectionId(CollectionFilter collectionFilter, Pageable pageable) {
//		return collectionViewRepository.findAllCollectionView(collectionFilter, pageable);
//	}

}
