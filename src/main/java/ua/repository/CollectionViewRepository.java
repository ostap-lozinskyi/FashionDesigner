package ua.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ua.model.filter.CollectionFilter;
import ua.model.view.CollectionView;

public interface CollectionViewRepository {

	Page<CollectionView> findAllCollectionView(CollectionFilter filter, Pageable pageable);
		
}
