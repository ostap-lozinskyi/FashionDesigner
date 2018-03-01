package ua.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import ua.entity.ClothingModel;
import ua.model.view.ClothingModelView;

public interface ClothingModelRepository extends JpaRepository<ClothingModel, Integer>, JpaSpecificationExecutor<ClothingModel> {
	
	ClothingModel findByName(String name);
	
	@Query("SELECT cm.name FROM ClothingModel cm")
	List<String> findAllClothingModelNames();
	
	@Query("SELECT new ua.model.view.ClothingModelView(cm.id, cm.name, cm.date, cm.text,  cm.furniture, s.name, cm.photoUrl, cm.version)"
			+ "FROM ClothingModel cm JOIN cm.season s ORDER BY cm.date DESC")
	List<ClothingModelView> findClothingModelViewsByDate();
	
	@Query("SELECT cm FROM ClothingModel cm WHERE cm.id=?1")
	ClothingModel findOneRequest(Integer id);
	
	@Query("SELECT cm FROM ClothingModel cm WHERE cm.id=?1")
	ClothingModel findClothingModelById(Integer id);
	
	@Query("SELECT new ua.model.view.ClothingModelView(cm.id, cm.name, cm.date, cm.text,  cm.furniture, s.name, cm.photoUrl, cm.version)"
			+ "FROM ClothingModel cm JOIN cm.season s WHERE cm.id=?1")
	ClothingModelView findClothingModelViewById(Integer id);

}
