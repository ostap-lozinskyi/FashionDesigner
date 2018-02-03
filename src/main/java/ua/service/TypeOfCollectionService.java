package ua.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.entity.TypeOfCollection;
import ua.model.filter.CollectionFilter;
import ua.model.filter.SimpleFilter;
import ua.model.view.CollectionView;
import ua.model.view.TypeOfCollectionView;

public interface TypeOfCollectionService extends CrudService<TypeOfCollection, Integer> {

	Page<TypeOfCollection> findAll(Pageable pageable, SimpleFilter filter);
	
	TypeOfCollectionView findTypeOfCollectionViewById(Integer id);
	
	Page<CollectionView> findCollectionViewByTypeOfCollectionId(CollectionFilter collectionFilter, Pageable pageable);
	
}
