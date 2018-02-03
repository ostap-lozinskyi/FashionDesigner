package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.TypeOfCollection;
import ua.model.view.TypeOfCollectionView;

public interface TypeOfCollectionRepository extends JpaNameRepository<TypeOfCollection>, JpaSpecificationExecutor<TypeOfCollection> {
	
	@Query("SELECT t FROM TypeOfCollection t WHERE t.id=?1")
	TypeOfCollection findTypeOfCollectionById(Integer id);	

	@Query("SELECT t.name FROM TypeOfCollection t")
	List<String> findAllTypeOfCollectionNames();
	
	@Query("SELECT new ua.model.view.TypeOfCollectionView(t.id, t.name) FROM TypeOfCollection t WHERE t.id=?1")
	TypeOfCollectionView findTypeOfCollectionViewById(Integer id);
	
}
