package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Collection;
import ua.model.view.CollectionView;

public interface CollectionRepository extends JpaRepository<Collection, Integer>, JpaSpecificationExecutor<Collection> {
	
	Collection findByTitle(String title);
	
	@Query("SELECT c.title FROM Collection c")
	List<String> findAllCollectionsTitles();
	
	@Query("SELECT new ua.model.view.CollectionView(c.id, c.title, c.text, c.date, c.photoUrl, c.version) FROM Collection c ORDER BY c.date DESC")
	List<CollectionView> findCollectionViewsByDate();
	
	@Query("SELECT c FROM Collection c WHERE c.id=?1")
	Collection findOneRequest(Integer id);
	
	@Query("SELECT c FROM Collection c WHERE c.id=?1")
	Collection findCollectionById(Integer id);
	
	@Query("SELECT new ua.model.view.CollectionView(c.id, c.title, c.text, c.date, c.photoUrl, c.version) FROM Collection c WHERE c.id=?1")
	CollectionView findCollectionViewById(Integer id);

}
