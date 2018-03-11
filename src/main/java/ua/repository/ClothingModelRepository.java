package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.ClothingModel;
import ua.model.view.ClothingModelView;

public interface ClothingModelRepository extends JpaNameRepository<ClothingModel>, JpaSpecificationExecutor<ClothingModel> {
	
	ClothingModel findByName(String name);
	
	@Query("SELECT cm.name FROM ClothingModel cm")
	List<String> findAllClothingModelNames();
		
	@Query("SELECT cm FROM ClothingModel cm WHERE cm.id=?1")
	ClothingModel findOneRequest(Integer id);
	
	@Query("SELECT cm FROM ClothingModel cm WHERE cm.id=?1")
	ClothingModel findClothingModelById(Integer id);
	
	@Query("SELECT new ua.model.view.ClothingModelView(cm.id, cm.name, cm.text, season.name, type.name, section.name)"
			+ "FROM ClothingModel cm JOIN cm.season season JOIN cm.typeOfClothes type JOIN cm.sectionOfClothes section WHERE cm.id=?1")
	ClothingModelView findClothingModelViewById(Integer id);
	
	@Query("SELECT p FROM ClothingModel cm JOIN cm.photoUrls p WHERE cm.id=?1")
	List<String> findPhotoUrls(Integer id);

}
