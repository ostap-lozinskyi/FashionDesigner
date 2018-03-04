package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.Color;

public interface ColorRepository extends JpaNameRepository<Color>, JpaSpecificationExecutor<Color> {
	
//	Season findByName(String name);
	
	@Query("SELECT c.name FROM Color c")
	List<String> findAllColorsNames();	
	
//	@Query("SELECT c FROM Collection c WHERE c.id=?1")
//	Season findCollectionById(Integer id);
	
//	@Query("SELECT new ua.model.view.CollectionView(c.id, c.name, c.text) FROM Collection c WHERE c.id=?1")
//	CollectionView findCollectionViewById(Integer id);

}
